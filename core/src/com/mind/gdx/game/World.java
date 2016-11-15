package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class World {

	public static boolean endStatus = false;
	public static int maxScore = 5;
	public static Bar bar1;
	public static Bar bar2;
	public static Ball ball;
	public static Ability ability;
	public static int maxBullet = 40;
	public static Bullet [] bullets;
	
	public static HomeMenu homeMenu;
	public static PauseMenu pauseMenu;
	public static EndMenu endMenu;
	public static boolean pauseStatus = false;
	private static int pressPause = Keys.P;
	private static int pressMute = Keys.M;
	public static int selectedPause = 1;
	
	public static boolean menuStatus = true;
	public static boolean helpStatus = false;
	public static boolean creditsStatus = false;
	
	public static float player2BarXInit;
	public static float player1BarXInit;
	
	public static SoundEffect soundEffect;
	public static boolean muteStatus = false;
	
	public World() {
		player2BarXInit = 20;
		player1BarXInit = GameScreen.width - player2BarXInit - GameScreen.barImg[Bar.PLAYER1][Bar.NOTFROZEN][1].getWidth();
		
		soundEffect = new SoundEffect();
		
		bar1 = new Bar(GameScreen.barImg[Bar.PLAYER1][Bar.NOTFROZEN][1],player1BarXInit,Keys.UP,Keys.DOWN,Keys.L,Bar.PLAYER1,soundEffect);
		bar2 = new Bar(GameScreen.barImg[Bar.PLAYER2][Bar.NOTFROZEN][1],player2BarXInit,Keys.W,Keys.S,Keys.G,Bar.PLAYER2,soundEffect);
		
		ball = new Ball(soundEffect);
		
		ability = new Ability(soundEffect);
		
		bullets = new Bullet [maxBullet];
		for(int i = 0 ; i < bullets.length ; i++) {
			bullets[i] = null;
		}
		
		homeMenu = new HomeMenu(soundEffect);
		pauseMenu = new PauseMenu(soundEffect);
		endMenu = new EndMenu(soundEffect);
		
	}
	
	public static void update() {
		if(menuStatus) {
			homeMenu.update();
		} else {
			if(!pauseStatus) {
				bar1.update();
				bar2.update();
				ball.update();
				Bullet.update();
				ability.update();
				ballHitAbility();
				bulletHitBar();
				scoreUpdate();
				checkEnding();
			} else {
				pauseMenu.update();
			}
			if(!endStatus) {
				pauseStatus = pauseAndMuteGame(pauseStatus,pressPause);
			} else {
				endMenu.update();
			}
		}
		muteStatus = pauseAndMuteGame(muteStatus,pressMute);
	}
	
	private static boolean pauseAndMuteGame(boolean status,int press) {
		if(Gdx.input.isKeyJustPressed(press)) {
			status = !status;
		}
		return status;
		
	}
	
	private static void bulletHitBar() {
		for(int i = 0 ; i < bullets.length ; i++) {
			if(bullets[i] != null) {
				if(bullets[i].owner == Bar.PLAYER1) {
					if(bullets[i].xPosition < bar2.position.x + bar2.length) {
						if(bullets[i].yPosition >= bar2.position.y && bullets[i].yPosition <= bar2.position.y + bar2.width) {
							bar2.frozenStatus = true;
						}
						bullets[i] = null;
					}
				}else if(bullets[i].owner == Bar.PLAYER2) {
					if(bullets[i].xPosition > bar1.position.x - GameScreen.frozenBulletImg[Bar.PLAYER1].getWidth()) {
						if(bullets[i].yPosition >= bar1.position.y && bullets[i].yPosition <= bar1.position.y + bar1.width) {
							bar1.frozenStatus = true;
						}
						bullets[i] = null;
					}
				}
			}
		}
	}
	
	private static void ballHitAbility() {
		if(checkhitAbility()) {
			ability.workAbility();			
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
	
	public static void reset() {
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
		resetBat(bar1);
		resetBat(bar2);
		Bar.updateBarImg();
		Bar.updateWidthHeight();
	}
	
	private static void resetBat(Bar bar) {
		bar.size = 2;
		bar.frozenBullet = 0;
		bar.frozenStatus = false;
		bar.frozenCount = 0;
		bar.shieldStatus = false;
		bar.shieldStatus = false;
		bar.stickybatStatus = false;
		bar.stickybatCount = 0;
		bar.countNextRandom = 0;
		bar.countRandom = bar.initCountRandom;
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
			endStatus = true;
			ball.moveStatus = false;
		}
	}
	
}
