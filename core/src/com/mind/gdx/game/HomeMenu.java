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
	
	public static final int onePlayer = 0;
	public static final int twoPlayer = 1;
	public static final int setting = 2;
	public static final int help = 3;
	public static final int NBOFMENU = 4;
	static int selectedHomeMenu = onePlayer;
	
	public static void update() {
		selectedHomeMenu = updateSelected(selectedHomeMenu,3);
		selectedHomeMenuEnd(selectedHomeMenu);
	}
	
	public static void selectedHomeMenuEnd(int selected) {
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			switch(selectedHomeMenu) {
				case onePlayer:
					startingWithOnePlayerSetting();
					break;
				case twoPlayer:
					startingWithTwoPlayerSetting();
					break;
				case setting:
					setting();
					break;
				case help:
					help();
					break;
				default:
					break;
			}
		}
	}
	public static void startingWithOnePlayerSetting() {
		World.bar1 = new Bar(GameScreen.barImg[Bar.PLAYER1][Bar.NOTFROZEN][1],World.player1BarXInit,bar1Up,bar1Down,bar1Active,Bar.PLAYER1);
		World.bar2 = new Bar(GameScreen.barImg[Bar.PLAYER2][Bar.NOTFROZEN][1],World.player2BarXInit,Bar.BOT,Bar.BOT,Bar.BOT,Bar.PLAYER2);
		World.menuStatus = false;
	}

	public static void startingWithTwoPlayerSetting() {
		World.bar1 = new Bar(GameScreen.barImg[Bar.PLAYER1][Bar.NOTFROZEN][1],World.player1BarXInit,bar1Up,bar1Down,bar1Active,Bar.PLAYER1);
		World.bar2 = new Bar(GameScreen.barImg[Bar.PLAYER2][Bar.NOTFROZEN][1],World.player2BarXInit,bar2Up,bar2Down,bar2Active,Bar.PLAYER2);
		World.menuStatus = false;
	}

	public static void help() {
		/////////////////////////////
	}

	public static void setting() {
		/////////////////////////////
	}
}
