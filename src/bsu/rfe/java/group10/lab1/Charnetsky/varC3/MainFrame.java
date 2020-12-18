package bsu.rfe.java.group10.lab1.Charnetsky.varC3;

import java.awt.image.BufferedImage;
import java.lang.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.StrictMath.cos;;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 400;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;

    private Double Mem1 = (double) 0;
    private Double Mem2 = (double) 0;
    private Double Mem3 = (double) 0;

    private JLabel imageLabel = new JLabel();

    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup radioButtonsForMemory = new ButtonGroup();

    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxMemoryType = Box.createHorizontalBox();

    private int formulaId = 1;
    private int memoryId = 1;

    public Double calculate1(Double x, Double y, Double z) {
        return (Math.pow(Math.log1p(Math.pow(1 + x, 2)) + cos(3.14 * z * z * z), Math.sin(y)) + Math.pow((Math.exp(x * x)) + cos(Math.exp(z)), 1 / x));
    }

    public Double calculate2(Double x, Double y, Double z) {
        return (Math.pow(cos(3.14 * x) + Math.log1p(Math.pow(1 + y, 2)), 0.25) * (Math.exp(z * z) + Math.pow(1 / x, 0.5) + cos(Math.exp(y))));
    }

    private String[] ImagePath = {"E:\\Java\\Exaple_lab_2\\img\\Formula_1.png", "E:\\Java\\Exaple_lab_2\\img\\Formula_2.png"};
    BufferedImage imageFunction;
    {
        try {
            imageFunction = ImageIO.read(new File(ImagePath[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
                try {
                    MainFrame.this.imageFunction = ImageIO.read(new File(ImagePath[formulaId - 1]));
                    imageLabel.setIcon(new ImageIcon(imageFunction));
                } catch (IOException ex) {
                    Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    private void addRadioButtonForMemory(String buttonName, final int memoryId){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.memoryId = memoryId;
            }
        });
        radioButtonsForMemory.add(button);
        hboxMemoryType.add(button);
    }


    public MainFrame() {

        super("Вычисление формулы");

        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();


        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);


        hboxMemoryType.add(Box.createHorizontalGlue());
        addRadioButtonForMemory("Переменная 1", 1);
        addRadioButtonForMemory("Переменная 2", 2);
        addRadioButtonForMemory("Переменная 3", 3);
        radioButtonsForMemory.setSelected(radioButtonsForMemory.getElements().nextElement().getModel(), true);
        hboxMemoryType.add(Box.createHorizontalGlue());
        hboxMemoryType.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));


        JTextField textFieldMem1 = new JTextField("0",15);
        textFieldMem1.setMaximumSize(textFieldMem1.getPreferredSize());
        JTextField textFieldMem2 = new JTextField("0",15);
        textFieldMem2.setMaximumSize(textFieldMem2.getPreferredSize());
        JTextField textFieldMem3 = new JTextField("0",15);
        textFieldMem3.setMaximumSize(textFieldMem3.getPreferredSize());


        Box hboxMemoryR = Box.createHorizontalBox();
        hboxMemoryR.add(Box.createHorizontalGlue());
        hboxMemoryR.add(textFieldMem1);
        hboxMemoryR.add(Box.createHorizontalStrut(10));
        hboxMemoryR.add(textFieldMem2);
        hboxMemoryR.add(Box.createHorizontalStrut(10));
        hboxMemoryR.add(textFieldMem3);
        hboxMemoryR.add(Box.createHorizontalGlue());


        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));


        imageLabel.setIcon(new ImageIcon(imageFunction));
        Box hboxImage = Box.createHorizontalBox();
        hboxImage.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        hboxImage.add(Box.createHorizontalGlue());
        hboxImage.add(imageLabel);
        hboxImage.add(Box.createHorizontalGlue());


        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());


        JLabel labelForResult = new JLabel("Результат");
        textFieldResult = new JTextField("", 15);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());


        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double Result;
                    if (formulaId == 1)
                        Result = calculate1(x, y, z);
                    else
                        Result = calculate2(x, y, z);
                    textFieldResult.setText(Result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("");
                textFieldY.setText("");
                textFieldZ.setText("");
                textFieldResult.setText("0");
            }
        });


        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(memoryId == 1){
                    Mem1 = (double) 0;
                    textFieldMem1.setText("0");
                }
                else if(memoryId == 2){
                    Mem2 = (double) 0;
                    textFieldMem2.setText("0");
                }
                else if(memoryId == 3){
                    textFieldMem3.setText("0");
                }
            }
        });


        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(memoryId == 1){
                    Mem1 = (double)Mem1 + Double.parseDouble(textFieldResult.getText());
                    textFieldMem1.setText(Mem1.toString());
                    textFieldResult.setText(Mem1.toString());
                }
                else if(memoryId == 2){
                    Mem2 = (double)Mem2 + Double.parseDouble(textFieldResult.getText());
                    textFieldMem2.setText(Mem2.toString());
                    textFieldResult.setText(Mem2.toString());
                }
                else if(memoryId == 3){
                    Mem3 = (double)Mem3 + Double.parseDouble(textFieldResult.getText());
                    textFieldMem3.setText(Mem3.toString());
                    textFieldResult.setText(Mem3.toString());
                }

            }
        });


        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMC);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonM);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));


        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxImage);
        contentBox.add(hboxMemoryType);
        contentBox.add(hboxMemoryR);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}