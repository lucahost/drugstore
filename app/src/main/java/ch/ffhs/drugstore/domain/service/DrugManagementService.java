package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.repository.DrugRepository;

public class DrugManagementService {
  @Inject DrugRepository drugRepository;

  @Inject
  public DrugManagementService() {
    // TODO document why this constructor is empty
  }

  public LiveData<List<Drug>> getAllDrugs() {
    // Still fakin' the actual data. Uncomment the following the get the real deal.
    // return drugRepository.getAllDrugs();
    ArrayList<Drug> drugs = new ArrayList<>();
    drugs.add(new Drug(1, "Morphin HCI Sintetica", Drug.DrugType.INJECTION, "100mg/10ml", true));
    drugs.add(new Drug(2, "Ultiva", Drug.DrugType.PILL, "2mg", true));
    drugs.add(new Drug(3, "Oxynorm", Drug.DrugType.ORAL, "10mg/ml", true));
    drugs.add(new Drug(4, "Methadon Sinetica", Drug.DrugType.INJECTION, "10mg/MI", false));
    drugs.add(new Drug(5, "Sufenta forte", Drug.DrugType.INJECTION, "10mcg/ml", false));
    drugs.add(new Drug(6, "Fentanyl KA", Drug.DrugType.ORAL, "50mcg/ml", false));
    drugs.add(new Drug(7, "Pethidin HCI Sintetica", Drug.DrugType.INJECTION, "10mg/ml", false));
    drugs.add(new Drug(8, "Durogesic Matrix", Drug.DrugType.PILL, "50mcg/h", false));
    drugs.add(new Drug(9, "Pethidin Bichsel", Drug.DrugType.PILL, "50mg/2ml", false));
    drugs.add(new Drug(10, "Targin", Drug.DrugType.ORAL, "10mg/5mg", false));
    return new MutableLiveData<>(drugs);
  }
}
