package ch.ffhs.drugstore.shared.exceptions;

/**
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class InvalidUserException extends DrugstoreException {
    public InvalidUserException(int code) {
        super("InvalidUserException", code);
    }
}
