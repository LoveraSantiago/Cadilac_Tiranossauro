package lovera.cadilac.tiranossauro2.componente.tela;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

public class mSpriteBatch implements TipoSingleton{

    private static SpriteBatch spriteBatch;

    @Override
    public void inicializar() {
        spriteBatch = new SpriteBatch();
    }

    public static SpriteBatch getInstancia() {
        return spriteBatch;
    }
}
