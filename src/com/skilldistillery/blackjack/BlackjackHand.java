package com.skilldistillery.blackjack;

import com.skilldistillery.common.Card;
import com.skilldistillery.common.Hand;
import com.skilldistillery.common.Rank;

public class BlackjackHand extends Hand {

	public BlackjackHand() {
		
	}

	@Override
	public int getHandValue() {
		int value = 0;
		for (Card card : super.getCards()) {
			value += card.getValue();
		}
		return value;
	}

	public boolean softAce() {
		boolean b = false;
		for (Card card : super.getCards()) {
			if (card.getValue() == 11) {
				b = true;
			}
		}
		return b;
		
	}

	
	
	
	
	
	
	

}
