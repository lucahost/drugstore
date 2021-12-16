package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.TransactionRepository;
import ch.ffhs.drugstore.data.repository.UserRepository;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;

public class HistoryService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Inject
    public HistoryService(TransactionRepository transactionRepository,
            UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public LiveData<List<TransactionDto>> getHistory() {
        return transactionRepository.getAllTransactions();
    }

    public void addTransaction(TransactionDto transactionDto) {
        UserDto user = transactionDto.getUser();
        String userShortname = user.getShortName();
        Integer userId = user.getUserId();

        // if we didn't got a userId but a user shortname create the new user
        if (userId == null && userShortname != null && !userShortname.isEmpty()) {
            user = userRepository.getOrCreateUserByShortName(userShortname);
            transactionDto.setUser(user);
        }

        transactionRepository.addTransaction(transactionDto);
    }
}
