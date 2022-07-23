import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void cria(InputStream inputstream, String nomeArquivo)throws Exception {

        // Uma forma de receber stream: por um arquivo salvo no computador.
        // InputStream inputStream = new FileInputStream(new File("diretorio/arquivo.jpg"));
        // Outra forma: chamando uma url e pegando o stream.
        // InputStream inputstream = new URL("http://exemplodeurl.com.br.jpg").openStream();
        // Lendo o stream e Armazendando em uma imagem na memoria.
        BufferedImage imagemOriginal = ImageIO.read(inputstream);

        // Pegar a altura e a largura da imagem original
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        // Criando uma imagem nova e translucida em memoria
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        // Desenhando a imagem antiga na imagem nova
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));

    }
}
