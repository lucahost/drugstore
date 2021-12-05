package ch.ffhs.drugstore.presentation.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;

import javax.inject.Inject;

public class NavTracker implements NavController.OnDestinationChangedListener {

  @Inject
  public NavTracker() {
    // TODO document why this constructor is empty
  }

  @Override
  public void onDestinationChanged(
      @NonNull NavController navController,
      @NonNull NavDestination navDestination,
      @Nullable Bundle bundle) {
    System.out.println(navDestination.getLabel());
  }
}
