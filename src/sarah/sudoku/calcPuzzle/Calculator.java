package sarah.sudoku.calcPuzzle;

import java.util.ArrayList;

import sarah.sudoku.error.CannotSolveException;


/**
 * This is for main methods that used to solve the sudoku.
 */
abstract class Calculator {
	
	/**
	 * Main calculate
	 * @param sudoku
	 * @return if the sudoku is solved.
	 * @throws CannotSolveException
	 */
	public boolean templateCalculate(Unit[][] sudoku) throws CannotSolveException {
		if (hiddenSingle(sudoku)) {
			return true;
		}
		if (nakedSingle(sudoku)) {
			return true;
		}
		
		hiddenPairs(sudoku);
		if (nakedSingle(sudoku)) {
			return true;
		}
		
		hiddenTriples(sudoku);
		if (nakedSingle(sudoku)) {
			return true;
		}
		
		throw new CannotSolveException();
	}

	private void detectColumn(Unit[][] sudoku, ArrayList<Integer> choiceList, int column) {
		for (int i = 0; i < 9; i++) {
			Integer number = sudoku[i][column].getNumber();// get the number which already appears
			choiceList.remove(number);// delete the number 
		}
		
	}
	
	private void detectRow(Unit[][] sudoku, ArrayList<Integer> choiceList, int row) {
		for (int i = 0; i < 9; i++) {
			Integer number = sudoku[row][i].getNumber();// get the number which already appears
			choiceList.remove(number);// delete the number
		}
		
	}
	
	private void detectBlock(Unit[][] sudoku, ArrayList<Integer> choiceList, int row, int column) {
		for (int i = row / 3 * 3; i < (row / 3 * 3 + 3); i++) {
			for (int j = column / 3 * 3; j < (column / 3 * 3 + 3); j++) {
				Integer number = sudoku[i][j].getNumber();// get the number which already appears
				choiceList.remove(number);// delete the number
			}
		}
		
	}
	
	/**
	 * Calculate all the choice list and the only answer, until there are none.
	 * @param sudoku
	 */
	public boolean hiddenSingle(Unit[][] sudoku) throws CannotSolveException {
		int n=0;
		while (n != getLeft(sudoku)) {
			n = getLeft(sudoku);
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {// for every unit
					int number = sudoku[i][j].getNumber();
					if (number != 0) {// if the number is given then 
						continue;// do not need to calculate the choice list
					}
					ArrayList<Integer> choiceList = new ArrayList<Integer>();
					for (int k = 0; k < 9; k++) {
						choiceList.add(k + 1);// build a choice list contain 1 to 9
					}
					
					detectRow(sudoku,choiceList,i);
					detectColumn(sudoku,choiceList,j);
					detectBlock(sudoku,choiceList,i,j);// delete the choice to the smallest list
					
					if (choiceList.isEmpty()) {
						throw new CannotSolveException();
					}
					
					sudoku[i][j].setChoiceList(choiceList);// save the list into the unit
					
					if (choiceList.size() == 1) {// if there is only one choice
						sudoku[i][j].setNumber(choiceList.get(0));// the choice is the answer, and put it in
					}
				}		
			}
		}
		return isFinish(sudoku);
	}
	
	private boolean singleRow(Unit[][] sudoku, int row, int column, int n) {
		for (int i = 0; i < 9; i++) {// in s row
			if (i == row){
				continue;}// pass itself
			ArrayList<Integer> otherRowChoiceList = sudoku[i][column].getChoiceList();// get others choice list
			if (otherRowChoiceList.contains(n)) {
				return true;// if there is a same choice
			}
		}
		return false;
	}
	
	private boolean singleColumn(Unit[][] sudoku, int row, int column, int n) {
		for (int j = 0; j < 9; j++) {// in a column
			if (j == column){
				continue;}// pass itself
			ArrayList<Integer> otherColumnChoiceList = sudoku[row][j].getChoiceList();// get others choice list
			if (otherColumnChoiceList.contains(n)) {
				return true;// if there is a same choice
			}
		}
		return false;
	}

	private boolean singleBlock(Unit[][] sudoku,int row, int column, int n) {
		for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
			for (int j = column / 3 * 3; j < column / 3 * 3 + 3; j++) {// in a block
				if (i == row){
					if (j == column){continue;}}// pass itself
				ArrayList<Integer> otherBlockChoiceList = sudoku[i][j].getChoiceList();// get others choice list
				if (otherBlockChoiceList.contains(n)) {
					return true;// if there is a same choice
				}
			}
		}
		return false;
	}

	
	/**
	 * Find out if the choice only appear in one unit, and then this is the answer.
	 * @param sudoku
	 */
	public boolean nakedSingle(Unit[][] sudoku) throws CannotSolveException {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {// for every unit
				int number = sudoku[i][j].getNumber();
				ArrayList<Integer> choiceList = sudoku[i][j].getChoiceList();
				
				if (number != 0) {// if the answer is concrete then
					continue;// do not need to calculate
				}
				
				for (int k = 0; k < choiceList.size(); k++) {// for every number in the choice list
					int n = choiceList.get(k);// get the number
					boolean same = singleRow(sudoku, i, j, n) || singleColumn(sudoku, i, j, n) 
							|| singleBlock(sudoku, i, j, n);
										
					if (!same){// there are not any same choices
						sudoku[i][j].setNumber(n);// the choice is the answer
						choiceList.clear();
						choiceList.add(n);// change the choice list to only one number
						sudoku[i][j].setChoiceList(choiceList);// save the choice list
						break;// go to next unit 
					}//as long as the number of the unit is solved, do not need to go on
				}
			}
		}
		return isFinish(sudoku);
	}


	/**
	 * Detect if the sudoku is solved or not
	 * @param sudoku
	 * @return if the sudoku is completely solved
	 */
	private boolean isFinish(Unit[][] sudoku) {
		int sum = getLeft(sudoku);
		if (sum == 0){// no zero in the sudoku 
			return true;// the sudoku is solved
		}
		else return false;// if not, still need to calculate
	}
	
	/**
	 * Detect the number in sudoku which is unsolved
	 * @param sudoku
	 * @return the number unsolved
	 */
	private int getLeft(Unit[][] sudoku) {
		int sum = 0;// the number of zeros in the sudoku
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int number = sudoku[i][j].getNumber();
				if (number == 0){// if there is a zero
					sum = sum + 1;// add one to sum
				}
			}
		}
		return sum;
	}
	
	public void hiddenTriples(Unit[][] sudoku) throws CannotSolveException {
		
		
	}
	
	public void hiddenPairs(Unit[][] sudoku) throws CannotSolveException {
		
		
	}
	
	
	public boolean recursionUnits(Unit[][] sudoku) {
		
		return isFinish(sudoku);
		
	}
	
	public abstract int[][] outputPuzzle(Unit[][] sudoku);
	
}
