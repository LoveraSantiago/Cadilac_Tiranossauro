package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.voltar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoLinear;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;

final class CalculadorVolta {

    private final Vector2 posicaoJogador;
    private final Vector2 posicaoCameraFim;

    private final CameraManager cameraManager;
    private final EquacaoLinear equacaoLinear;

    private float incremento;

    public CalculadorVolta(EquacaoLinear equacaoLinear, Vector2 posicaoCameraFim) {
        this.cameraManager = CameraUnico.getCameraManager();
        this.posicaoJogador = CorredorManager.getInstancia().getCorredorP().getPosicaoJogo();
        this.equacaoLinear = equacaoLinear;
        this.posicaoCameraFim = posicaoCameraFim;
    }

    public void calcularVolta(){
        this.equacaoLinear.definirEquacaoDaReta(this.posicaoJogador, this.cameraManager.getPosicao_CamJogo());
        this.incremento = (this.posicaoJogador.x - this.cameraManager.getPosicao_CamJogo().x)/ (Math.max(60f, Gdx.graphics.getFramesPerSecond() * 3));

        setPosicaoCameraFim();
    }

    public float getIncremento() {
        return incremento;
    }

    private void setPosicaoCameraFim() {
        this.posicaoCameraFim.set(this.cameraManager.getPosicao_CamJogo());

        this.posicaoCameraFim.x += this.posicaoJogador.x;
        this.posicaoCameraFim.y += this.posicaoJogador.y;
    }
}
