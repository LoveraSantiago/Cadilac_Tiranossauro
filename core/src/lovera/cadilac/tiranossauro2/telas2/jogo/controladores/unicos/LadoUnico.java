package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.lado.LadoManager;

public final class LadoUnico implements TipoSingleton {

    private static LadoUnico ladoUnico;
    private final LadoManager ladoManager;

    public LadoUnico() {
        this.ladoManager = new LadoManager();
    }

    @Override
    public void inicializar() {
        ladoUnico = this;
    }

    public static LadoUnico getInstancia() {
        return ladoUnico;
    }

    public LadoManager getLadoManager() {
        return ladoManager;
    }
}
