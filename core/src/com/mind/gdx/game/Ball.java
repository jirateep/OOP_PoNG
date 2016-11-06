package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Ball {

	public Vector2 position;
	
	public static float dilimiter = GameScreen.ballImg.getHeight();
	public static float radius = dilimiter/2;

	public static final int FIREBALL = 1;
	public static final int NOTHING = 0;
	public int ballAbilityStatus = NOTHING;
	
	public static final float INITSPEED = 20;
	public float speed = INITSPEED;
	public float speedX;
	public float speedY;
	public float initSpeedXFactor = 12f/13;
	public float initSpeedYFactor = 5f/13;
	public float speedXFactor;
	public float speedYFactor;
	
	public static float fireballSpeedFactor = 1.3f;
	public static float speedIncreaseFactor = 1.0001f;
	
	private float startingX ;
	private float startingY ;
	
	public static final int hitCeilling = 1;
	public static final int hitFloor = 2;
	public static final int hitPlayer1 = 3;
	public static final int hitPlayer2 = 4;
	public int hitStatusUpDown = hitFloor;
	public int hitStatusLeftRight = hitPlayer1; 
	public int oldHitStatusLeftRight = hitPlayer1;
	public boolean hitShield;

	public boolean moveStatus = false;
	
	public Ball(){
		updateStartingPosition();
		position = new Vector2(startingX,startingY);
		speedXFactor = initSpeedXFactor;
		speedYFactor = initSpeedYFactor;
		getSpeed();
		randomDirectionUpDown();
	}
	
	public void randomDirectionUpDown() {
		if(((int)(Math.random() * 100)) % 2 == 0) {
			hitStatusUpDown = hitFloor;
		} else {
			hitStatusUpDown = hitCeilling;
		}	
	}
	
	public void update() {
		if(!moveStatus && !World.endGame) {
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
	
	public void updateSpeed() {
		speed *= speedIncreaseFactor;
		getSpeed();
	}
	
	public void getSpeed() {
		if(ballAbilityStatus == FIREBALL){
			speedX = speed * fireballSpeedFactor * speedXFactor;
			speedY = speed * fireballSpeedFactor * speedYFactor;
		} else if(ballAbilityStatus == NOTHING){
			//System.out.println("speedYFactor:" + speedYFactor);
			//System.out.println("oldspeedY:" + speedY);
			speedX = speed * speedXFactor;
			speedY = speed * speedYFactor;
			//System.out.println("newspeedY:" + speedY);
		}
	}
	
	public void updateStartingPosition() {
		if(hitStatusLeftRight == hitPlayer2) {
			startingX = World.bar2.position.x + World.bar2.length;
			startingY = World.bar2.position.y + World.bar2.width / 2 - radius;
		}else if (hitStatusLeftRight == hitPlayer1) {
			startingX = World.bar1.position.x - Ball.dilimiter;
			startingY = World.bar1.position.y + World.bar1.width / 2 - radius;
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
			//System.out.print("hitFloor ");
			//System.out.print(speedYFactor + " "+position.y + " ");
			position.y += speedY;
			//System.out.println(position.y);
		}
		if(hitStatusUpDown == hitCeilling) {
			//System.out.print("hitCeilling ");
			//System.out.print(speedYFactor + " "+position.y + " ");
			position.y -= speedY;
			//System.out.println(position.y);
		}
		if(hitStatusLeftRight == hitPlayer1) {
			if(!hitShield) {
			 mkNewSpeed();
			}
			position.x -= speedX;
		}
		if(hitStatusLeftRight == hitPlayer2) {
			if(!hitShield) {
				mkNewSpeed();
			}
			position.x += speedX;
		}
		oldHitStatusLeftRight = hitStatusLeftRight;
	}
	public void mkNewSpeed() {
		int reflectionRadius = 100;
		if(oldHitStatusLeftRight == hitPlayer2 && hitStatusLeftRight == hitPlayer1 && checkReflectRange(World.bar1)) {
			float xHitPosition = position.x + dilimiter;
			float yHitPosition = position.y + radius;
			float differentYPosition = yHitPosition - World.bar1.position.y - World.bar1.width/2;
			float differentXPosition =xHitPosition - GameScreen.width - reflectionRadius;
			float distance = (float)Math.sqrt(Math.pow(differentXPosition,2) + Math.pow(differentYPosition,2));
			speedXFactor = Math.abs(differentXPosition / distance);
			speedYFactor = Math.abs(differentYPosition / distance);
			if(position.y < World.bar1.position.y + World.bar1.width / 2 - radius) {
				hitStatusUpDown = hitCeilling;
			} else {
				hitStatusUpDown = hitFloor;
			}
			oldHitStatusLeftRight = hitPlayer1;
			
		}
		if(oldHitStatusLeftRight == hitPlayer1 && hitStatusLeftRight == hitPlayer2 && checkReflectRange(World.bar2)) {
			float xHitPosition = position.x;
			float yHitPosition = position.y + radius;
			float differentYPosition = yHitPosition - World.bar2.position.y - World.bar1.width / 2;
			float differentXPosition = xHitPosition + reflectionRadius;
			float distance = (float)Math.sqrt(Math.pow(differentXPosition,2) + Math.pow(differentYPosition,2));
			speedXFactor = Math.abs(differentXPosition / distance);
			speedYFactor = Math.abs(differentYPosition / distance);
			if(position.y < World.bar2.position.y + World.bar2.width / 2 - radius) {
				hitStatusUpDown = hitCeilling;
			} else {
				hitStatusUpDown = hitFloor;
			}
			oldHitStatusLeftRight = hitPlayer2;
		}
	}
	
	public boolean checkReflectRange(Bar bar) {
		float reflectSpace = 1;
		float notReflectSpace = 1;
		float nbSpace = reflectSpace * 2 + notReflectSpace;
		float topSpace = (reflectSpace + notReflectSpace) / nbSpace;
		float bottomSpace = reflectSpace / nbSpace;
		return !((position.y < bar.position.y + bar.width * topSpace) && (position.y > bar.position.y + bar.width * bottomSpace));
	}
	public boolean hitingBar1() {
		if(hitStatusLeftRight == hitPlayer2) {
			if(checkHitBar1XPosition()) {
				if(checkHitBarYPosition(World.bar1)) {
					hitShield = false;
					return true;
				}else if(World.bar1.shieldStatus) {
					hitShield = true;
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	
	public boolean checkHitBarYPosition(Bar bar) {
		return position.y > bar.position.y - Ball.dilimiter &&	 
			   position.y < bar.position.y + bar.width;
	}
	
	public boolean checkHitBar1XPosition() {
		return position.x > World.bar1.position.x - Ball.dilimiter &&
				position.x < World.bar1.position.x;
	}
	
	public boolean checkHitBar2XPosition() {
		return position.x < World.bar2.position.x + World.bar2.length &&  
			   position.x > World.bar2.position.x;
	}
	
	public boolean hitingBar2() {
		if(hitStatusLeftRight == hitPlayer1) {
			if(checkHitBar2XPosition()) {
				if(checkHitBarYPosition(World.bar2)) {
					hitShield = false;
					return true;
				}else if(World.bar2.shieldStatus) {
					hitShield = true;
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}
}