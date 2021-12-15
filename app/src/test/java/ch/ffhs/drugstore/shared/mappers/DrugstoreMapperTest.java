package ch.ffhs.drugstore.shared.mappers;

import static org.junit.Assert.*;

import androidx.room.ColumnInfo;

import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.DrugType;
import ch.ffhs.drugstore.data.entity.Substance;
import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.data.entity.Unit;
import ch.ffhs.drugstore.data.entity.User;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.data.relation.TransactionWithDrugAndUser;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;

public class DrugstoreMapperTest {

    @Test
    public void drugDtoList() {
    }

    @Test
    public void drugToDrugDto() {
        // given
        int drugId = 101;
        String title = "drug";
        int drugTypeId = 101;
        String drugTypeTitle = "Liquid";
        int substanceId = 101;
        String substanceTitle = "Acid";
        int unitId = 101;
        String unitTitle = "mcg";
        String dosage = "100mcg/mg";
        double tolerance = 1.0;
        double stockAmount = 15.0;
        boolean isFavorite = true;

        DrugWithUnitAndDrugTypeAndSubstance drug = new DrugWithUnitAndDrugTypeAndSubstance();
        drug.drug = new Drug(drugId, title, drugTypeId, substanceId, unitId,
                dosage, tolerance, stockAmount, isFavorite);
        drug.drugType = new DrugType(drugTypeId, drugTypeId, drugTypeTitle);
        drug.substance = new Substance(substanceId, substanceTitle);
        drug.unit = new Unit(unitId, unitTitle);

        // when
        DrugDto drugDto = DrugstoreMapper.INSTANCE.drugToDrugWithUnitAndDrugTypesAndSubstanceDto(
                drug);

        //then
        assertNotNull(drugDto);
        assertEquals(drugDto.getDrugId(), drugId);
        assertEquals("drug", drugDto.getTitle());
        assertEquals(drugTypeTitle, drugDto.getDrugType());
        assertEquals(substanceTitle, drugDto.getSubstance());
        assertEquals(dosage, drugDto.getDosage());
        assertEquals(tolerance, drugDto.getTolerance(), 0);
        assertEquals(stockAmount, drugDto.getStockAmount(), 0);
        assertEquals(isFavorite, drugDto.isFavorite());
    }

    @Test
    public void createDrugDtoToDrugDto() {
    }

    @Test
    public void drugDtoToDrug() {
    }

    @Test
    public void drugListToDrugDtoList() {
    }

    @Test
    public void signatureToSignatureDto() {
    }

    @Test
    public void drugToDrugWithUnitAndDrugTypesAndSubstanceDto() {
    }

    @Test
    public void drugDtoToDrugWithUnitAndDrugTypeAndSubstance() {
    }

    @Test
    public void testCreateDrugDtoToDrugDto() {
    }

    @Test
    public void testDrugDtoToDrug() {
    }

    @Test
    public void testDrugListToDrugDtoList() {
    }

    @Test
    public void testSignatureToSignatureDto() {
    }

    @Test
    public void transactionDtoToTransaction() {
    }

    @Test
    public void transactionToTransactionDto() {
        // given
        int transactionId = 1;
        int userId = 2;
        String firstName = "a";
        String lastName = "b";
        String shortName = "c";
        String emailAddress = "d";
        String externalId = "e";
        int drugId = 3;
        String title = "Drug";
        Integer drugTypeId = 101;
        Integer substanceId = 202;
        Integer unitId = 303;
        String dosage = "100mg";
        double tolerance = 0.0;
        double stockAmount = 1.0;
        boolean isFavorite = true;
        ZonedDateTime createdAt = ZonedDateTime.now();
        double amount = 1.0;
        String patient = "Steve";
        TransactionWithDrugAndUser transaction = new TransactionWithDrugAndUser();
        transaction.transaction = new Transaction(transactionId, userId, drugId, createdAt, amount, patient);
        transaction.drug = new Drug(drugId, title, drugTypeId, substanceId, unitId, dosage, tolerance, stockAmount, isFavorite);
        transaction.user = new User(userId, firstName, lastName, shortName, emailAddress, externalId);

        // when
        TransactionDto transactionDto = DrugstoreMapper.INSTANCE.transactionToTransactionDto(transaction);

        //then
        assertNotNull(transactionDto);
        assertEquals(transactionId, transactionDto.getTransactionId());
        assertEquals(drugId, transactionDto.getDrug().getDrugId());
        assertEquals(userId, (int)transactionDto.getUser().getUserId());
        assertEquals(createdAt, transactionDto.getCreatedAt());
        assertEquals(amount, transactionDto.getAmount(),0);
        assertEquals(patient, transactionDto.getPatient());

    }

    @Test
    public void transactionListToTransactionDtoList() {
    }

    @Test
    public void userListToUserDtoList() {
    }

    @Test
    public void userToUserDto() {
    }

    @Test
    public void drugTypeDtoFromDrugType() {
    }

    @Test
    public void drugTypeListToDrugTypeDtoList() {
    }
}