package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.visuais;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;

public final class Digitais implements TipoDesenhavel{

    private QTD qtd;

    private final Digital digitalHum;

    private final Digital digitalDois;
    public Digitais() {
        this.digitalHum = new Digital();
        this.digitalDois = new Digital();
    }

    public void desenharDigital(Vector2 pt1){
        desenharDigitalHum(pt1);
        this.qtd = QTD.HUM;

        meDesenhar(null);
    }

    public void desenharDigitais(Vector2 pt1, Vector2 pt2){
        desenharDigitalHum(pt1);
        desenharDigitalDois(pt2);
        this.qtd = QTD.DOIS;

        meDesenhar(null);
    }

    private void desenharDigitalHum(Vector2 pt1){
        this.digitalHum.setPosicao(pt1);
    }

    private void desenharDigitalDois(Vector2 pt2){
        this.digitalDois.setPosicao(pt2);
    }

    @Override
    public void meDesenhar(Object objeto) {
        switch (this.qtd){
            case DOIS:
                this.digitalDois.meDesenhar(null);
            case HUM:
                this.digitalHum.meDesenhar(null);
                break;
        }
    }

    private enum QTD{
        HUM, DOIS;
    }
}
