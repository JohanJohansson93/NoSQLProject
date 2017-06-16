import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * Created by Johan on 2017-05-23.
 */
public class GUI implements ActionListener {

    JFrame frame;
    JPanel panelButtons, panelHeader, panelicon, panelOrder, panelReport, panelMember, panelButtonsreport, panelButtonsmember;
    JButton buttonPlace, buttonReport, buttonAddMember, buttonAddProduct, buttonConfirm, buttonClearProd, buttonBack, buttonBack2,
    buttonDateReport, buttonEmployeeDateReport, btnbackmember, btnAddmember;
    JLabel header, employeeLabel, memberLabel, chooseLabel, priceLabel, productsLabel, memberSSN, memberaddress, memberOccupation, reportSdate
            , reportEdate, SalesArea;
    JTextField employeeField, memberField, memberSSNfield, memberAdressfield, memberOccupationfield;
    JComboBox prods;
    JRadioButton radioEmployee, radioMember;
    Product[] listOfProducts;
    String[] prodNameList, orderList, finalListOfProducts;
    int counter = 0;
    Controller ctrl;
    int currentPrice = 0;
    private UtilDateModel model, utilmodel;
    private Properties p, prop;
    private JDatePanelImpl datePanel, Jdatepanel;
    private JDatePickerImpl datePicker, Jdatepicker;
    private Date selectedSDate, selectedEDate;
    private JTextArea salesTextArea;
    private JScrollPane scrollPane;
    private JLabel icon = new JLabel(new ImageIcon("./src/American_Beaver.jpg"));


    public GUI() throws InterruptedException, ExecutionException, UnknownHostException {
        ctrl = new Controller(this);
        listOfProducts = ctrl.FetchProducts();
        System.out.println("GUI: Products fetched");
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(600, 400));

        panelButtons = new JPanel(new FlowLayout());
        panelButtonsreport = new JPanel(new FlowLayout());
        panelButtonsmember = new JPanel(new FlowLayout());
        panelReport = new JPanel(new FlowLayout());
        panelReport.setLayout(new GridLayout(6,1));

        panelHeader = new JPanel(new FlowLayout());
        header = new JLabel("BeaverCoffee");
        header.setFont(new Font("Serif", Font.BOLD, 36));
        panelHeader.add(header);
        panelicon = new JPanel(new FlowLayout());
        panelicon.add(icon);



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

        panelButtonsreport.add(buttonDateReport);
        panelButtonsreport.add(buttonEmployeeDateReport);
        panelButtonsreport.add(buttonBack2);
        panelButtons.add(buttonPlace);
        panelButtons.add(buttonAddMember);
        panelButtons.add(buttonReport);

        frame.getContentPane().add(panelHeader, BorderLayout.NORTH);
        frame.getContentPane().add(panelButtons, BorderLayout.CENTER);
        frame.getContentPane().add(panelicon, BorderLayout.SOUTH);

        addListeners();

        employeeLabel = new JLabel("Emplyoee ID");
        employeeField = new JTextField();

        reportSdate = new JLabel("Select StartDate");

        model = new UtilDateModel();
        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("tex.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        reportEdate = new JLabel("Select EndDate");

        utilmodel = new UtilDateModel();
        prop = new Properties();
        prop.put("text.today", "Today");
        prop.put("text.month", "Month");
        prop.put("tex.year", "Year");
        Jdatepanel = new JDatePanelImpl(utilmodel, prop);
        Jdatepicker = new JDatePickerImpl(Jdatepanel, new DateLabelFormatter());

        SalesArea = new JLabel("Sales");
        salesTextArea = new JTextArea("", 4, 10);
        scrollPane = new JScrollPane(salesTextArea);
        salesTextArea.setEditable(false);

        panelReport.add(employeeLabel);
        panelReport.add(employeeField);
        panelReport.add(reportSdate);
        panelReport.add(datePicker);
        panelReport.add(reportEdate);
        panelReport.add(Jdatepicker);
        panelReport.add(SalesArea);
        panelReport.add(scrollPane);


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

        panelButtonsmember.add(btnAddmember);
        panelButtonsmember.add(btnbackmember);

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
        buttonDateReport.addActionListener(this);
        btnbackmember.addActionListener(this);
        btnAddmember.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonPlace) {
            frame.remove(panelButtons);
            frame.remove(panelicon);
            frame.getContentPane().add(panelOrder, BorderLayout.CENTER);
            frame.invalidate();
            frame.validate();
            frame.repaint();
        }

