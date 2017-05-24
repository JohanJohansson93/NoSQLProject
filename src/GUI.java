import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Johan on 2017-05-23.
 */
public class GUI implements ActionListener {

    JFrame frame;
    JPanel panelButtons, panelHeader, panelOrder, panelReport;
    JButton buttonPlace, buttonReport, buttonAddMember, buttonAddProduct, buttonConfirm, buttonClearProd, buttonBack, buttonBack2,
    buttonDateReport, buttonEmployeeDateReport;
    JLabel header, employeeLabel, memberLabel, chooseLabel, priceLabel, productsLabel, employeeCustLabel;
    JTextField employeeField, memberField, employeeCustField;
    JComboBox prods;
    Product[] listOfProducts, finalListOfProducts;
    String[] prodNameList, orderList;
    int counter = 0;
    Controller ctrl;
    int currentPrice = 0;



    public GUI() {
        ctrl = new Controller(this);
        //listOfProducts = ctrl.FetchProducts();
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
        buttonAddProduct = new JButton("Add product");
        buttonBack = new JButton("Back");
        buttonBack2 = new JButton("Back");
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

        employeeLabel = new JLabel("Employee ID");
        employeeField = new JTextField();

        employeeCustLabel = new JLabel("ID for Employee Discount");
        employeeCustField = new JTextField();

        memberLabel = new JLabel("Member ID");
        memberField = new JTextField();

        productsLabel = new JLabel("Products: ");
        priceLabel = new JLabel("" + currentPrice);

        listOfProducts = new Product[4];

        String[] string = new String[2];
        string[0] = new String("milk");
        string[1] = new String("espresso");

        listOfProducts[0] = new Product("Coffee", 2, 3, 25, string);
        listOfProducts[1] = new Product("Milk", 1, 2, 15, string);
        listOfProducts[2] = new Product("Cookie", 2, 3, 25, string);
        listOfProducts[3] = new Product("Lemon", 2, 3, 25, string);
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
        panelOrder.add(employeeCustLabel);
        panelOrder.add(employeeCustField);

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
        buttonReport.addActionListener(this);
        buttonAddProduct.addActionListener(this);
        buttonConfirm.addActionListener(this);
        buttonClearProd.addActionListener(this);
        buttonBack.addActionListener(this);
        buttonBack2.addActionListener(this);
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

        if (e.getSource() == buttonAddMember) {

        }

        if (e.getSource() == buttonConfirm) {
            finalListOfProducts = new Product[counter];
            int g = 0;
            for(int k = 0; k< orderList.length; k++) {
                for(int l = 0; l<listOfProducts.length; l++) {
                    if (orderList[k] == listOfProducts[l].getName()) {
                        finalListOfProducts[g] = listOfProducts[l];
                        g++;
                    }
                }
            }

            for (int d = 0; d<finalListOfProducts.length; d++) {
                try {
                    System.out.println(finalListOfProducts[d].getName());
                } catch(NullPointerException np) {
                    System.out.println("null");
                }
            }
            System.out.print(priceLabel.getText());
            Order ord = new Order(Double.parseDouble(priceLabel.getText()), false, finalListOfProducts, getDate(), 1 );
            System.out.println(ord.getPrice());
            System.out.println(ord.getDate());
            System.out.println(ord.getOrderID());

            for (int s = 0; s<ord.getProducts().length ; s++) {
                System.out.println(ord.getProducts()[s].getName());
            }
        }

        }

        public Date getDate() {

        try {
                return new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-24");
            } catch (ParseException e1) {
                e1.printStackTrace();
                return null;
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
