/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package view;

import java.awt.*;
import java.awt.image.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DicePanel extends JPanel {

	//Variavel utilizada para implementar design pattern SINGLETON
	private static DicePanel firstInstance = null;

	//Variavel utilizada para carregar todas as imagens de dados possíveis e guarda-las em um vetor
	BufferedImage[] diceImages = new BufferedImage[7];

	//Variavel utilizada para represendar dado corrente do jogo
	private int currentRoll = 7;
	
	//Class SINGLETON
	public static DicePanel getInstance(){
		if(firstInstance == null)
				firstInstance = new DicePanel();			
		return firstInstance;
	}
	
	public DicePanel() {

		//Formatacao da localizacao, tamanho do Card
		this.setBounds(730, 575, 275, 125);

		//Formatacao do layout do Card
		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		//Criacao de tooltip para mouse event
		this.setToolTipText("Dice Roll");

		//Iteracao para carregar imagens dos dados
		for (int i = 0; i < 7; i++) 
		{
			//Variavel para determinar o numero do dado
			int diceNum = i + 1;
			try 
			{
				//Carregando os dados com variacao de i em dice"i".pgn file
				this.diceImages[i] = ImageIO.read(new File("img/dice/dice" + diceNum + ".png"));
			} 
			catch (IOException e) 
			{
				//Printando excecao levantada caso ocorra alguma
				e.printStackTrace();
			}
		}
	}

	//Metodo responsavel por desenhar e redesenhar a imagem do dado
	public void displayRoll(int roll) 
	{
		this.currentRoll = roll;
		this.repaint();
	}

	@Override 
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(this.diceImages[this.currentRoll - 1], 80, 10, null);
	}

}
