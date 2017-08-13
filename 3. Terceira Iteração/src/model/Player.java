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

class Player {
	
	protected boolean turnToMove;
	protected boolean PlayerTurn;
	protected boolean turnToGuess;
	protected boolean turnToThrow;
	protected boolean isInRoom;
	private boolean isSelected;
	private Position currentPosition;
	private Position startingPosition;	

	private List<String> Annotations=new LinkedList<String>();
	
	private static short activePlayers=0;

	private static List<Player> player=new ArrayList<Player>(6);
	private List<Card> cardDeck=new LinkedList<Card>();
	private static List<Player> activePlayerList=new LinkedList<Player>();

	private final String playerName;
	
	public Player(String playerName, boolean isSelected,boolean Turn,boolean Room) 
	{
		this.playerName = playerName;
		this.isSelected = isSelected;
		this.isInRoom=Room;
		this.turnToMove=this.PlayerTurn=this.turnToGuess=this.turnToThrow=Turn;
	}
	
	protected Position getPosition() 
	{
		return this.currentPosition;
	}

	protected int getStartingPositionX()
	{
		return startingPosition.getPosX();
	}
	
	protected int getStartingPositionY()
	{
		return startingPosition.getPosY();
	}
	protected Position getStartingPosition()
	{
		return startingPosition;
	}
	
	protected static short getActivePlayes()
	{
		return activePlayers;
	}
	
	protected static List<Player> getActivePlayerList()
	{
		return activePlayerList;
	}
	
	protected static void addPlayer(PlayerNum name,boolean isSelected,int posX,int posY,boolean turn,boolean Room)
	{
		Player novo=new Player(name.toString(),isSelected,turn,Room);
		player.add(novo);
		player.get(player.indexOf(novo)).setStartingPosition(new Position(posX,posY));
		player.get(player.indexOf(novo)).setPosition(player.get(player.indexOf(novo)).getStartingPosition());

		Card.getPlayerCards().add(new Card(name.toString(),'P'));
		
		if(isSelected){
			activePlayers++;
			activePlayerList.add(player.get(player.indexOf(novo)));
		}
	}
	
	protected void addCard(Card c)
	{
		cardDeck.add(c);
	}
	
	protected List<Card> getCardDeck()
	{
		return cardDeck;
	}
	
	protected void addAnnotation(String c)
	{
		Annotations.add(c);
	}
	
	protected List<String> getAnnotations(){
		return Annotations;
	}
	
	protected static List<Player> getPlayerArray()
	{
		return player;
	}
	
	protected void setStartingPosition(Position pos) 
	{
		this.startingPosition = pos;
	}
	
	protected void setPosition(Position pos) 
	{
		this.currentPosition = pos;
	}

	protected String getPlayerName() 
	{
		return playerName;
	}

	protected boolean isSelected() 
	{
		return isSelected;
	}

}