/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package view;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	
	//Variavel que representa o Painel corrente
	private JPanel contentPane;

	//Variavel utilizada pra SINGLETON
	private static GameFrame firstInstance = null;
	
	//Class SINGLETON
	public static GameFrame getInstance(){
		if(firstInstance == null)
				firstInstance = new GameFrame();		
		return firstInstance;
	}
	
	public GameFrame() {
		
		super(); 
		
		//Configurando o nome do cabecalho do Frame
		this.setTitle("DETETIVE GAME | AUTHORS: LUCAS RODRIGUES & MARCELO RAMOS");

		//Formatando o tamanho do frame
		this.setSize(1024, 768);

		//Evitando que o frame possa ser redimensionado
		this.setResizable(false); 

		//Organizando todos os paineis do jogo 
		this.setUpGamePanels();

		//Permite que consigamos a confirmacao do usuario ao tentar fechar o frame
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 

		//Centralizando o frame na tela
		this.setLocationRelativeTo(null);

		//Determinando o painei principal
		this.setContentPane(this.contentPane);
		
		//Adicionando confirmacao ao tentar sair do jogo
		this.addWindowListener(new WindowAdapter()
		{
			//Metodo para tratar evento
			public void windowClosing(WindowEvent arg0) 
			{
				//Variavel de opcoes de resposta do usuario
				String optionButtons[] = { "SIM", "NAO" };

				//Pop-Up responsavel por pegar confirmacao do usuario
				int PromptResult = JOptionPane.showOptionDialog(null, "Tem certeza que deseja sair do jogo?", "Sair do Detetive?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, optionButtons, optionButtons[1]);
				
				//Caso o usuario deseja realmente sair
				if (PromptResult == 0)
					//Fecha o jogo completamente
					System.exit(0);
			}
		});
		
		//Disponibiliza na tela o frame do jogo
		this.setVisible(true);
	}

	//Metodo responsavel por organizar os paines utilizados no frame
	public void setUpGamePanels() {

		//Criando novo painel
		this.contentPane = new JPanel();
		
		//Determinando layout de paineis como Card
		this.contentPane.setLayout(new CardLayout());

		//Inserindo diferentes Cards no Frame 1 a 1
		contentPane.add(new MenuPanel(contentPane), "MenuPanel");
		contentPane.add(new SuspectPanel(contentPane), "SuspectPanel");
		contentPane.add(new MainGamePanel(contentPane), "BoardPanel");
	}
}
