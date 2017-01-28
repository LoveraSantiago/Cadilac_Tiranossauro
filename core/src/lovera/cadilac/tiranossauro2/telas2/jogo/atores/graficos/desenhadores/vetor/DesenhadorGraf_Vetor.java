package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;

//TODO refatorar com DesenhadorGraf_Parabola informacao da para ser atributo estatico de superclasse
public final class DesenhadorGraf_Vetor extends DesenhadorGrafico{

    private final Vector2 posicaoJogadorP;
    private final Corredor2 corredorP;

    public DesenhadorGraf_Vetor() {
        super(new PincaEntrada2());

        this.corredorP = CorredorUnico.getInstancia().getCorredorManager().getCorredorP();
        this.posicaoJogadorP = this.corredorP.getPosicaoJogo();
    }

    @Override
    public void meDesenhar(Object objeto) {
        resetarComponentes();

        super.rotacionarEAtualizar(super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y, this.posicaoJogadorP);
        this.corredorP.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());

        super.iniciarShapeRenderer();

//TODO LEVAR ESSE CARA PARA OS EIXOS CARTESIANOS
//        this.shapeRenderer.line(this.posicaoJogadorP.x, this.posicaoJogadorP.y - 1,
//                                this.posicaoJogadorP.x, super.entrada.getPtSuperior().y);
//
//        if(super.entrada.getPtLateral().x >= this.posicaoJogadorP.x){
//            this.shapeRenderer.line(this.posicaoJogadorP.x - 1    , this.posicaoJogadorP.y,
//                                    super.entrada.getPtLateral().x , this.posicaoJogadorP.y);
//        }
//        else{
//            this.shapeRenderer.line(super.entrada.getPtLateral().x, this.posicaoJogadorP.y,
//                                    this.posicaoJogadorP.x + 1   , this.posicaoJogadorP.y);
//        }

        super.addLinhaToShapeRenderer(this.posicaoJogadorP, super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y);
        super.fecharShapeRenderer();

        super.addInformacao(this.posicaoJogadorP.x, this.posicaoJogadorP.y, super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y);
    }

    private void resetarComponentes(){
        super.resetarInformacao();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
