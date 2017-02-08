package lovera.cadilac.tiranossauro2.utils.imagens;

public enum Img {

    VOLANTE("meiabussola");

    private String nomeImg;

    private Img(String nomeImg) {
        this.nomeImg = nomeImg;
    }

    public String getNomeImg() {
        return nomeImg;
    }
}
