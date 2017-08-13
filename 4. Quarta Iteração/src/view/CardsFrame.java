/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package view;

import model.MVBridge;
import model.Observer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class CardsFrame extends JFrame implements Observer {
	
	private JPanel cardsPanel;
	private BufferedImage cardPanelbackbround;

	private static CardsFrame firstInstance = null;
	public static CardsFrame getInstance()
	{
		if(firstInstance == null)
				firstInstance = new CardsFrame();
		return firstInstance;
	}

	protected CardsFrame() 
	{
		super(); 

		this.setTitle("DETETIVE GAME | VIEW PLAYER AND PUBLIC CARDS");

		this.setSize(650, 640);
		this.setResizable(false); 
		this.setLocationRelativeTo(null);

		try 
		{
			this.cardPanelbackbround = ImageIO.read(new File("img/cards_frame.png"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
				
		this.displayCards();
		this.setVisible(true);
	}
	
	protected void displayCards() 
	{

		this.cardsPanel = new JPanel(null) 
		{
			@Override
			public void paintComponent(Graphics g) 
			{
				super.paintComponent(g);
				g.drawImage(cardPanelbackbround, 0, 0, null);
				
      			//Push up offsets
				int offSetX = 42;
				int offSetY = 60;
				
				for (int i=0; i<MVBridge.getPlayerDeckSize(); i++) 
				{
					BufferedImage currentImg = null;
					try 
					{
						currentImg = ImageIO.read(new File("img/cards/" + MVBridge.getCurrentPlayerCards(i) + ".png"));
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}

					g.drawImage(currentImg, offSetX, offSetY, 155, 230, null);
					offSetX = offSetX + 205;

					//Push down offsets.
					if (i == 2) 
					{
						offSetX = 42;
						offSetY = 340;
					}
				}
			}
		};

		this.cardsPanel.setBackground(new Color(32, 32, 32));
		this.add(this.cardsPanel);
	}

	@Override
	public void update() {
		getInstance();
	}

	@Override
	public void sendMessage(int code) {}
}