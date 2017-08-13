/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package model;

 class GameBoard {
	
	private Tiles BoardMatrix[][] = new Tiles[25][24];
	
	public GameBoard() {
		
		for(short i=0;i<25;i++)
			for(short j=0;j<24;j++)
				BoardMatrix[i][j]=Tiles.GameFloor;
		
		for(short i=0;i<25;i++)
		{
			for(short j=0;j<24;j++)
			{
				if(i==0) //first matrix line
				{
					if(j==9 || j==14)
						BoardMatrix[i][j]=Tiles.Starter; //starting position
					else
						BoardMatrix[i][j]=Tiles.GameFloor;
				}
				
				if(i==1 && (j==6 || j==17)) //buraco
						BoardMatrix[i][j]=Tiles.Invalid;
				//ballroom part 1
				else if(i==1 && (j>=10 && j<=13)) //ballroom part 1
					BoardMatrix[i][j]=Tiles.Ballroom;
				
				else if((i>=2 && i<=7) && (j>=8 && j<=15)) //ballroom part 2
				{
					BoardMatrix[i][j]=Tiles.Ballroom; 			
					if(i==5 && j==15)
						BoardMatrix[i][j]=Tiles.BallroomDoorR;
					if(i==7 && j==9)
						BoardMatrix[i][j]=Tiles.BallroomDoorL;
				}
				
			
				else if((i>=1 && i<=6) && (j>=0 && j<=5)) //kitchen
				{
						BoardMatrix[i][j]=Tiles.Kitchen; 
						if(i==1 && j==4)
							BoardMatrix[i][j]=Tiles.SecretPassageK2S;
				
						if(i==6 && j ==4)
							BoardMatrix[i][j]=Tiles.KitchenDoor;
				}
				
				else if(i==8 && j==0) //buraco
					BoardMatrix[i][j]=Tiles.GameFloor;
				
				else if((i>=1 && i<=4) && (j>=18 && j<=23)) //conservatory part1
					BoardMatrix[i][j]=Tiles.Conservatory; 
				else if(i==5 && (j>=19 && j<=23)) //conservatory part2
				{
					BoardMatrix[i][j]=Tiles.Conservatory;
					if(i==5 && j==19)
						BoardMatrix[i][j]=Tiles.ConservatoryDoor;
					if(i==5 && j==22)
						BoardMatrix[i][j]=Tiles.SecretPassageC2L;
				}
				
				else if(j==23 && i==6) //starting point
					BoardMatrix[i][j]=Tiles.Starter;
				else if(j==23 && i==7) //buraco
					BoardMatrix[i][j]=Tiles.Invalid;
				
				else if(i==9 && (j>=0 && j<=4)) //dining room part 1
					BoardMatrix[i][j]=Tiles.DiningRoom;
				else if((i>=10 && i<=15) && (j>=0 && j<=7)) //dining room part2
				{
					BoardMatrix[i][j]=Tiles.DiningRoom;
					if(i==15 && j==6)
						BoardMatrix[i][j]=Tiles.DiningRoomDoor;
				}
				
				else if(i==17 && j==0) //starting point
					BoardMatrix[i][j]=Tiles.Starter; 	
				else if((i==16 || i==18) && j==0) //buraco
					BoardMatrix[i][j]=Tiles.Invalid; 
				
				else if((i>=10 && i<=16) && (j>=10 && j<=14)) //CLUEDO
					BoardMatrix[i][j]=Tiles.Invalid;
				
				else if((i>=8 && i<=12) && (j>=18 && j<=23)) //billard room
				{
					BoardMatrix[i][j]=Tiles.BilliardRoom;
					if(i==9 && j==18)
						BoardMatrix[i][j]=Tiles.BilliardRoomDoor;
				}
				
				else if((i>=14 && i<=18) && (j>=18 && j<=23)) // library part 1
						BoardMatrix[i][j]=Tiles.Library;
				else if(j==17 && (i>=15 && i<=17)) //library part2
				{
					BoardMatrix[i][j]=Tiles.Library;
					if(i==16 && j==17)
						BoardMatrix[i][j]=Tiles.LibraryDoor;
				}
				
				else if(i==19 && j==23) //starting point
					BoardMatrix[i][j]=Tiles.Starter; 
				else if(i==20 && j==23) //buraco
					BoardMatrix[i][j]=Tiles.Invalid; 
				
				else if((i>=19 && i<=24) && (j>=0 && j<=6)) //lounge
				{
					BoardMatrix[i][j]=Tiles.Lounge; 
					if(i==19&&j==6)
						BoardMatrix[i][j]=Tiles.LoungeDoor;
					if(i==20 && j==1)
						BoardMatrix[i][j]=Tiles.SecretPassageL2C;
				}
				
				else if(i==24 && j==7) //starting point
					BoardMatrix[i][j]=Tiles.Starter;
				else if(i==24 && j==8) //buraco
					BoardMatrix[i][j]=Tiles.GameFloor;
				
				else if((i>=18 && i<=24) && (j>=9 && j<=14)) //hall
				{
					BoardMatrix[i][j]=Tiles.Hall;
					if(i==18 && j==12)
						BoardMatrix[i][j]=Tiles.HallDoor;
				}
			
				else if(i==24 && j==15) //buraco
					BoardMatrix[i][j]=Tiles.Invalid;
				
				else if((i>=21 && i<=24) && (j>=17 && j<=23)) //study
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

	protected Tiles[][] getBoardMatrix()
	{
		return BoardMatrix;
	}
}