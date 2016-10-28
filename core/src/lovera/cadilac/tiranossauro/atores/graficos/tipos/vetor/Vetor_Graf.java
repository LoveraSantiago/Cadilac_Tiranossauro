package lovera.cadilac.tiranossauro.atores.graficos.tipos.vetor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro.atores.Corredor;

final class Vetor_Graf implements Disposable {

    private final ShapeRenderer shapeRenderer;

    private final Corredor corredor;
    

    public Vetor_Graf(Corredor corredor) {
        this.corredor = corredor;

        this.shapeRenderer = new ShapeRenderer();
    }

    public final void desenharGrafico(Vector3 entradaPtSuperior, Vector3 entradaPtLateral){
        this.corredor.setPtFuturoProj(entradaPtLateral.x, entradaPtSuperior.y);

        this.shapeRenderer.setProjectionMatrix(this.corredor.getCameraManipulador().getCameraProjecao().combined);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);

        Gdx.gl.glLineWidth(60);
        this.shapeRenderer.line(this.corredor.getPosicaoProjX(), this.corredor.getPosicaoProjY() - 1,
                                this.corredor.getPosicaoProjX(), entradaPtSuperior.y);

        if(entradaPtLateral.x >= this.corredor.getPosicaoProjX()){
            this.shapeRenderer.line(this.corredor.getPosicaoProjX() - 1, this.corredor.getPosicaoProjY(),
                                    entradaPtLateral.x, this.corredor.getPosicaoProjY());
        }
        else{
            this.shapeRenderer.line(entradaPtLateral.x, this.corredor.getPosicaoProjY(),
                                    this.corredor.getPosicaoProjX() + 1, this.corredor.getPosicaoProjY());
        }
        this.shapeRenderer.end();
    }

    @Override
    public final void dispose() {
        this.shapeRenderer.dispose();
    }
}
