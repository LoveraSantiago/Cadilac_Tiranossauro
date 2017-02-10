package lovera.cadilac.tiranossauro2.utils.imagens;

public enum Img9Patch {

    AREA("area_jogavel2"),
    EIXO_X("seta_x_eixocart"),
    EIXO_Y("seta_y_eixocart"),
    FILLER_HORIZONTAL("filler_horizontal"),
    FILLER_VERTICAL("filler_vertical");

    private String nomeImg;

    private Img9Patch(String nomeImg) {
        this.nomeImg = nomeImg;
    }

    public String getNomeImg() {
        return nomeImg;
    }
}
