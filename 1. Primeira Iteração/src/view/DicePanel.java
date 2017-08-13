/***************************************************************************
*  $MCI Módulo de implementação: DicePanel  Módulo desenhador do Card Dice
*
*  Arquivo gerado:              DicePanel.java
*  Letras identificadoras:      DicePanel
*
*  Projeto: INF 1636 / Jogo do Detetive (CLUE)
*  Gestor:  Professor Ivan Mathias Filho
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS
*
*  $ED Descrição do módulo
*     Módulo responsável pela interface do Card de dado que ficará localizado
*     em um espaço Painel reservado na tela do tabuleiro.
*
***************************************************************************/

package view;

//Lista de import utilizadas no módulo de implementação
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Utilizado para evitar warnings
@SuppressWarnings("serial")
public class DicePanel extends JPanel {

	//Variável utilizada para implementar design pattern SINGLETON
	private static DicePanel firstInstance = null;

	//Variável utilizada para carregar todas as imagens de dados possíveis e guardá-las em um vetor
	BufferedImage[] diceImages = new BufferedImage[7];

	//Váriavel utilizada para represendar dado corrente do jogo
	private int currentRoll = 7;
	
	//Class SINGLETON
	public static DicePanel getInstance(){
		
		//Caso Class não tenha sido criada
		if(firstInstance == null)
				//Retorna uma nova class
				firstInstance = new DicePanel();
			
		return firstInstance;
	}
	
	//Construtor default da class
	public DicePanel() {

		//Formatação da localização, tamanho do Card
		this.setBounds(730, 575, 275, 125);

		//Formatação do layout do Card
		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		//Criação de tooltip para mouse event
		this.setToolTipText("Dice Roll");

		//Iteração para carregar imagens dos dados
		for (int i = 0; i < 7; i++) 
		{
			//Variável para determinar o numero do dado
			int diceNum = i + 1;
			try 
			{
				//Carregando os dados com variação de i em dice"i".pgn file
				this.diceImages[i] = ImageIO.read(new File("img/dice/dice" + diceNum + ".png"));
			} 
			catch (IOException e) 
			{
				//Printando exceção levantada caso ocorra alguma
				e.printStackTrace();
			}
		}
	}

	//Método responsável por desenhar e redesenhar a imagem do dado
	public void displayRoll(int roll) 
	{
		this.currentRoll = roll;
		this.repaint();
	}

	@Override //Método sobrescrito responsável por pintar imagem no Card Panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.diceImages[this.currentRoll - 1], 80, 10, null);
	}

}
