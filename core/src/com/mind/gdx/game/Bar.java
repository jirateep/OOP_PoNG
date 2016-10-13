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
	public int size = 2;
	private int pressUp;
	private int pressDown;
	private float speed = 20;
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
		if(!World.endGame)
			move();
	}
	
	private void move() {
		if(Gdx.input.isKeyPressed(pressUp)){
			position.y+=speed;
		}
		if(Gdx.input.isKeyPressed(pressDown)){
			position.y-=speed;
		}
		moveScope();
	}
	private void moveScope() {
		if(position.y < 0)
			position.y = 0;
		if(position.y > GameScreen.height - width)
			position.y = GameScreen.height - width;
	}
	
	public static void updateBarImg(int player) {
		if(player == 1 && World.bar1.size == 1)
			World.bar1.barImg = GameScreen.bar1Img1;
		if(player == 1 && World.bar1.size == 2)
			World.bar1.barImg = GameScreen.bar2Img1;
		if(player == 1 && World.bar1.size == 3)
			World.bar1.barImg = GameScreen.bar3Img1;
		if(player == 1 && World.bar1.size == 4)
			World.bar1.barImg = GameScreen.bar4Img1;
		if(player == 1 && World.bar1.size == 5)
			World.bar1.barImg = GameScreen.bar5Img1;
		if(player == 1 && World.bar1.size == 6)
			World.bar1.barImg = GameScreen.bar6Img1;
		if(player == 1 && World.bar1.size == 7)
			World.bar1.barImg = GameScreen.bar7Img1;
		if(player == 2 && World.bar1.size == 1)
			World.bar2.barImg = GameScreen.bar1Img2;
		if(player == 2 && World.bar1.size == 2)
			World.bar2.barImg = GameScreen.bar2Img2;
		if(player == 2 && World.bar1.size == 3)
			World.bar2.barImg = GameScreen.bar3Img2;
		if(player == 2 && World.bar1.size == 4)
			World.bar2.barImg = GameScreen.bar4Img2;
		if(player == 2 && World.bar1.size == 5)
			World.bar2.barImg = GameScreen.bar5Img2;
		if(player == 2 && World.bar1.size == 6)
			World.bar2.barImg = GameScreen.bar6Img2;
		if(player == 2 && World.bar1.size == 7)
			World.bar2.barImg = GameScreen.bar7Img2;
	}
}
