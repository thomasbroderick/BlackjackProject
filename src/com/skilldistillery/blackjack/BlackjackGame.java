package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.common.Card;
import com.skilldistillery.common.Deck;
import com.skilldistillery.common.Hand;

public class BlackjackGame {
	BlackjackHand playerHand;
	BlackjackHand dealerHand;
	Deck deck;

	public BlackjackGame() {
		deck = new Deck();
		deck.shuffle();

	}

	public void play() {
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to Blackjack Simulator BS-9000.");
		// Deal a hand to the player
		playerHand = new BlackjackHand();
		playerHand.addCard(deck.dealCard());
		playerHand.addCard(deck.dealCard());
		// Deal a hand to the dealer
		dealerHand = new BlackjackHand();
		dealerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());
		System.out.println("Your cards are:");
		playerHand.showCards();
		int playerValue = playerHand.getHandValue();
		System.out.println("The value of your hand is: " + playerValue);
		System.out.println("The dealer is showing:");
		dealerHand.showOneCard();

		int dealerValue = dealerHand.getHandValue();
		// Check player and dealer hands for blackjack
		if (playerValue == 21 && dealerValue != 21) {
			System.out.println("BLACKJACK - YOU WIN!");
		} else if (playerValue == 21 && dealerValue == 21) {
			dealerHand.showSecondCard();
			System.out.println("DOUBLE BLACKJACK - THAT'S A PUSH. :(");
		} else {
			// Loop to run while player has not busted
			String hitOrStay;
				do {
					System.out.println("Would you like to Hit or Stay? Enter H or S");
					hitOrStay = kb.next();
					if (hitOrStay.equals("S")) {
						break;
					}

					else if (hitOrStay.equals("H")) {
						playerHand.addCard(deck.dealCard());
						playerHand.showCards();
						playerValue = playerHand.getHandValue(); 
						System.out.println("The value of your hand is: " + playerValue);
						if (playerValue > 21) {
							System.out.println("You have busted. You lose.");
							break;
						}
					}

				} while (hitOrStay.equals("H"));
			}

		
		dealerHand.showSecondCard();
		while (dealerValue < 17) {
			dealerHand.addCard(deck.dealCard());
			System.out.println("Dealer draws a card and now has: ");
			dealerHand.showCards();
			dealerValue = dealerHand.getHandValue();
			System.out.println("The dealer's value is now: " + dealerValue);
			if (dealerValue > 21) {
				System.out.println("Dealer has busted. You win!");
				break;
			}
		}

		if (dealerValue > playerValue) {
			System.out.println("You win.");
		} else if (dealerValue == playerValue) {
			System.out.println("It's a push.");
		} else {
			System.out.println("You lose.");
		}
	}

}
