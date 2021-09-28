package fr.minesalbi.gsi.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher {
   public static void main (String[] arg) {
      LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
      config.title = "BumpGame";
      config.width = 800;
      config.height = 480;
      //config.fullscreen = true;
      new LwjglApplication(new BumpGame(), config);
   }
}