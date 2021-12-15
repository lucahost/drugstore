package ch.ffhs.drugstore.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.SubstanceDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.entity.Substance;
import ch.ffhs.drugstore.data.entity.User;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

public class SubstanceRepository {
    private final SubstanceDao substanceDao;
    private final LiveData<List<Substance>> allSubstances;
    private final DrugstoreMapper mapper;

    @Inject
    public SubstanceRepository(Application application) {
        DrugstoreDatabase db = DrugstoreDatabase.getDatabase(application);
        mapper = DrugstoreMapper.INSTANCE;
        substanceDao = db.substanceDao();
        allSubstances = substanceDao.getAllSubstances();
    }

    public LiveData<List<SubstanceDto>> getAllSubstances() {
        return Transformations.map(allSubstances, mapper::substanceListToSubstanceDtoList);
    }

    public SubstanceDto getSubstanceById(int substanceId) {
        Substance substance = substanceDao.getSubstanceById(substanceId);
        return mapper.substanceToSubstanceDto(substance);
    }

    public SubstanceDto getSubstanceByTitle(String title) {
        Substance substance = substanceDao.getSubstanceByTitle(title);
        return mapper.substanceToSubstanceDto(substance);
    }

    public SubstanceDto getOrCreateSubstanceByTitle(String title) {
        Substance substance = substanceDao.getSubstanceByTitle(title);
        if (substance == null) {
            substance = new Substance(0, title);
            long userId = substanceDao.insert(substance);
            substance.setSubstanceId((int) userId);
        }
        return mapper.substanceToSubstanceDto(substance);
    }
}
