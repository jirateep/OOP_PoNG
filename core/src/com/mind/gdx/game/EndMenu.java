package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class EndMenu extends Menu{
	public static final int RESTART = 0;
	public static final int MENU = 1;
	public static final int NBOFMENU = 2;
	
	static int selectedEndMenu = RESTART;
	
	public static void update() {
		selectedEndMenu = Menu.updateSelected(selectedEndMenu,NBOFMENU - 1);
		selectedEndEnd();
	}
	
	public static void selectedEndEnd() {
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
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
