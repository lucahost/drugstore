package ch.ffhs.drugstore.presentation.management.drugs.viewmodel;

import static org.junit.Assert.assertEquals;

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
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.data.repository.DrugTypeRepository;
import ch.ffhs.drugstore.data.repository.SubstanceRepository;
import ch.ffhs.drugstore.data.repository.TransactionRepository;
import ch.ffhs.drugstore.data.repository.UnitRepository;
import ch.ffhs.drugstore.data.repository.UserRepository;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.service.HistoryService;
import ch.ffhs.drugstore.domain.service.UserService;
import ch.ffhs.drugstore.domain.usecase.management.drugs.CreateDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.DeleteDrug;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.exceptions.DrugAlreadyUsedException;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;
import util.LiveDataTestUtil;
import util.TestUtil;

@RunWith(AndroidJUnit4.class)
public class DeleteDrugIntegrationTest {
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

    @Before
    public void setUp() {
        db = Room.inMemoryDatabaseBuilder(appContext, DrugstoreDatabase.class).build();

        drugRepository = new DrugRepository(db.drugDao());
        drugTypeRepository = new DrugTypeRepository(db.drugTypeDao());
        substanceRepository = new SubstanceRepository(db.substanceDao());
        unitRepository = new UnitRepository(db.unitDao());
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test(expected = DrugAlreadyUsedException.class)
    public void DeleteDrugWithTransactionThrowsDrugAlreadyUsedException() throws Exception {
        // Assert
        DrugManagementService drugManagementService = new DrugManagementService(drugRepository,
                drugTypeRepository, substanceRepository, unitRepository);

        DeleteDrug deleteDrug = new DeleteDrug(drugManagementService);

        DrugsViewModel drugsViewModel = new DrugsViewModel(appContext, null, null, null, null,
                deleteDrug, null, null, null);


        CreateDrugDto createDrugDto = TestUtil.createCreateDrugDto();

        insertDrugType(createDrugDto.getDrugTypeId());
        insertUnit(createDrugDto.getUnitId());

        int newDrugId = (int) drugManagementService.createDrug(createDrugDto);

        // Create Transaction
        TransactionRepository transactionRepository = new TransactionRepository(db.transactionDao());
        UserService userService = new UserService(new UserRepository(db.userDao()));
        HistoryService historyService = new HistoryService(transactionRepository, userService);
        TransactionDto transactionDto = TestUtil.createTransactionDto(1);
        DrugDto drugDto =  mapper.createDrugDtoToDrugDto(createDrugDto);
        drugDto.setDrugId(newDrugId);
        transactionDto.setDrug(drugDto);
        // Remove userid on transaction, so that the user will be created
        transactionDto.getUser().setUserId(null);
        historyService.addTransaction(transactionDto);

        // Act
        // Should Throw
        drugsViewModel.deleteDrug(newDrugId);
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
