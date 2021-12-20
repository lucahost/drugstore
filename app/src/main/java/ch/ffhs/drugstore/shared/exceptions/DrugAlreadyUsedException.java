package ch.ffhs.drugstore.shared.exceptions;


/**
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugAlreadyUsedException extends DrugstoreException {
    public DrugAlreadyUsedException(int code) {
        super("DrugAlreadyUsedException", code);
    }
}