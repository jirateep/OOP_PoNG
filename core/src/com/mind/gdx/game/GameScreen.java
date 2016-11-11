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
	
	public static Texture [] frozenBulletImg = new Texture [2];

	public static Texture ballImg;
	public static Texture fireballImg;
	public static Texture abilityImg;
	
	public static Texture [] abilityListImg;
	public static Texture showFrozenBulletImg;
	
	public static Texture [] score = new Texture [10];

	public static final int NBOFPLAYERS = 2;
	public static final int NBOFFROZENSTATUS = 2;
	
	public static Texture [][][] barImg = new Texture [NBOFPLAYERS][NBOFFROZENSTATUS][Bar.maxSize];
	public static Texture [] sticky = new Texture [Bar.maxSize];

	public static Texture shieldImg1;
	public static Texture shieldImg2;
	
	public static Texture pauseImg;
	public static Texture resumeImg;
	public static Texture restartImg;
	public static Texture selectedResumeImg;
	public static Texture selectedRestartImg;
	
	public static Texture pongHomeImg;
	public static Texture onePlayerImg;
	public static Texture twoPlayersImg;
	public static Texture settingImg;
	public static Texture helpImg;
	public static Texture selectedOnePlayerImg;
	public static Texture selectedTwoPlayersImg;
	public static Texture selectedSettingImg;
	public static Texture selectedHelpImg;
	
	//public static FreeTypeFontGenerator ttf_font;
	public static BitmapFont bar1Score_bitmap;
	public static BitmapFont bar2Score_bitmap;
	public static BitmapFont ending_bitmap;
	
	static GlyphLayout ending;
	
	public static int width = 1536;//1920
	public static int height = 800;//1000
	static float fontsize = 6;
	static float endingfontsize = 8;
	
	public GameScreen(PongGame pongGame) {
		GameScreen.pongGame = pongGame;
		
		score[0] = new Texture("0White.png");
		score[1] = new Texture("1White.png");
		score[2] = new Texture("2White.png");
		score[3] = new Texture("3White.png");
		score[4] = new Texture("4White.png");
		score[5] = new Texture("5White.png");
		score[6] = new Texture("6White.png");
		score[7] = new Texture("7White.png");
		score[8] = new Texture("8White.png");
		score[9] = new Texture("9White.png");
		
		barImg[Bar.PLAYER1][Bar.NOTFROZEN][0] = new Texture("normalBar1P1.png");
		barImg[Bar.PLAYER2][Bar.NOTFROZEN][0] = new Texture("normalBar1P2.png");
		barImg[Bar.PLAYER1][Bar.NOTFROZEN][1] = new Texture("normalBar2P1.png");
		barImg[Bar.PLAYER2][Bar.NOTFROZEN][1] = new Texture("normalBar2P2.png");
		barImg[Bar.PLAYER1][Bar.NOTFROZEN][2] = new Texture("normalBar3P1.png");
		barImg[Bar.PLAYER2][Bar.NOTFROZEN][2] = new Texture("normalBar3P2.png");
		barImg[Bar.PLAYER1][Bar.NOTFROZEN][3] = new Texture("normalBar4P1.png");
		barImg[Bar.PLAYER2][Bar.NOTFROZEN][3] = new Texture("normalBar4P2.png");
		barImg[Bar.PLAYER1][Bar.NOTFROZEN][4] = new Texture("normalBar5P1.png");
		barImg[Bar.PLAYER2][Bar.NOTFROZEN][4] = new Texture("normalBar5P2.png");
		barImg[Bar.PLAYER1][Bar.NOTFROZEN][5] = new Texture("normalBar6P1.png");
		barImg[Bar.PLAYER2][Bar.NOTFROZEN][5] = new Texture("normalBar6P2.png");
		barImg[Bar.PLAYER1][Bar.NOTFROZEN][6] = new Texture("normalBar7P1.png");
		barImg[Bar.PLAYER2][Bar.NOTFROZEN][6] = new Texture("normalBar7P2.png");
		barImg[Bar.PLAYER1][Bar.FROZEN][0] = new Texture("normalBar1FP1.png");
		barImg[Bar.PLAYER2][Bar.FROZEN][0] = new Texture("normalBar1FP2.png");
		barImg[Bar.PLAYER1][Bar.FROZEN][1] = new Texture("normalBar2FP1.png");
		barImg[Bar.PLAYER2][Bar.FROZEN][1] = new Texture("normalBar2FP2.png");
		barImg[Bar.PLAYER1][Bar.FROZEN][2] = new Texture("normalBar3FP1.png");
		barImg[Bar.PLAYER2][Bar.FROZEN][2] = new Texture("normalBar3FP2.png");
		barImg[Bar.PLAYER1][Bar.FROZEN][3] = new Texture("normalBar4FP1.png");
		barImg[Bar.PLAYER2][Bar.FROZEN][3] = new Texture("normalBar4FP2.png");
		barImg[Bar.PLAYER1][Bar.FROZEN][4] = new Texture("normalBar5FP1.png");
		barImg[Bar.PLAYER2][Bar.FROZEN][4] = new Texture("normalBar5FP2.png");
		barImg[Bar.PLAYER1][Bar.FROZEN][5] = new Texture("normalBar6FP1.png");
		barImg[Bar.PLAYER2][Bar.FROZEN][5] = new Texture("normalBar6FP2.png");
		barImg[Bar.PLAYER1][Bar.FROZEN][6] = new Texture("normalBar7FP1.png");
		barImg[Bar.PLAYER2][Bar.FROZEN][6] = new Texture("normalBar7FP2.png");
		
		sticky[0] = new Texture("sticky1.png");
		sticky[1] = new Texture("sticky2.png");
		sticky[2] = new Texture("sticky3.png");
		sticky[3] = new Texture("sticky4.png");
		sticky[4] = new Texture("sticky5.png");
		sticky[5] = new Texture("sticky6.png");
		sticky[6] = new Texture("sticky7.png");
		
		ballImg = new Texture("normalBall.png");
		fireballImg = new Texture("fireBall.png");
		
		shieldImg1 = new Texture("shieldP1.png");
		shieldImg2 = new Texture("shieldP2.png");
		
		frozenBulletImg[Bar.PLAYER1] = new Texture("frozenBulletP1.png");
		frozenBulletImg[Bar.PLAYER2] = new Texture("frozenBulletP2.png");
		
		abilityListImg = new Texture [Ability.numberOfAbility+1];
		abilityListImg[Ability.FROZENBULLET] = new Texture("frozenAbility.png");
		abilityListImg[Ability.BIGGERBAT] = new Texture("biggerbatAbility.png");
		abilityListImg[Ability.SMALLERBAT] = new Texture("smallerbatAbility.png");
		abilityListImg[Ability.FIREBALL] = new Texture("fireballAbility.png");
		abilityListImg[Ability.SHIELD] = new Texture("shieldAbility.png");
		abilityListImg[Ability.STICKYBAT] = new Texture("stickybatAbility.png");
		abilityImg = abilityListImg[Ability.FIREBALL];
		
		showFrozenBulletImg = new Texture("showFrozenBullet.png");
		
		pauseImg = new Texture("pause.png");
		resumeImg = new Texture("resume.png");
		selectedResumeImg = new Texture("resumeSelected.png");
		restartImg = new Texture("restart.png");
		selectedRestartImg = new Texture("restartSelected.png");
		pongHomeImg = new Texture("PoNG.png");
		onePlayerImg = new Texture("1player.png");
		twoPlayersImg = new Texture("2players.png");
		settingImg = new Texture("setting.png");
		helpImg = new Texture("help.png");
		selectedOnePlayerImg = new Texture("1playerSelected.png");
		selectedTwoPlayersImg = new Texture("2playersSelected.png");
		selectedSettingImg = new Texture("settingSelected.png");
		selectedHelpImg = new Texture("helpSelected.png");
		
		//ttf_font = new FreeTypeFontGenerator("NESCyrillic.ttf");
		//FreeTypeFontGenerator generator = new FreeTypeFontGenerator("NESCyrillic.ttf");
		bar1Score_bitmap = new BitmapFont();
		bar2Score_bitmap = new BitmapFont();
		ending_bitmap = new BitmapFont();
		
		world = new World();
		
		ending = new GlyphLayout(ending_bitmap, "Player1 WIN");
		
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
