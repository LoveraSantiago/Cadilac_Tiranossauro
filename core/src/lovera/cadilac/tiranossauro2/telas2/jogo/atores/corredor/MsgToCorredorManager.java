package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import lovera.cadilac.tiranossauro2.utils.custom_anotacoes.Revisao;

@Revisao(
        dataModificacao = "20/02/2017",
        dataRevisao     = "20/02/2017",
        revisada = true,

        importsVerificados = true,

        classePublica = false,

        classeFinal = false,
        justificativa_classeFinal = "A classe é uma interface",

        metodosEncapsulados = true,
        atributosEncapsulados = true,

        substituivel = false,

        descricao = "Interface do pacote permite comunicção entre o movimentador dentro da classe Corredor" +
                    " com a classe controladora de Corredor 'CorredorManager' sem expor demais metodos.",
        utilizadores = { Corredor2.class, Movimentador.class },
        implementadores = { CorredorManager.class }
)
interface MsgToCorredorManager {

    void acaoFinalizada();

}
