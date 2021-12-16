package ch.ffhs.drugstore.shared.extensions;

public class DoubleExtension {
    public static double tryParseDouble(final String number){

        double result;
        try {
            result = Double.parseDouble(number);
        }
        catch (NumberFormatException e) {
            result = 0.0;
        }
        return result;
    }
}
