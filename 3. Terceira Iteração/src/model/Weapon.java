/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package model;

import java.util.*;

 class Weapon {

 	private Room room;
	private final String name;
	
	private static List<Weapon> weapons=new ArrayList<>(6);

	protected Weapon(String name, Room room) 
	{
		this.name = name;
		this.setRoom(room);
	}
	
	protected static List<Weapon> getWeapons()
	{
		return weapons;
	}
	
	protected static void createWeapons()
	{
		for(short i=0; i<6; i++)
			switch(i)
			{
				case 0: 
					weapons.add(new Weapon(WeaponNum.Candlestick.toString(),Room.getRoomList().get(i)));
					Card.getWeaponCards().add(new Card(WeaponNum.Candlestick.toString(),'W'));
					break;
				case 1: 
					weapons.add(new Weapon(WeaponNum.Dagger.toString(),Room.getRoomList().get(i)));
					Card.getWeaponCards().add(new Card(WeaponNum.Dagger.toString(),'W'));
					break;
				case 2: 
					weapons.add(new Weapon(WeaponNum.LeadPipe.toString(),Room.getRoomList().get(i)));
					Card.getWeaponCards().add(new Card(WeaponNum.LeadPipe.toString(),'W'));
					break;
				case 3: 
					weapons.add(new Weapon(WeaponNum.Revolver.toString(),Room.getRoomList().get(i)));
					Card.getWeaponCards().add(new Card(WeaponNum.Revolver.toString(),'W'));
					break;
				case 4: 
					weapons.add(new Weapon(WeaponNum.Rope.toString(),Room.getRoomList().get(i)));
					Card.getWeaponCards().add(new Card(WeaponNum.Rope.toString(),'W'));
					break;
				case 5: 
					weapons.add(new Weapon(WeaponNum.Spanner.toString(),Room.getRoomList().get(i)));
					Card.getWeaponCards().add(new Card(WeaponNum.Spanner.toString(),'W'));
					break;
			}
	}

	protected Room getRoom() 
	{
		return room;
	}

	protected void setRoom(Room room) 
	{
		this.room = room;
	}

	protected String getName() 
	{
		return this.name;
	}
}