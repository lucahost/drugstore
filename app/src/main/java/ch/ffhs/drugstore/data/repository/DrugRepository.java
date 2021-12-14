package ch.ffhs.drugstore.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.DrugDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

public class DrugRepository {
    private final DrugDao drugDao;
    private final LiveData<List<DrugWithUnitAndDrugTypeAndSubstance>> allDrugs;
    private final DrugstoreMapper mapper;

    @Inject
    public DrugRepository(Application application) {
        DrugstoreDatabase db = DrugstoreDatabase.getDatabase(application);
        this.mapper = DrugstoreMapper.INSTANCE;
        drugDao = db.drugDao();
        allDrugs = drugDao.getAllDrugs();
    }

    public void addDrug(DrugDto drugDto) {
        Drug drug = mapper.drugDtoToDrug(drugDto);
        this.insert(drug);
    }

    public void updateDrugAmount(int drugId, double drugAmount) {
        Drug drug = drugDao.getDrugById(drugId).getDrug();
        double newAmount = drug.getStockAmount() - drugAmount;
        drug.setStockAmount(newAmount);
        drugDao.update(drug);
    }

    public LiveData<List<DrugDto>> getAllDrugs() {
        return Transformations.map(allDrugs, mapper::drugListToDrugDtoList);
    }

    public DrugDto getDrugById(int drugId) {
        return mapper.drugToDrugWithUnitAndDrugTypesAndSubstanceDto(drugDao.getDrugById(drugId));
    }

    public LiveData<List<DrugDto>> getOnStockDrugs(boolean favorites, String searchTerm) {
        if (favorites) {
            return Transformations.map(drugDao.getOnStockFavoriteDrugs(searchTerm),
                    mapper::drugListToDrugDtoList);
        } else {
            return Transformations.map(drugDao.getOnStockDrugs(searchTerm),
                    mapper::drugListToDrugDtoList);
        }
    }

    public LiveData<List<DrugDto>> getOnStockDrugs(boolean favorites, List<Integer> drugTypeIds,
            String searchTerm) {
        if (favorites) {
            return Transformations.map(
                    drugDao.getOnStockFavoriteDrugsByDrugTypes(drugTypeIds, searchTerm),
                    mapper::drugListToDrugDtoList);
        } else {
            return Transformations.map(drugDao.getOnStockDrugsByDrugTypes(drugTypeIds, searchTerm),
                    mapper::drugListToDrugDtoList);
        }
    }

    public void insert(Drug drug) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> drugDao.insert(drug));
    }

    public void update(Drug drug) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> drugDao.update(drug));
    }

    public void delete(Drug drug) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> drugDao.delete(drug));
    }

    public void deleteAll() {
        DrugstoreDatabase.databaseWriteExecutor.execute(drugDao::deleteAll);
    }
}
