package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * Simplified DTO containing only basic user identification data.
 */
public record UserSimpleDto(@Nullable Long id, String firstName, String lastName) {
}
