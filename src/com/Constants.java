package com;
import java.util.HashMap;
import java.util.Map;

public final class Constants {

	public static final String projectPath = System.getProperty("user.dir")+"\\src\\";
	public static final String resourcePath = projectPath + "resource\\";
	public static final String gameLogPath = resourcePath+"gamelog.txt";	
	public static final String cardFaceBackPath = resourcePath+"faceBackCard.png";	
	public static final String fullDeckCardsPath = resourcePath+"fullDeckCards.png";	
	public static final String crownIconPath = resourcePath+"crownIcon.png";	
	
	public static final String HIGHER = "HIGHER";
	public static final String LOWER = "LOWER";
	public static final String SAME = "SAME";
	public static final String SUIT = "SUIT";
		
	public static final String[] suits = {"CLUB", "DIAMOND", " HEART","SPADE"};  

	public static final Map<String, Integer> fullDeckKeyValues = new HashMap<>() 
		{{
	     put("Two", 	2);
	     put("Three", 	3);
	     put("Four", 	4);
	     put("Five", 	5);
	     put("Six", 	6);
	     put("Seven",	7);
	     put("Eight", 	8);
	     put("Nine", 	9);
	     put("Ten", 	10);
	     put("Jack", 	11);
	     put("Queen", 	12);
	     put("King", 	13);
	     put("Ace", 	14);
	    }};
}
