/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAssignment3;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
public class guiMain extends JFrame {

    final private Portfolio startPortfolio;

    //MainMenu
    final private JMenuBar menuBar = new JMenuBar();
    final private JMenu menuList = new JMenu("Commands");
    final private JMenuItem buyItem = new JMenuItem("Buy");
    final private JMenuItem sellItem = new JMenuItem("Sell");
    final private JMenuItem updateItem = new JMenuItem("Update");
    final private JMenuItem gainItem = new JMenuItem("getGain");
    final private JMenuItem searchItem = new JMenuItem("Search");
    final private JMenuItem quitItem = new JMenuItem("Quit");
    final private JTextArea mainMessage = new JTextArea();

    //Final - Very first panel that will contain everything
    final private JPanel finalPanel = new JPanel();

    //All Sub Panels
    final private JPanel mainPanel = new JPanel(new GridLayout());
    final private JPanel buyPanel = new JPanel(new GridLayout(2, 1));
    final private JPanel sellPanel = new JPanel(new GridLayout(2, 1));
    final private JPanel updatePanel = new JPanel(new GridLayout(2, 1));
    final private JPanel gainPanel = new JPanel(new GridLayout(2, 1));
    final private JPanel searchPanel = new JPanel(new GridLayout(2, 1));

    /* BUY PANEL VARIABLES */
    final private String[] typeInvestment = {"stocks", "mutual funds"};
    //Labels
    final private JComboBox typeDropdown = new JComboBox(typeInvestment);
    final private JLabel typeLabel = new JLabel("Type");
    final private JLabel symbolLabel = new JLabel("Symbol");
    final private JLabel nameLabel = new JLabel("Name");
    final private JLabel quantityLabel = new JLabel("Quantity");
    final private JLabel priceLabel = new JLabel("Price");
    //Buttons
    final private JButton resetButton = new JButton("Reset");
    final private JButton buyButton = new JButton("Buy");
    //TextArea
    final private JTextArea buyText = new JTextArea();
    //TextBoxes
    final private JTextField symbolTextbox = new JTextField();
    final private JTextField nameTextbox = new JTextField();
    final private JTextField quantityTextbox = new JTextField();
    final private JTextField priceBox = new JTextField();
    //Buy subpanels
    final private JPanel splitter = new JPanel(new GridLayout(1, 2));
    final private JPanel panel1Buy = new JPanel(new GridLayout(5, 2));
    final private JPanel panel2Buy = new JPanel(new GridLayout(2, 1, 50, 50));
    final private JPanel panel3Buy = new JPanel(new GridLayout());
    //scroller
    final private JScrollPane scrollBar = new JScrollPane(buyText);
    /* END OF BUY PANEL VARIABLES */

 /* SELL PANEL VARIABLES */
    final private JLabel symbolLabelSell = new JLabel("Symbol");
    final private JLabel quantityLabelSell = new JLabel("Quantity");
    final private JLabel priceLabelSell = new JLabel("Price");
    //Buttons
    final private JButton resetButtonSell = new JButton("Reset");
    final private JButton sellButton = new JButton("Sell");
    //TextArea
    final private JTextArea sellText = new JTextArea();
    //TextBoxes
    final private JTextField symbolTextboxSell = new JTextField();
    final private JTextField quantityTextboxSell = new JTextField();
    final private JTextField priceTextboxSell = new JTextField();
    //Buy subpanels
    final private JPanel splitterSell = new JPanel(new GridLayout(1, 2));
    final private JPanel panel1Sell = new JPanel(new GridLayout(3, 2, 0, 30));
    final private JPanel panel2Sell = new JPanel(new GridLayout(2, 1, 50, 50));
    final private JPanel panel3Sell = new JPanel(new GridLayout());
    //scroller
    final private JScrollPane scrollBarSell = new JScrollPane(sellText);
    /* END OF SELL PANEL VARIABLES */

