package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Ball {

	public Vector2 position;
	
	public float radius = 59;
	
	private float initX = GameScreen.bar1.position.x-Bar.length;
	private float initY = GameScreen.bar1.position.y+Bar.width/2-radius/2;

	public static int speed = 5;

	//private static int NOTHING = 0;
	private static int hitCeilling = 1;
	private static int hitFloor = 2;
	private static int hitPlayer1 = 3;
	private static int hitPlayer2 = 4;
	public static int hitStatusUpDown = hitFloor;
	public static int hitStatusLeftRight = hitPlayer1; 

	public static boolean moveStatus = false;
	
	public Ball(){
		position = new Vector2(initX,initY);
	}
	public void update() {
		if(!moveStatus) {
			checkStartMove();
		}
			move();
	}
	
	public void checkStartMove() {
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			moveStatus = true;
		}
	}
	
	public void move() {
		if(moveStatus) {
			updateDirection();
			moving();
		} else {
			position.x = GameScreen.bar1.position.x-Bar.length;
			position.y = GameScreen.bar1.position.y+Bar.width/2-radius/2;
		}
	}
	
	public void updateDirection() {
		if(position.y >= GameScreen.height - radius)
			hitStatusUpDown = hitCeilling;
		if(position.y <= 0)
			hitStatusUpDown = hitFloor;
		if((position.x > GameScreen.bar1.position.x-Bar.length &&
			position.x <GameScreen.bar1.position.x) && 
		  (position.y > GameScreen.bar1.position.y && 
		   position.y < GameScreen.bar1.position.y+Bar.width))
			hitStatusLeftRight = hitPlayer1;
		if((position.x < GameScreen.bar2.position.x+Bar.length &&  
		    position.x > GameScreen.bar2.position.x) && 
		  (position.y > GameScreen.bar2.position.y && 
		   position.y < GameScreen.bar2.position.y+Bar.width))
			hitStatusLeftRight = hitPlayer2;
	}
	
	public void moving() {
		if(hitStatusUpDown == hitFloor) {
			position.y+=speed;
		}
		if(hitStatusUpDown == hitCeilling) {
			position.y-=speed;
		}
		if(hitStatusLeftRight == hitPlayer1) {
			position.x-=speed;
		}
		if(hitStatusLeftRight == hitPlayer2) {
			position.x+=speed;
		}
	}
}
