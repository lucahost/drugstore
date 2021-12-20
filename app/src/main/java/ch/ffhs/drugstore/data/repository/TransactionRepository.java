package ch.ffhs.drugstore.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.TransactionDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.data.relation.TransactionWithDrugAndUser;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

/**
 * This class abstracts the data layer methods for Transaction data
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class TransactionRepository {
    private final TransactionDao transactionDao;
    private final LiveData<List<TransactionWithDrugAndUser>> allTransactions;
    private final DrugstoreMapper mapper;

    @Inject
    public TransactionRepository(TransactionDao transactionDao) {
        mapper = DrugstoreMapper.INSTANCE;
        this.transactionDao = transactionDao;
        allTransactions = transactionDao.getAllTransactions();
    }

    public LiveData<List<TransactionDto>> getAllTransactions() {
        return Transformations.map(allTransactions, mapper::transactionListToTransactionDtoList);
    }

    public void addTransaction(TransactionDto transactionDto) {
        Transaction transaction = mapper.transactionDtoToTransaction(transactionDto);
        transactionDao.insert(transaction);
    }

    public void update(Transaction transaction) {
        transactionDao.update(transaction);
    }

    public void delete(Transaction transaction) {
        transactionDao.delete(transaction);
    }

    public void deleteAll() {
        transactionDao.deleteAll();
    }
}
