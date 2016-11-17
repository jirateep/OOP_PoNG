package com.mind.gdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {

	private static float centerX = GameScreen.width / 2;
	private static float centerY = GameScreen.height / 2;
	
	private static float ScoreYPosition = centerY - GameScreen.score[0].getHeight() / 2;
	
	private static float oneHalfFour = (centerX - GameScreen.score[0].getWidth()) / 2;
	private static float bar2ScoreXPosition = oneHalfFour;
	private static float bar1ScoreXPosition = GameScreen.width / 2 + oneHalfFour;

	static float abilityXPosition = (GameScreen.width - GameScreen.abilityListImg[Ability.FIREBALL].getWidth()) / 2;
	static float abilityYPosition = (GameScreen.height - GameScreen.abilityListImg[Ability.FIREBALL].getHeight()) / 2;
	private static float shield1XPosition = World.bar1.position.x - GameScreen.shieldImg[0].getWidth();
	private static float shield2XPosition = World.bar2.position.x + World.bar2.length;
	private static float shieldYPosition = 0;

	private static float [] helpPosition = new float [2];
	private static float [] creditsPosition = new float [2];
	private static float [] endPosition = new float [2];
	private static float [] pongHomePosition = new float[2];
	private static float [] pausePosition = new float [2];

	private static float [][] pauseMenuChoices = new float [PauseMenu.NBOFMENU][2];
	private static float [][] homeMenuChoices = new float [HomeMenu.NBOFMENU][2];
	private static float [][] endMenuChoices = new float [EndMenu.NBOFMENU][2];
	
	private static SpriteBatch batch = GameScreen.pongGame.batch;
	
	public static void render() {
		setPosition();
		
		batch.begin();
		
		if(World.menuStatus) {
			drawHomeMenu();
		} else {
			drawBats();
			drawScores();
			if(!World.pauseStatus) {
					drawBall();
					drawAbility();
					drawBullet();
					drawShield();
					drawSticky();
			} else {
				drawWhenPause();
			}
		}
		if(World.endStatus) {
			whenEndGame();
		}

		batch.end();
	}
	
	private static void setPosition() {
		pongHomePosition[0] = mkImgXCenter(GameScreen.pongHomeImg);
		pongHomePosition[1] = centerY + 150;
		for(int i = 0 ; i < HomeMenu.NBOFMENU ; i++) {
			homeMenuChoices[i][0] = mkImgXCenter(GameScreen.homeMenuChoicesImg[i][GameScreen.UNSELECTED]);
			homeMenuChoices[i][1] = centerY - i * 100;
		}
		
		pausePosition[0] = mkImgXCenter(GameScreen.pauseImg);
		pausePosition[1] = centerY + 120;
		for(int i = 0 ; i < PauseMenu.NBOFMENU ; i++) {
			pauseMenuChoices[i][0] = mkImgXCenter(GameScreen.pauseMenuChoicesImg[i][GameScreen.UNSELECTED]);
			pauseMenuChoices[i][1] = pausePosition[1] - 120 - i * 100;
		}
		
		endPosition[0] = mkImgXCenter(GameScreen.nowEndGameImg);
		endPosition[1] = centerY - 10;
		for(int i = 0 ; i < EndMenu.NBOFMENU ; i++) {
			endMenuChoices[i][0] = mkImgXCenter(GameScreen.endMenuChoicesImg[i][GameScreen.UNSELECTED]);
			endMenuChoices[i][1] = endPosition[1] - 150 - i * 100;
		}
		
		helpPosition[0] = mkImgXCenter(GameScreen.helpImg);
		helpPosition[1] = mkImgYCenter(GameScreen.helpImg);
		
		creditsPosition[0] = mkImgXCenter(GameScreen.creditsImg);
		creditsPosition[1] = mkImgYCenter(GameScreen.creditsImg);
	}
	
	private static void drawHomeMenu() {
		if(!World.helpStatus && !World.creditsStatus) {
			batch.draw(GameScreen.pongHomeImg,pongHomePosition[0],pongHomePosition[1]);
			for(int i = 0 ; i < HomeMenu.NBOFMENU ; i++) {
				batch.draw(GameScreen.homeMenuChoicesImg[i][GameScreen.UNSELECTED],homeMenuChoices[i][0],homeMenuChoices[i][1]);
			}
			drawSelectedHomeMenu();
		} else if(World.helpStatus) {
			batch.draw(GameScreen.helpImg,helpPosition[0],helpPosition[1]);
		} else if(World.creditsStatus) {
			batch.draw(GameScreen.creditsImg,creditsPosition[0],creditsPosition[1]);
		}
	}
	
	private static void drawSelectedHomeMenu() {
		batch.draw(GameScreen.homeMenuChoicesImg[HomeMenu.selectedHomeMenu][GameScreen.SELECTED],
				   homeMenuChoices[HomeMenu.selectedHomeMenu][0],homeMenuChoices[HomeMenu.selectedHomeMenu][1]);
	}
	
	private static void drawWhenPause() {	
		batch.draw(GameScreen.pauseImg,pausePosition[0],pausePosition[1]);
		for(int i = 0 ; i < PauseMenu.NBOFMENU ; i++) {
			batch.draw(GameScreen.pauseMenuChoicesImg[i][GameScreen.UNSELECTED],pauseMenuChoices[i][0],pauseMenuChoices[i][1]);
		}
		drawSelectedPause();
	}
	
	private static void drawSelectedPause() {
		batch.draw(GameScreen.pauseMenuChoicesImg[PauseMenu.selectedPauseMenu][GameScreen.SELECTED],
				   pauseMenuChoices[PauseMenu.selectedPauseMenu][0],pauseMenuChoices[PauseMenu.selectedPauseMenu][1]);
	}
	
	private static void drawSticky() {
		if(World.bar1.stickybatStatus) {
			float stickyXPosition = World.bar1.position.x;
			float stickyYPosition = World.bar1.position.y;
			batch.draw(GameScreen.sticky[World.bar1.size - 1],stickyXPosition,stickyYPosition);
		}
		if(World.bar2.stickybatStatus) {
			float stickyXPosition = World.bar2.position.x + World.bar2.barImg.getWidth() - GameScreen.sticky[0].getWidth();
			float stickyYPosition = World.bar2.position.y;
			batch.draw(GameScreen.sticky[World.bar2.size - 1],stickyXPosition,stickyYPosition);
		}
	}
	
	private static float mkImgXCenter(Texture img) {
		return centerX - img.getWidth() / 2;
	}
	
	private static float mkImgYCenter(Texture img) {
		return centerY - img.getHeight() / 2;
	}
	
	private static void drawShield() {
		if(World.bar1.shieldStatus) {
			batch.draw(GameScreen.shieldImg[Bar.PLAYER1],shield1XPosition,shieldYPosition);
		}
		if(World.bar2.shieldStatus) {
			batch.draw(GameScreen.shieldImg[Bar.PLAYER2],shield2XPosition,shieldYPosition);
		}
	}
	
	private static void drawBullet() {
		for(int i = 0 ; i < World.bullets.length ; i++) {
			if(World.bullets[i] != null) {
				batch.draw(GameScreen.frozenBulletImg[World.bullets[i].owner],World.bullets[i].xPosition,World.bullets[i].yPosition);
			}
		}
	}
	
	private static void drawAbility() {
		if(Ability.showAbility != Ability.NOTHING) {
			GameScreen.abilityImg = GameScreen.abilityListImg[Ability.showAbility];
			batch.draw(GameScreen.abilityImg,abilityXPosition,abilityYPosition);
		} else {
			float minimumXRange = 400;
			float minimumYRange = 200;
			abilityXPosition = minimumXRange + (int)(Math.random() * (GameScreen.width - 2*minimumXRange)); 
			abilityYPosition = minimumYRange + (int)(Math.random() * (GameScreen.height - 2*minimumYRange)); 
		}
	}
	
	private static void whenEndGame() {
		drawWinner();
		drawEndMenu();
		drawSelectedEnd();
	}
	
	private static void drawEndMenu() {
		for(int i = 0 ; i < EndMenu.NBOFMENU ; i++) {
			batch.draw(GameScreen.endMenuChoicesImg[i][GameScreen.UNSELECTED],endMenuChoices[i][0],endMenuChoices[i][1]);
		}
	}
	
	private static void drawSelectedEnd() {
		batch.draw(GameScreen.endMenuChoicesImg[EndMenu.selectedEndMenu][GameScreen.SELECTED],
				   endMenuChoices[EndMenu.selectedEndMenu][0],endMenuChoices[EndMenu.selectedEndMenu][1]);
	}
	
	private static void drawWinner() {
		if(World.endStatus) {
			if(World.bar1.score == World.maxScore) {
				GameScreen.nowEndGameImg = GameScreen.endGameImg[Bar.PLAYER1];
			}
			if(World.bar2.score == World.maxScore) {
				GameScreen.nowEndGameImg = GameScreen.endGameImg[Bar.PLAYER2];
			}
			batch.draw(GameScreen.nowEndGameImg,endPosition[0],endPosition[1]);
		}
	}
	
	private static void drawBall() {
		if(World.ball.fireballStatus) {
			batch.draw(GameScreen.fireballImg,World.ball.position.x,World.ball.position.y);
		} else {
			batch.draw(GameScreen.ballImg,World.ball.position.x,World.ball.position.y);
		}
	}
	
	private static void drawBats() {
		batch.draw(World.bar1.barImg,World.bar1.position.x,World.bar1.position.y);
		batch.draw(World.bar2.barImg,World.bar2.position.x,World.bar2.position.y);
		drawFrozenBullet();
	}
	
	private static void drawFrozenBullet() {
		drawingFrozenBullet(World.bar1);
		drawingFrozenBullet(World.bar2);
	}
	
	private static void drawingFrozenBullet(Bar bar) {
		float distanceBetween = 4;
		float showFrozenBulletDilimiter = GameScreen.showFrozenBulletImg.getWidth();
		float showFrozenBulletRadius = showFrozenBulletDilimiter / 2;
		float distanceIncrease = distanceBetween + showFrozenBulletDilimiter;
		float xPosition = bar.position.x + bar.length / 2 - showFrozenBulletRadius;
		float center = bar.position.y + bar.width / 2 - showFrozenBulletRadius;
		float centerUp = bar.position.y + bar.width / 2 + distanceBetween / 2;
		float centerDown = bar.position.y + bar.width / 2 - distanceBetween / 2 - showFrozenBulletDilimiter;
		if(bar.frozenBullet % 2 == 1) {
			drawOddBullet(bar,xPosition,center,distanceIncrease);
		} else if(bar.frozenBullet % 2 == 0 && bar.frozenBullet != 0) {
			drawEvenBullet(bar,xPosition,centerUp,centerDown,distanceIncrease);
		}
	}
	
	private static void drawOddBullet(Bar bar,float xPosition, float center, float distanceIncrease) {
		int n = (bar.frozenBullet-1) / 2;
		for(int i = -1 * n ; i <= n ; i++)	{
			batch.draw(GameScreen.showFrozenBulletImg,xPosition,center + i * distanceIncrease);
		}
	}

	private static void drawEvenBullet(Bar bar,float xPosition,float centerUp,float centerDown,float distanceIncrease) {
		int n = bar.frozenBullet / 2;
		for(int i = 0;i < n;i++) {
			batch.draw(GameScreen.showFrozenBulletImg,xPosition,centerUp + i * distanceIncrease);
			batch.draw(GameScreen.showFrozenBulletImg,xPosition,centerDown - i * distanceIncrease);
		}
	}
	
	private static void drawScores() {
		batch.draw(GameScreen.score[World.bar1.score],bar1ScoreXPosition, ScoreYPosition);
		batch.draw(GameScreen.score[World.bar2.score],bar2ScoreXPosition, ScoreYPosition);
	}
}
