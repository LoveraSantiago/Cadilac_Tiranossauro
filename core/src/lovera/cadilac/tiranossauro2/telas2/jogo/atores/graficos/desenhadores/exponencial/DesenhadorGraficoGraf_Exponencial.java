package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.exponencial;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.ProjetorDePontoFuturo2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoExponencial2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.ArrastarEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;

//LINK DE AJUDA: https://www.desmos.com/calculator/3fisjexbvp
public final class DesenhadorGraficoGraf_Exponencial extends DesenhadorGrafico {

    private float helperContador;
    private float contador;

    private final EquacaoExponencial2 eqExponencial;
    private final CameraManager cameraManager;
    private final ProjetorDePontoFuturo2 projetorPt;
    private final Corredor2 corredorP;

    private final Vector2 ptToque;
    private final Vector2 posicaoCorredor;

    public DesenhadorGraficoGraf_Exponencial() {
        super(new ArrastarEntrada2());

        this.corredorP = CorredorUnico.getInstancia().getCorredorManager().getCorredorP();
        this.posicaoCorredor = this.corredorP.getPosicaoJogo();

        this.cameraManager = CameraUnico.getCameraManager();

        this.eqExponencial = new EquacaoExponencial2();
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

        this.eqExponencial.setB(this.posicaoCorredor.y - this.ptToque.y);
        this.pt1Desenho.set(this.posicaoCorredor);

        if(this.posicaoCorredor.x < this.ptToque.x){
            procedimentoADireita();
        }
        else{
            procedimentoAEsquerda();
        }
        super.fecharShapeRenderer();
    }

    private void procedimentoADireita() {
        this.helperContador = getProporcaoDoGraficoPeloToque();

        for(this.contador = 1;
            this.contador <= this.helperContador;
            this.contador = this.contador + .1f) {

            this.pt2Desenho.set(this.posicaoCorredor.x + this.contador, this.posicaoCorredor.y + (this.eqExponencial.getY(this.contador)));
            super.addLinhaToShapeRenderer(this.pt1Desenho, this.pt2Desenho);

            super.addInformacao(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        super.rotacionarEAtualizar(this.projetorPt.calcularPtFuturoDireita(this.eqExponencial, 1, this.posicaoCorredor), this.posicaoCorredor);
        this.corredorP.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());
    }

    private void procedimentoAEsquerda() {
        this.ptToque.x = this.projetorPt.espelharEsquerdaPDireita(this.ptToque.x, this.posicaoCorredor.x);
        this.helperContador = getProporcaoDoGraficoPeloToque();

        for(this.contador = 1;
            this.contador <= this.helperContador;
            this.contador = this.contador + .1f) {

            this.pt2Desenho.set(this.posicaoCorredor.x + this.contador, (this.posicaoCorredor.y + (this.eqExponencial.getY(this.contador))));
            this.pt2Desenho.x = this.projetorPt.espelharEsquerdaPDireita(this.pt2Desenho.x, this.posicaoCorredor.x);

            super.addLinhaToShapeRenderer(this.pt1Desenho, this.pt2Desenho);

            super.addInformacao(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        super.rotacionarEAtualizar(this.projetorPt.calcularPtFuturoEsquerda(this.eqExponencial, 1, this.posicaoCorredor), this.posicaoCorredor);
        this.corredorP.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());
    }

    private float getProporcaoDoGraficoPeloToque(){
        //(Tamanho do espaco horizontal entre o toque e a posicao do jogador) * (Tamanho do espaco horizontal entre o topo da pela e a posicao y do jogador) /10
        //dessa forma pega o tamanho proporcional
        return this.eqExponencial.getX((this.ptToque.x - this.posicaoCorredor.x) * (this.cameraManager.getMaiorPtY_CamProj() - this.posicaoCorredor.y) / 10);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
