package nl.ekholabs.escode.graphics;

import nl.ekholabs.escode.core.EsCodeParser;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class ParsedCanvas extends JComponent {

  private static final long serialVersionUID = 8660447244732117207L;

  private final BufferedImage bufferedImage;

  public ParsedCanvas(final BufferedImage bufferedImage) {
    this.bufferedImage = bufferedImage;
  }

  @Override
  public void paint(final Graphics graphics) {
    setOpaque(true);

    final EsCodeParser esCodeParser = new EsCodeParser();
    esCodeParser.drawGraphics(graphics, bufferedImage);
  }
}