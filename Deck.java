/* Sharat Val
 * Period 2
 * 12/2/18
 * 
 * The deck class was easy to do, but it helped me further learn the 
 * relationships between classes and using objects from different classes.
 * 
 */
import java.util.*;
public class Deck
{
    ArrayList<Card> deck = new ArrayList<Card>();
    Scanner in;
    public Deck(){
    	
    }
    
    public Deck(String s){
        int num = 0;
        boolean state = true;
        String sym = "";
        int val = 0;
        in = new Scanner(s);
        while(in.hasNext()){
            if(num == 0 && in.next().equals("true")){
                state = true;
                num++;
            }else if(num == 0){
                state = false;
                num++;
            }else if(num == 1){
                sym = in.next();
                num++;
            }else if(num == 2){
                val = in.nextInt();
                num++;
            }
            if(num == 3){
                Card c = new Card(sym, val);
                c.setFaceUp(state);
                deck.add(c);
                num = 0;
            }
        }
    }
    
    public String returnDeck(){
        String s = "";
        for(int i = 0; i < deck.size(); i++){
            s = s + " " + deck.get(i).isFaceUp();
            deck.get(i).setFaceUp(true);
            s = s + " " + deck.get(i).getSymbol() + " " + deck.get(i).getValue();
        }
        return s;
    }
    
    public void addDeck(String[] sym, int[] val){
        for(int i = 0; i < sym.length; i++){
            Card c = new Card(sym[i], val[i]);
            c.setFaceUp(true);
            deck.add(c);
        }
    }
    
    public Card draw(){
        if(deck.size() == 0){
            return null;
        }
        Card d = deck.get(deck.size() - 1);
        deck.remove(deck.size() - 1);
        return d;
    }
    
    public void draw(int n){
        for(int i = deck.size() - 1; i >= n; i--){
            deck.remove(i);
        }
    }
    
    public void createDeck(String[] sym, int[] val){
        for(int i = 0; i < sym.length; i++){
            Card c = new Card(sym[i], val[i]);
            deck.add(c);
        }
    }
    
    public void add(Card c){
        deck.add(c);
    }
    
    public void remove(Card c){
        deck.remove(c);
    }
    
    public int indexOf(String c){
        for(int i = deck.size() - 1; i >= 0; i--){
            if(deck.get(i).toString().equals(c)){
                return i;
            }
        }
        return -1;
    }
    
    public boolean contains(String c){
        return deck.contains(c);
    }
    
    public Deck subList(int a, int b){
        Deck c = new Deck();
        for(int i = a; i < b; i++){
            c.add(deck.get(i));
        }
        return c;
    }
    
    public Card get(int i){
        return deck.get(i);
    }
    
    public int size(){
        return deck.size();
    }
    
    public void shuffle(){
        for(int i = deck.size() - 1; i >= 0; i--){
            int a = (int) (Math.random() * deck.size());
            Card s = deck.get(i);
            deck.set(i, deck.get(a));
            deck.set(a, s);
        }
    }
    
    
    @Override
    public String toString(){
        return deck.toString();
    }
    /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
}