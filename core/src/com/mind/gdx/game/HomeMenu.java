package com.mind.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class HomeMenu extends Menu{
	private static int bar1Up = Keys.UP;
	private static int bar1Down = Keys.DOWN;
	private static int bar1Active = Keys.L;
	private static int bar2Up = Keys.W;
	private static int bar2Down = Keys.S;
	private static int bar2Active = Keys.G;
	
	public static final int ONEPLAYER = 0;
	public static final int TWOPLAYERS = 1;
	public static final int HELP = 2;
	public static final int CREDITS = 3;
	public static final int NBOFMENU = 4;
	public static int selectedHomeMenu = ONEPLAYER;

	private static int countCanExitHelp = 0;
	private static int maxCountCanExitHelp = 10;

	public HomeMenu(SoundEffect soundEffect) {
		super(soundEffect);
	}
	
	public void update() {
		if(!World.helpStatus && !World.creditsStatus) {
			selectedHomeMenu = updateSelected(selectedHomeMenu,NBOFMENU - 1);
			selectedHomeMenuEnd(selectedHomeMenu);
		}
		setHelpMenu();
		setCreditsMenu();
	}
	
	public void setHelpMenu() {
		if(World.helpStatus) {
			countCanExitHelp++;
			if(countCanExitHelp > maxCountCanExitHelp && World.helpStatus) {
				if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
					World.helpStatus = false;
					countCanExitHelp = 0;
				}
			}
			
		}
	}
	
	public void setCreditsMenu() {
		if(World.creditsStatus) {
			countCanExitHelp++;
			if(countCanExitHelp > maxCountCanExitHelp && World.creditsStatus) {
				if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
					World.creditsStatus = false;
					countCanExitHelp = 0;
				}
			}
			
		}
	}
		
	public void selectedHomeMenuEnd(int selected) {
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			soundEffect.play(SoundEffect.ENTERSOUND);
			switch(selectedHomeMenu) {
				case ONEPLAYER:
					startingWithOnePlayerSetting(World.menuStatus);
					break;
				case TWOPLAYERS:
					startingWithTwoPlayerSetting(World.menuStatus);
					break;
				case HELP:
					help();
					break;
				case CREDITS:
					credits();
					break;
				default:
					break;
			}
			if(!World.helpStatus && !World.creditsStatus) {
				World.menuStatus = resetStatus();
				selectedHomeMenu = resetSelected();
			}
		}
	}
	
	public void startingWithOnePlayerSetting(boolean status) {
		World.bar1 = new Bar(GameScreen.barImg[Bar.PLAYER1][Bar.NOTFROZEN][1],World.player1BarXInit,bar1Up,bar1Down,bar1Active,Bar.PLAYER1,World.soundEffect);
		World.bar2 = new Bar(GameScreen.barImg[Bar.PLAYER2][Bar.NOTFROZEN][1],World.player2BarXInit,Bar.BOT,Bar.BOT,Bar.BOT,Bar.PLAYER2,World.soundEffect);
	}

	public void startingWithTwoPlayerSetting(boolean status) {
		World.bar1 = new Bar(GameScreen.barImg[Bar.PLAYER1][Bar.NOTFROZEN][1],World.player1BarXInit,bar1Up,bar1Down,bar1Active,Bar.PLAYER1,World.soundEffect);
		World.bar2 = new Bar(GameScreen.barImg[Bar.PLAYER2][Bar.NOTFROZEN][1],World.player2BarXInit,bar2Up,bar2Down,bar2Active,Bar.PLAYER2,World.soundEffect);
	}

	public static void help() {
		World.helpStatus = true;
	}
	
	public static void credits() {
		World.creditsStatus = true;
	}
}
