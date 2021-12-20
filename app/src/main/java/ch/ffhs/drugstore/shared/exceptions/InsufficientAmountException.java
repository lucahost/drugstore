package ch.ffhs.drugstore.shared.exceptions;

/**
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class InsufficientAmountException extends DrugstoreException {
    public InsufficientAmountException(int code) {
        super("InsufficientAmountException", code);
    }
}
