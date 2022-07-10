public class DeckTester
{
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
        String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
    	int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    	/*
    	Board b = new Board(7, 4);
    	b.printBoard();
        d.createDeck(symbols, values);
        d.shuffle();
        System.out.println(d);
        */
        Deck d = new Deck();
        d.createDeck(symbols, values);
        System.out.println(d);
        String s = d.returnDeck();
        System.out.println(s);
        Deck f = new Deck(s);
        System.out.println(f);
    }
}