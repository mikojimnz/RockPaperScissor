package ap.elevens.activity9;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen",
			"king" };

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS = { "spades", "hearts", "diamonds", "clubs" };

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0 };

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = true;

	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	public ElevensBoard() {
		super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	}

	/**
	 * Determines if the selected cards form a valid group for removal. In Elevens,
	 * the legal groups are (1) a pair of non-face cards whose values add to 11, and
	 * (2) a group of three cards consisting of a jack, a queen, and a king in some
	 * order.
	 * 
	 * @param selectedCards
	 *            the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal; false
	 *         otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {

		if (selectedCards.size() == 2) {
			return containsPairSum11(selectedCards);
		}

		if (selectedCards.size() == 3) {
			return containsJQK(selectedCards);
		}

		return false;
	}

	/**
	 * Determine if there are any legal plays left on the board. In Elevens, there
	 * is a legal play if the board contains (1) a pair of non-face cards whose
	 * values add to 11, or (2) a group of three cards consisting of a jack, a
	 * queen, and a king in some order.
	 * 
	 * @return true if there is a legal play left on the board; false otherwise.
	 */
	@Override
	public boolean anotherPlayIsPossible() {
		List<Integer> indexes = cardIndexes();
		ArrayList<ArrayList<Integer>> possibilities = new ArrayList<ArrayList<Integer>>();

		if (I_AM_DEBUGGING) {
			System.out.println("Array Size: " + indexes.size());
		}

		for (int i = 0; i < indexes.size(); i++) {
			for (int j = 0; j < indexes.size(); j++) {
				if ((cardAt(i).pointValue() + cardAt(j).pointValue()) == 11) {
					possibilities.add(new ArrayList<Integer>(Arrays.asList(i, j)));
				}
			}
		}

		for (int i = 0; i < indexes.size(); i++) {
			for (int j = 0; j < indexes.size(); j++) {
				for (int k = 0; k < indexes.size(); k++) {
					if ((cardAt(i).pointValue() + cardAt(j).pointValue() + cardAt(k).pointValue()) == 0) {
						possibilities.add(new ArrayList<Integer>(Arrays.asList(i, j, k)));
					}
				}
			}
		}
		
		for (int i = 0; i < possibilities.size(); i++) {
			
		}

		if (I_AM_DEBUGGING) {
			System.out.println(possibilities + "\n");
		}

		if (possibilities.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Check for an 11-pair in the selected cards.
	 * 
	 * @param selectedCards
	 *            selects a subset of this board. It is list of indexes into this
	 *            board that are searched to find an 11-pair.
	 * @return true if the board entries in selectedCards contain an 11-pair; false
	 *         otherwise.
	 */
	private boolean containsPairSum11(List<Integer> selectedCards) {
		int c1 = cardAt(selectedCards.get(0)).pointValue();
		int c2 = cardAt(selectedCards.get(1)).pointValue();

		if (c1 + c2 == 11) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check for a JQK in the selected cards.
	 * 
	 * @param selectedCards
	 *            selects a subset of this board. It is list of indexes into this
	 *            board that are searched to find a JQK group.
	 * @return true if the board entries in selectedCards include a jack, a queen,
	 *         and a king; false otherwise.
	 */
	private boolean containsJQK(List<Integer> selectedCards) {
		int c1 = cardAt(selectedCards.get(0)).pointValue();
		int c2 = cardAt(selectedCards.get(1)).pointValue();
		int c3 = cardAt(selectedCards.get(2)).pointValue();

		if ((c1 + c2 + c3) == 0) {
			return true;
		} else {
			return false;
		}
	}
}
