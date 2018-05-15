package main;

import java.util.*;
import java.awt.SystemTray;
import java.text.*;

/**
 * The Sudoku class creates a random standard (9x9) Sudoku board through the use
 * of backtracking techniques.
 */
public class Sudoku {
	public static int[][] board;
	public static final int BOARD_WIDTH = 9;
	public static final int BOARD_HEIGHT = 9;
	
	public Sudoku() {
		board = new int[BOARD_WIDTH][BOARD_HEIGHT];
	}
	
	public int[][] nextBoard(int difficulty) {
		board = new int[BOARD_WIDTH][BOARD_HEIGHT];
		nextCell(0, 0);
		makeHoles(difficulty);
		return board;

	}
	
	public boolean nextCell(int x, int y) {
		int queA = x;
		int queB = y;
		int[] toCheck = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		int tmp = 0;
		int current = 0;
		int top = toCheck.length;

		for (int i = top - 1; i > 0; i--) {
			current = rand.nextInt(i);
			tmp = toCheck[current];
			toCheck[current] = toCheck[i];
			toCheck[i] = tmp;
		}

		for (int i = 0; i < toCheck.length; i++) {
			if (legalMove(x, y, toCheck[i])) {
				board[x][y] = toCheck[i];
				if (x == 8) {
					if (y == 8) return true;
					else {
						queA = 0;
						queB = y + 1;
					}
				} else {
					queA = x + 1;
				}
				if (nextCell(queA, queB)) return true;
			}
		}
		board[x][y] = 0;
		return false;
	}
	
	boolean legalMove(int x, int y, int current) {
		for (int i = 0; i < 9; i++) {
			if (current == board[x][i]) return false;
		}
		for (int i = 0; i < 9; i++) {
			if (current == board[i][y]) return false;
		}
		int cornerA = 0;
		int cornerB = 0;
		if (x > 2) {
			if (x > 5) cornerA = 6;
			else cornerA = 3;
		}
			
		if (y > 2) {
			if (y > 5) cornerB = 6;
			else cornerB = 3;
		}
		for (int i = cornerA; i < 10 && i < cornerA + 3; i++)
			for (int j = cornerB; j < 10 && j < cornerB + 3; j++)
				if (current == board[i][j])
					return false;
		return true;
	}
	
	public void makeHoles(int holesToMake) {
		double rS = 81;
		double rH = (double) holesToMake;

		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				double chance = rH / rS;
				if (Math.random() <= chance) {
					board[i][j] = 0;
					rH--;
				}
				rS--;
			}
	}
	
	public void getCell() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				System.out.print(board[i][j]+ " ");
			System.out.println();
		}
		System.out.println();
	}
	
	public void setCell(int row, int col, int val) {
		if(legalMove(row, col, val)) board[row][col] = val;
		else System.out.println("Invalid move! Try again");
	}

	public static void main(String[] args) {
		Sudoku sg = new Sudoku();
		//difficult can be set between 0 and 81:
		sg.nextBoard(38);
		sg.getCell();
		
		String gameState = "";
		System.out.println("A valid move is in the format of three integers.");
		System.out.println("Moves should be in the format: <Row> <Col> <Value to Insert>");
		//game loop to get and set cell values:
		while(!gameState.equals("exit")) {
			//to exit the game, user must type "exit" and hit enter
			Scanner sc = new Scanner(System.in);	
			gameState = sc.nextLine();
			System.out.println("user entered: " + gameState);
			if(!gameState.equals("exit")) {
				//if the user made a move:
				Scanner breakDown = new Scanner(gameState);
				int row = breakDown.nextInt();
				int col = breakDown.nextInt();
				int val = breakDown.nextInt();
				sg.setCell(row, col, val);
				sg.getCell();
			}
		}
		System.out.println("game over");
	}

}