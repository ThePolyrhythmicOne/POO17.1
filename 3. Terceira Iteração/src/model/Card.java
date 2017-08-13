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

	@SuppressWarnings("unused")
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

	protected static void shuffleCards()
	{
		Collections.shuffle(roomCards);
		Collections.shuffle(weaponCards);
		Collections.shuffle(playerCards);
		
		secretEnvelope.add(roomCards.get(2));
		roomCards.remove(2);
		secretEnvelope.add(weaponCards.get(1));
		weaponCards.remove(1);
		secretEnvelope.add(playerCards.get(0));
		playerCards.remove(0);
		
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
}