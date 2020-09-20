package com.tictactoe;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(4, 3);
        try (Scanner scanner = new Scanner(System.in)) {
            int nextMove;
            while (!game.gameOver()) {
                System.out.println("Player " + game.getCurrentPlayer() + ":(type index of chosen cell)");
                nextMove = scanner.nextInt();
                try {
                    game.makeMove(nextMove);
                } catch (Exception ex) {
                    System.out.println("Wrong input. Try again");
                }
            }
        } catch(Exception ex) {
            System.out.println("Scanning Error");
        }
        if (game.tie()) {
            System.out.println("Tie");
        } else if (game.gameOver()){
            System.out.println("Player " + (game.getCurrentPlayer() % 2 + 1) + " wins");
        }
    }
}
