package nyam;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class AppGUI extends JFrame implements ActionListener {
    private final static String FOOD_PIC_FOLDER = "/home/sammi/Documents/labs/gounsplash/images/";
    private final static String DECK_EMPTY_PIC = "51.jpg";
    private final static String DECK_FULL_PIC = "52.jpg";
    private final static int CARD_LENGTH = 50;
    private final static int CARD_HEIGHT = 70;
    private final static int CUSTOMER_AREA_LENGTH = 200;
    private final static int CUSTOMER_AREA_HEIGHT = 400;
    private final static int DEFAULT_NUM_CUSTOMER_INDEX = 2;

    public static GridBagLayout layout = new GridBagLayout();
    public static Container container;

    private final JLabel foodLabel, gameTableLabel, mainDeck, gameCard;
    private final JButton startBtn;
    private ArrayList<JList> customerTable;
    private Service service;

    public AppGUI() {
        int totalCustomer = getTotalCustomer();
        service = new Service(totalCustomer);
        service.initService();

        container = getContentPane();
        container.setLayout(layout);

        foodLabel = new JLabel("Food");
        gameTableLabel = new JLabel("List Food");

        mainDeck = new JLabel();
        ImageIcon image = new ImageIcon(new ImageIcon(FOOD_PIC_FOLDER + DECK_FULL_PIC).getImage().getScaledInstance(CARD_LENGTH, CARD_HEIGHT, Image.SCALE_DEFAULT));
        mainDeck.setIcon(image);
        mainDeck.setBounds(50, 30, CARD_LENGTH, CARD_HEIGHT);

        gameCard = new JLabel();
        image = new ImageIcon(new ImageIcon(FOOD_PIC_FOLDER + DECK_EMPTY_PIC).getImage().getScaledInstance(CARD_LENGTH, CARD_HEIGHT, Image.SCALE_DEFAULT));
        gameCard.setIcon(image);
        gameCard.setBounds(50, 30, CARD_LENGTH, CARD_HEIGHT);

        startBtn = new JButton("Start");
        startBtn.addActionListener(this);

        customerTable = new ArrayList<JList>();
        ArrayList<ArrayList<Food>> customerCardList = service.getCustomerFoodList();
        for (int i = 0; i < customerCardList.size(); i++) {

            // Buat list makanan untuk setiap pelanggan
            DefaultListModel listModel = new DefaultListModel();
            for (int j = 0; j < customerCardList.get(i).size(); j++) {
                Food currFood = customerCardList.get(i).get(j);

                ImageIcon currImage = new ImageIcon(new ImageIcon(FOOD_PIC_FOLDER +
                        currFood.getImage()).
                        getImage().
                        getScaledInstance(CARD_LENGTH, CARD_HEIGHT, Image.SCALE_SMOOTH));

                listModel.add(j, currImage);
            }

            JList playerCardGUI = new JList(listModel);
            playerCardGUI.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            playerCardGUI.setVisibleRowCount(-1);
            playerCardGUI.setPreferredSize(new Dimension(CUSTOMER_AREA_LENGTH, CUSTOMER_AREA_HEIGHT));
            customerTable.add(playerCardGUI);
        }

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(0, 0, 5, 0);
        container.add(foodLabel, constraint);

        constraint.weightx = 0.5;
        constraint.gridx = 2;
        constraint.gridy = 0;
        container.add(gameTableLabel, constraint);

        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets(0, 0, 10, 10);
        container.add(mainDeck, constraint);

        constraint.weightx = 0.5;
        constraint.gridx = 2;
        constraint.gridy = 1;
        container.add(gameCard, constraint);

        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 2;
        container.add(startBtn, constraint);

        for (int i = 0; i < customerCardList.size(); i++) {
            constraint.weightx = 0.5;
            constraint.gridx = i;
            constraint.gridy = 3;
            container.add(customerTable.get(i), constraint);
        }
    }

    public static void main(String[] args) {
        AppGUI appGUI = new AppGUI(); //Buat papan permainan
        appGUI.setSize(600, 500);
        appGUI.setVisible(true);
        appGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    private int getTotalCustomer() {
        String[] choices = {"2", "3", "4"};
        Object selectedChoice = JOptionPane.showInputDialog(
                container,
                "Pilih Jumlah Pelanggan",
                "Jumlah Pelanggan",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choices,
                choices[DEFAULT_NUM_CUSTOMER_INDEX]);
        if (selectedChoice != null) {
            int totalPlayers = Integer.parseInt(selectedChoice.toString());
            if (totalPlayers >= 1 && totalPlayers <= 4) {
                return totalPlayers;
            }
        }
        return DEFAULT_NUM_CUSTOMER_INDEX + 1;
    }
}
