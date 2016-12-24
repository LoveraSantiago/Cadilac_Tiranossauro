package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.voltar;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoLinear;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;

public final class VoltarOrigem2 implements TipoDesenhavel{

    private final CalculadorVolta calculadorVolta;
    private final FaseManager2 faseManager2;

    private final Vector2 posicaoCameraFim;

    private final Vector2 posicaoTemp;
    private final Vector2 posicaoCorredor;
    private final CameraManager cameraManager;

    private final EquacaoLinear equacaoLinear;

    public VoltarOrigem2() {
        this.posicaoCameraFim = new Vector2();

        this.equacaoLinear = new EquacaoLinear();
        this.calculadorVolta = new CalculadorVolta(this.equacaoLinear, this.posicaoCameraFim);
        this.posicaoTemp = new Vector2();

        this.faseManager2 = FaseManager2.getInstancia();
        this.posicaoCorredor = CorredorManager.getInstancia().getCorredorP().getPosicaoJogo();
        this.cameraManager = CameraUnico.getCameraManager();
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(this.faseManager2.isFaseAtual(Fase2.TELA_VOLTANDO)){
            this.posicaoTemp.set(this.cameraManager.getPosicao_CamJogo());

            if(!isCameraPosicaoCorreta()){
                this.posicaoTemp.x += this.calculadorVolta.getIncremento();
                this.posicaoTemp.y = this.equacaoLinear.getY(this.posicaoTemp.x);

                this.cameraManager.setPosicao_CamJogo(this.posicaoTemp);
            }
            else{
                this.faseManager2.setFaseAtual(Fase2.ESCOLHENDO_GRAFICO);
            }
        }
    }

    private boolean isCameraPosicaoCorreta(){
        return this.posicaoTemp.x == this.posicaoCameraFim.x && this.posicaoTemp.y == this.posicaoCameraFim.y;
    }

    public void calcularVolta(){
        this.calculadorVolta.calcularVolta();

        atualizar_CamProj();

        this.faseManager2.setFaseAtual(Fase2.TELA_VOLTANDO);
    }

    private void atualizar_CamProj(){
        this.posicaoTemp.set(this.cameraManager.getPosicao_CamProj());

        this.posicaoTemp.x += this.posicaoCorredor.x;
        this.posicaoTemp.y += this.posicaoCorredor.y;

        this.cameraManager.setPosicao_CamProj(this.posicaoTemp);
    }
}
