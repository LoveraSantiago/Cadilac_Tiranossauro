package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.InformacaoManager;

public final class InformacaoUnico implements TipoSingleton{

    private static InformacaoUnico informacaoUnico;
    private final InformacaoManager informacaoManager;

    public InformacaoUnico() {
        this.informacaoManager = new InformacaoManager();
    }

    @Override
    public void inicializar() {
        informacaoUnico = this;
    }

    public static InformacaoUnico getInstancia() {
        return informacaoUnico;
    }

    public InformacaoManager getInformacaoManager() {
        return informacaoManager;
    }
}
