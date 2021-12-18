package util;

import com.github.javafaker.Faker;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.DrugType;
import ch.ffhs.drugstore.data.entity.Substance;
import ch.ffhs.drugstore.data.entity.Unit;
import ch.ffhs.drugstore.data.entity.User;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.EditDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.dto.management.signature.CreateSignatureDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;

public class TestUtil {
    private static final Faker faker = new Faker();

    public static User createUser(int userId) {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String shortName = faker.name().prefix();
        String externalId = faker.number().toString();
        String email = faker.internet().emailAddress();
        return new User(userId, firstName, lastName, shortName, email, externalId);
    }

    public static UserDto createUserDto(int userId) {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String shortName = faker.name().prefix();
        String externalId = faker.number().toString();
        String email = faker.internet().emailAddress();
        return new UserDto(userId, firstName, lastName, shortName, email, externalId);
    }

    public static DrugWithUnitAndDrugTypeAndSubstance createDrugWithAllRelations(int drugTypeId,
            int unitId, int substanceId,
            int drugId) {
        String drugTypeTitle = faker.funnyName().name();
        DrugType drugType = new DrugType(drugTypeId, null, drugTypeTitle);

        String unitTitle = faker.funnyName().name();
        Unit unit = new Unit(unitId, unitTitle);

        String substanceTitle = faker.funnyName().name();
        Substance substance = new Substance(substanceId, substanceTitle);

        String drugTitle = faker.funnyName().name();
        String dosage = "100mg";
        Double tolerance = faker.number().randomDouble(2, 0, 2);
        Double stockAmount = faker.number().randomDouble(2, 0, 100);
        boolean isFavorite = true;
        Drug drug = new Drug(drugId, drugTitle, drugTypeId, substanceId, unitId, dosage, tolerance,
                stockAmount, isFavorite);

        DrugWithUnitAndDrugTypeAndSubstance drugWithUnitAndDrugTypeAndSubstance =
                new DrugWithUnitAndDrugTypeAndSubstance();
        drugWithUnitAndDrugTypeAndSubstance.drugType = drugType;
        drugWithUnitAndDrugTypeAndSubstance.unit = unit;
        drugWithUnitAndDrugTypeAndSubstance.substance = substance;
        drugWithUnitAndDrugTypeAndSubstance.drug = drug;

        return drugWithUnitAndDrugTypeAndSubstance;
    }

    public static DrugDto createDrugDto(int drugId) {
        String title = faker.funnyName().name();
        String drugType = faker.funnyName().name();
        String substance = faker.funnyName().name();
        String unit = faker.funnyName().name();
        String dosage = "100mg";
        double tolerance = faker.number().randomDouble(2, 1, 10);
        double stockAmount = faker.number().randomDouble(2, 1, 10);
        boolean isFavorite = false;
        return new DrugDto(0, title, drugType, substance, unit, dosage, tolerance, stockAmount,
                isFavorite);
    }

    public static CreateDrugDto createCreateDrugDto() {
        String title = faker.funnyName().name();
        String dosage = "100mg";
        int drugTypeId = faker.number().numberBetween(1, 3);
        int unitId = faker.number().numberBetween(1, 3);
        String substance = faker.name().name();
        double tolerance = faker.number().randomDouble(2, 1, 2);
        boolean isFavorite = false;
        return new CreateDrugDto(title, dosage, drugTypeId, unitId, substance, tolerance,
                isFavorite);
    }

    public static EditDrugDto createEditDrugDto(int drugId) {
        String title = faker.funnyName().name();
        String dosage = "100mg";
        int drugTypeId = faker.number().numberBetween(1, 3);
        int unitId = faker.number().numberBetween(1, 3);
        String substance = faker.name().name();
        double tolerance = faker.number().randomDouble(2, 1, 2);
        boolean isFavorite = false;
        return new EditDrugDto(drugId, title, dosage, drugTypeId, unitId, substance, tolerance,
                isFavorite);
    }

    public static UpdateDrugAmountDto createUpdateDrugAmountDto(int drugId) {
        double amount = faker.number().randomDouble(2, 1, 10);
        return new UpdateDrugAmountDto(drugId, amount);
    }

    public static DrugTypeDto createDrugTypeDto(int drugTypeId) {
        String drugTypeTitle = faker.name().name();
        return new DrugTypeDto(drugTypeId, drugTypeTitle);
    }

    public static UnitDto createDrugUnitDto(int unitId) {
        String unitTitle = faker.name().name();
        return new UnitDto(unitId, unitTitle);
    }

    public static SubstanceDto createSubstanceDto(int substanceId) {
        String substanceTitle = faker.funnyName().name();
        return new SubstanceDto(substanceId, substanceTitle);
    }

    public static TransactionDto createTransactionDto(int transactionId) {
        DrugDto drug = createDrugDto(faker.number().randomDigit());
        UserDto user = createUserDto(faker.number().randomDigit());
        ZonedDateTime createdAt = zonedDateFromDate(faker.date().birthday());
        double amount = faker.number().randomDouble(2, 1, 10);
        String patient = faker.funnyName().name();
        return new TransactionDto(transactionId, drug, user, createdAt, amount, patient);
    }

    public static SignatureDto createSignatureDto(int signatureId) {
        UserDto user = createUserDto(faker.number().randomDigit());
        ZonedDateTime createdAt = zonedDateFromDate(faker.date().birthday());
        return new SignatureDto(signatureId, user, createdAt);
    }

    public static SignatureDrugDto createSignatureDrugDto() {
        int signatureId = faker.number().randomDigit();
        int drugId = faker.number().randomDigit();
        DrugDto drug = createDrugDto(drugId);
        double expectedAmount = faker.number().randomDouble(2, 1, 10);
        double actualAmount = faker.number().randomDouble(2, 1, 10);
        boolean approved = false;
        return new SignatureDrugDto(signatureId, drugId, drug, expectedAmount, actualAmount, approved);
    }

    public static CreateSignatureDto createCreateSignatureDto() {
        String userShortName = faker.funnyName().name();
        List<SignatureDrugDto> signatureDrugs = createObjectListWithSupplier(2, TestUtil::createSignatureDrugDto);
        return new CreateSignatureDto(userShortName, signatureDrugs);
    }

    private static <T> List<T> createObjectListWithSupplier(int count, Supplier<T> creator)  {
        List<T> objectList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            objectList.add(creator.get());
        }
        return objectList;
    }

    private static ZonedDateTime zonedDateFromDate(Date date) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return instant.atZone(defaultZoneId);
    }
}
