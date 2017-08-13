/***************************************************************************
*
*  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
*  Gestor:  Professor Ivan Mathias Filho
*  Autores: Lucas Rodrigues - 1510848
*           Marcelo Ramos   - 1510823
*
***************************************************************************/

package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import model.MVBridge;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
class ResultFrame extends JFrame implements WindowListener {
	
	private JPanel contentPane;
	private BufferedImage winnerBackground;
	
	public ResultFrame(String winnerName) 
	{
		super(); 
		
		this.setTitle("DETETIVE GAME | CONGRATULATIONS !! YOU WIN THE GAME...");

		this.setSize(1024, 768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		try 
		{
			this.winnerBackground = ImageIO.read(new File("img/winners/winner_background_" + winnerName + ".png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		this.displayMessage();
		this.setVisible(true);
	}

	protected void displayMessage() 
	{
		this.contentPane = new JPanel(null) {
			public void paintComponent(Graphics g) 
			{
				super.paintComponent(g);
				g.drawImage(winnerBackground, 0, 0, null);
			}
		};
		this.contentPane.setBackground(new Color(32, 32, 32));
		this.add(this.contentPane);
	}
	
	@Override
	public void windowClosing(WindowEvent we) 
	{
		this.dispose();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) 
	{
		MVBridge.saveGame();
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}