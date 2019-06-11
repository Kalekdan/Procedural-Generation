package main.java.com.pixolestudios.uiUtils;

import main.java.com.pixolestudios.exceptions.UninitializedMapException;
import main.java.com.pixolestudios.plogger.PLog;
import main.java.com.pixolestudios.procgen.ImageGen;
import main.java.com.pixolestudios.procgen.MapGrid;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgWindow extends JFrame {
    private static final String WINDOW_TITLE = "Image preview";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int MARGIN = 20;
    private static final int STD_HEIGHT = 25;

    private static final int pixelsPerGrid = 10;

    private ImageGen imgGen;
    private JLabel picLabel;

    public ImgWindow(){
        setupWindow();
        setupWindowContents();
        setLayout(null);
    }

    private void setupWindow() {
        PLog.debug("Creating image view window: " + WINDOW_TITLE);
        setTitle(WINDOW_TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupWindowContents() {
        picLabel = new JLabel();
    }

    protected void addImageToView(MapGrid mapToImg) throws IOException, UninitializedMapException {
        imgGen = new ImageGen(mapToImg, pixelsPerGrid);
        imgGen.GenerateImg();
        BufferedImage img = ImageIO.read(new File(ImageGen.DEFAULT_OUT_LOC));
        picLabel.setIcon(new ImageIcon(img));
        picLabel.setBounds(0, 0, mapToImg.getXSize() * pixelsPerGrid, mapToImg.getYSize() * pixelsPerGrid);
        getContentPane().setPreferredSize(new Dimension(mapToImg.getXSize() * pixelsPerGrid, mapToImg.getYSize() * pixelsPerGrid));
        add(picLabel);
        picLabel.setVisible(true);
        repaint();
        pack();
    }

}
