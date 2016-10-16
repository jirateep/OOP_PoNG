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
	
	public static Texture ballImg;
	public static Texture fireballImg;
	public static Texture abilityImg;
	public static Texture fireballAbilityImg;
	public static Texture biggerbatAbilityImg;
	public static Texture smallerbatAbilityImg;
	public static Texture forzenbulletAbilityImg;
	public static Texture showForzenBulletImg;
	public static Texture bar1Img1;
	public static Texture bar1Img2;
	public static Texture bar2Img1;
	public static Texture bar2Img2;
	public static Texture bar3Img1;
	public static Texture bar3Img2;
	public static Texture bar4Img1;
	public static Texture bar4Img2;
	public static Texture bar5Img1;
	public static Texture bar5Img2;
	public static Texture bar6Img1;
	public static Texture bar6Img2;
	public static Texture bar7Img1;
	public static Texture bar7Img2;
	
	public static BitmapFont bar1Score_bitmap;
	public static BitmapFont bar2Score_bitmap;
	public static BitmapFont ending_bitmap;
	
	static GlyphLayout bar1Score;
	static GlyphLayout bar2Score;
	static GlyphLayout ending;
	
	public static int width = 1920;
	public static int height = 1000;
	static float fontsize = 7;
	static float endingfontsize = 10;
	
	public GameScreen(PongGame pongGame) {
		GameScreen.pongGame = pongGame;
		
		bar1Img1 = new Texture("normalBar1P1.png");
		bar1Img2 = new Texture("normalBar1P2.png");
		bar2Img1 = new Texture("normalBar2P1.png");
		bar2Img2 = new Texture("normalBar2P2.png");
		bar3Img1 = new Texture("normalBar3P1.png");
		bar3Img2 = new Texture("normalBar3P2.png");
		bar4Img1 = new Texture("normalBar4P1.png");
		bar4Img2 = new Texture("normalBar4P2.png");
		bar5Img1 = new Texture("normalBar5P1.png");
		bar5Img2 = new Texture("normalBar5P2.png");
		bar6Img1 = new Texture("normalBar6P1.png");
		bar6Img2 = new Texture("normalBar6P2.png");
		bar7Img1 = new Texture("normalBar7P1.png");
		bar7Img2 = new Texture("normalBar7P2.png");
		
		//ballImg = new Texture("newNormalBall.png");
		ballImg = new Texture("normalBall.png");
		fireballImg = new Texture("fireBall.png");
		
		forzenbulletAbilityImg = new Texture("forzenAbility.png");
		biggerbatAbilityImg = new Texture("biggerbatAbility.png");
		smallerbatAbilityImg = new Texture("smallerbatAbility.png");
		fireballAbilityImg = new Texture("fireballAbility.png");
		abilityImg = fireballAbilityImg;
		
		showForzenBulletImg = new Texture("showForzenBullet.png");
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
