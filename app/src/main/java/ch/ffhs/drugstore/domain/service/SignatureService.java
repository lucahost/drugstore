package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Signature;

public class SignatureService {
  @Inject
  public SignatureService() {
    // TODO document why this constructor is empty
  }

  public LiveData<List<Signature>> getSignatures() {
    // Still fakin' the actual data.
    List<Signature> signatures = new ArrayList<>();
    signatures.add(new Signature(1, 1, new Date(2021, 11, 3, 14, 27)));
    signatures.add(new Signature(2, 1, new Date(2021, 5, 7, 17, 23)));
    signatures.add(new Signature(3, 1, new Date(2021, 2, 13, 10, 1)));
    signatures.add(new Signature(4, 1, new Date(2021, 1, 24, 18, 53)));
    return new MutableLiveData<>(signatures);
  }
}
