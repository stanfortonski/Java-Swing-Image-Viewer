import java.awt.*;
import javax.swing.*;
import viewer.*;

public class ImageViewer
{
  public static void main(String[] args)
  {
    try
    {
      String style = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
      UIManager.setLookAndFeel(style);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        JFrame frame = new ViewerFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
      }
    });
  }
}
