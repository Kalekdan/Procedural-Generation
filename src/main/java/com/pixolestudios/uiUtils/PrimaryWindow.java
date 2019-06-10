package main.java.com.pixolestudios.uiUtils;

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
import java.text.ParseException;

public class PrimaryWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    private static final int MARGIN = 20;
    private static final int STD_HEIGHT = 25;

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
        // Generate empty map object elements
        fld_mapGenXSize = new JTextField();
        fld_mapGenXSize.setBounds(0 + MARGIN, 0 + MARGIN, 50, STD_HEIGHT);
        fld_mapGenXSize.setName("Map X Size");
        fld_mapGenXSize.setToolTipText("Map x size");

        fld_mapGenYSize = new JTextField();
        fld_mapGenYSize.setBounds(fld_mapGenXSize.getBounds().x + fld_mapGenXSize.getWidth() + MARGIN, 0 + MARGIN, 50, STD_HEIGHT);
        fld_mapGenYSize.setName("Map Y Size");
        fld_mapGenYSize.setToolTipText("Map y size");

        add(fld_mapGenXSize);
        add(fld_mapGenYSize);

        btn_genMapObj = new JButton("Create empty map object");
        btn_genMapObj.setBounds(fld_mapGenYSize.getBounds().x + fld_mapGenYSize.getWidth() + MARGIN, 0 + MARGIN, 200, STD_HEIGHT);
        btn_genMapObj.addActionListener(this);

        add(btn_genMapObj);

        // Generate dry map contents elements
        SpinnerNumberModel heightSpnModel = new SpinnerNumberModel(255.0f, 1.0f, 255.0f, 1.0f);
        spn_mapMaxHeight = new JSpinner(heightSpnModel);
        spn_mapMaxHeight.setBounds(0 + MARGIN, fld_mapGenXSize.getBounds().y + fld_mapGenXSize.getHeight() + MARGIN, 50, STD_HEIGHT);

        add(spn_mapMaxHeight);

        btn_genDryMap = new JButton("Generate dry map");
        btn_genDryMap.setBounds(spn_mapMaxHeight.getBounds().x + spn_mapMaxHeight.getWidth() + MARGIN, fld_mapGenXSize.getBounds().y + fld_mapGenXSize.getHeight() + MARGIN, 200, STD_HEIGHT);
        btn_genDryMap.addActionListener(this);

        add(btn_genDryMap);

        // Flooding terrain elements
        SpinnerNumberModel floodSpnModel = new SpinnerNumberModel(255.0f, 1.0f, 255.0f, 1.0f);
        spn_floodWaterHeight = new JSpinner(floodSpnModel);
        spn_floodWaterHeight.setBounds(0 + MARGIN, spn_mapMaxHeight.getBounds().y + spn_mapMaxHeight.getHeight() + MARGIN, 50, STD_HEIGHT);

        add(spn_floodWaterHeight);

        btn_floodMap = new JButton("Flood map");
        btn_floodMap.setBounds(spn_mapMaxHeight.getBounds().x + spn_mapMaxHeight.getWidth() + MARGIN, spn_mapMaxHeight.getBounds().y + spn_mapMaxHeight.getHeight() + MARGIN, 200, STD_HEIGHT);
        btn_floodMap.addActionListener(this);

        add(btn_floodMap);

        // Noise removal elements
        fld_rmNoiseIterations = new JTextField();
        fld_rmNoiseIterations.setBounds(0 + MARGIN, spn_floodWaterHeight.getBounds().y + spn_floodWaterHeight.getHeight() + MARGIN, 50, STD_HEIGHT);
        fld_rmNoiseIterations.setToolTipText("Noise removal iterations");

        fld_rmNoiseThreshold = new JTextField();
        fld_rmNoiseThreshold.setBounds(fld_rmNoiseIterations.getBounds().x + fld_rmNoiseIterations.getWidth() + MARGIN, spn_floodWaterHeight.getBounds().y + spn_floodWaterHeight.getHeight() + MARGIN, 50, STD_HEIGHT);
        fld_rmNoiseThreshold.setToolTipText("Noise removal threshold");

        fld_rmNoiseSize = new JTextField();
        fld_rmNoiseSize.setBounds(fld_rmNoiseThreshold.getBounds().x + fld_rmNoiseThreshold.getWidth() + MARGIN, spn_floodWaterHeight.getBounds().y + spn_floodWaterHeight.getHeight() + MARGIN, 50, STD_HEIGHT);
        fld_rmNoiseSize.setToolTipText("Noise removal area size");

        add(fld_rmNoiseIterations);
        add(fld_rmNoiseThreshold);
        add(fld_rmNoiseSize);

        btn_rmNoise = new JButton("Remove noise");
        btn_rmNoise.setBounds(fld_rmNoiseSize.getBounds().x + fld_rmNoiseSize.getWidth() + MARGIN, spn_floodWaterHeight.getBounds().y + spn_floodWaterHeight.getHeight() + MARGIN, 200, STD_HEIGHT);
        btn_rmNoise.addActionListener(this);

        add(btn_rmNoise);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the event source was btn_genMapObj
        if (e.getSource() == btn_genMapObj) {
            doGenEmptyMapObjEvent();
        } else if (e.getSource() == btn_genDryMap) {
            doGenDryMapEvent();
        } else if (e.getSource() == btn_floodMap) {
            doFloodMapEvent();
        } else if (e.getSource() == btn_rmNoise) {
            doRemoveNoiseEvent();
        }
    }

    private void doGenEmptyMapObjEvent() {
        if (!isValidInt(fld_mapGenXSize.getText()) || !isValidInt(fld_mapGenYSize.getText())) {
            PLog.warning("Map x and y size must be a valid integer");
            JOptionPane.showMessageDialog(this, "Map x and y size must be valid whole numbers", "Invalid value", JOptionPane.WARNING_MESSAGE);
        } else {
            PLog.info("Creating map object");
            map = new MapGrid(Integer.parseInt(fld_mapGenXSize.getText()), Integer.parseInt(fld_mapGenYSize.getText()));
        }
    }

    private void doGenDryMapEvent() {
        try {
            spn_mapMaxHeight.commitEdit();
            PLog.info("Generating dry terrain");
            map.InitialGenerateDryMap(1, ((Double) spn_mapMaxHeight.getValue()).floatValue());
        } catch (ParseException e) {
            PLog.warning("Max height must be a valid integer in range 1-255");
            JOptionPane.showMessageDialog(this, "Max height must be a valid whole number in range 1-255", "Invalid value", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void doFloodMapEvent() {
        try {
            spn_floodWaterHeight.commitEdit();
            PLog.info("Flooding terrain");
            map.FloodMap(((Double) spn_floodWaterHeight.getValue()).floatValue());
        } catch (ParseException e) {
            PLog.warning("Flood height must be a valid integer in range 1-255");
            JOptionPane.showMessageDialog(this, "Flood height must be a valid whole number in range 1-255", "Invalid value", JOptionPane.WARNING_MESSAGE);        }
    }

    private void doRemoveNoiseEvent() {
        if (!isValidInt(fld_rmNoiseIterations.getText()) || !isValidInt(fld_rmNoiseThreshold.getText()) || !isValidInt(fld_rmNoiseSize.getText())) {
            PLog.warning("Noise iterations, noise threshold and noise size must be valid integers");
            JOptionPane.showMessageDialog(this, "Noise iterations, noise threshold and noise size must be valid whole numbers", "Invalid value", JOptionPane.WARNING_MESSAGE);
        } else {
            PLog.info("Removing terrain noise");
            map.RemoveTerrainNoise(Integer.parseInt(fld_rmNoiseIterations.getText()), Integer.parseInt(fld_rmNoiseThreshold.getText()), Integer.parseInt(fld_rmNoiseSize.getText()));
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
}
