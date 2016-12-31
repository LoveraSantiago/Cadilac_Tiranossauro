package lovera.cadilac.tiranossauro2.componente.tela;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class SpriteBatchManager{

    private final SpriteBatch spriteBatch;

    public SpriteBatchManager() {
        this.spriteBatch = new SpriteBatch();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}
