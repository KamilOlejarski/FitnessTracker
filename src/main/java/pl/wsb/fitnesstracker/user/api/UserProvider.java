package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return list of all users
     */
    List<User> findAllUsers();

    /**
     * Retrieves all users born before the given date (i.e. older than that date).
     *
     * @param date the reference date
     * @return list of users whose birthdate is before {@code date}
     */
    List<User> findUsersOlderThan(LocalDate date);

    /**
     * Searches users by email fragment, case-insensitive.
     *
     * @param email partial or full email to search for
     * @return list of matching users
     */
    List<User> findUsersByEmail(String email);

}
