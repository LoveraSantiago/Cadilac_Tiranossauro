package lovera.cadilac.tiranossauro.atores.graficos.tipos.exponencial;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.Grafico;
import lovera.cadilac.tiranossauro.atores.graficos.equacoes.EquacaoExponencial;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.exp_log.ExpLog_Acao;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.exp_log.ExpLog_ProjetorPtFuturo;
import lovera.cadilac.tiranossauro.atores.graficos.utils.AreaJogavel;
import lovera.cadilac.tiranossauro.atores.graficos.utils.entradas.Arrastar_Entrada;
import lovera.cadilac.tiranossauro.controladores.FaseManager;
import lovera.cadilac.tiranossauro.telas.AjustadorDeTela;

//LINK DE AJUDA: https://www.desmos.com/calculator/3fisjexbvp
public final class Exponencial_Manager extends Grafico {

    private final Exponencial_Graf grafico;
    private final ExpLog_Acao acao;

    private final Corredor corredor;

    public Exponencial_Manager(Corredor corredor, FaseManager faseManager, ExpLog_ProjetorPtFuturo projetorPt, AreaJogavel areaJogavel) {
        super(new Arrastar_Entrada(corredor), faseManager, areaJogavel);

        EquacaoExponencial eqExponecial = new EquacaoExponencial();
        this.grafico = new Exponencial_Graf(corredor, eqExponecial, projetorPt);
        this.acao = new ExpLog_Acao(corredor, eqExponecial, projetorPt, this);
        this.corredor = corredor;
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        switch (super.faseManager.getFaseAtual()){
            case ACEITAR_ENTRADA:
                super.areaJogavel.setarTamanhoEDesenhar(spriteBatch, 0, 0, AjustadorDeTela.LARGURA_TELA, this.corredor.getPosicaoProjY());
                break;
            case JOGANDO:
                super.areaJogavel.setarTamanhoEDesenhar(spriteBatch, 0, 0, AjustadorDeTela.LARGURA_TELA, this.corredor.getPosicaoProjY());
                this.grafico.desenharGrafico(super.entrada.getPosicaoFinal());
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
        super.areaJogavel.dispose();
    }

    @Override
    public void finalizadoFromInput() {
        this.acao.setPtToque(this.grafico.getPtToqueProcessado());
        this.acao.setPosicaoFinal(this.grafico.getUltimpoPontoProcessado());
        this.acao.setLado(this.grafico.getLado());
        this.acao.calcularPontoFinal();
    }
}
