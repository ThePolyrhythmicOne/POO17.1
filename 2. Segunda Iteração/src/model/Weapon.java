/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package model;

public class Weapon {

	//Variavel para guardar nome da arma
	private final String name;

	//Variavel para guardar nome do quarto
	private Room room;

	public Weapon(String name, Room room) {

		//Guardando informacoes
		this.name = name;
		this.setRoom(room);
	}

	//Metodo para retornar nome do quarto
	public Room getRoom() {
		return room;
	}

	//Metodo responsavel para inserir quarto
	public void setRoom(Room room) {
		this.room = room;
	}

	//Metodo para retornar nome
	public String getName() {
		return this.name;
	}
}
