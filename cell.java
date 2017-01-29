package sudoku;

import java.util.ArrayList;

public class cell {
	//Class to represent each square of the Sudoku puzzle
	
	ArrayList<Integer> domain;
	int value;
	
	public cell(int value){	
		this.value=value;
		this.domain=new ArrayList<Integer>();
	}	
	//Print associated value of a square
	public int printvalue(){
		
		return (this.value);
	}
	//remove everything from the domain
	public void cleardomain(){
		this.domain.removeAll(domain);
	}
	
	//add an item to the domain of a square
	public void setdomain(int place){
		this.value=place;
		this.domain.add(value);
	}
	
	//function that fills the domain of a square
	public void filldomain(){
		this.domain.add(1);
		this.domain.add(2);
		this.domain.add(3);
		this.domain.add(4);
		this.domain.add(5);
		this.domain.add(6);
		this.domain.add(7);
		this.domain.add(8);
		this.domain.add(9);
	}
}
	
	


