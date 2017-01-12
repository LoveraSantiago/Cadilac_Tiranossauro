package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.helpers.HelperUmaAcao;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromColisao;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromMovimentador;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromTimerColisao;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgToCorredorManager;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.pontos.Pontos;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes.ClassificadorDeQuadrante;

final class Movimentador implements TipoAtualizavel, MsgFromTimerColisao, MsgFromColisao {

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

    private HelperUmaAcao helperAtual;
    private final HelperUmaAcao helperComColisao;
    private final HelperUmaAcao helperSemColisao;

    public Movimentador(Body corredor, MsgToCorredorManager msgCm, MsgFromMovimentador msgMv, CalculadorAngulo2 calculadorAngulo2) {
        this.corredor = corredor;
        this.msgMv = msgMv;
        this.msgCm = msgCm;

        this.posicaoCorredor = corredor.getPosition();
        this.proximaPosicao = new Vector2();
        this.proximaPosicao.set(this.posicaoCorredor);

        this.quadrante = new ClassificadorDeQuadrante();
        this.calcVelocidade = new CalculadorVelocidade();
        this.timer = new TimerColisao(this, calculadorAngulo2);
        this.colisao = new Colisao(this.corredor, this, calculadorAngulo2);

        this.helperComColisao = new HelperComColisao();
        this.helperSemColisao = new HelperSemColisao();
        this.helperAtual = this.helperSemColisao;
    }

    public void prepararParaAcao(InformacaoManager informacao) {
        this.pontos = informacao.getPontos();
        this.pontos.prepararPontos();
        this.calcVelocidade.calcularVelocidadePercurso(this.pontos.getQtdPontos(), informacao.getDistancia().getEspacoPercorrido());
        this.quadrante.resetQuadrante();
    }

    @Override
    public void atualizar() {
        this.helperAtual.realizarAcao();
    }

    @Override
    public void colisaoAconteceu() {
        this.timer.inicializar();
        this.helperAtual = this.helperComColisao;
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
    public void timerFinalizado(){
        this.msgMv.resetAngulo();

        this.corredor.setLinearVelocity(0, 0);
        this.corredor.setAngularVelocity(0);

        this.helperAtual = this.helperSemColisao;

        this.msgCm.acaoFinalizada();
    }

    class HelperComColisao implements HelperUmaAcao {

        @Override
        public void realizarAcao() {
            timer.atualizar();
        }
    }

    class HelperSemColisao implements HelperUmaAcao {

        @Override
        public void realizarAcao() {
            if(irParaProximoPonto()){
                if(pontos.temPonto()){
                    proximaPosicao.set(pontos.consumirPonto());

                    setarQuadrante();
                    setarPtFuturo();

                    corredor.setLinearVelocity(calcVelocidade.calcularVelocidadePonto(posicaoCorredor, proximaPosicao));
                }
                else{
                    timerFinalizado();
                }
            }
        }
    }
}
