package uk.ac.le.cs.CO3015.domain;

/**
 * @author Dayo Shonubi
 */

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//the tags here are used to determine how the variable headers will be initialised in the database

@Entity
@Table(name="SCHC")
public class Algorithms {
	
	@Id
	@GeneratedValue
	@Column(name="Id")
	int id;
	
	@Column(name="Algorithm")
	String algorithm;

	@Column(name="TimeTakenInSeconds")
	long timeTaken;
	
	@Column(name="FinalSolution")
	int solution;
	
	@Column(name="PathTaken")
	int [] path;
	
	@Column(name="ProblemDimension")
	int dimension;
	
	@Column(name="Timestamp")
	String timestamp;
	
	//getters and setters for each of the variables above
	
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public String getAlgorithm() {
		return algorithm;
	}

	public void setTimeTaken(long timeTaken) {
		this.timeTaken = timeTaken;
	}
	
	public long getTimeTaken() {
		return timeTaken;
	}
	
	public void setSolution(int solution) {
		this.solution = solution;
	}
	
	public int getSolution() {
		return solution;
	}
	
	public void setPathTaken(int[] path) {
		this.path = path;
	}
	
	public int [] getPathTaken() {
		return path;
	}
	
	public void setProblemDimension(int dimension) {
		this.dimension = dimension;
	}
	
	public int getProblemDimension() {
		return dimension;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
}