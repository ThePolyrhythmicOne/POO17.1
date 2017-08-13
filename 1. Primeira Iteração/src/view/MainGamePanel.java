/***************************************************************************
*  $MCI Módulo de implementação: MainGamePanel  Módulo desenhador da pág. principal
*
*  Arquivo gerado:              MainGamePanel.java
*  Letras identificadoras:      MainGamePanel
*
*  Projeto: INF 1636 / Jogo do Detetive (CLUE)
*  Gestor:  Professor Ivan Mathias Filho
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS
*
*  $ED Descrição do módulo
*     Módulo responsável por desenhar a página principal do jogo, que é onde
*     irá ocorrer maior parte das interações.
*
***************************************************************************/

package view;

//Lista de import utilizado na implementação
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

//Utilizado para evitar warnings
@SuppressWarnings("serial")
public class MainGamePanel  extends JPanel {

	//Variável que representa o tabuleiro do jogo
	private JPanel Board;

	//Variável que representa a imagem do dado
	private JPanel Dice;

	//Variável que representa o fundo subdividido entre paineis
	private BufferedImage Background;

	//Variável para implementação de design pattern SINGLETON
	private static MainGamePanel firstInstance = null;
	
	//Class SINGLETON
	public static MainGamePanel getInstance(){
		
		//Caso a Class não tenha sido criada
		if(firstInstance == null)
				//Retorna uma nova Class
				firstInstance = new MainGamePanel();
			
		return firstInstance;
	}

	//Construtor default da Class	
	public MainGamePanel() {}
	
	//Contrutor não-default da Class recebendo 1 parâmetro
	public MainGamePanel(JPanel contentPane) {

		try 
		{
			//Leitura da imagem do fundo texturado inicial do frame
			this.Background = ImageIO.read(new File("img/backboard.png"));
		} 
		catch (IOException e) 
		{
			//Printa na tela a exceção que possa ter ocorrido
			e.printStackTrace();
		}

		//Não utiliza manipulador de layout no painel
		this.setLayout(null);

		//Inclui no painel a imagem do tabuleiro
		this.Board = new BoardPanel(this);

		//Inclui no painel a imagem do dado
		this.Dice = new DicePanel();

		//Adiciona as imagens definitivamente no painel
		this.add(this.Board);
		this.add(this.Dice);
	}
	
	@Override //Método que sobrescrito que pinta imagens no frame
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Background, 0, 0, null);
	}
}
