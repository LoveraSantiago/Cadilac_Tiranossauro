package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos;

import lovera.cadilac.tiranossauro2.utils.custom_anotacoes.Revisao;

@Revisao(
        dataModificacao = "09/02/2017",
        dataRevisao     = "09/02/2017",
        revisada = true,

        importsVerificados = true,
        classePublica = false,

        classeFinal = false,
        justificativa_classeFinal = "A classe é uma interface",

        metodosEncapsulados = true,
        atributosEncapsulados = true,

        substituivel = false,

        descricao = "Interface que permite deslizador notificar MenuGraficos, " +
                    "que por sua vez delega ao Volante chamar o Volante Controle " +
                    "para definir o tamanho dos componentes do volante a partir do " +
                    "tamanho da table de menu",
        utilizadores = { Deslizador.class },
        implementadores = { MenuGraficos2.class }

)
interface MsgFromDeslizador {

    void setBarraPosicao_Normal();

}
