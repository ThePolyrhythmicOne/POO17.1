/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package model;

import java.awt.event.*;

public class Position extends MouseAdapter {

	//Variavel de posI de X
	@SuppressWarnings("unused")
	private int posI,posX;

	//Vareiavel de posJ de Y
	@SuppressWarnings("unused")
	private int posJ,posY;

	//Variavel que representa dado
	private int roll;

	//Variavel que determina movimento
	private PlayerMovement movement;

	//Variavel utilizada para implementar design pattern SINGLETON
	private static Position firstInstance = null;
	
	//Class SINGLETON
	public static Position getInstance(){
		if(firstInstance == null)
			firstInstance = new Position();
		return firstInstance;
	}

	public Position() {}

	public Position(int posX, int posY) {

		//Guardando posicao X e Y
		this.posX = posX;
		this.posY = posY;

		//Posicao referente na matriz
		this.posI=getCoordOnMatrixI(posY);
		this.posJ=getCoordOnMatrixJ(posX);
	}

	//Metodo que retona posX
	public int getPosX() {
		return posX;
	}

	//Metodo que retona posY
	public int getPosY() {
		return posY;
	}
	
	//Metodo que trata evento de clique
	public void mouseClicked(MouseEvent e)
	{
		if(roll==0)
			return;

		posX=e.getX(); 
		posY=e.getY();

		posI=getCoordOnMatrixI(posY);
		posJ=getCoordOnMatrixJ(posX);

		movement.setClick(this);
		movement.movePlayer(roll, PlayerNum.MrLifschitz);
	}
	
	//Metodo que determina o tamanho do movimento
	public void setMoveSize(int roll,PlayerMovement move)
	{
		this.roll=roll;
		movement=move;
	}

	//Metodo que retona coordenada referente na matriz
	public int getCoordOnMatrixI(int Ycoord){
		return (int)((float)Ycoord/25-1.2);
	}

	//Metodo que retona coordenada referente na matriz
	public int getCoordOnMatrixJ(int Xcoord){
		return (int)((float)Xcoord/25-1.75);
	}
}
