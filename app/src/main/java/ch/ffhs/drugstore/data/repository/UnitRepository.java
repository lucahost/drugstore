package ch.ffhs.drugstore.data.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.UnitDao;
import ch.ffhs.drugstore.data.entity.Unit;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

/**
 * This class abstracts the data layer methods for Unit data
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class UnitRepository {
    private final UnitDao unitDao;
    private final LiveData<List<UnitDto>> allUnits;
    private final DrugstoreMapper mapper = DrugstoreMapper.INSTANCE;

    @Inject
    public UnitRepository(UnitDao unitDao) {
        this.unitDao = unitDao;
        allUnits = unitDao.getAllUnits();
    }

    public LiveData<List<UnitDto>> getAllUnits() {
        return allUnits;
    }

    public long createUnit(UnitDto unitDto) {
        Unit unit = mapper.unitFromUnitDto(unitDto);
        return unitDao.insert(unit);
    }
}
