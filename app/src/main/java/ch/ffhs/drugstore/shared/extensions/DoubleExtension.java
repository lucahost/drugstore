package ch.ffhs.drugstore.shared.extensions;

/**
 * TODO: add description
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DoubleExtension {
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
