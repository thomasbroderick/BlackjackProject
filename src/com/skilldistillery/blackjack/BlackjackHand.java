package com.skilldistillery.blackjack;

import com.skilldistillery.common.Card;
import com.skilldistillery.common.Hand;
import com.skilldistillery.common.Rank;

public class BlackjackHand extends Hand {
	private boolean busted = false;
	private boolean blackjack = false;
	
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

	public boolean isBusted() {
		return busted;
	}

	public void setBusted(boolean busted) {
		this.busted = busted;
	}

	public boolean isBlackjack() {
		return blackjack;
	}

	public void setBlackjack(boolean blackjack) {
		this.blackjack = blackjack;
	}

	
	
	
	
	
	
	

}
