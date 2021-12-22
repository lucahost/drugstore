package ch.ffhs.drugstore.e2e;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertNotEquals;

import static ch.ffhs.drugstore.e2e.helpers.TextHelpers.getText;
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
public class RemoveDrugTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    final Faker faker = new Faker();

    @Test
    public void removeDrug() {
        // Click on management tab
        onView(withId(R.id.management)).perform(click());
        // Click on the first item in the list
        onView(withId(R.id.drugsList)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                click()
        ));
        // Click on remove menu item if it exists
        onView(withText(R.string.remove)).check(matches(isDisplayed())).perform(click());
        // Get the current stock
        String stockAmountBefore = getText(onView(withId(R.id.drug_stock_amount_text)));
        // Fill form with random values
        double amount = faker.number().randomDouble(2, 1, 2);
        onView(withId(R.id.drug_count_text)).perform(typeText(Double.toString(amount)));
        onView(withId(R.id.user_short_name_text)).perform(typeText(faker.funnyName().name()));
        // Confirm form
        onView(withId(android.R.id.button1)).perform(click());

        // Click on the first item in the list
        onView(withId(R.id.drugsList)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                click()
        ));
        // Click on remove menu item if it exists
        onView(withText(R.string.remove)).check(matches(isDisplayed())).perform(click());
        // Get the current stock
        String stockAmountAfter = getText(onView(withId(R.id.drug_stock_amount_text)));
        assertNotEquals(stockAmountAfter, stockAmountBefore);
    }

    @Test
    public void removeDrugWithEmptyFormShouldDisplayValidationError() {
        // Click on management tab
        onView(withId(R.id.management)).perform(click());
        // Click on the first item in the list
        onView(withId(R.id.drugsList)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                click()
        ));
        // Click on add menu item if it exists
        onView(withText(R.string.remove)).check(matches(isDisplayed())).perform(click());
        // Confirm form
        onView(withId(android.R.id.button1)).perform(click());
        // Check if validation error is shown
        onView(withId(R.id.drug_count_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_amount_over_zero_required)));
        onView(withId(R.id.user_short_name_text_layout)).check(
                matches(hasTextInputLayoutErrorText(R.string.error_name_required)));
        // Dismiss form
        onView(withId(android.R.id.button2)).perform(click());
    }
}
