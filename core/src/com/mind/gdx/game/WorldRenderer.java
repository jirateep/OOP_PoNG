package com.mind.gdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	private static float bar1ScoreXPosition = GameScreen.width/2+200;
	private static float ScoreYPosition = GameScreen.height-50;
	private static float bar2ScoreXPosition = GameScreen.width/2-200-GameScreen.bar2Score.width;
	private static float endingXPosition;
	private static float endingYPosition;
	
	public static void render() {
		SpriteBatch batch = GameScreen.pongGame.batch;
		batch.begin();
		
		batch.draw(GameScreen.bar1.barImg, GameScreen.bar1.position.x, GameScreen.bar1.position.y);
		batch.draw(GameScreen.bar2.barImg, GameScreen.bar2.position.x, GameScreen.bar2.position.y);
		
		GameScreen.bar1Score.setText(GameScreen.bar1Score_bitmap,Integer.toString(GameScreen.bar1.score));
		GameScreen.bar1Score_bitmap.draw(batch, GameScreen.bar1Score, bar1ScoreXPosition, ScoreYPosition);
		
		
		GameScreen.bar2Score.setText(GameScreen.bar2Score_bitmap,Integer.toString(GameScreen.bar2.score));
		GameScreen.bar2Score_bitmap.draw(batch, GameScreen.bar2Score, bar2ScoreXPosition, ScoreYPosition);
		
		//if(Ball.hitStatusLeftRight==Ball.hitPlayer2)
		batch.draw(GameScreen.ballImg, GameScreen.ball.position.x, GameScreen.ball.position.y);
		//else if(Ball.hitStatusLeftRight==Ball.hitPlayer2)
			//batch.draw(GameScreen.ballImg, GameScreen.ball.position.x, GameScreen.ball.position.y);
		if(World.endGame) {

			if(GameScreen.bar1.score>GameScreen.bar2.score){
				GameScreen.ending.setText(GameScreen.ending_bitmap,"Player1 WIN");
			}else{
				GameScreen.ending.setText(GameScreen.ending_bitmap,"Player2 WIN");
			}
			getEndingPosition();
			GameScreen.ending_bitmap.draw(batch, GameScreen.ending, endingXPosition, endingYPosition);
		}
		batch.end();
	}
	
	private static void getEndingPosition() {
		endingXPosition = (GameScreen.width-GameScreen.ending.width)/2;
		endingYPosition = (GameScreen.height+GameScreen.ending.height)/2;
	}
	
}
