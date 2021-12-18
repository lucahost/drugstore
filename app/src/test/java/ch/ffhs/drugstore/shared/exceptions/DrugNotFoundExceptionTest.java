package ch.ffhs.drugstore.shared.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DrugNotFoundExceptionTest {

    @Test
    public void drugNotFoundExceptionHasCorrectMessage() {
        // Arrange
        int errorCode = 123;

        // Act
        DrugNotFoundException drugNotFoundException = new DrugNotFoundException(errorCode);

        // Verify
        assertEquals("DrugNotFoundException", drugNotFoundException.getMessage());
        assertEquals(123, drugNotFoundException.getCode());
    }
}