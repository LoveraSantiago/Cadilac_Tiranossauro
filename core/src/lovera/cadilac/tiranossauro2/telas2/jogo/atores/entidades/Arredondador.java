package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades;


public final class Arredondador {

    private int potencia;
    private int contador;
    private final int escala;

    private float temp;

    public Arredondador() {
        this.escala = 1;
    }

    public Arredondador(int escala){
        this.escala = escala;
    }

    public float arredondar(float numero){
        this.potencia = 10;
        for(this.contador = 1; this.contador < this.escala; this.contador++){
            potencia *= 10;
        }
        this.temp = numero * potencia;
        return (float)(int)((this.temp - (int) this.temp) >= 0.5f ? this.temp + 1 : temp) / potencia;
    }

}
