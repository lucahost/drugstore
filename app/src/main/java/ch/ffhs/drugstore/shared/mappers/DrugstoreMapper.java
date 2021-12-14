package ch.ffhs.drugstore.shared.mappers;

import androidx.lifecycle.LiveData;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "jsr330", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DrugstoreMapper {
    @IterableMapping()
    LiveData<List<DrugDto>> drugLiveDataListToDrugDtoLiveDataList(
            LiveData<List<DrugWithUnitAndDrugTypeAndSubstance>> drugEntity);

    DrugDto drugToDrugDto(DrugWithUnitAndDrugTypeAndSubstance drugEntity);

    DrugDto createDrugDtoToDrugDto(CreateDrugDto createDrugDto);

    Drug drugDtoToDrug(DrugDto drugDto);


    List<DrugDto> drugListToDrugDtoList(List<DrugWithUnitAndDrugTypeAndSubstance> drugEntity);

    SignatureDto signatureToSignatureDto(
            SignatureWithUserAndSignatureDrugsAndDrugs signatureEntity);
}
