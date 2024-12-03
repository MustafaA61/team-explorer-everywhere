
import javax.swing.*; // Import Swing components for GUI
import java.awt.*; // Import AWT for layout and event handling
import java.awt.event.*; // Import AWT event handling classes
import java.io.*; // Import classes for file handling
import java.util.ArrayList; // Import ArrayList class for dynamic array

// Room class to represent each hotel room
class Room {
    private String type; // Type of the room (e.g., Single, Double)
    private double price; // Price of the room per night
    private boolean isVacant; // Room vacancy status
    private boolean petsAllowed; // Whether pets are allowed
    private boolean isClean; // Cleanliness status

    // Constructor to initialize room details
    public Room(String type, double price, boolean isVacant, boolean petsAllowed, boolean isClean) {
        this.type = type; // Set room type
        this.price = price; // Set room price
        this.isVacant = isVacant; // Set vacancy status
        this.petsAllowed = petsAllowed; // Set pet policy
        this.isClean = isClean; // Set cleanliness status
    }

    // Getters and Setters for room attributes
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public boolean isVacant() { return isVacant; }
    public void setVacant(boolean vacant) { isVacant = vacant; }
    public boolean isPetsAllowed() { return petsAllowed; }
    public void setPetsAllowed(boolean petsAllowed) { this.petsAllowed = petsAllowed; }
    public boolean isClean() { return isClean; }
    public void setClean(boolean clean) { isClean = clean; }

    // Method to return room details as a string
    @Override
    public String toString() {
        return "Room Type: " + type + ", Price: $" + price + ", Vacant: " + isVacant +
                ", Pets Allowed: " + petsAllowed + ", Clean: " + isClean;
    }
}

// Food class to represent menu items
class Food {
    private String name; // Name of the food item
    private double price; // Price of the food item

    // Constructor to initialize food details
    public Food(String name, double price) {
        this.name = name; // Set food name
        this.price = price; // Set food price
    }

    // Getters for food attributes
    public String getName() { return name; }
    public double getPrice() { return price; }

    // Method to return food details as a string
    @Override
    public String toString() {
        return name + " - $" + price; // Format food details
    }
}

// Main class for the hotel booking system
public class HotelBooking {
    private ArrayList<Room> rooms; // List to store rooms
    private ArrayList<Food> foodMenu; // List to store food items
    private JFrame frame; // Main application window
    private JTextArea roomListArea; // Text area to display room information
    private JTextArea foodMenuArea; // Text area to display food menu

    // Constructor to initialize the system
    public HotelBooking() {
        rooms = new ArrayList<>(); // Initialize the room list
        foodMenu = new ArrayList<>(); // Initialize the food menu
        createFoodMenu(); // Create the food menu
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

    // Method to create the GUI
    private void createGUI() {
        frame = new JFrame("Hotel Room Booking System"); // Create the main frame with title
        frame.setSize(600, 500); // Set frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
        frame

