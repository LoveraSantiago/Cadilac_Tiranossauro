package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.ponto.Pontos;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.PontoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;

final class DesenhadorGraf_Vetor implements TipoDesenhavel, Disposable{

    private final ShapeRenderer shapeRenderer;
    private final OrthographicCamera cameraProjecao;

    private final Corredor2 corredorP;
    private final Vector2 posicaoJogadorP;

    private final Entrada2 entrada;

    private final Pontos pontos;

    public DesenhadorGraf_Vetor(Entrada2 entrada) {
        this.entrada = entrada;
        this.cameraProjecao = CameraManager.getInstancia().getCameraProjecao();

        this.corredorP = CorredorManager.getInstancia().getCorredorP();
        this.posicaoJogadorP = this.corredorP.getPosicaoJogo();

        this.shapeRenderer = new ShapeRenderer();

        this.pontos = PontoManager.getInstancia().getPontos();
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.pontos.limparPontos();

        this.corredorP.setPtFuturoProj(this.entrada.getPtLateral().x, this.entrada.getPtSuperior().y);

        this.shapeRenderer.setProjectionMatrix(this.cameraProjecao.combined);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);

        Gdx.gl.glLineWidth(60);
        this.shapeRenderer.line(this.posicaoJogadorP.x, this.posicaoJogadorP.y - 1,
                                this.posicaoJogadorP.x, this.entrada.getPtSuperior().y);

        if(this.entrada.getPtLateral().x >= this.posicaoJogadorP.x){
            this.shapeRenderer.line(this.posicaoJogadorP.x - 1    , this.posicaoJogadorP.y,
                                    this.entrada.getPtLateral().x , this.posicaoJogadorP.y);
        }
        else{
            this.shapeRenderer.line(this.entrada.getPtLateral().x, this.posicaoJogadorP.y,
                                    this.posicaoJogadorP.x + 1   , this.posicaoJogadorP.y);
        }
        this.shapeRenderer.end();

        addToPontos(this.entrada.getPtLateral().x, this.entrada.getPtSuperior().y);
    }

    private void addToPontos(float x, float y){
        this.pontos.addPontos(x, y);
    }

    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
    }
}
