package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.logaritmo;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoLogaritmo3;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.DesenhadorGraf_LOGEXP;

//LINK DE AJUDA: https://www.desmos.com/calculator/auubsajefh
public final class DesenhadorGraf_Logaritmo2 extends DesenhadorGraf_LOGEXP {

    public DesenhadorGraf_Logaritmo2() {
        super(new EquacaoLogaritmo3());
    }

    @Override
    protected float getProporcaoDoGraficoPeloToque(){
        //A largura maxima do grafico é dividida pela diferenca da largura maxima e a posicao do corredor
        // apos é multiplicado pela diferenca entre o toque e a posicao do corredor
        //finalmente é passado a equacao para pegar a largura maxima
        return super.equacao.getX((super.equacao.getMaximo() / (super.cameraManager.getMaiorPtX_CamProj() - super.posicaoCorredor.x)) * (super.ptToque.x - super.posicaoCorredor.x));
    }

}
