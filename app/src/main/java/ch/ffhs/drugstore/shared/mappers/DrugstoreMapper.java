package ch.ffhs.drugstore.shared.mappers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        suppressTimestampInGenerated = true)
public interface DrugstoreMapper {

    DrugstoreMapper INSTANCE = Mappers.getMapper(DrugstoreMapper.class);

    @Mappings({
            @Mapping(target = ".", source = "drug"),
            @Mapping(target = "unit", source = "unit.title"),
            @Mapping(target = "drugType", source = "drugType.title"),
            @Mapping(target = "substance", source = "substance.title")
    })
    DrugDto drugToDrugWithUnitAndDrugTypesAndSubstanceDto(
            DrugWithUnitAndDrugTypeAndSubstance drugEntity);

    @Mappings({
            @Mapping(target = "unit.title", source = "unit"),
            @Mapping(target = "drugType.title", source = "drugType"),
            @Mapping(target = "substance.title", source = "substance")
    })
    DrugWithUnitAndDrugTypeAndSubstance drugDtoToDrugWithUnitAndDrugTypeAndSubstance(
            DrugDto drugDto);

    DrugDto createDrugDtoToDrugDto(CreateDrugDto createDrugDto);

    Drug drugDtoToDrug(DrugDto drugDto);

    List<DrugDto> drugListToDrugDtoList(List<DrugWithUnitAndDrugTypeAndSubstance> drugEntity);

    SignatureDto signatureToSignatureDto(
            SignatureWithUserAndSignatureDrugsAndDrugs signatureEntity);
}
