/***************************************************************************
*  $MCI Módulo de implementação: MenuPanel  Módulo desenhador do menu
*
*  Arquivo gerado:              MenuPanel.java
*  Letras identificadoras:      MenuPanel
*
*  Projeto: INF 1636 / Jogo do Detetive (CLUE)
*  Gestor:  Professor Ivan Mathias Filho
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS
*
*  $ED Descrição do módulo
*     Módulo responsável por desenhar o menu principal do jogo.
*
***************************************************************************/

package view;

//Lista de import utilizado na implementação
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

//Utilizado para evitar warnings
@SuppressWarnings("serial")
public class MenuPanel extends JPanel{
	
	//Variável responsável na implementação do design pattern SINGLETON
	private static MenuPanel firstInstance = null;

	//Variável que representa a capa do jogo no menu principal.
	private BufferedImage Background;

	//Variável que representa o painel principal do menu
	private JPanel contentPane;

	//Variável que irá dinamizar o sistemas de Card
	CardLayout cardLayout;
	
	//Class SINGLETON
	public static MenuPanel getInstance(){
		
		//Caso a Class não tenha sido criada
		if(firstInstance == null)
				//Retorna uma nova Class do tipo MenuPanel
				firstInstance = new MenuPanel();
			
		return firstInstance;
	}
		
	//Contrutor default da Class
	public MenuPanel() {}
		
	//Construtor não-default da Class recebendo 1 parâmetro
	public MenuPanel(JPanel contentPane) {
		
		//Criação de layout com borda
		super.setLayout(new BorderLayout());

		//Atribuição de painel
		this.contentPane = contentPane;

		try 
		{
			//Leitura da imagem referente à capa do menu
			this.Background = ImageIO.read(new File("img/background.png"));
		} 
		catch (IOException e) 
		{
			//Printar na tela quaisquer exceção que possa ocorrer
			e.printStackTrace();
		}

		//Chamada ao método que irá criar os botões do jogo e tratá-los
		this.createButtons();
	}

	//Método para criar o layout de botões, colocar imagens
	public void createButtons() {

		//Configuração dos ícones dos botões como imagens
		Icon imgb1 = new ImageIcon("img/btnplaygame.png");
		Icon imgb2 = new ImageIcon("img/btnloadgame.png");
		Icon imgb3 = new ImageIcon("img/btnexit.png");

		//Criação de Painel
		JPanel painel = new JPanel();

		//Criação dos botões com as imagens
		JButton b1 = new JButton(imgb1);
		JButton b2 = new JButton(imgb2);
		JButton b3 = new JButton(imgb3);
		
		//Determinando layout livre pra manejar
		painel.setLayout(null);
		
		//Tirar fundo dos botãos criados
		b1.setOpaque(true);
		b1.setBorderPainted(false);
		b1.setContentAreaFilled(false);

		b2.setOpaque(true);
		b2.setBorderPainted(false);
		b2.setContentAreaFilled(false);

		b3.setOpaque(true);
		b3.setBorderPainted(false);
		b3.setContentAreaFilled(false);

		//Determinar tamanho e localização dos botãos
		b1.setBounds(650, 320, 160, 45);
		b2.setBounds(650, 380, 160, 45);
		b3.setBounds(650, 440, 160, 45);

		//Criação de TooltipText para cada botão
		b1.setToolTipText("Clique aqui para iniciar um novo jogo.");
		b2.setToolTipText("Clique aqui para continuar uma partida.");
		b3.setToolTipText("Clique aqui para sair do jogo.");

		//Criação no botão de inicar jogo o atalho de apertar ENTER
		b1.setMnemonic(KeyEvent.VK_ENTER);
		
		//Implementação de tratador para botão 
		b1.addActionListener(new ActionListener() {

			//Método de tratador do evento
			public void actionPerformed (ActionEvent event) 
			{
				//Mudança pra o próximo Card no frame 
				cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);
			}
		});
		
		//Implementação de tratador para botão 
		b2.addActionListener(new ActionListener() { 

			//Método de tratador do evento
			public void actionPerformed(ActionEvent event) 
			{
				//Abrir selecionador de arquivo para carregar novo jogo
				JFileChooser filech = new JFileChooser();
				filech.showOpenDialog(b2);
			}
		});
		
		//Implementação de tratador para botão 
		b3.addActionListener(new ActionListener() { 

			//Método de tratador do evento
			public void actionPerformed(ActionEvent event) 
			{
				//Fecha o programa
				System.exit(0);
			}
		});

		//Adição dos botãos ao painel do menu
		painel.add(b1);
		painel.add(b2);
		painel.add(b3);

		//Tornando o painel sem fundo
		painel.setOpaque(false);;

		//Adicionando o painel ao frame
		this.add(painel);
	}

	@Override //Método sobrescrito para pintar capa do menu na tela
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.Background, 0, 0, null);
	}
}