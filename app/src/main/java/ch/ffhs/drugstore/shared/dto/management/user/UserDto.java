package ch.ffhs.drugstore.shared.dto.management.user;

/**
 * DTO to represent a user
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class UserDto {
    private Integer userId;
    private String shortName;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String externalId;

    public UserDto() {
    }

    public UserDto(
            Integer userId,
            String shortName,
            String firstName,
            String lastName,
            String emailAddress,
            String externalId) {
        this.userId = userId;
        this.shortName = shortName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.externalId = externalId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
