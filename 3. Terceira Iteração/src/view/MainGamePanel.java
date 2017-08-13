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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
 class MainGamePanel  extends JPanel {

	JButton moveButton;	
	JButton accuseButton;
	JButton suggestButton;

	private JPanel Dice;
	private JPanel Board;
	private JPanel playerOptionPanel;
	private JPanel playerStatusPanel;
		
	private BufferedImage Background;

	private static MainGamePanel firstInstance = null;
	
	public static MainGamePanel getInstance()
	{
		if(firstInstance == null)
				firstInstance = new MainGamePanel();
		return firstInstance;
	}
	
	public MainGamePanel() {}
	
	public MainGamePanel(JPanel contentPane) 
	{
		try 
		{
			this.Background = ImageIO.read(new File("img/backboard.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		this.setLayout(null);
		this.Board = new BoardPanel(this);
		this.playerStatusPanel = new PlayerStatusPanel();
		this.Dice = new DicePanel();
		
		this.add(this.Board);
		this.add(this.playerStatusPanel);
		this.add(this.Dice);
		
		this.setUpHUDPanel();
		
		((BoardPanel) this.Board).update();
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(Background, 0, 0, null);
	}
	
	
	public void setUpHUDPanel() 
	{
		this.playerOptionPanel = new JPanel(null);
		this.playerOptionPanel.setBounds(730, 315, 275, 235);
		this.playerOptionPanel.setOpaque(false);

		this.moveButton = new JButton("JOGAR O DADO");
		this.moveButton.setBounds(45, 25, 175, 45);
		this.moveButton.setActionCommand("MOVE");
		this.moveButton.setMnemonic(KeyEvent.VK_M);
		this.moveButton.setToolTipText("Cique aqui para jogar o dado e realizar o movimento");
		this.moveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				if(!MVBridge.isTimeToThrow()){
					BoardPanel.getInstance().sendMessage(5);
					return;
				}
				int roll=MVBridge.rollDice();
				((DicePanel) Dice).displayRoll(roll);
    			MVBridge.setMoveSize(roll);
			}
		});
		
		this.suggestButton = new JButton("REALIZAR PALPITE");
		this.suggestButton.setBounds(45, 90, 175, 45);
		this.suggestButton.setMnemonic(KeyEvent.VK_P);
		this.suggestButton.setEnabled(true);
		this.suggestButton.setToolTipText("Clique aqui para realizar sugestao");
		this.suggestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(MVBridge.isPlayerInRoom()) 
					if(MVBridge.isTimeToGuess())
						new GuessFrame("SUGGEST");
					else
						JOptionPane.showMessageDialog(null, "Voce ja realizou um palpite!");
				else
					JOptionPane.showMessageDialog(null, "Voce nao esta em um comodo!");
			}
		});

		this.accuseButton = new JButton("ACUSAR");
		this.accuseButton.setBounds(45, 160, 175, 45);
		this.accuseButton.setMnemonic(KeyEvent.VK_A);
		this.accuseButton.setEnabled(false);
		this.accuseButton.setToolTipText("Clique para fazer acusacao. OBS: Acusacaoo incorreta podera te eliminar do jogo!");
		this.accuseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new GuessFrame("ACCUSE");
			}
		});

		this.playerOptionPanel.add(this.moveButton);
		this.playerOptionPanel.add(this.suggestButton);
		this.playerOptionPanel.add(this.accuseButton);

		this.add(this.playerOptionPanel);
	}
}