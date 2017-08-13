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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class NotesFrame extends JFrame {
	
	private JPanel notesPanel;
	private BufferedImage notesBackground;

	protected NotesFrame() 
	{
		super(); 
		
		this.setTitle("DETETIVE GAME | NOTEBOOK ANOTATIONS!");

		this.setSize(650, 500);
		this.setResizable(false); 
		this.setLocationRelativeTo(null);

		try 
		{
			this.notesBackground = ImageIO.read(new File("img/notes.jpg"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		this.displayNotes();
		this.setVisible(true);
	}
	
	protected void displayNotes() 
	{
		this.notesPanel = new JPanel(null) {
			public void paintComponent(Graphics g) 
			{
				super.paintComponent(g);
				g.drawImage(notesBackground, 0, 0, null);
				int offsetX = 100 ;
				int offsetY = 50 ;
				for(String c: MVBridge.getAnnotations())
				{
					g.setFont(new Font("Courier", Font.BOLD, 20));
					g.drawString(c, offsetX, offsetY);
					offsetY += 25;
				}
			}
		};
		this.notesPanel.setBackground(new Color(32, 32, 32));
		this.add(this.notesPanel);
	}
}
