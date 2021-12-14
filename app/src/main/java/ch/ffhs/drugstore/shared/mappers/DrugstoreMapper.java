package ch.ffhs.drugstore.shared.mappers;

import androidx.lifecycle.LiveData;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import dagger.Provides;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapStructMapper {

    @Provides
    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    @IterableMapping()
    LiveData<List<DrugDto>> drugLiveDataListToDrugDtoLiveDataList(
            LiveData<List<DrugWithUnitAndDrugTypeAndSubstance>> drugEntity);

    @Mapping(source = "drugType", target = "drugType")
    DrugDto drugToDrugDto(DrugWithUnitAndDrugTypeAndSubstance drugEntity);

    List<DrugDto> drugListToDrugDtoList(List<DrugWithUnitAndDrugTypeAndSubstance> drugEntity);

    SignatureDto signatureToSignatureDto(
            SignatureWithUserAndSignatureDrugsAndDrugs signatureEntity);
}
