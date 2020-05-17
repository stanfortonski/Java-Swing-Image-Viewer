package viewer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ImageComponent extends JPanel
{
  private final double MAX_ZOOM = 10;
  private final double MIN_ZOOM = 0.2;
  private Dimension size = new Dimension(600, 400);
  private double scale;
  private double posX;
  private double posY;
  private Image img;

  public ImageComponent()
  {
    addKeyListener(new ScaleEvent());
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    setBackground(Color.BLACK);

    if (isImageLoaded())
    {
      MediaTracker mt = new MediaTracker(this);
      mt.addImage(img, 0);
      try
      {
        mt.waitForAll();
      }
      catch (InterruptedException e){;}

      calcImage();
      g.drawImage(img, (int)posX, (int)posY, size.width, size.height, this);
    }
  }

  public void calcImage()
  {
    double imgWidth = img.getWidth(this) * scale;
    double imgHeight = img.getHeight(this) * scale;

    posX = getWidth()/2 - imgWidth/2;
    posY = getHeight()/2 - imgHeight/2;
    size = new Dimension((int)imgWidth, (int)imgHeight);
  }

  public void loadImage(String path, Dimension size)
  {
    setSize(size);
    img = new ImageIcon(path).getImage();

    int imageWidth = img.getWidth(this);
    if (imageWidth > getWidth())
      scale = (double)getWidth()/(double)imageWidth;
    else scale = 1.0;
  }

  private void scaleImage(double newScale)
  {
    scale = newScale;
    revalidate();
    repaint();
  }

  public boolean isImageLoaded()
  {
    return img != null;
  }

  public Dimension getPreferredSize()
  {
    return size;
  }

  private class ScaleEvent extends KeyAdapter
  {
    public void keyPressed(KeyEvent e)
    {
      if (isImageLoaded() && e.isControlDown())
      {
        if (e.getKeyCode() == KeyEvent.VK_EQUALS)
        {
          if (scale <= MAX_ZOOM)
            scaleImage(scale + 0.1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_MINUS)
        {
          if (scale >= MIN_ZOOM)
            scaleImage(scale - 0.1);
        }
      }
    }
  }
}
