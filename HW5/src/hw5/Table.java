
package hw5;


import java.util.ArrayList;


public class Table {
    static final int MAXPLAYER = 4;
    private Deck AllCard;
    private Player[] AllPlayer;
    private Dealer dealer;
    private int[] pos_betArray = new int[MAXPLAYER];


    public Table(int nDeck) {
        AllCard = new Deck(nDeck);
        AllPlayer = new Player[MAXPLAYER];
    }
    public void set_player(int pos, Player p){
        AllPlayer[pos] = p;
    }
    public Player[] get_player(){
        return AllPlayer;
    }
    public void set_dealer(Dealer d){
        dealer = d;
    }
    public Card get_face_up_card_of_dealer(){
        return dealer.getOneRoundCard().get(1);
    }
    private void ask_each_player_about_bets(){
        for(int i = 0;i<MAXPLAYER;i++){
            AllPlayer[i].say_hello();
            pos_betArray[i] = AllPlayer[i].make_bet();
        }
    }
    private void distribute_cards_to_dealer_and_players(){
        for(int i = 0;i<MAXPLAYER;i++){
            ArrayList<Card> playerCard = new ArrayList<Card>();
            playerCard.add(AllCard.getOneCard(true));
            playerCard.add(AllCard.getOneCard(true));
            AllPlayer[i].setOneRoundCard(playerCard);
        }
        ArrayList<Card> dealerCard = new ArrayList<Card>();
        dealerCard.add(AllCard.getOneCard(true));
        dealerCard.add(AllCard.getOneCard(true));
        dealer.setOneRoundCard(dealerCard);
        
        System.out.println("Dealer's face up card is ");
        Card card = new Card(dealer.getOneRoundCard().get(1).getSuit(), dealer.getOneRoundCard().get(1).getRank());
        card.printCard();
    }
private void ask_each_player_about_hits(){
     for(int i = 0;i<MAXPLAYER;i++){
         boolean hit=false;
         do{
             hit=AllPlayer[i].hit_me(this); //this
             if(hit){
                AllPlayer[i].getOneRoundCard().add(AllCard.getOneCard(true));
                System.out.print("Hit! ");
                System.out.println(AllPlayer[i].get_name()+"'s Cards now:");
                 for(Card c : AllPlayer[i].getOneRoundCard()){
                       c.printCard();
                   }
                }else{
                    System.out.println(AllPlayer[i].get_name()+", Pass hit!");
                    System.out.println(AllPlayer[i].get_name()+"'s hit is over!");
             }
	}while(hit);
     }
}
private void ask_dealer_about_hits(){
     boolean hit=false;
         do{
             hit=dealer.hit_me(this); //this
             if(hit){
                dealer.getOneRoundCard().add(AllCard.getOneCard(true));
             }else{
                System.out.println("Dealer's hit is over!");
            }
	}while(hit);
}
private void calculate_chips(){
    System.out.println("Dealer's card value is "+dealer.getTotalValue()+"\nDealer's Cards:");
    dealer.printAllCard();
    
    for(int i = 0;i<MAXPLAYER;i++){
        System.out.println(AllPlayer[i].get_name()+"'s Card:");
        AllPlayer[i].printAllCard();
        System.out.print(AllPlayer[i].get_name()+" card value is "+AllPlayer[i].getTotalValue());
        if((AllPlayer[i].getTotalValue()>21&&dealer.getTotalValue()<=21)||(AllPlayer[i].getTotalValue()<=21&&dealer.getTotalValue()<=21&&AllPlayer[i].getTotalValue()<dealer.getTotalValue())){
            AllPlayer[i].increase_chips(-pos_betArray[i]);
            System.out.println(", Loss "+pos_betArray[i]+" Chips, the Chips now is:"+AllPlayer[i].get_current_chips());
        }else if((AllPlayer[i].getTotalValue()<=21&&dealer.getTotalValue()>21)||(AllPlayer[i].getTotalValue()<=21&&dealer.getTotalValue()<=21&&AllPlayer[i].getTotalValue()>dealer.getTotalValue())){
            AllPlayer[i].increase_chips(pos_betArray[i]);
            System.out.println(", Get "+pos_betArray[i]+" Chips, the Chips now is:"+AllPlayer[i].get_current_chips());
        }else{
            System.out.println(",chips have no change! The Chips now is: "+AllPlayer[i].get_current_chips());
        }
    }
}
public int[] get_palyers_bet(){
        return pos_betArray;   
}
public void play(){
    ask_each_player_about_bets();
    distribute_cards_to_dealer_and_players();
    ask_each_player_about_hits();
    ask_dealer_about_hits();
    calculate_chips();
}



    

}
