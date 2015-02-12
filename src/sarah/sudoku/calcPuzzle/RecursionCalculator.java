package sarah.sudoku.calcPuzzle;


/**
 * Testing. Will not be used.
 */
public class RecursionCalculator {

	private Unit[][] temp = new Unit[9][9];
	int i = 0;
	int j = 0;
	
	
	public RecursionCalculator(Unit[][] sudoku) {
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku.length; j++) {
				temp[i][j] = new Unit();
				temp[i][j].setNumber(sudoku[i][j].getNumber());
			}
		}
	}
	
	public Unit[][] recursionUnits(Unit[][] sudoku) {
		int k = 0;	
		if (temp[0][0].getNumber() != 0) {
			//go to next.
		}
		while (k < temp[0][0].getChoiceList().size()) {
			temp[0][0].setNumber(temp[0][0].getChoiceList().get(k));
			
//			detectRow(sudoku,temp[0][0].getChoiceList(),0);
//			detectColumn(sudoku,temp[0][0].getChoiceList(),0);
//			detectBlock(sudoku,temp[0][0].getChoiceList(),0,0);
		
			if (temp[0][0].getChoiceList().isEmpty()) {
				break;
			}
		
			else if ((i == 9) && (j == 9)) {
				return temp;
			}
			
			//try next.
		}
		
		
		return temp;
	}
	
	
	
	
	
}
