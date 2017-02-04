package lovera.cadilac.tiranossauro2.contratos.tipo;

public interface TipoEquacao_LOGEXP extends TipoEquacao{

    void setB(float b, float diferencaAltura, float diferencaLargura);

    void setMaximo(float maxAlt, float maxLarg);
    float getMaximo();
}
