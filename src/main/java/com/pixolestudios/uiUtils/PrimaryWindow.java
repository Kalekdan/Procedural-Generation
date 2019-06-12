package main.java.com.pixolestudios.uiUtils;

import main.java.com.pixolestudios.exceptions.UninitializedMapException;
import main.java.com.pixolestudios.plogger.PLog;
import main.java.com.pixolestudios.procgen.MapGenMain;
import main.java.com.pixolestudios.procgen.MapGrid;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class PrimaryWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int MARGIN = 20;
    private static final int STD_HEIGHT = 25;

    private ImgWindow imgWindow;

    private static MapGrid map;

    private JTextField fld_mapGenXSize;
    private JTextField fld_mapGenYSize;
    private JButton btn_genMapObj;

    private JSpinner spn_mapMaxHeight;
    private JButton btn_genDryMap;

    private JSpinner spn_floodWaterHeight;
    private JButton btn_floodMap;

    private JTextField fld_rmNoiseIterations;
    private JTextField fld_rmNoiseThreshold;
    private JTextField fld_rmNoiseSize;
    private JButton btn_rmNoise;

    private JTextField fld_smoothHeightSize;
    private JButton btn_smoothHeight;

    private JTextField fld_addBeachIterations;
    private JTextField fld_addBeachThreshold;
    private JTextField fld_addBeachSize;
    private JButton btn_addBeach;

    public PrimaryWindow() {
        setupWindow();
        setupWindowContents();
        setLayout(null);
    }

    private void setupWindow() {
        PLog.debug("Creating main window: " + MapGenMain.WINDOW_TITLE);
        setTitle(MapGenMain.WINDOW_TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupWindowContents() {
        setupMapGenContents();
        setupDryMapGenContents();
        setupFloodTerrainContents();
        setupNoiseRemovalContents();
        setupHeightSmoothingContents();
        setupAddBeachesContents();

        setupImgPreview();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // If the event source was btn_genMapObj etc
            if (e.getSource() == btn_genMapObj) {
                doGenEmptyMapObjEvent();
            } else if (e.getSource() == btn_genDryMap) {
                doGenDryMapEvent();
                imgWindow.addImageToView(map);
                imgWindow.setVisible(true);
            } else if (e.getSource() == btn_floodMap) {
                doFloodMapEvent();
                imgWindow.addImageToView(map);
                imgWindow.setVisible(true);
            } else if (e.getSource() == btn_rmNoise) {
                doRemoveNoiseEvent();
                imgWindow.addImageToView(map);
                imgWindow.setVisible(true);
            } else if (e.getSource() == btn_smoothHeight) {
                doSmoothHeightEvent();
                imgWindow.addImageToView(map);
                imgWindow.setVisible(true);
            } else if (e.getSource() == btn_addBeach) {
                doAddBeachEvent();
                imgWindow.addImageToView(map);
                imgWindow.setVisible(true);
            }
        } catch (UninitializedMapException ex) {
            handleUninitializedMap();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void handleUninitializedMap() {
        PLog.error("Map object must be created and initial map must be generated");
        JOptionPane.showMessageDialog(this, "Map object must be created and initial map must be generated", "Invalid value", JOptionPane.WARNING_MESSAGE);
    }

    private void doGenEmptyMapObjEvent() {
        if (!isValidInt(fld_mapGenXSize.getText()) || !isValidInt(fld_mapGenYSize.getText())) {
            PLog.warning("Map x and y size must be a valid integer");
            JOptionPane.showMessageDialog(this, "Map x and y size must be valid whole numbers", "Invalid value", JOptionPane.WARNING_MESSAGE);
        } else {
            PLog.info("Creating map object");
            map = new MapGrid(Integer.parseInt(fld_mapGenXSize.getText()), Integer.parseInt(fld_mapGenYSize.getText()));
            enableDryMapGenContents();
            enableMapFunctionsContents(false);
        }
    }

    private void doGenDryMapEvent() throws UninitializedMapException {
        try {
            spn_mapMaxHeight.commitEdit();
            PLog.info("Generating dry terrain");
            if (map == null) {
                throw new UninitializedMapException();
            }
            map.InitialGenerateDryMap(1, ((Double) spn_mapMaxHeight.getValue()).floatValue());
            enableMapFunctionsContents(true);
        } catch (ParseException e) {
            PLog.warning("Max height must be a valid integer in range 1-255");
            JOptionPane.showMessageDialog(this, "Max height must be a valid whole number in range 1-255", "Invalid value", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void doFloodMapEvent() throws UninitializedMapException {
        try {
            spn_floodWaterHeight.commitEdit();
            PLog.info("Flooding terrain");
            if (map == null) {
                throw new UninitializedMapException();
            }
            map.FloodMap(((Double) spn_floodWaterHeight.getValue()).floatValue());
        } catch (ParseException e) {
            PLog.warning("Flood height must be a valid integer in range 1-255");
            JOptionPane.showMessageDialog(this, "Flood height must be a valid whole number in range 1-255", "Invalid value", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void doRemoveNoiseEvent() throws UninitializedMapException {
        if (!isValidInt(fld_rmNoiseIterations.getText()) || !isValidInt(fld_rmNoiseThreshold.getText()) || !isValidInt(fld_rmNoiseSize.getText())) {
            PLog.warning("Noise iterations, noise threshold and noise size must be valid integers");
            JOptionPane.showMessageDialog(this, "Noise iterations, noise threshold and noise size must be valid whole numbers", "Invalid value", JOptionPane.WARNING_MESSAGE);
        } else {
            PLog.info("Removing terrain noise");
            if (map == null) {
                throw new UninitializedMapException();
            }
            map.RemoveTerrainNoise(Integer.parseInt(fld_rmNoiseIterations.getText()), Integer.parseInt(fld_rmNoiseThreshold.getText()), Integer.parseInt(fld_rmNoiseSize.getText()));
        }
    }

    private void doSmoothHeightEvent() throws UninitializedMapException {
        if (!isValidInt(fld_smoothHeightSize.getText())) {
            PLog.warning("Smooth height area size must be a valid integer");
            JOptionPane.showMessageDialog(this, "Smooth height area must be a valid whole number", "Invalid value", JOptionPane.WARNING_MESSAGE);
        } else {
            PLog.info("Smooothing heights");
            if (map == null) {
                throw new UninitializedMapException();
            }
            map.BasicSmoothHeightMap(Integer.parseInt(fld_smoothHeightSize.getText()));
        }
    }

    private void doAddBeachEvent() throws UninitializedMapException {
        if (!isValidInt(fld_addBeachIterations.getText()) || !isValidInt(fld_addBeachThreshold.getText()) || !isValidInt(fld_addBeachSize.getText())) {
            PLog.warning("Beach addition iterations, threshold and area size must be valid integers");
            JOptionPane.showMessageDialog(this, "Beach addition iterations, threshold and area size must be valid whole numbers", "Invalid value", JOptionPane.WARNING_MESSAGE);
        } else {
            PLog.info("Adding beaches");
            if (map == null) {
                throw new UninitializedMapException();
            }
            map.AddBeaches(Integer.parseInt(fld_addBeachIterations.getText()), Integer.parseInt(fld_addBeachThreshold.getText()), Integer.parseInt(fld_addBeachSize.getText()));
        }
    }

    private boolean isValidInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void setupMapGenContents() {
        // Generate empty map object elements
        fld_mapGenXSize = new JTextField();
        fld_mapGenXSize.setBounds(0 + MARGIN, 0 + MARGIN, 50, STD_HEIGHT);
        fld_mapGenXSize.setName("Map X Size");
        fld_mapGenXSize.setToolTipText("Map x size");
        fld_mapGenXSize.setText("65");

        fld_mapGenYSize = new JTextField();
        fld_mapGenYSize.setBounds(fld_mapGenXSize.getBounds().x + fld_mapGenXSize.getWidth() + MARGIN, 0 + MARGIN, 50, STD_HEIGHT);
        fld_mapGenYSize.setName("Map Y Size");
        fld_mapGenYSize.setToolTipText("Map y size");
        fld_mapGenYSize.setText("50");

        add(fld_mapGenXSize);
        add(fld_mapGenYSize);

        btn_genMapObj = new JButton("Create empty map object");
        btn_genMapObj.setBounds(fld_mapGenYSize.getBounds().x + fld_mapGenYSize.getWidth() + MARGIN, 0 + MARGIN, 200, STD_HEIGHT);
        btn_genMapObj.addActionListener(this);

        add(btn_genMapObj);
    }

    private void setupDryMapGenContents() {
        // Generate dry map contents elements
        SpinnerNumberModel heightSpnModel = new SpinnerNumberModel(255.0f, 1.0f, 255.0f, 1.0f);
        spn_mapMaxHeight = new JSpinner(heightSpnModel);
        spn_mapMaxHeight.setBounds(0 + MARGIN, fld_mapGenXSize.getBounds().y + fld_mapGenXSize.getHeight() + MARGIN, 50, STD_HEIGHT);
        spn_mapMaxHeight.setEnabled(false);

        add(spn_mapMaxHeight);

        btn_genDryMap = new JButton("Generate dry map");
        btn_genDryMap.setBounds(spn_mapMaxHeight.getBounds().x + spn_mapMaxHeight.getWidth() + MARGIN, fld_mapGenXSize.getBounds().y + fld_mapGenXSize.getHeight() + MARGIN, 200, STD_HEIGHT);
        btn_genDryMap.addActionListener(this);
        btn_genDryMap.setEnabled(false);

        add(btn_genDryMap);
    }

    private void setupFloodTerrainContents() {
        // Flooding terrain elements
        SpinnerNumberModel floodSpnModel = new SpinnerNumberModel(80.0f, 1.0f, 255.0f, 1.0f);
        spn_floodWaterHeight = new JSpinner(floodSpnModel);
        spn_floodWaterHeight.setBounds(0 + MARGIN, spn_mapMaxHeight.getBounds().y + spn_mapMaxHeight.getHeight() + MARGIN, 50, STD_HEIGHT);
        spn_floodWaterHeight.setEnabled(false);

        add(spn_floodWaterHeight);

        btn_floodMap = new JButton("Flood map");
        btn_floodMap.setBounds(spn_mapMaxHeight.getBounds().x + spn_mapMaxHeight.getWidth() + MARGIN, spn_mapMaxHeight.getBounds().y + spn_mapMaxHeight.getHeight() + MARGIN, 200, STD_HEIGHT);
        btn_floodMap.addActionListener(this);
        btn_floodMap.setEnabled(false);

        add(btn_floodMap);
    }

    private void setupNoiseRemovalContents() {
        // Noise removal elements
        fld_rmNoiseIterations = new JTextField();
        fld_rmNoiseIterations.setBounds(0 + MARGIN, spn_floodWaterHeight.getBounds().y + spn_floodWaterHeight.getHeight() + MARGIN, 50, STD_HEIGHT);
        fld_rmNoiseIterations.setToolTipText("Noise removal iterations");
        fld_rmNoiseIterations.setEnabled(false);
        fld_rmNoiseIterations.setText("2");

        fld_rmNoiseThreshold = new JTextField();
        fld_rmNoiseThreshold.setBounds(fld_rmNoiseIterations.getBounds().x + fld_rmNoiseIterations.getWidth() + MARGIN, spn_floodWaterHeight.getBounds().y + spn_floodWaterHeight.getHeight() + MARGIN, 50, STD_HEIGHT);
        fld_rmNoiseThreshold.setToolTipText("Noise removal threshold");
        fld_rmNoiseThreshold.setEnabled(false);
        fld_rmNoiseThreshold.setText("17");

        fld_rmNoiseSize = new JTextField();
        fld_rmNoiseSize.setBounds(fld_rmNoiseThreshold.getBounds().x + fld_rmNoiseThreshold.getWidth() + MARGIN, spn_floodWaterHeight.getBounds().y + spn_floodWaterHeight.getHeight() + MARGIN, 50, STD_HEIGHT);
        fld_rmNoiseSize.setToolTipText("Noise removal area size");
        fld_rmNoiseSize.setEnabled(false);
        fld_rmNoiseSize.setText("2");

        add(fld_rmNoiseIterations);
        add(fld_rmNoiseThreshold);
        add(fld_rmNoiseSize);

        btn_rmNoise = new JButton("Remove noise");
        btn_rmNoise.setBounds(fld_rmNoiseSize.getBounds().x + fld_rmNoiseSize.getWidth() + MARGIN, spn_floodWaterHeight.getBounds().y + spn_floodWaterHeight.getHeight() + MARGIN, 200, STD_HEIGHT);
        btn_rmNoise.addActionListener(this);
        btn_rmNoise.setEnabled(false);

        add(btn_rmNoise);
    }

    private void setupHeightSmoothingContents() {
        // Height smoothing elements
        fld_smoothHeightSize = new JTextField();
        fld_smoothHeightSize.setBounds(0 + MARGIN, fld_rmNoiseIterations.getBounds().y + fld_rmNoiseIterations.getHeight() + MARGIN, 50, STD_HEIGHT);
        fld_smoothHeightSize.setToolTipText("Height smoothing area size");
        fld_smoothHeightSize.setEnabled(false);
        fld_rmNoiseSize.setText("1");

        add(fld_smoothHeightSize);

        btn_smoothHeight = new JButton("Smooth heights");
        btn_smoothHeight.setBounds(fld_smoothHeightSize.getBounds().x + fld_smoothHeightSize.getWidth() + MARGIN, fld_rmNoiseIterations.getBounds().y + fld_rmNoiseIterations.getHeight() + MARGIN, 200, STD_HEIGHT);
        btn_smoothHeight.addActionListener(this);
        btn_smoothHeight.setEnabled(false);

        add(btn_smoothHeight);
    }

    private void setupAddBeachesContents() {
        // Beach adding elements
        fld_addBeachIterations = new JTextField();
        fld_addBeachIterations.setBounds(0 + MARGIN, fld_smoothHeightSize.getBounds().y + fld_smoothHeightSize.getHeight() + MARGIN, 50, STD_HEIGHT);
        fld_addBeachIterations.setToolTipText("Beach addition iterations");
        fld_addBeachIterations.setEnabled(false);
        fld_addBeachIterations.setText("1");

        fld_addBeachThreshold = new JTextField();
        fld_addBeachThreshold.setBounds(fld_addBeachIterations.getBounds().x + fld_addBeachIterations.getWidth() + MARGIN, fld_smoothHeightSize.getBounds().y + fld_smoothHeightSize.getHeight() + MARGIN, 50, STD_HEIGHT);
        fld_addBeachThreshold.setToolTipText("Beach addition threshold");
        fld_addBeachThreshold.setEnabled(false);
        fld_addBeachThreshold.setText("8");

        fld_addBeachSize = new JTextField();
        fld_addBeachSize.setBounds(fld_addBeachThreshold.getBounds().x + fld_addBeachThreshold.getWidth() + MARGIN, fld_smoothHeightSize.getBounds().y + fld_smoothHeightSize.getHeight() + MARGIN, 50, STD_HEIGHT);
        fld_addBeachSize.setToolTipText("Beach addition area size");
        fld_addBeachSize.setEnabled(false);
        fld_addBeachSize.setText("2");

        add(fld_addBeachIterations);
        add(fld_addBeachThreshold);
        add(fld_addBeachSize);

        btn_addBeach = new JButton("Add beaches");
        btn_addBeach.setBounds(fld_addBeachSize.getBounds().x + fld_addBeachSize.getWidth() + MARGIN, fld_smoothHeightSize.getBounds().y + fld_smoothHeightSize.getHeight() + MARGIN, 200, STD_HEIGHT);
        btn_addBeach.addActionListener(this);
        btn_addBeach.setEnabled(false);

        add(btn_addBeach);
    }

    private void enableDryMapGenContents() {
        spn_mapMaxHeight.setEnabled(true);
        btn_genDryMap.setEnabled(true);
    }

    private void enableMapFunctionsContents(boolean enabled) {
        enableFloodTerrainContents(enabled);
        enableNoiseRemovalContents(enabled);
        enableHeightSmoothingContents(enabled);
        enableAddBeachesContents(enabled);
    }

    private void enableFloodTerrainContents(boolean enabled) {
        spn_floodWaterHeight.setEnabled(enabled);
        btn_floodMap.setEnabled(enabled);
    }

    private void enableNoiseRemovalContents(boolean enabled) {
        fld_rmNoiseIterations.setEnabled(enabled);
        fld_rmNoiseThreshold.setEnabled(enabled);
        fld_rmNoiseSize.setEnabled(enabled);
        btn_rmNoise.setEnabled(enabled);
    }

    private void enableHeightSmoothingContents(boolean enabled) {
        fld_smoothHeightSize.setEnabled(enabled);
        btn_smoothHeight.setEnabled(enabled);
    }

    private void enableAddBeachesContents(boolean enabled) {
        fld_addBeachIterations.setEnabled(enabled);
        fld_addBeachThreshold.setEnabled(enabled);
        fld_addBeachSize.setEnabled(enabled);
        btn_addBeach.setEnabled(enabled);
    }

    private void setupImgPreview() {
        imgWindow = new ImgWindow();
    }
}
