package ch.ffhs.drugstore.presentation.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.databinding.DrugItemBinding;

public class DrugListAdapter extends ListAdapter<Drug, DrugListAdapter.DrugHolder> {
  private static final DiffUtil.ItemCallback<Drug> DIFF_CALLBACK =
      new DiffUtil.ItemCallback<Drug>() {
        @Override
        public boolean areItemsTheSame(@NonNull Drug oldItem, @NonNull Drug newItem) {
          return oldItem.getDrugId() == newItem.getDrugId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Drug oldItem, @NonNull Drug newItem) {
          return oldItem.getTitle().equals(newItem.getTitle())
              && oldItem.getDosage().equals(newItem.getDosage());
        }
      };

  private DrugListAdapter.OnItemClickListener clickListener;

  @Inject
  public DrugListAdapter() {
    super(DIFF_CALLBACK);
  }

  @NonNull
  @Override
  public DrugHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    DrugItemBinding binding =
        DrugItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new DrugListAdapter.DrugHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull DrugHolder holder, int position) {
    holder.bind(position);
  }

  // allows clicks events to be caught
  public void setClickListener(OnItemClickListener itemClickListener) {
    this.clickListener = itemClickListener;
  }

  public interface OnItemClickListener {
    void onItemClick(View view, Drug drug);
  }

  /** Provide a reference to the type of views that you are using (custom ViewHolder). */
  public class DrugHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView title;
    private final TextView secondary;
    private final ImageButton itemAction;

    DrugHolder(DrugItemBinding binding) {
      super(binding.getRoot());
      title = binding.title;
      secondary = binding.secondary;
      itemAction = binding.itemAction;
    }

    void bind(int position) {
      title.setText(getItem(position).getTitle());
      secondary.setText(getItem(position).getDosage());
      itemAction.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      if (clickListener != null)
        clickListener.onItemClick(itemAction, getItem(getAdapterPosition()));
    }
  }
}
