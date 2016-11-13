package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class PauseMenu extends Menu{
	
	public PauseMenu(SoundEffect soundEffect) {
		super(soundEffect);
	}

	public static final int RESUME = 0;
	public static final int RESTART = 1;
	public static final int MENU = 2;
	public static final int NBOFMENU = 3;
	
	static int selectedPauseMenu = RESUME;
	
	public void update() {
		selectedPauseMenu = updateSelected(selectedPauseMenu,NBOFMENU - 1);
		selectedPauseEnd();
	}
	
	public void selectedPauseEnd() {
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			soundEffect.play(SoundEffect.ENTERSOUND);
			switch(selectedPauseMenu) {
				case RESUME:
					resume(World.pauseStatus,selectedPauseMenu);
					break;
				case RESTART:
					restart(World.pauseStatus,selectedPauseMenu);
					break;
				case MENU:
					startMenu(World.pauseStatus,selectedPauseMenu);
					break;
				default:
					break;
			}

			World.pauseStatus = resetStatus();
			selectedPauseMenu = resetSelected();
		}
	}
}
