package lovera.cadilac.tiranossauro2.componente.tela;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

public class mSpriteBatch implements TipoSingleton{

    private static SpriteBatch spriteBatch;

    public mSpriteBatch() {
        spriteBatch = new SpriteBatch();
    }

    //SO DE ENFEITE
    @Override
    public void inicializar() {}

    public static SpriteBatch getInstancia() {
        return spriteBatch;
    }
}
