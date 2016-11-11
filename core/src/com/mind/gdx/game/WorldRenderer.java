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

	private static float endingXPosition;
	private static float endingYPosition;
	private static float minimumXRange = 400;
	private static float minimumYRange = 200;
	static float abilityXPosition = (GameScreen.width - GameScreen.fireballAbilityImg.getWidth()) / 2;
	static float abilityYPosition = (GameScreen.height - GameScreen.fireballAbilityImg.getHeight()) / 2;
	private static float shield1XPosition = World.bar1.position.x - GameScreen.shieldImg1.getWidth();
	private static float shield2XPosition = World.bar2.position.x + World.bar2.length;
	private static float shieldYPosition = 0;
	
	private static float pauseXPosition = mkImgXCenter(GameScreen.pauseImg);
	private static float pauseYPosition = centerY + 90;
	
	private static float resumeXPosition = mkImgXCenter(GameScreen.resumeImg);
	private static float resumeYPosition = centerY - GameScreen.resumeImg.getHeight() + 30;
	
	private static float restartXPosition = mkImgXCenter(GameScreen.restartImg);
	private static float restartYPosition = resumeYPosition - GameScreen.restartImg.getHeight() - 50;
	
	private static float pongHomeXPosition = mkImgXCenter(GameScreen.pongHomeImg);
	private static float pongHomeYPosition = centerY + 150;
	
	private static float onePlayerXPosition = mkImgXCenter(GameScreen.onePlayerImg);
	private static float onePlayerYPosition = centerY;
	
	private static float twoPlayersXPosition = mkImgXCenter(GameScreen.twoPlayersImg);
	private static float twoPlayersYPosition = centerY - 100;
	
	private static float settingXPosition = mkImgXCenter(GameScreen.restartImg);
	private static float settingYPosition = twoPlayersYPosition - 100;
	
	private static float helpXPosition = mkImgXCenter(GameScreen.helpImg);
	private static float helpYPosition = settingYPosition - 100;
	
	private static SpriteBatch batch = GameScreen.pongGame.batch;
	
	public static void render() {
		batch.begin();
		
		if(World.menuStatus) {
			drawMenu();
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
		whenEndGame();
		
		batch.end();
	}
	
	private static void drawMenu() {
		batch.draw(GameScreen.pongHomeImg,pongHomeXPosition,pongHomeYPosition);
		batch.draw(GameScreen.onePlayerImg,onePlayerXPosition,onePlayerYPosition);
		batch.draw(GameScreen.twoPlayersImg,twoPlayersXPosition,twoPlayersYPosition);		
		batch.draw(GameScreen.settingImg,settingXPosition,settingYPosition);
		batch.draw(GameScreen.helpImg,helpXPosition,helpYPosition);
		
		drawSelectedMenu();
	}
	
	private static void drawSelectedMenu() {
		if(World.selectedMenu == 1) {
			batch.draw(GameScreen.selectedOnePlayerImg,onePlayerXPosition,onePlayerYPosition);	
		} else if(World.selectedMenu == 2){
			batch.draw(GameScreen.selectedTwoPlayersImg,twoPlayersXPosition,twoPlayersYPosition);
		} else if(World.selectedMenu == 3){
			batch.draw(GameScreen.selectedSettingImg,settingXPosition,settingYPosition);
		} else if(World.selectedMenu == 4){
			batch.draw(GameScreen.selectedHelpImg,helpXPosition,helpYPosition);
		}
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
	
	private static void drawWhenPause() {	
		batch.draw(GameScreen.pauseImg,pauseXPosition,pauseYPosition);
		batch.draw(GameScreen.resumeImg,resumeXPosition,resumeYPosition);		
		batch.draw(GameScreen.restartImg,restartXPosition,restartYPosition);
		
		drawSelectedPause();
	}
	
	private static void drawSelectedPause() {
		if(World.selectedPause == 1) {
			batch.draw(GameScreen.selectedResumeImg,resumeXPosition,resumeYPosition);	
		} else if(World.selectedPause == 2){
			batch.draw(GameScreen.selectedRestartImg,restartXPosition,restartYPosition);
		}	
	}
	
	private static float mkImgXCenter(Texture img) {
		return centerX - img.getWidth() / 2;
	}
	
	private static void drawShield() {
		if(World.bar1.shieldStatus) {
			batch.draw(GameScreen.shieldImg1,shield1XPosition,shieldYPosition);
		}
		if(World.bar2.shieldStatus) {
			batch.draw(GameScreen.shieldImg2,shield2XPosition,shieldYPosition);
		}
	}
	
	private static void drawBullet() {
		for(int i = 0 ; i < World.bullets.length ; i++) {
			if(World.bullets[i] != null) {
				if(World.bullets[i].owner == Bullet.PLAYER1) {
					batch.draw(GameScreen.frozenBulletImg1,World.bullets[i].xPosition,World.bullets[i].yPosition);
				}
				if(World.bullets[i].owner == Bullet.PLAYER2) {
					batch.draw(GameScreen.frozenBulletImg2,World.bullets[i].xPosition,World.bullets[i].yPosition);
				}	
			}
		}
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
			if(Ability.showAbility == Ability.FROZENBULLET) {
				GameScreen.abilityImg = GameScreen.frozenbulletAbilityImg;
			}
			if(Ability.showAbility == Ability.SHIELD) {
				GameScreen.abilityImg = GameScreen.shieldAbilityImg;
			}
			if(Ability.showAbility == Ability.STICKYBAT) {
				GameScreen.abilityImg = GameScreen.stickybatAbilityImg;
			}
			batch.draw(GameScreen.abilityImg,abilityXPosition,abilityYPosition);
		} else {
			abilityXPosition = minimumXRange + (int)(Math.random() * (GameScreen.width - 2*minimumXRange)); 
			abilityYPosition = minimumYRange + (int)(Math.random() * (GameScreen.height - 2*minimumYRange)); 
		}
	}
	
	private static void getEndingPosition() {
		endingXPosition = (GameScreen.width - GameScreen.ending.width) / 2;
		endingYPosition = (GameScreen.height + GameScreen.ending.height) / 2;
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
		if(World.ball.ballAbilityStatus == Ball.NOTHING) {
				batch.draw(GameScreen.ballImg,World.ball.position.x,World.ball.position.y);
		}
		else if(World.ball.ballAbilityStatus == Ball.FIREBALL) {
			batch.draw(GameScreen.fireballImg,World.ball.position.x,World.ball.position.y);
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
			int n = (bar.frozenBullet-1) / 2;
			for(int i = -1 * n ; i <= n ; i++)	{
				batch.draw(GameScreen.showFrozenBulletImg,xPosition,center + i * distanceIncrease);
			}
		} else if(bar.frozenBullet % 2 == 0 && bar.frozenBullet != 0) {
			int n = bar.frozenBullet / 2;
			for(int i = 0;i <= n;i++) {
				batch.draw(GameScreen.showFrozenBulletImg,xPosition,centerUp + i * distanceIncrease);
				batch.draw(GameScreen.showFrozenBulletImg,xPosition,centerDown - i * distanceIncrease);
			}
		}
	}
	
	private static void drawScores() {
		batch.draw(GameScreen.score[World.bar1.score],bar1ScoreXPosition, ScoreYPosition);
		batch.draw(GameScreen.score[World.bar2.score],bar2ScoreXPosition, ScoreYPosition);
	}
}
