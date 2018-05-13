package com.skilldistillery.common;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	List<Card> cards = new ArrayList<>();
	
	public Hand() {
	}
	
	/*public int getHandValue() {
		int value = 0;
		for (Card card : cards) {
			value += card.getValue();
		}
		return value;
	}*/
	
	public abstract int getHandValue();
	
	public void addCard(Card c) {
		cards.add(c);
	}
	
	public void clearHand() {
		cards.clear();
	}
	
	public void showCards() {
		for (Card card : cards) {
			System.out.println(card.toString());
		}
	}
	public void showOneCard() {
		System.out.println(cards.get(0));
	}
	public void showSecondCard() {
		System.out.println(cards.get(1));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hand []");
		return builder.toString();
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	
	
	
	
	
}
