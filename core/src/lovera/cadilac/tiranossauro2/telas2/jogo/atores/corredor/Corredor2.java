package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromMovimentador;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoParseavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.Informacao;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;

public final class Corredor2 implements TipoParseavel, TipoDesenhavel, MsgFromMovimentador {

    private final Lataria lataria;
    private final CalculadorAngulo calcAngulo;
    private final Movimentador movimentador;

    private final Body corredor;

    private final FaseManager2 faseManager2;

    public Corredor2() {
        this.faseManager2 = FaseManager2.getInstancia();

        this.lataria = new Lataria();
        this.corredor = meTransforme(this.lataria);
        this.calcAngulo = new CalculadorAngulo(this.corredor);

        this.lataria.setCorredor(this.corredor);
        this.movimentador = new Movimentador(this.corredor, this);
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(!this.faseManager2.isFaseAtual(Fase2.ACAO)){
            meDesenhar_FaseOutras();
        }
        else{
            meDesenhar_FaseAcao();
        }
    }

    private void meDesenhar_FaseOutras(){
        this.calcAngulo.rotacionarParado();
        this.lataria.meDesenhar(null);
    }

    private void meDesenhar_FaseAcao(){
        this.calcAngulo.rotacionarEmMovimento();
        this.lataria.meDesenhar(null);
        this.movimentador.atualizar();
    }

    public void prepararParaAcao(Informacao informacao){
        this.movimentador.prepararParaAcao(informacao);
    }

    @Override
    public void resetAngulo(){
        this.calcAngulo.resetAngulo();
    }

    @Override
    public final void setPtFuturoProj(float x, float y){
        this.calcAngulo.calcularAngulo(x, y);
    }

    public final void setPtFuturoProj(Vector2 ptFuturo){
        setPtFuturoProj(ptFuturo.x, ptFuturo.y);
    }

    @Override
    public Body meTransforme(Object lataria) {
        return new ParserToBody_Corredor().meTransforme(lataria);
    }

    public Vector2 getPosicaoJogo(){
        return this.corredor.getPosition();
    }
}
