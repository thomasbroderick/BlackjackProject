package com.skilldistillery.blackjack;

import java.util.Scanner;

import com.skilldistillery.common.Card;
import com.skilldistillery.common.Deck;
import com.skilldistillery.common.Rank;

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
		// Show the player their cards
		System.out.println("Your cards are:");
		playerHand.showCards();
		int playerValue = playerHand.getHandValue();
		System.out.println("The value of your hand is: " + playerValue);
		// Show the dealer's up card
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
				// If they choose to stay, move to the dealer's turn
				if (hitOrStay.equals("S")) {
					break;
				}
				// If they choose to Hit, add a card to their hand, check for bust, and re-run
				// the loop
				else if (hitOrStay.equals("H")) {
					playerHand.addCard(deck.dealCard());
					playerHand.showCards();
					playerValue = playerHand.getHandValue();
					if (playerValue > 21) {
						if (!playerHand.softAce()) {
							System.out.println("The value of your hand is: " + playerValue);
							System.out.println("You have busted. You lose.");
							System.exit(0);
						} else {
							playerValue -= 10;
							System.out.println("The value of your hand is: " + playerValue);
						}
					} else {
						System.out.println("The value of your hand is: " + playerValue);
					}
				}

			} while (hitOrStay.equals("H"));
		}

		// Logic for dealer's hand
		System.out.print("The dealer's down card is: ");
		dealerHand.showSecondCard();
		System.out.println("The dealer's value is now: " + dealerValue);
		while (dealerValue < 17) {
			dealerHand.addCard(deck.dealCard());
			System.out.println("Dealer draws a card and now has: ");
			dealerHand.showCards();
			dealerValue = dealerHand.getHandValue();
			if (dealerValue > 21) {
				if (!dealerHand.softAce()) {
					System.out.println("The dealer's value is now: " + dealerValue);
					System.out.println("Dealer has busted. You win!");
					System.exit(0);
				} else {
					dealerValue -= 10;
					System.out.println("The dealer's value is now: " + dealerValue);
				}
			}
			System.out.println("The dealer's final value is: " + dealerValue);
			// Outputs based on had result
			if (dealerValue > playerValue) {
				System.out.println("You lose.");
			} else if (dealerValue == playerValue) {
				System.out.println("It's a push.");
			} else {
				System.out.println("You win.");
			}
			kb.close();
		}
	}
}
