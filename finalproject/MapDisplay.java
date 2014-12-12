// MapDisplay.java
// Andrea Compton
// 12/12/14
// run with java -classpath ./ MapDisplay
// Displays users at Amazon's specified locations

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.util.Map;
import java.util.List;
import java.util.Collection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;

public class MapDisplay {

    public MapDisplay() throws MalformedURLException, IOException {
        initComponents();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MapDisplay();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initComponents() throws MalformedURLException, IOException {
        JFrame frame = new JFrame("Map Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Image background = 
        	ImageUtils.scaleImage(1200, 600, 
        						 ImageIO.read(new File("Aws_region_map.png")));
        final Dimension jpanelDimensions = 
        	new Dimension(new ImageIcon(background).getIconWidth(), 
        				  new ImageIcon(background).getIconHeight());

        final LogExamples logs = new LogExamples();
        final Map<String, Collection<String>> map = logs.initComponents();
        
        frame.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                setLayout(null);
                super.paintComponent(grphcs);
                grphcs.drawImage(background, 0, 0, this);
                int size;
                for (String key : map.keySet()) {
                    String[] users = map.get(key).toArray(new String[0]);
                    ListPanel panel = new ListPanel(users);
                    JButton btn = new JButton(
                                    new ButtonAction(String.valueOf(map.get(key).size()), 
                                                     KeyEvent.VK_A, panel));
                    btn.setBounds(getPositionX(key), getPositionY(key), 30, 30);
                    add(btn);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return jpanelDimensions;
            }
        });

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    // hardcoded X coordinate for each location
    private int getPositionX(String region){
    	if (region.equals("us-west-2"))
    		return 120;
    	if (region.equals("us-west-1"))
    		return 120;
    	if (region.equals("us-east-1"))
    		return 200;
    	if (region.equals("sa-east-1"))
    		return 270;
    	if (region.equals("eu-west-1"))
    		return 500;
    	if (region.equals("eu-central-1"))
    		return 550;
    	if (region.equals("ap-southeast-2"))
    		return 945;
    	if (region.equals("ap-southeast-1"))
    		return 765;
    	if (region.equals("ap-northeast-1"))
    		return 765;
    	return 0;
    }

    // hardcoded Y coordinate for each location
    private int getPositionY(String region){
    	if (region.equals("us-west-2"))
    		return 140;
    	if (region.equals("us-west-1"))
    		return 240;
    	if (region.equals("us-east-1"))
    		return 140;
    	if (region.equals("sa-east-1"))
    		return 440;
    	if (region.equals("eu-west-1"))
    		return 240;
    	if (region.equals("eu-central-1"))
    		return 240;
    	if (region.equals("ap-southeast-2"))
    		return 490;
    	if (region.equals("ap-southeast-1"))
    		return 265;
    	if (region.equals("ap-northeast-1"))
    		return 170;
    	return 0;
    }

    // opens new panel of users upon click
    private class ButtonAction extends AbstractAction {
        private ListPanel panel;
        public ButtonAction(String name, Integer mnemonic, ListPanel panel) {
            super(name);
            putValue(MNEMONIC_KEY, mnemonic);
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.display();
        }
   }
}

class ImageUtils {

    public static BufferedImage scaleImage(int width, int height, String filename) {
        BufferedImage bi;
        try {
            ImageIcon ii = new ImageIcon(filename);
            bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, 
                                            RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(ii.getImage(), 0, 0, width, height, null);
        } catch (Exception e) {
            return null;
        }
        return bi;
    }

    static Image scaleImage(int width, int height, BufferedImage filename) {
        BufferedImage bi;
        try {
            bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, 
                                            RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(filename, 0, 0, width, height, null);
        } catch (Exception e) {
            return null;
        }
        return bi;
    }
}




