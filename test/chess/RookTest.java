package chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    Rook rook = new Rook(new int[]{0,0});

    @Test
    void validateMovement() {
        assertTrue(rook.validateMovement(new int[]{0, 0}, null));
        assertTrue(rook.validateMovement(new int[]{0, 1}, null));
        assertTrue(rook.validateMovement(new int[]{0, 2}, null));
        assertTrue(rook.validateMovement(new int[]{0, 3}, null));
        assertTrue(rook.validateMovement(new int[]{0, 4}, null));
        assertTrue(rook.validateMovement(new int[]{0, 5}, null));
        assertTrue(rook.validateMovement(new int[]{0, 6}, null));
        assertTrue(rook.validateMovement(new int[]{0, 7}, null));
        assertTrue(rook.validateMovement(new int[]{1, 0}, null));
        assertFalse(rook.validateMovement(new int[]{1, 1}, null));
        assertFalse(rook.validateMovement(new int[]{1, 2}, null));
        assertFalse(rook.validateMovement(new int[]{1, 3}, null));
        assertFalse(rook.validateMovement(new int[]{1, 4}, null));
        assertFalse(rook.validateMovement(new int[]{1, 5}, null));
        assertFalse(rook.validateMovement(new int[]{1, 6}, null));
        assertFalse(rook.validateMovement(new int[]{1, 7}, null));
        assertTrue(rook.validateMovement(new int[]{2, 0}, null));
        assertFalse(rook.validateMovement(new int[]{2, 1}, null));
        assertFalse(rook.validateMovement(new int[]{2, 2}, null));
        assertFalse(rook.validateMovement(new int[]{2, 3}, null));
        assertFalse(rook.validateMovement(new int[]{2, 4}, null));
        assertFalse(rook.validateMovement(new int[]{2, 5}, null));
        assertFalse(rook.validateMovement(new int[]{2, 6}, null));
        assertFalse(rook.validateMovement(new int[]{2, 7}, null));
        assertTrue(rook.validateMovement(new int[]{3, 0}, null));
        assertFalse(rook.validateMovement(new int[]{3, 1}, null));
        assertFalse(rook.validateMovement(new int[]{3, 2}, null));
        assertFalse(rook.validateMovement(new int[]{3, 3}, null));
        assertFalse(rook.validateMovement(new int[]{3, 4}, null));
        assertFalse(rook.validateMovement(new int[]{3, 5}, null));
        assertFalse(rook.validateMovement(new int[]{3, 6}, null));
        assertFalse(rook.validateMovement(new int[]{3, 7}, null));
        assertTrue(rook.validateMovement(new int[]{4, 0}, null));
        assertFalse(rook.validateMovement(new int[]{4, 1}, null));
        assertFalse(rook.validateMovement(new int[]{4, 2}, null));
        assertFalse(rook.validateMovement(new int[]{4, 3}, null));
        assertFalse(rook.validateMovement(new int[]{4, 4}, null));
        assertFalse(rook.validateMovement(new int[]{4, 5}, null));
        assertFalse(rook.validateMovement(new int[]{4, 6}, null));
        assertFalse(rook.validateMovement(new int[]{4, 7}, null));
        assertTrue(rook.validateMovement(new int[]{5, 0}, null));
        assertFalse(rook.validateMovement(new int[]{5, 1}, null));
        assertFalse(rook.validateMovement(new int[]{5, 2}, null));
        assertFalse(rook.validateMovement(new int[]{5, 3}, null));
        assertFalse(rook.validateMovement(new int[]{5, 4}, null));
        assertFalse(rook.validateMovement(new int[]{5, 5}, null));
        assertFalse(rook.validateMovement(new int[]{5, 6}, null));
        assertFalse(rook.validateMovement(new int[]{5, 7}, null));
        assertTrue(rook.validateMovement(new int[]{6, 0}, null));
        assertFalse(rook.validateMovement(new int[]{6, 1}, null));
        assertFalse(rook.validateMovement(new int[]{6, 2}, null));
        assertFalse(rook.validateMovement(new int[]{6, 3}, null));
        assertFalse(rook.validateMovement(new int[]{6, 4}, null));
        assertFalse(rook.validateMovement(new int[]{6, 5}, null));
        assertFalse(rook.validateMovement(new int[]{6, 6}, null));
        assertFalse(rook.validateMovement(new int[]{6, 7}, null));
        assertTrue(rook.validateMovement(new int[]{7, 0}, null));
        assertFalse(rook.validateMovement(new int[]{7, 1}, null));
        assertFalse(rook.validateMovement(new int[]{7, 2}, null));
        assertFalse(rook.validateMovement(new int[]{7, 3}, null));
        assertFalse(rook.validateMovement(new int[]{7, 4}, null));
        assertFalse(rook.validateMovement(new int[]{7, 5}, null));
        assertFalse(rook.validateMovement(new int[]{7, 6}, null));
        assertFalse(rook.validateMovement(new int[]{7, 7}, null));
    }
}