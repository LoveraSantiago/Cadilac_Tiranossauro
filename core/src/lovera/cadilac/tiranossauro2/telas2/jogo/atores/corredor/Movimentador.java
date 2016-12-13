package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro.utils.Debugagem;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.Pontos;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes.ClassificadorDeQuadrante;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.PontoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;

final class Movimentador implements TipoAtualizavel{

    private final Vector2 posicaoCorredor;
    private final Vector2 proximaPosicao;

    private final Pontos pontos;

    private final Body corredor;

    private final ClassificadorDeQuadrante quadrante;

    public Movimentador(Body corredor) {
        this.corredor = corredor;

        this.posicaoCorredor = corredor.getPosition();
        this.proximaPosicao = new Vector2();
        this.proximaPosicao.set(this.posicaoCorredor);

        this.pontos = PontoManager.getInstancia().getPontos();

        this.quadrante = new ClassificadorDeQuadrante();
    }

    @Override
    public void atualizar() {
        if(irParaProximoPonto()){
            if(this.pontos.temProximoPonto()){
                this.proximaPosicao.set(this.pontos.consumirProximoPonto());

                setarQuadrante();
                calcularDiferenca();

//                this.corredor.applyForceToCenter(this.diferencaV, true);
                this.corredor.applyLinearImpulse(this.diferencaV, this.corredor.getLocalCenter(), true);
//                this.corredor.setLinearVelocity(this.diferencaV);
            }
            else{
                FaseManager2.getInstancia().setFaseAtual(Fase2.CALCULAR_VOLTA);
            }
        }
        else{
//            calcularDiferenca();
//            this.corredor.setLinearVelocity(this.diferencaV);
//            this.corredor.applyForceToCenter(this.diferencaV, true);
//            this.corredor.applyLinearImpulse(this.diferencaV, this.corredor.getLocalCenter(), true);
        }
    }

    private void setarQuadrante(){
        this.quadrante.determinarQuadrante(this.posicaoCorredor, this.proximaPosicao);
    }

    private boolean irParaProximoPonto(){
        return this.quadrante.pontoAtingido();
    }

    Vector2 diferencaV = new Vector2();
    private void calcularDiferenca(){
        this.diferencaV.set(this.proximaPosicao).sub(this.posicaoCorredor);
        Debugagem.dbgPontoVector2("Corredor P:", this.posicaoCorredor);
        Debugagem.dbgPontoVector2("ProximaPos:", this.proximaPosicao);
        Debugagem.dbgPontoVector2("Diferenca_:", this.diferencaV);
        System.out.println("**************************************");
    }
}
