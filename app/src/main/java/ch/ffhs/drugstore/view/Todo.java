package ch.ffhs.drugstore.view;

public class Todo {
  private String text;
  private boolean checked;

  public Todo(String text, boolean checked) {
    this.text = text;
    this.checked = checked;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isChecked() {
    return checked;
  }

  public void toggle() {
    this.checked = !this.checked;
  }
}
