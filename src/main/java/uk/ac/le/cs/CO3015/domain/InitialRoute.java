package uk.ac.le.cs.CO3015.domain;

import java.util.ArrayList;
import java.util.Collections;

public class InitialRoute {	
	
public int[] generateInitialRoute(int[] swapping) {
	
	//generates a temporary list for shuffling and mapping
	ArrayList<Integer> list = new ArrayList<Integer>();
	for(int i = 1; i<swapping.length; i++) {
		list.add(i);
	}
	
	//shuffles the list
	Collections.shuffle(list);
	
	//ensures the route begins at 0
	list.add(0,0);
	
	//maps the values to array that was passed in
	System.out.println("Initial Route: " + list);
	for(int i = 0; i<swapping.length; i++) {
		swapping[i] = list.get(i);
	}
	
	return swapping;
}

}
