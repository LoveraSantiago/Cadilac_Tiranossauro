package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.componente.tela.mSpriteBatch;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;

public class MenuGraficos2 implements TipoDesenhavel, TipoSingleton, Disposable{

    private static MenuGraficos2 menuGraficos;

    private final ParserToTable_MenuGraficos2 parserTable;
    private final Deslizador deslizador;

    private final Stage stage;
    private final Actor table;

    public MenuGraficos2() {
        //VOLANTE DEVE SER O PRIMEIRO POIS SERA ADICIONADO NA TABLE
        new Volante().inicializar();

        this.deslizador = new Deslizador();
        this.deslizador.inicializar();

        this.parserTable = new ParserToTable_MenuGraficos2();
        this.table = this.parserTable.meTransforme(null);

        this.stage = new Stage(CameraManager.getInstance().getViewPortCameraProjecao(), mSpriteBatch.getInstance());
        this.stage.addActor(this.table);
    }

    @Override
    public void inicializar() {
        menuGraficos = this;
    }

    public static MenuGraficos2 getInstancia() {
        return menuGraficos;
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.deslizador.analisarPosicaoDaBarra();
        this.table.setPosition(this.deslizador.getPosicaoBarra(), 0);
        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.parserTable.dispose();
        Volante.getInstancia().dispose();
    }
}
