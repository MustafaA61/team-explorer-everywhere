
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


