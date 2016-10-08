package com.mind.gdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {

	public static void render() {
		SpriteBatch batch = GameScreen.pongGame.batch;
		batch.begin();
		batch.draw(GameScreen.barImg, GameScreen.bar1.position.x, GameScreen.bar1.position.y);
		batch.draw(GameScreen.barImg, GameScreen.bar2.position.x, GameScreen.bar2.position.y);
		batch.draw(GameScreen.ballImg, GameScreen.ball.position.x, GameScreen.ball.position.y);
		batch.end();
	}
	
}
