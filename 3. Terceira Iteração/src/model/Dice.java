/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package model;

import java.util.Random;


 class Dice {
	
	protected static int roll()
	{
		Random rand = new Random();
		int roll = rand.nextInt(6) + 1;
		return roll;
	}
}