package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro.utils.Debugagem;
import lovera.cadilac.tiranossauro2.contratos.helpers.HelperUmaAcao;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromColisao;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromMovimentador;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgToCorredorManager;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.pontos.Pontos;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes.ClassificadorDeQuadrante;

final class Movimentador implements TipoAtualizavel, MsgFromColisao {

    private final Vector2 posicaoCorredor;
    private final Vector2 proximaPosicao;

    private Pontos pontos;

    private final Body corredor;

    private final ClassificadorDeQuadrante quadrante;
    private final CalculadorVelocidade calcVelocidade;

    private final MsgFromMovimentador msgFromMovimentador;
    private final MsgToCorredorManager msgToCorredorManager;
    private final MsgFromMovimentador msgCalcAngulo;

    private HelperUmaAcao helperAtual;
    private final HelperUmaAcao helperComColisao;
    private final HelperUmaAcao helperSemColisao;

    public Movimentador(Body corredor, MsgToCorredorManager msgToCorredorManager, MsgFromMovimentador msgFromMovimentador, CalculadorAngulo2 calculadorAngulo2) {
        this.corredor = corredor;
        this.msgFromMovimentador = msgFromMovimentador;
        this.msgToCorredorManager = msgToCorredorManager;

        this.posicaoCorredor = corredor.getPosition();
        this.proximaPosicao = new Vector2();
        this.proximaPosicao.set(this.posicaoCorredor);

        this.quadrante = new ClassificadorDeQuadrante();
        this.calcVelocidade = new CalculadorVelocidade();

        this.helperComColisao = new HelperComColisao();
        this.helperSemColisao = new HelperSemColisao();
        this.helperAtual = this.helperSemColisao;

        this.msgCalcAngulo = calculadorAngulo2;
    }

    public void prepararParaAcao(InformacaoManager informacao) {
        this.pontos = informacao.getPontos();
        this.pontos.prepararPontos();
        this.calcVelocidade.calcularVelocidadePercurso(this.pontos.getQtdPontos(), informacao.getDistancia().getEspacoPercorrido());
        this.quadrante.resetQuadrante();

        this.proximaPosicao.set(this.pontos.getProximoPonto());
        setarPtFuturo();
    }

    @Override
    public void atualizar() {
        this.helperAtual.realizarAcao();
    }

    @Override
    public void colisaoAconteceu() {
        this.helperAtual = this.helperComColisao;
    }

    private boolean irParaProximoPonto(){
        return this.quadrante.pontoAtingido();
    }

    private void setarQuadrante(){
        this.quadrante.determinarQuadrante(this.posicaoCorredor, this.proximaPosicao);
    }

    private void setarPtFuturo(){
        this.msgFromMovimentador.setPtFuturoProj(this.proximaPosicao.x, this.proximaPosicao.y);
    }

    public void encerrarMovimentacao(){
        this.msgFromMovimentador.movimentacaoEncerrada();
        this.msgCalcAngulo.movimentacaoEncerrada();

        this.corredor.setLinearVelocity(0, 0);
        this.corredor.setAngularVelocity(0);

        this.helperAtual = this.helperSemColisao;

        this.msgToCorredorManager.acaoFinalizada();
    }

    class HelperComColisao implements HelperUmaAcao {

        @Override
        public void realizarAcao() {
            if(corredor.getLinearVelocity().x <= 0.4f && corredor.getLinearVelocity().y <= 0.4f){
                encerrarMovimentacao();
            }
            else{
                corredor.setLinearVelocity(corredor.getLinearVelocity().x * .99f, corredor.getLinearVelocity().y * .99f);
                corredor.setAngularVelocity(corredor.getAngularVelocity() * .99f);
            }
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
                    encerrarMovimentacao();
                }
            }
        }
    }
}
