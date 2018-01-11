/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAssignment3;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Abir Abbas
 */
public class guiAssignment3 {

    /**
     *
     * @param args command line arguments
     * @throws PortfolioException custom
     */
    public static void main(String[] args) throws PortfolioException {

        if (args.length >= 1) {
            guiMain mainGui = new guiMain(args[0]);

        } else {
            System.out.println("No file name was entered, program will exit, please enter the fileName when running the program!");
            System.exit(0);
        }
    }

}


