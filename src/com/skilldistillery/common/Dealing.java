package com.skilldistillery.common;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Dealing {

	public static void main(String[] args) {
		Dealing d = new Dealing();
		d.run();
	}

	private void run() {
		Deck deck = new Deck();
		Scanner kb = new Scanner(System.in);

		System.out.print("How many cards do you want?");

		try {
			int choice = kb.nextInt();
			if (choice > 52) {
				kb.close();
				throw new InputMismatchException();
			}
			int value = 0;
			deck.shuffle();
			for (int i = 0; i < choice; i++) {
				Card dealtCard = deck.dealCard();
				value += dealtCard.getValue();
				System.out.println(dealtCard.toString());
			}
			System.out.println("The value is " + value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("That is not a valid number of cards.");
		}
		kb.close();
	}

}
