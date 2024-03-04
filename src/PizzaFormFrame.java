import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PizzaFormFrame extends JFrame {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 800;
    private JRadioButton thinCrust, regularCrust, deepDishCrust;
    private JComboBox<String> sizes;
    private JCheckBox cheese, ranch, sausage, bacon, hotsauce, chicken;
    private JTextArea orderArea;
    private JButton orderBtn, clearBtn, quitBtn;
    private int toppingCounter = 0;
    private int toppingPrice;
    private double taxBill, subTotal, pizzaPrice, totalBill;
    private String crust = "";
    private String size = "";
    private String topping = "";
    ButtonGroup button_Group = new ButtonGroup();

    public PizzaFormFrame() {

        createForm();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pizza Ordering Form");
        setVisible(true);
    }

    private void createForm() {
        JPanel crustPanel = crustRadioButton();
        JPanel sizePanel = pizzaSizeCombo();
        JPanel toppingPanel = toppings();
        JPanel receiptPanel = receipt();
        JPanel buttonPanel = controlPanel();

        JPanel panel = new JPanel();
        panel.add(crustPanel);
        panel.add(sizePanel);
        panel.add(toppingPanel);
        add(panel, BorderLayout.NORTH);

        add(receiptPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(buttonPanel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel crustRadioButton() {
        thinCrust = new JRadioButton("Thin");
        thinCrust.addActionListener((e) -> {
            crust = "Thin-crust";
        });
        regularCrust = new JRadioButton("Regular");
        regularCrust.addActionListener((e) -> {
            crust = "Regular-crust";
        });
        deepDishCrust = new JRadioButton("Deep-Dish");
        deepDishCrust.addActionListener((e) -> {
            crust = "Deep-Dish-crust";
        });

        button_Group.add(thinCrust);
        button_Group.add(regularCrust);
        button_Group.add(deepDishCrust);

        JPanel panel = new JPanel();
        panel.add(thinCrust);
        panel.add(regularCrust);
        panel.add(deepDishCrust);
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Crust Style"));
        return panel;
    }

    private JPanel pizzaSizeCombo() {
        sizes = new JComboBox();
        String[] pizzaSizes = {"---Select---", "Small", "Medium", "Large", "Super"};
        for (String pizzaSize : pizzaSizes) {
            sizes.addItem(pizzaSize);
        }
        sizes.addActionListener((e) -> {
            String choice = (String) sizes.getSelectedItem();
            if (choice.equalsIgnoreCase("Small")) {
                pizzaPrice = 8.00;
                size="Small ";
            }
            if (choice.equalsIgnoreCase("Medium")) {
                pizzaPrice = 12.00;
                size="Medium ";

            }
            if (choice.equalsIgnoreCase("Large")) {
                pizzaPrice = 16.00;
                size="Large ";

            }
            if (choice.equalsIgnoreCase("Super")) {
                pizzaPrice = 20.00;
                size="Super ";

            }
        });

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Sizes"));
        panel.add(sizes);
        return panel;
    }

    private JPanel toppings() {
        cheese = new JCheckBox("Cheese");
        cheese.addActionListener((e) -> {
            if (cheese.isSelected()) {
                toppingCounter++;
                toppingPrice = toppingCounter;
                topping= "Cheese " + topping;
            } else {
                toppingCounter--;
                toppingPrice = toppingCounter;
            }
        });
        ranch = new JCheckBox("Ranch");
        ranch.addActionListener((e) -> {
            if (ranch.isSelected()) {
                toppingCounter++;
                toppingPrice = toppingCounter;
                topping= "Ranch " + topping;
            } else {
                toppingCounter--;
                toppingPrice = toppingCounter;
            }
        });

        sausage = new JCheckBox("Sausage");
        sausage.addActionListener((e) -> {
            if (sausage.isSelected()) {
                toppingCounter++;
                toppingPrice = toppingCounter;
                topping= "Suasage " + topping;

            } else {
                toppingCounter--;
                toppingPrice = toppingCounter;
            }
        });

        bacon = new JCheckBox("Bacon");
        bacon.addActionListener((e) -> {
            if (bacon.isSelected()) {
                toppingCounter++;
                toppingPrice = toppingCounter;
                topping= "Bacon " + topping;

            } else {
                toppingCounter--;
                toppingPrice = toppingCounter;
            }
        });

        hotsauce= new JCheckBox("Hot-Sauce");
        hotsauce.addActionListener((e) -> {
            if (hotsauce.isSelected()) {
                toppingCounter++;
                toppingPrice = toppingCounter;
                topping= "Hot-Sauce " + topping;
            } else {
                toppingCounter--;
                toppingPrice = toppingCounter;
            }
        });

        chicken = new JCheckBox("Chicken");
        chicken.addActionListener((e) -> {
            if (chicken.isSelected()) {
                toppingCounter++;
                toppingPrice = toppingCounter;
                topping= "Chicken " + topping;

            } else {
                toppingCounter--;
                toppingPrice = toppingCounter;
            }
        });

        JPanel panel = new JPanel();
        panel.add(cheese);
        panel.add(ranch);
        panel.add(sausage);
        panel.add(bacon);
        panel.add(chicken);
        panel.add(hotsauce);
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Toppings"));

        return panel;
    }

    private JPanel receipt() {
        orderArea = new JTextArea(40, 36);
        orderArea.setEditable(false);
        JPanel panel = new JPanel();
        panel.add(orderArea);
        return panel;
    }

    private JPanel controlPanel() {
        orderBtn = new JButton("Order");
        orderBtn.addActionListener((e) -> {
            orderTotalPrintout();
        });

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener((e) -> {
            clearTotalPrintOut();
        });
        quitBtn=new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exiting Pizza Order Form", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.add(orderBtn);
        panel.add(clearBtn);
        panel.add(quitBtn);

        return panel;
    }

    private void orderTotalPrintout() {
        DecimalFormat total = new DecimalFormat("0.00");
        orderArea.append("======================================================\n");
        orderArea.append(size + crust+"  " + total.format(pizzaPrice) +  "\n");
        orderArea.append(topping+"  " + total.format(toppingPrice) + "\n");
        orderArea.append("\n");
        orderArea.append("\n");
        orderArea.append("\n");
        orderArea.append("\n");
        totalAmount();
        orderArea.append("Sub-Total " + total.format(subTotal) + "\n");
        totalTax();
        orderArea.append("Tax: " + total.format(taxBill) + "\n");
        orderArea.append("----------------------------------------------------------------------------------------------\n");
        totalBill();
        orderArea.append("Total: " + total.format(totalBill) + "\n");
        orderArea.append("======================================================\n");
    }

    private void clearTotalPrintOut(){
        orderArea.setText("");
        button_Group.clearSelection();
        cheese.setSelected(false);
        chicken.setSelected(false);
        sausage.setSelected(false);
        bacon.setSelected(false);
        hotsauce.setSelected(false);
        ranch.setSelected(false);
        sizes.setSelectedItem("---Select---");
    }

    private void totalAmount(){
        subTotal = (toppingPrice + pizzaPrice);
    }
    private void totalTax(){
        taxBill = (this.subTotal * .07);
    }
    private void totalBill(){
        totalBill = subTotal + taxBill;
    }

}