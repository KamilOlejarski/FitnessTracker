package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller exposing CRUD operations for {@link User} resources under {@code /v1/users}.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    /**
     * Returns full details of all users.
     *
     * @return list of all users as {@link UserDto}
     */
    @GetMapping
    public List<UserDto> getUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Returns simplified info (ID, first name, last name) for all users.
     *
     * @return list of all users as {@link UserSimpleDto}
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getSimpleUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserSimpleDto)
                .toList();
    }

    /**
     * Returns full details of a single user by ID.
     *
     * @param id user ID
     * @return user as {@link UserDto}
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Searches users by email fragment, case-insensitive. Returns only ID and email.
     *
     * @param email partial or full email to search for
     * @return list of matching users as {@link UserEmailDto}
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUsersByEmail(@RequestParam String email) {
        return userProvider.findUsersByEmail(email).stream()
                .map(userMapper::toUserEmailDto)
                .toList();
    }

    /**
     * Returns all users born before the given date (older than that date).
     *
     * @param time reference date in ISO format (yyyy-MM-dd)
     * @return list of matching users as {@link UserDto}
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {
        return userProvider.findUsersOlderThan(time).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Creates a new user.
     *
     * @param userDto user data from request body
     * @return the created user as {@link UserDto}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User created = userService.createUser(
                new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email()));
        return userMapper.toUserDto(created);
    }

    /**
     * Updates an existing user.
     *
     * @param userId  ID of the user to update
     * @param userDto new field values from request body
     * @return the updated user as {@link UserDto}
     */
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User updated = userService.updateUser(userId, userDto);
        return userMapper.toUserDto(updated);
    }

    /**
     * Deletes a user by ID.
     *
     * @param userId ID of the user to delete
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
