package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Rotacionador;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;

//TODO refatorar com DesenhadorGraf_Parabola informacao da para ser atributo estatico de superclasse
public final class DesenhadorGraf_Vetor extends DesenhadorGrafico{

    private final Vector2 posicaoJogadorP;
    private final Corredor2 corredorP;

    private final Entrada2 entrada;
    private final Rotacionador rotacionador;

    public DesenhadorGraf_Vetor() {
        this.entrada = new PincaEntrada2();

        this.corredorP = CorredorUnico.getInstancia().getCorredorManager().getCorredorP();
        this.posicaoJogadorP = this.corredorP.getPosicaoJogo();

        this.rotacionador = new Rotacionador();
    }

    @Override
    public void meDesenhar(Object objeto) {
        resetarComponentes();

        this.rotacionador.atualizarAnguloDoJogo();
        this.rotacionador.rotacionar(this.entrada.getPtLateral().x, this.entrada.getPtSuperior().y, this.posicaoJogadorP);
        this.corredorP.setPtFuturoProj(this.rotacionador.getResultX(), this.rotacionador.getResultY());

        super.iniciarShapeRenderer();

//TODO LEVAR ESSE CARA PARA OS EIXOS CARTESIANOS
//        this.shapeRenderer.line(this.posicaoJogadorP.x, this.posicaoJogadorP.y - 1,
//                                this.posicaoJogadorP.x, this.entrada.getPtSuperior().y);
//
//        if(this.entrada.getPtLateral().x >= this.posicaoJogadorP.x){
//            this.shapeRenderer.line(this.posicaoJogadorP.x - 1    , this.posicaoJogadorP.y,
//                                    this.entrada.getPtLateral().x , this.posicaoJogadorP.y);
//        }
//        else{
//            this.shapeRenderer.line(this.entrada.getPtLateral().x, this.posicaoJogadorP.y,
//                                    this.posicaoJogadorP.x + 1   , this.posicaoJogadorP.y);
//        }

        super.addLinhaToShapeRenderer(this.posicaoJogadorP, this.entrada.getPtLateral().x, this.entrada.getPtSuperior().y);
        super.fecharShapeRenderer();

        super.addInformacao(this.posicaoJogadorP.x, this.posicaoJogadorP.y, this.entrada.getPtLateral().x, this.entrada.getPtSuperior().y);
    }

    private void resetarComponentes(){
        super.resetarInformacao();
    }

    @Override
    public Entrada2 getEntrada() {
        return entrada;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
