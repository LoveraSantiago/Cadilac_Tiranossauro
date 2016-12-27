package lovera.cadilac.tiranossauro2.contratos.tipo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public interface TipoSubCamera extends TipoCamera{

    void updateSpriteBatch();
    OrthographicCamera getCamera();
    Vector2 getPosicao();
    Vector2 getDiferenca();
    void setPosicao(float x, float y);
}
