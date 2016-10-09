package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bar {
	
	Vector2 position;
	public int score = 0;
	public static float length ;
	public static float width ;
	public float y;
	public Texture barImg;
	private int pressUp;
	private int pressDown;
	public Bar(Texture barImg,float x,int up,int down) {
		this.barImg = barImg;
		length = barImg.getWidth();
		width = barImg.getHeight();
		y = (GameScreen.height-width)/2;
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
