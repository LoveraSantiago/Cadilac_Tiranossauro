package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

public abstract class DesenhadorGrafico implements TipoDesenhavel, Disposable{

    private static ShapeRenderer shapeRenderer;
    private static Matrix4 matrizCameraProjecao;

    public DesenhadorGrafico() {
        if(shapeRenderer == null){
            shapeRenderer = new ShapeRenderer();
        }
        if(matrizCameraProjecao == null){
            matrizCameraProjecao = CameraUnico.getCameraManager().getCamera_CamProj().combined;
        }
    }

    public abstract Entrada2 getEntrada();

    protected void iniciarShapeRenderer(){
        shapeRenderer.setProjectionMatrix(this.matrizCameraProjecao);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);
        Gdx.gl.glLineWidth(60);
    }

    protected void addLinhaToShapeRenderer(Vector2 pt1, float x2, float y2){
        addLinhaToShapeRenderer(pt1.x, pt1.y, x2, y2);
    }

    protected void addLinhaToShapeRenderer(Vector2 pt1, Vector2 pt2){
        addLinhaToShapeRenderer(pt1.x, pt1.y, pt2.x, pt2.y);
    }

    protected void addLinhaToShapeRenderer(float x1, float y1, float x2, float y2){
        shapeRenderer.line(x1, y1, x2, y2);
    }

    protected void fecharShapeRenderer(){
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
