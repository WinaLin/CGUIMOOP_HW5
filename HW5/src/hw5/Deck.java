package hw5;



import static hw5.Card.Suit.Club;
import java.util.ArrayList;

/*
 * Description: Deck類別包含ArrayList<Card>類別的屬性cards、Deck建構子，printDeck、getAllCards兩個方法，
 */
class Deck {

    private ArrayList<Card> cards;
    private ArrayList<Card> usedCard;
    private ArrayList<Card> openCard;
    public int nUsed;

    //TODO:建構子依照使用者輸入的撲克牌副數，將每張撲克牌存入cards陣列
    public Deck(int nDeck) {

        cards = new ArrayList<Card>();

        //Hint: Use new Card(x,y) and 3 for loops to add card into deck
//        for (int deck = 0; deck < nDeck; deck++) {
//            for (int i = 1; i <= 4; i++) {
//                for (int j = 1; j <= 13; j++) {
//                    Card card = new Card(i, j);
//                    cards.add(card);
//                }
//            }
//        }
        for (int deck = 0; deck < nDeck; deck++) {
            for (Card.Suit s : Card.Suit.values()) {
                for (int j = 1; j <= 13; j++) {
                    Card card = new Card(s, j);
                    cards.add(card);
                }
            }
        }
        shuffle();

    }

    //TODO: 用cards的長度除52(一副牌52張)來得到撲克牌副數，再沿用card內的方法printCard來印出每張牌
    public void printDeck() {

        //Hint: print all items in ArrayList<Card> cards, 
        //please implement and reuse printCard method in Card class
        for (int i = 0; i < (cards.size() / 52); i++) {
            Card card = new Card(Club, 1);
            card.printCard();
        }
    }

    public ArrayList<Card> getAllCards() {

        return cards;

    }

    public Card getOneCard(boolean isOpened) {
        if (cards.isEmpty()) {
            shuffle();
        }

        usedCard = new ArrayList<Card>();
        openCard = new ArrayList<Card>();

        Card getCard = cards.get(0);
        usedCard.add(getCard);
        nUsed++;
        if (isOpened) {
            openCard.add(getCard);
        }
        cards.remove(0);
        return getCard;
    }

    //亂數打亂ArrayList<Card> cards
    public void shuffle() {
        if (usedCard != null) {
            for (Card US : usedCard) {
                cards.add(US);
            }
            
        }

        for (int i = 1; i <= 52; i++) {
            int A = (int) (Math.random() * cards.size());
            Card a = cards.get(A);
            int B = (int) (Math.random() * cards.size());
            Card b = cards.get(B);
            cards.set(A, b);
            cards.set(B, a);

        }
        usedCard = new ArrayList<Card>();
        openCard = new ArrayList<Card>();
        nUsed = 0;
    }

    public ArrayList<Card> getOpenedCard() {
        return openCard;
    }
}
