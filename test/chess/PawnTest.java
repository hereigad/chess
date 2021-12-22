package chess;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    Pawn pawn = new Pawn(new int[] {1, 1});

    @org.junit.jupiter.api.Test
    void validateMovement() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(i == 1 && j == 2) {
                    assertTrue(pawn.validateMovement(new int[]{i, j}, null));
                } else {
                    assertFalse(pawn.validateMovement(new int[]{i, j}, null));
                }
                if((i == 0 || i == 2) && j == 2) {
                    assertTrue(pawn.validateMovement(new int[]{i, j}, new Pawn(new int[]{i, j})));
                } else {
                    assertFalse(pawn.validateMovement(new int[]{i, j}, new Pawn(new int[]{i, j})));
                }
            }
        }
    }
}