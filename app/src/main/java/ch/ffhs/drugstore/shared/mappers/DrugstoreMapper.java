package ch.ffhs.drugstore.shared.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.data.entity.User;
import ch.ffhs.drugstore.data.relation.DrugTypeWithParentDrugType;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.data.relation.TransactionWithDrugAndUser;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;

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

    Transaction transactionDtoToTransaction(TransactionDto transactionDto);

    @Mapping(target = ".", source = "transaction")
    TransactionDto transactionToTransactionDto(TransactionWithDrugAndUser transaction);

    List<TransactionDto> transactionListToTransactionDtoList(
            List<TransactionWithDrugAndUser> transaction);

    List<UserDto> userListToUserDtoList(List<User> users);

    UserDto userToUserDto(User user);

    @Mapping(target = ".", source = "drugType")
    DrugTypeDto drugTypeDtoFromDrugType(DrugTypeWithParentDrugType drugType);

    List<DrugTypeDto> drugTypeListToDrugTypeDtoList(List<DrugTypeWithParentDrugType> drugTypes);


}
