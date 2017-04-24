//PRATHAM DESAI

import java.util.ArrayList;

public class SudokuSolve {


	static long start = System.currentTimeMillis();
	static int combinations  = 0;

	public static void main(String[] args) {





		int[][] sudoku = {  { 5, 0, 0, 9, 0, 0, 0, 0, 2 },
							{ 1, 0, 0, 0, 0, 0, 0, 0, 9 },
							{ 0, 8, 0, 0, 5, 0, 1, 0, 0 },
							{ 0, 3, 5, 1, 0, 0, 9, 0, 0 },
							{ 0, 1, 0, 0, 0, 0, 0, 7, 0 },
							{ 0, 0, 6, 0, 0, 5, 8, 3, 0 },
							{ 0, 0, 1, 0, 7, 0, 0, 5, 0 },
							{ 2, 0, 0, 0, 0, 0, 0, 0, 8 },
							{ 8, 0, 0, 0, 0, 6, 0, 0, 3 }  };

		int[][] sudokuBlank = new int[9][9];









		try{

		displaySudoku(sudokuBlank);
		solveSudoku(sudokuBlank,0,0);

		}catch(Exception E){
			System.out.println("SUDOKU HAS BEEN SOLVED");
		}



	}
	public static void displaySudoku(int[][] sudoku) {
		try{
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[i].length; j++) {

				System.out.print(sudoku[i][j]);
			}
			System.out.println();
		}
		System.out.println("\n\n");
		//Thread.sleep(500);
		}catch(Exception e){

		}
	}

	public static boolean isSudokuEmpty(int[][] sudoku) {
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[i].length; j++) {
				if (sudoku[i][j] == 0) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkRow(int[][] sudoku, int row, int num) {

		for (int column = 0; column < 9; column++) {

			if (num == sudoku[row][column]) {
				return false;
			}

		}

		return true;
	}

	public static boolean checkColumn(int[][] sudoku, int column, int num) {

		for (int row = 0; row < 9; row++) {

			if (num == sudoku[row][column]) {
				return false;
			}

		}

		return true;

	}

	public static int whichMiniGridRow(int row) {

		row = row / 3;

		for (int i = 0; i < 3; i++) {
			if (row == i) {
				return row + 1;
			}

		}

		return 0;
	}

	public static int whichMiniGridColumn(int column) {

		column = column / 3;

		for (int j = 0; j < 3; j++) {

			if (column == j) {
				return column + 1;
			}

		}

		return 0;

	}

	public static boolean checkMiniGrid(int[][] sudoku, int row, int column,int numRow, int numCol, int num) {

		for (int i = 3 * row - 3; i <= 3 * row - 1; i++) {
			for (int j = 3 * column - 3; j <= 3 * column - 1; j++) {

				if (i != numRow && j != numCol) {
					if (num == sudoku[i][j]) {
						return false;

					}
				}
			}
		}
		return true;
	}

// recursion
	public static void solveSudoku(int[][] sudoku, int row, int column) {

		if (isSudokuEmpty(sudoku) == false) {

			System.out.println((System.currentTimeMillis() - start)/1000.0 + " seconds");
			//System.out.println("Number of combinations is: "+ combinations);

			displaySudoku(sudoku);
		}

		if (sudoku[row][column] != 0) {

			if (column < 8) {

				solveSudoku(sudoku, row, column + 1);

			}else {
				solveSudoku(sudoku, row + 1, 0);
			}

		}else {

			for (int num = 1; num < 10; num++) {

				if (checkRow(sudoku, row, num)  && checkColumn(sudoku, column, num)
						&& checkMiniGrid(sudoku, whichMiniGridRow(row),whichMiniGridColumn(column), row, column, num) ) {

					sudoku[row][column] = num;
					combinations ++;
					displaySudoku(sudoku);
					if (column < 8) {

						solveSudoku(sudoku, row, column + 1);

					}else {
						solveSudoku(sudoku, row + 1, 0);
					}

				}
			}

			sudoku[row][column] = 0;
			//combinations++;
			//displaySudoku(sudoku);

		}



	}




}
