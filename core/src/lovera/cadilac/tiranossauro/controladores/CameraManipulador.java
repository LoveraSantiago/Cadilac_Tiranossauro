package lovera.cadilac.tiranossauro.controladores;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro.contratos.Resetavel;
import lovera.cadilac.tiranossauro.telas.AjustadorDeTela;

public class CameraManipulador implements Resetavel {

    private float anguloAtual;

    private float cosTemp;
    private float senTemp;

    private final Vector2 posicaoInicial;
    private final Vector2 ptDeRotacao;
    private final Vector2 ptRotacionadoTemp2;

    private final Vector3 eixoZ;
    private final Vector3 ptCentroCProj;
    private final Vector3 ptCentroCJogo;
    private final Vector3 ptRotacionadoTemp3;

    private final OrthographicCamera cameraProjecao;
    private final OrthographicCamera cameraJogo;

    private final Viewport viewportProjecao;
    private final Viewport viewportJogo;

    public CameraManipulador() {
        this.eixoZ = new Vector3(0, 0, 1);
        this.anguloAtual = 0;
        this.ptCentroCJogo      = new Vector3();
        this.ptCentroCProj      = new Vector3();
        this.ptDeRotacao        = new Vector2();
        this.posicaoInicial     = new Vector2();
        this.ptRotacionadoTemp2 = new Vector2();
        this.ptRotacionadoTemp3 = new Vector3();

        //INICIO CONFIGURACAO VISUALIZACAO TELA
        this.cameraProjecao = new OrthographicCamera();
        this.viewportProjecao = new StretchViewport(AjustadorDeTela.LARGURA_TELA , AjustadorDeTela.ALTURA_TELA, this.cameraProjecao);
        this.viewportProjecao.apply();
        this.cameraProjecao.position.set(this.cameraProjecao.viewportWidth / 2, this.cameraProjecao.viewportHeight / 2, 0);
        this.posicaoInicial.set(this.cameraProjecao.position.x, this.cameraProjecao.position.y);

        this.cameraJogo = new OrthographicCamera();
        this.viewportJogo = new StretchViewport(AjustadorDeTela.LARGURA_TELA , AjustadorDeTela.ALTURA_TELA, this.cameraJogo);
        this.viewportJogo.apply();
        this.cameraJogo.position.set(this.cameraJogo.viewportWidth / 2, this.cameraJogo.viewportHeight / 2, 0);
        update();
    }

    @Override
    public void resetMe() {
        this.cameraProjecao.position.set(this.posicaoInicial.x, this.posicaoInicial.y, 0);
    }

    public void rotacionarCameraEmVoltaDoPonto(Vector3 posicao, float angulo){
        this.cameraJogo.rotateAround(posicao, eixoZ, angulo);
        this.anguloAtual = this.anguloAtual + angulo;
        this.ptDeRotacao.set(posicao.x, posicao.y);
    }

    public void normatizarAngulo(){
        if(this.anguloAtual < 0){
            while(this.anguloAtual < 0){
                this.anguloAtual += 360;
            }
            return;
        }
        else if(this.anguloAtual > 0){
            while(this.anguloAtual > 360){
                this.anguloAtual -= 360;
            }
            return;
        }
    }

    public Vector3 reangularEmRelacaoAoPonto(Vector3 ptOrigem, Vector2 pontoRotacionar){
        reangularEmRelacaoAoPonto(ptOrigem.x, ptOrigem.y, pontoRotacionar.x, pontoRotacionar.y);
        return this.ptRotacionadoTemp3;
    }

    public Vector2 reangularEmRelacaoAoPonto(Vector2 ptOrigem, Vector2 pontoRotacionar){
        reangularEmRelacaoAoPonto(ptOrigem.x, ptOrigem.y, pontoRotacionar.x, pontoRotacionar.y);
        return this.ptRotacionadoTemp2;
    }

    private void reangularEmRelacaoAoPonto(float origemX, float origemY, float rotacionarX, float rotationarY){
        this.ptRotacionadoTemp2.set(origemX - rotacionarX, origemY - rotationarY);

        this.cosTemp = MathUtils.cosDeg(this.anguloAtual);
        this.senTemp = MathUtils.sinDeg(this.anguloAtual);

        this.ptRotacionadoTemp3.x = (this.ptRotacionadoTemp2.x * this.cosTemp) - (this.ptRotacionadoTemp2.y * this.senTemp);
        this.ptRotacionadoTemp3.y = (this.ptRotacionadoTemp2.y * this.cosTemp) + (this.ptRotacionadoTemp2.x * this.senTemp);

        this.ptRotacionadoTemp2.set(this.ptRotacionadoTemp3.x, this.ptRotacionadoTemp3.y);
    }

    public void update(){
        this.cameraProjecao.update();
        this.cameraJogo.update();
    }

    public void resize(int width, int height){
        this.viewportProjecao.update(width, height);
        this.viewportJogo.update(width, height);
    }

    public float getMaiorPtYDaCameraProjecao(){
        float resultado = -1;

        for(int i = 0; i < this.cameraProjecao.frustum.planePoints.length; i++) {
            resultado = Math.max(resultado, this.cameraProjecao.frustum.planePoints[i].y);
        }
        return resultado;
    }

    public float getMaiorPtXDaCameraProjecao(){
        float resultado = -1;

        for(int i = 0; i < this.cameraProjecao.frustum.planePoints.length; i++) {
            resultado = Math.max(resultado, this.cameraProjecao.frustum.planePoints[i].x);
        }
        return resultado;
    }

    public OrthographicCamera getCameraProjecao(){
        return this.cameraProjecao;
    }

    public OrthographicCamera getCameraJogo(){
        return this.cameraJogo;
    }

    public Matrix4 getProjectionMatrix_Projecao(){
        return this.cameraProjecao.combined;
    }

    public Matrix4 getProjectionMatrix_Jogo(){
        return this.cameraJogo.combined;
    }

    public Vector2 getPosicaoInicialCameraJogo(){
        return this.posicaoInicial;
    }

    public void configurarPtDeRotacao(Vector2 ptDeRotacao){
        this.ptDeRotacao.set(ptDeRotacao);
    }
}
