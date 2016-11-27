package lovera.cadilac.tiranossauro.atores.graficos.tipos.vetor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.Grafico;
import lovera.cadilac.tiranossauro.atores.graficos.utils.AreaJogavel;
import lovera.cadilac.tiranossauro.atores.graficos.utils.entradas.Pinca_Entrada;
import lovera.cadilac.tiranossauro.controladores.FaseManager;
import lovera.cadilac.tiranossauro.telas.AjustadorDeTela;

public final class Vetor_Manager extends Grafico{

    private final Vetor_Graf grafico;
    private final Vetor_Acao acao;

    private final Corredor corredor;

    public Vetor_Manager(Corredor corredor, FaseManager faseManager, AreaJogavel areaJogavel){
        super(new Pinca_Entrada(corredor), faseManager, areaJogavel);
        this.grafico = new Vetor_Graf(corredor);
        this.acao = new Vetor_Acao(corredor, this);

        this.corredor = corredor;
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        switch (super.faseManager.getFaseAtual()){
            case ACEITAR_ENTRADA:
                super.areaJogavel.setarTamanhoEDesenhar(spriteBatch, 0, this.corredor.getPosicaoProjY(), AjustadorDeTela.LARGURA_TELA, AjustadorDeTela.ALTURA_TELA - this.corredor.getPosicaoProjY());
                break;
            case JOGANDO:
                super.areaJogavel.setarTamanhoEDesenhar(spriteBatch, 0, this.corredor.getPosicaoProjY(), AjustadorDeTela.LARGURA_TELA, AjustadorDeTela.ALTURA_TELA - this.corredor.getPosicaoProjY());
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
        super.areaJogavel.dispose();
    }

    Vector2 temp = new Vector2();
    @Override
    public void finalizadoFromInput() {
        this.acao.setPosicaoFinal(super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y);
        this.acao.calcularPontoFinal();

        corredor.getMouseJoint().setTarget(temp.set(super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y));
    }
}