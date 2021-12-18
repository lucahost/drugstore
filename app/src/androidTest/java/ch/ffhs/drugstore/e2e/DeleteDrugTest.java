package ch.ffhs.drugstore.e2e;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ch.ffhs.drugstore.e2e.helpers.RecyclerViewItemCount.getCountFromRecyclerView;
import static ch.ffhs.drugstore.e2e.helpers.RecyclerViewItemCountAssertion.withItemCount;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.github.javafaker.Faker;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.presentation.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DeleteDrugTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void deleteDrug() {
        // Click on management tab
        onView(withId(R.id.management)).perform(click());
        // Count how many items there are in the list
        int itemCountBefore = getCountFromRecyclerView(R.id.drugsList);
        // Click on the first item in the list
        onView(withId(R.id.drugsList)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                click()
        ));
        // Click on delete menu item if it exists
        onView(withText(R.string.delete)).check(matches(isDisplayed())).perform(click());
        // Confirm form
        onView(withId(android.R.id.button1)).perform(click());
        // Check if there is one less item in the list than before
        onView(withId(R.id.drugsList)).check(withItemCount(itemCountBefore - 1));
    }
}
