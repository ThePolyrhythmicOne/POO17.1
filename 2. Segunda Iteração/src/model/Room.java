/***********************************************
*                                              * 
*  Projeto: INF 1636 / Jogo do Detetive (CLUE) *
*  Gestor:  Professor Ivan Mathias Filho       *
*  Autores: LUCAS RODRIGUES & MARCELO RAMOS    *
*                                              *
***********************************************/

package model;

import java.util.ArrayList;
import java.util.List;

public class Room {

	//Variavel para guardar nome
	private final String name;

	//Vetor de lista de jogadores para determinar ocupantes
	private List<Player> occupants = new ArrayList<>();

	//Variavel que representa passagem secreta
	private Room secretPassage = null;

	//Vetor de lista com posicoes de entrada e saida
	private List<Position> entrances = new ArrayList<>();
	private List<Position> exits = new ArrayList<>();

	//Vetor de lista de armas
	private List<Weapon> weapons = new ArrayList<>();

	public Room(String name) {
		this.name = name;
	}

	//Metodo que retorna se possui ocupantes ou nao
	public boolean isOccupied() {
		return !occupants.isEmpty();
	}

	//Metodo para inserir/adicionar novo ocupante
	public void addOccupant(Player p) {
		this.occupants.add(p);
	}

	//Metodo para retirar ocupante
	public void removeOccupant(Player p) {
		this.occupants.remove(p);
	}

	//Metodo que returna se possui ocupante
	public boolean hasOccupant(Player p) 
	{
		for (int i = 0; i < this.occupants.size(); i++) 
		{
			if (this.occupants.get(i).getPlayerName().equals(p.getPlayerName()))
				return true;
		}
		return false;
	}

	//Metodo que retorna se possui passagem secreta
	public boolean hasSecretPassage() {
		return this.secretPassage != null;
	}

	//Metodo que retorna a passagem secreta
	public Room getSecretPassage() {
		return secretPassage;
	}

	//Metodo que adiciona uma passagem secreta
	public void setSecretPassage(Room room) {
		this.secretPassage = room;
	}

	//Metodo que adicionan nome do quarto
	public String getRoomName() {
		return this.name;
	}

	//Metodo que retorna lista de armas do quarto
	public List<Weapon> getWeapons() {
		return this.weapons;
	}

	//Metodo que adiciona arma no quarto
	public void addWeapon(Weapon weapon) {
		this.weapons.add(weapon);
	}

	//Metodo que retira arma do quarto
	public void removeWeapon(Weapon weapon) {
		this.weapons.remove(weapon);
	}

	//Metodo que retorna lista de entradas
	public List<Position> getEntrances() {
		return this.entrances;
	}

	//Metodo que adiciona entrada
	public void addEntrance(Position e) {
		this.entrances.add(e);
	}

	//Metodo que retorna lista de saidas
	public List<Position> getExits() {
		return exits;
	}

	//Metodo que adiciona saida
	public void addExit(Position exit) {
		this.exits.add(exit);
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entrances == null) ? 0 : entrances.hashCode());
		result = prime * result + ((exits == null) ? 0 : exits.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((occupants == null) ? 0 : occupants.hashCode());
		result = prime * result + ((secretPassage == null) ? 0 : secretPassage.hashCode());
		result = prime * result + ((weapons == null) ? 0 : weapons.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (entrances == null) {
			if (other.entrances != null)
				return false;
		} else if (!entrances.equals(other.entrances))
			return false;
		if (exits == null) {
			if (other.exits != null)
				return false;
		} else if (!exits.equals(other.exits))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (occupants == null) {
			if (other.occupants != null)
				return false;
		} else if (!occupants.equals(other.occupants))
			return false;
		if (secretPassage == null) {
			if (other.secretPassage != null)
				return false;
		} else if (!secretPassage.equals(other.secretPassage))
			return false;
		if (weapons == null) {
			if (other.weapons != null)
				return false;
		} else if (!weapons.equals(other.weapons))
			return false;
		return true;
	}
}
