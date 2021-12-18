package ch.ffhs.drugstore.shared.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InsufficientAmountExceptionTest {
    @Test
    public void insufficientAmountExceptionHasCorrectMessage() {
        // Arrange
        int errorCode = 123;

        // Act
        InsufficientAmountException insufficientAmountException = new InsufficientAmountException(
                errorCode);

        // Verify
        assertEquals("InsufficientAmountException", insufficientAmountException.getMessage());
        assertEquals(123, insufficientAmountException.getCode());
    }

}