package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.common.Deck;

public class BlackjackGame {
	BlackjackHand playerHand;
	BlackjackHand dealerHand;
	int playerValue;
	int dealerValue;
	Deck deck;
	int numPlayers;
	int playerPosition;
	List<BlackjackHand> players = null;
	List<BlackjackHand> playersHands;

	Scanner kb = new Scanner(System.in);

	public BlackjackGame() {
		deck = new Deck();
		deck.shuffle();

	}

	public void play() {
		introAndPlayerNumSelection();
		cardsToDealer();
		cardsToPlayers(numPlayers, dealerHand);
		dealerAlgorithm(dealerHand);
		reportOutcomes(playersHands, dealerHand);
	}
	
	public int introAndPlayerNumSelection() {

		System.out.println("Welcome to Blackjack Simulator BS-9000.");
		System.out.println("How many players will there be: (1-6)?");
		try {
			numPlayers = kb.nextInt();
			if (numPlayers < 1 || numPlayers > 6) {
				throw new InputMismatchException();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("That is not a valid number of players");
			System.exit(0);
		}
		return numPlayers;
	}

	public BlackjackHand cardsToDealer() {
		dealerHand = new BlackjackHand();
		dealerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());
		return dealerHand;
	}

	public List<BlackjackHand> cardsToPlayers(int numOfPlayers, BlackjackHand b) {
		playersHands = new ArrayList<>();

		for (int i = 0; i < numOfPlayers; i++) {
			playerPosition = i+1;
			playerHand = new BlackjackHand();
			playerHand.addCard(deck.dealCard());
			playerHand.addCard(deck.dealCard());
			playersHands.add(playerHand);
			// Show the player their cards
			System.out.println("Player " + (playerPosition) + " your cards are:");
			playerHand.showCards();
			int playerValue = playerHand.getHandValue();
			if (playerValue == 22) {
				playerValue = 12;
			}
			System.out.println("The value of your hand is: " + playerValue);

			System.out.println("The dealer is showing:");
			b.showOneCard();

			int dealerValue = b.getHandValue();
			blackjackCheck(playerHand, dealerHand);
			if (!playerHand.isBlackjack()) {
			playerHitOrStand(playerHand);
			}
		}
		return playersHands;
	}

	public void blackjackCheck(BlackjackHand a, BlackjackHand b) {

		if (a.getHandValue() == 21 && b.getHandValue() != 21) {
			a.setBlackjack(true);
			System.out.println("BLACKJACK - YOU WIN PLAYER " + playerPosition + " !");
		} else if (a.getHandValue() == 21 && b.getHandValue() == 21) {
			dealerHand.showSecondCard();
			a.setBlackjack(true);
			b.setBlackjack(true);
			System.out.println("DOUBLE BLACKJACK - THAT'S A PUSH PLAYER " + playerPosition + " . :(");
		} else if (b.getHandValue() == 21) {
			System.out.println("Dealer has blackjack.  Player hands lose.");
			System.exit(0);
		}
	}

	public BlackjackHand playerHitOrStand(BlackjackHand b) {
		String hitOrStay;

		do {
			System.out.println("Player " + playerPosition + ", would you like to Hit or Stay? Enter H or S");
			hitOrStay = kb.next();
			// If they choose to stay, move to the dealer's turn
			if (hitOrStay.equals("S")) {
				break;
			}
			// If they choose to Hit, add a card to their hand, check for bust, and re-run
			// the loop
			else if (hitOrStay.equals("H")) {
				b.addCard(deck.dealCard());
				b.showCards();
				playerValue = b.getHandValue();
				if (playerValue > 21) {
					if (!b.softAce()) {
						b.setBusted(true);
						System.out.println("The value of your hand is: " + playerValue);
						System.out.println("You have busted. You lose player " + playerPosition + ".");
						break;
					} else {
						playerValue -= 10;
						System.out.println("The value of your hand is: " + playerValue);
						if (playerValue > 21) {
							System.out.println("You have busted. You lose player " + playerPosition + ".");
							b.setBusted(true);
							break;
						}
					}
				} 
					System.out.println("The value of your hand is: " + playerValue);
				
			}
			

		} while (hitOrStay.equals("H"));
		return b;
	}

	public BlackjackHand dealerAlgorithm(BlackjackHand b) {
		System.out.print("The dealer's down card is: ");
		b.showSecondCard();
		dealerValue = b.getHandValue();
		System.out.println("The dealer's value is now: " + dealerValue);
		while (dealerValue < 17) {
			b.addCard(deck.dealCard());
			System.out.println("Dealer draws a card and now has: ");
			b.showCards();
			dealerValue = b.getHandValue();
			if (dealerValue > 21) {
				if (!b.softAce()) {
					b.setBusted(true);
					System.out.println("The dealer's value is now: " + dealerValue);

				} else {
					dealerValue -= 10;
					System.out.println("The dealer's value is now: " + dealerValue);
					if (dealerValue > 21) {
						b.setBusted(true);
						break;
					}
				}
			}
			System.out.println("The dealer's final value is: " + dealerValue);

		}
		return b;
	}

	public void reportOutcomes(List<BlackjackHand> lbh, BlackjackHand b) {
		for (int i = 0; i < lbh.size(); i++) {
			playerPosition = i + 1;
			if (!playerHand.isBlackjack() && !playerHand.isBusted()) {
				if (b.isBusted()) {
					System.out.println("Dealer has busted. You win player " + playerPosition + "!");
				} else if (dealerValue > playerValue) {
					System.out.println("You lose player " + playerPosition + ".");
				} else if (dealerValue == playerValue) {
					System.out.println("It's a push player " + playerPosition + ".");
				} else if (!playerHand.isBusted()){
					System.out.println("You win player " + playerPosition + ".");
				}
			}
		}
	}
}
