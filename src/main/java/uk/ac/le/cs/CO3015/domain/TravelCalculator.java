package uk.ac.le.cs.CO3015.domain;

public class TravelCalculator {

private int totalDistance = 0;

public int totalDistanceTravelled(int[][] array, int[] swapping) {
	totalDistance=0;
	int j=0;
	for(int i=0; i<array.length-1;i++) {
		totalDistance += array[swapping[i]][swapping[++j]];
	
			if(i == (array.length-2)) {
				totalDistance += array[swapping[j]][0];	
			}	
	}
	return totalDistance;
}
}
