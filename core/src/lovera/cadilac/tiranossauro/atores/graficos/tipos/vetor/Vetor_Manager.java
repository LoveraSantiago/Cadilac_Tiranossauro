package lovera.cadilac.tiranossauro.atores.graficos.tipos.vetor;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.Grafico;
import lovera.cadilac.tiranossauro.atores.graficos.utils.entradas.Pinca_Entrada;
import lovera.cadilac.tiranossauro.controladores.FaseManager;
import lovera.cadilac.tiranossauro.telas.AjustadorDeTela;

public final class Vetor_Manager extends Grafico{

    private final Vetor_Graf grafico;
    private final Vetor_Acao acao;

    private TextureAtlas textureAtlas;
    private NinePatch ninePatch;

    private final Corredor corredor;

    public Vetor_Manager(Corredor corredor, FaseManager faseManager){
        super(new Pinca_Entrada(corredor), faseManager);
        this.grafico = new Vetor_Graf(corredor);
        this.acao = new Vetor_Acao(corredor, this);


        this.textureAtlas = new TextureAtlas("ninepatches/ninepatches_areajogavel.atlas");
        this.ninePatch = this.textureAtlas.createPatch("area_jogavel");
        this.corredor = corredor;
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        switch (super.faseManager.getFaseAtual()){
            case ACEITAR_ENTRADA:
                spriteBatch.begin();
                this.ninePatch.draw(spriteBatch, 0, this.corredor.getPosicaoProjY(), AjustadorDeTela.LARGURA_TELA, AjustadorDeTela.ALTURA_TELA - this.corredor.getPosicaoProjY());
                spriteBatch.end();
                break;
            case JOGANDO:
                this.grafico.desenharGrafico(super.entrada.getPtSuperior(), super.entrada.getPtLateral());

                spriteBatch.begin();
                this.ninePatch.draw(spriteBatch, 0, 0, AjustadorDeTela.LARGURA_TELA, this.corredor.getPosicaoProjY());
                spriteBatch.end();

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
        this.acao.setPosicaoFinal(super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y);
        this.acao.calcularPontoFinal();
    }
}
