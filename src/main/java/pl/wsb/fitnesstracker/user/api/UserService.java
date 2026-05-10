package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user the user to be created
     * @return the created user
     */
    User createUser(User user);

    /**
     * Deletes the user with the given ID.
     *
     * @param userId ID of the user to delete
     */
    void deleteUser(Long userId);

    /**
     * Updates an existing user with data from the given DTO.
     *
     * @param userId  ID of the user to update
     * @param userDto DTO carrying the new field values
     * @return the updated user
     */
    User updateUser(Long userId, UserDto userDto);

}
