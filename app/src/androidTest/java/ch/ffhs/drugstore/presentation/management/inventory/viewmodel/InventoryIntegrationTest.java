package ch.ffhs.drugstore.presentation.management.inventory.viewmodel;

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

import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.data.repository.DrugTypeRepository;
import ch.ffhs.drugstore.data.repository.SignatureDrugRepository;
import ch.ffhs.drugstore.data.repository.SignatureRepository;
import ch.ffhs.drugstore.data.repository.SubstanceRepository;
import ch.ffhs.drugstore.data.repository.UnitRepository;
import ch.ffhs.drugstore.data.repository.UserRepository;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.service.SignatureService;
import ch.ffhs.drugstore.domain.service.UserService;
import ch.ffhs.drugstore.domain.usecase.management.inventory.SignInventory;
import ch.ffhs.drugstore.domain.usecase.management.inventory.ToggleInventoryItem;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;
import util.TestUtil;

@RunWith(AndroidJUnit4.class)
public class InventoryIntegrationTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final Application appContext = ApplicationProvider.getApplicationContext();
    private final Faker faker = new Faker();
    private final DrugstoreMapper mapper = DrugstoreMapper.INSTANCE;
    private DrugstoreDatabase db;
    private UserRepository userRepository;
    private SignatureRepository signatureRepository;
    private SignatureDrugRepository signatureDrugRepository;

    private DrugRepository drugRepository;
    private DrugTypeRepository drugTypeRepository;
    private SubstanceRepository substanceRepository;
    private UnitRepository unitRepository;


    @Before
    public void setUp() {
        db = Room.inMemoryDatabaseBuilder(appContext, DrugstoreDatabase.class).build();

        userRepository = new UserRepository(db.userDao());
        signatureRepository = new SignatureRepository(db.signatureDao(), db.signatureDrugDao());
        signatureDrugRepository = new SignatureDrugRepository(db.signatureDrugDao());

        drugRepository = new DrugRepository(db.drugDao());
        drugTypeRepository = new DrugTypeRepository(db.drugTypeDao());
        substanceRepository = new SubstanceRepository(db.substanceDao());
        unitRepository = new UnitRepository(db.unitDao());
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void signInventory() throws InterruptedException {
        // Arrange
        UserService userService = new UserService(userRepository);
        SignatureService signatureService = new SignatureService(signatureRepository,
                signatureDrugRepository, userService);
        String employeeShortName = faker.funnyName().name();

        // Act
        SignInventory signInventory = new SignInventory(signatureService);
        InventoryViewModel inventoryViewModel = new InventoryViewModel(appContext, null, null,
                signInventory);

        inventoryViewModel.signInventory(employeeShortName);

        // Verify
        assertEquals(1, getValue(signatureRepository.getSignatures()).size());
    }

    @Test
    public void toggleInventoryItem() throws DrugstoreException {
        // Arrange
        DrugManagementService drugManagementService = new DrugManagementService(drugRepository,
                drugTypeRepository, substanceRepository, unitRepository);

        CreateDrugDto createDrugDto = TestUtil.createCreateDrugDto();

        insertDrugType(createDrugDto.getDrugTypeId());
        insertUnit(createDrugDto.getUnitId());

        int newDrugId = (int) drugManagementService.createDrug(createDrugDto);

        // Act
        ToggleInventoryItem toggleInventoryItem = new ToggleInventoryItem(drugManagementService);
        InventoryViewModel inventoryViewModel = new InventoryViewModel(appContext, null,
                toggleInventoryItem, null);

        inventoryViewModel.toggleInventoryItem(newDrugId, true);

        // Verify
        assertEquals(1, inventoryViewModel.getSignatureDrugs().size());

        // Re-Toggle to remove
        inventoryViewModel.toggleInventoryItem(newDrugId, true);
        assertEquals(0, inventoryViewModel.getSignatureDrugs().size());
    }

    @Test(expected = DrugNotFoundException.class)
    public void toggleNotFoundDrugThrowsDrugNotFoundException() throws DrugstoreException {
        // Arrange
        DrugManagementService drugManagementService = new DrugManagementService(drugRepository,
                drugTypeRepository, substanceRepository, unitRepository);

        // Act
        ToggleInventoryItem toggleInventoryItem = new ToggleInventoryItem(drugManagementService);
        InventoryViewModel inventoryViewModel = new InventoryViewModel(appContext, null,
                toggleInventoryItem, null);

        inventoryViewModel.toggleInventoryItem(-1, true);
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