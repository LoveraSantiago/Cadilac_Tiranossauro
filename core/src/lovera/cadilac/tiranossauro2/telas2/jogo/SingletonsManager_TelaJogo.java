package lovera.cadilac.tiranossauro2.telas2.jogo;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.componente.tela.mSpriteBatch;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.pista_de_corrida.PistaDeCorrida2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.ControleManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.GraficoManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.MenuManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.MeuBox2D2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.PontoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;

final class SingletonsManager_TelaJogo implements Disposable{

    private CameraManager cameraManager;
    private MenuManager2 menuManager2;
    private CorredorManager corredorManager;
    private GraficoManager2 graficoManager2;

    private MeuBox2D2 meuBox2DTemp;

    private PistaDeCorrida2 pistaDeCorrida2;

    public void iniciliazarSingletons(){
        new mSpriteBatch().inicializar();

        this.cameraManager = new CameraManager();
        this.cameraManager.inicializar();

        new FaseManager2().inicializar();

        new MeuBox2D2().inicializar();

        this.pistaDeCorrida2 = new PistaDeCorrida2();
        this.pistaDeCorrida2.inicializar();

        new PontoManager().inicializar();

        this.corredorManager = new CorredorManager();
        this.corredorManager.inicializar();

        this.menuManager2 = new MenuManager2();
        this.menuManager2.inicializar();

        this.graficoManager2 = new GraficoManager2();
        this.graficoManager2.inicializar();

        new ControleManager2(this.graficoManager2.getMapaEntradaGraficas()).inicializar();
    }

    public void render(float delta){
        this.meuBox2DTemp = MeuBox2D2.getInstancia();

        this.cameraManager.atualizar();
        this.meuBox2DTemp.atualizar();

        //UPDATE DO SPRITEBATCH COM CAMERA JOGO PARA RENDERIZAR PISTA
        this.cameraManager.updateSpriteBatch_CamJogo();
        this.pistaDeCorrida2.meDesenhar(null);

        //UPDATE DO SPRITEBATCH COM CAMERA NORMAL
        this.cameraManager.updateSpriteBatch_CamProj();
        this.graficoManager2.meDesenhar(null);
        this.corredorManager.meDesenhar(null);
        this.menuManager2.meDesenhar(null);

        //SENDO CHAMADO POR ULTIMO PARA PODER SER VISUALIZADO POR CIMA DA TELA
        this.meuBox2DTemp.meDesenhar(null);
    }

    @Override
    public void dispose() {
        mSpriteBatch.getInstancia().dispose();
        MeuBox2D2.getInstancia().dispose();
        this.menuManager2.dispose();
    }


}
