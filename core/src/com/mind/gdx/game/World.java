package com.mind.gdx.game;

public class World {

	public static boolean endGame = false;
	public static int maxScore = 3;
	
	public static void update() {	
		GameScreen.bar1.update();
		GameScreen.bar2.update();
		GameScreen.ball.update();
		GameScreen.ability.update();
		checkhitAbility();
		scoreUpdate();
		checkEnding();
	}
	
	private static void checkhitAbility() {
		if(GameScreen.ball.position.x>=WorldRenderer.abilityXPosition&&
		   GameScreen.ball.position.x<=WorldRenderer.abilityXPosition + GameScreen.fireballAbilityImg.getWidth() &&
		   GameScreen.ball.position.y>=WorldRenderer.abilityYPosition&&
		   GameScreen.ball.position.y<=WorldRenderer.abilityYPosition + GameScreen.fireballAbilityImg.getHeight()) {
			Ability.workAbility();
		}
	}
	private static void scoreUpdate(){
		if(GameScreen.ball.position.x < 0) {
			GameScreen.bar1.score+=1;
			//System.out.println("bar1 score = "+GameScreen.bar1.score);
			Ball.hitStatusLeftRight = Ball.hitPlayer2;
			resetBall();
			
		}
		else if(GameScreen.ball.position.x > GameScreen.width) {
			GameScreen.bar2.score+=1;
			//System.out.println("bar2 score = "+GameScreen.bar2.score);
			Ball.hitStatusLeftRight = Ball.hitPlayer1;
			resetBall();
		}
	}
	
	private static void resetBall() {
		Ball.moveStatus = false;
		Ball.updateStartingPosition();
		Ball.speed = Ball.INITSPEED;
		Ball.ballAbilityStatus = Ball.NOTHING;
		Ability.ballAbilityTimer = 0;
		Ability.startCountAbilityTimer = false;
		Ability.abilityTimer = 0;
		Ability.startCountAbilityTimer = false;
		Ability.showAbility = Ability.NOTHING;
	}
	
	private static void checkEnding(){
		if(GameScreen.bar1.score==maxScore || GameScreen.bar2.score==maxScore){
			endGame = true;
			Ball.moveStatus = false;
		}
	}
	
}
