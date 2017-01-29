package sudoku;
import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;




public class puzzle {
	
		
		public cell[][] grid=new cell[9][9];
		ArrayList<String> constraint=new ArrayList<String>();
		ArrayList<Integer> length= new ArrayList<Integer>();	 
		
		public puzzle(){
		
		}
		
		
		public void print_constraints(){
			//prints out the queue of constraints
			
			for (String b: constraint){
					System.out.println(b);
			}
		}
		
		
		public boolean revised(cell a, cell b){
			//Revised function for AC-3
			
			boolean revised= false;
			if (a.domain.size()==0 || b.domain.size()==0){
				return revised;
			}
			if ( b.domain.size()==1){
				int item= b.domain.get(0);
				Iterator<Integer> iter = a.domain.iterator();
				while (iter.hasNext()) {
				    Integer holder = iter.next();

				    if (item==holder){
				        iter.remove();
				        revised=true;
					}
			}
			
			}
			return revised;
		}
		
		public void output_file(){
			//Outputs the Sudoku puzzle that was input
			//in the text file by the user
			
			try{
			    PrintWriter writer = new PrintWriter("lenght_file.txt", "UTF-8");
			    for(Integer number: length){
			    	writer.println(number);	
			    }
			    
			    writer.close();
			} catch (Exception e) {
			   // do something
			}
			
			
		}
		
		public boolean isComplete(){
			//Checks if the Sudoku puzzle is solved
			for (int x=0; x<9; x++){
				for (int y=0; y<9; y++){
					if (grid[x][y].value==0){
						return false;
					}
				}
			}
			return true;
		}
		
		
		public boolean BackTracking(){
			//Recursive Backtracking Algorithm that is implemented
			//after AC-3 is completed 
			int i=0;
			int j=0;
			
			if(this.isComplete()){
				return true;
			}		
			for(int a=0; a<9; a++){
				for(int b=0; b<9; b++){
					if(this.grid[a][b].value==0){
						i=a;
						j=b;
						break;
					}
					
				}
			}
			
			for (int item: this.grid[i][j].domain){
				this.grid[i][j].value=item;
				BackTracking();
			}
			grid[i][j].value=0;
			if(this.isComplete()==false){
				return false;
			}
			return false;
		}
		
				
		
