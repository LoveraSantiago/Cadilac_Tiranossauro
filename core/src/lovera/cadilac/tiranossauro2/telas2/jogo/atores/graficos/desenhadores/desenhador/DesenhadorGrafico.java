package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;

public abstract class DesenhadorGrafico implements TipoDesenhavel, Disposable{

    private static WraperShapeRenderer wShapeRenderer;

    public DesenhadorGrafico() {
        if(wShapeRenderer == null){
            wShapeRenderer = new WraperShapeRenderer();
        }
    }

    public abstract Entrada2 getEntrada();

    protected void iniciarShapeRenderer(){
        wShapeRenderer.iniciarShapeRenderer();
    }

    protected final void addLinhaToShapeRenderer(Vector2 pt1, float x2, float y2){
        addLinhaToShapeRenderer(pt1.x, pt1.y, x2, y2);
    }

    protected final void addLinhaToShapeRenderer(Vector2 pt1, Vector2 pt2){
        addLinhaToShapeRenderer(pt1.x, pt1.y, pt2.x, pt2.y);
    }

    protected final void addLinhaToShapeRenderer(float x1, float y1, float x2, float y2){
        wShapeRenderer.addLinhaToShapeRenderer(x1, y1, x2, y2);
    }

    protected final void fecharShapeRenderer(){
        wShapeRenderer.fecharShapeRenderer();
    }

    @Override
    public void dispose() {
       wShapeRenderer.dispose();
    }
}
