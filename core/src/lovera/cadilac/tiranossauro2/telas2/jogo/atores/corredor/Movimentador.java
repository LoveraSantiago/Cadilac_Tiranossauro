package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro.utils.Debugagem;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.ponto.Pontos;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.PontoManager;

final class Movimentador implements TipoAtualizavel{

    private final Vector2 posicaoCorredor;
    private final Vector2 proximaPosicao;

    private final Pontos pontos;

    private final Arredondador arredondador;

    private final Body corredor;

    public Movimentador(Body corredor) {
        this.corredor = corredor;

        this.posicaoCorredor = corredor.getPosition();
        this.proximaPosicao = new Vector2();
        this.proximaPosicao.set(this.posicaoCorredor);

        this.pontos = PontoManager.getInstancia().getPontos();

        this.arredondador = new Arredondador();
    }

    public void prepararParaAcao(){
//        this.mouseJointer.criarMouseJoint();
    }

    @Override
    public void atualizar() {
        if(isMesmaPosicao()){
            if(this.pontos.temProximoPonto()){
                this.proximaPosicao.set(this.pontos.consumirProximoPonto());
                diferenca();
                this.corredor.applyForceToCenter(this.diferencaV, true);
//                this.corredor.applyLinearImpulse(this.diferencaV, this.corredor.getLocalCenter(), true);
            }
            else{
                FaseManager2.getInstancia().setFaseAtual(Fase2.CALCULAR_VOLTA);
            }
        }
        else{
            diferenca();
            this.corredor.applyForceToCenter(this.diferencaV, true);
//            this.corredor.applyLinearImpulse(this.diferencaV, this.corredor.getLocalCenter(), true);
        }
    }

    private boolean isMesmaPosicao(){
        return this.arredondador.arredondar(this.posicaoCorredor.x) == this.arredondador.arredondar(this.proximaPosicao.x) &&
               this.arredondador.arredondar(this.posicaoCorredor.y) == this.arredondador.arredondar(this.proximaPosicao.y);
    }

    Vector2 diferencaV = new Vector2();
    private void diferenca(){
        this.diferencaV.set(this.proximaPosicao).sub(this.posicaoCorredor);
        Debugagem.dbgPontoVector2("Corredor P:", this.posicaoCorredor);
        Debugagem.dbgPontoVector2("ProximaPos:", this.proximaPosicao);
        Debugagem.dbgPontoVector2("Diferenca_:", this.diferencaV);
        System.out.println("**************************************");
    }
}
