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

	private static int countCanExit = 0;
	private static int maxCountCanExit = 10;

	public HomeMenu(SoundEffect soundEffect) {
		super(soundEffect);
	}
	
	public void update() {
		if(Gdx.input.isKeyJustPressed(Keys.U)) {
			botVsBot();
		}
		if(!World.helpStatus && !World.creditsStatus) {
			selectedHomeMenu = updateSelected(selectedHomeMenu,NBOFMENU - 1);
			selectedHomeMenuEnd(selectedHomeMenu);
		}
		World.helpStatus = setMenu(World.helpStatus);
		World.creditsStatus = setMenu(World.creditsStatus);
	}
	
	public boolean setMenu(boolean status) {
		if(status) {
			countCanExit++;
			if(countCanExit > maxCountCanExit && status) {
				if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
					status = false;
					countCanExit = 0;
				}
			}
		}
		return status;
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
		World.botVsBot = false;
	}

	public void startingWithTwoPlayerSetting(boolean status) {
		World.bar1 = new Bar(GameScreen.barImg[Bar.PLAYER1][Bar.NOTFROZEN][1],World.player1BarXInit,bar1Up,bar1Down,bar1Active,Bar.PLAYER1,World.soundEffect);
		World.bar2 = new Bar(GameScreen.barImg[Bar.PLAYER2][Bar.NOTFROZEN][1],World.player2BarXInit,bar2Up,bar2Down,bar2Active,Bar.PLAYER2,World.soundEffect);
		World.botVsBot = false;
	}

	public static void help() {
		World.helpStatus = true;
	}
	
	public static void credits() {
		World.creditsStatus = true;
	}
	
	public void botVsBot() {
		World.bar1 = new Bar(GameScreen.barImg[Bar.PLAYER1][Bar.NOTFROZEN][0],World.player1BarXInit,Bar.BOT,Bar.BOT,Bar.BOT,Bar.PLAYER1,World.soundEffect);
		World.bar2 = new Bar(GameScreen.barImg[Bar.PLAYER2][Bar.NOTFROZEN][1],World.player2BarXInit,Bar.BOT,Bar.BOT,Bar.BOT,Bar.PLAYER2,World.soundEffect);
		World.botVsBot = true;
		if(!World.helpStatus && !World.creditsStatus) {
			World.menuStatus = resetStatus();
			selectedHomeMenu = resetSelected();
		}
	}
}