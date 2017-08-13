/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package model;

import java.util.Random;

public class Dice{

	//Variavel utilizada para implementar design pattern SINGLETON
	private static Dice firstInstance = null;
	
	//Class SINGLETON
	public static Dice getInstance(){
		if(firstInstance == null)
			firstInstance = new Dice();
		return firstInstance;
	}

	public Dice() {}
	
	//Metodo para retornar valor de dado aleatorio entre 1 e 6
	public int roll() 
	{
		Random rand = new Random();
		int roll = rand.nextInt(6) + 1;
		return roll;
	}
}
