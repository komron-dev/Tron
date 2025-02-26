package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LevelDialog extends JDialog {
    private JComboBox<String> combo;
    public boolean ok = false;

    public LevelDialog(Frame parent){
        super(parent,true);
        setTitle("Select Level");
        setLayout(new BorderLayout());
        JLabel lbl = new JLabel("Choose a Level:");
        lbl.setFont(new Font("Arial",Font.PLAIN,16));
        lbl.setHorizontalAlignment(JLabel.CENTER);
        add(lbl,BorderLayout.NORTH);

        JPanel center = new JPanel();
        String[] levels = new String[10];
        for(int i=1;i<=10;i++){
            levels[i-1] = "level"+i+".txt";
        }
        combo = new JComboBox<>(levels);
        center.add(combo);
        add(center,BorderLayout.CENTER);

        JButton selectBtn = new JButton("Select");
        selectBtn.setFont(new Font("Arial",Font.PLAIN,14));
        selectBtn.addActionListener((ActionEvent e)->{
            ok = true;
            dispose();
        });
        add(selectBtn,BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public String getSelectedLevel() {
        return (String)combo.getSelectedItem();
    }
}
