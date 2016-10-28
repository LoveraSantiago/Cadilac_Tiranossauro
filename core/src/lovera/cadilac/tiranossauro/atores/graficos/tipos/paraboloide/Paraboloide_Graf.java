package lovera.cadilac.tiranossauro.atores.graficos.tipos.paraboloide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.utils.Direcao;
import lovera.cadilac.tiranossauro.atores.graficos.equacoes.EquacaoQuadratica;

final class Paraboloide_Graf implements Disposable {

    private Direcao lado;
    private Direcao eixo;

    private final ShapeRenderer shapeRenderer;

    private float contador;

    private float alturaChegadaTemp;

    private final Corredor corredor;
    
    private final Vector2 pt1Desenho;
    private final Vector2 pt2Desenho;

    private final EquacaoQuadratica quadratica;

    private final Paraboloide_ProjetorPtFuturo projetorPt;

    public Paraboloide_Graf(Corredor corredor, EquacaoQuadratica quadratica, Paraboloide_ProjetorPtFuturo projetorPt) {
        this.quadratica = quadratica;
        this.corredor = corredor;
        this.projetorPt = projetorPt;

        this.shapeRenderer = new ShapeRenderer();
        this.pt1Desenho = new Vector2();
        this.pt2Desenho = new Vector2();
    }

    public void desenharGrafico(Vector3 entradaPtSuperior, Vector3 entradaPtLateral) {
        this.alturaChegadaTemp = entradaPtSuperior.y;

        definirDirecao(entradaPtLateral);
        definirEquacaoQuadratica(entradaPtSuperior, entradaPtLateral);
        desenharParabola(entradaPtSuperior);
    }

    private void definirDirecao(Vector3 entradaPtLateral) {
        this.lado = entradaPtLateral.x > this.corredor.getPosicaoProjX() ? Direcao.DIREITA : Direcao.ESQUERDA;
    }

    //LEMBRETE: entradaPtSuperior vira o ponto X e entradaPtLateral vira o pontovertice
    private void definirEquacaoQuadratica(Vector3 entradaPtSuperior, Vector3 entradaPtLateral){
        entradaPtSuperior.x = entradaPtSuperior.y - this.corredor.getPosicaoProjY();
        entradaPtSuperior.y = 0;


        if(this.lado == Direcao.DIREITA){
            entradaPtLateral.y = entradaPtLateral.x - this.corredor.getPosicaoProjX();
        }
        else{
            entradaPtLateral.y = this.corredor.getPosicaoProjX() - entradaPtLateral.x;
        }
        entradaPtLateral.x = entradaPtSuperior.x / 2;

        this.quadratica.definirEquacaoQuadratica(entradaPtLateral.x, entradaPtLateral.y, entradaPtSuperior.x, entradaPtSuperior.y);

        definirEixo(entradaPtSuperior, entradaPtLateral);
    }

    private void definirEixo(Vector3 entradaPtSuperior, Vector3 entradaPtLateral) {
        this.eixo = entradaPtSuperior.x > entradaPtLateral.y ? Direcao.HORIZONTAL : Direcao.VERTICAL;
    }

    //LEMBRETE: entradaPtSuperior vira o ponto X e entradaPtLateral vira o pontovertice
    private void desenharParabola(Vector3 entradaPtSuperior){
        this.shapeRenderer.setProjectionMatrix(this.corredor.getCameraManipulador().getCameraProjecao().combined);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);

        Gdx.gl.glLineWidth(60);

        this.pt1Desenho.set(this.corredor.getPosicaoProjetada());
        if(lado == Direcao.DIREITA){
            procedimentoADireita(entradaPtSuperior);
            this.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturo_HorizontalDireita(0, this.corredor.getPosicaoProjetada()));
        }
        else{
            procedimentoAEsquerda(entradaPtSuperior);
            this.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturo_HorizontalEsquerda(0, this.corredor.getPosicaoProjetada()));
        }
        this.shapeRenderer.end();
    }

    private void procedimentoADireita(Vector3 entradaPtSuperior){
        for(this.contador = 0; this.contador < entradaPtSuperior.x; this.contador = this.contador + 1f){

            this.pt2Desenho.set(this.contador, this.quadratica.getY(this.contador));
            this.projetorPt.inverterXYDoVector2(this.pt2Desenho);
            this.pt2Desenho.x += this.corredor.getPosicaoProjX();
            this.pt2Desenho.y += this.corredor.getPosicaoProjY();

            this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.corredor.getPosicaoProjX(), this.alturaChegadaTemp);
    }

    private void procedimentoAEsquerda(Vector3 entradaPtSuperior){
        for(this.contador = 0; this.contador < entradaPtSuperior.x; this.contador = this.contador + 1f){

            this.pt2Desenho.set(this.contador, this.quadratica.getY(this.contador));
            this.projetorPt.inverterXYDoVector2(this.pt2Desenho);
            this.pt2Desenho.x += this.corredor.getPosicaoProjX();
            this.pt2Desenho.y += this.corredor.getPosicaoProjY();
            this.pt2Desenho.x = this.corredor.getPosicaoProjX() - (this.pt2Desenho.x - this.corredor.getPosicaoProjX());//Espelhamento de X

            this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.corredor.getPosicaoProjX(), this.alturaChegadaTemp);
    }


    public Direcao getLado(){
        return this.lado;
    }

    public Direcao getEixo() {
        return this.eixo;
    }

    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
    }
}
