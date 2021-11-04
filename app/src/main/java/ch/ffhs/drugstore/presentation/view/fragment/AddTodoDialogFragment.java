package ch.ffhs.drugstore.presentation.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.AddTodoFragmentBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddTodoDialogFragment extends DialogFragment {

  public static final String TAG = "AddTodoDialog";

  private ConfirmAddTodoListener confirmAddTodoListener;

  @Inject
  public AddTodoDialogFragment() {
    /* TODO document why this constructor is empty */
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    if (getParentFragment() instanceof ConfirmAddTodoListener) {
      this.confirmAddTodoListener = (ConfirmAddTodoListener) getParentFragment();
    }
    if (context instanceof ConfirmAddTodoListener) {
      this.confirmAddTodoListener = (ConfirmAddTodoListener) context;
    }
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    AddTodoFragmentBinding binding = AddTodoFragmentBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    View addTodoView = View.inflate(getActivity(), R.layout.add_todo_fragment, null);
    return new AlertDialog.Builder(requireContext())
        .setView(addTodoView)
        .setMessage(getString(R.string.dialog_add_todo))
        .setPositiveButton(
            getString(R.string.add),
            (dialog, id) -> {
              EditText text = addTodoView.findViewById(R.id.todo_text);
              this.confirmAddTodoListener.onConfirmAddTodo(text.getText().toString());
            })
        .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {})
        .create();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.confirmAddTodoListener = null;
  }

  public interface ConfirmAddTodoListener {
    void onConfirmAddTodo(String text);
  }
}
