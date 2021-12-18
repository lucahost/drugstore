package ch.ffhs.drugstore.e2e.helpers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.core.AllOf.allOf;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

public class RecyclerViewItemCount {
    public static int getCountFromRecyclerView(@IdRes int id) {
        final int[] count = {0};
        Matcher<View> matcher = new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
            }

            @Override
            protected boolean matchesSafely(View item) {
                count[0] = Objects.requireNonNull(
                        ((RecyclerView) item).getAdapter()).getItemCount();
                return true;
            }
        };
        onView(allOf(withId(id), isDisplayed())).check(matches(matcher));
        return count[0];
    }
}
