import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// OOP Class 1: Ticket
class Ticket {
    private String route;
    private String date;
    private Set<Integer> bookedSeats;
    private Customer customer;
    private Payment payment;
    private String busName;
    private String timing;

    public Ticket(String route, String date, Set<Integer> bookedSeats, Customer customer, Payment payment, String busName, String timing) {
        this.route = route;
        this.date = date;
        this.bookedSeats = bookedSeats;
        this.customer = customer;
        this.payment = payment;
        this.busName = busName;
        this.timing = timing;
    }

    public String getTicketDetails() {
        return " Ticket Confirmation \n" +
               "Bus Name: " + busName + "\n" +
               "Route: " + route + "\n" +
               "Date: " + date + "\n" +
               "Timing: " + timing + "\n" +
               "Seats: " + bookedSeats + "\n" +
               customer.getCustomerDetails() + "\n" +
               payment.getPaymentDetails() + "\n" +
               "Enjoy your journey! ";
    }

    public String getCancellationDetails() {
        return " Ticket Canceled \n" +
               "Bus Name: " + busName + "\n" +
               "Route: " + route + "\n" +
               "Date: " + date + "\n" +
               "Timing: " + timing + "\n" +
               "Seats: " + bookedSeats + "\n" +
               "Your ticket has been canceled successfully.\n" +
               "Refund will be processed within 24 hours. ";
    }

    public Set<Integer> getBookedSeats() {
        return bookedSeats;
    }
}

// OOP Class 2: Customer
class Customer {
    private String name;
    private String phone;
    private String gender;
    private String address;

    public Customer(String name, String phone, String gender, String address) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
    }

    public String getCustomerDetails() {
        return "Customer Details:\nName: " + name + "\nPhone: " + phone + "\nGender: " + gender + "\nAddress: " + address;
    }
}

// OOP Class 3: Payment
class Payment {
    private String method;
    private String transactionId;

    public Payment(String method, String transactionId) {
        this.method = method;
        this.transactionId = transactionId;
    }

    public String getPaymentDetails() {
        return "Payment Method: " + method + "\nTransaction ID: " + transactionId;
    }
}

// OOP Class 4: Seat (Encapsulation)
class Seat {
    private int number;
    private String type;
    private boolean booked;

    public Seat(int number, String type) {
        this.number = number;
        this.type = type;
        this.booked = false;
    }

    public boolean isBooked() { return booked; }
    public void setBooked(boolean booked) { this.booked = booked; }
    public String getType() { return type; }
    public int getNumber() { return number; }
}

// Main Booking System
public class BusTicketBookingOOP extends JFrame {
    private JComboBox<String> fromCityBox, toCityBox, dateBox, busBox, timingBox;
    private JButton[] seatButtons = new JButton[30];
    private JButton bookButton, cancelButton;
    private JTextArea ticketArea;
    private Set<Integer> selectedSeats = new HashSet<>();
    private Seat[] seats = new Seat[30];

    // Customer Details Fields
    private JTextField nameField, phoneField, addressField;
    private JComboBox<String> genderBox;
    private JComboBox<String> paymentBox;
    private JTextField transactionIdField;
    
    // Store the ticket after booking
    private Ticket bookedTicket;

    public BusTicketBookingOOP() {
        setTitle("Bus Ticket Booking (OOP)");
        setSize(850, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize seats with different types
        initializeSeats();

        // Top Panel - Route Selection
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JPanel routePanel = new JPanel();
        String[] cities = {"Vizag", "Rajahmundry", "Hyderabad", "Chennai", "Bangalore", "Vijayawada", "Tirupati", "Warangal", "Nellore", "Kakinada"};

        routePanel.add(new JLabel("From:"));
        fromCityBox = new JComboBox<>(cities);
        routePanel.add(fromCityBox);
        routePanel.add(new JLabel("To:"));
        toCityBox = new JComboBox<>(cities);
        routePanel.add(toCityBox);
        routePanel.add(new JLabel("Date:"));
        dateBox = new JComboBox<>(generateDates());
        routePanel.add(dateBox);
        routePanel.add(new JLabel("Bus:"));
        String[] buses = {"Orange Travels", "SVR Tours", "Sri Krishna", "Jagan Travels"};
        busBox = new JComboBox<>(buses);
        routePanel.add(busBox);
        routePanel.add(new JLabel("Timing:"));
        String[] timings = {"06:00 AM", "09:00 AM", "12:00 PM", "03:00 PM", "06:00 PM"};
        timingBox = new JComboBox<>(timings);
        routePanel.add(timingBox);
        topPanel.add(routePanel);

        // Customer Details Panel
        JPanel customerPanel = new JPanel(new GridLayout(4, 2));
        nameField = new JTextField();
        phoneField = new JTextField();
        addressField = new JTextField();
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});

        customerPanel.add(new JLabel("Name:"));
        customerPanel.add(nameField);
        customerPanel.add(new JLabel("Phone:"));
        customerPanel.add(phoneField);
        customerPanel.add(new JLabel("Gender:"));
        customerPanel.add(genderBox);
        customerPanel.add(new JLabel("Address:"));
        customerPanel.add(addressField);
        topPanel.add(customerPanel);
        add(topPanel, BorderLayout.NORTH);

