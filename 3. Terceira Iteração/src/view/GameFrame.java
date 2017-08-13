/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package view;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

@SuppressWarnings("serial")
 public class GameFrame extends JFrame {
	
	private JPanel contentPane;

	private static GameFrame firstInstance = null;
	
	public static GameFrame getInstance()
	{
		if(firstInstance == null)
				firstInstance = new GameFrame();		
		return firstInstance;
	}
	
	public GameFrame() 
	{
		super(); 
		
		this.setTitle("DETETIVE GAME | AUTHORS: LUCAS RODRIGUES & MARCELO RAMOS");

		this.setSize(1024, 768);
		this.setResizable(false); 
		this.setUpGamePanels();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		this.setLocationRelativeTo(null);
		this.setContentPane(this.contentPane);
		
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent arg0) 
			{
				String optionButtons[] = { "SIM", "NAO" };
				int PromptResult = JOptionPane.showOptionDialog(null, "Tem certeza que deseja sair do jogo?", "Sair do Detetive?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, optionButtons, optionButtons[1]);
				if (PromptResult == 0)
					System.exit(0);
			}
		});

		this.setVisible(true);
	}

	public void setUpGamePanels() 
	{
		this.contentPane = new JPanel();
		this.contentPane.setLayout(new CardLayout());

		contentPane.add(new MenuPanel(contentPane), "MenuPanel");
		contentPane.add(new SuspectPanel(contentPane), "SuspectPanel");
		contentPane.add(new MainGamePanel(contentPane), "BoardPanel");
	}
}