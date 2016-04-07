package nl.ekholabs.escode.action;

import nl.ekholabs.escode.web.EsCode;
import nl.ekholabs.escode.core.EsCodeParser;
import nl.ekholabs.escode.graphics.ParsedCanvas;


//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;


public class ParseEsCodeAction {

  private final File selectedFile;
  public ParseEsCodeAction(File selectedFile) {
    this.selectedFile = selectedFile;
  }

  public void parseFile() {
    
    try {
      final BufferedImage bufferedImage = ImageIO.read(new java.io.FileInputStream(selectedFile.getAbsolutePath()));

      final EsCodeParser esCodeParser = new EsCodeParser();

      final List<Integer> byteBuffer = esCodeParser.extractBytesFromImage(bufferedImage);
      final BufferedImage clone = esCodeParser.createImage(bufferedImage);
      esCodeParser.drawGraphics(clone.getGraphics(), bufferedImage);
      esCodeParser.persistImage(clone, selectedFile.getParent());
      esCodeParser.persistRawData(byteBuffer, selectedFile.getParent());
      
    } catch (final IOException e) {
      System.out.println("e.getMessage()");
    }
  }
}