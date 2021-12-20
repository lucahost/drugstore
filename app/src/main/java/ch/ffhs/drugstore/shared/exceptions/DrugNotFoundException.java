package ch.ffhs.drugstore.shared.exceptions;

/**
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugNotFoundException extends DrugstoreException {
    public DrugNotFoundException(int code) {
        super("DrugNotFoundException", code);
    }
}
