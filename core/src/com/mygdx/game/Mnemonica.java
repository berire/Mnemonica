package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mygdx.game.Screens.Register_screen;

import java.net.UnknownHostException;

public class Mnemonica extends Game {
	public static int WIDTH= 800;
	public static int HEIGHT= 800;
	public static int ratio;

	public final static String TITLE = "MNEMONICA";
	public static SpriteBatch batch;
	public static ScreenViewport view;
	public OrthographicCamera cam;


	//DATABASE
	public static DB MDb;
	public static MongoClient mongoClient;

	//TIME
	//ZonedDateTime now = ZonedDateTime.now( zoneId );

	//PLACE

	@Override
	public void create () {

		try {
			mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/Mnemonica"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		MDb=mongoClient.getDB("Mnemonica");

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		view = new ScreenViewport( );
		batch = new SpriteBatch();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		// Constructs a new OrthographicCamera, using the given viewport width and height
		// Height is multiplied by aspect ratio.
		cam = new OrthographicCamera(30, 30 * (h / w));
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);



		cam.update();
		setScreen(new Register_screen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}


	/*public static final DBObject toDBObject(User user) {
		return new BasicDBObject("_id", user.getId())
				.append("name", user.getName())
				.append("address", new BasicDBObject("street", user.getAddress().getStreet())
						.append("city", user.getAddress().getTown())
						.append("phone", user.getAddress().getPhone()))
				.append("books", user.getBookIds());
	}*/

}
