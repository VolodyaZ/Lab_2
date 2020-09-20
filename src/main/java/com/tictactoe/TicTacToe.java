package com.tictactoe;

class TicTacToe {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private final int SIZE;
    private final int WINNING_NUM;

    private char[][] board;
    private int madeTurns;
    private boolean gameOver;
    private boolean tie;

    TicTacToe(int size, int winningNum) {
        this.board = new char[size][size];
        this.SIZE = size;
        this.madeTurns = 0;
        this.gameOver = false;
        this.tie = false;
        this.WINNING_NUM = winningNum;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                board[i][j] = ' ';
            }
            drawLine(i, size);
        }
    }

    public void makeMove(int num) throws Exception {
        int i = (num - 1) / SIZE;
        int j = (num - 1) % SIZE;
        if (num <= 0 && num > SIZE * SIZE || board[i][j] != ' ') {
            throw new Exception("wrong input");
        }
        char ch;
        if (madeTurns++ % 2 == 0) {
            ch = 'X';
        } else {
            ch = 'O';
        }
        board[i][j] = ch;
        drawBoard();
        if (checkGameOver(i, j)) {
            gameOver = true;
        } else if (madeTurns >= SIZE * SIZE) {
            gameOver = true;
            tie = true;
        }
    }

    public boolean gameOver() {
        return gameOver;
    }

    public int getCurrentPlayer() {
        return madeTurns % 2 + 1;
    }

    public boolean tie() {
        return tie;
    }

    private boolean checkGameOver(int lastRow, int lastColumn) {
        return checkRow(lastRow, lastColumn) ||
                checkColumn(lastRow, lastColumn) ||
                checkMainDiag(lastRow, lastColumn) ||
                checkSideDiag(lastRow, lastColumn);

    }

    private boolean checkRow(int row, int column) {
        int count = 0;
        for (int j = column - 1; j >= 0; --j) {
            if (board[row][j] == board[row][column]) {
                count++;
            } else {
                break;
            }
        }
        for (int j = column + 1; j < SIZE; ++j) {
            if (board[row][j] == board[row][column]) {
                count++;
            } else {
                break;
            }
        }
        return count + 1 >= WINNING_NUM;
    }

    private boolean checkColumn(int row, int column) {
        int count = 0;
        for (int i = row - 1; i >= 0; --i) {
            if (board[i][column] == board[row][column]) {
                count++;
            } else {
                break;
            }
        }
        for (int i = row + 1; i < SIZE; ++i) {
            if (board[i][column] == board[row][column]) {
                count++;
            } else {
                break;
            }
        }
        return count + 1 >= WINNING_NUM;
    }

    private boolean checkMainDiag(int row, int column) {
        int count = 0;
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; --i, --j) {
            if (board[i][j] == board[row][column]) {
                count++;
            } else {
                break;
            }
        }
        for (int i = row + 1, j = column + 1; i < SIZE && j < SIZE; ++i, ++j) {
            if (board[i][j] == board[row][column]) {
                count++;
            } else {
                break;
            }
        }
        return count + 1 >= WINNING_NUM;
    }

    private boolean checkSideDiag(int row, int column) {
        int count = 0;
        for (int i = row - 1, j = column + 1; i >= 0 && j < SIZE; --i, ++j) {
            if (board[i][j] == board[row][column]) {
                count++;
            } else {
                break;
            }
        }
        for (int i = row + 1, j = column - 1; i < SIZE && j >= 0; ++i, --j) {
            if (board[i][j] == board[row][column]) {
                count++;
            } else {
                break;
            }
        }
        return count + 1 >= WINNING_NUM;
    }

    private void drawBoard() {
        for (int i = 0; i < SIZE; ++i) {
            drawLine(i, SIZE);
        }
    }

    private void drawLine( int lineInd, int numOfCells) {
        if (lineInd == 0) {
            for (int i = 0; i < numOfCells; ++i) {
                System.out.print(" ___ ");
            }
            System.out.println();
        }
        for (int j = 0; j < numOfCells; ++j) {
            System.out.printf("|%-2d |", lineInd * SIZE + j + 1);
        }
        System.out.println();
        for (int j = 0; j < numOfCells; ++j) {
            if (board[lineInd][j] == 'X') {
                System.out.printf("| " + ANSI_RED + "%c" + ANSI_RESET + " |", this.board[lineInd][j]);
            } else {
                System.out.printf("| " + ANSI_GREEN + "%c" + ANSI_RESET + " |", this.board[lineInd][j]);
            }
        }
        System.out.println();
        for (int j = 0; j < numOfCells; ++j) {
            System.out.print("|___|");
        }
        System.out.println();
    }
}
