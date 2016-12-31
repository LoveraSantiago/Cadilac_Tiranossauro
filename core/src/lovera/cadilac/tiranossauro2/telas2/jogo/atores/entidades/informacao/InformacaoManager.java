package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.pontos.Pontos;

public class InformacaoManager {

    private final Pontos pontos;
    private final Distancia distancia;

    public InformacaoManager() {
        this.pontos = new Pontos();
        this.distancia = new Distancia();
    }

    public void resetarInformacao(){
        this.pontos.limparPontos();
        this.distancia.resetEspacoPercorrido();
    }

    public void addInformacao(float pt1X, float pt1Y, float pt2X, float pt2Y){
        this.pontos.addPontos(pt2X, pt2Y);
        this.distancia.addEspacoPercorrido(pt1X, pt1Y, pt2X, pt2Y);
    }

    public Pontos getPontos() {
        return pontos;
    }

    public Distancia getDistancia() {
        return distancia;
    }
}
