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
	
	public static final int FORZENBULLET = 1;
	public static final int NOTHING = 0;
	public int barAbilityStatus = NOTHING;
	
	int pressActive;
	private int pressUp;
	private int pressDown;
	private float speed = 20;
	
	public boolean shieldStatus = false;
	private int shieldCount = 0;
	private int maxShieldCount = 500;
	
	public int forzenBullet = 0;
	public boolean forzenStatus = false;
	private int forzenCount = 0;
	private int maxForzenCount = 500; 
	public int forzenSpeedFactor = 5;
	public static int maxForzenBullet = 10;
	public static int increaseBullet = 3;
	
	public Bar(Texture barImg,float x,int up,int down,int active, int p) {
		this.barImg = barImg;
		length = barImg.getWidth();
		width = barImg.getHeight();
		y = (GameScreen.height-width)/2;
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
			move();
			shoot();
			forzenTimer();
			shieldTimer();
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
	
	public void forzenTimer() {
		if(forzenStatus) {
			forzenCount++;
			if(forzenCount == maxForzenCount) {
				forzenStatus = false;
				forzenCount = 0;
			}
		}
	}
	
	public void shoot() {
		if(Ball.moveStatus) {
			if(forzenBullet > 0) {
				if(Gdx.input.isKeyJustPressed(pressActive)) {
					forzenBullet--;
					int reserved = Bullet.findAvailable();
					World.bullets[reserved] = new Bullet(getBulletXPosition(),getBulletYPosition(),player);
				}
			} else {
				barAbilityStatus = NOTHING;
			}
		}
	}
	
	private float getBulletXPosition() {
		float bulletWidth = GameScreen.forzenBulletImg1.getWidth();
		//int player = findOwner();
		if(player == Bullet.PLAYER1) {
			return World.bar1.position.x - bulletWidth;
		} else {
			return World.bar2.position.x + World.bar2.width;
		}
	}
	
	private float getBulletYPosition() {
		float bulletHeight = GameScreen.forzenBulletImg1.getHeight();
		//int player = findOwner();
		if(player == Bullet.PLAYER1) {
			return position.y + World.bar1.width/2 - bulletHeight/2;
		} else {
			return position.y + World.bar2.width/2 - bulletHeight/2;
		}
	}
	
	/*private int findOwner() {
		if(pressActive == World.bar1.pressActive) {
			return Bullet.PLAYER1;
		} else {
			return Bullet.PLAYER2;
		}
	}*/
	
	private void move() {
		if(!forzenStatus) {
			normalMove();
		}else if(forzenStatus) {
			forzenMove();
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
	
	private void forzenMove() {
		if(Gdx.input.isKeyPressed(pressUp)) {
			position.y += speed/forzenSpeedFactor;
		}
		if(Gdx.input.isKeyPressed(pressDown)) {
			position.y -= speed/forzenSpeedFactor;
		}
	}
	
	private void moveScope() {
		if(position.y < 0)
			position.y = 0;
		if(position.y > GameScreen.height - width)
			position.y = GameScreen.height - width;
	}

	public static void updateBarImg() {
		if(World.bar1.forzenStatus) {
			World.bar1.barImg = GameScreen.barFImg[0][World.bar1.size-1];
		}else{
			World.bar1.barImg = GameScreen.barImg[0][World.bar1.size-1];
		}
		
		if(World.bar2.forzenStatus) {
			World.bar2.barImg = GameScreen.barFImg[1][World.bar2.size-1];
		}else{
			World.bar2.barImg = GameScreen.barImg[1][World.bar2.size-1];
		}
	}	
	
	public static void updateWidthHeight() {
		World.bar1.length = World.bar1.barImg.getWidth();
		World.bar1.width = World.bar1.barImg.getHeight();
		World.bar2.length = World.bar2.barImg.getWidth();
		World.bar2.width = World.bar2.barImg.getHeight();	
	}

}
