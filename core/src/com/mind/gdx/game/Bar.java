package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Bar {
	
	Vector2 position;
	public static float length = 59;
	public static float width = 334;
	public float y = GameScreen.height/2-(length/2);
	private int pressUp;
	private int pressDown;
	public Bar(float x,int up,int down) {
		position = new Vector2(x,y);
		pressUp = up;
		pressDown = down;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void update() {
		move();
	}
	
	private void move() {
		if(Gdx.input.isKeyPressed(pressUp)){
			position.y+=10;
		}
		if(Gdx.input.isKeyPressed(pressDown)){
			position.y-=10;
		}
		moveScope();
	}
	private void moveScope() {
		if(position.y < 0)
			position.y = 0;
		if(position.y > GameScreen.height - width)
			position.y = GameScreen.height - width;
	}
}
