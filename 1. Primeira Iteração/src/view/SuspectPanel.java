/***************************************************************************
*  $MCI Módulo de implementação: SuspectPanel  Módulo desenhador da seleção
*                                              dos personagens do jogo
*
*  Arquivo gerado:              SuspectPanel.java
*  Letras identificadoras:      SuspectPanel
*
*  Projeto: INF 1636 / Jogo do Detetive (CLUE)
*  Gestor:  Professor Ivan Mathias Filho
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS
*
*  $ED Descrição do módulo
*     Módulo responsável por desenhar o menu de seleção dos personagens do
*     jogo, ou seja, no mínimo devem ser selecionados 3 personagens para se
*     iniciar o jogo.
*
***************************************************************************/

package view;

//Importando o model para criação de jogadores
import model.Player;

//Lista de import utilizado na implementação
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.*;
import javax.swing.*;

//Utilizado para evitar warnings
@SuppressWarnings("serial")
public class SuspectPanel extends JPanel{
	
	//Variável responsável na implementação do design pattern SINGLETON
	private static SuspectPanel firstInstance = null;

	//Variável de leitura para capa de fundo do menu2
	private BufferedImage Background;

	//Criação do painel responsável pela apresentação da seleção dos personagens
	private JPanel contentPane;

	//Variável para dinamizar CardLayout do frame
	CardLayout cardLayout;

	//Class SINGLETON
	public static SuspectPanel getInstance() {
		
		//Caso a Class não tenha sido criada 		
		if(firstInstance == null)
				//Retorna uma nova Class
				firstInstance = new SuspectPanel();
		
		return firstInstance;
	}
	
	//Construtor default da Class
	public SuspectPanel() {}
	
	//Construtor não-default da Class que recebe 1 parâmetro
	public SuspectPanel(JPanel contentPane) {
		
		//Envia ao super a informação de retirar o manejo de layout dos paineis
		super.setLayout(null);

		//Atribuição do painel principal
		this.contentPane = contentPane;

		try 
		{
			//Leitura da imagem que representa os personagens para seleção
			this.Background = ImageIO.read(new File("img/suspectselection.jpg"));
		} 
		catch (IOException e) 
		{
			//Printar na tela quaisquer exceções que a leitura pode gerar
			e.printStackTrace();
		}

		//Chamada para método que cria seleção
		this.createSelection();
	}

