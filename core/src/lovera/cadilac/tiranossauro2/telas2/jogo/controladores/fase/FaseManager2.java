package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase;

public final class FaseManager2{

    private Fase2 faseAtual;

    public FaseManager2() {
        this.faseAtual = Fase2.ESCOLHENDO_GRAFICO;
    }

    public boolean isFaseAtual(Fase2 fase){
        return this.faseAtual == fase;
    }

    public boolean isUmaDasFaseAtual(Fase2... fases){
        for(Fase2 fase : fases){
            if(isFaseAtual(fase)) return true;
        }
        return false;
    }

    public Fase2 getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(Fase2 faseAtual) {
        this.faseAtual = faseAtual;
    }
}
