package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.voltar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoLinear;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;

final class CalculadorVolta {

    private final CameraManager cameraManager;
    private final Vector2 posicaoJogador;

    private final EquacaoLinear equacaoLinear;

    private float incremento;

    public CalculadorVolta(EquacaoLinear equacaoLinear) {
        this.cameraManager = CameraUnico.getCameraManager();
        this.posicaoJogador = CorredorManager.getInstancia().getCorredorP().getPosicaoJogo();
        this.equacaoLinear = equacaoLinear;
    }

    public void calcularVolta(){
        this.equacaoLinear.definirEquacaoDaReta(this.posicaoJogador, this.cameraManager.getPosicao_CamJogo());
        this.incremento = ((this.posicaoJogador.x - this.cameraManager.getPosicao_CamJogo().x)/300) / Math.max(60f, Gdx.graphics.getFramesPerSecond());
    }

    public float getIncremento() {
        return incremento;
    }
}
