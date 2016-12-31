package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.box2d.Box2DManager;

public final class Box2DUnico implements TipoSingleton {

    private static Box2DUnico box2DUnico;
    private final Box2DManager box2DManager;

    public Box2DUnico() {
        this.box2DManager = new Box2DManager();
    }

    @Override
    public void inicializar() {
        box2DUnico = this;
    }

    public static Box2DUnico getInstancia() {
        return box2DUnico;
    }

    public Box2DManager getBox2DManager() {
        return box2DManager;
    }
}
