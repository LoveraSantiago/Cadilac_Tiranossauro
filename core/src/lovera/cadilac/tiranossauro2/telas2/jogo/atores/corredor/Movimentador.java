package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro.utils.Debugagem;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.Informacao;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.Pontos;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes.ClassificadorDeQuadrante;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;

final class Movimentador implements TipoAtualizavel{

    private final Vector2 posicaoCorredor;
    private final Vector2 proximaPosicao;

    private float percursoTotal;
    private Pontos pontos;

    private final Body corredor;

    private final ClassificadorDeQuadrante quadrante;
    private final CalculadorVelocidade calcVelocidade;

    public Movimentador(Body corredor) {
        this.corredor = corredor;

        this.posicaoCorredor = corredor.getPosition();
        this.proximaPosicao = new Vector2();
        this.proximaPosicao.set(this.posicaoCorredor);

        this.quadrante = new ClassificadorDeQuadrante();
        this.calcVelocidade = new CalculadorVelocidade();
    }

    public void prepararParaAcao(Informacao informacao) {
        this.pontos = informacao.getPontos();
        this.percursoTotal = informacao.getDistancia().getEspacoPercorrido();
        this.calcVelocidade.calcularVelocidadePercurso(this.pontos.getQtdPontos(), this.percursoTotal);
    }

    @Override
    public void atualizar() {
        if(irParaProximoPonto()){
            if(this.pontos.temProximoPonto()){
                this.proximaPosicao.set(this.pontos.consumirProximoPonto());

                setarQuadrante();
                this.corredor.setLinearVelocity(this.calcVelocidade.calcularVelocidadePonto(this.posicaoCorredor, this.proximaPosicao));
            }
            else{
                this.corredor.setLinearVelocity(0, 0);
                FaseManager2.getInstancia().setFaseAtual(Fase2.CALCULAR_VOLTA);
            }
        }
    }

    private void setarQuadrante(){
        this.quadrante.determinarQuadrante(this.posicaoCorredor, this.proximaPosicao);
    }

    private boolean irParaProximoPonto(){
        return this.quadrante.pontoAtingido();
    }
}
