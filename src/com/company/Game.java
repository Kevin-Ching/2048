package com.company;

import java.util.Arrays;
import java.util.Random;

public class Game {

    Game() {
        this(4);
    }

    Game(int dim) {
        this.dim = dim;
        board = new int[dim][dim];
    }

    Game(Game game) {
        dim = game.dim;
        board = new int[dim][dim];
        cloneBoard(game);
    }

    void cloneBoard(Game game) {
        for (int i = 0; i < dim; i++) {
            board[i] = game.board[i].clone();
        }
    }

    void mergeUp() {
        shiftUp();
        // combine numbers
        for (int j = 0; j < dim; j++) {
            for (int i = 1; i < dim; i++) {
                if (board[i][j] == board[i - 1][j]) {
                    board[i - 1][j] *= 2;
                    board[i][j] = 0;
                    int newInt = board[i - 1][j];
                    updateLongestDigits(newInt);
                    score += newInt;
                    if (newInt == 2048) {
                        won = true;
                    }
                }
            }
        }
        shiftUp();
    }

    void shiftUp() {
        for (int j = 0; j < dim; j++) {
            for (int i = 0; i < dim; i++) {
                if (board[i][j] == 0) {
                    int temp = i + 1;
                    while (temp < dim && board[temp][j] == 0) {
                        temp++;
                    }
                    if (temp < dim) {
                        board[i][j] = board[temp][j];
                        board[temp][j] = 0;
                    }
                }
            }
        }
    }

    void mergeDown() {
        shiftDown();
        // combine numbers
        for (int j = 0; j < dim; j++) {
            for (int i = dim - 2; i >= 0; i--) {
                if (board[i][j] == board[i + 1][j]) {
                    board[i + 1][j] *= 2;
                    board[i][j] = 0;
                    int newInt = board[i + 1][j];
                    updateLongestDigits(newInt);
                    score += newInt;
                    if (newInt == 2048) {
                        won = true;
                    }
                }
            }
        }
        shiftDown();
    }

    void shiftDown() {
        for (int j = 0; j < dim; j++) {
            for (int i = dim - 1; i >= 0; i--) {
                if (board[i][j] == 0) {
                    int temp = i - 1;
                    while (temp >= 0 && board[temp][j] == 0) {
                        temp--;
                    }
                    if (temp >= 0) {
                        board[i][j] = board[temp][j];
                        board[temp][j] = 0;
                    }
                }
            }
        }
    }

    void mergeLeft() {
        shiftLeft();
        // combine numbers
        for (int i = 0; i < dim; i++) {
            for (int j = 1; j < dim; j++) {
                if (board[i][j] == board[i][j - 1]) {
                    board[i][j - 1] *= 2;
                    board[i][j] = 0;
                    int newInt = board[i][j - 1];
                    updateLongestDigits(newInt);
                    score += newInt;
                    if (newInt == 2048) {
                        won = true;
                    }
                }
            }
        }
        shiftLeft();
    }

    void shiftLeft() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (board[i][j] == 0) {
                    int temp = j + 1;
                    while (temp < dim && board[i][temp] == 0) {
                        temp++;
                    }
                    if (temp < dim) {
                        board[i][j] = board[i][temp];
                        board[i][temp] = 0;
                    }
                }
            }
        }
    }

    void mergeRight() {
        shiftRight();
        // combine numbers
        for (int i = 0; i < dim; i++) {
            for (int j = dim - 2; j >= 0; j--) {
                if (board[i][j] == board[i][j + 1]) {
                    board[i][j + 1] *= 2;
                    board[i][j] = 0;
                    int newInt = board[i][j + 1];
                    updateLongestDigits(newInt);
                    score += newInt;
                    if (newInt == 2048) {
                        won = true;
                    }
                }
            }
        }
        shiftRight();
    }

    void shiftRight() {
        for (int i = 0; i < dim; i++) {
            for (int j = dim - 1; j >= 0; j--) {
                if (board[i][j] == 0) {
                    int temp = j - 1;
                    while (temp >= 0 && board[i][temp] == 0) {
                        temp--;
                    }
                    if (temp >= 0) {
                        board[i][j] = board[i][temp];
                        board[i][temp] = 0;
                    }
                }
            }
        }
    }

    void merge(String direction) {
        switch (direction) {
            case "w" -> mergeUp();
            case "a" -> mergeLeft();
            case "s" -> mergeDown();
            case "d" -> mergeRight();
        }
    }

    boolean noZeros() {
        for (int[] row : board) {
            for (int elem : row) {
                if (elem == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean gameOver() {
        if (!noZeros()) {
            return false;
        }
        Game copy = new Game(this);

//        copy.mergeUp();
//        for (int i = 0; i < dim; i++) {
//            if (!Arrays.equals(board[i], copy.board[i])) {
//                return false;
//            }
//        }
        return !validMergeAny();

//        copy.mergeDown();
//        for (int i = 0; i < dim; i++) {
//            if (!Arrays.equals(board[i], copy.board[i])) {
//                return false;
//            }
//        }
//
//        copy.mergeLeft();
//        for (int i = 0; i < dim; i++) {
//            if (!Arrays.equals(board[i], copy.board[i])) {
//                return false;
//            }
//        }
//
//        copy.mergeRight();
//        for (int i = 0; i < dim; i++) {
//            if (!Arrays.equals(board[i], copy.board[i])) {
//                return false;
//            }
//        }
    }

    boolean validMergeAny() {
        String[] directions = new String[]{"w", "a", "s", "d"};
        for (String s : directions) {
            if (validMerge(s)) {
                return true;
            }
        }
        return false;
    }

    boolean validMerge(String s) {
        Game copy = new Game(this);
        switch (s) {
            case "w" -> copy.mergeUp();
            case "a" -> copy.mergeLeft();
            case "s" -> copy.mergeDown();
            case "d" -> copy.mergeRight();
        }
        return !this.equals(copy);
    }

    void printBoard() {
        System.out.println("Score: " + score);
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (board[i][j] == 0) {
                    System.out.print("_" + " ".repeat(longest_digits));
                    System.out.flush();
                } else {
                    int currInt = board[i][j];
                    int currLen = String.valueOf(currInt).length();
                    System.out.print(currInt + " ".repeat(longest_digits - currLen + 1));
                    System.out.flush();
                }
            }
            System.out.println();
        }
    }

    void addRand() {
        addRand(false);
    }

    void addRand(boolean start) {
        int i = r.nextInt(dim);
        int j = r.nextInt(dim);
        while (board[i][j] != 0) {
            i = r.nextInt(dim);
            j = r.nextInt(dim);
        }
        if (start) {
            board[i][j] = r.nextInt(1, 3) * 2;
        } else {
            board[i][j] = 2;
        }
    }

    void updateLongestDigits(int newInt) {
        int length = String.valueOf(newInt).length();
        longest_digits = Math.max(longest_digits, length);
    }

    boolean equals(Game game) {
        for (int i = 0; i < dim; i++) {
            if (!Arrays.equals(board[i], game.board[i])) {
                return false;
            }
        }
        return true;
    }

    int dim;
    int[][] board;
    int score = 0;
    int longest_digits = 1;
    boolean won = false;
    Random r = new Random();
}
