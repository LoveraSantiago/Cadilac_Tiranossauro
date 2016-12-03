package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;

public final class CorredorManager implements TipoSingleton, TipoDesenhavel{

    private static CorredorManager corredorManager;

    private final Corredor2 corredorP;
    private Corredor2[] corredores;

    public CorredorManager() {
        this.corredorP = new Corredor2();
    }

    @Override
    public void inicializar() {
        corredorManager = this;
    }

    public static CorredorManager getInstancia() {
        return corredorManager;
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.corredorP.meDesenhar(objeto);
    }

    public Corredor2 getCorredorP() {
        return corredorP;
    }
}
