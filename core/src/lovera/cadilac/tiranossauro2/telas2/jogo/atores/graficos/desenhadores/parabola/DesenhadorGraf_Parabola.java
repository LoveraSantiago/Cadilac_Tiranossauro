package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.parabola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.DirecaoEnum;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.equacoes.EquacaoQuadratica2;

public final class DesenhadorGraf_Parabola implements TipoDesenhavel, Disposable{

    private final Entrada2 entrada2;

    private final ShapeRenderer shapeRenderer;

    private final Definidor_Helper definidor;
    private final EquacaoQuadratica2 quadratica2;
    private final ProjetorPt_Parabola projetorPt;

    private float contador;
    private float alturaChegadaTemp;

    private final Vector2 pt1Desenho;
    private final Vector2 pt2Desenho;
    private final Vector2 posicaoCorredorP;

    public DesenhadorGraf_Parabola(Entrada2 entrada2) {
        this.entrada2 = entrada2;

        this.quadratica2 = new EquacaoQuadratica2();
        this.definidor = new Definidor_Helper(entrada2);
        this.projetorPt = new ProjetorPt_Parabola();

        this.shapeRenderer = new ShapeRenderer();
        this.pt1Desenho = new Vector2();
        this.pt2Desenho = new Vector2();
        this.posicaoCorredorP = CorredorManager.getInstancia().getCorredorP().getPosicaoJogo();
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.alturaChegadaTemp = this.entrada2.getPtSuperior().y;
        this.definidor.definirDirecao();
        this.definidor.definirEquacaoQuadratica(this.quadratica2);
        desenharParabola();
    }

    //LEMBRETE: entradaPtSuperior vira o ponto X e entradaPtLateral vira o pontovertice
    private void desenharParabola(){
        this.shapeRenderer.setProjectionMatrix(CameraManager.getInstancia().getCameraProjecao().combined);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);

        Gdx.gl.glLineWidth(60);

        this.pt1Desenho.set(posicaoCorredorP);
        if(this.definidor.isLado(DirecaoEnum.DIREITA)){
            procedimentoADireita();
//            this.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturo_HorizontalDireita(0, this.corredor.getPosicaoProjetada()));
        }
        else{
            procedimentoAEsquerda();
//            this.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturo_HorizontalEsquerda(0, this.corredor.getPosicaoProjetada()));
        }
        this.shapeRenderer.end();
    }

    private void procedimentoADireita(){
        for(this.contador = 0; this.contador < this.entrada2.getPtSuperior().x; this.contador = this.contador + 1f){

            this.pt2Desenho.set(this.contador, this.quadratica2.getY(this.contador));
            this.projetorPt.inverterXYDoVector2(this.pt2Desenho);
            this.pt2Desenho.x += this.posicaoCorredorP.x;
            this.pt2Desenho.y += this.posicaoCorredorP.y;

            this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.posicaoCorredorP.x, this.alturaChegadaTemp);
    }

    private void procedimentoAEsquerda(){
        for(this.contador = 0; this.contador < this.entrada2.getPtSuperior().x; this.contador = this.contador + 1f){

            this.pt2Desenho.set(this.contador, this.quadratica2.getY(this.contador));
            this.projetorPt.inverterXYDoVector2(this.pt2Desenho);
            this.pt2Desenho.x += this.posicaoCorredorP.x;
            this.pt2Desenho.y += this.posicaoCorredorP.y;
            this.pt2Desenho.x = this.posicaoCorredorP.x - (this.pt2Desenho.x - this.posicaoCorredorP.x);//Espelhamento de X

            this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.posicaoCorredorP.x, this.alturaChegadaTemp);
    }


    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
    }
}
