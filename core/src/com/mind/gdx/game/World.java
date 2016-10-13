package com.mind.gdx.game;

import com.badlogic.gdx.Input.Keys;

public class World {

	public static boolean endGame = false;
	public static int maxScore = 5;
	public static Bar bar1;
	public static Bar bar2;
	public static Ball ball;
	public static Ability ability;
	
	public float player2BarXInit;
	public float player1BarXInit;
	
	public World() {
	
		player2BarXInit = 20;
		player1BarXInit = GameScreen.width -player2BarXInit - GameScreen.barImg1.getWidth();
		
		bar1 = new Bar(GameScreen.barImg1,player1BarXInit,Keys.UP,Keys.DOWN);
		bar2 = new Bar(GameScreen.barImg2,player2BarXInit,Keys.W,Keys.S);
		
		ball = new Ball();
		
		ability = new Ability();
		
	}
	
	public static void update() {	
		bar1.update();
		bar2.update();
		ball.update();
		Ability.update();
		checkhitAbility();
		scoreUpdate();
		checkEnding();
	}
	
	private static void checkhitAbility() {
		float ballXCenter = ball.position.x + Ball.radius;
		float ballYCenter = ball.position.y + Ball.radius;
		float abilityXCenter = WorldRenderer.abilityXPosition+(GameScreen.abilityImg.getWidth()/2);
		float abilityYCenter = WorldRenderer.abilityYPosition+(GameScreen.abilityImg.getHeight()/2);
		float checkingRadius = Ball.radius+(GameScreen.abilityImg.getWidth()/2)+10;
		float abilityXLowerBand = abilityXCenter-checkingRadius;
		float abilityXUpperBand = abilityXCenter+checkingRadius;
		if(ballXCenter > abilityXLowerBand &&
		   ballXCenter < abilityXUpperBand &&
		   ballYCenter < Math.sqrt(Math.pow(checkingRadius,2)-Math.pow(ball.position.x-abilityXCenter,2))+abilityYCenter &&
		   ballYCenter > -Math.sqrt(Math.pow(checkingRadius,2)-Math.pow(ball.position.x-abilityXCenter,2))+abilityYCenter) {
			Ability.workAbility();
		}
	}
	private static void scoreUpdate(){
		if(ball.position.x < 0) {
			bar1.score+=1;
			//System.out.println("bar1 score = "+GameScreen.bar1.score);
			Ball.hitStatusLeftRight = Ball.hitPlayer2;
			reset();
			
		}
		else if(ball.position.x > GameScreen.width) {
			bar2.score+=1;
			//System.out.println("bar2 score = "+GameScreen.bar2.score);
			Ball.hitStatusLeftRight = Ball.hitPlayer1;
			reset();
		}
	}
	
	private static void reset(){
		resetBall();
		resetAbility();
	}
	
	private static void resetBall() {
		Ball.moveStatus = false;
		Ball.updateStartingPosition();
		Ball.speed = Ball.INITSPEED;
		Ball.ballAbilityStatus = Ball.NOTHING;
		Ability.ballAbilityTimer = 0;
	}
	
	private static void resetAbility() {
		Ability.startCountAbilityTimer = false;
		Ability.abilityTimer = 0;
		Ability.startCountAbilityTimer = false;
		Ability.showAbility = Ability.NOTHING;
	}
	
	private static void checkEnding(){
		if(bar1.score==maxScore || bar2.score==maxScore){
			endGame = true;
			Ball.moveStatus = false;
		}
	}
	
}
