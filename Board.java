/* Sharat Val
 * Period 2
 * 12/4/18
 * 
 * My program works for the most part, but there are certain scenarios where
 * an error is thrown. I plan to fix this during class with the help of my
 * peers. Writing the logic for the board class was challenging, but the 
 * hardest part for me might be the debugging process.
 * 
 */
import java.util.*;
import javax.swing.JFileChooser;
import java.io.*;
public class Board
{   
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    // Attributes
    private ArrayList<Deck> stacks = new ArrayList<Deck>();
    private Deck pile;
    /**
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  The number of Cards in a Deck
     *  depends on the number of suits. Here are examples:
     *  
     *  # suits     # numDecks      #cards in overall Deck
     *      1            1          13 (all same suit)
     *      1            2          26 (all same suit)
     *      2            1          26 (one of each suit)
     *      2            2          52 (two of each suit)
     *      4            2          104 (two of each suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.  If you'd like to specify
     *  more than one suit, feel free to add to the parameter list.
     */    
    public Board(int numStacks, int numDecks) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        Deck d = new Deck();
        Deck deck1 = new Deck();
        pile = new Deck();
        for(int i = 0; i < numDecks; i++){
            d.createDeck(symbols, values);
        }
        d.shuffle();
        deck1 = d.subList(d.size() / 2, d.size());
        int length = d.size() / 2;
        for(int i = d.size() - 1; i >= length; i--){
            d.remove(d.get(i));
        }
        length = deck1.size();
        int num1 = 0;
        for(int i = 0; i < numStacks; i++){
            stacks.add(new Deck());
        }
        for(int i = deck1.size() - 1; i >= 0; i--){
            stacks.get(num1).add(deck1.get(i));
            deck1.remove(deck1.get(i));
            num1++;
            if(num1 % numStacks == 0){
                num1 = 0;
            }
        }
        for(int i = 0; i < numStacks; i++){
            stacks.get(i).get(stacks.get(i).size() - 1).setFaceUp(true);
        }
        pile = d;
    }
    
    public void load(){
        JFileChooser chooser = new JFileChooser(".");
        chooser.showOpenDialog(null);
        File a = chooser.getSelectedFile();
        try{
            Scanner in = new Scanner(a);
            stacks = new ArrayList<Deck>();
            Deck pile;
            pile = new Deck(in.nextLine());
            while(in.hasNext()){
                Deck d = new Deck(in.nextLine());
                stacks.add(d);
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void save(){
        JFileChooser chooser = new JFileChooser(".");
        chooser.showSaveDialog(null);
        File a = chooser.getSelectedFile();
        try{
            FileWriter b = new FileWriter(a);
            b.write(pile.returnDeck());
            for(int i = 0; i < stacks.size(); i++){
                b.write("\n");
                b.write(stacks.get(i).returnDeck());
            }
            b.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Moves a run of cards from src to dest (if possible) and flips the
     *  next card if one is available.  Change the parameter list to match
     *  your implementation of Card if you need to.
     */
    public void makeMove(String symbol, int src, int dest) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        boolean check = true;
        boolean check2 = true;
        boolean check3 = true;
        if(src < 0 || src >= stacks.size() || dest < 0 || dest >= stacks.size()){
            check = false;
            System.out.println("Enter numbers within range 1 - " + stacks.size());
        }else if(stacks.get(src).indexOf(symbol) == -1){
            check = false;
            System.out.println("Enter numbers that exist within a stack");
        }
        if(check){
            for(int i = stacks.get(src).indexOf(symbol); i < stacks.get(src).size(); i++){
                if(!stacks.get(src).get(i).isFaceUp()){
                    check = false;
                    System.out.println("Only cards that are face up can move");
                }
            }
        }
        if(check){
            for(int i = 0; i < stacks.size(); i++){
                for(int j = stacks.get(src).indexOf(symbol); j < stacks.get(src).size(); j++){
                    if(check3){
                        for(int k = stacks.get(src).indexOf(symbol); k < (stacks.get(src).size() - 1); k++){
                            if(stacks.get(src).get(k).isFaceUp() == false){
                                check = false;
                                System.out.println("Only cards that are face up can move");
                                break;
                            }else if(!(stacks.get(src).get(k).getValue() - 1 == stacks.get(src).get(k + 1).getValue())){
                                check = false;
                                System.out.println("Only cards that are at the end or runs can be moved");
                            }
                        }
                    }
                    check3 = false;
                    if(!check){
                        break;
                    }
                    if(stacks.get(dest).size() != 0){
                        if(check2 && !(stacks.get(dest).get(stacks.get(dest).size() - 1).getValue() - 1 == stacks.get(src).get(stacks.get(src).indexOf(symbol)).getValue())){
                            check = false;
                            System.out.println("Cards can only be placed in empty stacks or at the end of a stack where a run is created");
                            break;
                        }
                    }
                    
                    check2 = false;
                    stacks.get(dest).add(stacks.get(src).get(j));
                    stacks.get(src).remove(stacks.get(src).get(j));
                    j--;
                }
                break;
            }
            check2 = true;
            check3 = true;
            if(stacks.get(src).size() != 0 && check){
                stacks.get(src).get(stacks.get(src).size() - 1).setFaceUp(true);
            }
        }
    }

    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        boolean check = true;
        if(pile.size() == 0){
            check = false;
        }
        for(int i = 0; i < stacks.size(); i++){
            if(stacks.get(i).size() == 0){
                check = false;
            }
        }
        if(check){
            if(pile.size() >= stacks.size()){
                for(int i = stacks.size() - 1; i >= 0; i--){
                    if(pile.size() != 0){
                        pile.get(i).setFaceUp(true);
                        stacks.get(i).add(pile.get(i));
                        pile.remove(pile.get(i));
                    }
                }
            }else{
                int num = 0;
                for(int i = pile.size() - 1; i >= 0; i--){
                    if(pile.size() != 0){
                        pile.get(i).setFaceUp(true);
                        stacks.get(num).add(pile.get(i));
                        pile.remove(pile.get(i));
                        num++;
                    }
                }
            }
        }else{
            System.out.println("Invalid");
        }
    }

    /**
     *  Returns true if all stacks and the draw pile are all empty
     */ 
    public boolean isEmpty() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        int num = 0;
        for(int i = 0; i < stacks.size(); i++){
            if(stacks.get(i).size() == 0){
                num++;
            }
        }
        return num == stacks.size() && pile.size() == 0;
    }

    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        boolean check = true;
        boolean check2 = true;
        if(sourceStack < 0 || sourceStack > stacks.size() - 1){
            check2 = false;
            System.out.println("Enter numbers within range 1 - " + stacks.size());
        }
        if(check2 && stacks.get(sourceStack).size() >= 13){
            for(int i = stacks.get(sourceStack).size() - 1; i >= stacks.get(sourceStack).size() - 13; i--){
                if(stacks.get(sourceStack).get(i).isFaceUp() == false && !(stacks.get(sourceStack).get(i).getValue() + 1 == stacks.get(sourceStack).get(i - 1).getValue())){
                    check = false;
                }
            }
        }else{
            check = false;
        }
        if(check){
            int num = stacks.get(sourceStack).size();
            for(int i = 0; i < 13; i++){
                stacks.get(sourceStack).remove(stacks.get(sourceStack).get(stacks.get(sourceStack).size() - 1));
            }
            if(stacks.get(sourceStack).size() != 0){
                stacks.get(sourceStack).get(stacks.get(sourceStack).size() - 1).setFaceUp(true);
            }
        }else if(!check && check2){
            System.out.println("There needs to be a run of A through K with A as the last card in a stack");   
        }
    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        System.out.println("Stacks: ");
        for(int i = 0; i < stacks.size(); i++){
            System.out.println((i + 1) + ": " + stacks.get(i));
        }
        System.out.println("\nDraw Pile:");
        System.out.println(pile);
    }
}