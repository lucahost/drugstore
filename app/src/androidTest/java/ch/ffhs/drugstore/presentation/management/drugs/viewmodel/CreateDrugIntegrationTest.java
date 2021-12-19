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

import ch.ffhs.drugstore.data.dao.SubstanceDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.entity.Substance;
import ch.ffhs.drugstore.data.relation.DrugTypeWithParentDrugType;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.data.repository.DrugTypeRepository;
import ch.ffhs.drugstore.data.repository.SubstanceRepository;
import ch.ffhs.drugstore.data.repository.UnitRepository;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.management.drugs.CreateDrug;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
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
    private SubstanceDao substanceDao;

    @Before
    public void setUp() {
        db = Room.inMemoryDatabaseBuilder(appContext, DrugstoreDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void CreateDrug() throws Exception {
        // Assert
        DrugRepository drugRepository = new DrugRepository(appContext);
        DrugTypeRepository drugTypeRepository = new DrugTypeRepository(appContext);
        SubstanceRepository substanceRepository = new SubstanceRepository(appContext);
        UnitRepository unitRepository = new UnitRepository(appContext);

        DrugManagementService drugManagementService = new DrugManagementService(drugRepository,
                drugTypeRepository, substanceRepository, unitRepository);

        CreateDrug createDrug = new CreateDrug(drugManagementService);

        DrugsViewModel drugsViewModel = new DrugsViewModel(appContext, null, createDrug, null, null,
                null, null, null, null);

        String drugName = faker.funnyName().name();
        int substanceId = faker.number().randomDigit();
        SubstanceDto substanceDto = TestUtil.createSubstanceDto(substanceId);
        String substance = substanceDto.getTitle();
        String dosage = faker.funnyName().name();
        int drugTypeId = faker.number().randomDigit();
        DrugTypeDto drugTypeDto = TestUtil.createDrugTypeDto(drugTypeId);
        int unitId = faker.number().randomDigit();
        UnitDto unitDto = TestUtil.createDrugUnitDto(unitId);
        String sTolerance = faker.funnyName().name();
        boolean isFavorite = false;

        db.drugTypeDao().insert(
                mapper.drugTypeFromDrugTypeDto(drugTypeDto));
        db.unitDao().insert(mapper.unitFromUnitDto(unitDto));
        db.substanceDao().insert(mapper.substanceDtoToSubstance(substanceDto));

        List<DrugTypeWithParentDrugType> drugTypes = LiveDataTestUtil.getValue(
                db.drugTypeDao().getAllDrugTypes());
        List<UnitDto> units = LiveDataTestUtil.getValue(db.unitDao().getAllUnits());
        List<Substance> substances = LiveDataTestUtil.getValue(
                db.substanceDao().getAllSubstances());

        // Act
        drugsViewModel.createDrug(drugName, substance, dosage, drugTypeId, unitId,
                sTolerance, isFavorite);

        // Verify
        List<DrugWithUnitAndDrugTypeAndSubstance> drugs = LiveDataTestUtil.getValue(
                db.drugDao().getAllDrugs());
        assertEquals(1, drugs.size());
        assertEquals(drugName, drugs.get(0).getDrug().getTitle());
    }
}
