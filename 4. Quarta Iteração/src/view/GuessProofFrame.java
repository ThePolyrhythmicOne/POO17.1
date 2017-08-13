/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package view;

import model.MVBridge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
class GuessProofFrame extends JFrame {

	private JPanel radioPanel = new JPanel();
	private BufferedImage cardPanelbackbround;
	
	private JRadioButton a, b, c;
	private JPanel cardsPanel;
	
	private List<String> selectedCards=new ArrayList<String>(3);
	private List<String> selectedCardsAux=new ArrayList<String>(3);
	private List<BufferedImage> currentImg=new ArrayList<BufferedImage>(3);

	private static short auxCounter=0;
	
	private static GuessProofFrame firstInstance = null;
	public static GuessProofFrame getInstance()
	{
		if(firstInstance == null)
				firstInstance = new GuessProofFrame();
		return firstInstance;
	}
	
	protected GuessProofFrame()
	{
		super(); 
		
		boolean aux = false;
		
		for(short i=0;i<MVBridge.getProofCards().size();i++)
			if(MVBridge.getProofCards().iterator().hasNext())
				aux=true;
		if(aux==false)
		{
			MVBridge.setNextRound();
			return;
		}

		this.setTitle("DETETIVE GAME | "+"PLAYER: "+MVBridge.getProofCardsOwners().get(auxCounter) + " ,CHOOSE CARDS");

		this.setSize(648, 403);
		this.setResizable(false); 
		this.setLocationRelativeTo(null);
		
		a = new JRadioButton("Primeira Carta");
		b = new JRadioButton("Segunda Carta");
		c = new JRadioButton("Terceira Carta");
		
		a.setOpaque(false);
		b.setOpaque(false);
		c.setOpaque(false);
		
		a.setForeground(Color.WHITE);
		b.setForeground(Color.WHITE);
		c.setForeground(Color.WHITE);
		
		a.setToolTipText("Selecione a primeira carta!");
		b.setToolTipText("Selecione a segunda carta!");
		c.setToolTipText("Selecione a terceira carta!");

		try 
		{
			this.cardPanelbackbround = ImageIO.read(new File("img/choice_frame.png"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		ButtonGroup group = new ButtonGroup();
		
        group.add(a);
        group.add(b);
        group.add(c);
        group.clearSelection();
        
        
        radioPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 80, 5));
        
		radioPanel.add(a);
		radioPanel.add(b);
		radioPanel.add(c);
		
		
		radioPanel.setOpaque(false);
		radioPanel.setBounds(0 , 300, 800, 50);
		
		this.add(radioPanel);	
		this.loadProofCards();
		this.displayCards();
		this.addRelevantButtons();
		this.setVisible(true);
	}
	
	protected void addRelevantButtons() 
	{
		JPanel south = new JPanel();
		south.setBackground(new Color(32, 32, 32));

		JButton submitButton = new JButton("SUBMIT");
		submitButton.setToolTipText("Enviar carta escolhida!");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				for(short i=0;i< MVBridge.getProofCards().size();i++)
					selectedCardsAux.add( MVBridge.getProofCards().get(i));
				
				if(a.isSelected() && MVBridge.getProofCards().get(0)!=null)
				{
					selectedCards.add(MVBridge.getProofCards().get(0));
					currentImg.remove(0);
					MVBridge.getProofCards().remove(0);
				}
				
				else if(b.isSelected()&& MVBridge.getProofCards().get(1)!=null)
				{
					selectedCards.add(MVBridge.getProofCards().get(1));
					currentImg.remove(1);
					MVBridge.getProofCards().remove(1);
				}
				
				else if(c.isSelected()&&MVBridge.getProofCards().get(2)!=null)
				{
					selectedCards.add(MVBridge.getProofCards().get(2));
					currentImg.remove(2);
					MVBridge.getProofCards().remove(2);
				}
				
				
				for(short j=0;j<currentImg.size();j++)
				{
					currentImg.remove(j);
					MVBridge.getProofCards().remove(j);
				}
				
				
				if(auxCounter==MVBridge.getProofCardsOwners().size())
				{			
					for(String a: selectedCards)
					{
						String s= "Palpite realizado: "+ a +" - falso";
						MVBridge.addAnnotations(s);
					}
					MVBridge.setNextRound();
					dispose();
				}
				else
				{
					loadProofCards();
					repaint();
				}
			}
		});

		JButton cancelButton = null;
		cancelButton = new JButton("CANCEL");		
		cancelButton.setToolTipText("Cancelar escolha de carta!");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				dispose();
			}
		});
		
		south.add(submitButton);
		south.add(cancelButton);
		this.add(south, BorderLayout.SOUTH);
	}
	
	protected void loadProofCards()
	{
		for(short i=0;i<MVBridge.getProofCards().size();i++)
		{
			try 
			{
				currentImg.add(ImageIO.read(new File("img/cards/" + MVBridge.getProofCards().get(i) + ".png")));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}	
			
			if(auxCounter+1==MVBridge.getProofCardsOwners().size()|| MVBridge.getProofCardsOwners().get(auxCounter)!=MVBridge.getProofCardsOwners().get(auxCounter+1))
			{
				auxCounter++;
				break;
			}
			
			auxCounter++;
		}
	}
	
	protected void displayCards() 
	{	
		this.cardsPanel = new JPanel(null) 
		{
			@Override
			public void paintComponent(Graphics g) 
			{
				super.paintComponent(g);
				g.drawImage(cardPanelbackbround, 0, 0, null);
				
      			//Push up offsets
				int offSetX = 42;
				int offSetY = 60;
			
				for(BufferedImage a: currentImg){
						g.drawImage(a, offSetX, offSetY, 155, 230, null);
						offSetX = offSetX + 205;
				}
			}
		};
		this.cardsPanel.setBackground(new Color(32, 32, 32));
		this.add(this.cardsPanel);
	}
}
