package com.mind.gdx.game;

public class Bullet {

	public int owner;
	public float xPosition;
	public float yPosition;
	public static float speed = 80;

	public Bullet(float x, float y, int player) {
		xPosition = x;
		yPosition = y;
		owner = player;
	}
	
	public static void update() {
		for(int i = 0 ; i < World.bullets.length ; i++) {
			if(World.bullets[i] != null) {
				if(World.bullets[i].owner == Bar.PLAYER1) {
					World.bullets[i].xPosition -= speed;
				}
				if(World.bullets[i].owner == Bar.PLAYER2) {
					World.bullets[i].xPosition += speed;
				}	
			}
		}
	}
	
	public static int findAvailable() {
		for(int i = 0 ; i < World.bullets.length ; i++) {
			if(World.bullets[i] == null) {
				return i;
			}
		}
		return -1;
	}
}
