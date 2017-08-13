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
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
class MenuPanel extends JPanel {
	
	CardLayout cardLayout;

	private JPanel contentPane;

	private BufferedImage Background;

	private static MenuPanel firstInstance = null;

	public static MenuPanel getInstance()
	{
		if(firstInstance == null)
				firstInstance = new MenuPanel();
			
		return firstInstance;
	}
		
	public MenuPanel() {}
		
	public MenuPanel(JPanel contentPane) 
	{
		super.setLayout(new BorderLayout());
		this.contentPane = contentPane;

		try 
		{
			this.Background = ImageIO.read(new File("img/background.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		this.createButtons();
	}

	public void createButtons() 
	{
		JPanel painel = new JPanel();
		
		Icon imgb1 = new ImageIcon("img/btnplaygame.png");
		Icon imgb2 = new ImageIcon("img/btnloadgame.png");
		Icon imgb3 = new ImageIcon("img/btnexit.png");

		JButton b1 = new JButton(imgb1);
		JButton b2 = new JButton(imgb2);
		JButton b3 = new JButton(imgb3);
		
		painel.setLayout(null);
		
		b1.setOpaque(true);
		b1.setBorderPainted(false);
		b1.setContentAreaFilled(false);

		b2.setOpaque(true);
		b2.setBorderPainted(false);
		b2.setContentAreaFilled(false);

		b3.setOpaque(true);
		b3.setBorderPainted(false);
		b3.setContentAreaFilled(false);

		b1.setBounds(650, 320, 160, 45);
		b2.setBounds(650, 380, 160, 45);
		b3.setBounds(650, 440, 160, 45);

		b1.setToolTipText("Clique aqui para iniciar um novo jogo.");
		b2.setToolTipText("Clique aqui para continuar uma partida.");
		b3.setToolTipText("Clique aqui para sair do jogo.");

		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent event) 
			{
				cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);
			}
		});
		
		b2.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent event) 
			{
				JFileChooser filech = new JFileChooser();
				filech.showOpenDialog(b2);
			}
		});
		
		b3.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent event) 
			{
				System.exit(0);
			}
		});

		painel.add(b1);
		painel.add(b2);
		painel.add(b3);

		painel.setOpaque(false);;
		this.add(painel);
	}

	@Override 
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(this.Background, 0, 0, null);
	}
}