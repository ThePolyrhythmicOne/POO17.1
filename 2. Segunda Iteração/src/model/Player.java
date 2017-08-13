/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package model;

public class Player {
	
	//Variavel para determinar posicao corrente
	private Position currentPosition;

	//Variavel para determinar posicao inicial
	private Position startingPosition;

	//Vetor de jogadores ativos e inativos
	private static Player[] player = new Player[6];
	private static Player[] inactivePlayer = new Player[6];

	//Variável para determinar nome do jogador (ex: Mathias, Rodriguez, etc)
	private String playerName = null;
	
	//Variável para determinar se o jogador foi selecionado no checkbox
	private final boolean isSelected;
	
	public Player(String playerName, boolean isSelected) {
		this.playerName = playerName;
		this.isSelected = isSelected;
	}

	//Metodo que retona posicao corrente
	public Position getPosition() {
		return this.currentPosition;
	}

	//Metodo que retorna X do inicio
	public int getStartingPositionX(){
		return startingPosition.getPosX();
	}
	
	//Metodo que retorna Y do inicio
	public int getStartingPositionY(){
		return startingPosition.getPosY();
	}

	//Metodo que retona posicao inicial
	public Position getStartingPosition(){
		return startingPosition;
	}
	
	//Metodo que adiciona jogador ativo
	public static void addPlayer(PlayerNum name,boolean isSelected,int posX,int posY)
	{
		player[name.ordinal()]=new Player(name.toString(),isSelected);
		player[name.ordinal()].setStartingPosition(new Position(posX,posY));
		player[name.ordinal()].setPosition(new Position(posX,posY));
	}
	
	//Metodo que adiciona jogador inativo
	public static void addInactivePlayer(PlayerNum name,boolean isSelected,int posX,int posY)
	{
		inactivePlayer[name.ordinal()]=new Player(name.toString(),isSelected);
		inactivePlayer[name.ordinal()].setStartingPosition(new Position(posX,posY));
		inactivePlayer[name.ordinal()].setPosition(new Position(posX,posY));
	}
	
	//Metodo que retorna vetor de jogadores ativos
	public static Player[] getPlayerArray(){
		return player;
	}
	
	//Metodo que retorna vetor de jogadores inativos
	public static Player[] getInactivePlayerArray(){
		return inactivePlayer;
	}
	
	//Metodo que determina posicao inicial
	public void setStartingPosition(Position pos) {
		this.startingPosition = pos;
	}
	
	//Metodo que determina posicao
	public void setPosition(Position pos) {
		this.currentPosition = pos;
	}

	//Metodo responsável em retornar nome do jogador corrente
	public String getPlayerName() {
		return playerName;
	}

	//Metodo responsavel em retornar true caso jogador foi selecionado e falso caso contrário
	public boolean isSelected() {
		return isSelected;
	}
}
