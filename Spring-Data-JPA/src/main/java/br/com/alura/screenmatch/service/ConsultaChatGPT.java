package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService("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");

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}
