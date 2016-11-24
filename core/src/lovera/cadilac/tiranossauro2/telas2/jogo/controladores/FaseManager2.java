package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

public final class FaseManager2 implements TipoSingleton{

    private static FaseManager2 faseManager;

    private Fase2 faseAtual;

    @Override
    public void inicializar() {
        this.faseAtual = Fase2.ESCOLHENDO_GRAFICO;
    }

    public Fase2 getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(Fase2 faseAtual) {
        this.faseAtual = faseAtual;
    }

    public static FaseManager2 getInstance(){
        return faseManager;
    }
}
