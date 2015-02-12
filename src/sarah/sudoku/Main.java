package sarah.sudoku;

import sarah.sudoku.calcPuzzle.Unit;
import sarah.sudoku.error.CannotSolveException;

/**
 * This is my App for my mom. She loves playing sudoku.
 * The math game sudoku. I changed the structure more flexible and upload some existing sudoke puzzle.
 * I will fix the sudoku puzzle solving problems in version 2.0.
 * 
 * @author Sarah
 * @version 1.8
 */
public class Main {

	public static void main(String[] args) {

		try {
			
			new SudokuGui();
			
		} catch (CannotSolveException e) {

//			gui.cardLayout.show(gui.frame.getContentPane(),"fifth");
			
			System.out.println("You got the wrong sudoku puzzle. It cannot be solved.");
			
			System.out.println("Testing......");
		}
		
		
	}
	
	public static void test(Unit[][] sudoku) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sudoku[i][j].getChoiceList() + " ");
			}
			System.out.println();
		}
	}


	
}
