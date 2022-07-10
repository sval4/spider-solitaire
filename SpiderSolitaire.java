/* Sharat Val
 * Period 2
 * 12/6/18
 * 
 * First I completed all the unfinished work from activity 4 to 5. 
 * I mainly used try catch blocks in the SpiderSolitaire class and I also
 * used from if else statements in the board class. The reason there are
 * if else statements that stop errors, is because I implemented them during
 * activity 4 to 5 and I decided to leave them there since they work.
 * When there is only one token and that token is "move" or "clear" the
 * program does not throw an error it just waits for more tokens. For 
 * "move" I need to add at least 3 tokens and for "clear" I need to add
 * at least 2 in order for the program to run smoothly. However no errors are
 * ever thrown.
 * 
 * Sharat Val
 * Period 2
 * 12/9/18
 * 
 * Overall this lab was challenging and fun at the same time. I think the
 * String Craft lab was more challening for me. I think it was cool that
 * we got to create a text based version of solitaire. I was suprised I was
 * able to complete the save and load features without too much difficulty.
 * The hardest part of this lab was probably activity 5.
 * 
 * 
 * 
 */

import java.util.*;

public class SpiderSolitaire
{
    /** Number of stacks on the board **/
    public final int NUM_STACKS = 10;

    /** Number of complete decks used in the game.  
     *  The number of cards in a deck depends on the
     *  type of Card used.  For example, a 1-suit deck
     *  of standard playing cards consists of only 13 cards
     *  whereas a 4-suit deck consists of 52 cards.
     */
    public final int NUM_DECKS = 10;

    /** A Board contains stacks and a draw pile **/
    private Board board;

    /** Used for keyboard input **/
    private Scanner input;

    public SpiderSolitaire()
    {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS);
        input = new Scanner(System.in);
    }

    /** Main game loop that plays games until user wins or quits **/
    public void play() {

        board.printBoard();
        boolean gameOver = false;
        String p;
        while(!gameOver) {
            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   save");
            System.out.println("   load");
            System.out.println("   quit");
            System.out.print(">");
            String command = input.next();
            if (command.equals("move")) {
                /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                try{
                    String symbol = input.next();
                    int sourceStack = input.nextInt();
                    int destinationStack = input.nextInt();
                    board.makeMove(symbol, sourceStack - 1, destinationStack - 1);
                }catch(IllegalStateException e){
                    System.out.println("Invalid move");
                    p = input.nextLine();
                }
                catch(InputMismatchException e){
                    System.out.println("Enter two different integers for source stack and destination stack between 1 and " + NUM_STACKS);
                    p = input.nextLine();
                }
                catch(NoSuchElementException e){
                    System.out.println("Too many characters");
                    p = input.nextLine();
                }
            }
            else if (command.equals("draw")) {
                board.drawCards();
            }
            else if (command.equals("clear")) {
                /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                try{
                    int sourceStack = input.nextInt();
                    p = input.nextLine();
                    if(p.length() > 0){
                        throw new IllegalArgumentException();
                    }
                    board.clear(sourceStack - 1);
                }catch(IllegalStateException e){
                    System.out.println("Invalid move");
                    p = input.nextLine();
                }
                catch(InputMismatchException e){
                    System.out.println("Enter two different integers for source stack and destination stack between 1 and " + NUM_STACKS);
                    p = input.nextLine();
                }
                catch(NoSuchElementException e){
                    System.out.println("Too many characters");
                    p = input.nextLine();
                }
                catch(IllegalArgumentException e){
                    System.out.println("Enter a stack to access 1 - " + NUM_STACKS);
                }
            }
            else if (command.equals("restart")) {
                board = new Board(NUM_STACKS, NUM_DECKS);
            }
            else if (command.equals("quit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            else if(command.equals("save")) {
                board.save();
                board = new Board(NUM_STACKS, NUM_DECKS);
            }
            else if(command.equals("load")){
                board.load();
            }
            else {
                System.out.println("Invalid command.");
                p = input.nextLine();
            }
            
            board.printBoard();

            // If all stacks and the draw pile are clear, you win!
            if (board.isEmpty()) {
                gameOver = true;
            }
        }
        System.out.println("Congratulations!  You win!");
    }
}
