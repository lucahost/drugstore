package ch.ffhs.drugstore.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.domain.usecase.dispensary.GetAllDrugs;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DispensaryViewModel extends AndroidViewModel {
    @Inject
    GetAllDrugs getAllDrugs;
    private LiveData<List<Drug>> drugs;

    @Inject
    public DispensaryViewModel(Application application) {
        super(application);
    }

    public LiveData<List<Drug>> getDrugs() {
        if (drugs == null) {
            drugs = getAllDrugs.execute(null);
        }
        return drugs;
    }
}
