import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame {
    private JComboBox[] comboBoxes;

    Frame(Data data) {
        super(data.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setPreferredSize(new Dimension(1500, 750));
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(data.getAttributes().length + 2, 0, 10, 10));
        StringBuilder classNames = new StringBuilder("Классы: ");
        for (Class cl : data.getClasses()) {
            classNames.append(new StringBuilder(cl.getName()).append(", "));
        }
        JLabel label = new JLabel(classNames.substring(0, classNames.length() - 2));
        container.add(label);
        JLabel l = new JLabel();
        container.add(l);
        JLabel[] labels = new JLabel[data.getAttributes().length];
        comboBoxes = new JComboBox[data.getAttributes().length];
        for (int i = 0; i < comboBoxes.length; i++) {
            labels[i] = new JLabel(data.getAttributes()[i] + ": ");
            container.add(labels[i]);
            comboBoxes[i] = new JComboBox();
            comboBoxes[i].addItem("Нет");
            comboBoxes[i].addItem("Да");
            container.add(comboBoxes[i]);
        }
        JButton button = new JButton("Распознать");
        button.addActionListener((ActionEvent actionEvent) -> {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < data.getAttributes().length; i++) {
                    list.add(comboBoxes[i].getSelectedIndex());
                }
                Algorithm algorithm = new Algorithm(data.getClasses(), data.getAttributes().length);
                algorithm.train();
                String answer = algorithm.recognize(list);
                JOptionPane.showMessageDialog(Frame.this, "Ответ: " + answer);
            }
        );
        container.add(button);
        pack();
    }
}
