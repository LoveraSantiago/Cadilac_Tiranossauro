package lovera.cadilac.tiranossauro2.contratos.tipo;

public interface TipoEquacao {

    float getX(float y);
    float getY(float x);
    void setMaximo(float maxAlt, float maxLarg);
    float getMaximo();
}
