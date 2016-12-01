package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;

public final class CorredorManager implements TipoSingleton, TipoDesenhavel{

    private static CorredorManager corredorManager;

    private Corredor2 corredorP;
    private Corredor2[] corredores;

    @Override
    public void inicializar() {
        corredorManager = this;
        this.corredorP = new Corredor2();
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
