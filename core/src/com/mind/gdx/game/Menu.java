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
}
