package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect {
	
	private Sound [] sounds;
	
	public static final int ERRORSOUND = 1;
	public static final int SELECTEDSOUND = 2;
	public static final int ENTERSOUND = 3;
	public static final int HITSOUND = 4;
	public static final int SHOOTSOUND = 5;
	public static final int ABILITYSOUND = 6;
	public static final int NBOFSOUNDS = 7;
	
	public SoundEffect() {
		sounds = new Sound [NBOFSOUNDS];
		sounds[ERRORSOUND] = Gdx.audio.newSound(Gdx.files.internal("errorSound.mp3"));
		sounds[SELECTEDSOUND] = Gdx.audio.newSound(Gdx.files.internal("selectedSound.mp3"));
		sounds[ENTERSOUND] = Gdx.audio.newSound(Gdx.files.internal("enterSound.mp3"));
		sounds[HITSOUND] = Gdx.audio.newSound(Gdx.files.internal("hitSound.mp3"));
		sounds[SHOOTSOUND] = Gdx.audio.newSound(Gdx.files.internal("shootSound.mp3"));
		sounds[ABILITYSOUND] = Gdx.audio.newSound(Gdx.files.internal("abilitySound.mp3"));
	}
	
	public boolean canPlaySound() {
		return !World.muteStatus;
	}
	
	public void play(int sound) {
		if(canPlaySound()) {
			sounds[sound].play(1.0f);
		}
	}
}