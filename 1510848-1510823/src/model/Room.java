/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Room {

	private final String name;
	private int weapPosX, weapPosY;

	private List<Weapon> weapons = new ArrayList<>();
	private List<Player> occupants = new ArrayList<>();
	private static List<Room> rooms=new ArrayList<>(9);

	protected Room(String name,int posX,int posY) 
	{
		this.name = name;
		this.weapPosX=posX;
		this.weapPosY=posY;
	}
	
	protected static List<Room> getRoomList()
	{
		return rooms;
	}
	protected static void createRooms(List <String> weaps,List <String> rms)
	{
		for(String name: rms)
			switch(name)
			{
				case "Ballroom": 
					rooms.add(new Room(Tiles.Ballroom.toString(), 280, 170));
					Card.getRoomCards().add(new Card(Tiles.Ballroom.toString(),'R'));
					break;
				case "BilliardRoom": 
					rooms.add(new Room(Tiles.BilliardRoom.toString(), 550, 255));
					Card.getRoomCards().add(new Card(Tiles.BilliardRoom.toString(),'R'));
					break;
				case "Conservatory": 
					rooms.add(new Room(Tiles.Conservatory.toString(), 510, 86));
					Card.getRoomCards().add(new Card(Tiles.Conservatory.toString(),'R'));
					break;
				case "DiningRoom": 
					rooms.add(new Room(Tiles.DiningRoom.toString(), 95, 385));
					Card.getRoomCards().add(new Card(Tiles.DiningRoom.toString(),'R'));
					break;
				case "Hall": 
					rooms.add(new Room(Tiles.Hall.toString(), 370, 535));
					Card.getRoomCards().add(new Card(Tiles.Hall.toString(),'R'));
					break;
				case "Kitchen": 
					rooms.add(new Room(Tiles.Kitchen.toString(), 100, 80));
					Card.getRoomCards().add(new Card(Tiles.Kitchen.toString(),'R'));
					break;
				case "Library": 
					rooms.add(new Room(Tiles.Library.toString(), 520, 400));
					Card.getRoomCards().add(new Card(Tiles.Library.toString(),'R'));
					break;
				case "Lounge": 
					rooms.add(new Room(Tiles.Lounge.toString(), 90, 550));
					Card.getRoomCards().add(new Card(Tiles.Lounge.toString(),'R'));
					break;
				case "Study": 
					rooms.add(new Room(Tiles.Study.toString(), 540, 570));
					Card.getRoomCards().add(new Card(Tiles.Study.toString(),'R'));
					break;
			}
		
		for(short i=0;i<6;i++)
			Room.getRoomList().get(i).addWeapon(Weapon.createWeapons(weaps.get(i), Room.getRoomList().get(i)));
	}
	
	protected static void createRooms()
	{
		for(short i=0; i<9; i++)
			switch(i)
			{
				case 0: 
					rooms.add(new Room(Tiles.Ballroom.toString(), 280, 170));
					Card.getRoomCards().add(new Card(Tiles.Ballroom.toString(),'R'));
					break;
				case 1: 
					rooms.add(new Room(Tiles.BilliardRoom.toString(), 550, 255));
					Card.getRoomCards().add(new Card(Tiles.BilliardRoom.toString(),'R'));
					break;
				case 2: 
					rooms.add(new Room(Tiles.Conservatory.toString(), 510, 86));
					Card.getRoomCards().add(new Card(Tiles.Conservatory.toString(),'R'));
					break;
				case 3: 
					rooms.add(new Room(Tiles.DiningRoom.toString(), 95, 385));
					Card.getRoomCards().add(new Card(Tiles.DiningRoom.toString(),'R'));
					break;
				case 4: 
					rooms.add(new Room(Tiles.Hall.toString(), 370, 535));
					Card.getRoomCards().add(new Card(Tiles.Hall.toString(),'R'));
					break;
				case 5: 
					rooms.add(new Room(Tiles.Kitchen.toString(), 100, 80));
					Card.getRoomCards().add(new Card(Tiles.Kitchen.toString(),'R'));
					break;
				case 6: 
					rooms.add(new Room(Tiles.Library.toString(), 520, 400));
					Card.getRoomCards().add(new Card(Tiles.Library.toString(),'R'));
					break;
				case 7: 
					rooms.add(new Room(Tiles.Lounge.toString(), 90, 550));
					Card.getRoomCards().add(new Card(Tiles.Lounge.toString(),'R'));
					break;
				case 8: 
					rooms.add(new Room(Tiles.Study.toString(), 540, 570));
					Card.getRoomCards().add(new Card(Tiles.Study.toString(),'R'));
					break;
			}
		
		Collections.shuffle(rooms);
		Weapon.createWeapons();
		
		for(short i=0;i<Weapon.getWeapons().size();i++)
			Room.getRoomList().get(i).addWeapon(Weapon.getWeapons().get(i));
			
	}
	
	protected int getWeaponPosX()
	{
		return weapPosX;
	}
	protected int getWeaponPosY()
	{
		return weapPosY;
	}

	protected boolean isOccupied() 
	{
		return !occupants.isEmpty();
	}

	protected void addOccupant(Player p) 
	{
		this.occupants.add(p);
	}

	protected void removeOccupant(Player p) 
	{
		this.occupants.remove(p);
	}

	protected boolean hasOccupant(Player p) 
	{
		for (int i = 0; i < this.occupants.size(); i++)
			if (this.occupants.get(i).getPlayerName().equals(p.getPlayerName()))
				return true;

		return false;
	}

	protected String getRoomName() 
	{
		return this.name;
	}

	protected List<Weapon> getWeapons() 
	{
		return this.weapons;
	}

	protected void addWeapon(Weapon weapon) 
	{
		this.weapons.add(weapon);
		weapon.setRoom(this);
	}

	protected void removeWeapon(Weapon weapon) 
	{
		this.weapons.remove(weapon);
		weapon.setRoom(null);
	}
}