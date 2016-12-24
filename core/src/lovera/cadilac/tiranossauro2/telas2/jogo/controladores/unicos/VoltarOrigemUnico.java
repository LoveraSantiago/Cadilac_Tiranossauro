package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.voltar.VoltarOrigem2;

public class VoltarOrigemUnico implements TipoSingleton{

    private static VoltarOrigem2 voltarOrigem2;

    @Override
    public void inicializar() {
        voltarOrigem2 = new VoltarOrigem2();
    }

    public static VoltarOrigem2 voltarOrigem2() {
        return voltarOrigem2;
    }
}
