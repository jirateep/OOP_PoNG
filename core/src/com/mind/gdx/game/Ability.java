package com.mind.gdx.game;

public class Ability {
	
	public static int abilityTimer = 0; 
	public static boolean startCountAbilityTimer = true;
	
	public static final int APPEARABILITY = -2;
	public static final int CHANGESHOWABILITY = -1;
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
	public static int wait = 0;
	public static int maxWait = 600;
	
	public SoundEffect soundEffect;
	
	public Ability(SoundEffect soundEffect) {
		this.soundEffect = soundEffect;
	}
	
	public void update() {
		updateTimer();
		checkNowAbility();
	}
	
	public void updateTimer() {
		updateWaitTimer();
		updateAbilityTimer();
	}
	
	public static void updateWaitTimer() {
		wait = timer(showAbility != NOTHING,wait,maxWait,CHANGESHOWABILITY,null,null);
		/*if(showAbility != NOTHING) {
			wait++;
			if(maxWait == wait) {
				showAbility = NOTHING;
				wait = 0;
			}
		}*/
			
	}
	
	public void updateAbilityTimer() {
		abilityTimer = timer(startCountAbilityTimer && World.ball.moveStatus,abilityTimer,maxCount,APPEARABILITY,null,null);
		/*if(startCountAbilityTimer && World.ball.moveStatus){
			abilityTimer++;
		}
		if(abilityTimer==maxCount) {
			startCountAbilityTimer = false;
			abilityTimer = 0;
			int random = (int)(Math.random() * 1000);
			updateShowAbility(1 + random % numberOfAbility);
		}*/
	}
	
	public static void updateShowAbility (int selectedAbility) {
		showAbility = selectedAbility;
	}
	
	public void checkNowAbility() {
		if(showAbility == NOTHING) {
			startCountAbilityTimer = true;
		}
	}
	
	public void workAbility() {
		if(showAbility != NOTHING)  {
			soundEffect.play(SoundEffect.ABILITYSOUND);
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
	
	private void workFireball() {
		World.ball.fireballStatus = true;
		World.ball.fireballCount = 0;
	}
	
	private void workBiggerbat() {
		Bar bar = World.ball.getOwner();
		bar.size += Bar.BIGGERBATINCREASE;
		if(bar.size > Bar.maxSize) {
			bar.size = Bar.maxSize;
		}
	}
	
	private void workSmallerbat() {
		Bar bar = World.ball.getOppositeOwner();
		bar.size -= Bar.SMALLERBATDECREASE;
		if(bar.size < Bar.minSize) {
			bar.size = Bar.minSize;
		}
	}
	
	private void workFrozenbullet() {
		Bar bar = World.ball.getOwner();
		bar.frozenBullet += Bar.increaseBullet;
		bar.barAbilityStatus = Bar.FROZENBULLET;
		if(bar.frozenBullet > Bar.maxFrozenBullet) {
			bar.frozenBullet = Bar.maxFrozenBullet;
		}			
	}
	
	private void workShield() {
		Bar bar = World.ball.getOwner();
		bar.shieldStatus = true;
		bar.shieldCount = 0;
	}
	
	private void workStickybat() {
		Bar bar = World.ball.getOwner();
		bar.stickybatStatus = true;
		bar.stickybatCount = 0;
	}
	
	public static int timer(boolean status,int count,int max,int statusName,Bar bar,Ball ball) {
		if(status) {
			count++;
			if(count == max) {
				resetStatus(statusName,bar,ball);
				count = 0;
			}
		}
		return count;
	}
	
	public static void resetStatus(int statusName,Bar bar,Ball ball) {
		switch(statusName) {
			case SHIELD:
				bar.shieldStatus = false;
				break;
			case FROZENBULLET:
				bar.frozenStatus = false;
				break;
			case STICKYBAT:
				bar.stickybatStatus = false;
				break;
			case FIREBALL:
				ball.fireballStatus = false;
				break;
			case CHANGESHOWABILITY:
				showAbility = NOTHING;
				break;
			case APPEARABILITY:
				startCountAbilityTimer = false;
				int random = (int)(Math.random() * 1000);
				updateShowAbility(1 + random % numberOfAbility);
				break;
			default:
				break;
		}
	}
}
