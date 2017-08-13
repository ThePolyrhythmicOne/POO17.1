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
import model.PlayerNum;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
 class SuspectPanel extends JPanel {
	
	CardLayout cardLayout;
	
	private JPanel contentPane;
	private BufferedImage Background;

	private static SuspectPanel firstInstance = null;
	
	public static SuspectPanel getInstance() 
	{ 		
		if(firstInstance == null)
			firstInstance = new SuspectPanel();

		return firstInstance;
	}
	
	public SuspectPanel() {}
	
	public SuspectPanel(JPanel contentPane) 
	{
		super.setLayout(null);
		this.contentPane = contentPane;

		try 
		{
			this.Background = ImageIO.read(new File("img/suspectselection.jpg"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		this.createSelection();
	}
	
	@Override 
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(Background, 0, 0, null);
	}

	public void createSelection() 
	{
		JPanel selectionPanelup = new JPanel();
		selectionPanelup.setBounds(18, 350, 710, 250);
		selectionPanelup.setLayout(new GridLayout(1, 3, 10, 50));
		selectionPanelup.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		selectionPanelup.setOpaque(false);

		JPanel selectionPaneldown = new JPanel();
		selectionPaneldown.setBounds(20, 660, 710, 250);
		selectionPaneldown.setLayout(new GridLayout(1, 3, 10, 50));
		selectionPaneldown.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		selectionPaneldown.setOpaque(false);


		/*    MR. LIFSCHITZ    */


		JPanel MrLifschitz = new JPanel();
		MrLifschitz.setOpaque(false);
		JCheckBox MrLifschitzBox = new JCheckBox("Mr. Lifschitz", false);
		MrLifschitzBox.setBackground(Color.BLACK);
		MrLifschitzBox.setForeground(Color.WHITE);
		MrLifschitzBox.setFont(new Font("Courier", Font.ITALIC, 16));
		MrLifschitz.add(MrLifschitzBox);


		/*    MR. MATHIAS    */


		JPanel MrMathias = new JPanel();
		MrMathias.setOpaque(false);
		JCheckBox MrMathiasBox = new JCheckBox("Mr. Mathias", false);
		MrMathiasBox.setBackground(Color.BLACK);
		MrMathiasBox.setForeground(Color.WHITE);
		MrMathiasBox.setFont(new Font("Courier", Font.ITALIC, 16));
		MrMathias.add(MrMathiasBox);


		/*    MR. ENDLER    */


		JPanel MrEndler = new JPanel();
		MrEndler.setOpaque(false);
		JCheckBox MrEndlerBox = new JCheckBox("Mr. Endler", false);
		MrEndlerBox.setBackground(Color.BLACK);
		MrEndlerBox.setForeground(Color.WHITE);
		MrEndlerBox.setFont(new Font("Courier", Font.ITALIC, 16));
		MrEndler.add(MrEndlerBox);


		/*    MR. RODRIGUEZ    */


		JPanel MrsRodriguez = new JPanel();
		MrsRodriguez.setOpaque(false);
		JCheckBox MrsRodriguezBox = new JCheckBox("Mrs. Rodriguez", false);
		MrsRodriguezBox.setBackground(Color.BLACK);
		MrsRodriguezBox.setForeground(Color.WHITE);
		MrsRodriguezBox.setFont(new Font("Courier", Font.ITALIC, 16));
		MrsRodriguez.add(MrsRodriguezBox);


		/*    MR. GATTASS    */


		JPanel MrGattass = new JPanel();
		MrGattass.setOpaque(false);
		JCheckBox MrGattassBox = new JCheckBox("Mr. Gattass", false);
		MrGattassBox.setBackground(Color.BLACK);
		MrGattassBox.setForeground(Color.WHITE);
		MrGattassBox.setFont(new Font("Courier", Font.ITALIC, 16));
		MrGattass.add(MrGattassBox);


		/*    MR. SEIBEL    */


		JPanel MrSeibel = new JPanel();
		MrSeibel.setOpaque(false);
		JCheckBox MrSeibelBox = new JCheckBox("Mr. Seibel", false);
		MrSeibelBox.setBackground(Color.BLACK);
		MrSeibelBox.setForeground(Color.WHITE);
		MrSeibelBox.setFont(new Font("Courier", Font.ITALIC, 16));
		MrSeibel.add(MrSeibelBox);


		selectionPanelup.add(MrLifschitz);
		selectionPanelup.add(MrMathias);
		selectionPanelup.add(MrEndler);
		selectionPaneldown.add(MrsRodriguez);
		selectionPaneldown.add(MrGattass);
		selectionPaneldown.add(MrSeibel);

		this.add(selectionPanelup);
		this.add(selectionPaneldown);

		Icon imgb5 = new ImageIcon("img/btnplaygame.png");
		Icon imgb6 = new ImageIcon("img/btnexit.png");

		JButton PlayGameButton = new JButton(imgb5);
		JButton ExitGameButton = new JButton(imgb6);

		PlayGameButton.setToolTipText("Clique depois de selecionado 3 jogadores para continuar ao jogo...");
		ExitGameButton.setToolTipText("Clique aqui para sair do jogo...");
		PlayGameButton.setBorderPainted(false);
		PlayGameButton.setContentAreaFilled(false);
		ExitGameButton.setBorderPainted(false);
		ExitGameButton.setContentAreaFilled(false);
		PlayGameButton.setPreferredSize(new Dimension(160, 50));
		ExitGameButton.setPreferredSize(new Dimension(160, 50));
		ExitGameButton.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent event) 
			{
				System.exit(0);
			}
		});
		
		PlayGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) 
			{	
				int playerCount = 0;

				if(MrLifschitzBox.isSelected())
					playerCount++;

				if(MrMathiasBox.isSelected())
					playerCount++;

				if(MrEndlerBox.isSelected())
					playerCount++;

				if(MrGattassBox.isSelected())
					playerCount++;

				if(MrSeibelBox.isSelected())
					playerCount++;
				
				if(MrsRodriguezBox.isSelected())
					playerCount++;
				
				if (playerCount< 3 || playerCount> 6) 
				{
					JOptionPane.showMessageDialog(null, "Por favor selecione mais do que 3 jogadores para seguir ao jogo!");
					return;
				}			
				
				if(MrLifschitzBox.isSelected())
					MVBridge.addPlayer(PlayerNum.MrLifschitz, MrLifschitzBox.isSelected(), 270, 29,false,false);
				else
					MVBridge.addPlayer(PlayerNum.MrLifschitz, MrLifschitzBox.isSelected(), 270, 29,false,false);
				
				if(MrMathiasBox.isSelected())
					MVBridge.addPlayer(PlayerNum.MrMathias, MrMathiasBox.isSelected(), 396, 28,false,false);
				else
					MVBridge.addPlayer(PlayerNum.MrMathias, MrMathiasBox.isSelected(), 396, 28,false,false);
				
				if(MrEndlerBox.isSelected())
					MVBridge.addPlayer(PlayerNum.MrEndler, MrEndlerBox.isSelected(), 624, 182,false,false);
				else
					MVBridge.addPlayer(PlayerNum.MrEndler, MrEndlerBox.isSelected(), 624, 182,false,false);

				if(MrGattassBox.isSelected())
					MVBridge.addPlayer(PlayerNum.MrGattass, MrGattassBox.isSelected(), 622, 509,false,false);
				else
					MVBridge.addPlayer(PlayerNum.MrGattass, MrGattassBox.isSelected(), 622, 509,false,false);

				if(MrSeibelBox.isSelected())
					MVBridge.addPlayer(PlayerNum.MrSeibel, MrSeibelBox.isSelected(), 219, 636,false,false);
				else
					MVBridge.addPlayer(PlayerNum.MrSeibel, MrSeibelBox.isSelected(), 219, 636,false,false);
				
				if(MrsRodriguezBox.isSelected())
					MVBridge.addPlayer(PlayerNum.MrsRodriguez, MrsRodriguezBox.isSelected(), 42, 456,false,false);
				else
					MVBridge.addPlayer(PlayerNum.MrsRodriguez, MrsRodriguezBox.isSelected(), 42, 456,false,false);
				
				MVBridge.initRooms();
				MVBridge.startRounds();
				MVBridge.shuffleCards();
				BoardPanel.loadPawn();
				PlayerStatusPanel.loadAvatars();
				
				cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);
			}
		});
		
		JPanel PlayGameButtonPanel = new JPanel();
		JPanel ExitGameButtonPanel = new JPanel();
		PlayGameButtonPanel.add(PlayGameButton);
		ExitGameButtonPanel.add(ExitGameButton);
		PlayGameButtonPanel.setBounds(800, 450, 200, 600);
		ExitGameButtonPanel.setBounds(800, 500, 200, 600);
		PlayGameButtonPanel.setOpaque(false);
		ExitGameButtonPanel.setOpaque(false);
		this.add(PlayGameButtonPanel);
		this.add(ExitGameButtonPanel);
	}
}