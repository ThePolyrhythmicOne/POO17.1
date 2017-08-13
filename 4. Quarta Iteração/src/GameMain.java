/***************************************************************************
 *
 *  Projeto: INF 1636 - DETETIVE GAME | D.I EDITION
 *  Gestor:  Professor Ivan Mathias Filho
 *  Autores: Lucas Rodrigues - 1510848
 *           Marcelo Ramos   - 1510823
 *
 ***************************************************************************/

import view.*;
/*
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import java.io.FileInputStream;
import java.io.IOException;
*/

public class GameMain {

	public static void main(String[] args) 
	{
		//music();
		new GameFrame();
	}
	
	/*public static void music()
	{
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;
	
		try
		{
			BGM = new AudioStream(new FileInputStream("snd/file.wav"));
			MD = BGM.getData();
			loop = new ContinuousAudioDataStream(MD);
		}
		catch(IOException error)
		{
			error.printStackTrace();
		}
		MGP.start(loop);
	}*/
}