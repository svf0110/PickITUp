/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pickitup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author Gio Turtal and Jose Laserna
 */

public class GuestInterfaceGUI extends JFrame 
{
    
    private TicketHandle ticketHandle = new TicketHandle(); // Initialize ticket handle

    public GuestInterfaceGUI() 
    {
        
        // JFrame setup for Guest Menu
        setTitle("Guest Menu");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Use BorderLayout for better layout management

        // Create a panel for the guest menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBackground(new Color(174, 255, 173)); // Light green background
        menuPanel.setPreferredSize(new Dimension(400, 600)); // Set preferred size for the menu panel

        // Create Guest Ticket Button
        JButton createTicketButton = new JButton("Create Guest Ticket");
        createTicketButton.setBounds(100, 100, 200, 40);
        menuPanel.add(createTicketButton);
        
        JButton backButton = new JButton("Log Out");
        backButton.setBounds(100, 200, 200, 40); // Adjust size and position as needed
        menuPanel.add(backButton);

        createTicketButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Create a new JFrame for creating a ticket
                JFrame createTicketFrame = new JFrame("Create Ticket");
                createTicketFrame.setSize(400, 400);
                createTicketFrame.setLayout(null);
                createTicketFrame.getContentPane().setBackground(new Color(174, 255, 173)); // Light green background

                // Add components for creating a ticket (like text fields and labels)
                JLabel nameLabel = new JLabel("Enter your Full Name:");
                nameLabel.setBounds(20, 20, 150, 25);
                createTicketFrame.add(nameLabel);

                JTextField nameField = new JTextField();
                nameField.setBounds(180, 20, 180, 25);
                createTicketFrame.add(nameField);

                JLabel emailLabel = new JLabel("Enter your Email:");
                emailLabel.setBounds(20, 60, 150, 25);
                createTicketFrame.add(emailLabel);

                JTextField emailField = new JTextField();
                emailField.setBounds(180, 60, 180, 25);
                createTicketFrame.add(emailField);

                JLabel phoneLabel = new JLabel("Enter your Phone Number:");
                phoneLabel.setBounds(20, 100, 150, 25);
                createTicketFrame.add(phoneLabel);

                JTextField phoneField = new JTextField();
                phoneField.setBounds(180, 100, 180, 25);
                createTicketFrame.add(phoneField);

                JLabel descLabel = new JLabel("Enter Description:");
                descLabel.setBounds(20, 140, 150, 25);
                createTicketFrame.add(descLabel);

                JTextField descField = new JTextField();
                descField.setBounds(180, 140, 180, 25);
                createTicketFrame.add(descField);
                
                JLabel priorityLabel = new JLabel("Select Priority:");
                priorityLabel.setBounds(20, 180, 150, 25);
                createTicketFrame.add(priorityLabel);
                
                String[] priorities = {"Low", "Medium", "High", "Critical"};
                JComboBox<String> priorityComboBox = new JComboBox<>(priorities);
                priorityComboBox.setBounds(180, 180, 180, 25);
                createTicketFrame.add(priorityComboBox);

                JLabel typeLabel = new JLabel("Select Ticket Type:");
                typeLabel.setBounds(20, 220, 150, 25);
                createTicketFrame.add(typeLabel);

                String[] types = {"Hardware", "Software", "Network"};
                JComboBox<String> typeComboBox = new JComboBox<>(types);
                typeComboBox.setBounds(180, 220, 180, 25);
                createTicketFrame.add(typeComboBox);

                JButton submitButton = new JButton("Submit");
                submitButton.setBounds(150, 260, 100, 30);
                createTicketFrame.add(submitButton);

                submitButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        // Gather information to create and save the ticket using TicketHandle's saveTicket method
                        String name = nameField.getText();
                        String email = emailField.getText();
                        String phone = phoneField.getText();
                        String description = descField.getText();
                        String type = (String) typeComboBox.getSelectedItem();
                        String priority = (String) priorityComboBox.getSelectedItem();

                        try 
                        {
                            // Call saveTicket with relevant fields based on ticket type
                            switch (type) 
                            {
                                case "Hardware":
                                    String hardware = JOptionPane.showInputDialog("Enter the type of Hardware:");
                                    String model = JOptionPane.showInputDialog("Enter Model Number of Hardware:");
                                    ticketHandle.createTicket(type, name, description, email, phone, priority, hardware, model);
                                    break;
                                case "Software":
                                    String software = JOptionPane.showInputDialog("Enter name of Software:");
                                    String version = JOptionPane.showInputDialog("Enter the current Version of Software:");
                                    ticketHandle.createTicket(type, name, description, email, phone, priority, software, version);
                                    break;
                                case "Network":
                                    String device = JOptionPane.showInputDialog("Enter Network Issue:");
                                    String ipAddress = JOptionPane.showInputDialog("Enter IP address:");
                                    ticketHandle.createTicket(type, name, description, email, phone, priority, device, ipAddress);
                                    break;
                            }
                            JOptionPane.showMessageDialog(createTicketFrame, "Ticket Created Successfully!");
                            createTicketFrame.dispose(); // Close the frame after submission
                        } 
                        catch (SQLException ex) 
                        {
                            JOptionPane.showMessageDialog(createTicketFrame, "Error creating ticket: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
                });
                createTicketFrame.setVisible(true);
            }
        });
        
        backButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Go back to the login screen
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.setVisible(true);
                dispose(); // Close the current window
            }
        });

        // Add the menu panel to the left side of the main frame
        add(menuPanel, BorderLayout.WEST);

        // Add the plant image on the right side of the main frame
        ImageIcon icon = new ImageIcon("plant.jpg"); // Change to your image path
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH); // Scale the image
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));
        add(imageLabel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}


