package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.repository.DrugRepository;

public class DrugService {
  @Inject
  DrugRepository drugRepository;

  @Inject
  public DrugService() {
    // TODO document why this constructor is empty
  }

  public LiveData<List<Drug>> getAllDrugs() {
    return drugRepository.getAllDrugs();
  }
}
