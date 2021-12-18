package ch.ffhs.drugstore.domain.usecase.dispensary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import com.github.javafaker.Faker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.service.HistoryService;
import ch.ffhs.drugstore.shared.dto.dispensary.SubmitDispenseDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import ch.ffhs.drugstore.shared.exceptions.InsufficientAmountException;
import util.TestUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DispenseDrug.class})
public class DispenseDrugTest {
    Faker faker = new Faker();
    DrugManagementService drugManagementService = null;
    HistoryService historyService = null;

    @Before
    public void setUp() throws Exception {
        drugManagementService = mock(DrugManagementService.class);
        historyService = mock(HistoryService.class);
    }

    @After
    public void tearDown() throws Exception {
        DispensaryService dispensaryService = null;
        DrugManagementService drugManagementService = null;
        HistoryService historyService = null;
    }

    @Test(expected = DrugNotFoundException.class)
    public void testExecuteUseCaseDrugNotFound() throws DrugstoreException {
        // Arrange
        int drugId = 0;
        String userShortName = faker.funnyName().name();
        String patient = faker.funnyName().name();
        double amount = faker.number().randomDouble(2, 1, 10);

        SubmitDispenseDto submitDispenseDto = new SubmitDispenseDto(drugId, userShortName, patient,
                amount);

        // Act
        DispenseDrug dispenseDrugUseCase = new DispenseDrug(drugManagementService, historyService);
        dispenseDrugUseCase.execute(submitDispenseDto);
    }

    @Test(expected = InsufficientAmountException.class)
    public void testExecuteUseCaseWithProvidedDrugWithoutAmountThrowsInsufficientAmount()
            throws DrugstoreException {
        // Arrange
        int drugId = 0;
        String userShortName = faker.funnyName().name();
        String patient = faker.funnyName().name();
        double amount = faker.number().randomDouble(2, 1, 10);

        DrugDto fakeDrug = TestUtil.createDrugDto(0);
        fakeDrug.setStockAmount(0);
        when(drugManagementService.getDrugById(0)).thenReturn(fakeDrug);

        SubmitDispenseDto submitDispenseDto = new SubmitDispenseDto(drugId, userShortName, patient,
                amount);

        // Act
        DispenseDrug dispenseDrugUseCase = new DispenseDrug(drugManagementService, historyService);

        dispenseDrugUseCase.execute(submitDispenseDto);
    }

    @Test
    public void testExecuteUseCase() throws Exception {
        // Arrange
        int drugId = 0;
        String userShortName = faker.funnyName().name();
        String patient = faker.funnyName().name();
        double amount = faker.number().randomDouble(2, 1, 10);

        DrugDto fakeDrug = TestUtil.createDrugDto(0);
        fakeDrug.setStockAmount(10);
        when(drugManagementService.getDrugById(0)).thenReturn(fakeDrug);

        SubmitDispenseDto submitDispenseDto = new SubmitDispenseDto(drugId, userShortName, patient,
                amount);

        UpdateDrugAmountDto updateDrugAmountDtoMock = mock(UpdateDrugAmountDto.class);
        whenNew(UpdateDrugAmountDto.class).withAnyArguments().thenReturn(updateDrugAmountDtoMock);

        TransactionDto transactionDtoMock = mock(TransactionDto.class);
        whenNew(TransactionDto.class).withArguments(submitDispenseDto, fakeDrug).thenReturn(
                transactionDtoMock);

        // Act
        DispenseDrug dispenseDrugUseCase = new DispenseDrug(drugManagementService, historyService);

        dispenseDrugUseCase.execute(submitDispenseDto);

        // Validate
        verify(drugManagementService, times(1)).getDrugById(0);
        verify(historyService, times(1)).addTransaction(transactionDtoMock);
        verify(drugManagementService, times(1)).updateDrugAmount(updateDrugAmountDtoMock);

    }
}