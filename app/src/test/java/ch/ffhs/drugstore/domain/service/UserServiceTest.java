package ch.ffhs.drugstore.domain.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.ffhs.drugstore.data.repository.UserRepository;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;
import util.TestUtil;

public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        userService = new UserService(userRepository);
    }

    @After
    public void tearDown() throws Exception {
        userService = null;
    }

    @Test
    public void getUserById() {
        // Arrange
        int userId = 1;
        UserDto userDto = TestUtil.createUserDto(userId);
        when(userRepository.getUserById(anyInt())).thenReturn(userDto);

        // Act
        UserDto actualUser = userService.getUserById(userId);

        // Verify
        assertEquals(userDto, actualUser);
        verify(userRepository, times(1)).getUserById(anyInt());
        verify(userRepository, times(0)).addUser(any());
        verify(userRepository, times(0)).getUserByShortName(any());
        verify(userRepository, times(0)).getAllUsers();
    }

    @Test
    public void getUserByShortName() {
        // Arrange
        int userId = 1;
        UserDto userDto = TestUtil.createUserDto(userId);
        String userShortName = userDto.getShortName();
        when(userRepository.getUserByShortName(anyString())).thenReturn(null);
        when(userRepository.getUserByShortName(userShortName)).thenReturn(userDto);

        // Act
        UserDto actualUserByNonExistingShortName = userService.getUserByShortName("");
        UserDto actualUser = userService.getUserByShortName(userShortName);

        // Verify
        assertEquals(null, actualUserByNonExistingShortName);
        assertEquals(userDto, actualUser);
        verify(userRepository, times(0)).getUserById(anyInt());
        verify(userRepository, times(0)).addUser(any());
        verify(userRepository, times(2)).getUserByShortName(any());
        verify(userRepository, times(0)).getAllUsers();
    }

    @Test
    public void getOrCreateUserByShortNameWithExistingUser() {
        // Arrange
        int userId = 1;
        UserDto userDto = TestUtil.createUserDto(userId);
        String userShortName = userDto.getShortName();
        when(userRepository.getUserByShortName(userShortName)).thenReturn(userDto);

        // Act
        UserDto actualExistingUser = userService.getUserByShortName(userShortName);

        // Verify
        assertEquals(userShortName, actualExistingUser.getShortName());
        verify(userRepository, times(0)).addUser(any());
    }

    @Test
    public void getOrCreateUserByShortNameWithNonExistingUser() {
        // Arrange
        int userId = 1;
        UserDto userDto = TestUtil.createUserDto(userId);
        String userShortName = userDto.getShortName();
        when(userRepository.getUserByShortName(anyString())).thenReturn(null);

        // Act
        UserDto actualNewUser = userService.getOrCreateUserByShortName(userShortName);

        // Verify
        assertEquals(userShortName, actualNewUser.getShortName());
        verify(userRepository, times(1)).addUser(any());
    }
}