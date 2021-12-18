package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.TransactionRepository;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;

/**
 * This service class communicates with data layer, is used to get the history of all transactions
 * and creates new transactions
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class HistoryService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;

    @Inject
    public HistoryService(TransactionRepository transactionRepository,
            UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    /**
     *
     * @return all transactions
     */
    public LiveData<List<TransactionDto>> getHistory() {
        return transactionRepository.getAllTransactions();
    }

    /**
     * adds a new transaction
     * @param transactionDto
     */
    public void addTransaction(TransactionDto transactionDto) {
        UserDto user = transactionDto.getUser();
        String userShortname = user.getShortName();
        Integer userId = user.getUserId();

        // if we didn't got a userId but a user shortname create the new user
        if (userId == null && userShortname != null && !userShortname.isEmpty()) {
            user = userService.getOrCreateUserByShortName(userShortname);
            transactionDto.setUser(user);
        }

        transactionRepository.addTransaction(transactionDto);
    }
}
