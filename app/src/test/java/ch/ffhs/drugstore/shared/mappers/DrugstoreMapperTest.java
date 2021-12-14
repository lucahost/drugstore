package ch.ffhs.drugstore.shared.mappers;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.DrugType;
import ch.ffhs.drugstore.data.entity.Substance;
import ch.ffhs.drugstore.data.entity.Unit;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

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
        DrugDto drugDto = DrugstoreMapper.INSTANCE.drugToDrugDto(drug);

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
}