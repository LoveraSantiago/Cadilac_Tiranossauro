package lovera.cadilac.tiranossauro;

import com.badlogic.gdx.Game;

import lovera.cadilac.tiranossauro.telas.TelaJogo;

/*
links:
http://voodoobits.blogspot.com.br/2013/04/box2d-via-libgdx-in-top-down-action.html
https://github.com/signalsin/Racer
http://www.iforce2d.net/b2dtut/top-down-car
 */

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
