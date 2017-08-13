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

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
class PlayerStatusPanel extends JPanel {

	JButton notesButton;
	JButton viewCardsButton;

	JTextField nameField;
	
	private static BufferedImage defaultAvatar;
	private static PlayerStatusPanel firstInstance = null;

	public static PlayerStatusPanel getInstance()
	{
		if(firstInstance == null)
				firstInstance = new PlayerStatusPanel();
		return firstInstance;
	}
		
	public PlayerStatusPanel() 
	{
		this.setLayout(null);
		this.setOpaque(false);
		this.setBounds(730, 0, 275, 300);

		this.nameField = new JTextField("DETETIVE - RODADA DA VEZ");
		this.nameField.setHorizontalAlignment(SwingConstants.CENTER);
		this.nameField.setBounds(22, 25, 230, 30);
		this.nameField.setEditable(false);
		this.add(this.nameField);
		
		this.notesButton = new JButton("NOTAS");
		this.notesButton.setBounds(15, 240, 120, 40);
		this.notesButton.setMnemonic(KeyEvent.VK_B);
		this.add(this.notesButton);
		this.notesButton.setToolTipText("Clique aqui para ver o bloco de notas do jogador...");
		this.notesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				new NotesFrame();	
			}
		});

		this.viewCardsButton = new JButton("VER CARTAS");
		this.viewCardsButton.setBounds(140, 240, 120, 40);
		this.viewCardsButton.setMnemonic(KeyEvent.VK_V);
		this.add(this.viewCardsButton);
		this.viewCardsButton.setToolTipText("Clique aqui para ver as cartas do jogador...");
		this.viewCardsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				new CardsFrame();				
			}
		});
	}

	protected static void loadAvatars() 
	{
		try 
		{
			defaultAvatar = ImageIO.read(new File("img/players_avatars/"+ MVBridge.getCurrentPlayerName() + ".png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		loadAvatars();
		g.drawImage(defaultAvatar, 57, 70, 155, 155, null);
		repaint();
	}
}
