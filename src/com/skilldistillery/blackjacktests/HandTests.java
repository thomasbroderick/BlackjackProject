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

public class HandTests {
	Hand h;
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

	
	@Test
	public void test_add_card_functionality() {
		
		h.addCard(d.dealCard());
		((BlackjackHand) h).showCards();
		System.out.println(h.getHandValue());
	}
	

}
