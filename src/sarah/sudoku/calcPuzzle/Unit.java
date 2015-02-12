package sarah.sudoku.calcPuzzle;

import java.util.ArrayList;

/**
 * A unit of the sudoku.
 */
public class Unit {

	private int number;
	private ArrayList<Integer> choiceList;
	
	/**
	 * Get the number in the unit
	 * @return the number of the unit
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Set the number to the unit
	 * @param the number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * Get the choice list
	 * @return the choice list of the unit
	 */
	public ArrayList<Integer> getChoiceList() {
		return choiceList;
	}
	
	/**
	 * Set the choice list
	 * @param the choice list
	 */
	public void setChoiceList(ArrayList<Integer> choiceList) {
		this.choiceList = choiceList;
	}
}
