package test;

import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionListener;



public class TicTacToe implements ActionListener
{
    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel topPannel;
    private JLabel text;
    private JButton[] buttons;
    private int moveAlternator;
    private int filledSquares;
    
    public TicTacToe() {
        this.frame = new JFrame();
        this.buttonPanel = new JPanel();
        this.topPannel = new JPanel();
        this.text = new JLabel();
        this.buttons = new JButton[9];
        this.moveAlternator = (int)(Math.random() * 2.0) + 1;
        this.filledSquares = 0;
        this.GUIsetup();
    }
    
    public void GUIsetup() {
        this.frame.setVisible(true);
        this.frame.setSize(750, 750);
        this.frame.setDefaultCloseOperation(3);
        this.text.setBackground(new Color(50, 50, 50));
        this.text.setForeground(new Color(85, 97, 207));
        this.text.setText("Tic-Tac-Toe");
        this.text.setFont(new Font("Times New Roman", 1, 80));
        this.text.setOpaque(true);
        this.topPannel.setLayout(new BorderLayout());
        this.topPannel.setBounds(0, 0, 800, 100);
        this.topPannel.add(this.text);
        this.text.setHorizontalAlignment(0);
        this.frame.add(this.topPannel, "North");
        this.buttonPanel.setLayout(new GridLayout(3, 3));
        this.frame.add(this.buttonPanel);
        for (int x = 0; x < 9; ++x) {
            this.buttons[x] = new JButton();
            this.buttonPanel.add(this.buttons[x]);
            this.buttons[x].setFont(new Font("Times New Roman", 1, 105));
            this.buttons[x].addActionListener(this);
        }
        this.startGame();
    }
    
    public void startGame() {
        try {
            Thread.currentThread();
            Thread.sleep(3000L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.moveAlternator == 1) {
            this.text.setText("It's X's turn");
        }
        if (this.moveAlternator == 2) {
            this.text.setText("It's O's turn");
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        for (int i = 0; i < 9; ++i) {
            if (e.getSource() == this.buttons[i]) {
                if (this.moveAlternator == 1 && this.buttons[i].getText() == "") {
                    this.buttons[i].setText("X");
                    this.buttons[i].setForeground(new Color(255, 0, 0));
                    this.moveAlternator = 2;
                    this.text.setText("It's O's turn");
                    ++this.filledSquares;
                    if (this.filledSquares == 9 && !this.checkSquares()) {
                        this.tieCheck();
                    }
                    else {
                        this.checkSquares();
                    }
                }
                if (this.moveAlternator == 2 && this.buttons[i].getText() == "") {
                    this.buttons[i].setText("O");
                    this.buttons[i].setForeground(new Color(255, 225, 0));
                    this.moveAlternator = 1;
                    this.text.setText("It's X's turn");
                    ++this.filledSquares;
                    if (this.filledSquares == 9 && !this.checkSquares()) {
                        this.tieCheck();
                    }
                    else {
                        this.checkSquares();
                    }
                }
            }
        }
    }
    
    public boolean checkSquares() {
        for (int i = 0; i < 9; i += 3) {
            if (this.buttons[i].getText() == "O" && this.buttons[i + 1].getText() == "O" && this.buttons[i + 2].getText() == "O") {
                this.Winner("O");
                return true;
            }
        }
        for (int x = 0; x < 9; x += 3) {
            if (this.buttons[x].getText() == "X" && this.buttons[x + 1].getText() == "X" && this.buttons[x + 2].getText() == "X") {
                this.Winner("X");
                return true;
            }
        }
        for (int c = 0; c < 3; ++c) {
            if (this.buttons[c].getText() == "O" && this.buttons[c + 3].getText() == "O" && this.buttons[c + 6].getText() == "O") {
                this.Winner("O");
                return true;
            }
        }
        for (int k = 0; k < 3; ++k) {
            if (this.buttons[k].getText() == "X" && this.buttons[k + 3].getText() == "X" && this.buttons[k + 6].getText() == "X") {
                this.Winner("X");
                return true;
            }
        }
        if (this.buttons[0].getText() == "X" && this.buttons[4].getText() == "X" && this.buttons[8].getText() == "X") {
            this.Winner("X");
            return true;
        }
        if (this.buttons[0].getText() == "O" && this.buttons[4].getText() == "O" && this.buttons[8].getText() == "O") {
            this.Winner("O");
            return true;
        }
        if (this.buttons[2].getText() == "X" && this.buttons[4].getText() == "X" && this.buttons[6].getText() == "X") {
            this.Winner("X");
            return true;
        }
        if (this.buttons[2].getText() == "O" && this.buttons[4].getText() == "O" && this.buttons[6].getText() == "O") {
            this.Winner("O");
            return true;
        }
        return false;
    }
    
    
    public void Winner(final String winLetter) {
        for (int a = 0; a < 9; ++a) {
            this.buttons[a].setEnabled(false);
        }
        this.text.setText("\"" + winLetter + "\"" + " is the winner!");
    }
    
    public void tieCheck() {
        for (int a = 0; a < 9; ++a) {
            this.buttons[a].setEnabled(false);
        }
        this.text.setText("Tie!");
    }
}
