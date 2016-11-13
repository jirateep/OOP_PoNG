package com.mind.gdx.game;

public class Ability {
	
	public static int abilityTimer = 0; 
	public static boolean startCountAbilityTimer = true;
	
	public static final int NOTHING = 0;
	public static final int FIREBALL = 1;
	public static final int BIGGERBAT = 2;
	public static final int SMALLERBAT = 3;
	public static final int FROZENBULLET = 4;
	public static final int SHIELD = 5;
	public static final int STICKYBAT = 6;
	public static int numberOfAbility = 6;
	
	public static int showAbility = NOTHING;
	public static int maxCount = 50;
	public static int maxAbilityTime = 500;
	public static int ballAbilityTimer = 0;
	public static boolean startBallAbilityTimer = false;
	public static int wait = 0;
	public static int maxWait = 600;
	
	public static void update() {
		updateTimer();
		checkNowAbility();
	}
	
	public static void updateTimer() {
		updateWaitTimer();
		updateAbilityTimer();
		if(World.ball.ballAbilityStatus == Ball.FIREBALL) {
			updateBallAbilityTimer();
		}
	}
	
	public static void updateWaitTimer() {
		if(showAbility != NOTHING) {
			wait++;
			if(maxWait == wait) {
				showAbility = NOTHING;
				//abilityTimer=maxCount;
				wait = 0;
			}
		}
			
	}
	
	public static void updateBallAbilityTimer() {
		if(startBallAbilityTimer) {
			ballAbilityTimer++;
		}
		if(ballAbilityTimer==maxAbilityTime) {
			startBallAbilityTimer = false;
			ballAbilityTimer = 0;
			World.ball.ballAbilityStatus = Ball.NOTHING;
		}
	}
	
	public static void updateAbilityTimer() {
		if(startCountAbilityTimer && World.ball.moveStatus){
			abilityTimer++;
		}
		if(abilityTimer==maxCount) {
			startCountAbilityTimer = false;
			abilityTimer = 0;
			int random = (int)(Math.random() * 1000);
			updateShowAbility(random%numberOfAbility);
		}
	}
	
	public static void updateShowAbility (int selectedAbility) {
		showAbility = selectedAbility;
	}
	
	public static void checkNowAbility() {
		if(showAbility == NOTHING) {
			startCountAbilityTimer = true;
		}
	}
	
	public static void workAbility() {
		if(showAbility != NOTHING)  {
			switch(showAbility) {
			case FIREBALL:
				workFireball();
				break;
			case BIGGERBAT:
				workBiggerbat();
				break;
			case SMALLERBAT:
				workSmallerbat();
				break;
			case FROZENBULLET:
				workFrozenbullet();
				break;
			case SHIELD:
				workShield();
				break;
			case STICKYBAT:
				workStickybat();
				break;
			default:
				break;
			}
			wait = 0;
			showAbility = NOTHING;
		}
	}
	
	private static void workFireball() {
		World.ball.ballAbilityStatus = Ball.FIREBALL;
		startBallAbilityTimer = true;
		ballAbilityTimer = 0;
	}
	
	private static void workBiggerbat() {
		Bar bar = Ball.getOwner();
		bar.size += Bar.BIGGERBATINCREASE;
		if(bar.size > Bar.maxSize) {
			bar.size = Bar.maxSize;
		}
	}
	
	private static void workSmallerbat() {
		Bar bar = Ball.getOppositeOwner();
		bar.size -= Bar.SMALLERBATDECREASE;
		if(bar.size < Bar.minSize) {
			bar.size = Bar.minSize;
		}
	}
	
	private static void workFrozenbullet() {
		Bar bar = Ball.getOwner();
		bar.frozenBullet += Bar.increaseBullet;
		bar.barAbilityStatus = Bar.FROZENBULLET;
		if(bar.frozenBullet > Bar.maxFrozenBullet) {
			bar.frozenBullet = Bar.maxFrozenBullet;
		}			
	}
	
	private static void workShield() {
		Bar bar = Ball.getOwner();
		bar.shieldStatus = true;
		bar.shieldCount = 0;
	}
	
	private static void workStickybat() {
		Bar bar = Ball.getOwner();
		bar.stickybatStatus = true;
		bar.stickybatCount = 0;
		bar.ballStayAtSamePosition = true;
	}
}
