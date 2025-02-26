package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class SetupDialog extends JDialog {
    public String p1Name,p2Name;
    public Color p1Color,p2Color;
    public boolean ok=false;

    public SetupDialog(Frame parent){
        super(parent,true);
        setTitle("Player Setup");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(5,5,5,5);
        gbc.gridx=0;gbc.gridy=0;

        add(new JLabel("Player 1 Name:"),gbc);
        gbc.gridx=1;
        JTextField p1Field=new JTextField("Player1",10);
        add(p1Field,gbc);

        gbc.gridy=1;gbc.gridx=0;
        add(new JLabel("Player 2 Name:"),gbc);
        gbc.gridx=1;
        JTextField p2Field=new JTextField("Player2",10);
        add(p2Field,gbc);

        gbc.gridy=2;gbc.gridx=0;
        JButton c1=new JButton("Choose P1 Color");
        c1.addActionListener((ActionEvent e)->{
            p1Color=JColorChooser.showDialog(SetupDialog.this,"P1 Color",Color.CYAN);
        });
        add(c1,gbc);

        gbc.gridx=1;
        JButton c2=new JButton("Choose P2 Color");
        c2.addActionListener((ActionEvent e)->{
            p2Color=JColorChooser.showDialog(SetupDialog.this,"P2 Color",Color.RED);
        });
        add(c2,gbc);

        gbc.gridy=3;gbc.gridx=0;gbc.gridwidth=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        JButton start=new JButton("Start");
        start.addActionListener((ActionEvent e)->{
            p1Name=p1Field.getText();
            p2Name=p2Field.getText();
            if(p1Color==null)p1Color=Color.CYAN;
            if(p2Color==null)p2Color=Color.RED;
            ok=true;dispose();
        });
        add(start,gbc);

        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
