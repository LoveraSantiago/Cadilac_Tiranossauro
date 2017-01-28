package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.parabola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Rotacionador;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoQuadratica2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.InformacaoUnico;

public final class DesenhadorGraf_Parabola extends DesenhadorGrafico{

    private float contador;
    private float alturaChegadaTemp;

    private final Matrix4 matrizCameraProjecao;
    private final ShapeRenderer shapeRenderer;

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

        this.shapeRenderer = new ShapeRenderer();
        this.pt1Desenho = new Vector2();
        this.pt2Desenho = new Vector2();
        this.ptSuperior = new Vector2();
        this.ptLateral  = new Vector2();

        this.corredorP = CorredorUnico.getInstancia().getCorredorManager().getCorredorP();
        this.posicaoCorredor = this.corredorP.getPosicaoJogo();

        this.informacao = InformacaoUnico.getInstancia().getInformacaoManager();

        this.matrizCameraProjecao = CameraUnico.getCameraManager().getCamera_CamProj().combined;
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

    //TODO: configurar o shaperenderer seria necessario apenas uma vez! Testar alguma melhoria. *Obs ver se da problema uma vez q tem q dar end
    //LEMBRETE: entradaPtSuperior vira o ponto X e entradaPtLateral vira o pontovertice
    private void desenharParabola(){
        this.shapeRenderer.setProjectionMatrix(this.matrizCameraProjecao);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);

        Gdx.gl.glLineWidth(60);

        this.pt1Desenho.set(this.posicaoCorredor);
        if(this.ptLateral.x > this.posicaoCorredor.x){
            procedimentoADireita();
        }
        else{
            procedimentoAEsquerda();
        }
        this.shapeRenderer.end();
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

            this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            addToComponentes(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.posicaoCorredor.x, this.alturaChegadaTemp);

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

            this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            addToComponentes(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.posicaoCorredor.x, this.alturaChegadaTemp);

        addToComponentes(this.pt1Desenho.x, this.pt1Desenho.y, this.posicaoCorredor.x, this.alturaChegadaTemp);

        this.rotacionador.atualizarAnguloDoJogo();
        this.rotacionador.rotacionar(this.projetorPt.calcularPtFuturoEsquerda(this.quadratica, 1, this.posicaoCorredor), this.posicaoCorredor);
        this.corredorP.setPtFuturoProj(this.rotacionador.getResultX(), this.rotacionador.getResultY());
    }

    private void addToComponentes(float pt1X, float pt1Y, float pt2X, float pt2Y){
        this.informacao.addInformacao(pt1X, pt1Y, pt2X, pt2Y);
    }

    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
    }

    @Override
    public Entrada2 getEntrada() {
        return entrada;
    }
}
