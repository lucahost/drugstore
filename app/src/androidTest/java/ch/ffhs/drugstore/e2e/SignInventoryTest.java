package ch.ffhs.drugstore.e2e;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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
public class SignInventoryTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    Faker faker = new Faker();

    @Test
    public void signInventory() {
        // Click on management tab
        onView(withId(R.id.management)).perform(click());
        // Click on inventory menu
        onView(withId(R.id.inventory)).perform(click());
        // Click on the first item in the list
        onView(withId(R.id.inventoryList)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                click()
        ));
        // Click sign inventory
        onView(withId(R.id.extended_fab)).perform(click());
        // Fill form with random values
        onView(withId(R.id.employee_text)).perform(typeText(faker.funnyName().name()));
        // Confirm form
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void signInventoryWithEmptyFormShouldDisplayValidationError() {
        // Click on management tab
        onView(withId(R.id.management)).perform(click());
        // Click on inventory menu
        onView(withId(R.id.inventory)).perform(click());
        // Click on the first item in the list
        onView(withId(R.id.inventoryList)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                click()
        ));
        // Click sign inventory
        onView(withId(R.id.extended_fab)).perform(click());
        // Confirm form
        onView(withId(android.R.id.button1)).perform(click());
        // Check if validation error is shown
        onView(withId(R.id.employee_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_employee_required)));
        // Dismiss form
        onView(withId(android.R.id.button2)).perform(click());
    }
}
