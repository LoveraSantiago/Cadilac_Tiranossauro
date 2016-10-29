package lovera.cadilac.tiranossauro.atores.graficos.tipos.logaritmo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.Grafico;
import lovera.cadilac.tiranossauro.atores.graficos.equacoes.EquacaoLog;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.exp_log.ExpLog_Acao;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.exp_log.ExpLog_ProjetorPtFuturo;
import lovera.cadilac.tiranossauro.atores.graficos.utils.AreaJogavel;
import lovera.cadilac.tiranossauro.atores.graficos.utils.entradas.Arrastar_Entrada;
import lovera.cadilac.tiranossauro.controladores.FaseManager;

//LINK DE AJUDA: https://www.desmos.com/calculator/auubsajefh
public final class Logaritmo_Manager extends Grafico {

    private final Logaritmo_Graf grafico;
    private final ExpLog_Acao acao;

    public Logaritmo_Manager(Corredor corredor, FaseManager faseManager, ExpLog_ProjetorPtFuturo projetorPt, AreaJogavel areaJogavel) {
        super(new Arrastar_Entrada(corredor), faseManager, areaJogavel);

        EquacaoLog equacaoLog = new EquacaoLog();
        this.grafico = new Logaritmo_Graf(corredor, equacaoLog, projetorPt);
        this.acao = new ExpLog_Acao(corredor, equacaoLog, projetorPt, this);
    }


    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        switch (super.faseManager.getFaseAtual()){
            case JOGANDO:
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
    }

    @Override
    public void finalizadoFromInput() {
        this.acao.setPtToque(this.grafico.getPtToqueProcessado());
        this.acao.setPosicaoFinal(this.grafico.getUltimpoPontoProcessado());
        this.acao.calcularPontoFinal(this.grafico.getLado());
    }
}
