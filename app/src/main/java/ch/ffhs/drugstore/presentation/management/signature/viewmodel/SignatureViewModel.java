package ch.ffhs.drugstore.presentation.management.signature.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.domain.usecase.management.signatures.GetSignatureDrug;
import ch.ffhs.drugstore.domain.usecase.management.signatures.GetSignatures;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SignatureViewModel extends AndroidViewModel {
    @Inject
    GetSignatures getSignatures;
    @Inject
    GetSignatureDrug getSignatureDrug;
    private LiveData<List<SignatureDto>> items;
    private LiveData<SignatureDrugDto> signatureDrugDtoLiveData;

    @Inject
    public SignatureViewModel(Application application) {
        super(application);
    }

    public LiveData<List<SignatureDto>> getItems() {
        if (items == null) {
            items = getSignatures.execute(null);
        }
        return items;
    }

    public LiveData<List<SignatureDrugDto>> getSignatureDrugBySignatureId(int signatureId) {
        return getSignatureDrug.execute(signatureId);
    }
}
