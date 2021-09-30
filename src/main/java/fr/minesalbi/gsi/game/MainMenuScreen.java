package fr.minesalbi.gsi.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

	final MarioGame game;
	OrthographicCamera camera;
	private float mainMenuAnimationTime = 0f;

	public MainMenuScreen(final MarioGame game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.spriteBatch.setProjectionMatrix(camera.combined);
		game.spriteBatch.begin();
		
		
		
		
		this.mainMenuAnimationTime += delta/2;
		Animation<AtlasRegion> walk = new Animation<>(1 / 30f, game.textureAtlas.findRegions("m-player-walk"), PlayMode.LOOP);
		AtlasRegion region = walk.getKeyFrame(mainMenuAnimationTime);
        game.spriteBatch.draw(region, mainMenuAnimationTime*150%this.camera.viewportWidth, 300, 50, 50);
        game.spriteBatch.draw(region, mainMenuAnimationTime*100%this.camera.viewportWidth, 300, 50, 50);
        
        
		game.font.getData().setScale(2);
		game.font.draw(game.spriteBatch, "Welcome to  Mario!!! ", 150, 50);
		game.font.draw(game.spriteBatch, "Tap anywhere to begin!", 150, 100);
		game.spriteBatch.end();

		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			game.setScreen(new MarioScreen(game));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}