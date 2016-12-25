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

    private final float diferencaCameraCorredorY;

    private final Vector2 posicaoTemp;
    private final Vector2 posicaoCorredor;
    private final CameraManager cameraManager;

    private final FaseManager2 faseManager2;
    private final EquacaoLinear equacaoLinear;
    private final CalculadorVolta calculadorVolta;

    public VoltarOrigem2() {

        this.equacaoLinear = new EquacaoLinear();
        this.calculadorVolta = new CalculadorVolta(this.equacaoLinear);
        this.posicaoTemp = new Vector2();

        this.faseManager2 = FaseManager2.getInstancia();
        this.posicaoCorredor = CorredorManager.getInstancia().getCorredorP().getPosicaoJogo();
        this.cameraManager = CameraUnico.getCameraManager();

        this.diferencaCameraCorredorY = this.cameraManager.getPosicao_CamProj().x - this.posicaoCorredor.x;
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(this.faseManager2.isFaseAtual(Fase2.TELA_VOLTANDO)){
            this.posicaoTemp.set(this.cameraManager.getPosicao_CamJogo());

            if(!isCameraPosicaoFinal()){
                this.posicaoTemp.x += this.calculadorVolta.getIncremento();
                this.posicaoTemp.y = this.equacaoLinear.getY(this.posicaoTemp.x);

                this.cameraManager.setPosicao_CamJogo(this.posicaoTemp);
            }
            else{
                this.faseManager2.setFaseAtual(Fase2.ESCOLHENDO_GRAFICO);
            }
        }
    }

    private boolean isCameraPosicaoFinal(){
        return this.cameraManager.isCamerasMesmaPosicao();
    }

    public void calcularVolta(){
        this.calculadorVolta.calcularVolta();

        atualizar_CamProj();

        this.faseManager2.setFaseAtual(Fase2.TELA_VOLTANDO);
    }

    private void atualizar_CamProj(){
        this.cameraManager.setPosicao_CamProj(this.posicaoCorredor.x, this.posicaoCorredor.y + this.diferencaCameraCorredorY);
    }
}
