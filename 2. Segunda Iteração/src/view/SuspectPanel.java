/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package view;

import model.Player;
import model.PlayerNum;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class SuspectPanel extends JPanel{
	
	//Variavel responsavel na implementacao do design pattern SINGLETON
	private static SuspectPanel firstInstance = null;

	//Variavel de leitura para capa de fundo do menu2
	private BufferedImage Background;

	//Criacao do painel responsavel pela apresentacao da selecao dos personagens
	private JPanel contentPane;

	//Variavel para dinamizar CardLayout do frame
	CardLayout cardLayout;

	//Class SINGLETON
	public static SuspectPanel getInstance() { 		
		if(firstInstance == null)
			firstInstance = new SuspectPanel();
		return firstInstance;
	}
	
	public SuspectPanel() {}
	
	public SuspectPanel(JPanel contentPane) {
		
		//Envia ao super a informacao de retirar o manejo de layout dos paineis
		super.setLayout(null);

		//Atribuicao do painel principal
		this.contentPane = contentPane;

		try 
		{
			//Leitura da imagem que representa os personagens para selecao
			this.Background = ImageIO.read(new File("img/suspectselection.jpg"));
		} 
		catch (IOException e) 
		{
			//Printar na tela quaisquer excecoes que a leitura pode gerar
			e.printStackTrace();
		}

		//Chamada para metodo que cria selecao
		this.createSelection();
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(Background, 0, 0, null);
	}

	//Metodo que cria a selecao dos personagens, com a criacao dos mesmos
	public void createSelection() {

		//Criacao do painel para personagens de cima
		JPanel selectionPanelup = new JPanel();

		//Personalizacao de tamanho, layout, alinhamento e opacidade do painel para personagens de cima
		selectionPanelup.setBounds(18, 350, 710, 250);
		selectionPanelup.setLayout(new GridLayout(1, 3, 10, 50));
		selectionPanelup.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		selectionPanelup.setOpaque(false);
		
		//Criacao do painel para personagens de cima
		JPanel selectionPaneldown = new JPanel();

		//Personalizacao de tamanho, layout, alinhamento e opacidade do painel para personagens de cima
		selectionPaneldown.setBounds(20, 660, 710, 250);
		selectionPaneldown.setLayout(new GridLayout(1, 3, 10, 50));
		selectionPaneldown.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		selectionPaneldown.setOpaque(false);



		/*    MR. LIFSCHITZ    */


		//Criacao de painel proprio
		JPanel MrLifschitz = new JPanel();

		//Tirar opacidade do painel
		MrLifschitz.setOpaque(false);

		//Criacao do checkbox
		JCheckBox MrLifschitzBox = new JCheckBox("Mr. Lifschitz", false);

		//Formatacao do checkbox e da letra do checkbox
		MrLifschitzBox.setBackground(Color.BLACK);
		MrLifschitzBox.setForeground(Color.WHITE);
		MrLifschitzBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adicao do checkbox ao painel do personagem
		MrLifschitz.add(MrLifschitzBox);



		/*    MR. MATHIAS    */


		//Criacao de painel proprio
		JPanel MrMathias = new JPanel();

		//Tirar opacidade do painel
		MrMathias.setOpaque(false);

		//Criacao do checkbox
		JCheckBox MrMathiasBox = new JCheckBox("Mr. Mathias", false);

		//Formatacao do checkbox e da letra do checkbox
		MrMathiasBox.setBackground(Color.BLACK);
		MrMathiasBox.setForeground(Color.WHITE);
		MrMathiasBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adicao do checkbox ao painel do personagem
		MrMathias.add(MrMathiasBox);



		/*    MR. ENDLER    */


		//Criacao de painel proprio
		JPanel MrEndler = new JPanel();

		//Tirar opacidade do painel
		MrEndler.setOpaque(false);

		//Criacao do checkbox
		JCheckBox MrEndlerBox = new JCheckBox("Mr. Endler", false);

		//Formatacao do checkbox e da letra do checkbox
		MrEndlerBox.setBackground(Color.BLACK);
		MrEndlerBox.setForeground(Color.WHITE);
		MrEndlerBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adicao do checkbox ao painel do personagem
		MrEndler.add(MrEndlerBox);



		/*    MR. RODRIGUEZ    */


		//Criacao de painel proprio
		JPanel MrsRodriguez = new JPanel();

		//Tirar opacidade do painel
		MrsRodriguez.setOpaque(false);

		//Criacao do checkbox
		JCheckBox MrsRodriguezBox = new JCheckBox("Mrs. Rodriguez", false);

		//Formatacao do checkbox e da letra do checkbox
		MrsRodriguezBox.setBackground(Color.BLACK);
		MrsRodriguezBox.setForeground(Color.WHITE);
		MrsRodriguezBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adicao do checkbox ao painel do personagem
		MrsRodriguez.add(MrsRodriguezBox);



		/*    MR. GATTASS    */


		//Criacao de painel proprio
		JPanel MrGattass = new JPanel();

		//Tirar opacidade do painel
		MrGattass.setOpaque(false);

		//Criacao do checkbox
		JCheckBox MrGattassBox = new JCheckBox("Mr. Gattass", false);

		//Formatacao do checkbox e da letra do checkbox
		MrGattassBox.setBackground(Color.BLACK);
		MrGattassBox.setForeground(Color.WHITE);
		MrGattassBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adicao do checkbox ao painel do personagem
		MrGattass.add(MrGattassBox);



		/*    MR. SEIBEL    */


		//Criacao de painel proprio
		JPanel MrSeibel = new JPanel();

		//Tirar opacidade do painel
		MrSeibel.setOpaque(false);

		//Criacao do checkbox
		JCheckBox MrSeibelBox = new JCheckBox("Mr. Seibel", false);

		//Formatacao do checkbox e da letra do checkbox
		MrSeibelBox.setBackground(Color.BLACK);
		MrSeibelBox.setForeground(Color.WHITE);
		MrSeibelBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adicao do checkbox ao painel do personagem
		MrSeibel.add(MrSeibelBox);

		//Adicionar os checkboxes dos personagens superiores ao painel superior
		selectionPanelup.add(MrLifschitz);
		selectionPanelup.add(MrMathias);
		selectionPanelup.add(MrEndler);

		//Adicionar os checkboxes dos personagens inferiores ao painel inferior
		selectionPaneldown.add(MrsRodriguez);
		selectionPaneldown.add(MrGattass);
		selectionPaneldown.add(MrSeibel);

		//Adicionar os paineis ao Card
		this.add(selectionPanelup);
		this.add(selectionPaneldown);

		//Leitura do icone de fundo para botao
		Icon imgb5 = new ImageIcon("img/btnplaygame.png");
		
		//Criacao do botao com icone de fundo
		JButton PlayGameButton = new JButton(imgb5);

		//Adicao de TooltipText para botao
		PlayGameButton.setToolTipText("Clique apos selecionado 3 jogadores para continuar ao jogo...");

		//Adicao de atalho de evento para ENTER
		PlayGameButton.setMnemonic(KeyEvent.VK_ENTER);

		//Tirando o fundo do botao para aparecer somente o icone
		PlayGameButton.setBorderPainted(false);
		PlayGameButton.setContentAreaFilled(false);

		//Determinando a dimensao do botao
		PlayGameButton.setPreferredSize(new Dimension(160, 50));

		//Criacao de ActionListener para tratar evento do clique
		PlayGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) 
			{	

				//Adicao dos jogadores juntamente com a informacao se o mesmo foi selecionado
				int playerCount = 0;
				if(MrLifschitzBox.isSelected())
				{
					Player.addPlayer(PlayerNum.MrLifschitz, MrLifschitzBox.isSelected(), 266, 29);
					playerCount++;
				}
				else
					Player.addInactivePlayer(PlayerNum.MrLifschitz, MrLifschitzBox.isSelected(), 266, 29);
				
				if(MrMathiasBox.isSelected())
				{
					Player.addPlayer(PlayerNum.MrMathias, MrMathiasBox.isSelected(), 392, 28);
					playerCount++;
				}
				else
					Player.addInactivePlayer(PlayerNum.MrMathias, MrMathiasBox.isSelected(), 392, 28);
				
				if(MrEndlerBox.isSelected())
				{
					Player.addPlayer(PlayerNum.MrEndler, MrEndlerBox.isSelected(), 624, 182);
					playerCount++;
				}
				else
					Player.addInactivePlayer(PlayerNum.MrEndler, MrEndlerBox.isSelected(), 624, 182);
				
				if(MrsRodriguezBox.isSelected())
				{
					Player.addPlayer(PlayerNum.MrsRodriguez, MrsRodriguezBox.isSelected(), 42, 456);
					playerCount++;
				}
				else
					Player.addInactivePlayer(PlayerNum.MrsRodriguez, MrsRodriguezBox.isSelected(), 42, 456);
				
				if(MrGattassBox.isSelected())
				{
					Player.addPlayer(PlayerNum.MrGattass, MrGattassBox.isSelected(), 622, 509);
					playerCount++;
				}
				else
					Player.addInactivePlayer(PlayerNum.MrGattass, MrGattassBox.isSelected(), 622, 509);

				if(MrSeibelBox.isSelected())
				{
					Player.addPlayer(PlayerNum.MrSeibel, MrSeibelBox.isSelected(), 219, 636);
					playerCount++;
				}
				else
					Player.addInactivePlayer(PlayerNum.MrSeibel, MrSeibelBox.isSelected(), 219, 636);
				
				//Se menos que 3 jogadores forem selecionados e o botao PlayGameButton foi acionado
				if (playerCount< 3 || playerCount> 6) 
				{
					//Abrir pop-up com mensagem de alerta, e retorna para selecao dos jogadores
					JOptionPane.showMessageDialog(null, "Por favor selecione mais do que 3 jogadores para seguir ao jogo!");
					return;
				}
				
				BoardPanel.loadPawn(Player.getPlayerArray());
				
				
				//Caso contrario segue para o proximo Card do frame
				cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);
			}
		});
		
		//Criacao do painel que contem o botao de PlayGameButton
		JPanel PlayGameButtonPanel = new JPanel();

		//Adicionando o botao ao painel
		PlayGameButtonPanel.add(PlayGameButton);

		//Determinando o tamanho e coordenada do painel
		PlayGameButtonPanel.setBounds(800, 500, 200, 600);

		//Tirando opacidade do painel e deixando-o sem fundo
		PlayGameButtonPanel.setOpaque(false);

		//Adicioando o painel ao frame corrente
		this.add(PlayGameButtonPanel);
	}
}