 /* UPDATE PANEL VARIABLES */
    //Labels
    final private JLabel symbolLabelUpdate = new JLabel("Symbol");
    final private JLabel nameLabelUpdate = new JLabel("Name");
    final private JLabel priceLabelUpdate = new JLabel("Price");
    //Buttons
    final private JButton saveButton = new JButton("Save");
    final private JButton prevButton = new JButton("Prev");
    final private JButton nextButton = new JButton("Next");
    //TextArea
    final private JTextArea updateText = new JTextArea();
    //TextBoxes
    final private JTextField symbolTextboxUpdate = new JTextField();
    final private JTextField nameTextboxUpdate = new JTextField();
    final private JTextField priceTextboxUpdate = new JTextField();
    //Buy subpanels
    final private JPanel splitterUpdate = new JPanel(new GridLayout(1, 2));
    final private JPanel panel1Update = new JPanel(new GridLayout(3, 2, 0, 30));
    final private JPanel panel2Update = new JPanel(new GridLayout(3, 1, 40, 40));
    final private JPanel panel3Update = new JPanel(new GridLayout());
    //scroller
    final private JScrollPane scrollBarUpdate = new JScrollPane(updateText);
    /* END OF UPDATE PANEL VARIABLES */

 /* GAIN PANEL VARIABLES */
    //Labels
    final private JLabel gainLabel = new JLabel("Total gain : ");
    //Textbox
    final private JTextField gainTextbox = new JTextField();
    //TextArea
    final private JTextArea gainText = new JTextArea();
    //scroller
    final private JScrollPane scrollBarGain = new JScrollPane(gainText);
    //subpanels
    final private JPanel panel1Gain = new JPanel(new GridLayout());
    final private JPanel panel2Gain = new JPanel(new GridLayout());
    /* END OF GAIN PANEL VARIABLES */

 /* SEARCH PANEL VARIABLES */
    //Labels
    final private JLabel symbolLabelSearch = new JLabel("Symbol");
    final private JLabel nameLabelSearch = new JLabel("Name Keywords");
    final private JLabel lowPriceLabel = new JLabel("Low price");
    final private JLabel highPriceLabel = new JLabel("High price");
    //Buttons
    final private JButton resetButtonSearch = new JButton("Reset");
    final private JButton searchButton = new JButton("Search");
    //TextArea
    final private JTextArea searchText = new JTextArea();
    //TextBoxes
    final private JTextField symbolTextboxSearch = new JTextField();
    final private JTextField nameTextboxSearch = new JTextField();
    final private JTextField lowPriceTextbox = new JTextField();
    final private JTextField highPriceTextbox = new JTextField();
    //Buy subpanels
    final private JPanel splitterSearch = new JPanel(new GridLayout(1, 2));
    final private JPanel panel1Search = new JPanel(new GridLayout(4, 2, 0, 20));
    final private JPanel panel2Search = new JPanel(new GridLayout(2, 1, 50, 50));
    final private JPanel panel3Search = new JPanel(new GridLayout());
    //scroller
    final private JScrollPane scrollBarSearch = new JScrollPane(searchText);
    /* END OF SEARCH PANEL VARIABLES */

    private int index = 0;

