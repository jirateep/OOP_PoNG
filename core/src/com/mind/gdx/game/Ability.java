package com.mind.gdx.game;

public class Ability {
	
	public static int abilityTimer = 0; 
	public static boolean startCountAbilityTimer = true;
	public static final int NOTHING = 0;
	public static final int FIREBALL = 1;
	public static int showAbility = NOTHING;
	public static int maxCount = 500;
	public static int maxAbilityTime = 500;
	public static int ballAbilityTimer = 0;
	public static boolean startBallAbilityTimer = false;
	
	public static void update() {
		updateTimer();
		checkNowAbility();
	}
	
	public static void updateTimer() {
		updateAbilityTimer();
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
			updateShowAbility(FIREBALL);
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
		if(showAbility == FIREBALL) {
			Ball.ballAbilityStatus=Ball.FIREBALL;
			startBallAbilityTimer = true;
		}
		showAbility = NOTHING;
	}
}
