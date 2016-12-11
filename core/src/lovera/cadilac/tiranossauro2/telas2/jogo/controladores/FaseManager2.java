package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

public final class FaseManager2 implements TipoSingleton{

    private static FaseManager2 faseManager;

    private lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2 faseAtual;

    @Override
    public void inicializar() {
        faseManager = this;
        this.faseAtual = lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2.ESCOLHENDO_GRAFICO;
    }

    public static FaseManager2 getInstancia(){
        return faseManager;
    }

    public boolean isFaseAtual(lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2 fase){
        return this.faseAtual == fase;
    }

    public lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2 getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2 faseAtual) {
        this.faseAtual = faseAtual;
    }
}
