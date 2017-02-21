package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.FaseUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.VoltarOrigemUnico;

public final class CorredorManager implements TipoDesenhavel, MsgToCorredorManager{

    private final Corredor2 corredorP;
    private Corredor2[] corredores;

    private int qtdJogadores;
    private int contadorAcoes;

    private final FaseManager2 faseManager;

    public CorredorManager() {
        this.faseManager = FaseUnico.getInstancia().getFaseManager2();
        this.corredorP = new Corredor2(this);
        this.corredores = new Corredor2[0];

        contarCorredores();
        resetContadorAcoes();
    }

    private void contarCorredores(){
        this.qtdJogadores = this.corredores.length + 1;
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.corredorP.meDesenhar(objeto);
    }

    @Override
    public void acaoFinalizada() {
        this.contadorAcoes++;

        if(this.contadorAcoes == this.qtdJogadores){
            VoltarOrigemUnico.voltarOrigem2().calcularVolta();
            resetContadorAcoes();
        }
    }

    private void resetContadorAcoes(){
        this.contadorAcoes = 0;
    }

    public Corredor2 getCorredorP() {
        return corredorP;
    }
}
