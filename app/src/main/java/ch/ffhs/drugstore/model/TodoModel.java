package ch.ffhs.drugstore.model;

public class TodoModel {
  private final String text;
  private boolean checked;

  public TodoModel(String text, boolean checked) {
    this.text = text;
    this.checked = checked;
  }

  public String getText() {
    return text;
  }

  public boolean isChecked() {
    return checked;
  }

  public void toggle() {
    this.checked = !this.checked;
  }
}
