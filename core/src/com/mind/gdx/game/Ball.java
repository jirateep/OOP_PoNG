package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Ball {

	public Vector2 position;
	
	public static float dilimiter = GameScreen.ballImg.getHeight();
	public static float radius = dilimiter/2;

	public static final int FIREBALL = 1;
	public static final int NOTHING = 0;
	public static int ballAbilityStatus = NOTHING;
	
	public static final float INITSPEED = 20;
	public static float speed = INITSPEED;
	public static float speedX;
	public static float speedY;
	public static float fireballSpeedFactor = 1.3f;
	public static float speedIncreaseFactor = 1.0001f;
	
	private static float startingX ;
	private static float startingY ;
	
	//public static final int NOTHING = 0;
	public static final int hitCeilling = 1;
	public static final int hitFloor = 2;
	public static final int hitPlayer1 = 3;
	public static final int hitPlayer2 = 4;
	public static int hitStatusUpDown = hitFloor;
	public static int hitStatusLeftRight = hitPlayer1; 

	public static boolean moveStatus = false;
	
	public Ball(){
		updateStartingPosition();
		position = new Vector2(startingX,startingY);
		getSpeed();
	}
	
	public void update() {
		if(!moveStatus&&!World.endGame) {
			checkStartMove();
		}
			move();
	}
	
	public void checkStartMove() {
		if(hitStatusLeftRight == hitPlayer1) {
			if(Gdx.input.isKeyPressed(World.bar1.pressActive)) {
				moveStatus = true;
			}
		}
		if(hitStatusLeftRight == hitPlayer2) {
			if(Gdx.input.isKeyPressed(World.bar2.pressActive)) {
				moveStatus = true;
			}
		}
	}
	
	public void move() {
		if(moveStatus) {
			updateDirection();
			moving();
			updateSpeed();
		} else {
			updateStartingPosition();
			position.x = startingX;
			position.y = startingY;
		}
	}

	static void updateSpeed() {
		speed *= speedIncreaseFactor;
		getSpeed();
	}
	
	static void getSpeed() {
		if(ballAbilityStatus == FIREBALL){
			speedX = speed*fireballSpeedFactor*3/5;
			speedY = speed*fireballSpeedFactor*4/5;
		} else if(ballAbilityStatus == NOTHING){
			speedX = speed*3/5;
			speedY = speed*4/5;
		}
	}
	
	static void updateStartingPosition() {
		if(hitStatusLeftRight == hitPlayer2) {
			startingX = World.bar2.position.x+World.bar2.length;
			startingY = World.bar2.position.y+World.bar2.width/2-radius;
		}else if (hitStatusLeftRight == hitPlayer1) {
			startingX = World.bar1.position.x-Ball.dilimiter;
			startingY = World.bar1.position.y+World.bar1.width/2-radius;
		}
	}
	
	
	public void updateDirection() {
		if(position.y >= GameScreen.height - dilimiter)
			hitStatusUpDown = hitCeilling;
		if(position.y <= 0)
			hitStatusUpDown = hitFloor;
		if(hitingBar1())
			hitStatusLeftRight = hitPlayer1;
		if(hitingBar2())
			hitStatusLeftRight = hitPlayer2;
	}
	
	public void moving() {
		if(hitStatusUpDown == hitFloor) {
			position.y+=speedY;
		}
		if(hitStatusUpDown == hitCeilling) {
			position.y-=speedX;
		}
		if(hitStatusLeftRight == hitPlayer1) {
			position.x-=speedY;
		}
		if(hitStatusLeftRight == hitPlayer2) {
			position.x+=speedX;
		}
	}
	
	public boolean hitingBar1() {
		return (position.x > World.bar1.position.x-Ball.dilimiter &&
				position.x < World.bar1.position.x) && 
			   (position.y > World.bar1.position.y-Ball.dilimiter && 
			    position.y < World.bar1.position.y+World.bar1.width);
	}
	
	public boolean hitingBar2() {
		return (position.x < World.bar2.position.x+World.bar2.length &&  
			    position.x > World.bar2.position.x) && 
			   (position.y > World.bar2.position.y-Ball.dilimiter && 
			    position.y < World.bar2.position.y+World.bar2.width);
	}
}