/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package model;

import view.BoardPanel;

public class PlayerMovement implements Observed {

	//Variavel para pegar clique do mouse
	private Position mouseClick;

	//Vetor de jogadores
	private Player[] player=Player.getPlayerArray();

	//Variavel que representa o a matriz
	private GameBoard board=new GameBoard();

	//Variavel utilizada para implementar design pattern SINGLETON
	private static PlayerMovement firstInstance = null;
	
	//Class SINGLETON
	public static PlayerMovement getInstance(){
		if(firstInstance == null)
			firstInstance = new PlayerMovement();
		return firstInstance;
	}

	public PlayerMovement() {}
	
	public void movePlayer(int roll,PlayerNum pl) {
		
		//Variavel que determina as coordenadas do clique no tabuleiro
		int i = mouseClick.getCoordOnMatrixI(mouseClick.getPosY()),j =mouseClick.getCoordOnMatrixJ(mouseClick.getPosX());

		//Variavel que pega coordenada na matriz de cada jogador
		int iplayer=player[pl.ordinal()].getPosition().getCoordOnMatrixI(player[pl.ordinal()].getPosition().getPosY()), jplayer=player[pl.ordinal()].getPosition().getCoordOnMatrixJ(player[pl.ordinal()].getPosition().getPosX());
		

		if(i>=0 && i<=24 && j>=0 && j<=23)
		{
			if(board.getBoardMatrix()[i][j]==Tiles.GameFloor && !isInRoom(pl))
			{
				if((module(iplayer,i) +  module(jplayer,j)) <=roll)
				{
					int playerX=(int)((float)(j+1.75)*25+2),playerY=(int)((float)(i+1.2)*25+1);
					Position newPlayerPos=new Position(playerX,playerY);
					
					player[pl.ordinal()].setPosition(newPlayerPos);
					Notify();
					
					System.out.printf("Nova Casa:%d %d\n X=%d Y=%d\n",player[pl.ordinal()].getPosition().getCoordOnMatrixI(player[pl.ordinal()].getPosition().getPosY())
							,player[pl.ordinal()].getPosition().getCoordOnMatrixJ(player[pl.ordinal()].getPosition().getPosX()),player[pl.ordinal()].getPosition().getPosX()
							,player[pl.ordinal()].getPosition().getPosY());
				}			
				else
					showMessage(1);
			}
			//Checando se o clique foi na porta de algum comodo
			else if (board.getBoardMatrix()[i][j]==Tiles.BallroomDoorL    || 
					 board.getBoardMatrix()[i][j]==Tiles.BallroomDoorR    || 
					 board.getBoardMatrix()[i][j]==Tiles.ConservatoryDoor || 
					 board.getBoardMatrix()[i][j]==Tiles.DiningRoomDoor   || 
					 board.getBoardMatrix()[i][j]==Tiles.HallDoor         || 
					 board.getBoardMatrix()[i][j]==Tiles.KitchenDoor      || 
					 board.getBoardMatrix()[i][j]==Tiles.LibraryDoor      || 
					 board.getBoardMatrix()[i][j]==Tiles.LoungeDoor       || 
					 board.getBoardMatrix()[i][j]==Tiles.StudyDoor        || 
					 board.getBoardMatrix()[i][j]==Tiles.SecretPassageC2L || 
					 board.getBoardMatrix()[i][j]==Tiles.SecretPassageK2S || 
					 board.getBoardMatrix()[i][j]==Tiles.SecretPassageS2K || 
					 board.getBoardMatrix()[i][j]==Tiles.SecretPassageL2C || 
					 board.getBoardMatrix()[i][j]==Tiles.BillardRoomDoor)
			{
				//Checa se o jogador esta no quarto
				if(isInRoom(pl))
					moveToRoom(board.getBoardMatrix()[i][j],pl,true);
				else
					moveToRoom(board.getBoardMatrix()[i][j],pl,false);
			}
			else
				showMessage(2);
		}
		else
			showMessage(3);
	}
	