    /**
     * guiMain is the constructor for the GUI frame and it calls all needed
     * functions to create a working GUI
     *
     * @param fileName : to get fileName from user from args which is passed
     * through from the actual main function
     * 
     */
    public guiMain(String fileName) throws PortfolioException {

        startPortfolio = new Portfolio(fileName);

        CardLayout c1 = new CardLayout();
        finalPanel.setLayout(c1);

        //creates Menu items
        menuInterface();
        //creates the list of interfaces, Buy, sell, gain, etc
        createList();
        //adds events for each menu choice
        addActionListener(c1);

        this.add(finalPanel);
        this.setTitle("Investment Portfolio");
        this.setSize(800, 600);
        this.setVisible(true);
        this.setMinimumSize(new Dimension(800,600));
        this.setLocationRelativeTo(null);
        
        buyText.setEditable(false);
        sellText.setEditable(false);
        searchText.setEditable(false);
        updateText.setEditable(false);
        gainText.setEditable(false);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                startPortfolio.writeFile();
                System.exit(0);
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * menuInterface creates the mainMenu, and also intializes all of the menu
     * options into the commands menu
     *
     */
    public void menuInterface() {
        //Menu bar
        menuList.add(buyItem);
        menuList.add(sellItem);
        menuList.add(updateItem);
        menuList.add(gainItem);
        menuList.add(searchItem);
        menuList.add(quitItem);
        menuBar.add(menuList);

        //main message panel
        mainMessage.setWrapStyleWord(true);
        mainMessage.setLineWrap(true);
        mainMessage.setFont(mainMessage.getFont().deriveFont(30f));
        this.setJMenuBar(menuBar);
        mainPanel.add(mainMessage);
        mainMessage.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainMessage.setText("\n\nWelcome to Investment Portfolio\n\n\nChoose a command from the \"Commads\" menu to buy or sell an investment, update prices for all investments, get gain for the portfolio, search for relevant investments, or quit the program.");
        mainMessage.setEditable(false);
    }

    /**
     * createList creates the panels into the finalPanel which uses cardLayout
     * to output certain panels at the users discretion
     */
    public void createList() {
        //Adding subpanels to main (final) panel
        finalPanel.add(mainPanel, "Main");
        finalPanel.add(buyPanel, "Buy");
        finalPanel.add(sellPanel, "Sell");
        finalPanel.add(updatePanel, "Update");
        finalPanel.add(gainPanel, "Gain");
        finalPanel.add(searchPanel, "Search");
    }

    /**
     * creates the required Interface for buy
     */
    public void buyInterface() {
        //buyPanel
        //Labels
        panel1Buy.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Buying  an Investment : "), new EmptyBorder(5, 5, 5, 5)));
        buyPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        panel1Buy.add(typeLabel);
        panel1Buy.add(typeDropdown);
        panel1Buy.add(symbolLabel);
        panel1Buy.add(symbolTextbox);
        panel1Buy.add(nameLabel);
        panel1Buy.add(nameTextbox);
        panel1Buy.add(quantityLabel);
        panel1Buy.add(quantityTextbox);
        panel1Buy.add(priceLabel);
        panel1Buy.add(priceBox);
        //Panel2
        panel2Buy.add(resetButton);
        panel2Buy.add(buyButton);

        panel2Buy.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Options : "), new EmptyBorder(80, 50, 50, 50)));

        typeDropdown.setFont(mainMessage.getFont().deriveFont(15f));
        symbolTextbox.setFont(mainMessage.getFont().deriveFont(15f));
        nameTextbox.setFont(mainMessage.getFont().deriveFont(15f));
        quantityTextbox.setFont(mainMessage.getFont().deriveFont(15f));
        priceBox.setFont(mainMessage.getFont().deriveFont(15f));

        typeLabel.setFont(mainMessage.getFont().deriveFont(15f));
        symbolLabel.setFont(mainMessage.getFont().deriveFont(15f));
        nameLabel.setFont(mainMessage.getFont().deriveFont(15f));
        quantityLabel.setFont(mainMessage.getFont().deriveFont(15f));
        priceLabel.setFont(mainMessage.getFont().deriveFont(15f));

        buyText.setEditable(false);

        //Panel3
        panel3Buy.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Messages : "), new EmptyBorder(5, 5, 5, 5)));
        panel3Buy.add(scrollBar);
        buyText.setWrapStyleWord(true);
        buyText.setLineWrap(true);
        buyText.setFont(mainMessage.getFont().deriveFont(20f));
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        splitter.add(panel1Buy);
        splitter.add(panel2Buy);
        buyPanel.add(splitter);
        buyPanel.add(panel3Buy);

        symbolTextbox.setText("");
        nameTextbox.setText("");
        priceBox.setText("");
        quantityTextbox.setText("");
    }

    /**
     * creates required interface for sell
     */
    public void sellInterface() {
        //buyPanel
        //Labels
        panel1Sell.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Selling  an Investment : "), (new EmptyBorder(30, 5, 30, 5))));
        sellPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        panel1Sell.add(symbolLabelSell);
        panel1Sell.add(symbolTextboxSell);
        panel1Sell.add(quantityLabelSell);
        panel1Sell.add(quantityTextboxSell);
        panel1Sell.add(priceLabelSell);
        panel1Sell.add(priceTextboxSell);
        //Panel2
        panel2Sell.add(resetButtonSell);
        panel2Sell.add(sellButton);
        panel2Sell.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Options : "), new EmptyBorder(80, 50, 50, 50)));

        //Panel3
        panel3Sell.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Messages : "), (new EmptyBorder(5, 5, 5, 5))));
        panel3Sell.add(scrollBarSell);
        sellText.setWrapStyleWord(true);
        sellText.setLineWrap(true);
        sellText.setFont(sellText.getFont().deriveFont(20f));
        scrollBarSell.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        symbolLabelSell.setFont(mainMessage.getFont().deriveFont(15f));
        quantityLabelSell.setFont(mainMessage.getFont().deriveFont(15f));
        priceLabelSell.setFont(mainMessage.getFont().deriveFont(15f));
        symbolTextboxSell.setFont(mainMessage.getFont().deriveFont(15f));
        quantityTextboxSell.setFont(mainMessage.getFont().deriveFont(15f));
        priceTextboxUpdate.setFont(mainMessage.getFont().deriveFont(15f));

        splitterSell.add(panel1Sell);
        splitterSell.add(panel2Sell);
        sellPanel.add(splitterSell);
        sellPanel.add(panel3Sell);

        symbolTextboxSell.setText("");
        quantityTextboxSell.setText("");
        priceTextboxSell.setText("");

    }

    /**
     * creates required interface for update
     */
    public void updateInterface() {
        //buyPanel
        //Labels
        panel1Update.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Updating Investments : "), (new EmptyBorder(30, 5, 30, 5))));
        updatePanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        panel1Update.add(symbolLabelUpdate);
        panel1Update.add(symbolTextboxUpdate);
        panel1Update.add(nameLabelUpdate);
        panel1Update.add(nameTextboxUpdate);
        panel1Update.add(priceLabelUpdate);
        panel1Update.add(priceTextboxUpdate);
        //Panel2
        panel2Update.add(prevButton);
        panel2Update.add(nextButton);
        panel2Update.add(saveButton);
        panel2Update.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Options : "), new EmptyBorder(50, 50, 20, 50)));

        //Panel3
        panel3Update.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Messages : "), (new EmptyBorder(5, 5, 5, 5))));
        panel3Update.add(scrollBarUpdate);
        updateText.setWrapStyleWord(true);
        updateText.setLineWrap(true);
        updateText.setFont(sellText.getFont().deriveFont(20f));
        scrollBarUpdate.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        symbolLabelUpdate.setFont(mainMessage.getFont().deriveFont(15f));
        nameLabelUpdate.setFont(mainMessage.getFont().deriveFont(15f));
        priceLabelUpdate.setFont(mainMessage.getFont().deriveFont(15f));

        symbolTextboxUpdate.setFont(mainMessage.getFont().deriveFont(15f));
        nameTextboxUpdate.setFont(mainMessage.getFont().deriveFont(15f));
        priceTextboxUpdate.setFont(mainMessage.getFont().deriveFont(15f));

        splitterUpdate.add(panel1Update);
        splitterUpdate.add(panel2Update);
        updatePanel.add(splitterUpdate);
        updatePanel.add(panel3Update);

        symbolTextboxUpdate.setEditable(false);
        nameTextboxUpdate.setEditable(false);

        index = 0;
        prevButton.setEnabled(false);

        if (startPortfolio.getSize() == 0 || startPortfolio.getSize() == 1) {
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
        }

        if (startPortfolio.getSize() > 0) {
            symbolTextboxUpdate.setText(startPortfolio.getSymbol(index));
            nameTextboxUpdate.setText(startPortfolio.getName(index));
            priceTextboxUpdate.setText(Double.toString(startPortfolio.getPrice(index)));
        }

    }

    /**
     * creates required interface for gain
     */
    public void gainInterface() {

        panel1Gain.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Getting total gain : "), new EmptyBorder(100, 100, 100, 100)));
        panel2Gain.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Individual gains : "), new EmptyBorder(5, 5, 5, 5)));

        gainLabel.setFont((gainLabel.getFont().deriveFont(25f)));

        panel1Gain.add(gainLabel);
        panel1Gain.add(gainTextbox);

        panel2Gain.add(scrollBarGain);

        gainPanel.add(panel1Gain);
        gainPanel.add(panel2Gain);

        gainTextbox.setEditable(false);

        gainTextbox.setFont(mainMessage.getFont().deriveFont(15f));
        gainLabel.setFont(mainMessage.getFont().deriveFont(15f));
        gainText.setFont(mainMessage.getFont().deriveFont(15f));

        startPortfolio.showGains(gainText, gainTextbox);

    }

    /**
     * creates required interface for searchInterface
     */
    public void searchInterface() {
        //buyPanel
        //Labels
        panel1Search.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Searching Investments : "), (new EmptyBorder(30, 5, 30, 5))));
        searchPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        panel1Search.add(symbolLabelSearch);
        panel1Search.add(symbolTextboxSearch);
        panel1Search.add(nameLabelSearch);
        panel1Search.add(nameTextboxSearch);
        panel1Search.add(lowPriceLabel);
        panel1Search.add(lowPriceTextbox);
        panel1Search.add(highPriceLabel);
        panel1Search.add(highPriceTextbox);
        //Panel2
        panel2Search.add(resetButtonSearch);
        panel2Search.add(searchButton);
        panel2Search.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Options : "), new EmptyBorder(80, 50, 50, 50)));

        symbolTextboxSearch.setText("");
        nameTextboxSearch.setText("");
        lowPriceTextbox.setText("");
        highPriceTextbox.setText("");
        //Panel3
        panel3Search.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Search results : "), (new EmptyBorder(5, 5, 5, 5))));
        panel3Search.add(scrollBarSearch);
        searchText.setWrapStyleWord(true);
        searchText.setLineWrap(true);
        searchText.setFont(searchText.getFont().deriveFont(15f));
        scrollBarSearch.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        splitterSearch.add(panel1Search);
        splitterSearch.add(panel2Search);
        searchPanel.add(splitterSearch);
        searchPanel.add(panel3Search);

    }

    /**
     * creates all action listeners required for every button in this GUI
     *
     * @param c1 : contains cardLayout of finalPanel so that the panels
     * currently being displayed may be changed
     */
    public void addActionListener(CardLayout c1) {

        buyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buyInterface();
                buyText.setText("");
                c1.show(finalPanel, "Buy");
            }
        });

        sellItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sellInterface();
                sellText.setText("");
                c1.show(finalPanel, "Sell");
            }
        });

        updateItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateText.setText("");
                updateInterface();
                c1.show(finalPanel, "Update");
            }
        });

        gainItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gainText.setText("");
                gainInterface();
                c1.show(finalPanel, "Gain");
            }
        });

        searchItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchText.setText("");
                searchInterface();
                c1.show(finalPanel, "Search");
            }
        });

        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startPortfolio.writeFile();
                System.exit(0);
            }
        });

        //BUY
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    startPortfolio.Buy(buyText, typeDropdown.getSelectedIndex(), quantityTextbox.getText(), priceBox.getText(), symbolTextbox.getText(), nameTextbox.getText());
                    //startPortfolio.Buy()
                } catch (PortfolioException ex) {
                    buyText.append(ex.getMessage() + "\n");
                }

            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                symbolTextbox.setText("");
                nameTextbox.setText("");
                priceBox.setText("");
                quantityTextbox.setText("");
            }
        });

        //SELL
        sellButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    startPortfolio.Sell(sellText, quantityTextboxSell.getText(), priceTextboxSell.getText(), symbolTextboxSell.getText());
                } catch (PortfolioException ex) {
                    sellText.append(ex.getMessage() + "\n");
                }
            }
        });

        resetButtonSell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                symbolTextboxSell.setText("");
                quantityTextboxSell.setText("");
                priceTextboxSell.setText("");
            }
        });

        //UPDATE
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (index < startPortfolio.getSize() - 1) {
                    index++;
                    symbolTextboxUpdate.setText(startPortfolio.getSymbol(index));
                    nameTextboxUpdate.setText(startPortfolio.getName(index));
                    priceTextboxUpdate.setText(Double.toString(startPortfolio.getPrice(index)));
                }

                if (index >= startPortfolio.getSize() - 1) {
                    nextButton.setEnabled(false);
                }

                if (index >= 0) {
                    prevButton.setEnabled(true);
                }
            }
        });

        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (index > 0) {
                    index--;
                    symbolTextboxUpdate.setText(startPortfolio.getSymbol(index));
                    nameTextboxUpdate.setText(startPortfolio.getName(index));
                    priceTextboxUpdate.setText(Double.toString(startPortfolio.getPrice(index)));
                }

                if (index == 0) {
                    prevButton.setEnabled(false);
                }

                if (index <= startPortfolio.getSize() - 1) {
                    nextButton.setEnabled(true);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (startPortfolio.getSize() > 0) {
                    try {
                        startPortfolio.Update(updateText, index, priceTextboxUpdate.getText());
                    } catch (PortfolioException ex) {
                        updateText.append(ex.getMessage() + "\n");
                    }
                }
            }
        });

        //SEARCH
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchText.setText("");
                try {
                    startPortfolio.Search(searchText, nameTextboxSearch.getText(), symbolTextboxSearch.getText(), lowPriceTextbox.getText(), highPriceTextbox.getText());
                } catch (PortfolioException ex) {
                    searchText.append(ex.getMessage() + "\n");
                }
            }
        });

        resetButtonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                symbolTextboxSearch.setText("");
                nameTextboxSearch.setText("");
                lowPriceTextbox.setText("");
                highPriceTextbox.setText("");

            }
        });

    }

}
