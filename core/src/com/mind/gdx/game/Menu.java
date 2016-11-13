package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Menu {
	
	private static int min = 0;
	public SoundEffect soundEffect;
	
	
	public Menu(SoundEffect soundEffect) {
		this.soundEffect = soundEffect;
	}
	
	public int [] initSelectedChoice(int [] choices) {
		for(int i = 0 ; i < choices.length ; i++) {
			choices[i] = i;
		}
		return choices;
	}
	
	public int updateSelected(int selected, int max) {
		if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			selected--;
			if(selected < min) {
				selected = min;
				soundEffect.play(SoundEffect.ERRORSOUND);
			} else {
				soundEffect.play(SoundEffect.SELECTEDSOUND);
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			selected++;
			if(selected > max) {
				selected = max;
				soundEffect.play(SoundEffect.ERRORSOUND);
			} else {
				soundEffect.play(SoundEffect.SELECTEDSOUND);
			}
		}
		return selected;
	}
	
	public boolean resetStatus() {
		return false;
	}
	
	public int resetSelected() {
		return 0;
	}
	
	public void resume(boolean status,int selected) {
	}
	
	public void restart(boolean status,int selected) {
		World.bar1.score = 0;
		World.bar2.score = 0;
		World.reset();
		World.ball.hitStatusLeftRight = Ball.hitPlayer1;
		World.endStatus = false;
	}
	
	public void startMenu(boolean status,int selected) {
		restart(status,selected);
		World.menuStatus = true;
	}
}
