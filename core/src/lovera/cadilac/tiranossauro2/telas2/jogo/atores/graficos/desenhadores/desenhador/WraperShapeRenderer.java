package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

final class WraperShapeRenderer implements Disposable{

    private final ShapeRenderer shapeRenderer;
    private final Matrix4 matrizCameraProjecao;

    public WraperShapeRenderer() {
        this.shapeRenderer = new ShapeRenderer();
        this.matrizCameraProjecao = CameraUnico.getCameraManager().getCamera_CamProj().combined;
    }

    public void iniciarShapeRenderer(){
        shapeRenderer.setProjectionMatrix(this.matrizCameraProjecao);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);
        Gdx.gl.glLineWidth(60);
    }

    public final void addLinhaToShapeRenderer(float x1, float y1, float x2, float y2){
        shapeRenderer.line(x1, y1, x2, y2);
    }

    public final void fecharShapeRenderer(){
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
