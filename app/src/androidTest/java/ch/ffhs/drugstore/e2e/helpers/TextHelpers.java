package ch.ffhs.drugstore.e2e.helpers;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import com.google.android.material.textfield.TextInputLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class TextHelpers {
    public static String getText(ViewInteraction matcher) {
        final String[] text = new String[1];
        ViewAction va = new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "Text of the view";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view;
                text[0] = tv.getText().toString();
            }
        };

        matcher.perform(va);

        return text[0];
    }

    public static Matcher<View> hasTextInputLayoutErrorText(@IdRes int resourceId) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();
                String expectedErrorText = view.getResources().getString(resourceId);

                if (error == null) {
                    return false;
                }

                String errorText = error.toString();

                return expectedErrorText.equals(errorText);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
