package uk.ac.le.cs.CO3015.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import uk.ac.le.cs.CO3015.domain.*;
import uk.ac.le.cs.CO3015.service.AlgorithmsService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/** 
 * @author Dayo Shonubi
 * 
 * 
 * */

@RestController
public class ComputerProjectMvcController {
	
	private static Connection connection = null;
	private static String host = "127.0.0.1";
	private static int PORT = 3307;
	private static String database = "as1068";
	private static String username = "as1068";
	private static String password = "queXoa6a";
	
	InitialRoute initialRoute = new InitialRoute();
	SimpleHillClimbing shc = new SimpleHillClimbing();
	StepCountingHillClimbing schc = new StepCountingHillClimbing();
	SimulatedAnnealing sa = new SimulatedAnnealing();
	LateAcceptanceHillClimbing lahc = new LateAcceptanceHillClimbing();
	TravelCalculator tc = new TravelCalculator();
	static ComputerProjectMvcController cpmc = new ComputerProjectMvcController();
	
	@Autowired
	AlgorithmsService as;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
		public ModelAndView indexPage(){
			ModelAndView mv = new ModelAndView();
			mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value = "/optimise", method = RequestMethod.POST)
	public @ResponseBody String executeAlgorithms(@RequestParam MultipartFile file){
		
		ModelAndView mv = new ModelAndView();
		JsonArray ja = new JsonArray();
		
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		
		if(!file.isEmpty()) {
		try {
		BufferedReader bufferedReader = new BufferedReader(new StringReader(new String(file.getBytes()))); 
		
		//Reads the first three lines of the text file as they are always the same
		// the name of the file, any comments and the type of problem it is
		bufferedReader.readLine();
		bufferedReader.readLine();
	    bufferedReader.readLine();
	    
	    //get the dimensions for the array
	    //split string, isolate the dimension and convert it to an integer
	    String dimensionLine = bufferedReader.readLine();
	    String [] dimensionLineSplit = dimensionLine.split(" ");
	    int dimension = Integer.parseInt(dimensionLineSplit[1]);
	   
	    int [][] tspArray = new int[dimension][dimension];
	    
	    //reads the next two lines after the dimensions which are the same
		bufferedReader.readLine();
		bufferedReader.readLine();
		bufferedReader.readLine();
		
		String distances;
		int count = 0;
		int i=0;
     	int j=0;
     	
		while((distances = bufferedReader.readLine()) != null) {
			
			String [] distancesSplit = distances.split("\\s+");
		
	     		while(count<distancesSplit.length) {
	     			if (distancesSplit[count].equals("EOF")) {
	     				break;
	     			}else {
	     			int distance = Integer.parseInt(distancesSplit[count]);
	     				if(distance==0) {
	     					tspArray[i][j] = distance;
	     					++j;
	     					i=0;
	     				}else {
	     					tspArray[i][j] = distance;
	     		     		tspArray[j][i] = distance;
	     		     		 ++i;		 
	     		     	 }
	     			}
	     			count++;
	     			
	     		}
	     		count=0;
	}
    	
    //creates an array the length of the the dimension for swapping values  	
	int [] swapping = new int[tspArray.length];
	
	//fills array to the dimension size
	for(int a=0; a<tspArray.length;a++) {
		swapping[a] = a;
	}
	
	//generates the first route to be tried by an algorithm
	swapping = initialRoute.generateInitialRoute(swapping);
	
	Date date = new Date();
	Date timestamp1 = Date.from(date.toInstant());
	String timestamp = timestamp1.toLocaleString();
	
	int [] storage2 = new int [dimension];
	startTime = System.currentTimeMillis();
	storage2 = schc.stepCountingHillClimbing(tspArray, swapping, 500);
	
	//counterLimit is set by the user - lst value on the right, it currently updates every 500
	
	endTime = System.currentTimeMillis();
	System.out.println("That took " + (endTime - startTime)/1000 + " seconds");
	
	Algorithms al2 = new Algorithms();
	al2.setAlgorithm("StepCountingHillClimbing");
	al2.setTimeTaken((endTime - startTime)/1000);
	al2.setSolution(tc.totalDistanceTravelled(tspArray, storage2));
	al2.setProblemDimension(dimension);
	al2.setPathTaken(storage2);
	al2.setTimestamp(timestamp);
	as.save(al2);
	
	int [] storage3 = new int [dimension];
	startTime = System.currentTimeMillis();
	storage3 = sa.simulatedAnnealing(tspArray, swapping);
	
	
	endTime = System.currentTimeMillis();
	System.out.println("That took " + (endTime - startTime)/1000 + " seconds");
	
	Algorithms al3 = new Algorithms();
	al3.setAlgorithm("SimulatedAnnealing");
	al3.setTimeTaken((endTime - startTime)/1000);
	al3.setSolution(tc.totalDistanceTravelled(tspArray, storage3));
	al3.setProblemDimension(dimension);
	al3.setPathTaken(storage3);
	al3.setTimestamp(timestamp);
	as.save(al3);
	
	
	int [] storage4 = new int [dimension];
	startTime = System.currentTimeMillis();
	storage4 = lahc.lateAcceptanceHillClimbing(tspArray, swapping);
		
	endTime = System.currentTimeMillis();
	System.out.println("That took " + (endTime - startTime)/1000 + " seconds");	
	
	Algorithms al4 = new Algorithms();
	al4.setAlgorithm("LateAcceptanceHillClimbing");
	al4.setTimeTaken((endTime - startTime)/1000);
	al4.setSolution(tc.totalDistanceTravelled(tspArray, storage4));
	al4.setPathTaken(storage4);
	al4.setProblemDimension(dimension);
	al4.setTimestamp(timestamp);
	as.save(al4);
	
	int solution = cpmc.selectBestAlgorithm(dimension, timestamp);
	Optional<Algorithms> algo = as.findById(solution);
	Algorithms a = algo.get();
	int [] path = a.getPathTaken();
	
	for(int m = 0; m<dimension; m++) {
	int p = m+1;	
	JsonObject jo = new JsonObject();
	jo.addProperty("none","location"+p);
	jo.addProperty("location"+p,path[m]);
	ja.add(jo);
	}
	
	}catch(Exception e) {
		e.printStackTrace();
	}
		}else {
			System.out.println("false");
		}
		
		System.out.println(ja.toString());
		
	return ja.toString();
	
	}
	
	public static Connection connection() {

		if (connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String connect_string = "jdbc:mysql://" + host + ":" + PORT + "/" + database;
				connection = DriverManager.getConnection(connect_string, username, password);
				return connection;

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return connection;
		}
	}
	
	public int selectBestAlgorithm(int dimension,String timestamp) throws SQLException {

		Connection connection = connection();
		Statement statement = connection.createStatement();
		String sql = "SELECT Id, FinalSolution FROM SCHC WHERE ProblemDimension=" + dimension + " AND Timestamp=\'" + timestamp+"\'";
		
		ResultSet rs = statement.executeQuery(sql); 
		int [] aId = new int[3];
		int [] algorithms = new int[3];
		int i = 0;
		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("Id"));
			int solution = Integer.parseInt(rs.getString("FinalSolution"));
			
			aId[i] = id;
			algorithms[i] = solution;
			
			i++;
		}
		
		int lowestSol = 0; 
		for(int j = 0; j<4;j++) {
			if(j==3) {
				break;
			}else if(algorithms[j]<algorithms[j+1]){
				lowestSol = j;
			}else {
				lowestSol = j+1;
			}
		}
		
		return aId[lowestSol];
		
	}
}