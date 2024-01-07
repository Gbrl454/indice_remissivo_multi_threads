package br.gbrl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;

public class LeitorDeArquivo extends Thread {
    private final Path pathArquivo;
    String palavras;

    public LeitorDeArquivo(String pathArquivo) {
        this.pathArquivo = Paths.get(pathArquivo);
    }

    private String clearLine(String str) {
        String[] palavras = Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[,.!?;]", "").split("\\s+");
        String palavrasAux = "";

        for (String palavra : palavras)
            if (palavra.length() > 1 && Character.isLetter(palavra.charAt(0))) palavrasAux += palavra + ",";

        return palavrasAux;
    }

    @Override
    public void run() {
        lerArquivoEmPalavras();
    }

    private void lerArquivoEmPalavras() {
        palavras = "";
        try {
            for (String linha : Files.readAllLines(pathArquivo)) palavras += clearLine(linha).toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getPalavras() {
        return palavras.split(",");
    }
}
