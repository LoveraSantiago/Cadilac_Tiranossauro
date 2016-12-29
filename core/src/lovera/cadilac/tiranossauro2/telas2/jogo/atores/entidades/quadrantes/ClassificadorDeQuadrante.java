package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoQuadrante;

public final class ClassificadorDeQuadrante {

    private static final List<TipoQuadrante> listaDeQuadrantes;

    private int contador;
    private TipoQuadrante quadranteAtual;

    static {
        listaDeQuadrantes = new ArrayList<TipoQuadrante>(5);
        listaDeQuadrantes.add(new Quad0());
        listaDeQuadrantes.add(new Quad1());
        listaDeQuadrantes.add(new Quad2());
        listaDeQuadrantes.add(new Quad3());
        listaDeQuadrantes.add(new Quad4());
    }

    public ClassificadorDeQuadrante() {
        resetQuadrante();
    }

    public void determinarQuadrante(Vector2 ptOrigem, Vector2 pt){

        for(this.contador = 0; this.contador < 5; this.contador++){

            this.quadranteAtual = listaDeQuadrantes.get(this.contador);
            this.quadranteAtual.setPontos(ptOrigem, pt.x, pt.y);
            if(this.quadranteAtual.meuQuadrante()){
                break;
            }
        }
    }

    public boolean pontoAtingido(){
        return this.quadranteAtual.pontoAtingido();
    }

    public void resetQuadrante(){
        this.quadranteAtual = listaDeQuadrantes.get(0);
    }
}
