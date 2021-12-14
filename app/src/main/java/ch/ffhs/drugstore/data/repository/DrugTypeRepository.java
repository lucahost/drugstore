package ch.ffhs.drugstore.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.DrugTypeDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.dto.DrugTypeDto;

public class DrugTypeRepository {
    private final DrugTypeDao drugTypeDao;
    private final LiveData<List<DrugTypeDto>> allDrugTypes;

    @Inject
    public DrugTypeRepository(Application application) {
        DrugstoreDatabase db = DrugstoreDatabase.getDatabase(application);
        drugTypeDao = db.drugTypeDao();
        allDrugTypes = drugTypeDao.getAllDrugTypes();
    }

    public LiveData<List<DrugTypeDto>> getAllDrugTypes() {
        return allDrugTypes;
    }
}
