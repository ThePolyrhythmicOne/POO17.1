/***************************************************************************
*  $MCI Módulo de implementação: GameFrame  Módulo desenhador do Frame
*
*  Arquivo gerado:              GameFrame.java
*  Letras identificadoras:      GameFrame
*
*  Projeto: INF 1636 / Jogo do Detetive (CLUE)
*  Gestor:  Professor Ivan Mathias Filho
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS
*
*  $ED Descrição do módulo
*     Módulo responsável por desenhar, formatar e inicializar o Frame principal
*     do jogo do Detetive.
*
***************************************************************************/

package view;

//Lista de import utilizado na implementação
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

//Utilizado para evitar warnings
@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	
	//Variável que representa o Panel principal do frame
	private JPanel contentPane;

	//Variável utilizada pra implementar design pattern SINGLETON
	private static GameFrame firstInstance = null;
	
	//Class SINGLETON
	public static GameFrame getInstance(){
		
		//Caso class não tenha sido criada ainda
		if(firstInstance == null)
				//Retorna uma nova class GameFrame
				firstInstance = new GameFrame();
			
		return firstInstance;
	}
	
	//Construtor Default da Class
	public GameFrame() {
		
		super(); 
		
		//Configurando o nome do cabeçalho do Frame
		this.setTitle("DETETIVE GAME | AUTHORS: LUCAS RODRIGUES & MARCELO RAMOS");

		//Formatando o tamanho do frame
		this.setSize(1024, 768);

		//Evitando que o frame possa ser redimensionado
		this.setResizable(false); 

		//Organizando todos os paineis do jogo 
		this.setUpGamePanels();

		//Permite que consigamos a confirmação do usuário ao tentar fechar o frame
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 

		//Centralizando o frame na tela
		this.setLocationRelativeTo(null);

		//Determinando o painei principal
		this.setContentPane(this.contentPane);
		
		//Adicionando confirmação ao tentar sair do jogo
		this.addWindowListener(new WindowAdapter()
		{
			//Método para tratar evento
			public void windowClosing(WindowEvent arg0) 
			{
				//Variável de opções de resposta do usuário
				String optionButtons[] = { "SIM", "NAO" };

				//Pop-Up responsável por pegar confirmação do usuário
				int PromptResult = JOptionPane.showOptionDialog(null, "Tem certeza que deseja sair do jogo?", "Sair do Detetive?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, optionButtons, optionButtons[1]);
				
				//Caso o usuário deseja realmente sair
				if (PromptResult == 0)
					//Fecha o jogo completamente
					System.exit(0);
			}
		});
		
		//Disponibiliza na tela o frame do jogo
		this.setVisible(true);
	}

	//Método responsável por organizar os painés utilizados no frame
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
