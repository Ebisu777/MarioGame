package fr.minesalbi.gsi.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher {
   public static void main (String[] arg) {
      LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
      config.title = "Mario";
      config.width = 1600;
      config.height = 960;
     // config.fullscreen = true;
      new LwjglApplication(new MarioGame(), config);
   }
}