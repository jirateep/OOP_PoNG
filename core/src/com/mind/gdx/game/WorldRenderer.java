package com.mind.gdx.game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	private static float bar1ScoreXPosition = GameScreen.width/2+200;
	private static float ScoreYPosition = GameScreen.height-50;
	private static float bar2ScoreXPosition = GameScreen.width/2-210;
	private static float fontsize = 5;
	
	public static void render() {
		SpriteBatch batch = GameScreen.pongGame.batch;
		batch.begin();
		//batch.draw(GameScreen.scoreBackImg,150,(GameScreen.height - GameScreen.scoreBackImg.getHeight())/2);
		GameScreen.bar1Score.getData().setScale(fontsize, fontsize);
		GameScreen.bar2Score.getData().setScale(fontsize, fontsize);
		//GameScreen.bar1Score.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		GameScreen.bar1Score.draw(batch, Integer.toString(GameScreen.bar1.score), bar1ScoreXPosition, ScoreYPosition); 
		GameScreen.bar2Score.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		GameScreen.bar2Score.draw(batch, Integer.toString(GameScreen.bar2.score), bar2ScoreXPosition, ScoreYPosition); 
		batch.draw(GameScreen.bar1.barImg, GameScreen.bar1.position.x, GameScreen.bar1.position.y);
		batch.draw(GameScreen.bar2.barImg, GameScreen.bar2.position.x, GameScreen.bar2.position.y);
		//if(Ball.hitStatusLeftRight==Ball.hitPlayer2)
			batch.draw(GameScreen.ballImg, GameScreen.ball.position.x, GameScreen.ball.position.y);
		//else if(Ball.hitStatusLeftRight==Ball.hitPlayer2)
			//batch.draw(GameScreen.ballImg, GameScreen.ball.position.x, GameScreen.ball.position.y);
		batch.end();
	}

	
}
