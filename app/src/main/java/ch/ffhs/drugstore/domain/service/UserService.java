package ch.ffhs.drugstore.domain.service;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.UserRepository;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;
/**
 * This service class returns or creates a user
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class UserService {
    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserById(int userId) {
        return userRepository.getUserById(userId);
    }

    /**
     * @param shortName
     * @return User with this short name
     */
    public UserDto getUserByShortName(String shortName) {
        return userRepository.getUserByShortName(shortName);
    }

    /**
     * gets user by short name, if not exists creates a new one
     * @param shortName
     * @return user
     */
    public UserDto getOrCreateUserByShortName(String shortName) {
        UserDto user = userRepository.getUserByShortName(shortName);
        if (user == null) {
            user = new UserDto(0, "", "", shortName, "", "");
            user.setShortName(shortName);
            long userId = userRepository.addUser(user);
            user.setUserId((int) userId);
        }
        return user;
    }
}
