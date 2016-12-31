package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.voltar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoLinear;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;

final class CalculadorVolta {

    private final Vector2 posicaoJogador;
    private final Vector2 posicaoFinalCamera;

    private final CameraManager cameraManager;
    private final EquacaoLinear equacaoLinear;

    private float incremento;

    public CalculadorVolta(EquacaoLinear equacaoLinear) {
        this.cameraManager = CameraUnico.getCameraManager();
        this.posicaoJogador = CorredorUnico.getInstancia().getCorredorManager().getCorredorP().getPosicaoJogo();
        this.equacaoLinear = equacaoLinear;

        this.posicaoFinalCamera = new Vector2();
    }

    public void calcularVolta(){
        this.posicaoFinalCamera.set(this.cameraManager.getDiferenca_CamJogo());
        this.posicaoFinalCamera.x += this.posicaoJogador.x;
        this.posicaoFinalCamera.y += this.posicaoJogador.y;

        this.equacaoLinear.definirEquacaoDaReta(this.posicaoFinalCamera, this.cameraManager.getPosicao_CamJogo());
        this.incremento = (this.posicaoFinalCamera.x - this.cameraManager.getPosicao_CamJogo().x)/ (Math.max(60f, Gdx.graphics.getFramesPerSecond()));
    }

    public float getIncremento() {
        return incremento;
    }

    public boolean isCameraNaPosicaoFinal(){
        return ((int)this.posicaoFinalCamera.x) == ((int) this.cameraManager.getPosicao_CamJogo().x) && ((int) this.posicaoFinalCamera.y) == ((int) this.cameraManager.getPosicao_CamJogo().y);
    }

    public Vector2 getPosicaoFinalCamera() {
        return posicaoFinalCamera;
    }
}
