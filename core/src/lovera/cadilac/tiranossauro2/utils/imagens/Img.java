package lovera.cadilac.tiranossauro2.utils.imagens;

public enum Img {

    SETA_FIXA("setafixa"),
    SETA_MOVEL("setamovel"),
    VOLANTE("meiabussola");

    private String nomeImg;

    private Img(String nomeImg) {
        this.nomeImg = nomeImg;
    }

    public String getNomeImg() {
        return nomeImg;
    }
}
