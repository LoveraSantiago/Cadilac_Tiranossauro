package lovera.cadilac.tiranossauro.atores.graficos.tipos.exponencial;

import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.equacoes.EquacaoExponencial;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.exp_log.ExpLog_ProjetorPtFuturo;
import lovera.cadilac.tiranossauro.atores.graficos.utils.Direcao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.projecao.Arrastar_Grafico;

final class Exponencial_Graf extends Arrastar_Grafico {

    private final EquacaoExponencial eqExponencial;
    private float helperContador;

    public Exponencial_Graf(Corredor corredor, EquacaoExponencial eqExponencial, ExpLog_ProjetorPtFuturo projetorPt) {
        super(corredor, projetorPt);

        this.eqExponencial = eqExponencial;
    }

    @Override
    protected final void procedimentoAEsquerda(Vector3 posicaoToque){
        posicaoToque.x = super.projetorPt.espelharEsquerdaPDireita(posicaoToque.x, super.corredor.getPosicaoProjX());
        this.eqExponencial.setB(super.corredor.getPosicaoProjY() - posicaoToque.y);

        super.pt1Desenho.set(super.corredor.getPosicaoProjetada());
        this.helperContador = getProporcaoDoGraficoPeloToque(posicaoToque);
        for(super.contador = 0;
            super.contador <= this.helperContador;
            super.contador = this.contador + 0.5f) {

            super.pt2Desenho.set(super.corredor.getPosicaoProjX() + super.contador, (super.corredor.getPosicaoProjY() + (this.eqExponencial.getY(super.contador))));
            super.pt2Desenho.x = super.projetorPt.espelharEsquerdaPDireita(super.pt2Desenho.x, super.corredor.getPosicaoProjX());

            super.shapeRenderer.line(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
        super.lado = Direcao.ESQUERDA;
        super.corredor.setPtFuturoProj(super.projetorPt.calcularPtFuturoEsquerda_Horizontal(this.eqExponencial, 8, super.corredor.getPosicaoProjetada()));//8 valor ajustado olhometro
    }

    @Override
    protected final void procedimentoADireita(Vector3 posicaoToque){
        this.eqExponencial.setB(super.corredor.getPosicaoProjY() - posicaoToque.y);

        super.pt1Desenho.set(super.corredor.getPosicaoProjetada());
        this.helperContador = getProporcaoDoGraficoPeloToque(posicaoToque);
        for(super.contador = 0;
            super.contador <= this.helperContador;
            super.contador = super.contador + 0.5f) {

            super.pt2Desenho.set(super.corredor.getPosicaoProjX() + super.contador, super.corredor.getPosicaoProjY() + (this.eqExponencial.getY(super.contador)));

            super.shapeRenderer.line(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
        super.lado = Direcao.DIREITA;
        super.corredor.setPtFuturoProj(super.projetorPt.calcularPtFuturoDireita_Horizontal(this.eqExponencial, 8, super.corredor.getPosicaoProjetada()));//8 valor ajustado olhometro
    }

    private float getProporcaoDoGraficoPeloToque(Vector3 posicaoToque){
        //(Tamanho do espaco horizontal entre o toque e a posicao do jogador) * (Tamanho do espaco horizontal entre o topo da pela e a posicao y do jogador) /100
        //dessa forma pega o tamanho proporcional
        return this.eqExponencial.getX((posicaoToque.x - super.corredor.getPosicaoProjX()) * (super.corredor.getCameraManipulador().getMaiorPtYDaCameraProjecao() - super.corredor.getPosicaoProjY())/ 100);
    }
}
