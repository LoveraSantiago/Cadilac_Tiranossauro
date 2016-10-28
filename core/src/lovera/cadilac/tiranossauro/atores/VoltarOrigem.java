package lovera.cadilac.tiranossauro.atores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro.atores.graficos.equacoes.EquacaoDaReta;
import lovera.cadilac.tiranossauro.atores.graficos.utils.Direcao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.acoes.Acao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.acoes.VelocidadeAcao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.MensagemGraficos;
import lovera.cadilac.tiranossauro.contratos.Desenhavel;
import lovera.cadilac.tiranossauro.controladores.ControleManager;
import lovera.cadilac.tiranossauro.controladores.Fase;
import lovera.cadilac.tiranossauro.controladores.FaseManager;

public class VoltarOrigem extends Acao implements Desenhavel{

    private float difHorizontal;
    private float difVertical;

    private final Vector2 posCameraInicialJogo;

    private final EquacaoDaReta equacaoDaReta;

    private final FaseManager faseManager;
    private final ControleManager controleManager;

    //LEMBRETE: MensagemGraficos esta sendo passado null. Obrigatoriamente sobreescrevendo finalizarAcao
    public VoltarOrigem(FaseManager faseManager, ControleManager controleManager, Corredor corredor, MensagemGraficos msg) {
        super(corredor, msg);

        this.faseManager = faseManager;
        this.controleManager = controleManager;

        this.equacaoDaReta = new EquacaoDaReta();
        this.posCameraInicialJogo = new Vector2();
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        switch(this.faseManager.getFaseAtual()){
            case CALCULAR_VOLTA:
                calcularVolta();
            case TELA_VOLTANDO:
                acao();
        }
    }

    private void calcularVolta() {
        definirLadoAcao();
        definirDiferencas();
        definirEquacaoDaReta();
        definirEixo();
        definirContador();

        this.posCameraInicialJogo.set(getPosicao_CameraJogo().x, getPosicao_CameraJogo().y);

        this.faseManager.setFaseAtual(Fase.TELA_VOLTANDO);
    }

    private void definirLadoAcao() {
        setLado(super.corredor.getPosicaoInicial().x > super.corredor.getPosicaoProjX() ? Direcao.ESQUERDA : Direcao.DIREITA);
    }

    private void definirDiferencas(){
        this.difHorizontal = super.corredor.getPosicaoProjX() - super.corredor.getPosicaoInicial().x;
        this.difVertical   = super.corredor.getPosicaoProjY() - super.corredor.getPosicaoInicial().y;
    }

    private void definirEquacaoDaReta() {
        this.equacaoDaReta.definirEquacaoDaReta(getPosicao_CameraProjecao().x, getPosicao_CameraProjecao().y,
                getPosicao_CameraProjecao().x + this.difHorizontal, getPosicao_CameraProjecao().y + this.difVertical);
    }

    private void definirEixo() {
        setEixo(Math.abs(super.corredor.getPosicaoInicial().x - super.corredor.getPosicaoProjX()) > Math.abs(this.corredor.getPosicaoProjY() - super.corredor.getPosicaoInicial().y) ?
                Direcao.HORIZONTAL : Direcao.VERTICAL);
    }

    private void definirContador() {
        if(getEixo() == Direcao.HORIZONTAL){
            super.contador = getPosicao_CameraProjecao().x;
        }
        else{
            super.contador = getPosicao_CameraProjecao().y;
        }
    }

    @Override
    protected boolean caminhoPercorridoCompleto() {
        return getPosicao_CameraProjecao().y > super.corredor.getCameraManipulador().getPosicaoInicialCameraJogo().y + this.difVertical;
    }

    @Override
    protected void acaoHorizontalADireita() {
        super.contador += VelocidadeAcao.VOLTAR_ORIGEM_PASSO_H * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);
        getPosicao_CameraProjecao().set(this.contador, this.equacaoDaReta.getY(this.contador), 0);
        ajusteCameraJogo();
    }

    @Override
    protected void acaoHorizontalAEsquerda() {
        this.contador -= VelocidadeAcao.VOLTAR_ORIGEM_PASSO_H * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);
        getPosicao_CameraProjecao().set(this.contador, this.equacaoDaReta.getY(this.contador), 0);
        ajusteCameraJogo();
    }

    @Override
    protected void acaoVerticalADireita() {
        acaoVerticalEmComum();
    }

    @Override
    protected void acaoVerticalAEsquerda() {
        acaoVerticalEmComum();
    }

    private void acaoVerticalEmComum(){
        super.contador += VelocidadeAcao.VOLTAR_ORIGEM_PASSO_V * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);
        getPosicao_CameraProjecao().set(this.equacaoDaReta.getX(super.contador), super.contador, 0);
        ajusteCameraJogo();
    }


    private void ajusteCameraJogo(){
        getPosicao_CameraJogo().set(super.corredor.getCameraManipulador().reangularEmRelacaoAoPonto(getPosicao_CameraProjecao(), super.corredor.getCameraManipulador().getPosicaoInicialCameraJogo()));
        getPosicao_CameraJogo().x = this.posCameraInicialJogo.x + getPosicao_CameraJogo().x;
        getPosicao_CameraJogo().y = this.posCameraInicialJogo.y + getPosicao_CameraJogo().y;
    }

    @Override
    protected void finalizarAcao() {
        getPosicao_CameraProjecao().x = super.corredor.getCameraManipulador().getPosicaoInicialCameraJogo().x + this.difHorizontal;
        getPosicao_CameraProjecao().y = super.corredor.getCameraManipulador().getPosicaoInicialCameraJogo().y + this.difVertical;
        ajusteCameraJogo();

        this.posCameraInicialJogo.set(getPosicao_CameraJogo().x, getPosicao_CameraJogo().y);

        this.corredor.resetMe();

        this.contador = 0;
        this.faseManager.setFaseAtual(Fase.ESCOLHENDO_GRAFICO);
        this.controleManager.voltarMenuGrafico();
    }

    @Override
    protected void calcularPontoFinal() {
        throw new UnsupportedOperationException("Voltar Origem 2 não precisa calcular ponto final");
    }
}
