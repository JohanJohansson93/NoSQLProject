import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Johan on 2017-05-23.
 */
public class GUI implements ActionListener {

    JFrame frame;
    JPanel panelButtons, panelHeader, panelOrder;
    JButton buttonPlace, buttonReview, buttonAddMember, buttonAddProduct, buttonConfirm, buttonClearProd, buttonBack;
    JLabel header, employeeLabel, memberLabel, chooseLabel, priceLabel, productsLabel  ;
    JTextField employeeField, memberField;
    JComboBox prods;
    Product[] listOfProducts;
    String[] prodNameList;
    Controller ctrl;
    int currentPrice = 0;



    public GUI() {
        ctrl = new Controller(this);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(500, 400));

        panelButtons = new JPanel(new FlowLayout());

        panelHeader = new JPanel(new FlowLayout());
        header = new JLabel("BeaverCoffee");
        header.setFont(new Font("Serif", Font.BOLD, 36));
        panelHeader.add(header);

        buttonPlace = new JButton("Place order");
        buttonReview = new JButton("Review order");
        buttonAddMember = new JButton("Add Member");
        buttonAddProduct = new JButton("Add product");
        buttonBack = new JButton("Back");
        buttonClearProd = new JButton("Clear products");
        buttonConfirm = new JButton("Confirm order");

        panelButtons.add(buttonPlace);
        panelButtons.add(buttonAddMember);

        frame.getContentPane().add(panelHeader, BorderLayout.NORTH);
        frame.getContentPane().add(panelButtons, BorderLayout.CENTER);

        addListeners();

        employeeLabel = new JLabel("Employee ID");
        employeeField = new JTextField();

        memberLabel = new JLabel("Member ID");
        memberField = new JTextField();

        productsLabel = new JLabel("Products: ");
        priceLabel = new JLabel("Price: " + currentPrice);

        listOfProducts = new Product[4];

        listOfProducts[0] = new Product("Coffee", 2, 3, 25, 1);
        listOfProducts[1] = new Product("Milk", 1, 2, 15, 2);
        listOfProducts[2] = new Product("Cookie", 2, 3, 25, 3);
        listOfProducts[3] = new Product("Lemon", 2, 3, 25, 4);
        prodNameList = new String[listOfProducts.length];

        for(int i = 0; i < listOfProducts.length; i++ ) {
            prodNameList[i] = listOfProducts[i].getName();
        }

        prods = new JComboBox<String>(prodNameList);

        chooseLabel = new JLabel("Choose products");

        panelOrder = new JPanel(new FlowLayout());
        panelOrder.setLayout(new GridLayout(14,1));
        panelOrder.add(employeeLabel);
        panelOrder.add(employeeField);
        panelOrder.add(memberLabel);
        panelOrder.add(memberField);
        panelOrder.add(chooseLabel);

        panelOrder.add(prods);
        panelOrder.add(productsLabel);
        panelOrder.add(priceLabel);
        panelOrder.add(buttonAddProduct);
        panelOrder.add(buttonClearProd);
        panelOrder.add(buttonConfirm);
        panelOrder.add(buttonBack);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void addListeners() {
        buttonAddMember.addActionListener(this);
        buttonPlace.addActionListener(this);
        buttonReview.addActionListener(this);
        buttonAddProduct.addActionListener(this);
        buttonConfirm.addActionListener(this);
        buttonClearProd.addActionListener(this);
        buttonBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonPlace) {
            frame.remove(panelButtons);
            frame.getContentPane().add(panelOrder, BorderLayout.CENTER);
            frame.invalidate();
            frame.validate();
            frame.repaint();
        }

            if (e.getSource() == buttonAddMember) {

            }

            if(e.getSource() == buttonBack) {
                frame.remove(panelOrder);
                frame.getContentPane().add(panelButtons, BorderLayout.CENTER);
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }

            if (e.getSource() == buttonClearProd) {
                productsLabel.setText("Products: ");
                currentPrice = 0;
                priceLabel.setText("Price: " + currentPrice);
            }

            if (e.getSource() == buttonAddProduct) {
                productsLabel.setText(productsLabel.getText() + " " + prods.getSelectedItem() + ",");
                for(int i = 0; i < listOfProducts.length; i++) {
                    if (prods.getSelectedItem() == listOfProducts[i].getName()) {
                        currentPrice = (int) (currentPrice + listOfProducts[i].getDollars());
                        priceLabel.setText("Price: " + currentPrice);
                    }
                }

            }

        if (e.getSource() == buttonAddMember) {

        }

        }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                GUI gui = new GUI();
            }
        });


    }
}
