package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.FaseManager2;

public final class FaseUnico implements TipoSingleton{

    private static FaseUnico faseUnico;
    private final FaseManager2 faseManager2;

    public FaseUnico() {
        this.faseManager2 = new FaseManager2();
    }

    @Override
    public void inicializar() {
        faseUnico = this;
    }

    public static FaseUnico getInstancia() {
        return faseUnico;
    }

    public FaseManager2 getFaseManager2() {
        return faseManager2;
    }
}
