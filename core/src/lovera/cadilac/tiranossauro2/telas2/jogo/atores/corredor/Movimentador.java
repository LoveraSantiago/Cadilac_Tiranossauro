package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro.utils.Debugagem;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.ponto.Pontos;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.PontoManager;

final class Movimentador implements TipoAtualizavel{

    private final Vector2 posicaoCorredor;
    private final Vector2 proximaPosicao;

    private final MouseJoint_Corredor mouseJointer;

    private final Pontos pontos;

    private final Arredondador arredondador;

    public Movimentador(Body corredor) {
        this.posicaoCorredor = corredor.getPosition();
        this.proximaPosicao = new Vector2();
        this.proximaPosicao.set(this.posicaoCorredor);

        this.mouseJointer = new MouseJoint_Corredor(corredor);
        this.pontos = PontoManager.getInstancia().getPontos();

        this.arredondador = new Arredondador();
    }

    public void prepararParaAcao(){
        this.mouseJointer.criarMouseJoint();
    }

    @Override
    public void atualizar() {
        if(isMesmaPosicao()){
            if(this.pontos.temProximoPonto()){
                this.proximaPosicao.set(this.pontos.consumirProximoPonto());
                this.mouseJointer.irPara(this.proximaPosicao);
            }
            else{
                this.mouseJointer.destruirMouseJoint();
                FaseManager2.getInstancia().setFaseAtual(Fase2.CALCULAR_VOLTA);
            }
        }
    }

    private boolean isMesmaPosicao(){
        return this.arredondador.arredondar(this.posicaoCorredor.x) == this.arredondador.arredondar(this.proximaPosicao.x) &&
               this.arredondador.arredondar(this.posicaoCorredor.y) == this.arredondador.arredondar(this.proximaPosicao.y);
    }
}
