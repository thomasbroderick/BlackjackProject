package com.skilldistillery.blackjacktests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.*;

import com.skilldistillery.blackjack.BlackjackHand;
import com.skilldistillery.common.Card;
import com.skilldistillery.common.Deck;
import com.skilldistillery.common.Hand;
import com.skilldistillery.common.Rank;
import com.skilldistillery.common.Suit;

public class HandTests {
	BlackjackHand h;
	Deck d;
	List<Card> dealtCards = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
		d = new Deck();
		d.shuffle();
		h = new BlackjackHand();
	}

	@After
	public void tearDown() throws Exception {
		h = null;
		d = null;
		dealtCards = null;
	}

	
	/*@Test
	public void test_add_card_functionality() {
		
		h.addCard(d.dealCard());
		((BlackjackHand) h).showCards();
		System.out.println(h.getHandValue());
	}*/
	@Test
	public void test_soft_ace_boolean() {
		Card c = new Card(Suit.CLUBS, Rank.ACE);
		Card d = new Card(Suit.CLUBS, Rank.TEN);
		
		h.addCard(c);
		h.addCard(d);
		assertTrue(h.softAce());
	}
	

}
