package ch.ffhs.drugstore.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

@Fts4
@Entity(tableName = "todo_table")
public class Todo {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "rowid")
  private int id;

  private String text;
  private boolean checked;

  public Todo(String text) {
    this.text = text;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public void setChecked(boolean checked) {
    this.checked = checked;
  }

  public void toggle() {
    this.checked = !this.checked;
  }
}
