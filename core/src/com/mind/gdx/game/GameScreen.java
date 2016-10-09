package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameScreen extends ScreenAdapter {
	public static PongGame pongGame;
	public static Texture barImg1;
	public static Texture barImg2;
	public static Texture ballImg;

	//public static Texture scoreBackImg;
	public static int width = 1800;
	public static int height = 950;
	public static Bar bar1;
	public static Bar bar2;
	public static Ball ball;
	public float player2BarXInit;
	public float player1BarXInit;
	public static BitmapFont bar1Score;
	public static BitmapFont bar2Score;
	
	public GameScreen(PongGame pongGame) {
		GameScreen.pongGame = pongGame;
		
		barImg1 = new Texture("normalBarP1.png");
		barImg2 = new Texture("normalBarP2.png");
		ballImg = new Texture("normalBall_2.png");
		//scoreBackImg = new Texture("scoreBackground.png");
		bar1Score = new BitmapFont();
		bar2Score = new BitmapFont();
		player2BarXInit = 20;
		player1BarXInit = width -player2BarXInit -barImg1.getWidth();
		
		bar1 = new Bar(barImg1,player1BarXInit,Keys.UP,Keys.DOWN);
		bar2 = new Bar(barImg2,player2BarXInit,Keys.W,Keys.S);
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
