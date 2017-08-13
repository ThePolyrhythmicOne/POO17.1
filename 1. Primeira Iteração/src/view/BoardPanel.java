/***************************************************************************
*  $MCI Módulo de implementação: BoardPanel  Módulo desenhador do Card Board
*
*  Arquivo gerado:              BoardPanel.java
*  Letras identificadoras:      BoardPanel
*
*  Projeto: INF 1636 / Jogo do Detetive (CLUE)
*  Gestor:  Professor Ivan Mathias Filho
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS
*
*  $ED Descrição do módulo
*     Módulo responsável pela interface do Card de tabuleiro que será inserido
*     no Frame do Jogo.
*
***************************************************************************/

package view;

//Lista de import utilizados pelo módulo de implementação
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

//Utilizado para evitar warnings
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	//Variável utilizada para carregar imagem do tabuleiro
	private BufferedImage boardImage;

	//Variável utilizada para implementar design pattern SINGLETON
	private static BoardPanel firstInstance = null;
	
	//Class SINGLETON
	public static BoardPanel getInstance(){
		
		//Caso classe não tenha sido criada
		if(firstInstance == null)
				//Retorna uma nova classe BoardPanel
				firstInstance = new BoardPanel();
			
		return firstInstance;
	}
	
	//Construtor default da class
	public BoardPanel() {}

	//Construtor não-default da class recebendo 1 parâmetro
	public BoardPanel(MainGamePanel mainPanel) {
		//Inicialização do Card
		this.setLayout(null);
		this.setBounds(8, 8, 700, 700);
		this.setBackground(Color.darkGray);

		try 
		{
			//Leitura da imagem do tabuleiro presente no source package img
			this.boardImage = ImageIO.read(new File("img/board.png"));
		} 
		catch (IOException e) 
		{
			//Caso ocorra exceção, printar na tela qual exceção ocorreu na leitura
			e.printStackTrace();
		}
	}

	//Class responsável por repintar o tabuleiro após quaisquer ações ocorridas no mesmo
	public void updateBoardPanel() {
		this.repaint();
	}


	@Override //Class sobrescrita responsável por desenhar imagem carregada no Card atual
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.boardImage, 0, 0, 700, 700, null);
	}
}
