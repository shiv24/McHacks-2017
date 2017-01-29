package sudoku;

import java.util.ArrayList;

public class driver {

	public static void main(String[] args) {
		puzzle testpuzzle= new puzzle();
		testpuzzle.fill_puzzle();
		testpuzzle.printpuzzle();
		testpuzzle.fix_initial_domain();
		testpuzzle.fill_constraints();
		testpuzzle.ac3();
		
		System.out.println("--------");
		testpuzzle.printpuzzle();
		}
	}	
	

