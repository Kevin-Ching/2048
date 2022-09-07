package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Game game = null;
        switch (args.length) {
            case 0 -> {
                game = new Game();
            }
            case 1 -> {
                Pattern pattern = Pattern.compile("\\d+");
                if (!pattern.matcher(args[0]).matches() || Integer.parseInt(args[0]) < 1) {
                    exitWithMessage("Dimension argument must be a positive integer");
                }
                game = new Game(Integer.parseInt(args[0]));
            }
            default -> exitWithMessage("Too many arguments. "
                    + "Only one argument is accepted for the board dimension");
        }

        System.out.println("Welcome to bootleg 2048");
        printHelp();
        game.addRand(true);
        game.addRand(true);
        System.out.flush();
        game.printBoard();

        boolean wonYet = false;
        while (!game.gameOver()) {
            String command = getCommand().strip().toLowerCase();
            boolean validMove = false;
            boolean moveMade = false;
            switch (command) {
                case "w", "a", "s", "d" -> {
                    validMove = game.validMerge(command);
                    moveMade = true;
                }
                case "help" -> printHelp();
                case "exit" -> exitWithMessage(null);
                default -> System.out.println("Incorrect command");
            }
            if (validMove) {
                game.merge(command);
                game.addRand();
            } else if (moveMade) {
                System.out.println("Invalid move");
            }
            game.printBoard();
            if (game.won && !wonYet) {
                System.out.println("You win! Keep playing to increase your score!");
                wonYet = true;
            }
        }
        exitWithMessage("Game Over! Your score is " + game.score);
    }

    static String getCommand() {
        System.out.print("\n> ");
        System.out.flush();
        if (in.hasNextLine()) {
            return in.nextLine();
//        } else if (!_readers.isEmpty()) {
//            _inp = new Scanner(_readers.remove(0));
//            return getCommand(prompt);
        } else {
            return null;
        }
    }

    static void exitWithMessage(String message) {
        if (message != null && !message.equals("")) {
            System.out.println(message);
        }
        System.exit(0);
    }

    static void printHelp() {
        String help = "Use WASD to move the board, type EXIT to exit, and type HELP to display this message.";
        System.out.println(help);
    }

    static Scanner in = new Scanner(System.in);
}
