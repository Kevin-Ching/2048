package com.company;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    Game game = new Game();
    int[][] board = game.board;
    int[][] correctBoard = new int[4][4];

    @Test
    public void mergeUpTest() {
        board[0] = new int[]{0, 4, 16, 8};
        board[1] = new int[]{0, 2, 0, 2};
        board[2] = new int[]{0, 2, 4, 4};
        board[3] = new int[]{0, 16, 4, 2};
        correctBoard[0] = new int[]{0, 4, 16, 8};
        correctBoard[1] = new int[]{0, 4, 8, 2};
        correctBoard[2] = new int[]{0, 16, 0, 4};
        correctBoard[3] = new int[]{0, 0, 0, 2};

        game.mergeUp();
        for (int i = 0; i < 4; i++) {
            assertArrayEquals(game.board[i], correctBoard[i]);
        }
    }

    @Test
    public void mergeDownTest() {
        board[0] = new int[]{0, 4, 16, 8};
        board[1] = new int[]{0, 2, 0, 2};
        board[2] = new int[]{0, 2, 4, 4};
        board[3] = new int[]{0, 16, 4, 2};
        correctBoard[0] = new int[]{0, 0, 0, 8};
        correctBoard[1] = new int[]{0, 4, 0, 2};
        correctBoard[2] = new int[]{0, 4, 16, 4};
        correctBoard[3] = new int[]{0, 16, 8, 2};

        game.mergeDown();
        for (int i = 0; i < 4; i++) {
            assertArrayEquals(game.board[i], correctBoard[i]);
        }
    }

    @Test
    public void mergeLeftTest() {
        board[0] = new int[]{0, 4, 16, 8};
        board[1] = new int[]{0, 2, 0, 2};
        board[2] = new int[]{2, 2, 4, 4};
        correctBoard[0] = new int[]{4, 16, 8, 0};
        correctBoard[1] = new int[]{4, 0, 0, 0};
        correctBoard[2] = new int[]{4, 8, 0, 0};

        game.mergeLeft();
        for (int i = 0; i < 4; i++) {
            assertArrayEquals(game.board[i], correctBoard[i]);
        }
    }

    @Test
    public void mergeRightTest() {
        board[0] = new int[]{4, 16, 8, 0};
        board[1] = new int[]{2, 0, 2, 0};
        board[2] = new int[]{2, 2, 4, 4};
        correctBoard[0] = new int[]{0, 4, 16, 8};
        correctBoard[1] = new int[]{0, 0, 0, 4};
        correctBoard[2] = new int[]{0, 0, 4, 8};

        game.mergeRight();
        for (int i = 0; i < 4; i++) {
            assertArrayEquals(game.board[i], correctBoard[i]);
        }
    }

    @Test
    public void noZerosTest() {
        assertFalse(game.noZeros());

        board[0] = new int[]{0, 4, 16, 8};
        board[1] = new int[]{0, 2, 0, 2};
        board[2] = new int[]{0, 2, 4, 4};
        board[3] = new int[]{0, 16, 4, 2};
        assertFalse(game.noZeros());

        board[0] = new int[]{2, 4, 16, 8};
        board[1] = new int[]{2, 2, 2, 2};
        board[2] = new int[]{2, 2, 4, 4};
        board[3] = new int[]{2, 16, 4, 2};
        assertTrue(game.noZeros());
    }

    @Test
    public void gameOverTest() {
        assertFalse(game.gameOver());

        board[0] = new int[]{0, 4, 16, 8};
        board[1] = new int[]{0, 2, 0, 2};
        board[2] = new int[]{0, 2, 4, 4};
        board[3] = new int[]{0, 16, 4, 2};
        assertFalse(game.gameOver());

        board[0] = new int[]{2, 4, 2, 4};
        board[1] = new int[]{4, 2, 4, 2};
        board[2] = new int[]{2, 4, 2, 4};
        board[3] = new int[]{4, 2, 4, 2};
        assertTrue(game.gameOver());
    }

}
