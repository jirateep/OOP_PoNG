package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GameScreen extends ScreenAdapter {
	
	public static PongGame pongGame;
	public static World world;
	public static WorldRenderer worldRenderer;
	
	public static Texture forzenBulletImg1;
	public static Texture forzenBulletImg2;
	public static Texture ballImg;
	public static Texture fireballImg;
	public static Texture abilityImg;
	public static Texture fireballAbilityImg;
	public static Texture biggerbatAbilityImg;
	public static Texture smallerbatAbilityImg;
	public static Texture forzenbulletAbilityImg;
	public static Texture shieldAbilityImg;
	public static Texture showForzenBulletImg;
	
	public static Texture [][] barImg = new Texture [2][7];
	public static Texture [][] barFImg = new Texture [2][7];

	public static Texture shieldImg1;
	public static Texture shieldImg2;
	
	//public static FreeTypeFontGenerator ttf_font;
	public static BitmapFont bar1Score_bitmap;
	public static BitmapFont bar2Score_bitmap;
	public static BitmapFont ending_bitmap;
	
	static GlyphLayout bar1Score;
	static GlyphLayout bar2Score;
	static GlyphLayout ending;
	
	public static int width = 960;//1920
	public static int height = 500;//1000
	static float fontsize = 4;
	static float endingfontsize = 7;
	
	public GameScreen(PongGame pongGame) {
		GameScreen.pongGame = pongGame;
		

		barImg[0][0] = new Texture("normalBar1P1.png");
		barImg[1][0] = new Texture("normalBar1P2.png");
		barImg[0][1] = new Texture("normalBar2P1.png");
		barImg[1][1] = new Texture("normalBar2P2.png");
		barImg[0][2] = new Texture("normalBar3P1.png");
		barImg[1][2] = new Texture("normalBar3P2.png");
		barImg[0][3] = new Texture("normalBar4P1.png");
		barImg[1][3] = new Texture("normalBar4P2.png");
		barImg[0][4] = new Texture("normalBar5P1.png");
		barImg[1][4] = new Texture("normalBar5P2.png");
		barImg[0][5] = new Texture("normalBar6P1.png");
		barImg[1][5] = new Texture("normalBar6P2.png");
		barImg[0][6] = new Texture("normalBar7P1.png");
		barImg[1][6] = new Texture("normalBar7P2.png");
		barFImg[0][0] = new Texture("normalBar1FP1.png");
		barFImg[1][0] = new Texture("normalBar1FP2.png");
		barFImg[0][1] = new Texture("normalBar2FP1.png");
		barFImg[1][1] = new Texture("normalBar2FP2.png");
		barFImg[0][2] = new Texture("normalBar3FP1.png");
		barFImg[1][2] = new Texture("normalBar3FP2.png");
		barFImg[0][3] = new Texture("normalBar4FP1.png");
		barFImg[1][3] = new Texture("normalBar4FP2.png");
		barFImg[0][4] = new Texture("normalBar5FP1.png");
		barFImg[1][4] = new Texture("normalBar5FP2.png");
		barFImg[0][5] = new Texture("normalBar6FP1.png");
		barFImg[1][5] = new Texture("normalBar6FP2.png");
		barFImg[0][6] = new Texture("normalBar7FP1.png");
		barFImg[1][6] = new Texture("normalBar7FP2.png");
		
		ballImg = new Texture("normalBall.png");
		fireballImg = new Texture("fireBall.png");
		
		shieldImg1 = new Texture("shieldP1.png");
		shieldImg2 = new Texture("shieldP2.png");
		
		forzenBulletImg1 = new Texture("forzenBulletP1.png");
		forzenBulletImg2 = new Texture("forzenBulletP2.png");
		
		forzenbulletAbilityImg = new Texture("forzenAbility.png");
		biggerbatAbilityImg = new Texture("biggerbatAbility.png");
		smallerbatAbilityImg = new Texture("smallerbatAbility.png");
		fireballAbilityImg = new Texture("fireballAbility.png");
		shieldAbilityImg = new Texture("shieldAbility.png");
		abilityImg = fireballAbilityImg;
		
		showForzenBulletImg = new Texture("showForzenBullet.png");
		//ttf_font = new FreeTypeFontGenerator("NESCyrillic.ttf");
		//FreeTypeFontGenerator generator = new FreeTypeFontGenerator("NESCyrillic.ttf");
		bar1Score_bitmap = new BitmapFont();
		bar2Score_bitmap = new BitmapFont();
		ending_bitmap = new BitmapFont();
		
		world = new World();
		
		bar1Score = new GlyphLayout(bar1Score_bitmap, Integer.toString(World.bar1.score));
		bar2Score = new GlyphLayout(bar2Score_bitmap, Integer.toString(World.bar2.score));
		ending = new GlyphLayout(ending_bitmap, "Player1 WIN");
		
		bar1Score_bitmap.getData().setScale(fontsize, fontsize);
		bar2Score_bitmap.getData().setScale(fontsize, fontsize);
		ending_bitmap.getData().setScale(endingfontsize, endingfontsize);
	}
	
	@Override
	public void render(float delta) {
		//System.out.println("Hello" + delta);
		World.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		WorldRenderer.render();
	}
}
