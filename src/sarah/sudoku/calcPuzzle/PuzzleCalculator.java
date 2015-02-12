package sarah.sudoku.calcPuzzle;

import java.util.ArrayList;



/**
 * Set puzzle. Access to exiting sudoku puzzle.
 */
public class PuzzleCalculator extends Calculator {
	
	/**
	 *  The sudoku puzzle for solving.
	 */
	public Unit[][] sudoku = new Unit[9][9];
	
	/**
	 * The sudoku puzzle. Difficulty: 2
	 */
	public static final int[][] sudoku6 = { 
		{1,0,0,0,2,0,0,0,0},
		{5,0,0,8,0,0,3,0,1},
		{0,0,6,0,0,4,7,0,0},
		{2,0,0,3,0,0,0,0,8},
		{0,4,0,0,1,0,0,2,0},
		{8,0,0,0,0,6,0,0,9},
		{0,0,8,4,0,0,5,0,0},
		{3,0,7,0,0,1,0,0,4},
		{0,0,0,0,9,0,0,0,6},
	};
	/**
	 * The sudoku puzzle. Difficulty: 1
	 */
	public static final int[][] sudoku1 = { 
		{0,4,7,0,5,0,0,0,8},
		{6,0,5,0,3,0,2,0,1},
		{0,0,0,7,0,6,0,3,0},
		{0,0,6,0,7,0,0,2,4},
		{9,0,0,8,0,4,0,0,6},
		{4,5,0,0,1,0,9,0,0},
		{0,1,0,5,0,2,0,0,0},
		{2,0,8,0,4,0,5,0,3},
		{5,0,0,0,9,0,7,1,0},
	};
	/**
	 * The sudoku puzzle. Difficulty: 1
	 */
	public static final int[][] sudoku5 = { 
		{0,0,9,0,0,1,6,2,0},
		{5,7,0,0,2,8,0,3,0},
		{3,0,0,7,0,0,0,0,4},
		{8,9,0,0,7,0,4,0,0},
		{0,6,0,4,0,3,0,9,0},
		{0,0,1,0,9,0,0,7,6},
		{6,0,0,0,0,7,0,0,8},
		{0,4,0,1,3,0,0,6,5},
		{0,2,7,6,0,0,9,0,0},
		};
	
	/**
	 * The sudoku puzzle. Difficulty: 2
	 */
	public static final int[][] sudoku2 = {
		{4,0,0,7,0,1,0,0,6},
		{7,2,0,0,0,0,0,8,9},
		{0,0,6,0,0,0,5,0,0},
		{0,9,0,1,0,5,0,6,0},
		{0,0,0,9,3,8,0,0,0},
		{0,7,0,6,0,4,0,5,0},
		{0,0,7,0,0,0,6,0,0},
		{1,3,0,0,0,0,0,4,8},
		{6,0,0,2,0,3,0,0,5},
		};
	
	/**
	 * The sudoku puzzle. Difficulty: 3
	 */
	public static final int[][] sudoku3 = {
		{0,0,2,0,6,0,0,0,0},
		{0,0,6,0,0,7,0,0,9},
		{0,0,7,0,0,8,4,0,0},
		{9,0,0,0,3,0,0,0,1},
		{3,0,0,5,0,2,0,0,4},
		{6,0,0,0,1,0,0,0,3},
		{0,0,5,3,0,0,8,0,0},
		{8,0,0,6,0,0,5,0,0},
		{0,0,0,0,2,0,9,0,0},
		};
	
	/**
	 * The sudoku puzzle. Difficulty: 4
	 */
	public static final int[][] sudoku4 = {
		{0,0,0,0,5,7,0,8,0},
		{3,0,0,0,0,0,0,0,0},
		{0,0,2,9,0,0,4,0,0},
		{2,0,0,8,0,9,3,0,0},
		{4,0,0,0,0,0,0,0,7},
		{0,0,7,4,0,6,0,0,8},
		{0,0,6,0,0,2,7,0,0},
		{0,0,0,0,0,0,0,0,5},
		{0,9,0,3,1,0,0,0,0},
		};
	
	/**
	 * The sudoku puzzle. Difficulty: 0
	 */	
	public static final int[][] sudoku0 = {
		{0,4,6,0,0,1,0,0,9},
		{7,9,0,3,5,0,6,0,1},
		{0,0,5,0,9,0,3,2,4},
		{0,0,0,0,8,0,4,0,5},
		{0,3,0,2,1,5,0,0,0},
		{5,8,9,0,0,0,0,7,2},
		{2,0,0,6,0,9,5,1,0},
		{1,0,8,5,0,2,0,0,0},
		{0,5,3,0,4,0,2,0,7},
		};


	/**
	 * Build an sudoku puzzle.
	 */
	public PuzzleCalculator(int[][] puzzle) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = new Unit(); // initialize every unit
				sudoku[i][j].setChoiceList(new ArrayList<Integer>());// initialize choice list
				int number = puzzle[i][j]; // get number of the sudoku puzzle
				sudoku[i][j].setNumber(number); // store the number for calculating
			}
		}
	}
	
	/* 
	 * Output the sudoku
	 * @see sarah.sudoku.calcPuzzle.Calculator#outputSudoku(sarah.sudoku.calcPuzzle.Unit[][])
	 */
	public int[][] outputPuzzle(Unit[][] sudoku) {
		int[][] number = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				number[i][j] = sudoku[i][j].getNumber();// print every number in a row
			}
		}
		return number;
	}

}