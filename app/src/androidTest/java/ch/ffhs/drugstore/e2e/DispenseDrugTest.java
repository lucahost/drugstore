package ch.ffhs.drugstore.e2e;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
public class DispenseDrugTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    Faker faker = new Faker();

    @Test
    public void dispenseDrug() {
        // Click on the first item in the list
        onView(withId(R.id.dispensaryList)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                click()
        ));
        // Check that dialog is displayed
        onView(withText(R.string.dispense)).check(matches(isDisplayed()));
        // Fill form with random values
        onView(withId(R.id.employee_text)).perform(typeText(faker.funnyName().name()));
        onView(withId(R.id.patient_text)).perform(typeText(faker.funnyName().name()));
        double amount = faker.number().randomDouble(2, 1, 100);
        onView(withId(R.id.amount_text)).perform(typeText(Double.toString(amount)));
        // Confirm form
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void dispenseDrugWithEmptyFormShouldDisplayValidationError() {
        // Click on the first item in the list
        onView(withId(R.id.dispensaryList)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                click()
        ));
        // Check that dialog is displayed
        onView(withText(R.string.dispense)).check(matches(isDisplayed()));
        // Confirm form
        onView(withId(android.R.id.button1)).perform(click());
        // Check if validation errors are shown
        onView(withId(R.id.employee_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_employee_required)));
        onView(withId(R.id.patient_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_patient_required)));
        onView(withId(R.id.amount_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_amount_over_zero_required)));
        // Dismiss form
        onView(withId(android.R.id.button2)).perform(click());
    }
}
