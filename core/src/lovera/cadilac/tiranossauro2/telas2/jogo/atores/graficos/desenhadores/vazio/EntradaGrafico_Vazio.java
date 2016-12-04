package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vazio;

import com.badlogic.gdx.InputProcessor;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.EntradaGrafica;

/**
 * CLASSE PARA SER DEFAULT EM GRAFICO MANAGER PARA EVITAR CHECAGEM DE NULL
 */
public final class EntradaGrafico_Vazio extends EntradaGrafica{

    @Override
    public InputProcessor passarInputProcessor() {
        throw new UnsupportedOperationException("Entrada Grafico vazio não passa input processor");
    }

    @Override
    public void meDesenhar(Object objeto) {
    }
}
