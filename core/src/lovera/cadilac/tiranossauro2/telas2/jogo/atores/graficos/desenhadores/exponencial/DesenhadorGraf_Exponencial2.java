package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.exponencial;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoEquacao_LOGEXP;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.ProjetorDePontoFuturo2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoExponencial3;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.DesenhadorGraf_LOGEXP;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.ArrastarEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

//LINK DE AJUDA: https://www.desmos.com/calculator/3fisjexbvp
//TODO: refatorar DesenhadorGraficoGraf_Exponencial com DesenhadorGraficoGraf_Logaritmo classes muito iguais REALIZAR ESSA REFATORACAO SOMENTE APOS AJUSTAR OS GRAFICOS PARA MAIORES ABERTURAS ESTAO MUITO VERTICAIS AMBOS
public final class DesenhadorGraf_Exponencial2 extends DesenhadorGraf_LOGEXP {

    private float helperContador;
    private float contador;

    private final TipoEquacao_LOGEXP eqExponencial;
    private final CameraManager cameraManager;
    private final ProjetorDePontoFuturo2 projetorPt;

    private final Vector2 ptToque;

    public DesenhadorGraf_Exponencial2() {
        super(new ArrastarEntrada2());

        this.cameraManager = CameraUnico.getCameraManager();

        this.eqExponencial = new EquacaoExponencial3();
        this.projetorPt = new ProjetorDePontoFuturo2();

        this.ptToque    = new Vector2();
    }

    @Override
    public void meDesenhar(Object objeto) {
        resetarComponentes();
        desenharExponencial();
    }

    private void resetarComponentes(){
        super.resetarInformacao();

        this.ptToque.set(super.entrada.getPtToque());
//        this.ptToque.set(15.851f, 1.333f);
    }

    private void desenharExponencial() {
        super.iniciarShapeRenderer();

        this.eqExponencial.setB(super.posicaoCorredor.y - this.ptToque.y, this.cameraManager.getMaiorPtY_CamProj() - super.posicaoCorredor.y, this.cameraManager.getMaiorPtX_CamProj() - super.posicaoCorredor.x);
        this.pt1Desenho.set(super.posicaoCorredor);

        if(super.posicaoCorredor.x < this.ptToque.x){
            procedimentoADireita();
        }
        else{
            procedimentoAEsquerda();
        }
        super.fecharShapeRenderer();
    }

    private void procedimentoADireita() {
        this.helperContador = getProporcaoDoGraficoPeloToque();

        for(this.contador = .1f;
            this.contador <= this.helperContador;
            this.contador = this.contador + .1f) {

            this.pt2Desenho.set(super.posicaoCorredor.x + this.contador, super.posicaoCorredor.y + (this.eqExponencial.getY(this.contador)));
            super.addLinhaToShapeRenderer(this.pt1Desenho, this.pt2Desenho);

            super.addInformacao(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        super.rotacionarEAtualizar(this.projetorPt.calcularPtFuturoDireita(this.eqExponencial, 1, super.posicaoCorredor), super.posicaoCorredor);
        super.corredor.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());
    }

    private void procedimentoAEsquerda() {
        this.ptToque.x = this.projetorPt.espelharEsquerdaPDireita(this.ptToque.x, super.posicaoCorredor.x);
        this.helperContador = getProporcaoDoGraficoPeloToque();

        for(this.contador = .1f;
            this.contador <= this.helperContador;
            this.contador = this.contador + .1f) {

            this.pt2Desenho.set(super.posicaoCorredor.x + this.contador, (super.posicaoCorredor.y + (this.eqExponencial.getY(this.contador))));
            this.pt2Desenho.x = this.projetorPt.espelharEsquerdaPDireita(this.pt2Desenho.x, super.posicaoCorredor.x);

            super.addLinhaToShapeRenderer(this.pt1Desenho, this.pt2Desenho);

            super.addInformacao(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        super.rotacionarEAtualizar(this.projetorPt.calcularPtFuturoEsquerda(this.eqExponencial, 1, super.posicaoCorredor), super.posicaoCorredor);
        super.corredor.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());
    }

    private float getProporcaoDoGraficoPeloToque(){
        //A largura maxima do grafico é dividida pela diferenca da largura maxima e a posicao do corredor
        // apos é multiplicado pela diferenca entre o toque e a posicao do corredor
        return (this.eqExponencial.getMaximo() / (this.cameraManager.getMaiorPtX_CamProj() - this.posicaoCorredor.x)) * (this.ptToque.x - this.posicaoCorredor.x);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
