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

/**
 * This class abstracts the data layer methods for Drug Repository data
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugRepository {
    private final DrugDao drugDao;
    private final LiveData<List<DrugWithUnitAndDrugTypeAndSubstance>> allDrugs;
    private final DrugstoreMapper mapper;

    /**
     * @param application for getting the database
     */
    @Inject
    public DrugRepository(Application application) {
        DrugstoreDatabase db = DrugstoreDatabase.getDatabase(application);
        this.mapper = DrugstoreMapper.INSTANCE;
        drugDao = db.drugDao();
        allDrugs = drugDao.getAllDrugs();
    }

    /**
     * create a drug
     *
     * @param drugDto
     */
    public void createDrug(DrugDto drugDto) {
        Drug drug = mapper.drugDtoToDrug(drugDto);
        this.insert(drug);
    }

    /**
     * edit a drug
     *
     * @param drugDto
     */
    public void editDrug(DrugDto drugDto) {
        Drug drug = mapper.drugDtoToDrug(drugDto);
        this.update(drug);
    }

    /**
     * update the a drug amount
     *
     * @param drugId
     * @param newAmount
     */
    public void updateDrugAmount(int drugId, double newAmount) {
        Drug drug = drugDao.getDrugById(drugId).getDrug();
        drug.setStockAmount(newAmount);
        drugDao.update(drug);
    }

    /**
     * get all drugs
     *
     * @return LiveData List with all drugs
     */
    public LiveData<List<DrugDto>> getAllDrugs() {
        return Transformations.map(allDrugs, mapper::drugListToDrugDtoList);
    }

    /**
     * get drugs from a specific id
     *
     * @param drugId
     * @return
     */
    public DrugDto getDrugById(int drugId) {
        return mapper.drugToDrugWithUnitAndDrugTypesAndSubstanceDto(drugDao.getDrugById(drugId));
    }

    /**
     * get drugs who are on stock
     *
     * @param favorites
     * @param searchTerm
     * @return
     */
    public LiveData<List<DrugDto>> getOnStockDrugs(boolean favorites, String searchTerm) {
        if (favorites) {
            return Transformations.map(drugDao.getOnStockFavoriteDrugs(searchTerm),
                    mapper::drugListToDrugDtoList);
        } else {
            return Transformations.map(drugDao.getOnStockDrugs(searchTerm),
                    mapper::drugListToDrugDtoList);
        }
    }

    /**
     * get drugs who are on stock
     *
     * @param favorites
     * @param drugTypeIds
     * @param searchTerm
     * @return
     */
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

    /**
     * execute dao methods
     */
    public void insert(Drug drug) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> drugDao.insert(drug));
    }

    public void update(Drug drug) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> drugDao.update(drug));
    }

    public void delete(Drug drug) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> drugDao.delete(drug));
    }

    public void deleteDrugById(int drugId) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> drugDao.deleteDrugById(drugId));
    }

    public void deleteAll() {
        DrugstoreDatabase.databaseWriteExecutor.execute(drugDao::deleteAll);
    }

    public void toggleDrugIsFavorite(int drugId) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> drugDao.toggleDrugIsFavorite(drugId));
    }
}
