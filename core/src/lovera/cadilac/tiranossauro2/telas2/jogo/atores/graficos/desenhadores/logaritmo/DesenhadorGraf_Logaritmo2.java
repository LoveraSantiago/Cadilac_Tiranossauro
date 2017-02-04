package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.logaritmo;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoEquacao_LOGEXP;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.ProjetorDePontoFuturo2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoLogaritmo3;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.DesenhadorGraf_LOGEXP;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.ArrastarEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

//LINK DE AJUDA: https://www.desmos.com/calculator/auubsajefh
//TODO: refatorar DesenhadorGraficoGraf_Exponencial com DesenhadorGraficoGraf_Logaritmo classes muito iguais REALIZAR ESSA REFATORACAO SOMENTE APOS AJUSTAR OS GRAFICOS PARA MAIORES ABERTURAS ESTAO MUITO VERTICAIS AMBOS
public final class DesenhadorGraf_Logaritmo2 extends DesenhadorGraf_LOGEXP {

    private float helperContador;
    private float contador;

    private final TipoEquacao_LOGEXP eqLog;
    private final CameraManager cameraManager;
    private final ProjetorDePontoFuturo2 projetorPt;

    private final Vector2 ptToque;

    public DesenhadorGraf_Logaritmo2() {
        super(new ArrastarEntrada2());

        this.cameraManager = CameraUnico.getCameraManager();

        this.projetorPt = new ProjetorDePontoFuturo2();
        this.eqLog = new EquacaoLogaritmo3();

        this.ptToque = new Vector2();
    }

    @Override
    public void meDesenhar(Object objeto) {
        resetarComponentes();
        desenharLogaritmo();
    }

    private void resetarComponentes(){
        super.resetarInformacao();

        this.ptToque.set(super.entrada.getPtToque());
//        this.ptToque.set(15.851f, 1.333f);
    }

    private void desenharLogaritmo(){
        super.iniciarShapeRenderer();

        this.eqLog.setB(super.posicaoCorredor.y - this.ptToque.y, this.cameraManager.getMaiorPtY_CamProj() - super.posicaoCorredor.y, this.cameraManager.getMaiorPtX_CamProj() - this.posicaoCorredor.x);
        super.pt1Desenho.set(super.posicaoCorredor);

        if(super.posicaoCorredor.x < this.ptToque.x){
            procedimentoADireita();
        }
        else{
            procedimentoAEsquerda();
        }
        super.fecharShapeRenderer();
    }

    public void procedimentoADireita(){
        this.helperContador = getProporcaoDoGraficoPeloToque();
        for(this.contador = .1f;
            this.contador <= this.helperContador;
            this.contador = this.contador + .1f) {

            super.pt2Desenho.set(super.posicaoCorredor.x + this.contador, super.posicaoCorredor.y + (this.eqLog.getY(this.contador)));

            super.addLinhaToShapeRenderer(super.pt1Desenho, super.pt2Desenho);

            super.addInformacao(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
        super.rotacionarEAtualizar(this.projetorPt.calcularPtFuturoDireita(this.eqLog, 1, super.posicaoCorredor), super.posicaoCorredor);
        super.corredor.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());
    }

    public void procedimentoAEsquerda(){
        this.ptToque.x = this.projetorPt.espelharEsquerdaPDireita(this.ptToque.x, super.posicaoCorredor.x);
        this.helperContador = getProporcaoDoGraficoPeloToque();
        for(this.contador = .1f;
            this.contador <= this.helperContador;
            this.contador = this.contador + .1f) {

            super.pt2Desenho.set(super.posicaoCorredor.x + this.contador, (super.posicaoCorredor.y + (this.eqLog.getY(this.contador))));
            super.pt2Desenho.x = this.projetorPt.espelharEsquerdaPDireita(super.pt2Desenho.x, super.posicaoCorredor.x);

            super.addLinhaToShapeRenderer(super.pt1Desenho, super.pt2Desenho);

            super.addInformacao(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
        super.rotacionarEAtualizar(this.projetorPt.calcularPtFuturoEsquerda(this.eqLog, 1, super.posicaoCorredor), super.posicaoCorredor);
        super.corredor.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());
    }

    private float getProporcaoDoGraficoPeloToque(){
        //A largura maxima do grafico é dividida pela diferenca da largura maxima e a posicao do corredor
        // apos é multiplicado pela diferenca entre o toque e a posicao do corredor
        //finalmente é passado a equacao para pegar a largura maxima
        return this.eqLog.getX((this.eqLog.getMaximo() / (this.cameraManager.getMaiorPtX_CamProj() - this.posicaoCorredor.x)) * (this.ptToque.x - this.posicaoCorredor.x));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
