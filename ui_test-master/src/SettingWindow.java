import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 350;



    SettingWindow(GameWindow gameWindow){
        JButton btnStart = new JButton("Start new game");

        setLocationRelativeTo(gameWindow);
        setSize(WIDTH, HEIGHT);
        setTitle("Game Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        JLabel modeLabel = new JLabel("Выберите режим игры");
        add(modeLabel);
        ButtonGroup modeGroup = new ButtonGroup();
        JRadioButton vsComputerButton = new JRadioButton("Человек против компьютера");
        JRadioButton vsPlayerButton = new JRadioButton("Человек против человека");
        modeGroup.add(vsComputerButton);
        modeGroup.add(vsPlayerButton);
        vsComputerButton.setSelected(true);
        JPanel modePanel = new JPanel();
        modePanel.add(vsComputerButton);
        modePanel.add(vsPlayerButton);
        add(modePanel);

        JLabel fieldSizeLabel = new JLabel("Выберите размеры поля");
        add(fieldSizeLabel);

        JLabel fieldSizeSelectedLabel = new JLabel("Установленный размер поля: 3");
        JSlider fieldSizeSlider = new JSlider(3, 10, 3);
        JLabel winLengthLabel = new JLabel("Выберите длину для победы");
        JLabel winLengthSelectedLabel = new JLabel("Установленная длина: 3");
        JSlider winLengthSlider = new JSlider(3, 10, 3);
        add(fieldSizeSelectedLabel);
        add(fieldSizeSlider);
        add(winLengthLabel);
        add(winLengthSelectedLabel);
        add(winLengthSlider);
        pack();
        setLocationRelativeTo(null);
        fieldSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fieldSizeSelectedLabel.setText("Установленный размер поля: " + fieldSizeSlider.getValue());
            }
        });
        winLengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                winLengthSelectedLabel.setText("Установленная длина: " + winLengthSlider.getValue());
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gameWindow.startNewGame(vsPlayerButton.isSelected() ? 1 : 0, fieldSizeSlider.getValue(), fieldSizeSlider.getValue(), winLengthSlider.getValue());
            }
        });

        add(btnStart);
    }
}
