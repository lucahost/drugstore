package ch.ffhs.drugstore.shared.extensions;

/**
 * Extension to work with doubles
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DoubleExtension {

    private DoubleExtension() {
        throw new IllegalStateException("Utility class");
    }

    public static double tryParseDouble(final String number) {

        double result;
        try {
            result = Double.parseDouble(number);
        } catch (NumberFormatException e) {
            result = 0.0;
        }
        return result;
    }
}
