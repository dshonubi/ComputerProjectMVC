package uk.ac.le.cs.CO3015.domain;

import java.util.ArrayList;

/** 
 * @author Dayo Shonubi
 * 
 * 
 * */

public class LateAcceptanceHillClimbing {
	
	Swap swap = new Swap();
	TravelCalculator tc = new TravelCalculator();
	TravelCalculator tmp_tc = new TravelCalculator();
	Algorithms algorithm = new Algorithms();
	
	private boolean change = false;
	private int local = 0;
	private boolean tempValue;
	private int countTour = 0;
	int lahcArrayFull = 0;
	int finalResult = 0;
	 
public int [] lateAcceptanceHillClimbing(int [][] tspArray, int [] swapping) {
	int [] tmp_swapping = new int[tspArray.length];
	ArrayList<int[]> lateAcceptanceFitnessArray = new ArrayList<int[]>();;
	
	while(tc.totalDistanceTravelled(tspArray, swapping)!=2085) {
		
		swapping=swap.swapIntegers(swapping);
		
		++countTour;
		if (countTour > 1) {
			if(tmp_tc.totalDistanceTravelled(tspArray, tmp_swapping)<=tc.totalDistanceTravelled(tspArray, swapping)){
				for(int a=0; a<swapping.length;a++) {
					swapping[a] = tmp_swapping[a];
				}
			}
			else{
				change = true;
				if(lahcArrayFull != 10)
				lateAcceptanceFitnessArray.add(0,swapping);
			}
		}else {
			change = true;
			for(int a=0; a<swapping.length;a++) {
				tmp_swapping[a] = swapping[a] ;
			}
			lateAcceptanceFitnessArray.add(0,swapping);
		}
	if(lahcArrayFull == 10 && change == true) {
		change = false;
	 int comparator	= countTour % lateAcceptanceFitnessArray.size();
	 int [] candidate = lateAcceptanceFitnessArray.get(comparator);
	 
	  if(tc.totalDistanceTravelled(tspArray, swapping) <=tc.totalDistanceTravelled(tspArray, candidate)) {
		  for(int a=0; a<swapping.length;a++) {
				tmp_swapping[a] = swapping[a] ;
			}
		  lateAcceptanceFitnessArray.remove(9);
		  lateAcceptanceFitnessArray.add(0,swapping);  
		  ++countTour;
	  }
	}
		// pass new state to current state
	//	if(change == false){
			local++;
	//	}else{
	//		change = false;
	//		local = 0;	
	//	}
		if(local == 900000) {
			tempValue= true;
		}
		//checks that you've reached 1,000,000

		//checks that it's the actual lowest local value
	//	System.out.println(tmp_tc.totalDistanceTravelled(tspArray, tmp_swapping));
		if(tc.totalDistanceTravelled(tspArray,tmp_swapping) == 2085 || (tempValue == true)){
			for(int a=0; a<swapping.length;a++) {
				System.out.print(tmp_swapping[a] + " ");
			}
			System.out.println();
			System.out.println("Cost of tour " + countTour + ": " + tc.totalDistanceTravelled(tspArray, tmp_swapping));
			System.out.println("Goal state achieved");
			finalResult = tc.totalDistanceTravelled(tspArray, tmp_swapping);
			break;
		}
	}
	return tmp_swapping;
	}
}