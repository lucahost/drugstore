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
import ch.ffhs.drugstore.data.repository.UnitRepository;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.management.drugs.CreateDrug;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;
import util.LiveDataTestUtil;
import util.TestUtil;

@RunWith(AndroidJUnit4.class)
public class CreateDrugIntegrationTest {
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

    @Test
    public void CreateDrug() throws Exception {
        // Assert
        DrugManagementService drugManagementService = new DrugManagementService(drugRepository,
                drugTypeRepository, substanceRepository, unitRepository);

        CreateDrug createDrug = new CreateDrug(drugManagementService);

        DrugsViewModel drugsViewModel = new DrugsViewModel(appContext, null, createDrug, null, null,
                null, null, null, null);

        String drugName = faker.funnyName().name();
        String substance = faker.funnyName().name();
        String dosage = faker.funnyName().name();
        int drugTypeId = faker.number().randomDigit();
        int unitId = faker.number().randomDigit();
        String sTolerance = faker.funnyName().name();
        boolean isFavorite = false;

        insertDrugType(drugTypeId);
        insertUnit(unitId);

        // Act
        drugsViewModel.createDrug(drugName, substance, dosage, drugTypeId, unitId,
                sTolerance, isFavorite);

        // Verify
        List<DrugWithUnitAndDrugTypeAndSubstance> drugs = LiveDataTestUtil.getValue(
                db.drugDao().getAllDrugs());
        assertEquals(1, drugs.size());
        assertEquals(drugName, drugs.get(0).getDrug().getTitle());
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
