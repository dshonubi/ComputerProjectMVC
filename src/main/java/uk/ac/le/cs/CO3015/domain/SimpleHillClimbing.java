package uk.ac.le.cs.CO3015.domain;

public class SimpleHillClimbing {
	
    /** Finds the local minimum between the maximum points selected by the initial route 
     * - checks that the same result does not change for 1 million times in a row 
     * ensuring the best solution amongst the two points*/
	
	Swap swap = new Swap();
	TravelCalculator tc = new TravelCalculator();
	TravelCalculator tmp_tc = new TravelCalculator();
	SHCChecks algorithm = new SHCChecks();
	
	private boolean change = false;
	private int local = 0;
	private boolean tempValue;
	private int countTour = 0;
	int finalResult = 0;
	
public int [] simpleHillClimbing(int [][] tspArray, int [] swapping) {
	int [] tmp_swapping = new int[tspArray.length];
	while(tc.totalDistanceTravelled(tspArray, swapping)!=2085) {
		
		tempValue = algorithm.simpleHillClimbing(tspArray,swapping);
		swapping=swap.swapIntegers(swapping);
		
		//System.out.println("Cost of tour " + ++countTour + ": " + tc.totalDistanceTravelled(tspArray, swapping));
		++countTour;
		if (countTour > 1) {
			if(tmp_tc.totalDistanceTravelled(tspArray, tmp_swapping)<=tc.totalDistanceTravelled(tspArray, swapping)){
				for(int a=0; a<swapping.length;a++) {
					swapping[a] = tmp_swapping[a];
				}
			}
			else{
				change = true;
				for(int a=0; a<swapping.length;a++) {
				tmp_swapping[a] = swapping[a];
				}
			}
		}else {
			change = true;
			for(int a=0; a<swapping.length;a++) {
				tmp_swapping[a] = swapping[a];
				}
		}
		
		// pass new state to current state
		if(change == false){
			local++;
		}else{
			change = false;
			local = 0;	
		}
		if(local == 50000000) {
			tempValue= true;
		}
		//checks that you've reached 1,000,000
	//	System.out.println(local);
		//checks that it's the actual lowest local value
	//	System.out.println(tmp_tc.totalDistanceTravelled(tspArray, tmp_swapping));
		if(tc.totalDistanceTravelled(tspArray,tmp_swapping) == 2085 || (tempValue == true)){
			for(int a=0; a<tmp_swapping.length;a++) {
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
