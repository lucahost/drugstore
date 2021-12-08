package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dto.DrugDto;
import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.repository.DrugRepository;

public class DispensaryService {
    @Inject
    DrugRepository drugRepository;

    @Inject
    public DispensaryService() {
        // TODO document why this constructor is empty
    }

    public LiveData<List<DrugDto>> getAllDrugs() {
        return drugRepository.getAllDrugs();
    }
}
