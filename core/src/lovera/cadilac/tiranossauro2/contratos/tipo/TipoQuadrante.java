package lovera.cadilac.tiranossauro2.contratos.tipo;

import com.badlogic.gdx.math.Vector2;

public interface TipoQuadrante {

    boolean meuQuadrante();
    boolean pontoAtingido();

    void setPontos(float ptOrigemX, float ptOrigemY, float ptX, float ptY);

    int getMeuQuadrante();
}
