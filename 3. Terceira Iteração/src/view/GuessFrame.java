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
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
 class GuessFrame extends JDialog implements WindowListener {
	
	private JRadioButton[] roomButtons; 
	private JRadioButton[] characterButtons; 
	private JRadioButton[] weaponButtons; 
	
	private List<String> selectedNames=new ArrayList<String>(3);
	
	private final String characterNames [] = { "MrLifschitz" , "MrMathias", "MrEndler", "MrsRodriguez", "MrGattass", "MrSeibel" };
	private final String weaponNames    [] = { "Candlestick", "Dagger", "LeadPipe", "Revolver", "Rope", "Spanner" };
	private final String roomNames      [] = { "Kitchen", "Ballroom", "Conservatory", "BilliardRoom", "Library", "Study", "Hall", "Lounge", "DiningRoom" };
	
	private BufferedImage accuseBackground;
	private BufferedImage suggestBackground;

	private String option;
	
	@SuppressWarnings("unused")
	private String roomGuess;
	
	@SuppressWarnings("unused")
	private String characterGuess;
	
	@SuppressWarnings("unused")
	private String weaponGuess;

	private static GuessFrame firstInstance = null;
	
	public static GuessFrame getInstance()
	{
		if(firstInstance == null)
				firstInstance = new GuessFrame();		
		return firstInstance;
	}

	public GuessFrame() {}

	protected GuessFrame(String option) 
	{
		this.option = option;

		if (option.equals("ACCUSE")) 
			this.setTitle("DETETIVE GAME | FACA UMA ACUSACAO!");
		else 
			this.setTitle("DETETIVE GAME | REALIZE UM PALPITE!");

		this.setSize(665, 530);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		try 
		{
			this.accuseBackground = ImageIO.read(new File("img/accusation_screen.png"));
			this.suggestBackground = ImageIO.read(new File("img/suggestion_screen.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		this.setUpGuessPanel();
		setVisible(true);
	}

	protected void setUpGuessPanel() 
	{
		JPanel guessPanel = new JPanel(null) 
		{
			@Override
			public void paintComponent(Graphics g) 
			{
				super.paintComponent(g);

				if (option.equals("ACCUSE")) 
					g.drawImage(accuseBackground, 0, 0, null);
				else
					g.drawImage(suggestBackground, 0, 0, null);
			}
		};
		
		JPanel roomChoicePanel = this.createRoomChoicePanel();
		roomChoicePanel.setBounds(23, 105, 180, 287);

		JPanel characterChoicePanel = this.createCharacterChoicePanel();
		characterChoicePanel.setBounds(235, 105, 180, 287);

		JPanel weaponChoicePanel = this.createWeaponChoicePanel();
		weaponChoicePanel.setBounds(445, 105, 180, 287);

		this.add(roomChoicePanel);
		this.add(characterChoicePanel);
		this.add(weaponChoicePanel);
		this.add(guessPanel);
		this.addRelevantButtons();
	}

	protected void addRelevantButtons() 
	{
		JPanel south = new JPanel();
		south.setBackground(new Color(32, 32, 32));

		JButton submitButton = new JButton("SUBMIT");
		submitButton.setToolTipText("Enviar acusação/palpite!");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				if(buttonSelected() == 3)
				{
					MVBridge.makeGuess(selectedNames);
					MVBridge.makeGuessAux(selectedNames);
					dispose();
					new GuessProofFrame();
				}
				else
					JOptionPane.showMessageDialog(null, "Selecione 1 Weapon, 1 Player e 1 Room!");
			}
		});

		JButton cancelButton = null;
		cancelButton = new JButton("CANCEL");		
		cancelButton.setToolTipText("Cancelar acusação/palpite!");
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

	private int buttonSelected()
	{
		short buttonSelected = 0;

		for(short i=0;i<roomButtons.length;i++)
			if(roomButtons[i].isSelected()){
				buttonSelected++;
				selectedNames.add(0,roomNames[i]);
			}

		for(short i=0;i<weaponButtons.length;i++)
			if(weaponButtons[i].isSelected()){
				buttonSelected++;
				selectedNames.add(1,weaponNames[i]);
			}

		for(short i=0;i<characterButtons.length;i++)
			if(characterButtons[i].isSelected()){
				buttonSelected++;
				selectedNames.add(2,characterNames[i]);
			}

		return buttonSelected;
	}

	protected JPanel createRoomChoicePanel()
	{
		roomButtons = new JRadioButton[this.roomNames.length];
		ButtonGroup group = new ButtonGroup();

		for (int i=0; i!=roomNames.length; ++i)
		{
			roomButtons[i] = new JRadioButton(roomNames[i]);
			roomButtons[i].setAction(new AbstractAction(roomNames[i]) {
				public void actionPerformed(ActionEvent e) 
				{
					AbstractButton selectedRadio = (AbstractButton) e.getSource();
					roomGuess = selectedRadio.getText();
				}
			});

			roomButtons[i].setOpaque(false);
			roomButtons[i].setForeground(Color.WHITE);
			group.add(roomButtons[i]);
		}

		JPanel roomChoicePanel = new JPanel(new GridLayout(9, 1));
		
		for (JRadioButton box : roomButtons)
			roomChoicePanel.add(box);

		roomChoicePanel.setOpaque(false);
		return roomChoicePanel;
	}


	protected JPanel createCharacterChoicePanel() 
	{
		characterButtons = new JRadioButton[this.characterNames.length];
		ButtonGroup group = new ButtonGroup();

		for (int i=0; i!=this.characterNames.length; ++i)
		{
			characterButtons[i] = new JRadioButton(this.characterNames[i]);
			characterButtons[i].setAction(new AbstractAction(this.characterNames[i]) {
				public void actionPerformed(ActionEvent e) 
				{
					AbstractButton selectedRadio = (AbstractButton) e.getSource();
					characterGuess = selectedRadio.getText();
				}
			});

			characterButtons[i].setOpaque(false);
			characterButtons[i].setForeground(Color.WHITE);
			group.add(characterButtons[i]);
		}

		JPanel characterChoicePanel = new JPanel(new GridLayout(6, 1));

		for (JRadioButton box : characterButtons)
			characterChoicePanel.add(box);
		
		characterChoicePanel.setOpaque(false);
		return characterChoicePanel;
	}

	public JPanel createWeaponChoicePanel() 
	{
		weaponButtons = new JRadioButton[this.weaponNames.length];
		ButtonGroup group = new ButtonGroup();

		for (int i=0; i!=this.weaponNames.length; ++i) 
		{
			weaponButtons[i] = new JRadioButton(this.weaponNames[i]);
			weaponButtons[i].setAction(new AbstractAction(this.weaponNames[i]) {
				public void actionPerformed(ActionEvent e) 
				{
					AbstractButton selectedRadio = (AbstractButton) e.getSource();
					weaponGuess = selectedRadio.getText();
				}
			});

			weaponButtons[i].setOpaque(false);
			weaponButtons[i].setForeground(Color.WHITE);
			group.add(weaponButtons[i]);
		}

		
		JPanel weaponChoicePanel = new JPanel(new GridLayout(6, 1));

		for (JRadioButton box : weaponButtons) 
			weaponChoicePanel.add(box);

		weaponChoicePanel.setOpaque(false);
		return weaponChoicePanel;
	} 
	
	@Override
	public void windowClosing(WindowEvent we) 
	{
		this.closeWindow();
	}

	protected void closeWindow() 
	{
		if (this.option.equals("SUGGEST")) 
		{
			JOptionPane.showMessageDialog(this, "Por favor realize seu palpite!");
			return;
		}
		else if (this.option.equals("ACCUSE")) 
		{
			JOptionPane.showMessageDialog(this, "Por favor realize sua acusação!");
			return;
		}
		this.dispose();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}