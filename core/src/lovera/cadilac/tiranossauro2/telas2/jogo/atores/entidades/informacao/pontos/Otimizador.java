package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.pontos;

import java.util.List;

final class Otimizador {

    private final List<Float> listaX;
    private final List<Float> listaY;

    private float atualX;
    private float atualY;

    private int contador;

    public Otimizador(List<Float> listaX, List<Float> listaY) {
        this.listaX = listaX;
        this.listaY = listaY;
    }

    public void otimizarPontos(){
        this.atualX = this.listaX.get(0);
        this.atualY = this.listaY.get(0);

        this.contador = 1;
        while(this.contador < this.listaX.size() - 1){
            if(calcularHipotenusa() < 1){
                this.listaX.remove(this.contador);
                this.listaY.remove(this.contador);
            }
            else{
                this.atualX = this.listaX.get(this.contador);
                this.atualY = this.listaY.get(this.contador);
                this.contador++;
            }
        }
    }

    private double calcularHipotenusa(){
        return Math.sqrt(Math.pow(this.listaX.get(this.contador) - this.atualX, 2) + Math.pow(this.listaY.get(this.contador) - this.atualY, 2));
    }
}
