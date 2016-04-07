package nl.ekholabs.escode.graphics;

import nl.ekholabs.escode.core.EsCodeGenerator;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;

public class GeneratedCanvas extends JComponent {

  private static final long serialVersionUID = 8660447244732117207L;

  private final List<EsCodeColour> colours;

  public GeneratedCanvas(final List<EsCodeColour> colours) {
    this.colours = colours;
  }

  @Override
  public void paint(final Graphics graphics) {
    setOpaque(true);

    final EsCodeGenerator esCodeGenerator = new EsCodeGenerator();
    esCodeGenerator.drawGraphics(colours, graphics);
  }
}