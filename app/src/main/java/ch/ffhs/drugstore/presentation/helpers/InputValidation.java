package ch.ffhs.drugstore.presentation.helpers;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import ch.ffhs.drugstore.shared.extensions.DoubleExtension;

/**
 * Validation helpers for text inputs.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class InputValidation {
    private InputValidation() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Validates if a given input text is not empty. If it is empty it will set an error on a given
     * text layout.
     *
     * @param text    edit text to be validated
     * @param layout  text input layout to show potential errors
     * @param message the error message
     * @return validation result
     */
    public static boolean validateTextNotEmpty(
            EditText text, TextInputLayout layout, String message) {
        if (text == null || text.getText() == null) return false;
        if (text.getText().toString().isEmpty()) {
            text.setError(message);
            layout.setError(message);
            return false;
        }
        text.setError(null);
        layout.setError(null);
        return true;
    }

    /**
     * Validates if a given input text is not empty and does not contain a value of zero. If it is
     * empty it will set an error on a given text layout.
     *
     * @param text    edit text to be validated
     * @param layout  text input layout to show potential errors
     * @param message the error message
     * @return validation result
     */
    public static boolean validateNumberDecimalStringNotZero(
            TextInputEditText text, TextInputLayout layout, String message) {
        if (text == null || text.getText() == null) return false;
        String string = text.getText().toString();
        if (string.isEmpty() || DoubleExtension.tryParseDouble(string) == 0.0) {
            text.setError(message);
            layout.setError(message);
            return false;
        }
        text.setError(null);
        layout.setError(null);
        return true;
    }
}
