package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.parabola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.DirecaoEnum;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.ponto.Pontos;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.PontoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.equacoes.EquacaoQuadratica2;

public final class DesenhadorGraf_Parabola implements TipoDesenhavel, Disposable{

    private DirecaoEnum lado;

    private final ShapeRenderer shapeRenderer;

    private final ProjetorPt_Parabola projetorPt;

    private float contador;

    private float alturaChegadaTemp;

    private final Vector2 pt1Desenho;
    private final Vector2 pt2Desenho;

    private final Corredor2 corredorP;
    private final Vector2 posicaoCorredorP;

    private final Entrada2 entrada;
    private final Vector3 ptSuperior;
    private final Vector3 ptLateral;

    private final EquacaoQuadratica2 quadratica;

    private final Pontos pontos;

    public DesenhadorGraf_Parabola(Entrada2 entrada2) {
        this.entrada = entrada2;

        this.quadratica = new EquacaoQuadratica2();
        this.projetorPt = new ProjetorPt_Parabola();

        this.shapeRenderer = new ShapeRenderer();
        this.pt1Desenho = new Vector2();
        this.pt2Desenho = new Vector2();
        this.ptSuperior = new Vector3();
        this.ptLateral  = new Vector3();

        this.corredorP = CorredorManager.getInstancia().getCorredorP();
        this.posicaoCorredorP = this.corredorP.getPosicaoJogo();

        this.pontos = PontoManager.getInstancia().getPontos();
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.pontos.limparPontos();

        this.ptSuperior.set(this.entrada.getPtSuperior());
        this.ptLateral.set(this.entrada.getPtLateral());

        this.alturaChegadaTemp = this.entrada.getPtSuperior().y;
        definirDirecao();
        definirEquacaoQuadratica();
        desenharParabola();
    }

    private void definirDirecao() {
        this.lado = this.ptLateral.x > posicaoCorredorP.x ? DirecaoEnum.DIREITA : DirecaoEnum.ESQUERDA;
    }

    private void definirEquacaoQuadratica(){
        this.ptSuperior.x = this.ptSuperior.y -  this.posicaoCorredorP.y;
        this.ptSuperior.y = 0;

        if(this.lado == DirecaoEnum.DIREITA){
            this.ptLateral.y = this.ptLateral.x -  this.posicaoCorredorP.x;
        }
        else{
            this.ptLateral.y =  this.posicaoCorredorP.x - this.ptLateral.x;
        }
        this.ptLateral.x = this.ptSuperior.x / 2;

        this.quadratica.definirEquacaoQuadratica(this.ptLateral.x, this.ptLateral.y, this.ptSuperior.x, this.ptSuperior.y);
    }

    //LEMBRETE: entradaPtSuperior vira o ponto X e entradaPtLateral vira o pontovertice
    private void desenharParabola(){
        this.shapeRenderer.setProjectionMatrix(CameraManager.getInstancia().getCameraProjecao().combined);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);

        Gdx.gl.glLineWidth(60);

        this.pt1Desenho.set(posicaoCorredorP);
        if(this.lado == DirecaoEnum.DIREITA){
            procedimentoADireita();
            this.corredorP.setPtFuturoProj(this.projetorPt.calcularPtFuturo_HorizontalDireita(this.quadratica, 0, this.posicaoCorredorP));
        }
        else{
            procedimentoAEsquerda();
            this.corredorP.setPtFuturoProj(this.projetorPt.calcularPtFuturo_HorizontalEsquerda(this.quadratica, 0, this.posicaoCorredorP));
        }
        this.shapeRenderer.end();
    }

    private void procedimentoADireita(){
        for(this.contador = 0; this.contador < this.ptSuperior.x; this.contador = this.contador + 5f){

            this.pt2Desenho.set(this.contador, this.quadratica.getY(this.contador));
            this.projetorPt.inverterXYDoVector2(this.pt2Desenho);
            this.pt2Desenho.x += this.posicaoCorredorP.x;
            this.pt2Desenho.y += this.posicaoCorredorP.y;

            this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);
            this.pt1Desenho.set(this.pt2Desenho);

            addToPontos(this.pt2Desenho);
        }
        this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.posicaoCorredorP.x, this.alturaChegadaTemp);

        addToPontos(this.posicaoCorredorP.x, this.alturaChegadaTemp);
//        System.out.println("contador " + this.contador);
    }

    private void procedimentoAEsquerda(){
        for(this.contador = 0; this.contador < this.ptSuperior.x; this.contador = this.contador + 5f){

            this.pt2Desenho.set(this.contador, this.quadratica.getY(this.contador));
            this.projetorPt.inverterXYDoVector2(this.pt2Desenho);
            this.pt2Desenho.x += this.posicaoCorredorP.x;
            this.pt2Desenho.y += this.posicaoCorredorP.y;
            this.pt2Desenho.x = this.projetorPt.espelharDireitaPEsquerda(this.pt2Desenho.x, this.posicaoCorredorP.x);

            this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);
            this.pt1Desenho.set(this.pt2Desenho);

            addToPontos(this.pt2Desenho);
        }
        this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.posicaoCorredorP.x, this.alturaChegadaTemp);

        addToPontos(this.posicaoCorredorP.x, this.alturaChegadaTemp);
//        System.out.println("contador " + this.contador);
    }

    private void addToPontos(Vector2 posicao){
        this.pontos.addPontos(posicao);
    }

    private void addToPontos(float x, float y){
        this.pontos.addPontos(x, y);
    }

    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
    }
}
