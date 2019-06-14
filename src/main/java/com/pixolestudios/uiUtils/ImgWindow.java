package main.java.com.pixolestudios.uiUtils;

import main.java.com.pixolestudios.exceptions.NoPPGSelectedException;
import main.java.com.pixolestudios.exceptions.UninitializedMapException;
import main.java.com.pixolestudios.plogger.PLog;
import main.java.com.pixolestudios.procgen.ImageGen;
import main.java.com.pixolestudios.procgen.MapGrid;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgWindow extends JFrame {
    private static final String WINDOW_TITLE = "Image preview";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int MARGIN = 20;
    private static final int STD_HEIGHT = 25;

    private static final int targetImgWidth = 600;
    private static final int targetImgHeight = 600;
    private static final int startingPixelsPerGrid = 500;
    private int pixelsPerGrid;

    private ImageGen imgGen;
    private MapGrid map;
    private JLabel picLabel;

    private JMenuBar mnub_MenuBar;
    private JMenu mnu_fileMenu;
    private JMenuItem mnui_save;

    private JFileChooser fileChooser;
    private String savePath = "";

    public ImgWindow() {
        setupWindow();
        setupWindowContents();
        setLayout(null);
        setResizable(false);
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
        setupSave();
    }

    protected void addImageToView(MapGrid mapToImg) throws IOException, UninitializedMapException {
        pixelsPerGrid = startingPixelsPerGrid;
        map = mapToImg;
        AutoAdjustPixelsPerGrid();
        imgGen = new ImageGen(map, pixelsPerGrid);
        imgGen.GenerateImg();
        BufferedImage img = ImageIO.read(new File(ImageGen.DEFAULT_OUT_LOC));
        picLabel.setIcon(new ImageIcon(img));
        picLabel.setBounds(0, 0, map.getXSize() * pixelsPerGrid, map.getYSize() * pixelsPerGrid);
        getContentPane().setPreferredSize(new Dimension(map.getXSize() * pixelsPerGrid, map.getYSize() * pixelsPerGrid));
        add(picLabel);
        picLabel.setVisible(true);
        repaint();
        pack();
    }

    private void AutoAdjustPixelsPerGrid() {
        if (map.getXSize() > targetImgWidth || map.getYSize() > targetImgHeight) {
            pixelsPerGrid = 1;
            return;
        }
        if (map.getXSize() * pixelsPerGrid > targetImgWidth || map.getYSize() * pixelsPerGrid > targetImgHeight) {
            pixelsPerGrid--;
            AutoAdjustPixelsPerGrid();
        }
    }

    private void setupSave() {
        mnui_save = new JMenuItem("Save Image");
        mnui_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doSaveImageEvent();
                } catch (UninitializedMapException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        mnu_fileMenu = new JMenu("File");
        mnu_fileMenu.add(mnui_save);

        mnub_MenuBar = new JMenuBar();
        mnub_MenuBar.add(mnu_fileMenu);

        setJMenuBar(mnub_MenuBar);
    }

    private void doSaveImageEvent() throws UninitializedMapException, IOException {
        try {
            int exportPPG = pixelsPerGridSelection();
            fileChooser = new JFileChooser();
            int r = fileChooser.showSaveDialog(null);
            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                savePath = fileChooser.getSelectedFile().getAbsolutePath();
                PLog.info("Saving image at " + savePath);
                ImageGen imgSave = new ImageGen(map, savePath, exportPPG);
                imgSave.GenerateImg();
            } else {
                PLog.debug("Save image cancelled");
            }
        } catch (NoPPGSelectedException e) {
            PLog.warning(e.getMessage() + " - Cancelling save");
        }
    }

    private int pixelsPerGridSelection() throws NoPPGSelectedException {
        JPanel pnl_dialog = new JPanel();
        JLabel lbl_message = new JLabel("<html>How many pixels per grid point?<br><i>Hint: current preview is using " + pixelsPerGrid + "</i></html>");
        SpinnerNumberModel spn_model = new SpinnerNumberModel(pixelsPerGrid, 1, 200, 1);
        JSpinner spn_ppg = new JSpinner(spn_model);

        pnl_dialog.add(lbl_message);
        pnl_dialog.add(spn_ppg);
        int option = JOptionPane.showOptionDialog(null, pnl_dialog, "Pixel size selection", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            return (int) spn_ppg.getValue();
        } else {
            throw new NoPPGSelectedException();
        }
    }

}
