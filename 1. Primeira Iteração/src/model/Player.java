/***************************************************************************
*  $MCI Módulo de implementação: Player  Módulo de Jogadores
*
*  Arquivo gerado:              Player.java
*  Letras identificadoras:      Player
*
*  Projeto: INF 1636 / Jogo do Detetive (CLUE)
*  Gestor:  Professor Ivan Mathias Filho
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS
*
*  $ED Descrição do módulo
*     Módulo presente no package model que representa os jogadores do jogo e
*     administra as informações dos mesmos.
*
***************************************************************************/

package model;

public class Player {

	//Variável para determinar nome do jogador (ex: Mathias, Rodriguez, etc)
	private final String playerName;
	
	//Variável para determinar se o jogador foi selecionado no checkbox
	private final boolean isSelected;
	
	//Construtor não-default que inicializa as variáveis de acordo com os parâmentros
	public Player(String playerName, boolean isSelected) {
		//Inicialização de variáveis
		this.playerName = playerName;
		this.isSelected = isSelected;
	}

	//Método responsável em retornar nome do jogador corrente
	public String getPlayerName() {
		return playerName;
	}

	//Método responsavel em retornar true caso jogador foi selecionado e falso caso contrário
	public boolean isSelected() {
		return isSelected;
	}
}
