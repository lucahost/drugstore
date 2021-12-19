package ch.ffhs.drugstore.e2e;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
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

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
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
public class CreateDrugTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    Faker faker = new Faker();

    @Test
    public void createDrug() {
        String drugName = faker.funnyName().name();
        String dosage = faker.funnyName().name();
        String substance = faker.funnyName().name();
        String tolerance = faker.number().digits(1);
        // Click on management tab
        onView(withId(R.id.management)).perform(click());
        // Count how many items there are in the list
        int itemCountBefore = getCountFromRecyclerView(R.id.drugsList);
        // Click create drug
        onView(withId(R.id.extended_fab)).perform(click());
        // Check that dialog is displayed
        onView(withText(R.string.create_drug)).check(matches(isDisplayed()));
        // Fill form with random values
        onView(withId(R.id.name_text)).perform(typeText(drugName));
        onView(withId(R.id.substance_text)).perform(typeText(substance));
        onView(withId(R.id.dosage_text)).perform(typeText(dosage));
        onView(withId(R.id.tolerance_text)).perform(typeText(tolerance));
        onView(withId(R.id.is_favorite_checkbox)).perform(scrollTo());
        onView(withId(R.id.is_favorite_checkbox)).perform(click());
        // Close KeyBoard (Or else the button is not in the view and will result in an exception
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        // Confirm form
        onView(withId(android.R.id.button1)).perform(click());
        // Check if there is one more item in the list
        onView(withId(R.id.drugsList)).check(withItemCount(itemCountBefore + 1));
        // Scroll to newly created item if it's there
        onView(withId(R.id.drugsList)).perform(RecyclerViewActions.scrollTo(
                hasDescendant(allOf(withText(drugName), hasSibling(withText(dosage))))
        ));
    }

    @Test
    public void createDrugWithEmptyFormShouldDisplayValidationError() {
        // Click on management tab
        onView(withId(R.id.management)).perform(click());
        // Click create drug
        onView(withId(R.id.extended_fab)).perform(click());
        // Check that dialog is displayed
        onView(withText(R.string.create_drug)).check(matches(isDisplayed()));
        // Close KeyBoard (Or else the button is not in the view and will result in an exception
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        // Confirm form
        onView(withId(android.R.id.button1)).perform(click());
        // Check if validation errors are shown
        onView(withId(R.id.name_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_name_required)));
        onView(withId(R.id.substance_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_substance_required)));
        onView(withId(R.id.dosage_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_dosage_required)));
        // Close KeyBoard (Or else the button is not in the view and will result in an exception
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        // Dismiss form
        onView(withId(android.R.id.button2)).perform(click());
    }
}
