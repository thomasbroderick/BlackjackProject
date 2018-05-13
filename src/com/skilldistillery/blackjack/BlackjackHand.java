package com.skilldistillery.blackjack;

import com.skilldistillery.common.Card;
import com.skilldistillery.common.Hand;

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

	

	
	
	
	
	
	
	

}
