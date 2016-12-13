package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao;

public class Informacao {

    private final Pontos pontos;
    private final Distancia distancia;

    public Informacao() {
        this.pontos = new Pontos();
        this.distancia = new Distancia();
    }

    private void resetarInformacao(){
        this.pontos.limparPontos();
        this.distancia.resetEspacoPercorrido();
    }

    private void addInformacao(float pt1X, float pt1Y, float pt2X, float pt2Y){
        this.pontos.addPontos(pt2X, pt2Y);
        this.distancia.addEspacoPercorrido(pt1X, pt1Y, pt2X, pt2Y);
    }
}
