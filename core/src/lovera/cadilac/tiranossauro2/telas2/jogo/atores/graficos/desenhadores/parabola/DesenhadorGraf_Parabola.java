package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.parabola;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Rotacionador;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoQuadratica2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.InformacaoUnico;

public final class DesenhadorGraf_Parabola extends DesenhadorGrafico{

    private float contador;
    private float alturaChegadaTemp;

    private final Entrada2 entrada;
    private final Vector2 ptSuperior;
    private final Vector2 ptLateral;
    private final Vector2 posicaoCorredor;

    private final Corredor2 corredorP;
    private final ProjetorPt_ParabolaFuturo projetorPt;
    private final EquacaoQuadratica2 quadratica;
    private final InformacaoManager informacao;
    private final Rotacionador rotacionador;

    private final Vector2 pt1Desenho;
    private final Vector2 pt2Desenho;

    public DesenhadorGraf_Parabola() {
        this.entrada = new PincaEntrada2();

        this.quadratica = new EquacaoQuadratica2();
        this.projetorPt = new ProjetorPt_ParabolaFuturo();

        this.pt1Desenho = new Vector2();
        this.pt2Desenho = new Vector2();
        this.ptSuperior = new Vector2();
        this.ptLateral  = new Vector2();

        this.corredorP = CorredorUnico.getInstancia().getCorredorManager().getCorredorP();
        this.posicaoCorredor = this.corredorP.getPosicaoJogo();

        this.informacao = InformacaoUnico.getInstancia().getInformacaoManager();

        this.rotacionador = new Rotacionador();
    }

    @Override
    public void meDesenhar(Object objeto) {
        resetarComponentes();
        desenharParabola();
    }

    private void resetarComponentes(){
        this.informacao.resetarInformacao();

        this.ptSuperior.set(this.entrada.getPtSuperior());
        this.ptLateral.set(this.entrada.getPtLateral());
        this.alturaChegadaTemp = this.entrada.getPtSuperior().y;
    }

    //LEMBRETE: entradaPtSuperior vira o ponto X e entradaPtLateral vira o pontovertice
    private void desenharParabola(){
        super.iniciarShapeRenderer();

        this.pt1Desenho.set(this.posicaoCorredor);
        if(this.ptLateral.x > this.posicaoCorredor.x){
            procedimentoADireita();
        }
        else{
            procedimentoAEsquerda();
        }
        super.fecharShapeRenderer();
    }

    private void procedimentoADireita(){
        this.ptSuperior.x = this.ptSuperior.y -  this.posicaoCorredor.y;
        this.ptSuperior.y = 0;

        this.ptLateral.y = this.ptLateral.x -  this.posicaoCorredor.x;
        this.ptLateral.x = this.ptSuperior.x / 2;

        this.quadratica.definirEquacaoQuadratica(this.ptLateral.x, this.ptLateral.y, this.ptSuperior.x, this.ptSuperior.y);


        for(this.contador = 1; this.contador < this.ptSuperior.x; this.contador = this.contador + .1f){

            this.pt2Desenho.set(this.contador, this.quadratica.getY(this.contador));
            this.projetorPt.inverterXYDoVector2(this.pt2Desenho);
            this.pt2Desenho.x += this.posicaoCorredor.x;
            this.pt2Desenho.y += this.posicaoCorredor.y;

            super.addLinhaToShapeRenderer(this.pt1Desenho, this.pt2Desenho);

            addToComponentes(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        super.addLinhaToShapeRenderer(this.pt1Desenho, this.posicaoCorredor.x, this.alturaChegadaTemp);

        addToComponentes(this.pt1Desenho.x, this.pt1Desenho.y, this.posicaoCorredor.x, this.alturaChegadaTemp);

        this.rotacionador.atualizarAnguloDoJogo();
        this.rotacionador.rotacionar(this.projetorPt.calcularPtFuturoDireita(this.quadratica, 1, this.posicaoCorredor), this.posicaoCorredor);
        this.corredorP.setPtFuturoProj(this.rotacionador.getResultX(), this.rotacionador.getResultY());
    }

    private void procedimentoAEsquerda(){
        this.ptSuperior.x = this.ptSuperior.y -  this.posicaoCorredor.y;
        this.ptSuperior.y = 0;

        this.ptLateral.y =  this.posicaoCorredor.x - this.ptLateral.x;
        this.ptLateral.x = this.ptSuperior.x / 2;

        this.quadratica.definirEquacaoQuadratica(this.ptLateral.x, this.ptLateral.y, this.ptSuperior.x, this.ptSuperior.y);

        for(this.contador = 1; this.contador < this.ptSuperior.x; this.contador = this.contador + .1f){

            this.pt2Desenho.set(this.contador, this.quadratica.getY(this.contador));
            this.projetorPt.inverterXYDoVector2(this.pt2Desenho);
            this.pt2Desenho.x += this.posicaoCorredor.x;
            this.pt2Desenho.y += this.posicaoCorredor.y;
            this.pt2Desenho.x = this.projetorPt.espelharDireitaPEsquerda(this.pt2Desenho.x, this.posicaoCorredor.x);

            super.addLinhaToShapeRenderer(this.pt1Desenho, this.pt2Desenho);

            addToComponentes(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        super.addLinhaToShapeRenderer(this.pt1Desenho, this.posicaoCorredor.x, this.alturaChegadaTemp);

        addToComponentes(this.pt1Desenho.x, this.pt1Desenho.y, this.posicaoCorredor.x, this.alturaChegadaTemp);

        this.rotacionador.atualizarAnguloDoJogo();
        this.rotacionador.rotacionar(this.projetorPt.calcularPtFuturoEsquerda(this.quadratica, 1, this.posicaoCorredor), this.posicaoCorredor);
        this.corredorP.setPtFuturoProj(this.rotacionador.getResultX(), this.rotacionador.getResultY());
    }

    private void addToComponentes(float pt1X, float pt1Y, float pt2X, float pt2Y){
        this.informacao.addInformacao(pt1X, pt1Y, pt2X, pt2Y);
    }

    @Override
    public Entrada2 getEntrada() {
        return entrada;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
