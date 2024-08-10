package com.example.lesson_api.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VersionComparatorTest {
    @Test
    public void lessThanTest() {
        // Given
        String precedingVersion = "22.1.2";
        String followingVersion = "24.1.5";

        // When
        boolean isLessThan = VersionComparator.of(precedingVersion).lessThan(followingVersion);

        // Then
        assertTrue(isLessThan);
    }

    @Test
    public void equalVersionTest() {
        // Given
        String precedingVersion = "22.1.2";
        String followingVersion = "22.1.2";

        // When
        boolean isGreaterThanOrEquals = VersionComparator.of(precedingVersion).greaterThanOrEquals(followingVersion);

        // Then
        assertTrue(isGreaterThanOrEquals);
    }

    @Test
    public void greaterVersionTest() {
        // Given
        String precedingVersion = "24.1.5";
        String followingVersion = "22.1.2";

        // When
        boolean isGreaterThanOrEquals = VersionComparator.of(precedingVersion).greaterThanOrEquals(followingVersion);

        // Then
        assertTrue(isGreaterThanOrEquals);
    }
}