package fr.minesalbi.gsi.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

import space.earlygrey.shapedrawer.ShapeDrawer;

/**
 * Initiates the main logic of the game, runs the main game loop, and renders the entities.
 */
public class BumpGame extends Game {
	public  SpriteBatch spriteBatch;
	public  TextureAtlas textureAtlas;
	public  ShapeDrawer shapeDrawer;
	public  ExtendViewport viewport;
	public  OrthographicCamera camera;
	public  SnapshotArray<Entity> entities;
	public  World<Entity> world;
	public  final String MAP =
			        "+--------------------------------------------------------------------------------------------------+\n" +
					"+------------------------+----------------------------------------------------------------------++-+\n" +
					"+------------------------+------------------------------------------------------------------++-----+\n" +
					"+-++--++-----------------+----------------+-+-+-+-+-+-----------------------------------++---------+\n" +
					"+--------------e--e------+--------------------------------+e-e-e-+------------------++-------------+\n" +
					"+-----------+++++++------+------++------------------------++++++++--------------++-----------------+\n" +
					"+-----------------+------+------++----------------------------------------++-----------------------+\n" +
					"+-----------------+-------------++-----------------------------------------------------------------+\n" +
					"+p-p--------------+----e-e-e----++--------------------e-----e----------e-----e----e----------------+\n" +
					"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
	
	public BitmapFont font;
	public static float TILE_DIMENSION = 100f;

	public BumpGame() {
		// TODO Auto-generated constructor stub
	}
	
	protected void loadMap() {
		for (Entity entity : entities) {
			if (entity.item != null) world.remove(entity.item);
		}
		entities.clear();
		
		String[] lines = MAP.split("\n");
		for (int j = 0; j < lines.length; j++) {
			String line = lines[j];
			for (int i = 0; i < line.length(); i++) {
				Entity entity = null;
				if (line.charAt(i) == '+') entity = new Block(this);
				else if (line.charAt(i) == 'p') entity = new Player(this);
				else if (line.charAt(i) == 'e') entity = new Enemy(this);
				
				if (entity != null) {
					entities.add(entity);
					entity.x = i * TILE_DIMENSION;
					entity.y = (lines.length - j) * TILE_DIMENSION;
					if (entity.item != null) {
						world.add((Item<Entity>) entity.item, entity.x + entity.bboxX, entity.y + entity.bboxY, entity.bboxWidth, entity.bboxHeight);
					}
				}
			}
		}
	}

	
	
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		textureAtlas = new TextureAtlas("assets/textures.atlas");
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(800, 800, camera);
		entities = new SnapshotArray<>();
		world = new World<>(TILE_DIMENSION);
		shapeDrawer = new ShapeDrawer(spriteBatch, textureAtlas.findRegion("white"));
		font = new BitmapFont();
		//load map and spawn entities
		//loadMap();
		
		this.setScreen(new MainMenuScreen(this));
	}
	
}