package ch.ffhs.drugstore.shared.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DrugstoreMapper {

    DrugstoreMapper INSTANCE = Mappers.getMapper(DrugstoreMapper.class);

    List<DrugDto> drugDtoList(List<DrugWithUnitAndDrugTypeAndSubstance> drugEntityList);

    @Mapping(target = "unit", ignore = true)
    @Mapping(target = "drugType", ignore = true)
    @Mapping(target = "substance", ignore = true)
    DrugDto drugToDrugDto(DrugWithUnitAndDrugTypeAndSubstance drugEntity);

    DrugDto createDrugDtoToDrugDto(CreateDrugDto createDrugDto);

    Drug drugDtoToDrug(DrugDto drugDto);


    List<DrugDto> drugListToDrugDtoList(List<DrugWithUnitAndDrugTypeAndSubstance> drugEntity);

    SignatureDto signatureToSignatureDto(
            SignatureWithUserAndSignatureDrugsAndDrugs signatureEntity);
}
