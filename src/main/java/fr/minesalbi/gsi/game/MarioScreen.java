package fr.minesalbi.gsi.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class MarioScreen implements Screen {

	private MarioGame game;

	private float offset =  0f;

	public MarioScreen(final MarioGame game) {
		this.game = game;
		this.game.loadMap();
	}


	@Override
	public void render(float delta) {
		//allow player to reset the game
		if (Gdx.input.isKeyJustPressed(Keys.F5)) {
			this.game.loadMap();
		}

		Gdx.gl.glClearColor(0.5f, 0.6f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//prepare batch
		game.viewport.apply();
		game.spriteBatch.setProjectionMatrix(game.camera.combined);
		game.spriteBatch.begin();
		
		offset +=  (100f * delta);
        offset = offset % 5000;
		

		//call logic on all entities
		Object[] ents = game.entities.begin();
		for (int i = 0, n = game.entities.size; i < n; i++) {
			Entity entity = (Entity) ents[i];
			entity.act(delta);
		}
		game.entities.end();

		for (Entity entity : game.entities) {
			entity.draw();
		}

		//draw debug
		//		for (Entity entity : game.entities) {
		//			Item item = entity.item;
		//			if (item != null) {
		//				game.shapeDrawer.setColor(Color.RED);
		//				game.shapeDrawer.setDefaultLineWidth(1.0f);
		//				Rect rect = game.world.getRect(item);
		//				game.shapeDrawer.rectangle(rect.x, rect.y, rect.w, rect.h);
		//			}
		//		}

		game.spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		game.viewport.update(width, height);
	}

	@Override
	public void dispose() {
		game.spriteBatch.dispose();
		game.textureAtlas.dispose();
	}

	@Override
	public void hide() {
		
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}



	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
}
