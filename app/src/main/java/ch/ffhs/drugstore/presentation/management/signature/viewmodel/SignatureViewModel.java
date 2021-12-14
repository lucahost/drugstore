package ch.ffhs.drugstore.presentation.management.signature.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.domain.usecase.management.signatures.GetSignatures;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SignatureViewModel extends AndroidViewModel {
    @Inject
    GetSignatures getSignatures;
    private LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> items;

    @Inject
    public SignatureViewModel(Application application) {
        super(application);
    }

    public LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> getItems() {
        if (items == null) {
            items = getSignatures.execute(null);
        }
        return items;
    }
}
