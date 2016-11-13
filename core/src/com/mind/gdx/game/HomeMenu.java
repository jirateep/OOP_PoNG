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
	public static final int SETTING = 2;
	public static final int HELP = 3;
	public static final int NBOFMENU = 4;
	static int selectedHomeMenu = ONEPLAYER;
	
	public static void update() {
		selectedHomeMenu = updateSelected(selectedHomeMenu,NBOFMENU - 1);
		selectedHomeMenuEnd(selectedHomeMenu);
	}
	
	public static void selectedHomeMenuEnd(int selected) {
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			switch(selectedHomeMenu) {
				case ONEPLAYER:
					startingWithOnePlayerSetting(World.menuStatus,HomeMenu.selectedHomeMenu);
					break;
				case TWOPLAYERS:
					startingWithTwoPlayerSetting(World.menuStatus,HomeMenu.selectedHomeMenu);
					break;
				case SETTING:
					setting(World.menuStatus,HomeMenu.selectedHomeMenu);
					break;
				case HELP:
					help(World.menuStatus,HomeMenu.selectedHomeMenu);
					break;
				default:
					break;
			}
			
			World.menuStatus = resetStatus();
			selectedHomeMenu = resetSelected();
		}
	}
	
	public static void startingWithOnePlayerSetting(boolean status,int selected) {
		World.bar1 = new Bar(GameScreen.barImg[Bar.PLAYER1][Bar.NOTFROZEN][1],World.player1BarXInit,bar1Up,bar1Down,bar1Active,Bar.PLAYER1);
		World.bar2 = new Bar(GameScreen.barImg[Bar.PLAYER2][Bar.NOTFROZEN][1],World.player2BarXInit,Bar.BOT,Bar.BOT,Bar.BOT,Bar.PLAYER2);
	}

	public static void startingWithTwoPlayerSetting(boolean status,int selected) {
		World.bar1 = new Bar(GameScreen.barImg[Bar.PLAYER1][Bar.NOTFROZEN][1],World.player1BarXInit,bar1Up,bar1Down,bar1Active,Bar.PLAYER1);
		World.bar2 = new Bar(GameScreen.barImg[Bar.PLAYER2][Bar.NOTFROZEN][1],World.player2BarXInit,bar2Up,bar2Down,bar2Active,Bar.PLAYER2);
	}

	public static void help(boolean status,int selected) {
		/////////////////////////////
	}

	public static void setting(boolean status,int selected) {
		/////////////////////////////
	}
}
