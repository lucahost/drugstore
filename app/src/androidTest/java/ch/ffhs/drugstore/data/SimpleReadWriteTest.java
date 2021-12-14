package ch.ffhs.drugstore.data;

import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.room.Room;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import ch.ffhs.drugstore.data.dao.DrugDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.entity.Drug;
/*
@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {
    private DrugDao drugDao;
    private DrugstoreDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, DrugstoreDatabase.class).build();
        drugDao = db.drugDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        Drug drug = new Drug(3);
        drug.setTitle("Test Drug");
        drugDao.insert(drug);
        Drug byName = drugDao.getDrugById(1);
        assertThat(byName.get(0), equalTo(user));
    }
}
*/