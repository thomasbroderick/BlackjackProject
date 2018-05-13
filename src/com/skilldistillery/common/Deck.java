package com.skilldistillery.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	List<Card> cards = new ArrayList<>();

	public Deck() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card c = new Card(suit, rank);
				cards.add(c);
			}
		}
	}

	public int checkDeckSize() {
		return cards.size();
	}
	
	public Card dealCard() {
		Card c = cards.remove(0);
		return c;
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
}
