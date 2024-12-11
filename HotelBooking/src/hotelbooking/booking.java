import javax.swing.*; // Import Swing components for GUI
import java.awt.*; // Import AWT for layout and event handling
import java.util.ArrayList; // Import ArrayList class for dynamic array
import java.text.DecimalFormat; // To format the total price


// Room class to represent each hotel room
class Room {
    private String type; // Type of the room (e.g., Single, Double, Suite)
    private double price; // Price of the room per night
    private boolean isVacant; // Room vacancy status
    private boolean petsAllowed; // Whether pets are allowed
    private boolean isClean; // Cleanliness status
    private String bookedBy; // Name of the person who booked the room

    // Constructor to initialize room details
    public Room(String type, double price, boolean isVacant, boolean petsAllowed, boolean isClean) {
        this.type = type;
        this.price = price;
        this.isVacant = isVacant;
        this.petsAllowed = petsAllowed;
        this.isClean = isClean;
        this.bookedBy = ""; // Initially, no one has booked the room
    }

    // Getters and Setters for room attributes
    public String getType() { return type; }
    public double getPrice() { return price; }
    public boolean isVacant() { return isVacant; }
    public boolean isPetsAllowed() { return petsAllowed; }
    public boolean isClean() { return isClean; }
    public String getBookedBy() { return bookedBy; }
    public void setVacant(boolean vacant) { isVacant = vacant; }
    public void setBookedBy(String bookedBy) { this.bookedBy = bookedBy; }

    // Method to return room details as a string
    @Override
    public String toString() {
        return "Room Type: " + type + ", Price: $" + price + ", Vacant: " + isVacant +
                ", Pets Allowed: " + petsAllowed + ", Clean: " + isClean + ", Booked By: " + (isVacant ? "N/A" : bookedBy);
    }
}

// Food class to represent menu items
class Food {
    private String name; // Name of the food item
    private double price; // Price of the food item

    // Constructor to initialize food details
    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters for food attributes
    public String getName() { return name; }
    public double getPrice() { return price; }

    // Method to return food details as a string
    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

// Main class for the hotel booking system
public class HotelBooking {
    private ArrayList<Room> rooms; // List to store rooms
    private ArrayList<Food> foodMenu; // List to store food items
    private JFrame frame; // Main application window
    private JTextArea roomListArea; // Text area to display room information
    private JTextArea foodMenuArea; // Text area to display food menu
    private JTextField nameField; // Field for user to input name
    private JComboBox<String> roomComboBox; // Dropdown to select room type
    private JCheckBox petsCheckBox; // Checkbox to select if the user has pets
    private JCheckBox swimmingPoolCheckBox; // Checkbox to select swimming pool access
    private JCheckBox breakfastCheckBox; // Checkbox to select breakfast
    private JCheckBox gymCheckBox; // Checkbox to select gym access
    private JList<String> foodList; // List to select multiple food items
    private DefaultListModel<String> foodListModel; // Model for the food list

    // Constructor to initialize the system
    public HotelBooking() {
        rooms = new ArrayList<>(); // Initialize the room list
        foodMenu = new ArrayList<>(); // Initialize the food menu
        createFoodMenu(); // Create the food menu
        createRooms(); // Create sample rooms
        createGUI(); // Create the GUI
    }

    // Method to create the food menu with sample items
    private void createFoodMenu() {
        foodMenu.add(new Food("Pasta", 15.99)); // Add Pasta to the menu
        foodMenu.add(new Food("Burger", 10.49)); // Add Burger to the menu
        foodMenu.add(new Food("Salad", 8.99)); // Add Salad to the menu
        foodMenu.add(new Food("Steak", 25.99)); // Add Steak to the menu
        foodMenu.add(new Food("Ice Cream", 5.49)); // Add Ice Cream to the menu
    }

    // Method to create sample rooms
    private void createRooms() {
        rooms.add(new Room("Single", 99.99, true, true, true)); // Add Single Room
        rooms.add(new Room("Double", 149.99, true, false, true)); // Add Double Room
        rooms.add(new Room("Suite", 299.99, true, true, true)); // Add Suite Room
    }

