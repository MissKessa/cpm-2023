package uo.cpm.module.player;

import java.io.File;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * It's the façade of the BasicPlayer class to have a simplified version
 * 
 * @author paula
 *
 */
public class MusicPlayer {
	/**
	 * It's the real player
	 */
	private BasicPlayer basicPlayer = null;

	/**
	 * Creates the basicPlayer
	 */
	public MusicPlayer() {
		basicPlayer = new BasicPlayer();
	}

	/**
	 * Plays the song passed as a file
	 * 
	 * @param file
	 */
	public void play(File file) {
		try {
			basicPlayer.open(file);
			basicPlayer.play();
		} catch (Exception e) {
		}
	}

	/**
	 * Plays the Music game.mp3 song
	 */
	public void play() {
//		System.out.println(new File("src/uo/cpm/module/player/Music game.mp3").exists());
		try {
			basicPlayer.open(new File("src/uo/cpm/module/player/Music game.mp3"));
			basicPlayer.play();
		} catch (Exception e) {
		}
	}

	/**
	 * Stops the music played
	 */
	public void stop() {
		try {
			basicPlayer.stop();
		} catch (BasicPlayerException e) {
		}
	}

	/**
	 * Sets the volume of the music
	 * 
	 * @param vol
	 * @param volMax
	 */
	public void setVolume(double vol, double volMax) {
		try {

			basicPlayer.setGain(vol / volMax);
		} catch (BasicPlayerException e) {
		}
	}
}
