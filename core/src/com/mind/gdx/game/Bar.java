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
	public float speed = 20;
	//private float botSpeedFactor =9.0f/11;
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
	BotBar botBar;

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
		if(pressActive == BOT) {
			botBar = new BotBar(this,soundEffect);
			World.botBar = botBar;
		}
		player = p;
		this.soundEffect = soundEffect;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void update() {
		if(!World.endStatus) {
			if(this.pressActive == Bar.BOT) {
				botBar.move();
				botBar.shoot();
				botBar.releaseBall();
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
	
	public float getBulletXPosition() {
		float bulletWidth = GameScreen.frozenBulletImg[Bar.PLAYER1].getWidth();
		if(player == Bar.PLAYER1) {
			return World.bar1.position.x - bulletWidth;
		} else {
			return World.bar2.position.x + World.bar2.width;
		}
	}
	
	public float getBulletYPosition() {
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
	
	public void moveScope() {
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
}