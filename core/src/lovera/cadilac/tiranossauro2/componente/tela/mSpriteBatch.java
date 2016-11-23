package lovera.cadilac.tiranossauro2.componente.tela;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

public class mSpriteBatch implements TipoSingleton{

    private SpriteBatch spriteBatch;

    @Override
    public void inicializar() {
        this.spriteBatch = new SpriteBatch();
    }

    @Override
    public SpriteBatch getInstancia() {
        return this.spriteBatch;
    }

    public static mSpriteBatch getInstance() {
        return mSpriteBatch;
    }
}
