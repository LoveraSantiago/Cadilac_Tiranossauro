package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.logaritmo;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.ProjetorDePontoFuturo2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Rotacionador;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoLogaritmo2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.ArrastarEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.InformacaoUnico;

//LINK DE AJUDA: https://www.desmos.com/calculator/auubsajefh
public final class DesenhadorGraficoGraf_Logaritmo extends DesenhadorGrafico {

    private float helperContador;
    private float contador;

    private final EquacaoLogaritmo2 eqLog;
    private final Entrada2 entrada2;
    private final InformacaoManager informacao;
    private final CameraManager cameraManager;
    private final Corredor2 corredorP;
    private final Rotacionador rotacionador;
    private final ProjetorDePontoFuturo2 projetorPt;

    private final Vector2 ptToque;
    private final Vector2 posicaoCorredor;

    private final Vector2 pt1Desenho;
    private final Vector2 pt2Desenho;

    public DesenhadorGraficoGraf_Logaritmo() {
        this.entrada2 = new ArrastarEntrada2();

        this.corredorP = CorredorUnico.getInstancia().getCorredorManager().getCorredorP();
        this.posicaoCorredor = this.corredorP.getPosicaoJogo();

        this.cameraManager = CameraUnico.getCameraManager();

        this.informacao = InformacaoUnico.getInstancia().getInformacaoManager();
        this.rotacionador = new Rotacionador();
        this.projetorPt = new ProjetorDePontoFuturo2();
        this.eqLog = new EquacaoLogaritmo2();

        this.ptToque = new Vector2();
        this.pt1Desenho = new Vector2();
        this.pt2Desenho = new Vector2();
    }

    @Override
    public void meDesenhar(Object objeto) {
        resetarComponentes();
        desenharLogaritmo();
    }

    private void resetarComponentes(){
        this.informacao.resetarInformacao();

        this.ptToque.set(this.entrada2.getPtToque());
//        this.ptToque.set(15.851f, 1.333f);
    }

    private void desenharLogaritmo(){
        super.iniciarShapeRenderer();

        this.eqLog.setB(this.posicaoCorredor.y - this.ptToque.y, this.cameraManager.getMaiorPtY_CamProj() - this.posicaoCorredor.y);
        this.pt1Desenho.set(this.posicaoCorredor);

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

            this.pt2Desenho.set(this.posicaoCorredor.x + this.contador, this.posicaoCorredor.y + (this.eqLog.getY(this.contador)));

            super.addLinhaToShapeRenderer(this.pt1Desenho, this.pt2Desenho);

            addToComponentes(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }

        this.rotacionador.atualizarAnguloDoJogo();
        this.rotacionador.rotacionar(this.projetorPt.calcularPtFuturoDireita(this.eqLog, 1, this.posicaoCorredor), this.posicaoCorredor);
        this.corredorP.setPtFuturoProj(this.rotacionador.getResultX(), this.rotacionador.getResultY());
    }

    public void procedimentoAEsquerda(){
        this.ptToque.x = this.projetorPt.espelharEsquerdaPDireita(this.ptToque.x, this.posicaoCorredor.x);
        this.helperContador = getProporcaoDoGraficoPeloToque();
        for(this.contador = .01f;
            this.contador <= this.helperContador;
            this.contador = this.contador + .01f) {

            this.pt2Desenho.set(this.posicaoCorredor.x + this.contador, (this.posicaoCorredor.y + (this.eqLog.getY(this.contador))));
            this.pt2Desenho.x = this.projetorPt.espelharEsquerdaPDireita(this.pt2Desenho.x, this.posicaoCorredor.x);

            super.addLinhaToShapeRenderer(this.pt1Desenho, this.pt2Desenho);

            addToComponentes(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        this.rotacionador.atualizarAnguloDoJogo();
        this.rotacionador.rotacionar(this.projetorPt.calcularPtFuturoEsquerda(this.eqLog, 1, this.posicaoCorredor), this.posicaoCorredor);
        this.corredorP.setPtFuturoProj(this.rotacionador.getResultX(), this.rotacionador.getResultY());
    }

    private float getProporcaoDoGraficoPeloToque(){
        //(Tamanho do espaco horizontal entre o toque e a posicao do jogador) * (Tamanho do espaco horizontal entre o topo da tela e a posicao y do jogador) /10 q e a distancia maxima
        //dessa forma pega o tamanho proporcional
        return this.eqLog.getX((this.ptToque.x - this.posicaoCorredor.x) * this.eqLog.getAlturaMax() / 10);
    }

    private void addToComponentes(float pt1X, float pt1Y, float pt2X, float pt2Y){
        this.informacao.addInformacao(pt1X, pt1Y, pt2X, pt2Y);
    }

    @Override
    public Entrada2 getEntrada() {
        return entrada2;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
