package lovera.cadilac.tiranossauro2.telas2.jogo;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.telas2.gerais.NinePatchLeitor;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.pista_de_corrida.PistaDeCorrida2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficoManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.MenuManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.box2d.Box2DManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.Box2DUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.ControleUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.FaseUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.GraficoUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.InformacaoUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.MenuUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.NinePatchUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.VoltarOrigemUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.voltar.VoltarOrigem2;

//TODO revisar todos os disposes acho que ta faltando
//TODO encontrar forma melhor para gerenciar singletons
final class SingletonsManager_TelaJogo implements Disposable{

    private CameraManager cameraManager;
    private MenuManager2 menuManager2;
    private CorredorManager corredorManager;
    private GraficoManager2 graficoManager2;
    private VoltarOrigem2 voltarOrigem2;

    private Box2DManager box2DManager;

    private PistaDeCorrida2 pistaDeCorrida2;

    public void inicializarSingletons(){
        new SpriteBatchUnico().inicializar();

        new NinePatchUnico().inicializar();

        new CameraUnico().inicializar();
        this.cameraManager = CameraUnico.getCameraManager();

        new FaseUnico().inicializar();

        new Box2DUnico().inicializar();
        this.box2DManager = Box2DUnico.getInstancia().getBox2DManager();

        this.pistaDeCorrida2 = new PistaDeCorrida2();
        this.pistaDeCorrida2.inicializar();

        new InformacaoUnico().inicializar();

        new CorredorUnico().inicializar();
        this.corredorManager = CorredorUnico.getInstancia().getCorredorManager();

        new MenuUnico().inicializar();
        this.menuManager2 = MenuUnico.getInstancia().getMenuManager();

        new GraficoUnico().inicializar();
        this.graficoManager2 = GraficoUnico.getInstancia().getGraficoManager2();

        new ControleUnico(this.graficoManager2.getMapaEntradaGraficas()).inicializar();

        new VoltarOrigemUnico().inicializar();
        this.voltarOrigem2 = VoltarOrigemUnico.voltarOrigem2();
    }

    public void render(float delta){
        this.cameraManager.atualizar();
        this.box2DManager.atualizar();

        //UPDATE DO SPRITEBATCH COM CAMERA JOGO PARA RENDERIZAR PISTA
        this.cameraManager.updateSpriteBatch_CamJogo();
        this.pistaDeCorrida2.meDesenhar(null);

        //UPDATE DO SPRITEBATCH COM CAMERA NORMAL
        this.cameraManager.updateSpriteBatch_CamProj();
        this.graficoManager2.meDesenhar(null);

        //UPDATE DO SPRITEBATCH COM CAMERA JOGO PARA RENDERIZAR PISTA
        this.cameraManager.updateSpriteBatch_CamJogo();
        this.corredorManager.meDesenhar(null);

        this.menuManager2.meDesenhar(null);

        //SENDO CHAMADO POR ULTIMO PARA PODER SER VISUALIZADO POR CIMA DA TELA
        this.box2DManager.meDesenhar(null);
        this.voltarOrigem2.meDesenhar(null);
    }

    @Override
    public void dispose() {
        SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch().dispose();
        NinePatchUnico.getInstancia().getNinePatchLeitor().dispose();

        this.box2DManager.dispose();
        this.menuManager2.dispose();
    }
}
