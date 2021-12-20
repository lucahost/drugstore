package ch.ffhs.drugstore.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ch.ffhs.drugstore.data.dao.DrugDao;
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
@Singleton
public class DrugRepository {
    private final DrugDao drugDao;
    private final LiveData<List<DrugWithUnitAndDrugTypeAndSubstance>> allDrugs;
    private final DrugstoreMapper mapper;

    /**
     * @param drugDao for communicating with the db
     */
    @Inject
    public DrugRepository(DrugDao drugDao) {
        this.mapper = DrugstoreMapper.INSTANCE;
        this.drugDao = drugDao;
        allDrugs = drugDao.getAllDrugs();
    }

    /**
     * create a drug
     *
     * @param drugDto
     */
    public long createDrug(DrugDto drugDto) {
        Drug drug = mapper.drugDtoToDrug(drugDto);
        return this.insert(drug);
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

    public long insert(Drug drug) {
        return drugDao.insert(drug);
    }

    public void update(Drug drug) {
        drugDao.update(drug);
    }

    public void delete(Drug drug) {
        drugDao.delete(drug);
    }

    public void deleteDrugById(int drugId) {
        drugDao.deleteDrugById(drugId);
    }

    public void deleteAll() {
        drugDao.deleteAll();
    }

    public void toggleDrugIsFavorite(int drugId) {
        drugDao.toggleDrugIsFavorite(drugId);
    }
}
