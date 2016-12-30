package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.componente.tela.SpriteBatchManager;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

public final class SpriteBatchUnico implements TipoSingleton{

    private static SpriteBatchUnico spriteBatchUnico;
    private final SpriteBatchManager spriteBatchManager;

    public SpriteBatchUnico() {
        this.spriteBatchManager = new SpriteBatchManager();
    }

    @Override
    public void inicializar() {
        spriteBatchUnico = this;
    }

    public static SpriteBatchUnico getInstancia() {
        return spriteBatchUnico;
    }

    public SpriteBatchManager getSpriteBatchManager() {
        return spriteBatchManager;
    }
}
