package com.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3, 3);
        Scanner scanner = new Scanner(System.in);
        while (!game.gameOver()) {
            System.out.println("Player " + game.getCurrentPlayer() + ":(type index of chosen cell)");
            try {
                game.makeMove(scanner.nextInt());
            } catch (Exception ex) {
                System.out.println("Wrong input. Try again");
            }
        }
        if (game.tie()) {
            System.out.println("Tie");
        } else {
            System.out.println("Player " + (game.getCurrentPlayer() % 2 + 1) + " wins");
        }
    }
}
