/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package model;

public class GameBoard {

	//Variavel que representa matriz
	private Tiles BoardMatrix[][]=new Tiles[25][24];

	//Variavel utilizada para implementar design pattern SINGLETON
	private static GameBoard firstInstance = null;
	
	//Class SINGLETON
	public static GameBoard getInstance(){
		if(firstInstance == null)
			firstInstance = new GameBoard();
		return firstInstance;
	}
	
	public GameBoard(){
		
		for(short i=0;i<25;i++)
			for(short j=0;j<24;j++)
				BoardMatrix[i][j]=Tiles.GameFloor;

		for(short i=0;i<25;i++)
		{
			for(short j=0;j<24;j++)
			{
				//Primeira linha da matriz
				if(i==0)
				{
					//Posicao de inicio
					if(j==9 || j==14)
						BoardMatrix[i][j]=Tiles.Starter;
					else
						BoardMatrix[i][j]=Tiles.GameFloor;
				}
				
				//Buracos da matriz
				if(i==1 && (j==6 || j==17))
						BoardMatrix[i][j]=Tiles.Invalid;
				
				//Ballroom PART1
				else if(i==1 && (j>=10 && j<=13))
					BoardMatrix[i][j]=Tiles.Ballroom;

				//Ballroom PART2
				else if((i>=2 && i<=7) && (j>=8 && j<=15))
				{
					BoardMatrix[i][j]=Tiles.Ballroom; 			
					if(i==5 && j==15)
						BoardMatrix[i][j]=Tiles.BallroomDoorR;
					if(i==7 && j==9)
						BoardMatrix[i][j]=Tiles.BallroomDoorL;
				}
				
				//Kitchen
				else if((i>=1 && i<=6) && (j>=0 && j<=5))
				{
						BoardMatrix[i][j]=Tiles.Kitchen; 
						if(i==1 && j==4)
							BoardMatrix[i][j]=Tiles.SecretPassageK2S;
				
						if(i==6 && j ==4)
							BoardMatrix[i][j]=Tiles.KitchenDoor;
				}
				
				//Buracos da matriz
				else if(i==8 && j==0)
					BoardMatrix[i][j]=Tiles.GameFloor;

				//Conservatory PART1
				else if((i>=1 && i<=4) && (j>=18 && j<=23))
					BoardMatrix[i][j]=Tiles.Conservatory; 

				//Conservatory PART2
				else if(i==5 && (j>=19 && j<=23))
				{
					BoardMatrix[i][j]=Tiles.Conservatory;
					if(i==5 && j==19)
						BoardMatrix[i][j]=Tiles.ConservatoryDoor;
					if(i==5 && j==22)
						BoardMatrix[i][j]=Tiles.SecretPassageC2L;
				}
				
				//Ponto de inicio
				else if(j==23 && i==6)
					BoardMatrix[i][j]=Tiles.Starter;

				//Buracos da matriz
				else if(j==23 && i==7)
					BoardMatrix[i][j]=Tiles.Invalid;
				
				//Dinning Room PART1
				else if(i==9 && (j>=0 && j<=4)) 
					BoardMatrix[i][j]=Tiles.DiningRoom;

				//Dinning Room PART2
				else if((i>=10 && i<=15) && (j>=0 && j<=7)) 
				{
					BoardMatrix[i][j]=Tiles.DiningRoom;
					if(i==15 && j==6)
						BoardMatrix[i][j]=Tiles.DiningRoomDoor;
				}
				
				//Ponto de inicio
				else if(i==17 && j==0)
					BoardMatrix[i][j]=Tiles.Starter; 	

				//Buracos da matriz
				else if((i==16 || i==18) && j==0) 
					BoardMatrix[i][j]=Tiles.Invalid;

				//CLUEDO
				else if((i>=10 && i<=16) && (j>=10 && j<=14)) 
					BoardMatrix[i][j]=Tiles.Invalid;
				
				//Billard Room
				else if((i>=8 && i<=12) && (j>=18 && j<=23))
				{
					BoardMatrix[i][j]=Tiles.BillardRoom;
					if(i==9 && j==18)
						BoardMatrix[i][j]=Tiles.BillardRoomDoor;
				}
				
				//Library PART1
				else if((i>=14 && i<=18) && (j>=18 && j<=23)) 
						BoardMatrix[i][j]=Tiles.Library;

				//Library PART2
				else if(j==17 && (i>=15 && i<=17))
				{
					BoardMatrix[i][j]=Tiles.Library;
					if(i==16 && j==17)
						BoardMatrix[i][j]=Tiles.LibraryDoor;
				}
				
				//Ponto de inicio
				else if(i==19 && j==23) 
					BoardMatrix[i][j]=Tiles.Starter; 

				//Buracos da matriz
				else if(i==20 && j==23)
					BoardMatrix[i][j]=Tiles.Invalid; 

				//Lounge
				else if((i>=19 && i<=24) && (j>=0 && j<=6)) 
				{
					BoardMatrix[i][j]=Tiles.Lounge; 
					if(i==19&&j==6)
						BoardMatrix[i][j]=Tiles.LoungeDoor;
					if(i==20 && j==1)
						BoardMatrix[i][j]=Tiles.SecretPassageL2C;
				}
				
				//Ponto de inicio
				else if(i==24 && j==7) 
					BoardMatrix[i][j]=Tiles.Starter;

				//Buracos da matriz
				else if(i==24 && j==8) 
					BoardMatrix[i][j]=Tiles.GameFloor;

				//HALL
				else if((i>=18 && i<=24) && (j>=9 && j<=14))
				{
					BoardMatrix[i][j]=Tiles.Hall;
					if(i==18 && j==12)
						BoardMatrix[i][j]=Tiles.HallDoor;
				}
			
				//Buracos da matriz
				else if(i==24 && j==15)
					BoardMatrix[i][j]=Tiles.Invalid;
				
				//Study
				else if((i>=21 && i<=24) && (j>=17 && j<=23))
				{
					BoardMatrix[i][j]=Tiles.Study;
					if(i==21 && j==17)
						BoardMatrix[i][j]=Tiles.StudyDoor;
					if(i==22 && j==22)
						BoardMatrix[i][j]=Tiles.SecretPassageS2K;
				}
				
			}	
		}
	}

	//Metodo responsavel por pegar matriz
	public Tiles[][] getBoardMatrix()
	{
		return BoardMatrix;
	}
}
