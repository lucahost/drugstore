package ch.ffhs.drugstore.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.UnitDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
/**
 * This class abstracts the data layer
 * methods for Unit data
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class UnitRepository {
    private final UnitDao unitDao;
    private final LiveData<List<UnitDto>> allUnits;

    @Inject
    public UnitRepository(Application application) {
        DrugstoreDatabase db = DrugstoreDatabase.getDatabase(application);
        unitDao = db.unitDao();
        allUnits = unitDao.getAllUnits();
    }
    
    public LiveData<List<UnitDto>> getAllUnits() {
        return allUnits;
    }
}
