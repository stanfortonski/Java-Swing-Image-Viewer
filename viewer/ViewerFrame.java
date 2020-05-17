package viewer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ViewerFrame extends JFrame
{
  private final String NAME = "Image Viewer";
  private JFileChooser fileChooser;
  private JMenuBar menuBar;
  private ImageComponent imageComponent;
  private AboutDialog dialog;
  
  public ViewerFrame()
  {
    setTitle(NAME);
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();

    int screenWidth = (int)(screenSize.width * 0.7);
    int screenHeight = (int)(screenSize.height * 0.7);
    setSize(screenWidth, screenHeight);

    ImageIcon imageIcon = new ImageIcon("icon.jpg");
    setIconImage(imageIcon.getImage());

    dialog = new AboutDialog(this);
    initFileChooser();
    initImageComponent();
    initMenuBar();
  }

  private void initFileChooser()
  {
    fileChooser = new JFileChooser(System.getProperty("user.home"));
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "gif", "png");
    fileChooser.setFileFilter(filter);
  }

  private void initImageComponent()
  {
    imageComponent = new ImageComponent();
    imageComponent.setSize(getWidth(), getHeight());
    imageComponent.setFocusable(true);
    JScrollPane scroll = new JScrollPane(imageComponent);
    add(scroll);
  }

  private void initMenuBar()
  {
    JMenu fileMenu = new JMenu("File");
    JMenuItem chooseItem = new JMenuItem("Choose file to open    ");
    chooseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
    chooseItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        int result = fileChooser.showOpenDialog(ViewerFrame.this);

        if (result == JFileChooser.APPROVE_OPTION)
        {
          String path = fileChooser.getSelectedFile().getPath();
          loadImage(path);

          int index = path.lastIndexOf("\\");
          String fileName = path.substring(index + 1);

          setTitle(NAME + " - " + fileName);
        }
      }
    });
    fileMenu.add(chooseItem);
    
    JMenu aboutMenu = new JMenu("About");
    JMenuItem aboutItem = new JMenuItem("About");
    aboutItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        dialog.setVisible(true);
      }
    });
    aboutMenu.add(aboutItem);

    menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    menuBar.add(fileMenu);
    menuBar.add(aboutMenu);
  }

  private void loadImage(String path)
  {
    imageComponent.loadImage(path, new Dimension(getWidth()-50, getHeight()-200));
    imageComponent.revalidate();
    imageComponent.repaint();
  }
}
