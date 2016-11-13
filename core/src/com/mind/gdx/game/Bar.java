package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bar {
	
	Vector2 position;
	public static final int PLAYER1 = 0;
	public static final int PLAYER2 = 1;
	public int player;
	
	public static final int NOTFROZEN = 0;
	public static final int FROZEN = 1;
	
	public int score = 0;
	public float length ;
	public float width ;
	public float y;
	public Texture barImg;
	
	public static int maxSize = 7;
	public static int minSize = 1;
	public int size = 2;
	public static final int BIGGERBATINCREASE = 2;
	public static final int SMALLERBATDECREASE = 1;
	
	public static final int FROZENBULLET = 1;
	public static final int NOTHING = 0;
	public int barAbilityStatus = NOTHING;
	
	int pressActive;
	int pressUp;
	private int pressDown;
	private float speed = 20;
	private float botSpeedFactor =9.0f/11;
	public static final int BOT = -1;
	
	public boolean shieldStatus = false;
	public int shieldCount = 0;
	private int maxShieldCount = 500;
	
	public int frozenBullet = 0;
	public boolean frozenStatus = false;
	public int frozenCount = 0;
	private int maxFrozenCount = 500; 
	public int frozenSpeedFactor = 5;
	public static int maxFrozenBullet = 10;
	public static int increaseBullet = 3;
	
	public boolean stickybatStatus = false;
	public int stickybatCount = 0;
	private int maxStickybatCount = 500;
	
	public int initCountRandom = 800;
	public int countRandom = initCountRandom;
	public int countNextRandom = 0;
	public int maxCountNextRandom = 60;
	
	private boolean botWinStatus = true;
	private static final int STAY = 0;
	private static final int MOVEUP = 1;
	private static final int MOVEDOWN = 2;
	private int moveUpDownStatus = STAY;
	
	private int countMovement = 0;
	private int maxCountMovement = 1;
	
	private int countToStart = 0;
	private int maxCountToStart = 100;
	private static int countReleaseBall = 0;
	private static int maxCountReleaseBall = 50;
	
	public SoundEffect soundEffect;
	
	public Bar(Texture barImg,float x,int up,int down,int active, int p, SoundEffect soundEffect) {
		this.barImg = barImg;
		length = barImg.getWidth();
		width = barImg.getHeight();
		y = (GameScreen.height - width)/2;
		position = new Vector2(x,y);
		pressUp = up;
		pressDown = down;
		pressActive = active;
		player = p;
		this.soundEffect = soundEffect;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void update() {
		if(!World.endStatus) {
			if(this.pressUp == Bar.BOT && this.pressDown == Bar.BOT && this.pressActive == Bar.BOT) {
				botMove();
				botShoot();
				botReleaseBall();
			} else {
				move();
				shoot();
			}
			frozenTimer();
			shieldTimer();
			stickybatTimer();
			updateBarImg();
			updateWidthHeight();
		}
	}
	
	public void shieldTimer() {
		if(shieldStatus) {
			shieldCount++;
			if(shieldCount == maxShieldCount) {
				shieldStatus = false;
				shieldCount = 0;
			}
		}
	}
	
	public void frozenTimer() {
		if(frozenStatus) {
			frozenCount++;
			if(frozenCount == maxFrozenCount) {
				frozenStatus = false;
				frozenCount = 0;
			}
		}
	}
	
	public void stickybatTimer() {
		if(stickybatStatus) {
			stickybatCount++;
			if(stickybatCount == maxStickybatCount) {
				stickybatStatus = false;
				World.ball.moveStatus = true;
				stickybatCount = 0;
			}
		}
	}
	
	public void shoot() {
		if(World.ball.moveStatus) {
			if(frozenBullet > 0) {
				if(Gdx.input.isKeyJustPressed(pressActive)) {
					soundEffect.play(SoundEffect.SHOOTSOUND);
					frozenBullet--;
					int reserved = Bullet.findAvailable();
					World.bullets[reserved] = new Bullet(getBulletXPosition(),getBulletYPosition(),player);
				}
			} else {
				barAbilityStatus = NOTHING;
			}
		}
	}
	
	private float getBulletXPosition() {
		float bulletWidth = GameScreen.frozenBulletImg[Bar.PLAYER1].getWidth();
		if(player == Bar.PLAYER1) {
			return World.bar1.position.x - bulletWidth;
		} else {
			return World.bar2.position.x + World.bar2.width;
		}
	}
	
	private float getBulletYPosition() {
		float bulletHeight = GameScreen.frozenBulletImg[Bar.PLAYER1].getHeight();
		if(player == Bar.PLAYER1) {
			return position.y + World.bar1.width / 2 - bulletHeight / 2;
		} else {
			return position.y + World.bar2.width / 2 - bulletHeight / 2;
		}
	}
	
	private void move() {
		if(!frozenStatus) {
			normalMove();
		}else if(frozenStatus) {
			frozenMove();
		}
		moveScope();
	}
	
	private void normalMove() {
		if(Gdx.input.isKeyPressed(pressUp)) {
			position.y += speed;
		}
		if(Gdx.input.isKeyPressed(pressDown)) {
			position.y -= speed;
		}
	}
	
	private void frozenMove() {
		if(Gdx.input.isKeyPressed(pressUp)) {
			position.y += speed/frozenSpeedFactor;
		}
		if(Gdx.input.isKeyPressed(pressDown)) {
			position.y -= speed/frozenSpeedFactor;
		}
	}
	
	private void moveScope() {
		if(position.y < 0)
			position.y = 0;
		if(position.y > GameScreen.height - width)
			position.y = GameScreen.height - width;
	}

	public static void updateBarImg() {
		if(World.bar1.frozenStatus) {
			World.bar1.barImg = GameScreen.barImg[Bar.PLAYER1][Bar.FROZEN][World.bar1.size - 1];
		}else{
			World.bar1.barImg = GameScreen.barImg[Bar.PLAYER1][Bar.NOTFROZEN][World.bar1.size - 1];
		}
		
		if(World.bar2.frozenStatus) {
			World.bar2.barImg = GameScreen.barImg[Bar.PLAYER2][Bar.FROZEN][World.bar2.size - 1];
		}else{
			World.bar2.barImg = GameScreen.barImg[Bar.PLAYER2][Bar.NOTFROZEN][World.bar2.size - 1];
		}
	}	
	
	public static void updateWidthHeight() {
		World.bar1.length = World.bar1.barImg.getWidth();
		World.bar1.width = World.bar1.barImg.getHeight();
		World.bar2.length = World.bar2.barImg.getWidth();
		World.bar2.width = World.bar2.barImg.getHeight();	
	}
	
	private void botMove() {
		if(World.ball.moveStatus) {
			botRandomMove();
		}
			botMoving();
			moveScope();
	}
	
	private void botRandomMove() {
		if(countNextRandom == maxCountNextRandom) {
			countNextRandom = 0;
			int random = (int)(Math.random() * 10000);
			//System.out.println(random%countRandom);
			if(random % countRandom < 10) {
				botWinStatus = false;
			} else {
				botWinStatus = true;
			}
			countRandom -= 5;
			if(countRandom <= 0) {
				countRandom = 10;
			}
		}
		countNextRandom++;
	}
	
	private void botMoving() {
		if(!botWinStatus || (stickybatStatus && !World.ball.moveStatus)) {
			if(position.y + width / 2 >= GameScreen.height - barImg.getHeight()) {
				moveUpDownStatus = MOVEDOWN;
			} else if(position.y <= 0) {
				moveUpDownStatus = MOVEUP;
			}
		} else {
			if(position.y + width / 2 <= World.ball.position.y)
				moveUpDownStatus = MOVEUP;
			else
				moveUpDownStatus = MOVEDOWN;
		}/*
		if(botWinStatus) {
			if(position.y + width / 2 <= World.ball.position.y)
				moveUpDownStatus = MOVEUP;
			else
				moveUpDownStatus = MOVEDOWN;
		} else {
			if(position.y + width / 2 >= GameScreen.height - barImg.getHeight()) {
				moveUpDownStatus = MOVEDOWN;
			} else if(position.y <= 0) {
				moveUpDownStatus = MOVEUP;
				}
		}*/
		if(countMovement == maxCountMovement) {
			botMovement();
			countMovement = 0;
		}
		countMovement++;
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
	
	private void botMovement() {
		if(frozenStatus){
			botForzenMove();
		} else {
			botNormalMove();
			
		}
	}
	
	private void botForzenMove() {
		switch(moveUpDownStatus) {
			case MOVEUP:
				position.y += (speed / frozenSpeedFactor) * botSpeedFactor;
				break;
			case MOVEDOWN:
				position.y -= (speed / frozenSpeedFactor)  * botSpeedFactor;
			case STAY:
				break;
			default:
				break;
		}
	}
	
	private void botNormalMove() {
		switch(moveUpDownStatus) {
		case MOVEUP:
			position.y += speed * botSpeedFactor;
			break;
		case MOVEDOWN:
			position.y -= speed * botSpeedFactor;
		case STAY:
			break;
		default:
			break;
		}
	}
	
	private void botReleaseBall() {
		if(World.bar2.pressActive == Bar.BOT && World.ball.hitStatusLeftRight == Ball.hitPlayer2 && !World.ball.moveStatus) {
			if(countReleaseBall==maxCountReleaseBall) {
				System.out.println("hit2");
				World.ball.moveStatus = true;
			}
			countReleaseBall++;
		} else {
			countReleaseBall = 0;
		}
	}
	
	private void botShoot() {
		if(frozenBullet > 0) {
			int random = (int)(Math.random()*100);
			if(random % 400 == 0) {
				soundEffect.play(SoundEffect.SHOOTSOUND);
				frozenBullet--;
				int reserved = Bullet.findAvailable();
				World.bullets[reserved] = new Bullet(getBulletXPosition(),getBulletYPosition(),player);
			}
		}
	}
}