        // Seat Selection Panel
        JPanel seatPanel = new JPanel(new GridLayout(5, 6, 5, 5));
        for (int i = 0; i < 30; i++) {
            seatButtons[i] = new JButton();
            updateSeatButton(i);
            seatButtons[i].addActionListener(new SeatSelectionHandler(i));
            seatPanel.add(seatButtons[i]);
        }
        add(seatPanel, BorderLayout.CENTER);

        // Payment and Booking Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel paymentPanel = new JPanel();
        paymentPanel.add(new JLabel("Payment Method:"));
        paymentBox = new JComboBox<>(new String[]{"PhonePe", "Cash", "Debit Card", "Credit Card"});
        paymentPanel.add(paymentBox);
        paymentPanel.add(new JLabel("Transaction ID:"));
        transactionIdField = new JTextField(15);
        paymentPanel.add(transactionIdField);
        bookButton = new JButton("Book Ticket");
        bookButton.addActionListener(new BookingHandler());
        paymentPanel.add(bookButton);

        // Cancel Booking Button
        cancelButton = new JButton("Cancel Booking");
        cancelButton.addActionListener(new CancellationHandler());
        cancelButton.setEnabled(false); // Initially disabled until booking is confirmed
        paymentPanel.add(cancelButton);
        bottomPanel.add(paymentPanel, BorderLayout.NORTH);

        ticketArea = new JTextArea(10, 60);
        ticketArea.setEditable(false);
        bottomPanel.add(new JScrollPane(ticketArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void initializeSeats() {
        String[] types = {"Window", "Middle", "Aisle"};
        Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            seats[i] = new Seat(i + 1, types[rand.nextInt(3)]);
        }
    }

    private void updateSeatButton(int index) {
        seatButtons[index].setText(seats[index].getNumber() + " (" + seats[index].getType() + ")");
        if (seats[index].isBooked()) {
            seatButtons[index].setBackground(Color.RED);
            seatButtons[index].setEnabled(false);
        } else {
            seatButtons[index].setBackground(selectedSeats.contains(index) ? Color.GREEN : Color.WHITE);
            seatButtons[index].setEnabled(true);
        }
    }

    private String[] generateDates() {
        String[] dates = new String[32];
        for (int i = 0; i < 32; i++) {
            dates[i] = "March " + (20 + i);
            if (i >= 12) dates[i] = "April " + (i - 11);
        }
        return dates;
    }

    private class SeatSelectionHandler implements ActionListener {
        private int seatIndex;
        public SeatSelectionHandler(int index) {
            this.seatIndex = index;
        }
        public void actionPerformed(ActionEvent e) {
            if (seats[seatIndex].isBooked()) {
                JOptionPane.showMessageDialog(null, "Seat already booked! Please select another seat.");
                return;
            }
            if (selectedSeats.contains(seatIndex)) {
                selectedSeats.remove(seatIndex);
            } else {
                selectedSeats.add(seatIndex);
            }
            updateSeatButton(seatIndex);
        }
    }

    private class BookingHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Validation
            if (fromCityBox.getSelectedItem().equals(toCityBox.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "Source and destination cannot be the same!");
                return;
            }
            if (selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select at least one seat!");
                return;
            }

            // Create Customer object
            Customer customer = new Customer(
                nameField.getText(),
                phoneField.getText(),
                (String) genderBox.getSelectedItem(),
                addressField.getText()
            );

            // Create Payment object
            Payment payment = new Payment(
                (String) paymentBox.getSelectedItem(),
                transactionIdField.getText()
            );

            // Create Ticket
            bookedTicket = new Ticket(
                fromCityBox.getSelectedItem() + " to " + toCityBox.getSelectedItem(),
                (String) dateBox.getSelectedItem(),
                new HashSet<>(selectedSeats), // Create a copy of the selected seats
                customer,
                payment,
                (String) busBox.getSelectedItem(),
                (String) timingBox.getSelectedItem()
            );

            // Update booked seats
            for (int seat : selectedSeats) {
                seats[seat].setBooked(true);
            }

            // Display the ticket details
            ticketArea.setText(bookedTicket.getTicketDetails());
            selectedSeats.clear();
            cancelButton.setEnabled(true); // Enable the cancel button
            refreshSeatDisplay();
        }
    }

    // Cancellation Handler
    private class CancellationHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (bookedTicket == null) {
                JOptionPane.showMessageDialog(null, "No booking found to cancel!");
                return;
            }
            // Cancel the booked seats
            for (int seat : bookedTicket.getBookedSeats()) {
                seats[seat].setBooked(false);
            }
            // Display cancellation details
            ticketArea.setText(bookedTicket.getCancellationDetails());
            bookedTicket = null; // Reset the booking
            cancelButton.setEnabled(false); // Disable cancel button after cancellation
            refreshSeatDisplay();
        }
    }

    private void refreshSeatDisplay() {
        for (int i = 0; i < 30; i++) {
            updateSeatButton(i);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BusTicketBookingOOP().setVisible(true));
    }
}