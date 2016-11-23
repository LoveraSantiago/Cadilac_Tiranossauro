package lovera.cadilac.tiranossauro2;


import com.badlogic.gdx.Game;

import lovera.cadilac.tiranossauro2.telas2.TelaJogo2;

public final class GameClass2 extends Game {

    @Override
    public void create () {
        setScreen(new TelaJogo2());
    }

    @Override
    public void render () {
        super.render();
    }
}