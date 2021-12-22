package ch.ffhs.drugstore.presentation.management.signature.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.usecase.management.signatures.GetSignatureDrug;
import ch.ffhs.drugstore.domain.usecase.management.signatures.GetSignatures;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * View model for signatures.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@HiltViewModel
public class SignatureViewModel extends AndroidViewModel {
    private final GetSignatures getSignatures;
    private final GetSignatureDrug getSignatureDrug;
    private LiveData<List<SignatureDto>> signatures;

    /**
     * Constructs a {@link SignatureViewModel}
     *
     * @param application      global application state
     * @param getSignatures    use case to get signatures
     * @param getSignatureDrug use case to get signature drug
     */
    @Inject
    public SignatureViewModel(
            Application application, GetSignatures getSignatures,
            GetSignatureDrug getSignatureDrug) {
        super(application);
        this.getSignatures = getSignatures;
        this.getSignatureDrug = getSignatureDrug;
    }

    /**
     * Get all signatures
     *
     * @return signatures
     */
    public LiveData<List<SignatureDto>> getSignatures() {
        if (signatures == null) {
            signatures = getSignatures.execute(null);
        }
        return signatures;
    }

    /**
     * Get a signature drugs by signature id
     *
     * @param signatureId signature id to filter by
     * @return signature drugs
     */
    public LiveData<List<SignatureDrugDto>> getSignatureDrugBySignatureId(int signatureId) {
        return getSignatureDrug.execute(signatureId);
    }
}
