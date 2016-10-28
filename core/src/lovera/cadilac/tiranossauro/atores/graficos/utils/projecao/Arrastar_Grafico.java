package lovera.cadilac.tiranossauro.atores.graficos.utils.projecao;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.exp_log.ExpLog_ProjetorPtFuturo;
import lovera.cadilac.tiranossauro.atores.graficos.utils.Direcao;

public abstract class Arrastar_Grafico implements Disposable {

    protected float contador;

    protected final Corredor corredor;
    
    protected final Vector2 pt1Desenho;
    protected final Vector2 pt2Desenho;
    private   final Vector2 ptToqueProcessado;

    protected Direcao lado;

    protected final ShapeRenderer shapeRenderer;

    protected final ExpLog_ProjetorPtFuturo projetorPt;

    public Arrastar_Grafico(Corredor corredor, ExpLog_ProjetorPtFuturo projetorPt) {
        this.corredor = corredor;
        this.projetorPt = projetorPt;

        this.shapeRenderer = new ShapeRenderer();
        this.pt1Desenho = new Vector2();
        this.pt2Desenho = new Vector2();
        this.ptToqueProcessado = new Vector2();
        this.contador = 0;
    }

    public final void desenharGrafico(Vector3 posicaoToque) {
        this.corredor.getCameraManipulador().getCameraProjecao().unproject(posicaoToque);

        this.shapeRenderer.setProjectionMatrix(this.corredor.getCameraManipulador().getCameraProjecao().combined);
        this.shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);

        desenharToque(posicaoToque);
        desenharArco(posicaoToque);
    }

    protected final void desenharToque(Vector3 posicaoToque){
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.circle(posicaoToque.x, posicaoToque.y, 2.5f);
        this.shapeRenderer.end();
    }

    protected final void desenharArco(Vector3 posicaoToque){
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl.glLineWidth(60);

        if(this.corredor.getPosicaoProjX() > posicaoToque.x){//toque acontecendo no lado esquerdo
            procedimentoAEsquerda(posicaoToque);
        }
        else if(this.corredor.getPosicaoProjX() < posicaoToque.x){//toque acontecendo no lado direito
            procedimentoADireita(posicaoToque);
        }

        this.shapeRenderer.end();
        setPtToqueProcessado(posicaoToque);
    }

    abstract protected void procedimentoAEsquerda(Vector3 posicaoToque);
    abstract protected void procedimentoADireita(Vector3 posicaoToque);

    private void setPtToqueProcessado(Vector3 posicaoToque){
        this.ptToqueProcessado.set(posicaoToque.x, posicaoToque.y);
    }

    public final Vector2 getPtToqueProcessado(){
        return this.ptToqueProcessado;
    }

    public final Direcao getLado(){
        return this.lado;
    }

    public final Vector2 getUltimpoPontoProcessado(){
        return this.pt2Desenho;
    }

    @Override
    public final void dispose() {
        this.shapeRenderer.dispose();
    }
}
