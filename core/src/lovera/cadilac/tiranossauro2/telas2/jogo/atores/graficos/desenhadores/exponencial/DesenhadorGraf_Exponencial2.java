package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.exponencial;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoExponencial3;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.DesenhadorGraf_LOGEXP;

//LINK DE AJUDA: https://www.desmos.com/calculator/3fisjexbvp
//TODO: refatorar DesenhadorGraficoGraf_Exponencial com DesenhadorGraficoGraf_Logaritmo classes muito iguais REALIZAR ESSA REFATORACAO SOMENTE APOS AJUSTAR OS GRAFICOS PARA MAIORES ABERTURAS ESTAO MUITO VERTICAIS AMBOS
public final class DesenhadorGraf_Exponencial2 extends DesenhadorGraf_LOGEXP {

    public DesenhadorGraf_Exponencial2() {
        super(new EquacaoExponencial3());
    }

    @Override
    protected float getProporcaoDoGraficoPeloToque(){
        //A largura maxima do grafico é dividida pela diferenca da largura maxima e a posicao do corredor
        // apos é multiplicado pela diferenca entre o toque e a posicao do corredor
        return (super.equacao.getMaximo() / (super.cameraManager.getMaiorPtX_CamProj() - super.posicaoCorredor.x)) * (super.ptToque.x - super.posicaoCorredor.x);
    }
}
