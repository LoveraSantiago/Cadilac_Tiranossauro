package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;

public abstract class DesenhadorGrafico implements TipoDesenhavel, Disposable{

    public abstract Entrada2 getEntrada();

}
