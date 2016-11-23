package lovera.cadilac.tiranossauro2;


import com.badlogic.gdx.Game;

import lovera.cadilac.tiranossauro.telas.TelaJogo;

public final class GameClass2 extends Game {

    @Override
    public void create () {
        setScreen(new TelaJogo());
    }

    @Override
    public void render () {
        super.render();
    }
}