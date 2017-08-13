/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package view;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
 class DicePanel extends JPanel {
	
	private int currentRoll = 7;
	BufferedImage[] diceImages = new BufferedImage[7];

	private static DicePanel firstInstance = null;

	protected static DicePanel getInstance()
	{
		if(firstInstance == null)
				firstInstance = new DicePanel();			
		return firstInstance;
	}
	
	protected DicePanel() 
	{
		this.setBounds(730, 575, 275, 125);
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setToolTipText("Dice Roll");

		for (int i=0; i<7; i++) 
		{
			int diceNum = i + 1;
			try 
			{
				this.diceImages[i] = ImageIO.read(new File("img/dice/dice" + diceNum + ".png"));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	protected void displayRoll(int roll) 
	{
		this.currentRoll = roll;
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(this.diceImages[this.currentRoll - 1], 80, 10, null);
	}
}
