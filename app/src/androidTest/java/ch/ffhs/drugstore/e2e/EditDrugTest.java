package ch.ffhs.drugstore.e2e;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.AllOf.allOf;

import static ch.ffhs.drugstore.e2e.helpers.RecyclerViewItemCount.getCountFromRecyclerView;
import static ch.ffhs.drugstore.e2e.helpers.RecyclerViewItemCountAssertion.withItemCount;
import static ch.ffhs.drugstore.e2e.helpers.TextHelpers.hasTextInputLayoutErrorText;

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
public class EditDrugTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    final Faker faker = new Faker();

    @Test
    public void editDrug() {
        String drugName = faker.funnyName().name();
        String dosage = faker.funnyName().name();
        String substance = faker.funnyName().name();
        String tolerance = faker.number().digits(1);
        // Click on management tab
        onView(withId(R.id.management)).perform(click());
        // Count how many items there are in the list
        int itemCountBefore = getCountFromRecyclerView(R.id.drugsList);
        // Click on the first item in the list
        onView(withId(R.id.drugsList)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                click()
        ));
        onView(withText(R.string.edit)).check(matches(isDisplayed())).perform(click());
        onView(withText(R.string.edit_drug)).check(matches(isDisplayed()));
        // Fill form with random values
        onView(withId(R.id.name_text)).perform(replaceText(drugName));
        onView(withId(R.id.substance_text)).perform(replaceText(substance));
        onView(withId(R.id.dosage_text)).perform(replaceText(dosage));
        onView(withId(R.id.tolerance_text)).perform(replaceText(tolerance));
        onView(withId(R.id.is_favorite_checkbox)).perform(scrollTo());
        onView(withId(R.id.is_favorite_checkbox)).perform(click());
        // Confirm form
        onView(withId(android.R.id.button1)).perform(click());
        // Check if there is no more item in the list than before
        onView(withId(R.id.drugsList)).check(withItemCount(itemCountBefore));
        // Scroll to newly created item if it's there
        onView(withId(R.id.drugsList)).perform(RecyclerViewActions.scrollTo(
                hasDescendant(allOf(withText(drugName), hasSibling(withText(dosage))))
        ));
    }

    @Test
    public void editDrugWithEmptyFormShouldDisplayValidationError() {
        // Click on management tab
        onView(withId(R.id.management)).perform(click());
        // Click on the first item in the list
        onView(withId(R.id.drugsList)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                click()
        ));
        onView(withText(R.string.edit)).check(matches(isDisplayed())).perform(click());
        onView(withText(R.string.edit_drug)).check(matches(isDisplayed()));
        // Fill form with random values
        onView(withId(R.id.name_text)).perform(replaceText(""));
        onView(withId(R.id.substance_text)).perform(replaceText(""));
        onView(withId(R.id.dosage_text)).perform(replaceText(""));
        onView(withId(R.id.tolerance_text)).perform(replaceText(""));
        // Confirm form
        onView(withId(android.R.id.button1)).perform(click());
        // Check if validation errors are shown
        onView(withId(R.id.name_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_name_required)));
        onView(withId(R.id.substance_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_substance_required)));
        onView(withId(R.id.dosage_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_dosage_required)));
        // Dismiss form
        onView(withId(android.R.id.button2)).perform(click());
    }
}
