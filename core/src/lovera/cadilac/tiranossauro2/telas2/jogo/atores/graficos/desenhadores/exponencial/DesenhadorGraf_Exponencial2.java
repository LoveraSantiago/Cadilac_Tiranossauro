package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.exponencial;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoExponencial3;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.DesenhadorGraf_LOGEXP;

//LINK DE AJUDA: https://www.desmos.com/calculator/3fisjexbvp
public final class DesenhadorGraf_Exponencial2 extends DesenhadorGraf_LOGEXP {

    public DesenhadorGraf_Exponencial2() {
        super(new EquacaoExponencial3());
    }

    @Override
    protected float getProporcaoDoGraficoPeloToque(){
        //A largura maxima do grafico é dividida pela diferenca da largura maxima e a posicao do corredor
        // apos é multiplicado pela diferenca entre o toque e a posicao do corredor
        return (super.equacao.getMaximo() / (super.cameraManager.getMaiorPtX_CamProj() - super.posJog.getX())) * (super.ptToque.x - super.posJog.getX());
    }
}
