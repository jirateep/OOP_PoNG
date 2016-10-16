package com.mind.gdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	private static float bar1ScoreXPosition = GameScreen.width/2+200;
	private static float ScoreYPosition = GameScreen.height-50;
	private static float bar2ScoreXPosition = GameScreen.width/2-200-GameScreen.bar2Score.width;
	private static float endingXPosition;
	private static float endingYPosition;
	private static float minimumXRange = 400;
	private static float minimumYRange = 200;
	static float abilityXPosition = (GameScreen.width - GameScreen.fireballAbilityImg.getWidth())/2;
	static float abilityYPosition = (GameScreen.height - GameScreen.fireballAbilityImg.getHeight())/2;
	
	private static SpriteBatch batch = GameScreen.pongGame.batch;
	
	public static void render() {
		batch.begin();
		
		drawBats();
		drawScores();
		drawBall();
		drawAbility();
		whenEndGame();
		
		batch.end();
	}
	
	private static void drawAbility() {
		if(Ability.showAbility != Ability.NOTHING) {
			if(Ability.showAbility == Ability.FIREBALL) {
				GameScreen.abilityImg = GameScreen.fireballAbilityImg;
			}
			if(Ability.showAbility == Ability.SMALLERBAT) {
				GameScreen.abilityImg = GameScreen.smallerbatAbilityImg;
			}
			if(Ability.showAbility == Ability.BIGGERBAT) {
				GameScreen.abilityImg = GameScreen.biggerbatAbilityImg;
			}
			if(Ability.showAbility == Ability.FORZENBULLET) {
				GameScreen.abilityImg = GameScreen.forzenbulletAbilityImg;
			}
			batch.draw(GameScreen.abilityImg, abilityXPosition, abilityYPosition);
		} else {
			abilityXPosition = minimumXRange + (int)(Math.random() * (GameScreen.width - 2*minimumXRange)); 
			abilityYPosition = minimumYRange + (int)(Math.random() * (GameScreen.height - 2*minimumYRange)); 
		}
	}
	
	private static void getEndingPosition() {
		endingXPosition = (GameScreen.width-GameScreen.ending.width)/2;
		endingYPosition = (GameScreen.height+GameScreen.ending.height)/2;
	}
	
	private static void whenEndGame() {
		if(World.endGame) {
			if(World.bar1.score>World.bar2.score){
				GameScreen.ending.setText(GameScreen.ending_bitmap,"Player1 WIN");
			}else{
				GameScreen.ending.setText(GameScreen.ending_bitmap,"Player2 WIN");
			}
			getEndingPosition();
			GameScreen.ending_bitmap.draw(batch, GameScreen.ending, endingXPosition, endingYPosition);
		}
	}
	
	private static void drawBall() {
		if(Ball.ballAbilityStatus==Ball.NOTHING) {
				batch.draw(GameScreen.ballImg, World.ball.position.x, World.ball.position.y);
		}
		else if(Ball.ballAbilityStatus==Ball.FIREBALL) {
			batch.draw(GameScreen.fireballImg, World.ball.position.x, World.ball.position.y);
		}
	}
	
	private static void drawBats() {
		batch.draw(World.bar1.barImg, World.bar1.position.x, World.bar1.position.y);
		batch.draw(World.bar2.barImg, World.bar2.position.x, World.bar2.position.y);
		drawForzenBullet();
	}
	
	private static void drawForzenBullet() {
		drawingForzenBullet(World.bar1);
		drawingForzenBullet(World.bar2);
	}
	
	private static void drawingForzenBullet(Bar bar) {
		float showForzenBulletDilimiter = GameScreen.showForzenBulletImg.getWidth();
		float showForzenBulletRadius = showForzenBulletDilimiter/2;
		float distanceBetween = 4;
		float xPosition = bar.position.x + bar.length/2 - showForzenBulletRadius;
		if(bar.forzenBullet == 1) {
			batch.draw(GameScreen.showForzenBulletImg, xPosition, bar.position.y+bar.width/2-showForzenBulletRadius);
		}else if(bar.forzenBullet == 2) {
			batch.draw(GameScreen.showForzenBulletImg, xPosition, bar.position.y+bar.width/2+distanceBetween/2);
			batch.draw(GameScreen.showForzenBulletImg, xPosition, bar.position.y+bar.width/2-distanceBetween/2-showForzenBulletDilimiter);
		}else if(bar.forzenBullet == 3) {
			batch.draw(GameScreen.showForzenBulletImg, xPosition, bar.position.y+bar.width/2+showForzenBulletRadius+distanceBetween);
			batch.draw(GameScreen.showForzenBulletImg, xPosition, bar.position.y+bar.width/2-showForzenBulletRadius);
			batch.draw(GameScreen.showForzenBulletImg, xPosition, bar.position.y+bar.width/2-3*showForzenBulletRadius-distanceBetween);
		}
	}
	
	private static void drawScores() {
		GameScreen.bar1Score.setText(GameScreen.bar1Score_bitmap,Integer.toString(World.bar1.score));
		GameScreen.bar1Score_bitmap.draw(batch, GameScreen.bar1Score, bar1ScoreXPosition, ScoreYPosition);
		
		GameScreen.bar2Score.setText(GameScreen.bar2Score_bitmap,Integer.toString(World.bar2.score));
		GameScreen.bar2Score_bitmap.draw(batch, GameScreen.bar2Score, bar2ScoreXPosition, ScoreYPosition);
	}
}
