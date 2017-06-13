import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Johan on 2017-05-23.
 */
public class GUI implements ActionListener {

    JFrame frame;
    JPanel panelButtons, panelHeader, panelOrder, panelReport, panelMember;
    JButton buttonPlace, buttonReport, buttonAddMember, buttonAddProduct, buttonConfirm, buttonClearProd, buttonBack, buttonBack2,
    buttonDateReport, buttonEmployeeDateReport, btnbackmember, btnAddmember;
    JLabel header, employeeLabel, memberLabel, chooseLabel, priceLabel, productsLabel, memberSSN, memberaddress, memberOccupation;
    JTextField employeeField, memberField, memberSSNfield, memberAdressfield, memberOccupationfield;
    JComboBox prods;
    JRadioButton radioEmployee, radioMember;
    Product[] listOfProducts;
    String[] prodNameList, orderList, finalListOfProducts;
    int counter = 0;
    Controller ctrl;
    int currentPrice = 0;



    public GUI() throws InterruptedException, ExecutionException, UnknownHostException {
        ctrl = new Controller(this);
        listOfProducts = ctrl.FetchProducts();
        System.out.println("GUI: Products fetched");
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(500, 400));

        panelButtons = new JPanel(new FlowLayout());
        panelReport = new JPanel(new FlowLayout());


        panelHeader = new JPanel(new FlowLayout());
        header = new JLabel("BeaverCoffee");
        header.setFont(new Font("Serif", Font.BOLD, 36));
        panelHeader.add(header);

        buttonPlace = new JButton("Place order");
        buttonReport = new JButton("Get report");
        buttonAddMember = new JButton("Add Member");
        btnAddmember = new JButton("Add Member");
        buttonAddProduct = new JButton("Add product");
        buttonBack = new JButton("Back");
        buttonBack2 = new JButton("Back");
        btnbackmember = new JButton("Back");
        buttonClearProd = new JButton("Clear products");
        buttonConfirm = new JButton("Confirm order");
        buttonEmployeeDateReport = new JButton("Get list of orders from employee");
        buttonDateReport = new JButton("Get report from chosen period");

        panelReport.add(buttonDateReport);
        panelReport.add(buttonEmployeeDateReport);
        panelReport.add(buttonBack2);

        panelButtons.add(buttonPlace);
        panelButtons.add(buttonAddMember);
        panelButtons.add(buttonReport);

        frame.getContentPane().add(panelHeader, BorderLayout.NORTH);
        frame.getContentPane().add(panelButtons, BorderLayout.CENTER);

        addListeners();

        memberSSN = new JLabel("SSN");
        memberSSNfield = new JTextField();

        memberaddress = new JLabel("Address");
        memberAdressfield = new JTextField();

        memberOccupation = new JLabel("Occupation");
        memberOccupationfield = new JTextField();

        panelMember = new JPanel(new FlowLayout());
        panelMember.setLayout(new GridLayout(14,1));

        panelMember.add(memberSSN);
        panelMember.add(memberSSNfield);

        panelMember.add(memberaddress);
        panelMember.add(memberAdressfield);

        panelMember.add(memberOccupation);
        panelMember.add(memberOccupationfield);

        panelMember.add(btnAddmember);
        panelMember.add(btnbackmember);

        employeeLabel = new JLabel("Employee ID");
        employeeField = new JTextField();

        memberLabel = new JLabel("Member ID");
        memberField = new JTextField();

        productsLabel = new JLabel("Products: ");
        priceLabel = new JLabel("" + currentPrice);

        /*
        listOfProducts = new Product[4];

        String[] string = new String[2];
        string[0] = new String("milk");
        string[1] = new String("espresso");

        listOfProducts[0] = new Product("Coffee", 2, 3, 25, string);
        listOfProducts[1] = new Product("Milk", 1, 2, 15, string);
        listOfProducts[2] = new Product("Cookie", 2, 3, 25, string);
        listOfProducts[3] = new Product("Lemon", 2, 3, 25, string);
        */

        prodNameList = new String[listOfProducts.length];
        orderList = new String[10];


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
        System.out.println("GUI: Gui complete");

    }

    public void addListeners() {
        buttonAddMember.addActionListener(this);
        buttonPlace.addActionListener(this);
        buttonReport.addActionListener(this);
        buttonAddProduct.addActionListener(this);
        buttonConfirm.addActionListener(this);
        buttonClearProd.addActionListener(this);
        buttonBack.addActionListener(this);
        buttonBack2.addActionListener(this);
        btnbackmember.addActionListener(this);
        btnAddmember.addActionListener(this);
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
                frame.remove(panelButtons);
                frame.getContentPane().add(panelMember, BorderLayout.CENTER);
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }

            if (e.getSource() == buttonReport) {
                frame.remove(panelButtons);
                frame.getContentPane().add(panelReport, BorderLayout.CENTER);
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }

            if(e.getSource() == buttonBack) {
                frame.remove(panelOrder);
                frame.getContentPane().add(panelButtons, BorderLayout.CENTER);
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }

        if(e.getSource() == buttonBack2) {
            frame.remove(panelReport);
            frame.getContentPane().add(panelButtons, BorderLayout.CENTER);
            frame.invalidate();
            frame.validate();
            frame.repaint();
        }

            if (e.getSource() == buttonClearProd) {
                productsLabel.setText("Products: ");
                currentPrice = 0;
                priceLabel.setText("" + currentPrice);
                counter = 0;
            }

            if (e.getSource() == buttonAddProduct) {
                productsLabel.setText(productsLabel.getText() + " " + prods.getSelectedItem() + ",");
                for(int i = 0; i < listOfProducts.length; i++) {
                    if (prods.getSelectedItem() == listOfProducts[i].getName()) {
                        currentPrice = (int) (currentPrice + listOfProducts[i].getDollars());
                        priceLabel.setText("" + currentPrice);
                        orderList[counter] = listOfProducts[i].getName();
                        counter++;
                    }
                }
            }

        if (e.getSource() == btnAddmember) {
            memberSSNfield.getText();
            memberAdressfield.getText();
            memberOccupationfield.getText();
            memberSSNfield.setText("");
            memberAdressfield.setText("");
            memberOccupationfield.setText("");
        }
        if (e.getSource() == btnbackmember){
            frame.remove(panelMember);
            frame.getContentPane().add(panelButtons, BorderLayout.CENTER);
            frame.invalidate();
            frame.validate();
            frame.repaint();
        }

        if (e.getSource() == buttonConfirm) {
            finalListOfProducts = new String[counter];
            int g = 0;
            for(int k = 0; k< orderList.length; k++) {
                for(int l = 0; l<listOfProducts.length; l++) {
                    if (orderList[k] == listOfProducts[l].getName()) {
                        finalListOfProducts[g] = listOfProducts[l].getName();
                        g++;
                    }
                }
            }

            for (int d = 0; d<finalListOfProducts.length; d++) {
                try {
                    System.out.println(finalListOfProducts[d]);
                } catch(NullPointerException np) {
                    System.out.println("null");
                }
            }

            try {
                Boolean processed = false;
                processed = ctrl.CreateOrder(Double.parseDouble(priceLabel.getText()), false, finalListOfProducts, getDate(), Integer.parseInt(employeeField.getText()));
                if (processed) {
                    System.out.println("GUI: Order placed");
                    orderList = new String[10];
                } else {
                    System.out.println("GUI: Something wrong with order");
                }
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();

            }
        }

        }

        public String getDate() {

        try {
                Date date = new Date();
                return date.toString();
            } catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }

        }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try {
                    GUI gui = new GUI();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
