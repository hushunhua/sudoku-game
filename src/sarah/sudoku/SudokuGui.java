package sarah.sudoku;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sarah.sudoku.calcPuzzle.PuzzleCalculator;
import sarah.sudoku.calcPuzzle.Unit;
import sarah.sudoku.error.CannotSolveException;

public class SudokuGui {

	JButton startButton;
	JButton choiceButton;
	JButton answerButton;
	JButton againButton;
	JButton errorButton;
	JFrame frame;
	JPanel startPanel;
	JPanel choicePanel;
	JPanel puzzlePanelA;
	JPanel answerPanelA;
	JPanel puzzlePanelB;
	JPanel answerPanelB;
	JPanel errorPanel;
	JLabel startLabel;
	JLabel choiceLabel;
	JLabel errorLabel;
	JTextField name;
	JTextArea results;
	PuzzleCalculator one;
	
	CardLayout cardLayout = new CardLayout();
	Color frameColor = new Color(65,150,211);
	int[][] puzzle =  PuzzleCalculator.sudoku0;
	

	public SudokuGui() throws CannotSolveException {
		one = new PuzzleCalculator(puzzle);
		
		setStartPanel();
		setErrorPanel();
		setAnswerPanel();
		setChoicePanel();
		setPuzzlePanel();			
		setFrame();
	}

	public void setFrame() {
		frame = new JFrame("Sudoku - Sarah");
		frame.setLayout(cardLayout);
		
		Container container = frame.getContentPane();
		container.setBackground(frameColor);		
		
		frame.add(startPanel,"first");
		frame.add(choicePanel,"second");
		frame.add(puzzlePanelB,"third");
		frame.add(answerPanelB,"forth");
		frame.add(errorPanel,"fifth");
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100,50,300,300);
		frame.setVisible(true);
	}

	
	
	public void setStartPanel() {
		startPanel = new JPanel();
		startButton = new JButton("start the game");
		startButton.addActionListener(new StartListener());
		startLabel = new JLabel("Welcome to the sudoku game");
		
		startPanel.add(startLabel);
		startPanel.add(startButton);
	}
	
	public void setChoicePanel() {
		choicePanel = new JPanel();
		ChoiceListener listener = new ChoiceListener();
		choiceButton = new JButton("choose a puzzle from 1 to 5");
		choiceButton.addActionListener(listener);
		choiceLabel = new JLabel("choose a sudoku puzzle by enter one number");
		name = new JTextField(15);
		
		choicePanel.add(choiceLabel);
		choicePanel.add(name);
		choicePanel.add(choiceButton);
		
	}
	
	public void setPuzzlePanel() {
		GridLayout grid = new GridLayout(9,9);
		puzzlePanelA = new JPanel();
		puzzlePanelB = new JPanel();
		puzzlePanelA.setLayout(grid);
		
		Integer[][] a = new Integer[9][9];
		a = changeIntegers(puzzle);
		
		answerButton = new JButton("see answer");
		answerButton.addActionListener(new AnswerListener());
				
		Label[][] label = new Label[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				label[i][j] = new Label(a[i][j].toString());
				label[i][j].setBackground(Color.white);
				puzzlePanelA.add(label[i][j]);
			}
		}
		puzzlePanelB.add(puzzlePanelA);
		puzzlePanelB.add(answerButton);
				
		
	}
	
	public void setAnswerPanel() throws CannotSolveException {
		GridLayout grid = new GridLayout(9,9);
		answerPanelA = new JPanel();
		answerPanelB = new JPanel();
		answerPanelA.setLayout(grid);
		
		one.templateCalculate(one.sudoku);
		Integer[][] a = new Integer[9][9];
		a = outPrintSudoku(one.sudoku);
		
		againButton = new JButton("play again");
		againButton.addActionListener(new AgainListener());
				
		Label[][] label = new Label[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				label[i][j] = new Label(a[i][j].toString());
				label[i][j].setBackground(Color.white);
				answerPanelA.add(label[i][j]);
			}
		}
		answerPanelB.add(answerPanelA);
		answerPanelB.add(againButton);
	}
	
	public void setErrorPanel() {
		errorPanel = new JPanel();
		errorButton = new JButton("start the game again");
		errorButton.addActionListener(new StartListener());
		errorLabel = new JLabel("Error. Cannot solve the puzzle.");
		errorPanel.add(errorLabel);
		errorPanel.add(errorButton);
	}
	
	
	
	class StartListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			cardLayout.show(frame.getContentPane(),"second");
		}
	}
	
	class ChoiceListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String nameText = name.getText();
			if (nameText == "1") {
				puzzle = PuzzleCalculator.sudoku1;			
			}
			else if (nameText == "2") {
				puzzle = PuzzleCalculator.sudoku2;
			}
			else if (nameText == "3") {
				puzzle = PuzzleCalculator.sudoku3;
			}
			else if (nameText == "4") {
				puzzle = PuzzleCalculator.sudoku4;
			}
			else if (nameText == "5") {
				puzzle = PuzzleCalculator.sudoku5;
			}
			else {
				puzzle = PuzzleCalculator.sudoku0;
			}
			//set puzzle
			cardLayout.show(frame.getContentPane(),"third");
		}
		
	}
	
	class AnswerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			cardLayout.show(frame.getContentPane(),"forth");
		}
	}
	
	class AgainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			cardLayout.show(frame.getContentPane(),"second");
		}
	}
	
	class ErrorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			cardLayout.show(frame.getContentPane(),"first");
		}
	}
	
	
	public Integer[][] changeIntegers(int[][] sudoku) {
		Integer[][] number = new Integer[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				number[i][j] = sudoku[i][j];// print every number in a row
			}// switch to next line
		}
		return number;
	}
	
	public Integer[][] outPrintSudoku(Unit[][] sudoku) {
		Integer[][] number = new Integer[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				number[i][j] = sudoku[i][j].getNumber();// print every number in a row
			}// switch to next line
		}
		return number;
	}


}
