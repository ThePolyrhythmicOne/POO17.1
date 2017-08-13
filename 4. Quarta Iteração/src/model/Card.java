/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package model;

import java.util.*;

class Card {

	private char type;

	private String Name;

	private static List<Card> roomCards=new LinkedList<Card>();
	private static List<Card> weaponCards=new LinkedList<Card>();
	private static List<Card> playerCards=new LinkedList<Card>();
	private static List<Card> secretEnvelope=new LinkedList<Card>(); 
	
	protected Card(String name,char Type)
	{
		Name=name; type=Type;
	}
	
	protected String getCardName()
	{
		return Name;
	}
	
	protected static List<Card> getRoomCards()
	{
		return roomCards;
	}

	protected static List<Card> getWeaponCards()
	{
		return weaponCards;
	}

	protected static List<Card> getPlayerCards()
	{
		return playerCards;
	}
	
	protected static List<Card> getSecretEnvelope(){
		return secretEnvelope;
	}

	protected static void shuffleCards()
	{
		Collections.shuffle(roomCards);
		Collections.shuffle(weaponCards);
		Collections.shuffle(playerCards);
		
		System.out.printf("----------------------------------------\n");
		System.out.printf("CRIMINAL RECORDS:\n\n");
		secretEnvelope.add(roomCards.get(2));
		System.out.printf("%s\n", roomCards.get(2).getCardName());
		roomCards.remove(2);
		secretEnvelope.add(weaponCards.get(1));
		System.out.printf("%s\n", weaponCards.get(1).getCardName());
		weaponCards.remove(1);
		secretEnvelope.add(playerCards.get(0));
		System.out.printf("%s\n", playerCards.get(0).getCardName());
		playerCards.remove(0);
		System.out.printf("----------------------------------------\n");
		
		List<Card> shuffledCards = new ArrayList<Card>(roomCards.size()+playerCards.size()+weaponCards.size());
		shuffledCards.addAll(roomCards);
		shuffledCards.addAll(weaponCards);
		shuffledCards.addAll(playerCards);
		Collections.shuffle(shuffledCards);
		
		for(short i=0, j=0; i<Player.getPlayerArray().size(); i++)
		{
			if(j>=shuffledCards.size())
				break;
			
			if(Player.getPlayerArray().get(i).isSelected())
			{
				Player.getPlayerArray().get(i).addCard(shuffledCards.get(j));
				j++;
			}
			
			if(i==Player.getPlayerArray().size()-1)
				i=-1;
		}
	}

	public static String getCriminalRecords() 
	{
		List<Card> aux = Card.getSecretEnvelope();
		String[] names =  {"MrLifschitz","MrMathias","MrEndler","MrsRodriguez","MrGattass","MrSeibel"};
		String winnerName = null;
		
		for(Card c: aux)
		{
			if(c.getCardName().equalsIgnoreCase("MrEndler"))	
				winnerName =  names[0];
			
			if(c.getCardName().equalsIgnoreCase("MrMathias"))	
				winnerName = names[1];
			
			if(c.getCardName().equalsIgnoreCase("MrLifschitz"))	
				winnerName = names[2];
			
			if(c.getCardName().equalsIgnoreCase("MrsRodriguez"))	
				winnerName = names[3];
			
			if(c.getCardName().equalsIgnoreCase("MrGattass"))	
				winnerName = names[4];
			
			if(c.getCardName().equalsIgnoreCase("MrSeibel"))	
				winnerName = names[5];
		}
		return winnerName;
	}
	
	protected char getCardType() 
	{
		return this.type;
	}
}
