package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class EndMenu extends Menu{

	public static final int RESTART = 0;
	public static final int MENU = 1;
	public static final int NBOFMENU = 2;
	
	static int selectedEndMenu = RESTART;

	public EndMenu(SoundEffect soundEffect) {
		super(soundEffect);
	}
	
	public void update() {
		selectedEndMenu = updateSelected(selectedEndMenu,NBOFMENU - 1);
		selectedEndEnd();
	}
	
	public void selectedEndEnd() {
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			soundEffect.play(SoundEffect.ENTERSOUND);
			switch(selectedEndMenu) {
				case RESTART:
					restart(World.endStatus,selectedEndMenu);
					break;
				case MENU:
					startMenu(World.endStatus,selectedEndMenu);
					break;
				default:
					break;
			}
			
			World.endStatus = resetStatus();
			selectedEndMenu = resetSelected();
		}
	}
}
