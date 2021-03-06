package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.voltar;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoLinear;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.controle.ControleManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.ControleUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.FaseUnico;

public final class VoltarOrigem2 implements TipoDesenhavel{

    private final Vector2 posicaoTemp;
    private final Vector2 posicaoCorredor;
    private final CameraManager cameraManager;

    private final FaseManager2 faseManager2;
    private final EquacaoLinear equacaoLinear;
    private final CalculadorVolta calculadorVolta;

    private final ControleManager2 controleManager2;

    public VoltarOrigem2() {

        this.equacaoLinear = new EquacaoLinear();
        this.calculadorVolta = new CalculadorVolta(this.equacaoLinear);
        this.posicaoTemp = new Vector2();

        this.faseManager2 = FaseUnico.getInstancia().getFaseManager2();
        this.posicaoCorredor = CorredorUnico.getInstancia().getCorredorManager().getCorredorP().getPosicaoJogo();
        this.cameraManager = CameraUnico.getCameraManager();
        this.controleManager2 = ControleUnico.getInstancia().getControleManager2();
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(this.faseManager2.isFaseAtual(Fase2.TELA_VOLTANDO)){
            this.posicaoTemp.set(this.cameraManager.getPosicao_CamJogo());

            if(!this.calculadorVolta.isCameraNaPosicaoFinal()){
                procedimentoIncremento();
            }
            else{
                procedimentoFinalizar();
            }
        }
    }

    private void procedimentoIncremento(){
        this.posicaoTemp.x += this.calculadorVolta.getIncremento();
        this.posicaoTemp.y = this.equacaoLinear.getY(this.posicaoTemp.x);

        this.cameraManager.setPosicao_CamJogo(this.posicaoTemp);
    }

    private void procedimentoFinalizar(){
        this.cameraManager.setPosicao_CamJogo(this.calculadorVolta.getPosicaoFinalCamera());
        this.cameraManager.setDiferenca();
        this.faseManager2.setFaseAtual(Fase2.ESCOLHENDO_GRAFICO);
        this.controleManager2.voltarMenuGrafico();
    }

    public void calcularVolta(){
        this.calculadorVolta.calcularVolta();

        atualizar_CamProj();

        this.faseManager2.setFaseAtual(Fase2.TELA_VOLTANDO);
    }

    public void calcularVoltaImediata(){
//        this.calculadorVolta.calcularVoltaImediata();
        atualizar_CamProj();
        atualizar_CamJogo();
    }

    private void atualizar_CamProj(){
        this.cameraManager.setPosicao_CamProj(this.posicaoCorredor.x, this.posicaoCorredor.y + this.cameraManager.getDiferenca_CamProj().y);
    }

    private void atualizar_CamJogo(){
        this.cameraManager.setPosicao_CamJogo(this.posicaoCorredor.x + this.cameraManager.getDiferenca_CamJogo().x, this.posicaoCorredor.y + this.cameraManager.getDiferenca_CamJogo().y);
    }
}
