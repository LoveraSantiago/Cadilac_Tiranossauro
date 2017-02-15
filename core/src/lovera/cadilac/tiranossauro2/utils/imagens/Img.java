package lovera.cadilac.tiranossauro2.utils.imagens;

public enum Img {

    BAD_LOGIC("badlogic"),
    CARRO_VERMELHO("redcarpeq"),
    DIGITAL       ("digital"),
    SETA_FIXA     ("setafixa"),
    SETA_MOVEL    ("setamovel"),
    TRACO_Y       ("traco_deitado"),
    TRACO_X       ("traco_pe"),
    VOLANTE       ("meiabussola");

    private String nomeImg;

    private Img(String nomeImg) {
        this.nomeImg = nomeImg;
    }

    public String getNomeImg() {
        return nomeImg;
    }
}
