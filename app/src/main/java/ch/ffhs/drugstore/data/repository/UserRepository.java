package ch.ffhs.drugstore.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.UserDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.entity.User;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;
/**
 * This class abstracts the data layer
 * methods for User data
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class UserRepository {
    private final UserDao userDao;
    private final LiveData<List<User>> allUsers;
    private final DrugstoreMapper mapper;

    @Inject
    public UserRepository(Application application) {
        DrugstoreDatabase db = DrugstoreDatabase.getDatabase(application);
        userDao = db.userDao();
        mapper = DrugstoreMapper.INSTANCE;
        allUsers = userDao.getAllUsers();
    }

    public LiveData<List<UserDto>> getAllUsers() {
        return Transformations.map(allUsers, mapper::userListToUserDtoList);
    }

    public UserDto getUserById(int userId) {
        return mapper.userToUserDto(userDao.getUserById(userId));
    }

    public UserDto getUserByShortName(String shortName) {
        return mapper.userToUserDto(userDao.getUserByShortName(shortName));
    }

    public long addUser(UserDto userDto) {
        User user = mapper.userDtoToUser(userDto);
        return userDao.insert(user);
    }
}
