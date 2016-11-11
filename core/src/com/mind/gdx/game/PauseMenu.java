package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class PauseMenu {
	public static final int RESUME = 0;
	public static final int RESTART = 1;
	public static final int MENU = 2;
	public static final int NBOFMENU = 3;
	
	static int selectedPauseMenu = RESUME;
	
	public static void update() {
		selectedPauseMenu = Menu.updateSelected(selectedPauseMenu,2);
		selectedPauseEnd();
	}
	
	public static void selectedPauseEnd() {
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			switch(selectedPauseMenu) {
				case RESUME:
					resume();
					break;
				case RESTART:
					restart();
					break;
				case MENU:
					startMenu();
					break;
				default:
					break;
			}
		}
	}
	
	public static void resume() {
		World.pauseStatus = false;
		selectedPauseMenu = 1;
	}
	
	public static void restart() {
		World.bar1.score = 0;
		World.bar2.score = 0;
		World.reset();
		World.ball.hitStatusLeftRight = Ball.hitPlayer1;
		World.pauseStatus = false;
		World.selectedPause = 1;
		World.endGame = false;
	}
	
	public static void startMenu() {
		restart();
		World.pauseStatus = false;
		World.menuStatus = true;
	}
}
