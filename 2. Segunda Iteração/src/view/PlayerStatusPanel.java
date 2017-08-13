/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package view;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PlayerStatusPanel extends JPanel {

	//Variavel para determinar a rodada da vez
	JTextField nameField;

	//Botao para link de vizualizacao de cartas
	JButton viewCardsButton;

	//Variavel responsavel na implementacao do design pattern SINGLETON
	private static PlayerStatusPanel firstInstance = null;

	//Variaveis para dar load e guardar imagens
	private BufferedImage[] playerAvatars = new BufferedImage[6];
	private BufferedImage   defaultAvatar;

	//Class SINGLETON
	public static PlayerStatusPanel getInstance() { 		
		if(firstInstance == null)
			firstInstance = new PlayerStatusPanel();
		return firstInstance;
	}

	public PlayerStatusPanel() {

		//Determinando layout nulo do panel
		this.setLayout(null);

		//Tirando opacidade do panel para aparecer itens atras
		this.setOpaque(false);

		//Determinando o tamanho e coordenadas do panel
		this.setBounds(730, 0, 275, 300);

		//Determinando nome do panel
		this.nameField = new JTextField("DETETIVE - RODADA DA VEZ");

		//Configurando alinhamento e tamanho do nome
		this.nameField.setHorizontalAlignment(SwingConstants.CENTER);
		this.nameField.setBounds(22, 25, 230, 30);

		//Determinando nome editavel para caso de Game Over
		this.nameField.setEditable(false);

		//Adicionando titulo no panel
		this.add(this.nameField);

		//Criacao, formatacao e adicao de botao referente as cartas
		this.viewCardsButton = new JButton("VER AS CARTAS");
		this.viewCardsButton.setBounds(47, 240, 175, 40);
		this.viewCardsButton.setMnemonic(KeyEvent.VK_V);
		this.viewCardsButton.setToolTipText("Clique aqui para ver as cartas do jogador...");
		this.add(this.viewCardsButton);
	}
	
	//Metodo responsavel por carregar imagens usadas
	public void loadAvatars() 
	{
		for (int i = 0; i < 6; i++) 
		{
			//Variavel para determinar imagem corrente
			int current = i + 1;
			
			try 
			{
				//Carregando os avatars dos jogadores
				this.playerAvatars[i] = ImageIO.read(new File("img/players_avatars/" + current + ".png"));
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try 
		{
			//Carregando avatar default
			this.defaultAvatar = ImageIO.read(new File("img/players_avatars/1.png"));
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
			g.drawImage(this.defaultAvatar, 57, 70, 155, 155, null);
	}
}
