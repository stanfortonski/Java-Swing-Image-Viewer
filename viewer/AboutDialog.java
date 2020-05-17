package viewer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AboutDialog extends JDialog
{
    public AboutDialog(JFrame owner)
    {
        super(owner, "About", true);
        Rectangle r = owner.getBounds();
        double width = r.width/2;
        double height = r.height/2;
        
        String htmlContent = "<html><h1>About</h1><hr><h3>Image Viewer made by Stan Fortonski 2019 - 2020</h3><h3><a href=\"https://github.com/stanfortonski/Java-Swing-Image-Viewer\">https://github.com/stanfortonski/Java-Swing-Image-Viewer</a></h3></html>";
        JLabel label = new JLabel(htmlContent);
        JButton button = new JButton("OK");
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(button, gbc);

        add(panel, BorderLayout.CENTER);
        setSize((int)width, (int)height);
        setResizable(false);
        setVisible(false);
    }
}