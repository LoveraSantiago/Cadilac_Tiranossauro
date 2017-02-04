package lovera.cadilac.tiranossauro2.componente.tela;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro2.utils.custom_anotacoes.Revisao;

@Revisao(
        dataModificacao = "02/02/2017",
        dataRevisao     = "02/02/2017",
        revisada = true,

        importsVerificados = true,

        classePublica = true,
        justificativa_classePublica = "Instancia dessa classe unica, gerenciada por SpriteBatchUnico",

        classeFinal = true,
        justificativa_classeFinal = "",

        metodosEncapsulados = true,
        atributosEncapsulados = true,

        substituivel = false,

        TODOS = {},
        descricao = "Classe que empacota SpriteBatch da libgdx. Objeto 'caro' deve ter apenas uma instancia. Utilizado em n partes.",
        links = {"https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/graphics/g2d/SpriteBatch.html"}
)
public final class SpriteBatchManager{

    private final SpriteBatch spriteBatch;

    public SpriteBatchManager() {
        this.spriteBatch = new SpriteBatch();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}