		public boolean ac3(){
			//AC-3 algorithm that can solve simple sudoku puzzles completely,
			//and can reduce domain for harder puzzles so that backtracking
			// can complete the job.
			
			while(constraint.size()!=0){
			
				length.add(constraint.size());
				
				String binary= constraint.remove(0);
				char[] cArray = binary.toCharArray();
				
			int i=Character.getNumericValue(cArray[5]);
			int j=Character.getNumericValue(cArray[8]);
			int k=Character.getNumericValue(cArray[19]);
			int l=Character.getNumericValue(cArray[22]);
			
			cell cell1= grid[i][j];
			cell cell2= grid[k][l];
			
			if(revised(cell1, cell2)){
				if (cell1.domain.size()==0){
					return false;
				}
				if (cell1.domain.size()==1){
					int temp_val= cell1.domain.get(0);
					grid[i][j].value=temp_val;
				}
				
					for(int x=0; x<i; x++){
						if (x!=k || j!=l){
			    		   String con= ("grid"+"["+x+"]"+"["+j+"] != grid"+"["+i+"]"+"["+j+"]");
			    		   this.constraint.add(con);
						}
		    	   }
		    	   
		    	 //comparing within row after index
		    	   for(int x=i+1; x<9; x++){
		    		   if (x!=k || j!=l){
		    		   String con= ("grid"+"["+x+"]"+"["+j+"] != grid"+"["+i+"]"+"["+j+"]");
		    		   this.constraint.add(con);
		    		   }
		    	   }
		    	 
		    	   //Comparing within column before index
		    	   for(int x=0; x<j; x++){
		    		   if (i!=k || j!=l){
		    		   String con= ("grid"+"["+i+"]"+"["+x+"] != grid"+"["+i+"]"+"["+j+"]");
		    		   this.constraint.add(con);
		    		   }
		    	   }
		    	   
		    	   
		    	   //comparing within the same column after index
		    	   for(int x=j+1; x<9; x++){
		    		   if (i!=k || j!=l){
		    		   String con= ("grid"+"["+i+"]"+"["+x+"] != grid"+"["+i+"]"+"["+j+"]");
		    		   this.constraint.add(con);
		    		   }
		    	   }
		    	   
		    	   if (i==1||i==4||i==7){
		    	   		if(j==0||j==3||j==6){
		    	   			String con1= ("grid"+"["+(i-1)+"]"+"["+(j+1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con2= ("grid"+"["+(i-1)+"]"+"["+(j+2)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con3= ("grid"+"["+(i+1)+"]"+"["+(j+1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con4= ("grid"+"["+(i+1)+"]"+"["+(j+2)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			this.constraint.add(con1);
			    			this.constraint.add(con2);
			    			this.constraint.add(con3);
			    			this.constraint.add(con4);	
		    	   		}
		    	   		if(j==1||j==4||j==7){
		    	   			String con1= ("grid"+"["+(i-1)+"]"+"["+(j-1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con2= ("grid"+"["+(i+1)+"]"+"["+(j-1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con3= ("grid"+"["+(i-1)+"]"+"["+(j+1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con4= ("grid"+"["+(i+1)+"]"+"["+(j+1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			this.constraint.add(con1);
			    			this.constraint.add(con2);
			    			this.constraint.add(con3);
			    			this.constraint.add(con4);
		    	   		}
		    	   		if(j==2||j==5||j==8){
		    	   			String con1= ("grid"+"["+(i-1)+"]"+"["+(j-1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con2= ("grid"+"["+(i-1)+"]"+"["+(j-2)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con3= ("grid"+"["+(i+1)+"]"+"["+(j-1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con4= ("grid"+"["+(i+1)+"]"+"["+(j-2)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			this.constraint.add(con1);
			    			this.constraint.add(con2);
			    			this.constraint.add(con3);
			    			this.constraint.add(con4);
		    	   		}
		    	   		
		    	   	}
		    	   	
		    	   	if(i==2||i==5||i==8){
		    	   		if(j==0||j==3||j==6){
		    	   			String con1= ("grid"+"["+(i-1)+"]"+"["+(j+1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con2= ("grid"+"["+(i-1)+"]"+"["+(j+2)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con3= ("grid"+"["+(i-2)+"]"+"["+(j+1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con4= ("grid"+"["+(i-2)+"]"+"["+(j+2)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			this.constraint.add(con1);
			    			this.constraint.add(con2);
			    			this.constraint.add(con3);
			    			this.constraint.add(con4);
		    	   			
		    	   		}
		    	   		
		    	   		if(j==1||j==4||j==7){
		    	   			String con1= ("grid"+"["+(i-1)+"]"+"["+(j-1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con2= ("grid"+"["+(i-2)+"]"+"["+(j-1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con3= ("grid"+"["+(i-1)+"]"+"["+(j+1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con4= ("grid"+"["+(i-2)+"]"+"["+(j+1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			this.constraint.add(con1);
			    			this.constraint.add(con2);
			    			this.constraint.add(con3);
			    			this.constraint.add(con4);
		    	   			
		    	   		}
		    	   		
		    	   		if(j==2||j==5||j==8){
		    	   			String con1= ("grid"+"["+(i-1)+"]"+"["+(j-1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con2= ("grid"+"["+(i-2)+"]"+"["+(j-1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con3= ("grid"+"["+(i-1)+"]"+"["+(j-2)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			String con4= ("grid"+"["+(i-2)+"]"+"["+(j-2)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			this.constraint.add(con1);
			    			this.constraint.add(con2);
			    			this.constraint.add(con3);
			    			this.constraint.add(con4);
		    	   			
		    	   		}
		    	   	}
		    	   
		    	   //constraint for within box
		    	  if (i%3==0 || i==0){
		    		  if(j==0||j==3||j==6){
		    		  for (int x=i+1;x<i+3; x++){
		    			  String con1= ("grid"+"["+x+"]"+"["+(j+1)+"] != grid"+"["+i+"]"+"["+j+"]");
		    			  String con2= ("grid"+"["+x+"]"+"["+(j+2)+"] != grid"+"["+i+"]"+"["+j+"]");
		    			  this.constraint.add(con1);
		    			  this.constraint.add(con2);
		    		  }
		    		  }
		    		  if(j==1||j==4||j==7){
		    			  for (int x=i+1;x<i+3; x++){
			    			  String con1= ("grid"+"["+x+"]"+"["+(j-1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			  String con2= ("grid"+"["+x+"]"+"["+(j+1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			  this.constraint.add(con1);
			    			  this.constraint.add(con2);  
		    			  
		    		  }
		    		  }
		    		  if(j==2||j==5||j==8){
		    			  for (int x=i+1;x<i+3; x++){
			    			  String con1= ("grid"+"["+x+"]"+"["+(j-1)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			  String con2= ("grid"+"["+x+"]"+"["+(j-2)+"] != grid"+"["+i+"]"+"["+j+"]");
			    			  this.constraint.add(con1);
			    			  this.constraint.add(con2);
		    			  }
		    		  	}		  
		    		  }
				}
			}
			return true;
		}
		
		
		
		public void print_domain(){
			//Print the domain of each square in puzzle
			for(int i = 0; i < 9; i++)
			{
				for(int j = 0; j < 9; j++)
				{
				for(int w=0;w<grid[i][j].domain.size();w++){
				    System.out.print(grid[i][j].domain.get(w));   
				} 
				System.out.println();
				}
			}
		}
		
		
		public void get_result(){
			int domain_count=0;
			for(int i = 0; i < 9; i++)
			{
				for(int j = 0; j < 9; j++)
				{
					if (grid[i][j].domain.size()==1){
						domain_count++;
					}
				}
			}
			if (domain_count==81){
				System.out.println("There is a solution to the puzzle that was found");
			}
			else{
				System.out.println("The domains are arc-consistent");
			}
		}
		
		
		
		
		public void fix_initial_domain(){
			//setting the initial domain of squares in the grid
			//Setting the domain of unknown squares from 1..9 and 
			//assigning a value to squares for which the value is given
			for(int i = 0; i < 9; i++)
				{
				for(int j = 0; j < 9; j++)
					{
					if (grid[i][j].value!=0){
						grid[i][j].cleardomain();
						grid[i][j].setdomain(grid[i][j].value);
					}
					else{
						grid[i][j].filldomain();
						}
					}
		
			}
			
		}
		

		 
		public void fill_puzzle(){
			//Fill the Sudoku grid with the values from the Text file 
	    try {

	    	File file = new File("input.txt");
	        Scanner sc = new Scanner(file);
	        int count=0;
	        while (sc.hasNextLine()) {
	            String myline = sc.nextLine();	 
	            
	            
	            for (int i=0; i< myline.length() ; i++){
	            	char c= myline.charAt(i);
	            	int num= Character.getNumericValue(c);
	            	
	            	
	            	grid[count][i]= new cell(num);
	            }
	            
	            count++;
	        
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	   
	 
	}
		
	
	public void printpuzzle(){
			for(int i = 0; i < 9; i++)
			{
			for(int j = 0; j < 9; j++)
			{
			System.out.print(grid[i][j].printvalue());
			}
			System.out.println();
			}
		}	
		
	
	public void fill_constraints() {
		//Fill the constraint queue with all the 
		//possible constraints for the sudoku puzzle
		
		for(int i = 0; i < 9; i++)
	    {
	       for(int j = 0; j < 9; j++){
	    	   
	    	   //comparing within column before index
	    	    
	    	   for(int x=0; x<i; x++){
	    		   String con= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+x+"]"+"["+j+"]");
	    		   this.constraint.add(con);
	    	   }
	    	   
	    	 //comparing within column after index
	    	   for(int x=i+1; x<9; x++){
	    		   String con= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+x+"]"+"["+j+"]");
	    		   this.constraint.add(con);
	    	   }
	    	 
	    	   //Comparing within row before index
	    	   for(int x=0; x<j; x++){
	    		   String con= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+i+"]"+"["+x+"]");
	    		   this.constraint.add(con);
	    	   }
	    	   
	    	   
	    	   //comparing within the same row after index
	    	   for(int x=j+1; x<9; x++){
	    		   String con= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+i+"]"+"["+x+"]");
	    		   this.constraint.add(con);
	    	   }
	    	   
	    	   //constraint for within box
	    	   	if (i==1||i==4||i==7){
	    	   		if(j==0||j==3||j==6){
	    	   			String con1= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j+1)+"]");
		    			String con2= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j+2)+"]");
		    			String con3= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i+1)+"]"+"["+(j+1)+"]");
		    			String con4= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i+1)+"]"+"["+(j+2)+"]");
		    			this.constraint.add(con1);
		    			this.constraint.add(con2);
		    			this.constraint.add(con3);
		    			this.constraint.add(con4);	
	    	   		
	    	   			
	    	   		}
	    	   		if(j==1||j==4||j==7){
	    	   			String con1= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j-1)+"]");
		    			String con2= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i+1)+"]"+"["+(j-1)+"]");
		    			String con3= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j+1)+"]");
		    			String con4= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i+1)+"]"+"["+(j+1)+"]");
		    			this.constraint.add(con1);
		    			this.constraint.add(con2);
		    			this.constraint.add(con3);
		    			this.constraint.add(con4);
	    	   		}
	    	   		if(j==2||j==5||j==8){
	    	   			String con1= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j-1)+"]");
		    			String con2= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j-2)+"]");
		    			String con3= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i+1)+"]"+"["+(j-1)+"]");
		    			String con4= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i+1)+"]"+"["+(j-2)+"]");
		    			this.constraint.add(con1);
		    			this.constraint.add(con2);
		    			this.constraint.add(con3);
		    			this.constraint.add(con4);
	    	   		}
	    	   		
	    	   	}
	    	   	
	    	   	if(i==2||i==5||i==8){
	    	   		if(j==0||j==3||j==6){
	    	   			String con1= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j+1)+"]");
		    			String con2= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j+2)+"]");
		    			String con3= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-2)+"]"+"["+(j+1)+"]");
		    			String con4= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-2)+"]"+"["+(j+2)+"]");
		    			this.constraint.add(con1);
		    			this.constraint.add(con2);
		    			this.constraint.add(con3);
		    			this.constraint.add(con4);
	    	   			
	    	   		}
	    	   		
	    	   		if(j==1||j==4||j==7){
	    	   			String con1= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j-1)+"]");
		    			String con2= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-2)+"]"+"["+(j-1)+"]");
		    			String con3= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j+1)+"]");
		    			String con4= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-2)+"]"+"["+(j+1)+"]");
		    			this.constraint.add(con1);
		    			this.constraint.add(con2);
		    			this.constraint.add(con3);
		    			this.constraint.add(con4);
	    	   			
	    	   		}
	    	   		
	    	   		if(j==2||j==5||j==8){
	    	   			String con1= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j-1)+"]");
		    			String con2= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-2)+"]"+"["+(j-1)+"]");
		    			String con3= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-1)+"]"+"["+(j-2)+"]");
		    			String con4= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+(i-2)+"]"+"["+(j-2)+"]");
		    			this.constraint.add(con1);
		    			this.constraint.add(con2);
		    			this.constraint.add(con3);
		    			this.constraint.add(con4);
	    	   			
	    	   		}
	    	   	}
	    	   
	    	   
	    	   	
	    	   	
	    	   	
	    	   	if (i==0||i==3||i==6){
	    		  if(j==0||j==3||j==6){
	    		  for (int x=i+1;x<i+3; x++){
	    			  String con1= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+x+"]"+"["+(j+1)+"]");
	    			  String con2= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+x+"]"+"["+(j+2)+"]");
	    			  this.constraint.add(con1);
	    			  this.constraint.add(con2);
	    		  }
	    		  }
	    		  if(j==1||j==4||j==7){
	    			  for (int x=i+1;x<i+3; x++){
		    			  String con1= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+x+"]"+"["+(j-1)+"]");
		    			  String con2= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+x+"]"+"["+(j+1)+"]");
		    			  this.constraint.add(con1);
		    			  this.constraint.add(con2);  
	    			  	}
	    		  	}
	    		  if(j==2||j==5||j==8){
	    			  for (int x=i+1;x<i+3; x++){
		    			  String con1= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+x+"]"+"["+(j-1)+"]");
		    			  String con2= ("grid"+"["+i+"]"+"["+j+"] != grid"+"["+x+"]"+"["+(j-2)+"]");
		    			  this.constraint.add(con1);
		    			  this.constraint.add(con2);
	    			  	}		  
	    		  	}
	    	   		}
	    	   
	    	  	}
	       		}
	    }
	}

	  
	    		
			
	
		
		 
		


	
	

	