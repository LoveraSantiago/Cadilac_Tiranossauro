package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromMovimentador;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromTimerColisao;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgToCorredorManager;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.Informacao;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.pontos.Pontos;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes.ClassificadorDeQuadrante;

final class Movimentador implements TipoAtualizavel, MsgFromTimerColisao {

    private final Vector2 posicaoCorredor;
    private final Vector2 proximaPosicao;

    private Pontos pontos;

    private final Body corredor;

    private final ClassificadorDeQuadrante quadrante;
    private final CalculadorVelocidade calcVelocidade;
    private final Colisao colisao;
    private final TimerColisao timer;

    private final MsgFromMovimentador msgMv;
    private final MsgToCorredorManager msgCm;

    public Movimentador(Body corredor, MsgToCorredorManager msgCm, MsgFromMovimentador msgMv) {
        this.corredor = corredor;
        this.msgMv = msgMv;
        this.msgCm = msgCm;

        this.posicaoCorredor = corredor.getPosition();
        this.proximaPosicao = new Vector2();
        this.proximaPosicao.set(this.posicaoCorredor);

        this.quadrante = new ClassificadorDeQuadrante();
        this.calcVelocidade = new CalculadorVelocidade();
        this.timer = new TimerColisao(this);
        this.colisao = new Colisao(this.corredor, this.timer);
    }

    public void prepararParaAcao(Informacao informacao) {
        this.pontos = informacao.getPontos();
        this.pontos.prepararPontos();
        this.calcVelocidade.calcularVelocidadePercurso(this.pontos.getQtdPontos(), informacao.getDistancia().getEspacoPercorrido());
        this.colisao.resetColisao();
    }

    @Override
    public void atualizar() {
        if(this.colisao.isAconteceuColisao()){
            procedimentoComColisao();
        }
        else{
            procedimentoSemColisao();
        }
    }

    private void procedimentoComColisao(){
        this.timer.atualizar();
    }

    private void procedimentoSemColisao(){
        if(irParaProximoPonto()){
            if(this.pontos.temPonto()){
                this.proximaPosicao.set(this.pontos.consumirPonto());

                setarQuadrante();
                setarPtFuturo();

                this.corredor.setLinearVelocity(this.calcVelocidade.calcularVelocidadePonto(this.posicaoCorredor, this.proximaPosicao));
            }
            else{
                finalizarMovimento();
            }
        }
    }

    private boolean irParaProximoPonto(){
        return this.quadrante.pontoAtingido();
    }

    private void setarQuadrante(){
        this.quadrante.determinarQuadrante(this.posicaoCorredor, this.proximaPosicao);
    }

    private void setarPtFuturo(){
        this.msgMv.setPtFuturoProj(this.proximaPosicao.x, this.proximaPosicao.y);
    }

    @Override
    public void finalizarMovimento(){
        this.msgMv.resetAngulo();
        this.corredor.setLinearVelocity(0, 0);
        this.corredor.setAngularVelocity(0);
        this.msgCm.acaoFinalizada();
    }
}
