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
	public static float initSpeedXFactor = 12f/13;
	public static float initSpeedYFactor = 5f/13;
	public static float speedXFactor;
	public static float speedYFactor;
	
	public static float fireballSpeedFactor = 1.3f;
	public static float speedIncreaseFactor = 1.0001f;
	
	private static float startingX ;
	private static float startingY ;
	
	public static final int hitCeilling = 1;
	public static final int hitFloor = 2;
	public static final int hitPlayer1 = 3;
	public static final int hitPlayer2 = 4;
	public static int hitStatusUpDown = hitFloor;
	public static int hitStatusLeftRight = hitPlayer1; 
	public static int oldHitStatusLeftRight = hitPlayer1;

	public static boolean moveStatus = false;
	
	public Ball(){
		updateStartingPosition();
		position = new Vector2(startingX,startingY);
		speedXFactor = initSpeedXFactor;
		speedYFactor = initSpeedYFactor;
		getSpeed();
		randomDirectionUpDown();
	}
	
	public void randomDirectionUpDown() {
		if(((int)(Math.random() * 100))%2 == 0) {
			hitStatusUpDown = hitFloor;
		} else {
			hitStatusUpDown = hitCeilling;
		}	
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
			speedX = speed*fireballSpeedFactor*speedXFactor;
			speedY = speed*fireballSpeedFactor*speedYFactor;
		} else if(ballAbilityStatus == NOTHING){
			//System.out.println("speedYFactor:" + speedYFactor);
			//System.out.println("oldspeedY:" + speedY);
			speedX = speed*speedXFactor;
			speedY = speed*speedYFactor;
			//System.out.println("newspeedY:" + speedY);
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
		if(position.y >= GameScreen.height - dilimiter) {
			hitStatusUpDown = hitCeilling;
		}
		if(position.y <= 0) {
			hitStatusUpDown = hitFloor;
		}
		if(hitingBar1()) {
			hitStatusLeftRight = hitPlayer1;
		}
		if(hitingBar2()) {
			hitStatusLeftRight = hitPlayer2;
		}
	}
	
	public void moving() {
		if(hitStatusUpDown == hitFloor) {
			position.y+=speedY;
		}
		if(hitStatusUpDown == hitCeilling) {
			position.y-=speedY;
		}
		if(hitStatusLeftRight == hitPlayer1) {
			mkNewSpeed();
			position.x-=speedX;
		}
		if(hitStatusLeftRight == hitPlayer2) {
			mkNewSpeed();
			position.x+=speedX;
		}
		oldHitStatusLeftRight = hitStatusLeftRight;
	}
	public void mkNewSpeed() {
		int reflectionRadius = 150;
		if(oldHitStatusLeftRight == hitPlayer2 && hitStatusLeftRight == hitPlayer1 && checkReflectRange(World.bar1)) {
			float xHitPosition = position.x+dilimiter;
			float yHitPosition = position.y+radius;
			float differentYPosition = yHitPosition - World.bar1.position.y - World.bar1.width/2;
			float differentXPosition =xHitPosition - GameScreen.width - reflectionRadius;
			float distance = (float)Math.sqrt(Math.pow(differentXPosition, 2) + Math.pow(differentYPosition, 2));
			speedXFactor = Math.abs(differentXPosition/distance);
			speedYFactor = Math.abs(differentYPosition/distance);
			if(position.y < World.bar1.position.y+World.bar1.width/2 - radius) {
				hitStatusUpDown = hitCeilling;
			} else {
				hitStatusUpDown = hitFloor;
			}
			oldHitStatusLeftRight = hitPlayer1;
			
		}
		if(oldHitStatusLeftRight == hitPlayer1 && hitStatusLeftRight == hitPlayer2 && checkReflectRange(World.bar2)) {
			float xHitPosition = position.x;
			float yHitPosition = position.y+radius;
			float differentYPosition = yHitPosition - World.bar2.position.y -World.bar1.width/2;
			float differentXPosition = xHitPosition + reflectionRadius;
			float distance = (float)Math.sqrt(Math.pow(differentXPosition, 2) + Math.pow(differentYPosition, 2));
			speedXFactor = Math.abs(differentXPosition/distance);
			speedYFactor = Math.abs(differentYPosition/distance);
			if(position.y < World.bar2.position.y+World.bar2.width/2 - radius) {
				hitStatusUpDown = hitCeilling;
			} else {
				hitStatusUpDown = hitFloor;
			}
			hitStatusLeftRight = hitPlayer2;
		}
	}
	
	public boolean checkReflectRange(Bar bar) {
		float reflectSpace = 2;
		float notReflectSpace = 1;
		float nbSpace = reflectSpace*2 + notReflectSpace;
		float topSpace = (reflectSpace+notReflectSpace)/nbSpace;
		float bottomSpace = reflectSpace/nbSpace;
		return !((position.y < bar.position.y + bar.width*topSpace) && (position.y > bar.position.y + bar.width*bottomSpace));
	}
	public boolean hitingBar1() {
		if(World.bar1.shieldStatus) {
			//return position.x > World.bar1.position.x+World.bar1.length;
			return position.x > World.bar1.position.x-Ball.dilimiter &&
				   position.x < World.bar1.position.x;
		} else {
			return (position.x > World.bar1.position.x-Ball.dilimiter &&
					position.x < World.bar1.position.x) && 
				   (position.y > World.bar1.position.y-Ball.dilimiter &&	 
					position.y < World.bar1.position.y+World.bar1.width);	
		}
	}
	
	public boolean hitingBar2() {
		if(World.bar2.shieldStatus) {
			//return position.x < World.bar2.position.x;
			return position.x < World.bar2.position.x+World.bar2.length &&  
				   position.x > World.bar2.position.x;
		} else {
			return (position.x < World.bar2.position.x+World.bar2.length &&  
					position.x > World.bar2.position.x) && 
				   (position.y > World.bar2.position.y-Ball.dilimiter && 	
					position.y < World.bar2.position.y+World.bar2.width);
		}
	}
}