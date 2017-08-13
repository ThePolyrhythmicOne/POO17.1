/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package view;

import model.Observer;
import model.MVBridge;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
class BoardPanel extends JPanel implements Observer, MouseListener {
	
	private BufferedImage boardImage;
	private BufferedImage inactiveTile;

	private static List<BufferedImage> pawnImage;
	private static List<BufferedImage> weaponImage;
	
	private static BoardPanel firstInstance = null;
	public static BoardPanel getInstance()
	{
		if(firstInstance == null)
				firstInstance = new BoardPanel();
		return firstInstance;
	}
	
	public BoardPanel() {}

	public BoardPanel(MainGamePanel mainPanel) 
	{
		this.setLayout(null);
		this.addMouseListener(this);
		this.setBounds(8, 8, 700, 700);
		this.setBackground(Color.darkGray);
		MVBridge.registerObserver(this,'M');

		try 
		{
			this.boardImage = ImageIO.read(new File("img/board.png"));
			this.inactiveTile = ImageIO.read(new File("img/inactive_player_tile.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void sendMessage(int code)
	{
		if(code == 1)
			JOptionPane.showMessageDialog(null, "Movimento Invalido! Selecione a casa de acordo com o dado.");
		if(code == 2)
			JOptionPane.showMessageDialog(null, "Movimento Invalido!");
		if(code == 3)
			JOptionPane.showMessageDialog(null, "Movimento Invalido! Clique dentro do tabuleiro.");
		if(code==4)
			JOptionPane.showMessageDialog(null, "Voce ja realizou um movimento!");
		if(code==5)
			JOptionPane.showMessageDialog(null, "Voce ja jogou o dado!");
	}
	
	public static void loadPawn()
	{
		pawnImage  = new ArrayList<BufferedImage>(MVBridge.getPlayerListLength());
		weaponImage= new ArrayList<BufferedImage>(6);
		
		for(int i=0; i<6; i++)
		{
			try
			{
				weaponImage.add(i,ImageIO.read(new File("img/weapon_tokens/"+MVBridge.getWeaponName(i)+".png")));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}	
			if(MVBridge.getPlayerList().get(i)!=null)
			{
				try
				{
					pawnImage.add(i,ImageIO.read(new File("img/player_tokens/"+MVBridge.getPlayerName(i)+"_token.png")));
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}			
			}
		}
	}
	
	@Override 
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(this.boardImage, 0, 0, 700, 700, null);
		
		for(int i=0;i<6;i++)
		{
			if(MVBridge.getPlayerList().get(i)!=null && MVBridge.isPlayerSelected(i))
				g.drawImage(pawnImage.get(i), MVBridge.getPlayerPosX(i), MVBridge.getPlayerPosY(i), 25, 25, null);
			else
			{
				g.drawImage(pawnImage.get(i), MVBridge.getPlayerPosX(i), MVBridge.getPlayerPosY(i), 25, 25, null);
				g.drawImage(inactiveTile, MVBridge.getPlayerPosX(i), MVBridge.getPlayerPosY(i), 25, 25, null);
			}
		}

		for(short i=0;i<MVBridge.getWeaponList().size();i++)
			g.drawImage(weaponImage.get(i), MVBridge.getWeaponPosX(MVBridge.getWeapon(i)), MVBridge.getWeaponPosY(MVBridge.getWeapon(i)), 25, 25, null);
		
		revalidate();
	}

	@Override
	public void update() 
	{
		this.repaint();	
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		MVBridge.setClickPosition(e.getX(),e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
