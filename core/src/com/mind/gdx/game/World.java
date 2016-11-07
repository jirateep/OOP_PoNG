package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class World {

	public static boolean endGame = false;
	public static int maxScore = 5;
	public static Bar bar1;
	public static Bar bar2;
	public static Ball ball;
	public static Ability ability;
	public static int maxBullet = 40;
	public static Bullet [] bullets;
	
	public static boolean pauseStatus = false;
	private static int pressPause = Keys.P;
	public static int selectedPause = 1;
	
	static boolean menuStatus = true;
	static int selectedMenu = 1;
	
	public static float player2BarXInit;
	public static float player1BarXInit;
	
	private static int bar1Up = Keys.UP;
	private static int bar1Down = Keys.DOWN;
	private static int bar1Active = Keys.L;
	private static int bar2Up = Keys.W;
	private static int bar2Down = Keys.S;
	private static int bar2Active = Keys.G;
	
	public World() {
		player2BarXInit = 20;
		player1BarXInit = GameScreen.width - player2BarXInit - GameScreen.barImg[0][1].getWidth();
		
		bar1 = new Bar(GameScreen.barImg[0][1],player1BarXInit,Keys.UP,Keys.DOWN,Keys.L,1);
		bar2 = new Bar(GameScreen.barImg[1][1],player2BarXInit,Keys.W,Keys.S,Keys.G,2);
		
		ball = new Ball();
		
		ability = new Ability();
		
		bullets = new Bullet [maxBullet];
		
		for(int i = 0 ; i < bullets.length ; i++) {
			bullets[i] = null;
		}
		
	}
	
	public static void update() {
		if(menuStatus) {
			homeMenu();
		} else {
			if(!pauseStatus) {
				bar1.update();
				bar2.update();
				ball.update();
				Bullet.update();
				Ability.update();
				ballHitAbility();
				bulletHitBar();
				scoreUpdate();
				checkEnding();
			} else {
				selectedPause = updateSelected(selectedPause,2);
				selectedPauseEnd();
			}
			pauseGame();
		}
	}
	
	public static void selectedPauseEnd() {
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			if(selectedPause == 1) {
				resume();
			} else if(selectedPause == 2){
				restart();
			}
		}
	}
	
	public static void resume() {
		pauseStatus = false;
		selectedPause = 1;
	}
	
	public static void restart() {
		bar1.score = 0;
		bar2.score = 0;
		reset();
		ball.hitStatusLeftRight = Ball.hitPlayer1;
		pauseStatus = false;
		selectedPause = 1;
		endGame = false;
	}
	
	public static void homeMenu() {
			selectedMenu = updateSelected(selectedMenu,4);
			selectedMenuEnd();
	}
	
	public static void selectedMenuEnd() {
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			if(selectedMenu == 1) {
				startingWithOnePlayer();
			} else if(selectedMenu == 2){
				startingWithTwoPlayerSetting();
			} else if(selectedMenu == 3){
				setting();
			} else if(selectedMenu == 4){
				help();
			}
		}
	}
	
	public static void startingWithOnePlayer() {
		bar1 = new Bar(GameScreen.barImg[0][1],player1BarXInit,bar1Up,bar1Down,bar1Active,1);
		bar2 = new Bar(GameScreen.barImg[1][1],player2BarXInit,Bar.BOT,Bar.BOT,Bar.BOT,2);
		menuStatus = false;
	}
	
	public static void startingWithTwoPlayerSetting() {
		bar1 = new Bar(GameScreen.barImg[0][1],player1BarXInit,bar1Up,bar1Down,bar1Active,1);
		bar2 = new Bar(GameScreen.barImg[1][1],player2BarXInit,bar2Up,bar2Down,bar2Active,2);
		menuStatus = false;
	}
	
	public static void help() {
		/////////////////////////////
	}
	
	public static void setting() {
		/////////////////////////////
	}
	
	public static int updateSelected(int selected, int max) {
		if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			selected--;
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			selected++;
		}
		if(selected > max) {
			selected = max;
		}
		if(selected < 1) {
			selected = 1;
		}
		return selected;
	}
	
	private static void pauseGame() {
		if(Gdx.input.isKeyJustPressed(pressPause)) {
			pauseStatus = !pauseStatus;
		}
		
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
		if(World.bar2.pressUp == Bar.BOT && ball.hitStatusLeftRight == Ball.hitPlayer2) {
			ball.moveStatus = true;
		}
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
		bar.forzenBullet = 0;
		bar.forzenStatus = false;
		bar.forzenCount = 0;
		bar.shieldStatus = false;
		bar.shieldStatus = false;
		bar.stickybatStatus = false;
		bar.stickybatCount = 0;
		bar.ballStayAtSamePosition = false;
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
