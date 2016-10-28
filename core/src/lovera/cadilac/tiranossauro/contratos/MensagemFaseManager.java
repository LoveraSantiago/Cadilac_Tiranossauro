package lovera.cadilac.tiranossauro.contratos;

import lovera.cadilac.tiranossauro.controladores.Fase;

public interface MensagemFaseManager {

    Fase getFaseFromFaseManager();
    void setFaseToFaseManager(Fase faseAtual);
}
