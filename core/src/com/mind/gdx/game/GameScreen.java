package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter {
	public static PongGame pongGame;
	public static Texture barImg;
	public static Texture ballImg;
	public static int width = 1800;
	public static int height = 950;
	public static Bar bar1;
	public static Bar bar2;
	public static Ball ball;
	public float player2_x_init = 20;
	public float player1_x_init = width -20 -Bar.length;
	public GameScreen(PongGame pongGame) {
		this.pongGame = pongGame;
		barImg = new Texture("size2.jpg");
		ballImg = new Texture("ball_normal.png");
		bar1 = new Bar(player1_x_init,Keys.UP,Keys.DOWN);
		bar2 = new Bar(player2_x_init,Keys.W,Keys.S);
		ball = new Ball();
	}
	
	@Override
	public void render(float delta){
		//System.out.println("Hello" + delta);
		World.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		WorldRenderer.render();
	}
}
