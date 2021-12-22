package ch.ffhs.drugstore.shared.exceptions;

/**
 * Abstract app exception
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public abstract class DrugstoreException extends Exception {
    private final int code;

    protected DrugstoreException(int code) {
        super("DrugstoreException");
        this.code = code;
    }

    protected DrugstoreException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
