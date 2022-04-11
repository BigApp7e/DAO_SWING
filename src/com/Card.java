package com;

public class Card {

	private String name;
	private int value;
	private String suit;
		
	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public String getSuit() {
		return suit;
	}

	public Card(String name, int value, String suit) {
		this.name = name;
		this.value = value;
		this.suit = suit;
	}
	
	public String toString() {
	   return this.name + "," + String.valueOf(this.value) + ","+this.suit;
	}

}