    // Method to create the GUI
    private void createGUI() {
        frame = new JFrame("Hotel Room Booking System"); // Create the main frame with title
        frame.setSize(600, 800); // Set frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close

        // Layout manager for the frame
        frame.setLayout(new BorderLayout());

        // Create text areas to display room and food information
        roomListArea = new JTextArea(10, 40);
        foodMenuArea = new JTextArea(10, 40);
        roomListArea.setEditable(false);
        foodMenuArea.setEditable(false);

        // Populate the text areas with room and food details
        for (Room room : rooms) {
            roomListArea.append(room.toString() + "\n");
        }

        for (Food food : foodMenu) {
            foodMenuArea.append(food.toString() + "\n");
        }

        // Create user input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        // Name input field
        inputPanel.add(new JLabel("Enter Your Name:"));
        nameField = new JTextField(15);
        inputPanel.add(nameField);

        // Room selection
        roomComboBox = new JComboBox<>();
        for (Room room : rooms) {
            if (room.isVacant()) {
                roomComboBox.addItem(room.getType());
            }
        }
        inputPanel.add(new JLabel("Choose a Room:"));
        inputPanel.add(roomComboBox);

        // Checkbox for pets
        petsCheckBox = new JCheckBox("Do you have pets?");
        inputPanel.add(petsCheckBox);

        // Checkbox for swimming pool access
        swimmingPoolCheckBox = new JCheckBox("Add Swimming Pool Access ($5 fee)");
        inputPanel.add(swimmingPoolCheckBox);

        // Checkbox for breakfast
        breakfastCheckBox = new JCheckBox("Add Breakfast ($12 per person)");
        inputPanel.add(breakfastCheckBox);

        // Checkbox for gym access
        gymCheckBox = new JCheckBox("Add Gym Access ($3 fee)");
        inputPanel.add(gymCheckBox);

        // Multi-selection list for food items
        foodListModel = new DefaultListModel<>();
        for (Food food : foodMenu) {
            foodListModel.addElement(food.getName() + " - $" + food.getPrice());
        }

        foodList = new JList<>(foodListModel);
        foodList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane foodScrollPane = new JScrollPane(foodList);
        inputPanel.add(new JLabel("Select Food Items:"));
        inputPanel.add(foodScrollPane);

        // Book button
        JButton bookButton = new JButton("Book Room");
        bookButton.addActionListener(e -> bookRoom());
        inputPanel.add(bookButton);

        // Add text areas and input panel to the frame
        frame.add(new JScrollPane(roomListArea), BorderLayout.NORTH); // Room list area in a scroll pane
        frame.add(new JScrollPane(foodMenuArea), BorderLayout.CENTER); // Food menu area in a scroll pane
        frame.add(inputPanel, BorderLayout.SOUTH); // User input section at the bottom

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to handle booking logic
    private void bookRoom() {
        String userName = nameField.getText();
        String selectedRoomType = (String) roomComboBox.getSelectedItem();
        boolean hasPets = petsCheckBox.isSelected();
        boolean wantsPoolAccess = swimmingPoolCheckBox.isSelected();
        boolean wantsBreakfast = breakfastCheckBox.isSelected();
        boolean wantsGymAccess = gymCheckBox.isSelected();
        double totalPrice = 0;

        // Find the selected room in the room list
        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getType().equals(selectedRoomType) && room.isVacant()) {
                selectedRoom = room;
                selectedRoom.setVacant(false);
                selectedRoom.setBookedBy(userName);
                totalPrice += room.getPrice();
                break;
            }
        }

        if (selectedRoom == null) {
            JOptionPane.showMessageDialog(frame, "Sorry, the selected room is not available.");
            return;
        }

        // Add food costs
        double foodTotal = 0;
        for (String selectedFood : foodList.getSelectedValuesList()) {
            for (Food food : foodMenu) {
                if (selectedFood.contains(food.getName())) {
                    foodTotal += food.getPrice();
                    break;
                }
            }
        }
        totalPrice += foodTotal;

        // Add breakfast cost if selected
        if (wantsBreakfast) {
            totalPrice += 12.00; // Breakfast fee per person
        }

        // Add gym access fee if selected
        if (wantsGymAccess) {
            totalPrice += 3.00; // Gym fee
        }

        // Add swimming pool fee if selected
        if (wantsPoolAccess) {
            totalPrice += 5.00; // Swimming pool fee
        }

        // Round the total price to the nearest tenth
        totalPrice = Math.round(totalPrice * 10.0) / 10.0;

        // Show confirmation message with the total
        JOptionPane.showMessageDialog(frame, "Booking Successful!\n" +
                "Room: " + selectedRoom.getType() + "\n" +
                "Booked By: " + selectedRoom.getBookedBy() + "\n" +
                "Price: $" + selectedRoom.getPrice() + "\n" +
                "Food Total: $" + foodTotal + "\n" +
                (wantsBreakfast ? "Breakfast: +$12.00" : "") + "\n" +
                (wantsGymAccess ? "Gym Access: +$3.00" : "") + "\n" +
                (wantsPoolAccess ? "Swimming Pool Access: +$5.00" : "") + "\n" +
                "Total: $" + totalPrice);

        // Clear the input fields after booking
        nameField.setText("");
        petsCheckBox.setSelected(false);
        swimmingPoolCheckBox.setSelected(false);
        breakfastCheckBox.setSelected(false);
        gymCheckBox.setSelected(false);
        foodList.clearSelection();
    }

    // Main method to run the application
    public static void main(String[] args) {
        new HotelBooking(); // Initialize and show the HotelBooking system
    }
}
