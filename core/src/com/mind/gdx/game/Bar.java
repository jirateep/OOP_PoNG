package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bar {
	
	Vector2 position;
	public int player;
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
	public boolean ballStayAtSamePosition = false;
	
	public int countRandom = 500;
	public int countNextRandom = 0;
	public int maxCountNextRandom = 100;
	
	private boolean botWinStatus = true;
	private boolean moveUpDownStatus = true;
	
	private int countMovement = 0;
	private int maxCountMovement = 1;
	
	public Bar(Texture barImg,float x,int up,int down,int active, int p) {
		this.barImg = barImg;
		length = barImg.getWidth();
		width = barImg.getHeight();
		y = (GameScreen.height - width)/2;
		position = new Vector2(x,y);
		pressUp = up;
		pressDown = down;
		pressActive = active;
		player = p;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void update() {
		if(!World.endGame) {
			if(this.pressUp == Bar.BOT && this.pressDown == Bar.BOT && this.pressActive == Bar.BOT) {
				botMove();
				botShoot();
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
	
	private void botShoot() {
		if(frozenBullet > 0) {
			int random = (int)(Math.random()*2);
			if(random % 2 == 0) {
				frozenBullet--;
				int reserved = Bullet.findAvailable();
				World.bullets[reserved] = new Bullet(getBulletXPosition(),getBulletYPosition(),player);
			}
		}
	}
	
	private void botMove() {
		if(countNextRandom == maxCountNextRandom) {
			countNextRandom = 0;
			int random = (int)(Math.random()*countRandom);
			if(random % 40 < 10) {
				botWinStatus = false;
			} else {
				botWinStatus = true;
			}
			countRandom -= 10;
		}
		if(World.ball.moveStatus) {
			countNextRandom++;
		}
		botMoving();
		//World.ball.moveStatus = true;
	}
	
	private void botMoving() {
		if(botWinStatus) {
			if(position.y <= World.ball.position.y)
				moveUpDownStatus = true;
			else
				moveUpDownStatus = false;
		} else {
			if(position.y >= GameScreen.height - barImg.getHeight()) {
				moveUpDownStatus = false;
			} else if(position.y <= 0) {
				moveUpDownStatus = true;
			}
		}
		if(Math.abs(position.y - World.ball.position.y) > 20) {
			if(countMovement == maxCountMovement) {
				botMovement();
				countMovement = 0;
			}
			countMovement++;
		}
		if(position.y >= GameScreen.height - barImg.getHeight()) {
			position.y = GameScreen.height - barImg.getHeight();
		} else if(position.y <= 0) {
			position.y = 0;
		}
		
		if(World.ball.moveStatus == false && World.bar2.stickybatStatus && World.ball.hitStatusLeftRight == Ball.hitPlayer2)
			World.ball.moveStatus = true;
	}
	
	private void botMovement() {
		if(frozenStatus){
			if(moveUpDownStatus) {
				position.y += (speed / frozenSpeedFactor) / 2;
			} else {
				position.y -= (speed / frozenSpeedFactor) / 2;
			}
		} else {
			if(moveUpDownStatus) {
				position.y += speed * 2 / 3;
			} else {
				position.y -= speed * 2 / 3;
			}
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
				stickybatCount = 0;
			}
		}
	}
	
	public void shoot() {
		if(World.ball.moveStatus) {
			if(frozenBullet > 0) {
				if(Gdx.input.isKeyJustPressed(pressActive)) {
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
		float bulletWidth = GameScreen.frozenBulletImg1.getWidth();
		if(player == Bullet.PLAYER1) {
			return World.bar1.position.x - bulletWidth;
		} else {
			return World.bar2.position.x + World.bar2.width;
		}
	}
	
	private float getBulletYPosition() {
		float bulletHeight = GameScreen.frozenBulletImg1.getHeight();
		if(player == Bullet.PLAYER1) {
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
			World.bar1.barImg = GameScreen.barFImg[0][World.bar1.size - 1];
		}else{
			World.bar1.barImg = GameScreen.barImg[0][World.bar1.size - 1];
		}
		
		if(World.bar2.frozenStatus) {
			World.bar2.barImg = GameScreen.barFImg[1][World.bar2.size - 1];
		}else{
			World.bar2.barImg = GameScreen.barImg[1][World.bar2.size - 1];
		}
	}	
	
	public static void updateWidthHeight() {
		World.bar1.length = World.bar1.barImg.getWidth();
		World.bar1.width = World.bar1.barImg.getHeight();
		World.bar2.length = World.bar2.barImg.getWidth();
		World.bar2.width = World.bar2.barImg.getHeight();	
	}
}
