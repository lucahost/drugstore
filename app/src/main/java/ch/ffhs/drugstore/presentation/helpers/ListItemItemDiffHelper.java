package ch.ffhs.drugstore.presentation.helpers;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.SelectableDrugDto;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;

/**
 * Recycler view list items helpers
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class ListItemItemDiffHelper {
    /**
     * Compares two {@link DrugDto} recycler view list items
     */
    public static final DiffUtil.ItemCallback<DrugDto> drugDtoItemDiffCallback =
            new DiffUtil.ItemCallback<DrugDto>() {
                @Override
                public boolean areItemsTheSame(@NonNull DrugDto oldItem, @NonNull DrugDto newItem) {
                    return oldItem.getDrugId() == newItem.getDrugId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull DrugDto oldItem,
                        @NonNull DrugDto newItem) {
                    return oldItem.getTitle().equals(newItem.getTitle())
                            && oldItem.getDrugType().equals(newItem.getDrugType())
                            && oldItem.getSubstance().equals(newItem.getSubstance())
                            && oldItem.getDosage().equals(newItem.getDosage())
                            && oldItem.getTolerance() == newItem.getTolerance()
                            && oldItem.getStockAmount() == newItem.getStockAmount()
                            && oldItem.isFavorite() == newItem.isFavorite();
                }
            };
    /**
     * Compares two {@link DrugDto} recycler view list items
     */
    public static final DiffUtil.ItemCallback<SelectableDrugDto> selectableDrugDtoItemDiffCallback =
            new DiffUtil.ItemCallback<SelectableDrugDto>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull SelectableDrugDto oldItem, @NonNull SelectableDrugDto newItem) {
                    return drugDtoItemDiffCallback.areItemsTheSame(oldItem, newItem);
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull SelectableDrugDto oldItem, @NonNull SelectableDrugDto newItem) {
                    return drugDtoItemDiffCallback.areContentsTheSame(oldItem, newItem);
                }
            };
    /**
     * Compares two {@link TransactionDto} recycler view list items
     */
    public static final DiffUtil.ItemCallback<TransactionDto> transactionDtoItemDiffCallback =
            new DiffUtil.ItemCallback<TransactionDto>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull TransactionDto oldItem, @NonNull TransactionDto newItem) {
                    return oldItem.getTransactionId().equals(newItem.getTransactionId());
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull TransactionDto oldItem, @NonNull TransactionDto newItem) {
                    return oldItem.getDrug().getDrugId() == newItem.getDrug().getDrugId()
                            && oldItem.getAmount() == newItem.getAmount();
                }
            };
    /**
     * Compares two {@link SignatureDto} recycler view list items
     */
    public static final DiffUtil.ItemCallback<SignatureDto> signatureDtoItemDiffCallback =
            new DiffUtil.ItemCallback<SignatureDto>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull SignatureDto oldItem, @NonNull SignatureDto newItem) {
                    return oldItem.getSignatureId() == newItem.getSignatureId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull SignatureDto oldItem, @NonNull SignatureDto newItem) {
                    return Objects.equals(oldItem.getUser().getUserId(),
                            newItem.getUser().getUserId())
                            && oldItem.getSignatureId() == newItem.getSignatureId()
                            && oldItem.getCreatedAt().equals(newItem.getCreatedAt());
                }
            };
    /**
     * Compares two {@link SignatureDrugDto} recycler view list items
     */
    public static final DiffUtil.ItemCallback<SignatureDrugDto> signatureDrugDtoItemDiffCallback =
            new DiffUtil.ItemCallback<SignatureDrugDto>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull SignatureDrugDto oldItem, @NonNull SignatureDrugDto newItem) {
                    return oldItem.getSignatureId() == newItem.getSignatureId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull SignatureDrugDto oldItem, @NonNull SignatureDrugDto newItem) {
                    return oldItem.getActualAmount() == newItem.getActualAmount()
                            && oldItem.getExpectedAmount() == newItem.getExpectedAmount()
                            && oldItem.getDrugId() == newItem.getDrugId();
                }
            };

    private ListItemItemDiffHelper() {
        throw new IllegalStateException("Utility class");
    }
}
