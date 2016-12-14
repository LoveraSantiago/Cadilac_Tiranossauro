package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.Informacao;

public final class InformacaoManager implements TipoSingleton{

    private static InformacaoManager informacaoManager;

    private final Informacao informacao;

    public InformacaoManager() {
        this.informacao = new Informacao();
    }

    @Override
    public void inicializar() {
        informacaoManager = this;
    }

    public static InformacaoManager getInstancia() {
        return informacaoManager;
    }

    public Informacao getInformacao() {
        return informacao;
    }
}
