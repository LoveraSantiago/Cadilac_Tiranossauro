package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoEquacao_LOGEXP;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.ProjetorDePontoFuturo2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.ArrastarEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

public abstract class DesenhadorGraf_LOGEXP extends DesenhadorGrafico{

    private static float helperContador;
    private static float contador;

    protected final TipoEquacao_LOGEXP equacao;

    protected static CameraManager cameraManager;
    private static ProjetorDePontoFuturo2 projetorPt;

    protected static final Vector2 ptToque;

    static{
        ptToque = new Vector2();
    }

    public DesenhadorGraf_LOGEXP(TipoEquacao_LOGEXP equacao) {
        super(new ArrastarEntrada2());

        this.equacao = equacao;

        if(cameraManager == null){
            cameraManager = CameraUnico.getCameraManager();
        }

        if(projetorPt == null){
            projetorPt = new ProjetorDePontoFuturo2();
        }
    }

    @Override
    public void meDesenhar(Object objeto) {
        resetarComponentes();
        desenharGrafico();
        super.desenharFiller(super.pt2Desenho.y, this.helperContador);
    }

    private void resetarComponentes(){
        super.resetarInformacao();
        ptToque.set(super.entrada.getPtToque());
    }

    private void desenharGrafico(){
        super.iniciarShapeRenderer();

        this.equacao.setB(super.posicaoCorredor.y - ptToque.y, cameraManager.getMaiorPtY_CamProj() - super.posicaoCorredor.y, cameraManager.getMaiorPtX_CamProj() - super.posicaoCorredor.x);
        super.pt1Desenho.set(super.posicaoCorredor);

        if(super.posicaoCorredor.x < ptToque.x){
            procedimentoADireita();
        }
        else{
            procedimentoAEsquerda();
        }
        super.fecharShapeRenderer();
    }

    public void procedimentoADireita(){
        helperContador = getProporcaoDoGraficoPeloToque();
        for(contador = .1f;
            contador <= helperContador;
            contador = contador + .1f) {

            super.pt2Desenho.set(super.posicaoCorredor.x + contador, super.posicaoCorredor.y + (this.equacao.getY(contador)));

            super.addLinhaToShapeRenderer(super.pt1Desenho, super.pt2Desenho);

            super.addInformacao(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
        super.rotacionarEAtualizar(projetorPt.calcularPtFuturoDireita(this.equacao, 1, super.posicaoCorredor), super.posicaoCorredor);
        super.corredor.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());

        helperContador += super.posicaoCorredor.x;
    }

    public void procedimentoAEsquerda(){
        ptToque.x = projetorPt.espelharEsquerdaPDireita(ptToque.x, super.posicaoCorredor.x);
        helperContador = getProporcaoDoGraficoPeloToque();

        for(contador = .1f;
            contador <= helperContador;
            contador = contador + .1f) {

            super.pt2Desenho.set(super.posicaoCorredor.x + contador, (super.posicaoCorredor.y + (this.equacao.getY(contador))));
            super.pt2Desenho.x = projetorPt.espelharEsquerdaPDireita(super.pt2Desenho.x, super.posicaoCorredor.x);

            super.addLinhaToShapeRenderer(super.pt1Desenho, super.pt2Desenho);

            super.addInformacao(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
        super.rotacionarEAtualizar(projetorPt.calcularPtFuturoEsquerda(this.equacao, 1, super.posicaoCorredor), super.posicaoCorredor);
        super.corredor.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());


        helperContador = super.posicaoCorredor.x - helperContador;
    }
    
    protected abstract float getProporcaoDoGraficoPeloToque();

    @Override
    public void dispose() {
        super.dispose();
    }
}
