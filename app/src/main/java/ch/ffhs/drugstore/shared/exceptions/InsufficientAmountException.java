package ch.ffhs.drugstore.shared.exceptions;

/**
 * TODO: add description
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class InsufficientAmountException extends DrugstoreException {
    /**
     * @param code
     */
    public InsufficientAmountException(int code) {
        super("InsufficientAmountException", code);
    }
}
