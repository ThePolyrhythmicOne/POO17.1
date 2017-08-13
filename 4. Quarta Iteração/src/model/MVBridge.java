/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package model;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class MVBridge {
	
	public static Position getNewPosition()
	{
		return (new Position());
	}

	public static List<Player> getPlayerList()
	{
		return Player.getPlayerArray();
	}

	public static void addPlayer(PlayerNum name,boolean isSelected,int posX, int posY,boolean turn,boolean Room)
	{
		Player.addPlayer(name, isSelected, posX, posY,turn,Room,turn,turn,turn,false);
	}

	public static int getPlayerListLength()
	{
		return Player.getPlayerArray().size();
	}

	public static String getPlayerName(int index)
	{
		return Player.getPlayerArray().get(index).getPlayerName();
	}

	public static boolean isPlayerSelected(int index)
	{
		return Player.getPlayerArray().get(index).isSelected();
	}

	public static int getPlayerPosX(int index)
	{
		return Player.getPlayerArray().get(index).getPosition().getPosX();
	}

	public static int getPlayerPosY(int index)
	{
		return Player.getPlayerArray().get(index).getPosition().getPosY();
	}

	public static void setMoveSize(int roll)
	{
		PlayerMovement.getInstance().setMoveSize((short)roll);
	}

	public static void registerObserver(Object O,char Type)
	{
		if(Type=='M')
			PlayerMovement.getInstance().addObserver(O);
	}

	public static void setClickPosition(int posX,int posY)
	{
		new Position(posX,posY).clickPosition(posX, posY);
	}

	public static void startRounds()
	{
		Round.startRounds(Player.getPlayerArray());
	}

	public static void makeGuess(List<String> selectedNames)
	{
		if(Round.getNextPlayer(Player.getPlayerArray()).isInRoom && Round.getNextPlayer(Player.getPlayerArray()).turnToGuess)
		{
			Round.getNextPlayer(Player.getPlayerArray()).turnToGuess=false;
			Round.makeGuess(selectedNames,Player.getActivePlayerList());
		}
		else
			System.out.println("Player not in a room or you already guessed");
	}
	
	public static int makeAccusation(List <String> selectedNames)
	{
		if(Round.getNextPlayer(Player.getPlayerArray()).isInRoom)
			return Round.makeAccusation(selectedNames);
		else
			return -1;
	}
	
	public static void setNextRound()
	{
		Round.setNextRound(Player.getPlayerArray());
	}
	
	public static List<String> getProofCards()
	{
		return Round.cardNames;
	}

	public static List<String> getProofCardsOwners()
	{
		return Round.cardOwners;
	}

	public static void initRooms()
	{
		Room.createRooms();
	}

	public static boolean isTimeToGuess()
	{
		return Round.getNextPlayer(Player.getPlayerArray()).turnToGuess;
	}

	public static boolean isTimeToThrow() 
	{
		return Round.getNextPlayer(Player.getPlayerArray()).turnToThrow;
	}
	
	public static String getWeaponName(int index)
	{
		return Weapon.getWeapons().get(index).getName();
	}

	public static int getWeaponPosX(Weapon w)
	{
		return w.getRoom().getWeaponPosX();
	}

	public static int getWeaponPosY(Weapon w)
	{
		return w.getRoom().getWeaponPosY();
	}
	
	public static Weapon getWeapon(int index)
	{
		return Weapon.getWeapons().get(index);
	}
	
	public static List<Weapon> getWeaponList()
	{
		return Weapon.getWeapons();
	}
	
	public static List<Room> getRoomList()
	{
		return Room.getRoomList();
	}

	public static String getCurrentPlayerName()
	{
		return Round.getNextPlayer(Player.getPlayerArray()).getPlayerName();
	}

	public static void addAnnotations(String c)
	{
		Round.getNextPlayer(Player.getPlayerArray()).addAnnotation(c);
	}

	public static List<String> getAnnotations()
	{
		return Round.getNextPlayer(Player.getPlayerArray()).getAnnotations();
	}

	public static boolean isPlayerInRoom()
	{
		return Round.getNextPlayer(Player.getPlayerArray()).isInRoom;
	}

	public static String getCurrentPlayerCards(int index)
	{
		return Round.getNextPlayer(Player.getPlayerArray()).getCardDeck().get(index).getCardName();
	}

	public static void shuffleCards()
	{
		Card.shuffleCards();
	}

	public static int rollDice()
	{
		Round.getNextPlayer(Player.getPlayerArray()).turnToThrow=false;
		return Dice.roll();
	}

	public static int getPlayerDeckSize()
	{
		return Round.getNextPlayer(Player.getPlayerArray()).getCardDeck().size();
	}

	public static void makeGuessAux(List<String> selectedCards) 
	{
		Round.makeGuessAux(selectedCards);
	}

	public static void setWinner() 
	{
		Round.setWinner();
	}

	public static void setLoser() 
	{
		Round.setLoser();
	}

	public static void saveGame() 
	{
		SaveLoad.Save();
	}

	public static void loadGame(File selectedFile) 
	{
		try 
		{
			SaveLoad.loadGame(selectedFile);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static String getPrisoner() 
	{
		return Card.getCriminalRecords();
	}
}
