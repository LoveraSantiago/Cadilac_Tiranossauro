package lovera.cadilac.tiranossauro2.contratos.tipo;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;

public interface TipoDesenhadorGrafico extends TipoDesenhavel, Disposable{

    Entrada2 getEntrada();

}
