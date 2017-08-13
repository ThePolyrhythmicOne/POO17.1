/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package view;

import java.awt.*;

import model.Observer;
import model.Player;
import model.PlayerMovement;
import model.Position;

import java.awt.image.*;

import java.io.*;

import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel implements Observer {
	
	//Vetor para guardar imagens de avatars ativos e inativos
	private static BufferedImage[] pawnImage, inactivePawnImage;

	//Variavel para guardar imagem de highlight vermelho
	private static BufferedImage inactivePlayerGlow;
	
	//Variavel utilizada para carregar imagem do tabuleiro
	private BufferedImage boardImage;

	//Variavel utilizada para implementar design pattern SINGLETON
	private static BoardPanel firstInstance = null;
	
	//Class SINGLETON
	public static BoardPanel getInstance(){
		if(firstInstance == null)
			firstInstance = new BoardPanel();
		return firstInstance;
	}
	
	public BoardPanel() {}

	public BoardPanel(MainGamePanel mainPanel) {

		//Determinando layout do panel
		this.setLayout(null);

		//Criando tratador de evento
		this.addMouseListener(new Position());

		//Configurando tamanho, coordenadas e cor
		this.setBounds(8, 8, 700, 700);
		this.setBackground(Color.darkGray);

		try 
		{
			//Leitura da imagem do tabuleiro presente no source package img
			this.boardImage = ImageIO.read(new File("img/board.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	//Metodo responsavel por repintar o tabuleiro
	public void updateBoardPanel() 
	{
		this.repaint(); 
	}
	
	//Metodo responsavel a acionar pop-up de alerta
	public void alertMessage(int code)
	{
		if(code == 1)
			JOptionPane.showMessageDialog(null, "Movimento Invalido! Selecione a casa de acordo com o dado.");
		if(code == 2)
			JOptionPane.showMessageDialog(null, "Movimento Invalido!");
		if(code == 3)
			JOptionPane.showMessageDialog(null, "Movimento Invalido! Clique dentro do tabuleiro.");
	}
	
	//Metodo responsavel por carregar imagens
	public static void loadPawn(Player[] p)
	{
		//Vetor para guardar imagem de avatar ativo
		pawnImage = new BufferedImage[Player.getPlayerArray().length];

		//Vetor para guardar imagem de avatar inativo
		inactivePawnImage=new BufferedImage[Player.getInactivePlayerArray().length];

		for(int i=0;i<6;i++)
		{
			//Se houver jogador ativo
			if(Player.getPlayerArray()[i]!=null)
			{
				try
				{
					//Carrega imagem
					pawnImage[i]=ImageIO.read(new File("img/player_tokens/"+Player.getPlayerArray()[i].getPlayerName()+"_token.png"));
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}			
			}
			else
			{
				try
				{
					//Carrega imagem inativa e highlight vermelho
					inactivePlayerGlow = ImageIO.read(new File("img/inactive_player_tile.png"));
					inactivePawnImage[i]=ImageIO.read(new File("img/player_tokens/"+Player.getInactivePlayerArray()[i].getPlayerName()+"_token.png"));
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}			
			}
		}
	}

	//Metodo responsavel por chamar um movimento no tabuleiro
	public void callMove(int roll)
	{
		((Position) this.getMouseListeners()[0]).setMoveSize(roll,new PlayerMovement());
	}

	@Override 
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(this.boardImage, 0, 0, 700, 700, null);
		for(int i=0;i<6;i++)
		{
			if(Player.getPlayerArray()[i]!=null)
				g.drawImage(pawnImage[i], Player.getPlayerArray()[i].getPosition().getPosX(), Player.getPlayerArray()[i].getPosition().getPosY(), 25, 25, null);
			else
			{
				g.drawImage(inactivePawnImage[i], Player.getInactivePlayerArray()[i].getPosition().getPosX(), Player.getInactivePlayerArray()[i].getPosition().getPosY(), 25, 25, null);
				g.drawImage(inactivePlayerGlow, Player.getInactivePlayerArray()[i].getPosition().getPosX(), Player.getInactivePlayerArray()[i].getPosition().getPosY(), 25, 25, null);
			}
		}
		revalidate();
		repaint();
	}
}
