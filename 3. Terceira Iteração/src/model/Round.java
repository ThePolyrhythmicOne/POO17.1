/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package model;

import java.util.LinkedList;
import java.util.List;

abstract class Round {
	
	protected static List<String> cardOwners = new LinkedList<String>();
	protected static List<String> cardNames  = new LinkedList<String>();
	
	protected static void makeGuessAux(List<String> selectedCards)
	{	
		String  room="",
				weapon="",
				player="";

		Room     roomObj = null;
		Weapon weaponObj = null;
		Player playerObj = null;

		for(short i=0;i<selectedCards.size();i++)
		{
			if(selectedCards.get(i)==WeaponNum.Candlestick.toString() || selectedCards.get(i)==WeaponNum.Dagger.toString() || selectedCards.get(i)==WeaponNum.LeadPipe.toString() || selectedCards.get(i)==WeaponNum.Revolver.toString() || selectedCards.get(i)==WeaponNum.Rope.toString() || selectedCards.get(i)==WeaponNum.Spanner.toString())
				weapon=selectedCards.get(i);
			else if   (selectedCards.get(i)==Tiles.Ballroom.toString() || selectedCards.get(i)==Tiles.BilliardRoom.toString() || selectedCards.get(i)==Tiles.Conservatory.toString() 
					|| selectedCards.get(i)==Tiles.DiningRoom.toString() || selectedCards.get(i)==Tiles.Hall.toString() || selectedCards.get(i)==Tiles.Kitchen.toString()
					|| selectedCards.get(i)==Tiles.Library.toString() || selectedCards.get(i)==Tiles.Lounge.toString() || selectedCards.get(i)==Tiles.Study.toString())
				room=selectedCards.get(i);
			else
				player=selectedCards.get(i);
		}

		for(Room r:Room.getRoomList())
		{
			if(r.getRoomName().equalsIgnoreCase(room))
			{
				roomObj=r;
				break;
			}
		}

		for(Weapon w:Weapon.getWeapons())
		{
			if(w.getName().equalsIgnoreCase(weapon))
			{
				weaponObj = w;
				break;
			}
		}

		for(Player p:Player.getPlayerArray())
		{
			if(p.getPlayerName().equalsIgnoreCase(player))
			{
				playerObj=p;
				break;
			}
		}

		
		for(short i=0;i<Room.getRoomList().size();i++)
			for(short j=0;j<Room.getRoomList().get(i).getWeapons().size();j++)
				if(Room.getRoomList().get(i).getWeapons().get(j)==weaponObj)
					Room.getRoomList().get(i).removeWeapon(weaponObj);
		
		roomObj.addWeapon(weaponObj);
		PlayerMovement.getInstance().moveToRoomAux(room, playerObj);
	}
	
	protected static void makeGuess(List<String> selectedNames,List<Player> activePlayerList)
	{
		for(Player p: activePlayerList)
		{
			if(p!=Round.getNextPlayer(Player.getPlayerArray()))
			{
				List<Card> aux=p.getCardDeck();
				for(Card c: aux)
				{
					if(c.getCardName().equalsIgnoreCase(selectedNames.get(0)))
					{	
						cardOwners.add(p.getPlayerName());
						cardNames.add(selectedNames.get(0));
					}
					if(c.getCardName().equalsIgnoreCase(selectedNames.get(1)))
					{
						cardOwners.add(p.getPlayerName());
						cardNames.add(selectedNames.get(1));
					}
					if( c.getCardName().equalsIgnoreCase(selectedNames.get(2)))
					{
						cardOwners.add(p.getPlayerName());
						cardNames.add(selectedNames.get(2));
					}
				}
			}
		}
	}
	
	protected static Player getNextPlayer(List<Player> list)
	{
		for(short i=0;i<list.size();i++)
		{
			if(list.get(i).isSelected() && list.get(i).PlayerTurn)
				return list.get(i);
		}
		return null;
	}
	
	protected static void startRounds(List<Player> list)
	{
		for(short i=0;i<list.size();i++)
		{
			if(list.get(i).isSelected() && !list.get(i).PlayerTurn)
			{
				list.get(i).turnToMove=list.get(i).turnToGuess=list.get(i).PlayerTurn=list.get(i).turnToThrow=true;
				break;
			}
		}
	}
	
	protected static void setNextRound(List<Player> list)
	{
		short k=0;
		for(short i=0; i<list.size(); i++)
		{	
			if(list.get(i).isSelected())
			{
				k++;
				if(list.get(i).PlayerTurn)
				{
					for(short j=i;j<list.size();j++)
					{
						if(i+1==list.size() || k==Player.getActivePlayes())
							j=0; //restart vector iterator (impromptu circular array)

						if(list.get(j).isSelected() && !list.get(j).PlayerTurn)
						{
							list.get(j).PlayerTurn=list.get(j).turnToMove=list.get(j).turnToThrow=true;
							list.get(i).PlayerTurn=list.get(i).turnToMove=false;
							break;
						}

						if(i+1==list.size()) 
							i=0; //restart vector iterator (impromptu circular array)
						
						if(k==Player.getActivePlayes())
							k=0;
					}
					break;
				}
			}
		}
	}
}