	//Metodo responsavel por mover jogador para dentro do quarto
	private void moveToRoom(Tiles room,PlayerNum pl,boolean isInRoom) 
	{ 
		switch(room)
		{
			case BallroomDoorL:
				if(isInRoom)
				{
					player[pl.ordinal()].setPosition(new Position(270,230));
					isInRoom=false;
				}
				else
					player[pl.ordinal()].setPosition(new Position(345,149));
				break;
		
			case BallroomDoorR:
				if(isInRoom)
				{
					player[pl.ordinal()].setPosition(new Position(445,156)); 
					isInRoom=false;
				}
				else
					player[pl.ordinal()].setPosition(new Position(345,149));
				break;
			
			case ConservatoryDoor:
				if(isInRoom)
				{
					player[pl.ordinal()].setPosition(new Position(495,156));
					isInRoom=false;
				}
				else
					player[pl.ordinal()].setPosition(new Position(562,113));
				break;
			
			case DiningRoomDoor:
				if(isInRoom)
				{
					player[pl.ordinal()].setPosition(new Position(195,430));
					isInRoom=false;
				}
				else
					player[pl.ordinal()].setPosition(new Position(145,357));
				break;
			
			case HallDoor:
				if(isInRoom)
				{
					player[pl.ordinal()].setPosition(new Position(345,455));
					isInRoom=false;
				}
				else
					player[pl.ordinal()].setPosition(new Position(346,573));
				break;
			
			case KitchenDoor:
				if(isInRoom)
				{
					player[pl.ordinal()].setPosition(new Position(145,205));
					isInRoom=false;
				}
				else
					player[pl.ordinal()].setPosition(new Position(118,121));
				break;
			
			case LibraryDoor:
				if(isInRoom)
				{
					player[pl.ordinal()].setPosition(new Position(445,430));
					isInRoom=false;
				}
				else
					player[pl.ordinal()].setPosition(new Position(568,435));
				break;
			
			case LoungeDoor:
				if(isInRoom)
				{
					player[pl.ordinal()].setPosition(new Position(195,480));
					isInRoom=false;
				}
				else
					player[pl.ordinal()].setPosition(new Position(118,585));
				break;
			
			case StudyDoor:
				if(isInRoom)
				{
					player[pl.ordinal()].setPosition(new Position(470,530));
					isInRoom=false;
				}
				else
					player[pl.ordinal()].setPosition(new Position(554,613));
				break;
			
			case BillardRoomDoor:
				if(isInRoom)
				{
					player[pl.ordinal()].setPosition(new Position(470,255));
					isInRoom=false;
				}
				else
					player[pl.ordinal()].setPosition(new Position(553,296));
				break;
			
			case SecretPassageK2S:
				player[pl.ordinal()].setPosition(new Position(554,613));
				break;
			
			case SecretPassageC2L:
				player[pl.ordinal()].setPosition(new Position(118,585));
				break;
			
			case SecretPassageS2K:
				player[pl.ordinal()].setPosition(new Position(118,121));
				break;
			
			case SecretPassageL2C:
				player[pl.ordinal()].setPosition(new Position(562,113));
				break;
			
			default:
				break;
		}
	}
	
	//Metodo responsavel por retornar se o jogador esta em um quarto
	private boolean isInRoom(PlayerNum pl)
	{
		if(board.getBoardMatrix()[player[pl.ordinal()].getPosition().getCoordOnMatrixI(player[pl.ordinal()].getPosition().getPosY())][player[pl.ordinal()].getPosition().getCoordOnMatrixJ(player[pl.ordinal()].getPosition().getPosX())]==Tiles.Ballroom 
		|| board.getBoardMatrix()[player[pl.ordinal()].getPosition().getCoordOnMatrixI(player[pl.ordinal()].getPosition().getPosY())][player[pl.ordinal()].getPosition().getCoordOnMatrixJ(player[pl.ordinal()].getPosition().getPosX())]==Tiles.Conservatory
		|| board.getBoardMatrix()[player[pl.ordinal()].getPosition().getCoordOnMatrixI(player[pl.ordinal()].getPosition().getPosY())][player[pl.ordinal()].getPosition().getCoordOnMatrixJ(player[pl.ordinal()].getPosition().getPosX())]==Tiles.DiningRoom 
		|| board.getBoardMatrix()[player[pl.ordinal()].getPosition().getCoordOnMatrixI(player[pl.ordinal()].getPosition().getPosY())][player[pl.ordinal()].getPosition().getCoordOnMatrixJ(player[pl.ordinal()].getPosition().getPosX())]==Tiles.Hall 
		|| board.getBoardMatrix()[player[pl.ordinal()].getPosition().getCoordOnMatrixI(player[pl.ordinal()].getPosition().getPosY())][player[pl.ordinal()].getPosition().getCoordOnMatrixJ(player[pl.ordinal()].getPosition().getPosX())]==Tiles.Kitchen
		|| board.getBoardMatrix()[player[pl.ordinal()].getPosition().getCoordOnMatrixI(player[pl.ordinal()].getPosition().getPosY())][player[pl.ordinal()].getPosition().getCoordOnMatrixJ(player[pl.ordinal()].getPosition().getPosX())]==Tiles.Library 
		|| board.getBoardMatrix()[player[pl.ordinal()].getPosition().getCoordOnMatrixI(player[pl.ordinal()].getPosition().getPosY())][player[pl.ordinal()].getPosition().getCoordOnMatrixJ(player[pl.ordinal()].getPosition().getPosX())]==Tiles.Lounge 
		|| board.getBoardMatrix()[player[pl.ordinal()].getPosition().getCoordOnMatrixI(player[pl.ordinal()].getPosition().getPosY())][player[pl.ordinal()].getPosition().getCoordOnMatrixJ(player[pl.ordinal()].getPosition().getPosX())]==Tiles.Study
		|| board.getBoardMatrix()[player[pl.ordinal()].getPosition().getCoordOnMatrixI(player[pl.ordinal()].getPosition().getPosY())][player[pl.ordinal()].getPosition().getCoordOnMatrixJ(player[pl.ordinal()].getPosition().getPosX())]==Tiles.BillardRoom)
			return true;
		else
			return false;
	}

	//Metodo responsavel por dar repaint no tabuleiro
	public void Notify()
	{
		BoardPanel.getInstance().updateBoardPanel();
	}
	
	//Metodo responsavel por mostrar mensagem
	public void showMessage(int showCode)
	{
		BoardPanel.getInstance().alertMessage(showCode);
	}
	
	//Metodo que adiciona clique do mouse
	public void setClick(Position mouse)
	{
		mouseClick=mouse;
	}
	
	//Metdodo que retorna o modulo de uma conta
	private int module(int x, int x2){
		if(x-x2<0)
			return x2-x;
		else
			return x-x2;
	}
}
