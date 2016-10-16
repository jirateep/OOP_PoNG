package com.mind.gdx.game;

public class Ability {
	
	public static int abilityTimer = 0; 
	public static boolean startCountAbilityTimer = true;
	
	public static final int NOTHING = 0;
	public static final int FIREBALL = 1;
	public static final int BIGGERBAT = 2;
	public static final int SMALLERBAT = 3;
	public static final int FORZENBULLET = 4;
	public static int numberOfAbility = 4;
	
	public static int showAbility = NOTHING;
	public static int maxCount = 200;
	public static int maxAbilityTime = 500;
	public static int ballAbilityTimer = 0;
	public static boolean startBallAbilityTimer = false;
	
	public static void update() {
		updateTimer();
		checkNowAbility();
	}
	
	public static void updateTimer() {
		updateAbilityTimer();
		if(Ball.ballAbilityStatus == Ball.FIREBALL)
			updateBallAbilityTimer();
	}
	
	public static void updateBallAbilityTimer() {
		if(startBallAbilityTimer) {
			ballAbilityTimer++;
		}
		if(ballAbilityTimer==maxAbilityTime) {
			startBallAbilityTimer = false;
			ballAbilityTimer = 0;
			Ball.ballAbilityStatus=Ball.NOTHING;
		}
	}
	
	public static void updateAbilityTimer() {
		if(startCountAbilityTimer && Ball.moveStatus){
			abilityTimer++;
		}
		if(abilityTimer==maxCount) {
			startCountAbilityTimer = false;
			abilityTimer = 0;
			int random = (int)(Math.random() * 1000);
			switch (1 + random%numberOfAbility) {
				case 1:
					updateShowAbility(FIREBALL);
					break;
				case 2:
					updateShowAbility(BIGGERBAT);
					break;
				case 3:
					updateShowAbility(SMALLERBAT);
					break;
				case 4:
					updateShowAbility(FORZENBULLET);
					break;
				default:
					break;
			}
			
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
			if(showAbility == FIREBALL) {
				Ball.ballAbilityStatus=Ball.FIREBALL;
				startBallAbilityTimer = true;
				ballAbilityTimer = 0;	
			}
			if(showAbility == BIGGERBAT) {
				if(Ball.hitStatusLeftRight==Ball.hitPlayer1) {
					World.bar1.size+=Bar.BIGGERBATINCREASE;
					//Bar.updateBarImg();
					//Bar.updateWidthHeight();
					if(World.bar1.size > Bar.maxSize) {
						World.bar1.size = Bar.maxSize;
					}
				}
				if(Ball.hitStatusLeftRight==Ball.hitPlayer2) {
					World.bar2.size+=Bar.BIGGERBATINCREASE;
					//Bar.updateBarImg();
					//Bar.updateWidthHeight();
					if(World.bar2.size > Bar.maxSize) {
						World.bar2.size = Bar.maxSize;
					}
				}
			}
			if(showAbility == SMALLERBAT) {
				if(Ball.hitStatusLeftRight==Ball.hitPlayer1) {
					World.bar2.size-=Bar.SMALLERBATDECREASE;
					//Bar.updateBarImg();
					//Bar.updateWidthHeight();
					if(World.bar2.size < Bar.minSize) {
						World.bar2.size = Bar.minSize;
					}
				}
				if(Ball.hitStatusLeftRight==Ball.hitPlayer2) {
					World.bar1.size-=Bar.SMALLERBATDECREASE;
					//Bar.updateBarImg();
					//Bar.updateWidthHeight();
					if(World.bar1.size > Bar.minSize) {
						World.bar1.size = Bar.minSize;
					}
				}
			}
			if(showAbility == FORZENBULLET) {
				if(Ball.hitStatusLeftRight==Ball.hitPlayer1) {
						World.bar1.forzenBullet = Bar.maxForzenBullet;
						World.bar1.barAbilityStatus = Bar.FORZENBULLET;
				}
				if(Ball.hitStatusLeftRight==Ball.hitPlayer2) {
						World.bar2.forzenBullet = Bar.maxForzenBullet;
						World.bar2.barAbilityStatus = Bar.FORZENBULLET;
				}
			}
			showAbility = NOTHING;
		}
	}
}
