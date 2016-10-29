package lovera.cadilac.tiranossauro.atores.graficos.tipos.paraboloide;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.Grafico;
import lovera.cadilac.tiranossauro.atores.graficos.equacoes.EquacaoQuadratica;
import lovera.cadilac.tiranossauro.atores.graficos.utils.AreaJogavel;
import lovera.cadilac.tiranossauro.atores.graficos.utils.entradas.Pinca_Entrada;
import lovera.cadilac.tiranossauro.controladores.FaseManager;

//LINK DE AJUDA: https://www.desmos.com/calculator/zukjgk9iry
public class Paraboloide_Manager extends Grafico {

    private final Paraboloide_Graf grafico;
    private final Paraboloide_Acao acao;

    public Paraboloide_Manager(Corredor corredor, FaseManager faseManager, AreaJogavel areaJogavel) {
        super(new Pinca_Entrada(corredor), faseManager, areaJogavel);

        EquacaoQuadratica quadratica = new EquacaoQuadratica();
        Paraboloide_ProjetorPtFuturo projetorPt = new Paraboloide_ProjetorPtFuturo(quadratica);
        this.grafico = new Paraboloide_Graf(corredor, quadratica, projetorPt);
        this.acao = new Paraboloide_Acao(corredor, quadratica, projetorPt, this);
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        switch (super.faseManager.getFaseAtual()){
            case JOGANDO:
                this.grafico.desenharGrafico(super.entrada.getPtSuperior(), super.entrada.getPtLateral());
                break;
            case ACAO:
                realizarAcao();
                break;
        }
    }

    @Override
    protected void realizarAcao() {
        this.acao.acao();
    }

    @Override
    public void dispose() {
        this.grafico.dispose();
    }

    @Override
    public void finalizadoFromInput() {
        this.acao.setLado(this.grafico.getLado());
        this.acao.setPosicaoInicial();
        this.acao.setPosicaoFinal(super.entrada.getPtSuperior());
        this.acao.setEixo(this.grafico.getEixo());
    }
}
