package com.mind.gdx.game;

public class BotBar {
	private float botSpeedFactor =9.0f/11;
	private boolean botWinStatus = true;
	private static final int STAY = 0;
	private static final int MOVEUP = 1;
	private static final int MOVEDOWN = 2;
	private int moveUpDownStatus = STAY;
	
	private int countMovement = 0;
	private int maxCountMovement = 1;
	
	public int initCountRandom = 1000;
	public int countRandom = initCountRandom;
	public int countNextRandom = 0;
	public int maxCountNextRandom = 60;
	
	private int countToStart = 0;
	private int maxCountToStart = 100;
	private static int countReleaseBall = 0;
	private static int maxCountReleaseBall = 50;
	private Bar bar;
	private SoundEffect soundEffect;
	
	public BotBar(Bar bar,SoundEffect soundEffect) {
		this.bar = bar;
		this.soundEffect = soundEffect;
	}
	
	public void move() {
		if(World.ball.moveStatus) {
			randomMove();
		}
		movement();
		stickyBat();
		dicisionMove();
		bar.moveScope();
	}
	
	private void randomMove() {
		if(countNextRandom == maxCountNextRandom) {
			countNextRandom = 0;
			int random = (int)(Math.random() * 10000);
			if(random % countRandom < 20) {
				botWinStatus = false;
			} else {
				botWinStatus = true;
			}
			countRandom -= 10;
			if(countRandom <= 0) {
				countRandom = 10;
			}
		}
		countNextRandom++;
	}
	
	private void dicisionMove() {
		if(!botWinStatus || (bar.stickybatStatus && !World.ball.moveStatus)) {
			if(bar.position.y + bar.width / 2 >= GameScreen.height - bar.barImg.getHeight()) {
				moveUpDownStatus = MOVEDOWN;
			} else if(bar.position.y <= 0) {
				moveUpDownStatus = MOVEUP;
			}
		} else {
			if(bar.position.y + bar.width / 2 <= World.ball.position.y)
				moveUpDownStatus = MOVEUP;
			else
				moveUpDownStatus = MOVEDOWN;
		}
	}
	
	private void stickyBat() {
		if(checkBotHitStickyBat()) {
			countToStart ++;
			if(countToStart == maxCountToStart) {
				System.out.println("hit1");
				World.ball.moveStatus = true;
			}
		} else {
			countToStart = 0;
		}
	}
	
	private boolean checkBotHitStickyBat() {
		return !World.ball.moveStatus && World.bar2.stickybatStatus && World.ball.hitStatusLeftRight == Ball.hitPlayer2;
	}
	
	private void movement() {
		countMovement++;
		if(countMovement == maxCountMovement) {
			if(bar.frozenStatus) {
				frozenMove();
			} else {
				normalMove();	
			}
			countMovement = 0;
		}
	}
	
	private void frozenMove() {
		switch(moveUpDownStatus) {
			case MOVEUP:
				bar.position.y += (bar.speed / bar.frozenSpeedFactor) * botSpeedFactor;
				break;
			case MOVEDOWN:
				bar.position.y -= (bar.speed / bar.frozenSpeedFactor)  * botSpeedFactor;
			default:
				break;
		}
	}
	
	private void normalMove() {
		switch(moveUpDownStatus) {
		case MOVEUP:
			bar.position.y += bar.speed * botSpeedFactor;
			break;
		case MOVEDOWN:
			bar.position.y -= bar.speed * botSpeedFactor;
		case STAY:
			break;
		default:
			break;
		}
	}
	
	public void releaseBall() {
		if(World.bar2.pressActive == Bar.BOT && World.ball.hitStatusLeftRight == Ball.hitPlayer2 && !World.ball.moveStatus) {
			if(countReleaseBall==maxCountReleaseBall) {
				World.ball.moveStatus = true;
			}
			countReleaseBall++;
		} else {
			countReleaseBall = 0;
		}
	}
	
	public void shoot() {
		if(bar.frozenBullet > 0) {
			int random = (int)(Math.random()*100);
			if(random % 400 == 0) {
				soundEffect.play(SoundEffect.SHOOTSOUND);
				bar.frozenBullet--;
				int reserved = Bullet.findAvailable();
				World.bullets[reserved] = new Bullet(bar.getBulletXPosition(),bar.getBulletYPosition(),bar.player);
			}
		}
	}
}
