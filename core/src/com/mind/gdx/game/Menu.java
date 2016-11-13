package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Menu {
	
	private static int min = 0;
	
	public static int [] initSelectedChoice(int [] choices) {
		for(int i = 0 ; i < choices.length ; i++) {
			choices[i] = i;
		}
		return choices;
	}
	
	public static int updateSelected(int selected, int max) {
		if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			selected--;
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			selected++;
		}
		if(selected > max) {
			selected = max;
		}
		if(selected < min) {
			selected = min;
		}
		return selected;
	}
	
	public static boolean resetStatus() {
		return false;
	}
	
	public static int resetSelected() {
		return 0;
	}
	
	public static void resume(boolean status,int selected) {
	}
	
	public static void restart(boolean status,int selected) {
		World.bar1.score = 0;
		World.bar2.score = 0;
		World.reset();
		World.ball.hitStatusLeftRight = Ball.hitPlayer1;
		World.endStatus = false;
	}
	
	public static void startMenu(boolean status,int selected) {
		restart(status,selected);
		World.menuStatus = true;
	}
}
