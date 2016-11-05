package com.mind.gdx.game;

import com.badlogic.gdx.Input.Keys;

public class World {

	public static boolean endGame = false;
	public static int maxScore = 5;
	public static Bar bar1;
	public static Bar bar2;
	public static Ball ball;
	public static Ability ability;
	public static int maxBullet = 20;
	public static Bullet [] bullets;
	
	public float player2BarXInit;
	public float player1BarXInit;
	
	public World() {
		player2BarXInit = 20;
		player1BarXInit = GameScreen.width - player2BarXInit - GameScreen.barImg[0][1].getWidth();
		
		bar1 = new Bar(GameScreen.barImg[0][1],player1BarXInit,Keys.UP,Keys.DOWN,Keys.ENTER,1);
		bar2 = new Bar(GameScreen.barImg[1][1],player2BarXInit,Keys.W,Keys.S,Keys.SPACE,2);
		
		ball = new Ball();
		
		ability = new Ability();
		
		bullets = new Bullet [maxBullet];
		
		for(int i = 0 ; i < bullets.length ; i++) {
			bullets[i] = null;
		}
		
	}
	
	public static void update() {	
		bar1.update();
		bar2.update();
		ball.update();
		Bullet.update();
		Ability.update();
		ballHitAbility();
		bulletHitBar();
		scoreUpdate();
		checkEnding();
	}
	
	private static void bulletHitBar() {
		for(int i = 0 ; i < bullets.length ; i++) {
			if(bullets[i] != null) {
				if(bullets[i].owner == Bullet.PLAYER1) {
					if(bullets[i].xPosition < bar2.position.x + bar2.length) {
						if(bullets[i].yPosition >= bar2.position.y && bullets[i].yPosition <= bar2.position.y + bar2.width) {
							bar2.forzenStatus = true;
						}
						bullets[i] = null;
					}
				}else if(bullets[i].owner == Bullet.PLAYER2) {
					if(bullets[i].xPosition > bar1.position.x - GameScreen.forzenBulletImg2.getWidth()) {
						if(bullets[i].yPosition >= bar1.position.y && bullets[i].yPosition <= bar1.position.y + bar1.width) {
							bar1.forzenStatus = true;
						}
						bullets[i] = null;
					}
				}
				
			}
		}
	}
	
	private static void ballHitAbility() {
		if(checkhitAbility()) {
			Ability.workAbility();			
		}
	}
	
	private static boolean checkhitAbility() {
		float ballXCenter = ball.position.x + Ball.radius;
		float ballYCenter = ball.position.y + Ball.radius;
		float abilityXCenter = WorldRenderer.abilityXPosition + (GameScreen.abilityImg.getWidth() / 2);
		float abilityYCenter = WorldRenderer.abilityYPosition + (GameScreen.abilityImg.getHeight() / 2);
		float xDifferent = ballXCenter - abilityXCenter;
		float yDifferent = ballYCenter - abilityYCenter;
		float distance = (float) Math.sqrt(Math.pow(xDifferent,2) + Math.pow(yDifferent,2));
		float checkDistance = Ball.radius + GameScreen.abilityImg.getHeight() / 2;
		return distance <= checkDistance;
	}
	private static void scoreUpdate(){
		if(ball.position.x < 0) {
			bar1.score += 1;
			//System.out.println("bar1 score = "+GameScreen.bar1.score);
			ball.hitStatusLeftRight = Ball.hitPlayer2;
			reset();
		}
		else if(ball.position.x > GameScreen.width) {
			bar2.score += 1;
			//System.out.println("bar2 score = "+GameScreen.bar2.score);
			ball.hitStatusLeftRight = Ball.hitPlayer1;
			reset();
		}
	}
	
	private static void reset() {
		resetBall();
		resetBats();
		resetAbility();
		resetBullet();
	}
	
	private static void resetBullet() {
		for(int i = 0 ; i < bullets.length ; i++) {
			bullets[i]=null;
		}
	}
	
	private static void resetBats() {
		bar1.size = 2;
		bar2.size = 2;
		bar1.forzenBullet = 0;
		bar2.forzenBullet = 0;
		bar1.forzenStatus = false;
		bar2.forzenStatus = false;
		bar1.shieldStatus = false;
		bar2.shieldStatus = false;
		Bar.updateBarImg();
		Bar.updateWidthHeight();
	}
	
	private static void resetBall() {
		ball.moveStatus = false;
		ball.updateStartingPosition();
		ball.speed = Ball.INITSPEED;
		ball.speedXFactor = ball.initSpeedXFactor;
		ball.speedYFactor = ball.initSpeedYFactor;
		ball.randomDirectionUpDown();
		//System.out.println("speedYFactor" + ball.speedYFactor);
		ball.oldHitStatusLeftRight = ball.hitStatusLeftRight;
		ball.ballAbilityStatus = Ball.NOTHING;
		Ability.ballAbilityTimer = 0;
	}
	
	private static void resetAbility() {
		Ability.startCountAbilityTimer = false;
		Ability.abilityTimer = 0;
		Ability.startCountAbilityTimer = false;
		Ability.showAbility = Ability.NOTHING;
	}
	
	private static void checkEnding() {
		if((bar1.score == maxScore) || (bar2.score == maxScore)){
			endGame = true;
			ball.moveStatus = false;
		}
	}
	
}
