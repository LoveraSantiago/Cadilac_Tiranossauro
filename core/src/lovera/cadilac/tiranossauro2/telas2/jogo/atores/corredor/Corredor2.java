package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromMovimentador;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgToCorredorManager;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoParseavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.FaseUnico;

public final class Corredor2 implements TipoParseavel, TipoDesenhavel, MsgFromMovimentador {

    private final Lataria lataria;
    private final CalculadorAngulo calcAngulo;
    private final Movimentador movimentador;

    private final Body corredor;

    private final FaseManager2 faseManager2;

    public Corredor2(MsgToCorredorManager msgCM) {
        this.faseManager2 = FaseUnico.getInstancia().getFaseManager2();

        this.lataria = new Lataria();
        this.corredor = meTransforme(this.lataria);
        this.calcAngulo = new CalculadorAngulo(this.corredor);

        this.lataria.setCorredor(this.corredor);
        this.movimentador = new Movimentador(this.corredor, msgCM, this);
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(this.faseManager2.isFaseAtual(Fase2.JOGANDO)){
            meDesenhar_FaseJogando();
        }
        else if(this.faseManager2.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
            meDesenhar_FaseAceitarEntrada();
        }
        else if(this.faseManager2.isFaseAtual(Fase2.ACAO)){
            meDesenhar_FaseAcao();
        }
        else{
            meDesenhar_FaseOutras();
        }
    }

    private void meDesenhar_FaseOutras(){
        this.calcAngulo.rotacionarParado();
        this.lataria.meDesenhar(null);
    }

    private void meDesenhar_FaseJogando(){
        this.calcAngulo.rotacionarIda();
        this.lataria.meDesenhar(null);
    }

    private void meDesenhar_FaseAceitarEntrada(){
        this.calcAngulo.rotacionarVolta();
        this.lataria.meDesenhar(null);
    }

    private void meDesenhar_FaseAcao(){
        this.calcAngulo.rotacionarEmMovimento();
        this.lataria.meDesenhar(null);
        this.movimentador.atualizar();
    }

    public void prepararParaAcao(InformacaoManager informacao){
        this.movimentador.prepararParaAcao(informacao);
    }

    @Override
    public void resetAngulo(){
        this.calcAngulo.resetAngulo();
    }

    public void telaAngulada(float angulo){
        this.calcAngulo.telaAngulada(angulo);
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
