package ch.ffhs.drugstore.presentation.dispensary.viewmodel;

import static org.junit.Assert.assertEquals;

import static util.LiveDataTestUtil.getValue;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.github.javafaker.Faker;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.data.repository.DrugTypeRepository;
import ch.ffhs.drugstore.data.repository.SubstanceRepository;
import ch.ffhs.drugstore.data.repository.TransactionRepository;
import ch.ffhs.drugstore.data.repository.UnitRepository;
import ch.ffhs.drugstore.data.repository.UserRepository;
import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.service.HistoryService;
import ch.ffhs.drugstore.domain.service.UserService;
import ch.ffhs.drugstore.domain.usecase.dispensary.DispenseDrug;
import ch.ffhs.drugstore.domain.usecase.dispensary.ToggleDrugIsFavorite;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import ch.ffhs.drugstore.shared.exceptions.InsufficientAmountException;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;
import util.TestUtil;

@RunWith(AndroidJUnit4.class)
public class DispensaryIntegrationTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final Application appContext = ApplicationProvider.getApplicationContext();
    private final Faker faker = new Faker();
    private final DrugstoreMapper mapper = DrugstoreMapper.INSTANCE;
    private DrugstoreDatabase db;
    private DrugRepository drugRepository;
    private DrugTypeRepository drugTypeRepository;
    private SubstanceRepository substanceRepository;
    private UnitRepository unitRepository;
    private UserRepository userRepository;
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        db = Room.inMemoryDatabaseBuilder(appContext, DrugstoreDatabase.class).build();

        drugRepository = new DrugRepository(db.drugDao());
        drugTypeRepository = new DrugTypeRepository(db.drugTypeDao());
        substanceRepository = new SubstanceRepository(db.substanceDao());
        unitRepository = new UnitRepository(db.unitDao());
        userRepository = new UserRepository(db.userDao());
        transactionRepository = new TransactionRepository(db.transactionDao());
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }


    @Test
    public void toggleDrugIsFavorite() throws DrugstoreException {
        // Arrange
        DispensaryService dispensaryService = new DispensaryService(drugRepository);

        ToggleDrugIsFavorite toggleDrugIsFavorite = new ToggleDrugIsFavorite(dispensaryService);
        DispensaryViewModel dispensaryViewModel = new DispensaryViewModel(appContext, null,
                toggleDrugIsFavorite, null, null);
        int drugId = insertDrug();
        boolean oldIsFavorite = drugRepository.getDrugById(drugId).isFavorite();

        // Act
        dispensaryViewModel.toggleDrugIsFavorite(drugId);

        // Verify
        DrugDto drug = drugRepository.getDrugById(drugId);
        assertEquals(!oldIsFavorite, drug.isFavorite());
    }

    @Test(expected = DrugNotFoundException.class)
    public void toggleDrugIsFavoriteThrowsIfDrugIsNotFound() throws DrugstoreException {
        // Arrange
        DispensaryService dispensaryService = new DispensaryService(drugRepository);

        ToggleDrugIsFavorite toggleDrugIsFavorite = new ToggleDrugIsFavorite(dispensaryService);
        DispensaryViewModel dispensaryViewModel = new DispensaryViewModel(appContext, null,
                toggleDrugIsFavorite, null, null);

        // Act
        dispensaryViewModel.toggleDrugIsFavorite(-1);
    }

    @Test
    public void dispenseDrug() throws DrugstoreException, InterruptedException {
        // Arrange
        DrugManagementService drugManagementService = new DrugManagementService(drugRepository,
                drugTypeRepository, substanceRepository, unitRepository);
        UserService userService = new UserService(userRepository);
        HistoryService historyService = new HistoryService(transactionRepository, userService);

        DispenseDrug dispenseDrug = new DispenseDrug(drugManagementService, historyService);
        DispensaryViewModel dispensaryViewModel = new DispensaryViewModel(appContext, null, null,
                dispenseDrug, null);

        int drugId = insertDrug();
        drugRepository.updateDrugAmount(drugId, 10);

        String employeeShortName = faker.funnyName().name();
        String patient = faker.funnyName().name();
        double patientAmount = faker.number().numberBetween(1, 10);
        String dosage = Double.toString(patientAmount);

        double expectedAmount = 10 - patientAmount;

        // Act
        dispensaryViewModel.dispenseDrug(drugId, employeeShortName, patient, dosage);

        // Verify
        List<TransactionDto> allTransactions = getValue(transactionRepository.getAllTransactions());
        DrugDto drug = drugRepository.getDrugById(drugId);
        assertEquals(1, allTransactions.size());
        assertEquals(patientAmount, allTransactions.get(0).getAmount(), 0);
        assertEquals(expectedAmount, drug.getStockAmount(), 0);

    }

    @Test(expected = InsufficientAmountException.class)
    public void dispenseDrugThrowsInsufficientExceptionIfAmountIsTooHigh()
            throws DrugstoreException, InterruptedException {
        // Arrange
        DrugManagementService drugManagementService = new DrugManagementService(drugRepository,
                drugTypeRepository, substanceRepository, unitRepository);
        UserService userService = new UserService(userRepository);
        HistoryService historyService = new HistoryService(transactionRepository, userService);

        DispenseDrug dispenseDrug = new DispenseDrug(drugManagementService, historyService);
        DispensaryViewModel dispensaryViewModel = new DispensaryViewModel(appContext, null, null,
                dispenseDrug, null);

        int drugId = insertDrug();
        String employeeShortName = faker.funnyName().name();
        String patient = faker.funnyName().name();
        String dosage = Integer.toString(faker.number().randomDigit());

        // Act
        dispensaryViewModel.dispenseDrug(drugId, employeeShortName, patient, dosage);
    }


    private int insertDrug() {
        DrugManagementService drugManagementService = new DrugManagementService(drugRepository,
                drugTypeRepository, substanceRepository, unitRepository);

        CreateDrugDto createDrugDto = TestUtil.createCreateDrugDto();

        insertDrugType(createDrugDto.getDrugTypeId());
        insertUnit(createDrugDto.getUnitId());

        return (int) drugManagementService.createDrug(createDrugDto);
    }

    private void insertDrugType(int drugTypeId) {
        DrugTypeDto drugTypeDto = TestUtil.createDrugTypeDto(drugTypeId);
        db.drugTypeDao().insert(
                mapper.drugTypeFromDrugTypeDto(drugTypeDto));
    }


    private void insertUnit(int unitId) {
        UnitDto unitDto = TestUtil.createDrugUnitDto(unitId);
        db.unitDao().insert(mapper.unitFromUnitDto(unitDto));
    }
}