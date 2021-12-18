package ch.ffhs.drugstore.shared.extensions;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleExtensionTest {

    @Test
    public void tryParseDoubleWithWrongStringReturnZero() {
        // Arrange
        String strDouble = "asd";

        // Act
        double parsedDouble = DoubleExtension.tryParseDouble(strDouble);

        // Verify
        assertEquals(0, parsedDouble, 0);
    }

    @Test
    public void tryParseDoubleReturnCorrectDouble() {
        // Arrange
        String strDouble = "1.2321";

        // Act
        double parsedDouble = DoubleExtension.tryParseDouble(strDouble);

        // Verify
        assertEquals(1.2321, parsedDouble, 0);
    }

    @Test
    public void tryParseDoubleReturnCorrectNegativeDouble() {
        // Arrange
        String strDouble = "-1.1";

        // Act
        double parsedDouble = DoubleExtension.tryParseDouble(strDouble);

        // Verify
        assertEquals(-1.1, parsedDouble, 0);
    }
}