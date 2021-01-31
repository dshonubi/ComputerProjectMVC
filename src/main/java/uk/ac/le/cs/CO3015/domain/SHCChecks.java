package uk.ac.le.cs.CO3015.domain;

import uk.ac.le.cs.CO3015.domain.TravelCalculator;

public class SHCChecks {

private int [] simpHC = {0,3,12,6,7,5,16,13,14,2,10,9,1,4,8,11,15}; //goal state
TravelCalculator tc = new TravelCalculator();

public boolean simpleHillClimbing(int[][]tspArray, int swapping[]) {
	//may need to make  another reader

		if(tc.totalDistanceTravelled(tspArray, swapping)==2085) {
			System.out.print("Success: ");
			for(int i=0; i<swapping.length;i++) {
				if(i==16) {
					System.out.println(swapping[i]);
				}else{
					System.out.print(swapping[i] + " ");
				}
			}
				return true;
			}else {
	return false;
}
		}
	
}
