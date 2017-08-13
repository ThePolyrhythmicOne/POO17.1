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

 class PlayerMovement implements Observed {

 	protected short diceRoll;
 	private Position mouseClick;
 	protected static boolean firstRound=true;
 	
 	private GameBoard board = new GameBoard();
	private List<Observer> Observer=new ArrayList<Observer>();
	
	private static PlayerMovement instance=null;

	protected static PlayerMovement getInstance()
	{
		if(instance == null)
			instance = new PlayerMovement();
		return instance;
	}
	
	protected void movePlayer(Player pl)
	{
		int i = mouseClick.getCoordOnMatrixI(mouseClick.getPosY()),
			j = mouseClick.getCoordOnMatrixJ(mouseClick.getPosX());

		int iplayer = pl.getPosition().getCoordOnMatrixI(pl.getPosition().getPosY()), 
			jplayer = pl.getPosition().getCoordOnMatrixJ(pl.getPosition().getPosX());

		if(!pl.turnToMove)
		{
			Observer.get(0).sendMessage(4);
			return;
		}
		if(i>=0 && i<=24 && j>=0 && j<=23)
		{
			if(board.getBoardMatrix()[i][j]==Tiles.GameFloor && !isInRoom(pl))
			{
				if((Math.abs(iplayer-i) +  Math.abs(jplayer-j)) <=diceRoll)
				{
					int playerX = (int)((float)(j+1.75)*25+1.75),
						playerY = (int)((float)(i+1.2)*25+1.2);
					
					Position newPlayerPos = new Position(playerX,playerY);
					
					pl.setPosition(newPlayerPos);
					diceRoll=0;
					Round.setNextRound(Player.getPlayerArray());
					pl.PlayerTurn=pl.turnToMove=false;
					notifyObserver();
				}			
				else
					Observer.get(0).sendMessage(1);
			}
			//Checks if click was on room door
			else if(board.getBoardMatrix()[i][j]==Tiles.BallroomDoorL || board.getBoardMatrix()[i][j]==Tiles.BallroomDoorR || board.getBoardMatrix()[i][j]==Tiles.ConservatoryDoor
					|| board.getBoardMatrix()[i][j]==Tiles.DiningRoomDoor || board.getBoardMatrix()[i][j]==Tiles.HallDoor || board.getBoardMatrix()[i][j]==Tiles.KitchenDoor
					|| board.getBoardMatrix()[i][j]==Tiles.LibraryDoor || board.getBoardMatrix()[i][j]==Tiles.LoungeDoor || board.getBoardMatrix()[i][j]==Tiles.StudyDoor
					|| board.getBoardMatrix()[i][j]==Tiles.SecretPassageC2L || board.getBoardMatrix()[i][j]==Tiles.SecretPassageK2S || board.getBoardMatrix()[i][j]==Tiles.SecretPassageS2K
					|| board.getBoardMatrix()[i][j]==Tiles.SecretPassageL2C || board.getBoardMatrix()[i][j]==Tiles.BilliardRoomDoor)
			{

				if(isInRoom(pl))
				{
					moveToRoom(board.getBoardMatrix()[i][j],pl,true);
					if(!pl.isInRoom){
						Round.setNextRound(Player.getPlayerArray());
						pl.PlayerTurn=pl.turnToMove=false;
					}
				}
				else{
					if(((Math.abs(iplayer-i) +  Math.abs(jplayer-j))>diceRoll)){
						Observer.get(0).sendMessage(1);
						return;
					}
					moveToRoom(board.getBoardMatrix()[i][j],pl,false);
				}
				notifyObserver();
			}
			else
				Observer.get(0).sendMessage(2);
		}
		else
			Observer.get(0).sendMessage(3);
	}
	
	private boolean isSomeoneAtDoor(Position p)
	{
		for(Player e: Player.getPlayerArray())
		{
			int ipos = e.getPosition().getCoordOnMatrixI(e.getPosition().getPosY());
			int jpos = e.getPosition().getCoordOnMatrixJ(e.getPosition().getPosX());

			if((ipos == p.getCoordOnMatrixI(p.getPosY()) && (jpos==p.getCoordOnMatrixJ(p.getPosX()))))
				if(p.getCoordOnMatrixI(p.getPosY())!=Round.getNextPlayer(Player.getPlayerArray()).getPosition().getCoordOnMatrixI(Round.getNextPlayer(Player.getPlayerArray()).getPosition().getPosY())
				&& p.getCoordOnMatrixJ(p.getPosX())!=Round.getNextPlayer(Player.getPlayerArray()).getPosition().getCoordOnMatrixJ(Round.getNextPlayer(Player.getPlayerArray()).getPosition().getPosX())){
					Observer.get(0).sendMessage(5);
					return true;
				}
		}
		return false;
	}
	
	protected void moveToRoomAux(String room,Player pl)
	{
		switch(room){
		case "Ballroom":
			pl.setPosition(new Position(345,149));
			break;
		case "Conservatory":
			pl.setPosition(new Position(562,113));
			break;
		case "DiningRoom":
			pl.setPosition(new Position(145,357));
			break;
		case "BilliardRoom":
			pl.setPosition(new Position(553,296));
			break;
		case "Lounge":
			pl.setPosition(new Position(118,585));
			break;
		case "Study":
			pl.setPosition(new Position(554,613));
			break;
		case "Hall":
			pl.setPosition(new Position(346,573));
			break;
		case "Kitchen":
			pl.setPosition(new Position(118,121));
			break;
		case "Library":
			pl.setPosition(new Position(568,435));
			break;
		}
		notifyObserver();
	}
	
	protected void moveToRoom(Tiles room,Player pl,boolean isInRoom) 
	{ 
		switch(room)
		{
			case BallroomDoorL:
				if(isInRoom)
				{
					if(isSomeoneAtDoor(new Position(270,231)))
						Round.setNextRound(Player.getPlayerArray());
					else
					{
						pl.setPosition(new Position(270,231));
						isInRoom=pl.isInRoom=false;
					}
				}
				else
				{
					if(!isSomeoneAtDoor(new Position(270,231)))
					{
						pl.setPosition(new Position(345,149));
						pl.isInRoom=pl.turnToGuess=true;
					}
				}
				break;
			
			case BallroomDoorR:
				if(isInRoom)
				{
					if(isSomeoneAtDoor(new Position(445,156)))
						Round.setNextRound(Player.getPlayerArray());
					else
					{
						pl.setPosition(new Position(445,156)); 
						isInRoom=pl.isInRoom=false;
					}
				}
				else
				{
					if(!isSomeoneAtDoor(new Position(445,156)))
					{
						pl.setPosition(new Position(345,149));
						pl.isInRoom=pl.turnToGuess=true;
					}
				}
				break;
			
			case ConservatoryDoor:
				if(isInRoom)
				{
					if(isSomeoneAtDoor(new Position(495,156)))
						Round.setNextRound(Player.getPlayerArray());
					else
					{
						pl.setPosition(new Position(495,156));
						isInRoom=pl.isInRoom=false;
					}
				}
				else
				{
					if(!isSomeoneAtDoor(new Position(495,156)))
					{
						pl.setPosition(new Position(562,113));
						pl.isInRoom=pl.turnToGuess=true;
					}
				}
				break;
			
			case DiningRoomDoor:
				if(isInRoom)
				{
					if(isSomeoneAtDoor(new Position(195,430)))
						Round.setNextRound(Player.getPlayerArray());
					else
					{
						pl.setPosition(new Position(195,430));
						isInRoom=pl.isInRoom=false;
					}
				}
				else
				{
					if(!isSomeoneAtDoor(new Position(195,430)))
						pl.setPosition(new Position(145,357));
					pl.isInRoom=pl.turnToGuess=true;
				}
				break;
			
			case HallDoor:
				if(isInRoom)
				{
					if(isSomeoneAtDoor(new Position(345,455)))
						Round.setNextRound(Player.getPlayerArray());
					else
					{
						pl.setPosition(new Position(345,455));
						isInRoom=pl.isInRoom=false;
					}
				}
				else
				{
					if(!isSomeoneAtDoor(new Position(345,455)))
					{
						pl.setPosition(new Position(346,573));
						pl.isInRoom=pl.turnToGuess=true;
					}
				}
				break;
			
			case KitchenDoor:
				if(isInRoom)
				{
					if(isSomeoneAtDoor(new Position(145,205)))
						Round.setNextRound(Player.getPlayerArray());
					else
					{
						pl.setPosition(new Position(145,205));
						isInRoom=pl.isInRoom=false;
					}
				}
				else
				{
					if(!isSomeoneAtDoor(new Position(145,205)))
					{
						pl.setPosition(new Position(118,121));
						pl.isInRoom=pl.turnToGuess=true;
					}
				}
				break;
			
			case LibraryDoor:
				if(isInRoom)
				{
					if(isSomeoneAtDoor(new Position(445,430)))
						Round.setNextRound(Player.getPlayerArray());
					else
					{
						pl.setPosition(new Position(445,430));
						isInRoom=pl.isInRoom=false;
					}
				}
				else
				{
					if(!isSomeoneAtDoor(new Position(445,430)))
					{
						pl.setPosition(new Position(568,435));
						pl.isInRoom=pl.turnToGuess=true;
					}
				}
				break;
			
			case LoungeDoor:
				if(isInRoom)
				{
					if(isSomeoneAtDoor(new Position(195,480)))
						Round.setNextRound(Player.getPlayerArray());
					else
					{
						pl.setPosition(new Position(195,480));
						isInRoom=pl.isInRoom=false;
					}
				}
				else
				{
					if(!isSomeoneAtDoor(new Position(195,480)))
					{
						pl.setPosition(new Position(118,585));
						pl.isInRoom=pl.turnToGuess=true;
					}
				}
				break;
			
			case StudyDoor:
				if(isInRoom)
				{
					if(isSomeoneAtDoor(new Position(470,530)))
						Round.setNextRound(Player.getPlayerArray());
					else
					{
						pl.setPosition(new Position(470,530));
						isInRoom=pl.isInRoom=false;
					}
				}
				else
				{
					if(!isSomeoneAtDoor(new Position(195,480)))
					{
						pl.setPosition(new Position(554,613));
						pl.isInRoom=pl.turnToGuess=true;
					}
				}
				break;
			
			case BilliardRoomDoor:
				if(isInRoom)
				{
					if(isSomeoneAtDoor(new Position(470,255)))
						Round.setNextRound(Player.getPlayerArray());
					else
					{
						pl.setPosition(new Position(470,255));
						isInRoom=false;
					}
				}
				else
				{
					if(!isSomeoneAtDoor(new Position(470,255)))
					{
						pl.setPosition(new Position(553,296));
						pl.isInRoom=pl.turnToGuess=true;
					}
				}
				break;
			
			case SecretPassageK2S:
				pl.setPosition(new Position(554,613));
				pl.isInRoom=pl.turnToGuess=true;
				break;
			
			case SecretPassageC2L:
				pl.setPosition(new Position(118,585));
				pl.isInRoom=pl.turnToGuess=true;
				break;
			
			case SecretPassageS2K:
				pl.setPosition(new Position(118,121));
				pl.isInRoom=pl.turnToGuess=true;
				break;
			
			case SecretPassageL2C:
				pl.setPosition(new Position(562,113));
				pl.isInRoom=pl.turnToGuess=true;
				break;
			
			default:
				break;
		}
	}
	
	private boolean isInRoom(Player pl)
	{
		if(board.getBoardMatrix()[pl.getPosition().getCoordOnMatrixI(pl.getPosition().getPosY())][pl.getPosition().getCoordOnMatrixJ(pl.getPosition().getPosX())]==Tiles.Ballroom 
						|| board.getBoardMatrix()[pl.getPosition().getCoordOnMatrixI(pl.getPosition().getPosY())][pl.getPosition().getCoordOnMatrixJ(pl.getPosition().getPosX())]==Tiles.Conservatory
						|| board.getBoardMatrix()[pl.getPosition().getCoordOnMatrixI(pl.getPosition().getPosY())][pl.getPosition().getCoordOnMatrixJ(pl.getPosition().getPosX())]==Tiles.DiningRoom 
						|| board.getBoardMatrix()[pl.getPosition().getCoordOnMatrixI(pl.getPosition().getPosY())][pl.getPosition().getCoordOnMatrixJ(pl.getPosition().getPosX())]==Tiles.Hall 
						|| board.getBoardMatrix()[pl.getPosition().getCoordOnMatrixI(pl.getPosition().getPosY())][pl.getPosition().getCoordOnMatrixJ(pl.getPosition().getPosX())]==Tiles.Kitchen
						|| board.getBoardMatrix()[pl.getPosition().getCoordOnMatrixI(pl.getPosition().getPosY())][pl.getPosition().getCoordOnMatrixJ(pl.getPosition().getPosX())]==Tiles.Library 
						|| board.getBoardMatrix()[pl.getPosition().getCoordOnMatrixI(pl.getPosition().getPosY())][pl.getPosition().getCoordOnMatrixJ(pl.getPosition().getPosX())]==Tiles.Lounge 
						|| board.getBoardMatrix()[pl.getPosition().getCoordOnMatrixI(pl.getPosition().getPosY())][pl.getPosition().getCoordOnMatrixJ(pl.getPosition().getPosX())]==Tiles.Study
						|| board.getBoardMatrix()[pl.getPosition().getCoordOnMatrixI(pl.getPosition().getPosY())][pl.getPosition().getCoordOnMatrixJ(pl.getPosition().getPosX())]==Tiles.BilliardRoom)
			return true;
		else
			return false;
	}
	
	protected void showMessage(int showCode)
	{
		for(Observer u :Observer) 
			u.sendMessage(showCode);
	}
	
	protected void setClick(Position mouse)
	{
		mouseClick=mouse;
	}
	
	protected void setMoveSize(short size)
	{
		diceRoll=size;
	}
	
	public void notifyObserver()
	{
		for(Observer u :Observer) 
			u.update();
	}
	
	public void addObserver(Object O) 
	{
		Observer.add((Observer)O);
	}

	public void removeObserver(Object O) 
	{
		Observer.remove((Observer)O);
	}
}
