package uk.ac.le.cs.CO3015.domain;

import java.util.Random;

public class SimulatedAnnealing {

	TravelCalculator tc = new TravelCalculator();
	Swap swap = new Swap();
	TravelCalculator tmp_tc = new TravelCalculator();
	SHCChecks algorithm = new SHCChecks();
	
	private int local = 0;
	private int overallCount = 0;
	private int countTour = 0;
	private int temperature = 50000000;
	Random rand = new Random();
	int finalResult = 0;


public int [] simulatedAnnealing(int [][] tspArray, int [] swapping) {
	 int [] tmp_swapping = new int[tspArray.length];
	 
	 while(tc.totalDistanceTravelled(tspArray, swapping)!=2085) {
		
		for(int i = 0; i < swapping.length; i++) {
			tmp_swapping[i]= swapping[i];
		}
		
		tmp_swapping=swap.swapIntegers(tmp_swapping);
		
		int change =
		tc.totalDistanceTravelled(tspArray, swapping) - tmp_tc.totalDistanceTravelled(tspArray, tmp_swapping);
			
		++countTour;
		int numb = (change*-1);
		double value = (double) numb/temperature;
		if(change>0) {
			for(int a=0; a<swapping.length;a++) {
				 swapping[a] = tmp_swapping[a];
			}
		}
		
		if(value<rand.nextDouble() && change<=0) {
			for(int a=0; a<swapping.length;a++) {
				 swapping[a] = tmp_swapping[a];
			}
		}
	    temperature--;
		overallCount++;
		if(tc.totalDistanceTravelled(tspArray,swapping) == 2085 || (overallCount  == 50000000)){
			for(int a=0; a<swapping.length;a++) {
				System.out.print(swapping[a] + " ");
			}
			System.out.println();
			System.out.println("Cost of tour " + countTour + ": " + tc.totalDistanceTravelled(tspArray, swapping));
			System.out.println("Goal state achieved");
			finalResult = tc.totalDistanceTravelled(tspArray, swapping);
			break;
		}
	 }
	 return swapping;
}

}
