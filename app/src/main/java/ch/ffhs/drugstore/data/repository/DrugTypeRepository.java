package ch.ffhs.drugstore.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.DrugTypeDao;
import ch.ffhs.drugstore.data.entity.DrugType;
import ch.ffhs.drugstore.data.relation.DrugTypeWithParentDrugType;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

/**
 * This class abstracts the data layer, methods for Drug Type data
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugTypeRepository {
    private final DrugTypeDao drugTypeDao;
    private final LiveData<List<DrugTypeWithParentDrugType>> allDrugTypes;
    private final DrugstoreMapper mapper;

    @Inject
    public DrugTypeRepository(DrugTypeDao drugTypeDao) {
        mapper = DrugstoreMapper.INSTANCE;
        this.drugTypeDao = drugTypeDao;
        allDrugTypes = drugTypeDao.getAllDrugTypes();
    }

    public LiveData<List<DrugTypeDto>> getAllDrugTypes() {
        return Transformations.map(allDrugTypes, mapper::drugTypeListToDrugTypeDtoList);
    }

    public long createDrugType(DrugTypeDto drugTypeDto) {
        DrugType drugType = mapper.drugTypeFromDrugTypeDto(drugTypeDto);
        return drugTypeDao.insert(drugType);
    }
}
