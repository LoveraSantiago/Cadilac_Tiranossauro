package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.gerais.NinePatchLeitor;

public final class NinePatchUnico implements TipoSingleton{

    private static NinePatchUnico ninePatchUnico;
    private final NinePatchLeitor ninePatchLeitor;

    public NinePatchUnico() {
        this.ninePatchLeitor = new NinePatchLeitor();
    }

    @Override
    public void inicializar() {
        ninePatchUnico  = this;
    }

    public static NinePatchUnico getInstancia() {
        return ninePatchUnico;
    }

    public NinePatchLeitor getNinePatchLeitor() {
        return ninePatchLeitor;
    }
}
