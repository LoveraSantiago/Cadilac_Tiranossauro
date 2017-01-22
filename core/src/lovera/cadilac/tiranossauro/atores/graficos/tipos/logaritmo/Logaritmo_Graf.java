package lovera.cadilac.tiranossauro.atores.graficos.tipos.logaritmo;

import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.equacoes.EquacaoLog;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.exp_log.ExpLog_ProjetorPtFuturo;
import lovera.cadilac.tiranossauro.atores.graficos.utils.projecao.Arrastar_Grafico;
import lovera.cadilac.tiranossauro.atores.graficos.utils.Direcao;

final class Logaritmo_Graf extends Arrastar_Grafico {

    private final EquacaoLog equacaoLog;
    private float helperContador;

    public Logaritmo_Graf(Corredor corredor, EquacaoLog equacaoLog, ExpLog_ProjetorPtFuturo projetorPt) {
        super(corredor, projetorPt);

        this.equacaoLog = equacaoLog;
    }

    @Override
    protected final void procedimentoAEsquerda(Vector3 posicaoToque) {
        posicaoToque.x = super.projetorPt.espelharEsquerdaPDireita(posicaoToque.x, super.corredor.getPosicaoProjX());

        this.equacaoLog.setB((super.corredor.getPosicaoProjY() - posicaoToque.y), super.corredor.getCameraManipulador().getMaiorPtYDaCameraProjecao() - super.corredor.getPosicaoProjY());

        super.pt1Desenho.set(super.corredor.getPosicaoProjetada());
        this.helperContador = getProporcaoDoGraficoPeloToque(posicaoToque);
        for(super.contador = 0;
            super.contador <= this.helperContador;
            super.contador = super.contador + .5f) {

            super.pt2Desenho.set(super.corredor.getPosicaoProjX() + super.contador, super.corredor.getPosicaoProjY() + (this.equacaoLog.getY(super.contador)));
            this.equacaoLog.setB((super.corredor.getPosicaoProjY() - posicaoToque.y),super.corredor.getCameraManipulador().getMaiorPtYDaCameraProjecao() - super.corredor.getPosicaoProjY());

            super.pt2Desenho.x = super.projetorPt.espelharEsquerdaPDireita(super.pt2Desenho.x, super.corredor.getPosicaoProjX());
            super.shapeRenderer.line(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
        super.lado = Direcao.ESQUERDA;
        super.corredor.setPtFuturoProj(super.projetorPt.calcularPtFuturoEsquerda_Horizontal(this.equacaoLog, 8, super.corredor.getPosicaoProjetada()));
    }

    @Override
    protected final void procedimentoADireita(Vector3 posicaoToque) {
//        posicaoToque.set(150, 66, 0);
        this.equacaoLog.setB((super.corredor.getPosicaoProjY() - posicaoToque.y),super.corredor.getCameraManipulador().getMaiorPtYDaCameraProjecao() - super.corredor.getPosicaoProjY());

        super.pt1Desenho.set(super.corredor.getPosicaoProjetada());
        this.helperContador = getProporcaoDoGraficoPeloToque(posicaoToque);
        for(super.contador = 0;
            super.contador <= this.helperContador;
            super.contador = super.contador + .5f) {

            super.pt2Desenho.set(super.corredor.getPosicaoProjX() + super.contador, super.corredor.getPosicaoProjY() + (this.equacaoLog.getY(super.contador)));

            super.shapeRenderer.line(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
        super.lado = Direcao.DIREITA;
        super.corredor.setPtFuturoProj(super.projetorPt.calcularPtFuturoDireita_Horizontal(this.equacaoLog, 8, super.corredor.getPosicaoProjetada()));
    }

    private float getProporcaoDoGraficoPeloToque(Vector3 posicaoToque){
        //(Tamanho do espaco horizontal entre o toque e a posicao do jogador) * (Tamanho do espaco horizontal entre o topo da pela e a posicao y do jogador) /100
        //dessa forma pega o tamanho proporcional
        return this.equacaoLog.getX((posicaoToque.x - super.corredor.getPosicaoProjX()) * this.equacaoLog.getAlturaMax()
                / 100);
    }
}
