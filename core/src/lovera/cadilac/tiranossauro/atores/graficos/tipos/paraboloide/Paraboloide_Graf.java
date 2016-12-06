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
        this.quadratica = quadratica;//ok
        this.corredor = corredor;//ok
        this.projetorPt = projetorPt;//ok

        this.shapeRenderer = new ShapeRenderer();//ok
        this.pt1Desenho = new Vector2();//ok
        this.pt2Desenho = new Vector2();//ok
    }

    public void desenharGrafico(Vector3 entradaPtSuperior, Vector3 entradaPtLateral) {
        this.alturaChegadaTemp = entradaPtSuperior.y;//ok

        definirDirecao(entradaPtLateral);
        definirEquacaoQuadratica(entradaPtSuperior, entradaPtLateral);
        desenharParabola(entradaPtSuperior);
    }

    private void definirDirecao(Vector3 entradaPtLateral) {
        this.lado = entradaPtLateral.x > this.corredor.getPosicaoProjX() ? Direcao.DIREITA : Direcao.ESQUERDA;//ok
    }

    //LEMBRETE: entradaPtSuperior vira o ponto X e entradaPtLateral vira o pontovertice
    private void definirEquacaoQuadratica(Vector3 entradaPtSuperior, Vector3 entradaPtLateral){
        entradaPtSuperior.x = entradaPtSuperior.y - this.corredor.getPosicaoProjY();//ok
        entradaPtSuperior.y = 0;//ok


        if(this.lado == Direcao.DIREITA){//ok
            entradaPtLateral.y = entradaPtLateral.x - this.corredor.getPosicaoProjX();//ok
        }//ok
        else{//ok
            entradaPtLateral.y = this.corredor.getPosicaoProjX() - entradaPtLateral.x;//ok
        }//ok
        entradaPtLateral.x = entradaPtSuperior.x / 2;//ok

        this.quadratica.definirEquacaoQuadratica(entradaPtLateral.x, entradaPtLateral.y, entradaPtSuperior.x, entradaPtSuperior.y);//ok

        definirEixo(entradaPtSuperior, entradaPtLateral);//ok
    }

    private void definirEixo(Vector3 entradaPtSuperior, Vector3 entradaPtLateral) {
        this.eixo = entradaPtSuperior.x > entradaPtLateral.y ? Direcao.HORIZONTAL : Direcao.VERTICAL;
    }

    //LEMBRETE: entradaPtSuperior vira o ponto X e entradaPtLateral vira o pontovertice
    private void desenharParabola(Vector3 entradaPtSuperior){//ok
        this.shapeRenderer.setProjectionMatrix(this.corredor.getCameraManipulador().getCameraProjecao().combined);//ok
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);//ok
        this.shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);//ok

        Gdx.gl.glLineWidth(60);//ok

        this.pt1Desenho.set(this.corredor.getPosicaoProjetada());//ok
        if(lado == Direcao.DIREITA){//ok
            procedimentoADireita(entradaPtSuperior);//ok
            this.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturo_HorizontalDireita(0, this.corredor.getPosicaoProjetada()));//ok
        }//ok
        else{//ok
            procedimentoAEsquerda(entradaPtSuperior);//ok
            this.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturo_HorizontalEsquerda(0, this.corredor.getPosicaoProjetada()));//ok
        }//ok
        this.shapeRenderer.end();//ok
    }//ok

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
