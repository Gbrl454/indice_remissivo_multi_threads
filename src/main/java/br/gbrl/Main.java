package br.gbrl;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long tempoInicial = System.nanoTime();

        // Leitura de Arquivo Com as Palavras-Chave
        LeitorDeArquivo palavrasChave = new LeitorDeArquivo("src/main/resources/palavras-chave.txt");
        palavrasChave.start();

        // Leitura de Arquivo Com o Texto
        LeitorDeArquivo texto = new LeitorDeArquivo("src/main/resources/texto.txt");
        texto.start();

        palavrasChave.join();
        // TODO - Criar Molde de Índice

        texto.join();
        // TODO - Incrementar Índice


        System.out.println(palavrasChave.palavras);
        System.out.println(palavrasChave.getPalavras().length);

        System.out.println(texto.palavras);
        System.out.println(texto.getPalavras().length);

        System.out.println(System.nanoTime() - tempoInicial);
    }
}
