package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bar {
	
	Vector2 position;
	public int score = 0;
	public float length ;
	public float width ;
	public float y;
	public Texture barImg;
	public int size = 2;
	public final static int BIGGERBATINCREASE = 2;
	public final static int SMALLERBATDECREASE = 1;
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

	public static void updateBarImg() {
		if(World.bar1.size == 1)
			World.bar1.barImg = GameScreen.bar1Img1;
		if(World.bar1.size == 2)
			World.bar1.barImg = GameScreen.bar2Img1;
		if(World.bar1.size == 3)
			World.bar1.barImg = GameScreen.bar3Img1;
		if(World.bar1.size == 4)
			World.bar1.barImg = GameScreen.bar4Img1;
		if(World.bar1.size == 5)
			World.bar1.barImg = GameScreen.bar5Img1;
		if(World.bar1.size == 6)
			World.bar1.barImg = GameScreen.bar6Img1;
		if(World.bar1.size == 7)
			World.bar1.barImg = GameScreen.bar7Img1;
		if(World.bar2.size == 1)
			World.bar2.barImg = GameScreen.bar1Img2;
		if(World.bar2.size == 2)
			World.bar2.barImg = GameScreen.bar2Img2;
		if(World.bar2.size == 3)
			World.bar2.barImg = GameScreen.bar3Img2;
		if(World.bar2.size == 4)
			World.bar2.barImg = GameScreen.bar4Img2;
		if(World.bar2.size == 5)
			World.bar2.barImg = GameScreen.bar5Img2;
		if(World.bar2.size == 6)
			World.bar2.barImg = GameScreen.bar6Img2;
		if(World.bar2.size == 7)
			World.bar2.barImg = GameScreen.bar7Img2;
	}	
	public static void updateWidthHeight() {
		World.bar1.length = World.bar1.barImg.getWidth();
		World.bar1.width = World.bar1.barImg.getHeight();
		World.bar2.length = World.bar2.barImg.getWidth();
		World.bar2.width = World.bar2.barImg.getHeight();	
	}

}
