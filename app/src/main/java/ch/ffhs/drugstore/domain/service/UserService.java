package ch.ffhs.drugstore.domain.service;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.UserRepository;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;

public class UserService {
    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserById(int userId) {
        return userRepository.getUserById(userId);
    }

    public UserDto getUserByShortName(String shortName) {
        return userRepository.getUserByShortName(shortName);
    }

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
