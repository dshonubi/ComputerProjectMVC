package uk.ac.le.cs.CO3015.domain;

public class StepCountingHillClimbing {

	TravelCalculator tc = new TravelCalculator();
	Swap swap = new Swap();
	TravelCalculator tmp_tc = new TravelCalculator();
	SHCChecks algorithm = new SHCChecks();
	
	private int local = 0;
	private int overallCount = 0;
	private int countTour = 0;
	private int costBound = 0;
	int finalResult = 0;
	
	// cost limit set by a user
 public int [] stepCountingHillClimbing(int [][] tspArray, int [] swapping,int costLimit) {
	 
	 costBound = tc.totalDistanceTravelled(tspArray, swapping);
	 int [] tmp_swapping = new int[tspArray.length];
	 
	 while(tc.totalDistanceTravelled(tspArray, swapping)!=2085) {
		
		swapping=swap.swapIntegers(swapping);
		
		//System.out.println("Cost of tour " + ++countTour + ": " + tc.totalDistanceTravelled(tspArray, swapping));
		++countTour;
		++local;
		
		if(countTour==1) {
			for(int a=0; a<swapping.length;a++) {
				tmp_swapping[a] = swapping[a];
			}
		}else {
		
		if((local > costLimit) &&(tc.totalDistanceTravelled(tspArray, swapping)<costBound)) {
			local = 0;
			costBound = tc.totalDistanceTravelled(tspArray,swapping);
			for(int a=0; a<swapping.length;a++) {
				tmp_swapping[a] = swapping[a];
			}
			++local;
		//	System.out.println("New cost bound Value: " + costBound);
		}
		
		if((local > costLimit) &&(costBound<=tc.totalDistanceTravelled(tspArray, swapping))) {
			local = 0;
		//	System.out.println("CostBound Unchanged: " + costBound);
		}
		}
		//exception rule - accept the candidates with the cost equal to costBound when the current cost is also equal to costBound
		if(costBound == tc.totalDistanceTravelled(tspArray, swapping)){
			for(int a=0; a<swapping.length;a++) {
				tmp_swapping[a] = swapping[a];
			}
		}
		
	//	System.out.println(local);
		//checks that it's the actual lowest local value
	//	System.out.println(tmp_tc.totalDistanceTravelled(tspArray, tmp_swapping));
		
		//stops at 2,000,000 or 2085 -shortest distance
		overallCount++;
		if(tmp_tc.totalDistanceTravelled(tspArray,tmp_swapping) == 2085 || (overallCount  == 50000000)){
			for(int a=0; a<tmp_swapping.length;a++) {
				System.out.print(tmp_swapping[a] + " ");
			}
			System.out.println();
			System.out.println("Cost of tour " + countTour + ": " + costBound);
			System.out.println("Goal state achieved");
			finalResult = costBound;
			break;
		}
	 }
	 return tmp_swapping;
 }

}
