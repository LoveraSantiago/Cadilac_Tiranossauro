package lovera.cadilac.tiranossauro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro.telas.TelaJogo;

public class GameClass extends Game {

	@Override
	public void create () {
		setScreen(new TelaJogo());
	}

	@Override
	public void render () {
		super.render();
	}
}
