/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

package model;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import view.VMBridge;

class SaveLoad {
	
	public static void Save()
	{
		PrintWriter out = null;
		
		try 
		{
			out = new PrintWriter(new FileWriter("SAVE_GAME.jorge"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		for(Player p: Player.getPlayerArray())
		{
			out.println(p.getPlayerName());
			
			out.println("\tEliminated: "+p.Eliminated);
			out.println("\tIsSelected: "+p.isSelected());
			out.println("\tPlayerTurn: "+p.PlayerTurn);
			out.println("\tTurnToMove: "+p.turnToMove);
			if(p.turnToMove)
				out.println("\tDiceRoll: "+PlayerMovement.getInstance().diceRoll);
			out.println("\tTurnToThrow: "+p.turnToThrow);
			out.println("\tTurnToGuess: "+p.turnToGuess);
			out.println("\tIsInRoom: "+p.isInRoom+"\n");
			
			
			out.println("\tAnnotations:");
			for(String s: p.getAnnotations())
				out.println("\t  -> "+s);
			out.println("#");
			
			out.println("\tCards:");
			for(Card c: p.getCardDeck())
				out.println("\t  -> "+c.getCardName()+" "+c.getCardType());
			out.println("#");
			
			out.printf("\tPositionX: %d",p.getPosition().getPosX());
			out.println("\n");
			out.printf("\tPositionY: %d",p.getPosition().getPosY());
			out.println("\n");
			out.printf("\tPositionLine: %d",p.getPosition().getCoordOnMatrixI(p.getPosition().getPosY()));
			out.println("\n");
			out.printf("\tPositionColunm: %d",p.getPosition().getCoordOnMatrixJ(p.getPosition().getPosX()));
			out.println("\n");
			
			out.println("\n");
		}
		
		out.println("///////////////////////////////////////////////////////");
		out.println();
		
		out.printf("SecretEnvelope: %s %c %s %c %s %c",Card.getSecretEnvelope().get(0).getCardName(),Card.getSecretEnvelope().get(0).getCardType(),
				Card.getSecretEnvelope().get(1).getCardName(),Card.getSecretEnvelope().get(1).getCardType(),Card.getSecretEnvelope().get(2).getCardName()
				,Card.getSecretEnvelope().get(2).getCardType());
		out.println("\n");
		
		for(Room r: Room.getRoomList())
		{
			out.printf("Weapons inside "+r.getRoomName()+" = ");
			for(Weapon w: r.getWeapons())
				out.printf(w.getName()+" ");
			out.println("#\n");
		}
		
		out.close();
		System.out.println("\n**** JOGO SALVO COM SUCESSO ****\n");
	}

	public static void loadGame(File selectedFile) throws IOException
	{
		Scanner s=null;
		
		try 
		{
			s = new Scanner(new BufferedReader(new FileReader(selectedFile)));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		List <String> envelope=new LinkedList<String>();
		List <String> weaps=new LinkedList<String>(),rms=new LinkedList<String>();
		List <String> ann=new LinkedList<String>(),cards=new LinkedList<String>(),cardTypes=new LinkedList <String>();
		short roll = 0;
		
		//loop for setting players
		short j=0;
		while(s.hasNext())
		{
			String first=s.next();
			if(first.equals("///////////////////////////////////////////////////////"))
				break;
			String name;
			
			@SuppressWarnings("unused")
			int posx,posy,posi,posj;
			Boolean elim,issel,pt,t2m,t2t,t2g,iir;

			//boolean setting
			name=first;
			s.next();
			elim=s.nextBoolean();
			s.next();
			issel=s.nextBoolean();
			s.next();
			pt=s.nextBoolean();
			s.next();
			t2m=s.nextBoolean();
			s.next();
			
			if(t2m)
			{
				roll=(short) s.nextInt();
				s.next();
				VMBridge.loadDice(roll);
			}
			
			t2t=s.nextBoolean();
			s.next();
			
			t2g=s.nextBoolean();
			s.next();
			
			iir=s.nextBoolean();
			
			//loop for annotations
			s.next();
			while(!s.next().equals("#")){
				ann.add(s.next());
			}
			
			//loop for cards
			s.next();
			while(!s.next().equals("#"))
			{
				cards.add(s.next());
				cardTypes.add(s.next());
			}
			
			s.next();
			posx=s.nextInt();
			s.next();
			posy=s.nextInt();
			s.next();
			posi=s.nextInt();
			s.next();
			posj=s.nextInt();
			
			//create each player
			Player.addPlayer(PlayerNum.valueOf(name), issel, posx, posy, pt.booleanValue(), iir.booleanValue(),t2m,t2t,t2g,elim);
			
			//adds each player's card deck
			for(short i=0;i<cards.size();i++)
				Player.getPlayerArray().get(j).addCard(new Card(cards.get(i),cardTypes.get(i).charAt(0)));
			cards.removeAll(cards);
			
			//add each player's notes
			for(short i=0;i<ann.size()-2;i+=3)
				Player.getPlayerArray().get(j).addAnnotation(ann.get(i)+" realizado: "+ann.get(i+1)+" - " +ann.get(i+2));
			
			//set his position to match the saved one
			PlayerMovement.getInstance().movePlayer(Player.getPlayerArray().get(j), new Position(posx,posy));	
			PlayerMovement.getInstance().setMoveSize(roll);
			j++;
		}
		
		//secret envelope
		s.next();
		for(short i=0;i<6;i++)
			envelope.add(s.next());
		
		//weapons and rooms loop
		while(s.hasNext())
		{
			s.next();s.next();
			rms.add(s.next());
			s.next();
			String w=s.next();
			while(!w.equals("#"))
			{
				weaps.add(w);
				w=s.next();
			}
		}
		
		s.close();

		//adds cards to secret envelope
		for(short i=0;i<envelope.size()-1;i++)
			Card.getSecretEnvelope().add(new Card(envelope.get(i),envelope.get(i+1).charAt(0)));
		
		Room.createRooms(weaps, rms);
		VMBridge.loadImages();
		VMBridge.loadBoard();
		VMBridge.setGame();
	}
}