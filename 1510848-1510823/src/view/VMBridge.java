/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package view;

public abstract class VMBridge {
	
	public static void loadDice(short roll)
	{
		DicePanel.getInstance().displayRoll(roll);
	}
	
	public static void loadImages()
	{
		BoardPanel.loadPawn();
		PlayerStatusPanel.loadAvatars();
	}
	
	public static void loadBoard()
	{
		MainGamePanel.getInstance();
	}
	
	public static void setGame()
	{
		MenuPanel.setGameUp();
	}
}
