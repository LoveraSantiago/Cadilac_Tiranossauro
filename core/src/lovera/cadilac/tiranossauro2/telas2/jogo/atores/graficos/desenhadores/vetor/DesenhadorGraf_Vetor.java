package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Rotacionador;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.Informacao;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;


//TODO refatorar com DesenhadorGraf_Parabola informacao da para ser atributo estatico de superclasse
final class DesenhadorGraf_Vetor implements TipoDesenhavel, Disposable{

    private final ShapeRenderer shapeRenderer;
    private final OrthographicCamera cameraProjecao;

    private final Vector2 posicaoJogadorP;
    private final Corredor2 corredorP;

    private final Entrada2 entrada;
    private final Informacao informacao;
    private final Rotacionador rotacionador;

    public DesenhadorGraf_Vetor(Entrada2 entrada) {
        this.entrada = entrada;
        this.cameraProjecao = CameraUnico.getCameraManager().getCamera_CamProj();

        this.corredorP = CorredorManager.getInstancia().getCorredorP();
        this.posicaoJogadorP = this.corredorP.getPosicaoJogo();

        this.shapeRenderer = new ShapeRenderer();

        this.informacao = InformacaoManager.getInstancia().getInformacao();
        this.rotacionador = new Rotacionador();
    }

    @Override
    public void meDesenhar(Object objeto) {
        resetarComponentes();

        this.rotacionador.atualizarAnguloDoJogo();
        this.rotacionador.rotacionar(this.entrada.getPtLateral().x, this.entrada.getPtSuperior().y, this.posicaoJogadorP);
        this.corredorP.setPtFuturoProj(this.rotacionador.getResultX(), this.rotacionador.getResultY());

        this.shapeRenderer.setProjectionMatrix(this.cameraProjecao.combined);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);

        Gdx.gl.glLineWidth(60);

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

        this.shapeRenderer.line(this.posicaoJogadorP.x, this.posicaoJogadorP.y, this.entrada.getPtLateral().x, this.entrada.getPtSuperior().y);
        this.shapeRenderer.end();

        addToComponentes(this.posicaoJogadorP.x, this.posicaoJogadorP.y, this.entrada.getPtLateral().x, this.entrada.getPtSuperior().y);
    }

    private void resetarComponentes(){
        this.informacao.resetarInformacao();
    }

    private void addToComponentes(float pt1X, float pt1Y, float pt2X, float pt2Y){
        this.informacao.addInformacao(pt1X, pt1Y, pt2X, pt2Y);
    }

    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
    }
}
