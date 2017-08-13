/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;

import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel{
	
	//Variavel responsavel na implementacao do design pattern SINGLETON
	private static MenuPanel firstInstance = null;

	//Variavel que representa a capa do jogo no menu principal.
	private BufferedImage Background;

	//Variavel que representa o painel principal do menu
	private JPanel contentPane;

	//Variavel que ira dinamizar o sistemas de Card
	CardLayout cardLayout;
	
	//Class SINGLETON
	public static MenuPanel getInstance(){
		if(firstInstance == null)
				firstInstance = new MenuPanel();
		return firstInstance;
	}
		
	public MenuPanel() {}
		
	public MenuPanel(JPanel contentPane) {
		
		//Criacao de layout com borda
		super.setLayout(new BorderLayout());

		//Atribuicao de painel
		this.contentPane = contentPane;

		try 
		{
			//Leitura da imagem referente à capa do menu
			this.Background = ImageIO.read(new File("img/background.png"));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Chamada ao metodo que ira criar os botoes do jogo e trata-los
		this.createButtons();
	}

	public void createButtons() {

		//Configuracao dos icones dos botoes como imagens
		Icon imgb1 = new ImageIcon("img/btnplaygame.png");
		Icon imgb2 = new ImageIcon("img/btnloadgame.png");
		Icon imgb3 = new ImageIcon("img/btnexit.png");

		//Criacao de Painel
		JPanel painel = new JPanel();

		//Criacao dos botoes com as imagens
		JButton b1 = new JButton(imgb1);
		JButton b2 = new JButton(imgb2);
		JButton b3 = new JButton(imgb3);
		
		//Determinando layout livre pra manejar
		painel.setLayout(null);
		
		//Tirar fundo dos botaos criados
		b1.setOpaque(true);
		b1.setBorderPainted(false);
		b1.setContentAreaFilled(false);

		b2.setOpaque(true);
		b2.setBorderPainted(false);
		b2.setContentAreaFilled(false);

		b3.setOpaque(true);
		b3.setBorderPainted(false);
		b3.setContentAreaFilled(false);

		//Determinar tamanho e localizacao dos botaos
		b1.setBounds(650, 320, 160, 45);
		b2.setBounds(650, 380, 160, 45);
		b3.setBounds(650, 440, 160, 45);

		//Criacao de TooltipText para cada botao
		b1.setToolTipText("Clique aqui para iniciar um novo jogo.");
		b2.setToolTipText("Clique aqui para continuar uma partida.");
		b3.setToolTipText("Clique aqui para sair do jogo.");

		//Criacao no botao de inicar jogo o atalho de apertar ENTER
		b1.setMnemonic(KeyEvent.VK_ENTER);
		
		//Implementacao de tratador para botao 
		b1.addActionListener(new ActionListener() {

			//Metodo de tratador do evento
			public void actionPerformed (ActionEvent event) 
			{
				//Mudança pra o proximo Card no frame 
				cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);
			}
		});
		
		//Implementacao de tratador para botao 
		b2.addActionListener(new ActionListener() { 

			//Metodo de tratador do evento
			public void actionPerformed(ActionEvent event) 
			{
				//Abrir selecionador de arquivo para carregar novo jogo
				JFileChooser filech = new JFileChooser();
				filech.showOpenDialog(b2);
			}
		});
		
		//Implementacao de tratador para botao 
		b3.addActionListener(new ActionListener() { 

			//Metodo de tratador do evento
			public void actionPerformed(ActionEvent event) 
			{
				//Fecha o programa
				System.exit(0);
			}
		});

		//Adicao dos botaos ao painel do menu
		painel.add(b1);
		painel.add(b2);
		painel.add(b3);

		//Tornando o painel sem fundo
		painel.setOpaque(false);;

		//Adicionando o painel ao frame
		this.add(painel);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.Background, 0, 0, null);
	}
}