package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

public final class CorredorUnico implements TipoSingleton{

    private static CorredorUnico corredorUnico;
    private final lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.CorredorManager corredorManager;

    public CorredorUnico() {
        this.corredorManager = new lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.CorredorManager();
    }

    @Override
    public void inicializar() {
        corredorUnico = this;
    }

    public static CorredorUnico getInstancia() {
        return corredorUnico;
    }

    public lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.CorredorManager getCorredorManager() {
        return corredorManager;
    }
}
