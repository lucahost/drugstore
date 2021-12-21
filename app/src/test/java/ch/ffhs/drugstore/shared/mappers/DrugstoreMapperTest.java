package ch.ffhs.drugstore.shared.mappers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.time.ZonedDateTime;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.DrugType;
import ch.ffhs.drugstore.data.entity.Substance;
import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.data.entity.Unit;
import ch.ffhs.drugstore.data.entity.User;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.data.relation.TransactionWithDrugAndUser;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import util.TestUtil;

public class DrugstoreMapperTest {

    @Test
    public void drugDtoList() {

    }

    @Test
    public void drugToDrugDto() {
        // given
        DrugWithUnitAndDrugTypeAndSubstance drug = TestUtil.createDrugWithAllRelations(1, 1, 1, 1);

        // when
        DrugDto drugDto = DrugstoreMapper.INSTANCE.drugToDrugWithUnitAndDrugTypesAndSubstanceDto(
                drug);

        //then
        assertNotNull(drugDto);
        assertEquals(drugDto.getDrugId(), 1);
        assertThat(drugDto.getTitle(), is(not(isEmptyOrNullString())));
        assertThat(drugDto.getDrugType(), is(not(isEmptyOrNullString())));
        assertThat(drugDto.getSubstance(), is(not(isEmptyOrNullString())));
        assertThat(drugDto.getSubstance(), is(not(isEmptyOrNullString())));
        assertTrue(drugDto.getTolerance() > 0);
        assertTrue(drugDto.getStockAmount() > 0);
    }

    @Test
    public void createDrugDtoToDrugDto() {
        CreateDrugDto createDrugDto = TestUtil.createCreateDrugDto();

        DrugDto drugDto = DrugstoreMapper.INSTANCE.createDrugDtoToDrugDto(createDrugDto);

        assertEquals(String.valueOf(createDrugDto.getDrugTypeId()), drugDto.getDrugType());
        assertEquals(createDrugDto.getSubstance(), drugDto.getSubstance());
        assertEquals(createDrugDto.getTitle(), drugDto.getTitle());
        assertEquals(createDrugDto.getTolerance(), drugDto.getTolerance(), 0);
        assertEquals(createDrugDto.isFavorite(), drugDto.isFavorite());
        assertEquals(createDrugDto.getDosage(), drugDto.getDosage());
        assertEquals(String.valueOf(createDrugDto.getUnitId()), drugDto.getUnit());
    }

    @Test
    public void drugDtoToDrug() {
        DrugDto drugDto = TestUtil.createDrugDto(1);
        drugDto.setSubstance(null);
        drugDto.setDrugType(null);
        drugDto.setUnit(null);

        Drug drug = DrugstoreMapper.INSTANCE.drugDtoToDrug(drugDto);

        assertEquals(drugDto.isFavorite(), drug.isFavorite());
        assertEquals(drugDto.getDrugId(), drug.getDrugId());
        assertEquals(drugDto.getTitle(), drug.getTitle());
        assertEquals(drugDto.getStockAmount(), drug.getStockAmount(), 0);
        assertEquals(drugDto.getDosage(), drug.getDosage());

        // Cant parse these back
        assertNotEquals(drugDto.getUnit(), String.valueOf(drug.getUnitId()));
        assertNotEquals(drugDto.getSubstance(), String.valueOf(drug.getSubstanceId()));
        assertNotEquals(drugDto.getDrugType(), String.valueOf(drug.getDrugTypeId()));
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
        Integer transactionId = 1;
        int userId = 2;
        String firstName = "a";
        String lastName = "b";
        String shortName = "c";
        String emailAddress = "d";
        String externalId = "e";
        int drugId = 3;
        String title = "Drug";
        Integer drugTypeId = 101;
        String drugTypeTitle = "ORAL";
        Integer substanceId = 202;
        String substanceTitle = "Substance 01";
        Integer unitId = 303;
        String unitTitle = "mg";
        String dosage = "100mg";
        double tolerance = 0.0;
        double stockAmount = 1.0;
        boolean isFavorite = true;
        ZonedDateTime createdAt = ZonedDateTime.now();
        double amount = 1.0;
        String patient = "Steve";
        TransactionWithDrugAndUser transaction = new TransactionWithDrugAndUser();
        transaction.transaction = new Transaction(transactionId, userId, drugId, createdAt, amount,
                patient);
        Unit unit = new Unit(unitId, unitTitle);
        Substance substance = new Substance(substanceId, substanceTitle);
        DrugType drugType = new DrugType(drugTypeId, 0, drugTypeTitle);
        Drug drug = new Drug(drugId, title, drugTypeId, substanceId, unitId, dosage,
                tolerance, stockAmount, isFavorite);

        transaction.drug = new DrugWithUnitAndDrugTypeAndSubstance();
        transaction.drug.drug = drug;
        transaction.drug.unit = unit;
        transaction.drug.substance = substance;
        transaction.drug.drugType = drugType;
        transaction.user = new User(userId, firstName, lastName, shortName, emailAddress,
                externalId);

        // when
        TransactionDto transactionDto = DrugstoreMapper.INSTANCE.transactionToTransactionDto(
                transaction);

        //then
        assertNotNull(transactionDto);
        assertEquals(transactionId, transactionDto.getTransactionId());
        assertEquals(drugId, transactionDto.getDrug().getDrugId());
        assertEquals(userId, (int) transactionDto.getUser().getUserId());
        assertEquals(createdAt, transactionDto.getCreatedAt());
        assertEquals(amount, transactionDto.getAmount(), 0);
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