package lovera.cadilac.tiranossauro2.telas2.jogo;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.componente.tela.mSpriteBatch;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.PistaDeCorrida2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;

final class SingletonsManager_TelaJogo implements Disposable{

    private CameraManager cameraManagerTemp;

    public void iniciliazarSingletons(){
        //CAMERA DEVE SER O PRIMEIRO! NUNCA MUDAR ESSA ORDEM
        new CameraManager().inicializar();
        new mSpriteBatch().inicializar();

        new FaseManager2().inicializar();

        new PistaDeCorrida2().inicializar();
    }

    public void render(float delta){
        this.cameraManagerTemp = CameraManager.getInstance();

        this.cameraManagerTemp.update();

        //UPDATE DO SPRITEBATCH COM CAMERA JOGO PARA RENDERIZAR PISTA
        this.cameraManagerTemp.updateSpriteBatch_CamJogo();

        //UPDATE DO SPRITEBATCH COM CAMERA NORMAL
        this.cameraManagerTemp.updateSpriteBatch_CamProj();
    }

    @Override
    public void dispose() {
        mSpriteBatch.getInstance().dispose();
    }
}
