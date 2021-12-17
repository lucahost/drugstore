package ch.ffhs.drugstore.presentation;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import ch.ffhs.drugstore.shared.extensions.DoubleExtension;

public class InputValidation {
    public static boolean validateTextNotEmpty(
            TextInputEditText text, TextInputLayout layout, String message) {
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
