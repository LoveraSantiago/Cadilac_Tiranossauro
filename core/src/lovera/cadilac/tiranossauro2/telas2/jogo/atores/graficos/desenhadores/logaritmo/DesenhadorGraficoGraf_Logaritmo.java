package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.logaritmo;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.ProjetorDePontoFuturo2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoLogaritmo2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.ArrastarEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;

//LINK DE AJUDA: https://www.desmos.com/calculator/auubsajefh
public final class DesenhadorGraficoGraf_Logaritmo extends DesenhadorGrafico {

    private float helperContador;
    private float contador;

    private final EquacaoLogaritmo2 eqLog;
    private final CameraManager cameraManager;
    private final Corredor2 corredorP;
    private final ProjetorDePontoFuturo2 projetorPt;

    private final Vector2 ptToque;
    private final Vector2 posicaoCorredor;

    public DesenhadorGraficoGraf_Logaritmo() {
        super(new ArrastarEntrada2());

        this.corredorP = CorredorUnico.getInstancia().getCorredorManager().getCorredorP();
        this.posicaoCorredor = this.corredorP.getPosicaoJogo();

        this.cameraManager = CameraUnico.getCameraManager();

        this.projetorPt = new ProjetorDePontoFuturo2();
        this.eqLog = new EquacaoLogaritmo2();

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

        this.eqLog.setB(this.posicaoCorredor.y - this.ptToque.y, this.cameraManager.getMaiorPtY_CamProj() - this.posicaoCorredor.y);
        super.pt1Desenho.set(this.posicaoCorredor);

        if(this.posicaoCorredor.x < this.ptToque.x){
            procedimentoADireita();
        }
        else{
            procedimentoAEsquerda();
        }
        super.fecharShapeRenderer();
    }

    public void procedimentoADireita(){
        this.helperContador = getProporcaoDoGraficoPeloToque();
        for(this.contador = .01f;
            this.contador <= this.helperContador;
            this.contador = this.contador + .01f) {

            super.pt2Desenho.set(this.posicaoCorredor.x + this.contador, this.posicaoCorredor.y + (this.eqLog.getY(this.contador)));

            super.addLinhaToShapeRenderer(super.pt1Desenho, super.pt2Desenho);

            super.addInformacao(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
        super.rotacionarEAtualizar(this.projetorPt.calcularPtFuturoDireita(this.eqLog, 1, this.posicaoCorredor), this.posicaoCorredor);
        this.corredorP.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());
    }

    public void procedimentoAEsquerda(){
        this.ptToque.x = this.projetorPt.espelharEsquerdaPDireita(this.ptToque.x, this.posicaoCorredor.x);
        this.helperContador = getProporcaoDoGraficoPeloToque();
        for(this.contador = .01f;
            this.contador <= this.helperContador;
            this.contador = this.contador + .01f) {

            super.pt2Desenho.set(this.posicaoCorredor.x + this.contador, (this.posicaoCorredor.y + (this.eqLog.getY(this.contador))));
            super.pt2Desenho.x = this.projetorPt.espelharEsquerdaPDireita(super.pt2Desenho.x, this.posicaoCorredor.x);

            super.addLinhaToShapeRenderer(super.pt1Desenho, super.pt2Desenho);

            super.addInformacao(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
       super.rotacionarEAtualizar(this.projetorPt.calcularPtFuturoEsquerda(this.eqLog, 1, this.posicaoCorredor), this.posicaoCorredor);
        this.corredorP.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());
    }

    private float getProporcaoDoGraficoPeloToque(){
        //(Tamanho do espaco horizontal entre o toque e a posicao do jogador) * (Tamanho do espaco horizontal entre o topo da tela e a posicao y do jogador) /10 q e a distancia maxima
        //dessa forma pega o tamanho proporcional
        return this.eqLog.getX((this.ptToque.x - this.posicaoCorredor.x) * this.eqLog.getAlturaMax() / 10);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