            if (e.getSource() == buttonAddMember) {
                frame.remove(panelButtons);
                frame.remove(panelicon);
                frame.getContentPane().add(panelMember, BorderLayout.CENTER);
                frame.getContentPane().add(panelButtonsmember, BorderLayout.SOUTH);
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }

            if (e.getSource() == buttonReport) {
                frame.remove(panelButtons);
                frame.remove(panelicon);
                frame.getContentPane().add(panelReport, BorderLayout.CENTER);
                frame.getContentPane().add(panelButtonsreport, BorderLayout.SOUTH);
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }

            if (e.getSource() == buttonDateReport){

                selectedSDate = (Date) datePicker.getModel().getValue();
                selectedEDate = (Date) Jdatepicker.getModel().getValue();

                if(selectedSDate == null || selectedEDate == null){
                    System.out.println("Please pick startDate and EndDate");

                }else{
                    salesTextArea.setText("");
                    try {
                        ArrayList<Order> sales = ctrl.createReport(selectedSDate,selectedEDate);

                        showSales(sales);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (ExecutionException e2) {
                        e2.printStackTrace();
                    } catch (UnknownHostException e3) {
                        e3.printStackTrace();
                    } catch (ParseException e4) {
                        e4.printStackTrace();
                    }
                    System.out.println("GUI: Dates selected");
                }

            }
            if (e.getSource() == buttonEmployeeDateReport){

                selectedSDate = (Date) datePicker.getModel().getValue();
                selectedEDate = (Date) Jdatepicker.getModel().getValue();
                int employeeID = Integer.parseInt(employeeField.getText());

                if(selectedSDate == null || selectedEDate == null){
                    System.out.println("Please pick startDate and EndDate");

                }else{
                    salesTextArea.setText("");
                    try {
                        ArrayList<Order> sales = ctrl.createEmployeeReport(employeeID,selectedSDate,selectedEDate);

                        showSales(sales);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (ExecutionException e2) {
                        e2.printStackTrace();
                    } catch (UnknownHostException e3) {
                        e3.printStackTrace();
                    } catch (ParseException e4) {
                        e4.printStackTrace();
                    }
                    System.out.println("GUI: Dates selected");
                }
            }

            if(e.getSource() == buttonBack) {
                frame.remove(panelOrder);
                frame.getContentPane().add(panelButtons, BorderLayout.CENTER);
                frame.getContentPane().add(panelicon, BorderLayout.SOUTH);
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }

        if(e.getSource() == buttonBack2) {
            frame.remove(panelReport);
            frame.remove(panelButtonsreport);
            frame.getContentPane().add(panelButtons, BorderLayout.CENTER);
            frame.getContentPane().add(panelicon, BorderLayout.SOUTH);
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
            try {
                ctrl.CreateMember(memberSSNfield.getText(), memberAdressfield.getText(),memberOccupationfield.getText());
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            memberSSNfield.setText("");
            memberAdressfield.setText("");
            memberOccupationfield.setText("");
        }
        if (e.getSource() == btnbackmember){
            frame.remove(panelMember);
            frame.remove(panelButtonsmember);
            frame.getContentPane().add(panelButtons, BorderLayout.CENTER);
            frame.getContentPane().add(panelicon, BorderLayout.SOUTH);
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
            DateFormat df = new SimpleDateFormat("MMM d HH:mm:ss yyyy");

            Date date = Calendar.getInstance().getTime();
            String dateFormated = df.format(date);

                return dateFormated;
            } catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }

        }

        public void showSales(ArrayList<Order> orders){

            System.out.println("GUI: " + orders.size());

            if (orders.size() == 0){
                salesTextArea.setText("No sales found during this time!");
            }else{
                for (int i = 0; i < orders.size(); i++) {
                    salesTextArea.append("Order: " + orders.get(i).getEmployeeID() + orders.get(i).getDate() + "\n");
                }
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
