package lovera.cadilac.tiranossauro.contratos;


import lovera.cadilac.tiranossauro.atores.graficos.utils.GraficosEnum;

public interface MensagemDeMenus extends MensagemFaseManager, MensagemRotacao{

    void graficoEscolhido(GraficosEnum graficoEnum);
    void helperEscolhido(byte btn);

}
