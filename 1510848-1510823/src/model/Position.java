/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package model;

 class Position {

	@SuppressWarnings("unused")
	private int posI,posX;

	@SuppressWarnings("unused")
	private int posJ,posY;

	protected Position(int posX, int posY) 
	{
		this.posX = posX;
		this.posY = posY;
		this.posI=getCoordOnMatrixI(posY);
		this.posJ=getCoordOnMatrixJ(posX);
	}

	protected Position() {}

	protected int getPosX() 
	{
		return this.posX;
	}

	protected int getPosY() 
	{
		return this.posY;
	}
	
	protected void clickPosition(int posX,int posY)
	{
		PlayerMovement.getInstance().setClick(this);
		PlayerMovement.getInstance().movePlayer(Round.getNextPlayer(Player.getPlayerArray()));
	}

	protected int getCoordOnMatrixI(int Ycoord)
	{
		return (int)((float)Ycoord/25-1.2);
	}

	protected int getCoordOnMatrixJ(int Xcoord)
	{
		return (int)((float)Xcoord/25-1.75);
	}

}