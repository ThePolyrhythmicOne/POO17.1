/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.*;

import java.io.*;

import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainGamePanel  extends JPanel {

	//Criacao de botoes de palpite, acusacao e dado
	JButton suggestButton;
	JButton accuseButton;
	JButton moveButton;	
	
	//Criacao dos panels referentes ao tabuleiro e dado
	private JPanel Dice;
	private JPanel Board;

	//Criacao dos panels para conter os botoes de acoes
	private JPanel playerOptionPanel;
	private JPanel playerStatusPanel;
	
	//Variavel para guardar imagem do tabuleiro
	private BufferedImage Background;

	//Variavel responsavel na implementacao do design pattern SINGLETON
	private static MainGamePanel firstInstance = null;
	
	//Class SINGLETON
	public static MainGamePanel getInstance(){
		if(firstInstance == null)
				firstInstance = new MainGamePanel();
		return firstInstance;
	}
	
	public MainGamePanel() {}
	
	public MainGamePanel(JPanel contentPane) {
		
		try 
		{
			//Leitura da imagem do fundo texturado inicial do frame
			this.Background = ImageIO.read(new File("img/backboard.png"));
		} 
		catch (IOException e) 
		{
			//Printa na tela a excecao que possa ter ocorrido
			e.printStackTrace();
		}

		//Não utiliza manipulador de layout no painel
		this.setLayout(null);

		//Inclui no painel a imagem do tabuleiro
		this.Board = new BoardPanel(this);
		
		//Criando panel que guarda avatars
		this.playerStatusPanel = new PlayerStatusPanel();

		//Carregar avatars
		((PlayerStatusPanel) this.playerStatusPanel).loadAvatars();
		
		//Inclui no painel a imagem do dado
		this.Dice = new DicePanel();
		
		//Adiciona as imagens definitivamente no painel
		this.add(this.Board);
		this.add(this.playerStatusPanel);
		this.add(this.Dice);
		
		//Configurando os paineis do frame
		this.setUpHUDPanel();
		
		//Atualizando imagem do tabuleiro
		((BoardPanel) this.Board).updateBoardPanel();
	}
	
	//Metodo responsavel por configurar botoes, etc
	public void setUpHUDPanel() {

		//Criando e configurando painel que apresenta botoes de acoes
		this.playerOptionPanel = new JPanel(null);
		this.playerOptionPanel.setBounds(730, 315, 275, 235);
		this.playerOptionPanel.setOpaque(false);

		//Criando e configurando botao para jogar/manipular dado
		this.moveButton = new JButton("JOGAR O DADO");
		this.moveButton.setBounds(45, 25, 175, 45);
		this.moveButton.setActionCommand("MOVE");
		this.moveButton.setMnemonic(KeyEvent.VK_M);
		this.moveButton.setToolTipText("Cique aqui para jogar o dado e realizar o movimento");

		//Adicionando tratador de evento para clique no botao
		this.moveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				manipulaDice();
			}
		});

		//Criando e configurando botao para realizar palpite
		this.suggestButton = new JButton("REALIZAR PALPITE");
		this.suggestButton.setBounds(45, 90, 175, 45);
		this.suggestButton.setActionCommand("SUGGEST");
		this.suggestButton.setMnemonic(KeyEvent.VK_S);
		this.suggestButton.setEnabled(false);
		this.suggestButton.setToolTipText("Clique aqui para realizar sugestão");

		//Criando e configurando botao para realizar acusacao
		this.accuseButton = new JButton("ACUSAR");
		this.accuseButton.setBounds(45, 160, 175, 45);
		this.accuseButton.setActionCommand("ACCUSE");
		this.accuseButton.setMnemonic(KeyEvent.VK_A);
		this.accuseButton.setToolTipText("Clique para fazer acusacao. OBS: Acusacao incorreta poderá te eliminar do jogo.");

		//Adicionando os botoes corretamente no painel
		this.playerOptionPanel.add(this.moveButton);
		this.playerOptionPanel.add(this.suggestButton);
		this.playerOptionPanel.add(this.accuseButton);

		//Incluindo painel em painel maior
		this.add(this.playerOptionPanel);
	}
	
	//Metodo responsavel por manipular dado
	public void manipulaDice() {

		//Criando frame para selecao do dado
		JFrame frame = new JFrame();

		//Criando botao para enviar escolha do dado
		JButton b = new JButton("Submit");

		//Criando e configurando opcoes de radio button
		ButtonGroup g = new ButtonGroup();
		JRadioButton option1 = new JRadioButton("1");
	    JRadioButton option2 = new JRadioButton("2");
	    JRadioButton option3 = new JRadioButton("3");
	    JRadioButton option4 = new JRadioButton("4");
	    JRadioButton option5 = new JRadioButton("5");
	    JRadioButton option6 = new JRadioButton("6");
	    
	    //Configurando o nome do cabecalho do Frame
	    frame.setTitle("MANIPULAR DADO");
	  		
	    //Formatando o tamanho do frame
	    frame.setSize(450, 300);
	  			  		
	    //Evitando que o frame possa ser redimensionado
	    frame.setResizable(false); 

	    //Fecha frame ao clicar no X
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
	  		
	    //Centralizando o frame na tela
	    frame.setLocationRelativeTo(null);

	  	//Configurando fundo como Preto		  				
	    frame.getContentPane().setBackground(Color.BLACK);
	  			  		
	  	//Configurando texto do radio button
	    option1.setForeground(Color.WHITE);
	    option1.setFont(new Font("Courier", Font.ITALIC, 16));
	    option2.setForeground(Color.WHITE);
	    option2.setFont(new Font("Courier", Font.ITALIC, 16));
	    option3.setForeground(Color.WHITE);
	    option3.setFont(new Font("Courier", Font.ITALIC, 16));
	    option4.setForeground(Color.WHITE);
	    option4.setFont(new Font("Courier", Font.ITALIC, 16));
	    option5.setForeground(Color.WHITE);
	    option5.setFont(new Font("Courier", Font.ITALIC, 16));
	    option6.setForeground(Color.WHITE);
	    option6.setFont(new Font("Courier", Font.ITALIC, 16));
	    
	    //Adicionando tips no botao de envio de escolha
	    b.setToolTipText("Cique aqui para definir dado e realizar movimento");

	    //Realizacao de tratador de evento
	    b.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent event) 
	    	{
	    		if (option1.isSelected())
	    		{
	    			((DicePanel) Dice).displayRoll(1);
	    			((BoardPanel) Board).callMove(1);
	    			frame.dispose();
	    		}
	    		if (option2.isSelected())
	    		{
	    			((DicePanel) Dice).displayRoll(2);
	    			((BoardPanel) Board).callMove(2);;
	    			frame.dispose();
	    		}
	    		if (option3.isSelected())
	    		{
	    			((DicePanel) Dice).displayRoll(3);
	    			((BoardPanel) Board).callMove(3);
	    			frame.dispose();
	    		}
	    		if (option4.isSelected())
	    		{
	    			((DicePanel) Dice).displayRoll(4);
	    			((BoardPanel) Board).callMove(4);
	    			frame.dispose();
	    		}
	    		if (option5.isSelected())
	    		{
	    			((DicePanel) Dice).displayRoll(5);
	    			((BoardPanel) Board).callMove(5);
	    			frame.dispose();
	    		}
	    		if (option6.isSelected())
	    		{
	    			((DicePanel) Dice).displayRoll(6);
	    			((BoardPanel) Board).callMove(6);
	    			frame.dispose();
	    		}
	    	}
	    });
	  	
	  	//Adicionando Radio Button no grupo	    
	    g.add(option1);
	    g.add(option2);
	    g.add(option3);
	    g.add(option4);
	    g.add(option5);
	    g.add(option6);
	    
	    //Configurando layout do grupo
	    frame.setLayout(new FlowLayout());
	    
	    //Adicionando botoes no frame
	    frame.add(option1);
	    frame.add(option2);
	    frame.add(option3);
	    frame.add(option4);
	    frame.add(option5);
	    frame.add(option6);
	    
	    //Adicionando botao no frame
	    frame.add(b);    

	    //Tornando frame do tamanho ideal
	    frame.pack();
	    
	    //Mostrando frame na tela
	    frame.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(Background, 0, 0, null);
	}
}
