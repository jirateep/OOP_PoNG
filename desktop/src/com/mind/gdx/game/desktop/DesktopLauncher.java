package com.mind.gdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mind.gdx.game.GameScreen;
import com.mind.gdx.game.PongGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameScreen.width;
        config.height = GameScreen.height;
		new LwjglApplication(new PongGame(), config);
	}
}