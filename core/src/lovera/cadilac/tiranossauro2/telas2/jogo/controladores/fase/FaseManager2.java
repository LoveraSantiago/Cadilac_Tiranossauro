package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase;

public final class FaseManager2{

    private Fase2 faseAtual;

    public FaseManager2() {
        this.faseAtual = Fase2.ESCOLHENDO_GRAFICO;
    }

    public boolean isFaseAtual(Fase2 fase){
        return this.faseAtual == fase;
    }

    public Fase2 getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(Fase2 faseAtual) {
        this.faseAtual = faseAtual;
    }
}
