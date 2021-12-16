package ch.ffhs.drugstore.data;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import ch.ffhs.drugstore.data.dao.DrugDao;
import ch.ffhs.drugstore.data.dao.DrugTypeDao;
import ch.ffhs.drugstore.data.dao.SubstanceDao;
import ch.ffhs.drugstore.data.dao.UnitDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.DrugType;
import ch.ffhs.drugstore.data.entity.Substance;
import ch.ffhs.drugstore.data.entity.Unit;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import util.TestUtil;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private DrugTypeDao drugTypeDao;
    private UnitDao unitDao;
    private SubstanceDao substanceDao;
    private DrugDao drugDao;

    private DrugstoreDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, DrugstoreDatabase.class).build();
        drugTypeDao = db.drugTypeDao();
        unitDao = db.unitDao();
        substanceDao = db.substanceDao();
        drugDao = db.drugDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test(expected = SQLiteConstraintException.class)
    public void drugWithNoRelationThrowsConstraintException() {
        DrugWithUnitAndDrugTypeAndSubstance drugWithAllRelations =
                TestUtil.createDrugWithAllRelations(1, 1, 1, 1);
        Drug drug = drugWithAllRelations.getDrug();
        drug.setTitle("Test Drug");
        drugDao.insert(drug);
    }

    @Test
    public void writeDrugAndReadDrugList() {
        // Create Test Data
        DrugWithUnitAndDrugTypeAndSubstance drugWithAllRelations =
                TestUtil.createDrugWithAllRelations(1, 1, 1, 1);

        // Get created data
        DrugType drugType = drugWithAllRelations.getDrugType();
        Unit unit = drugWithAllRelations.getUnit();
        Substance substance = drugWithAllRelations.getSubstance();
        Drug drug = drugWithAllRelations.getDrug();

        // Insert Data
        drugTypeDao.insert(drugType);
        unitDao.insert(unit);
        substanceDao.insert(substance);

        drug.setTitle("Test Drug");
        drugDao.insert(drug);

        // Compare Data from db
        DrugWithUnitAndDrugTypeAndSubstance byId = drugDao.getDrugById(1);
        assertEquals(byId.getDrug().getTitle(), drug.getTitle());
    }
}
