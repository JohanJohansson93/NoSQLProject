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


    public GUI() {
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

        String[] products = new String[20];
        products[0] = "espresso";
        products[1] = "coffee";
        products[2] = "cookie";

        prods = new JComboBox<String>(products);

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
            }

            if (e.getSource() == buttonAddProduct) {
                productsLabel.setText(productsLabel.getText() + " " + prods.getSelectedItem() + ",");
                System.out.println("jsdjsdfjdsf");
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