	@Override //Class sobrescrita para pintar na tela a imagem da capa
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(Background, 0, 0, null);
	}

	//Método que cria a seleção dos personagens, com a criação dos mesmos
	public void createSelection() {

		//Criação do painel para personagens de cima
		JPanel selectionPanelup = new JPanel();

		//Personalização de tamanho, layout, alinhamento e opacidade do painel para personagens de cima
		selectionPanelup.setBounds(18, 350, 710, 250);
		selectionPanelup.setLayout(new GridLayout(1, 3, 10, 50));
		selectionPanelup.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		selectionPanelup.setOpaque(false);
		
		//Criação do painel para personagens de cima
		JPanel selectionPaneldown = new JPanel();

		//Personalização de tamanho, layout, alinhamento e opacidade do painel para personagens de cima
		selectionPaneldown.setBounds(20, 660, 710, 250);
		selectionPaneldown.setLayout(new GridLayout(1, 3, 10, 50));
		selectionPaneldown.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		selectionPaneldown.setOpaque(false);



		/*    MR. LIFSCHITZ    */


		//Criação de painel próprio
		JPanel MrLifschitz = new JPanel();

		//Tirar opacidade do painel
		MrLifschitz.setOpaque(false);

		//Criação do checkbox
		JCheckBox MrLifschitzBox = new JCheckBox("Mr. Lifschitz", false);

		//Formatação do checkbox e da letra do checkbox
		MrLifschitzBox.setBackground(new Color(51, 50, 50));
		MrLifschitzBox.setForeground(Color.WHITE);
		MrLifschitzBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adição do checkbox ao painel do personagem
		MrLifschitz.add(MrLifschitzBox);



		/*    MR. MATHIAS    */


		//Criação de painel próprio
		JPanel MrMathias = new JPanel();

		//Tirar opacidade do painel
		MrMathias.setOpaque(false);

		//Criação do checkbox
		JCheckBox MrMathiasBox = new JCheckBox("Mr. Mathias", false);

		//Formatação do checkbox e da letra do checkbox
		MrMathiasBox.setBackground(new Color(51, 50, 50));
		MrMathiasBox.setForeground(Color.WHITE);
		MrMathiasBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adição do checkbox ao painel do personagem
		MrMathias.add(MrMathiasBox);



		/*    MR. ENDLER    */


		//Criação de painel próprio
		JPanel MrEndler = new JPanel();

		//Tirar opacidade do painel
		MrEndler.setOpaque(false);

		//Criação do checkbox
		JCheckBox MrEndlerBox = new JCheckBox("Mr. Endler", false);

		//Formatação do checkbox e da letra do checkbox
		MrEndlerBox.setBackground(new Color(51, 50, 50));
		MrEndlerBox.setForeground(Color.WHITE);
		MrEndlerBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adição do checkbox ao painel do personagem
		MrEndler.add(MrEndlerBox);



		/*    MR. RODRIGUEZ    */


		//Criação de painel próprio
		JPanel MrsRodriguez = new JPanel();

		//Tirar opacidade do painel
		MrsRodriguez.setOpaque(false);

		//Criação do checkbox
		JCheckBox MrsRodriguezBox = new JCheckBox("Mrs. Rodriguez", false);

		//Formatação do checkbox e da letra do checkbox
		MrsRodriguezBox.setBackground(new Color(51, 50, 50));
		MrsRodriguezBox.setForeground(Color.WHITE);
		MrsRodriguezBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adição do checkbox ao painel do personagem
		MrsRodriguez.add(MrsRodriguezBox);



		/*    MR. GATTASS    */


		//Criação de painel próprio
		JPanel MrGattass = new JPanel();

		//Tirar opacidade do painel
		MrGattass.setOpaque(false);

		//Criação do checkbox
		JCheckBox MrGattassBox = new JCheckBox("Mr. Gattass", false);

		//Formatação do checkbox e da letra do checkbox
		MrGattassBox.setBackground(new Color(51, 50, 50));
		MrGattassBox.setForeground(Color.WHITE);
		MrGattassBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adição do checkbox ao painel do personagem
		MrGattass.add(MrGattassBox);



		/*    MR. SEIBEL    */


		//Criação de painel próprio
		JPanel MrSeibel = new JPanel();

		//Tirar opacidade do painel
		MrSeibel.setOpaque(false);

		//Criação do checkbox
		JCheckBox MrSeibelBox = new JCheckBox("Mr. Seibel", false);

		//Formatação do checkbox e da letra do checkbox
		MrSeibelBox.setBackground(new Color(51, 50, 50));
		MrSeibelBox.setForeground(Color.WHITE);
		MrSeibelBox.setFont(new Font("Courier", Font.ITALIC, 16));

		//Adição do checkbox ao painel do personagem
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

		//Leitura do ícone de fundo para botão
		Icon imgb5 = new ImageIcon("img/btnplaygame.png");

		//Criação do botão com ícone de fundo
		JButton PlayGameButton = new JButton(imgb5);

		//Adição de TooltipText para botão
		PlayGameButton.setToolTipText("Clique após selecionado 3 jogadores para continuar ao jogo...");

		//Adição de atalho de evento para ENTER
		PlayGameButton.setMnemonic(KeyEvent.VK_ENTER);

		//Tirando o fundo do botão para aparecer somente o ícone
		PlayGameButton.setBorderPainted(false);
		PlayGameButton.setContentAreaFilled(false);

		//Determinando a dimensão do botão
		PlayGameButton.setPreferredSize(new Dimension(160, 50));

		//Criação de ActionListener para tratar evento do clique
		PlayGameButton.addActionListener(new ActionListener() {

			@Override //Método sobrescrito para tratar ação
			public void actionPerformed(ActionEvent event) 
			{
				//Variável que representa uma lista de jogadores
				List<Player> player = new ArrayList<>();

				//Adição dos jogadores juntamente com a informação se o mesmo foi selecionado
				player.add(new Player( "MrLifschitz" , MrLifschitzBox.isSelected()   ));
				player.add(new Player( "MrMathias"   , MrMathiasBox.isSelected()     ));
				player.add(new Player( "MrEndler"    , MrEndlerBox.isSelected()      ));
				player.add(new Player( "MrsRodriguez", MrsRodriguezBox.isSelected()  ));
				player.add(new Player( "MrGattass"   , MrGattassBox.isSelected()     ));
				player.add(new Player( "MrSeibel"    , MrSeibelBox.isSelected()      ));

				//Variável para contabilizar quantos foram selecionados
				int playerCount = 0;

				//Iteração para percorrer lista
				for (Player p : player) 
					//Caso o jogador foi selecionado
					if (p.isSelected())
						//Contabiliza a variável
						playerCount++;

				//Se menos que 3 jogadores forem selecionados e o botão PlayGameButton foi acionado
				if (playerCount < 3 || playerCount > 6) 
				{
					//Abrir pop-up com mensagem de alerta, e retorna para seleção dos jogadores
					JOptionPane.showMessageDialog(null, "Por favor selecione mais do que 3 jogadores para seguir ao jogo!");
					return;
				}

				//Caso contrário segue para o próximo Card do frame
				cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);
			}
		});
		
		//Criação do painel que contém o botão de PlayGameButton
		JPanel PlayGameButtonPanel = new JPanel();

		//Adicionando o botão ao painel
		PlayGameButtonPanel.add(PlayGameButton);

		//Determinando o tamanho e coordenada do painel
		PlayGameButtonPanel.setBounds(800, 500, 150, 600);

		//Tirando opacidade do painel e deixando-o sem fundo
		PlayGameButtonPanel.setOpaque(false);

		//Adicioando o painel ao frame corrente
		this.add(PlayGameButtonPanel);
	}
}
