package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgToCorredorManager;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;

public final class CorredorManager implements TipoSingleton, TipoDesenhavel, MsgToCorredorManager{

    private static CorredorManager corredorManager;

    private final Corredor2 corredorP;
    private Corredor2[] corredores;

    private int qtdJogadores;
    private int contadorAcoes;

    private final FaseManager2 faseManager;

    public CorredorManager() {
        this.faseManager = FaseManager2.getInstancia();
        this.corredorP = new Corredor2(this);
        this.corredores = new Corredor2[0];

        contarCorredores();
        resetContadorAcoes();
    }

    @Override
    public void inicializar() {
        corredorManager = this;
    }

    public static CorredorManager getInstancia() {
        return corredorManager;
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
