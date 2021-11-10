package ch.ffhs.drugstore.data.entity;

import androidx.room.PrimaryKey;

public class User {
    @PrimaryKey(autoGenerate = true)
    public long userId;
    public String firstName;
    public String lastName;
    public String shortName;
    public String emailAddress;
    public String externalId;
}
