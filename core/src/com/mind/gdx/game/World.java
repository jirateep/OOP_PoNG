package com.mind.gdx.game;

public class World {

	public static boolean endGame = false;
	public static int maxScore = 3;
	public static void update() {	
		GameScreen.bar1.update();
		GameScreen.bar2.update();
		GameScreen.ball.update();
		scoreUpdate();
		checkEnding();
	}
	
	private static void scoreUpdate(){
		if(GameScreen.ball.position.x < 0) {
			GameScreen.bar1.score+=1;
			//System.out.println("bar1 score = "+GameScreen.bar1.score);
			Ball.moveStatus = false;
			Ball.hitStatusLeftRight = Ball.hitPlayer2;
			Ball.updateStartingPosition();
			Ball.speed = Ball.INITSPEED;
		}
		else if(GameScreen.ball.position.x > GameScreen.width) {
			GameScreen.bar2.score+=1;
			//System.out.println("bar2 score = "+GameScreen.bar2.score);
			Ball.moveStatus = false;
			Ball.hitStatusLeftRight = Ball.hitPlayer1;
			Ball.updateStartingPosition();
			Ball.speed = Ball.INITSPEED;
		}
	}
	
	
	private static void checkEnding(){
		if(GameScreen.bar1.score==maxScore || GameScreen.bar2.score==maxScore){
			endGame = true;
			Ball.moveStatus = false;
		}
	}
	
}
