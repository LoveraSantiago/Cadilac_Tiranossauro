package lovera.cadilac.tiranossauro.controladores;

public final class FaseManager {

    private Fase faseAtual;

    public FaseManager(){
        this.faseAtual = Fase.ESCOLHENDO_GRAFICO;
    }

    public Fase getFaseAtual(){
        return this.faseAtual;
    }

    public void setFaseAtual(Fase fase){
        this.faseAtual = fase;
    }
}
