package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * DTO containing only user ID and email address, used for email-based search results.
 */
public record UserEmailDto(@Nullable Long id, String email) {
}
