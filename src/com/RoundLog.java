package com;

public class RoundLog {
	
	Card firstCard;
	Card secondCard;
	String bet;
	boolean isWin;
	
	public Card getFirstCard() {
		return firstCard;
	}

	public Card getSecondCard() {
		return secondCard;
	}

	public String getBet() {
		return bet;
	}

	public boolean getIsWin() {
		return isWin;
	}

	public RoundLog(Card firstCard, Card secondCard, String bet, boolean isWin) {
		this.firstCard = firstCard;
		this.secondCard = secondCard;
		this.bet = bet;
		this.isWin = isWin;
	}

	@Override
	public String toString() {
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("firstCard "+firstCard.getName()+" suit "+firstCard.getSuit());
	    sb.append(" secondCard "+secondCard.getName()+" suit "+secondCard.getSuit());
	    sb.append(" bet "+bet);
	    sb.append(" is win "+isWin);
	    return sb.toString();
	}
	
}
