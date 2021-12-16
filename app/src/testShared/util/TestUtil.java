package util;

import com.github.javafaker.Faker;

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
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;

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
}
