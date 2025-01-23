package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class librarygui {

    public static void main(String[] args) {
        // Set up the main frame
        JFrame frame = new JFrame("Library Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setLayout(new BorderLayout());

        // Create components for the main menu
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel welcomeLabel = new JLabel("Welcome to the Library Management System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JButton adminLoginButton = new JButton("Admin Login");
        JButton librarianLoginButton = new JButton("Librarian Login");
        JButton registerButton = new JButton("Register");
        JButton exitButton = new JButton("Exit");

        // Style buttons and labels
        adminLoginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        librarianLoginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        registerButton.setFont(new Font("Arial", Font.PLAIN, 16));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add components to the main panel with GridBagLayout for proper alignment
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 20, 20); // Padding
        mainPanel.add(welcomeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(adminLoginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(librarianLoginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(exitButton, gbc);

        // Add the panel to the frame
        frame.add(mainPanel, BorderLayout.CENTER);

        // Admin login action
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAdminLogin();
            }
        });

        // Librarian login action
        librarianLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLibrarianLogin();
            }
        });

        // Register button action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegistrationWindow(); // Open the registration window
            }
        });

        // Exit button action
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting... Goodbye!");
                System.exit(0);
            }
        });

        // Set frame visible
        frame.setVisible(true);
    }

    private static Connection getConnection() {
        try {
            // Register MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to database
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Rishabh.4002"); // Replace with your DB credentials
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void openAdminLogin() {
    // Admin login window
    JFrame adminLoginFrame = new JFrame("Admin Login");
    adminLoginFrame.setSize(400, 250);
    adminLoginFrame.setLocationRelativeTo(null);
    adminLoginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    adminLoginFrame.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    JLabel usernameLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField(15);
    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField(15);
    JButton loginButton = new JButton("Login");

    // Style components
    usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    loginButton.setFont(new Font("Arial", Font.PLAIN, 14));

    // Add components to the panel with GridBagLayout for alignment
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 10, 10); // Padding
    adminLoginFrame.add(usernameLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    adminLoginFrame.add(usernameField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    adminLoginFrame.add(passwordLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    adminLoginFrame.add(passwordField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    adminLoginFrame.add(loginButton, gbc);

    // Login button action
    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Validate login from database
            if (validateLogin(username, password, "admin")) {
                JOptionPane.showMessageDialog(adminLoginFrame, "Login Successful. Welcome Admin!");
                adminLoginFrame.dispose(); // Close login window
                openAdminDashboard(); // Open the admin dashboard
            } else {
                JOptionPane.showMessageDialog(adminLoginFrame, "Invalid credentials. Please try again.");
            }
        }
    });

    adminLoginFrame.setVisible(true);
}

private static void openAdminDashboard() {
    // Admin Dashboard window
    JFrame adminDashboardFrame = new JFrame("Admin Dashboard");
    adminDashboardFrame.setSize(600, 400);
    adminDashboardFrame.setLocationRelativeTo(null);
    adminDashboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    adminDashboardFrame.setLayout(new BorderLayout());

    JLabel dashboardLabel = new JLabel("Admin Dashboard", JLabel.CENTER);
    dashboardLabel.setFont(new Font("Arial", Font.BOLD, 20));
    adminDashboardFrame.add(dashboardLabel, BorderLayout.NORTH);

    // Add more admin features here
    JButton manageBooksButton = new JButton("Manage Books");
    JButton manageUsersButton = new JButton("Manage Users");
    JButton logoutButton = new JButton("Logout");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(manageBooksButton);
    buttonPanel.add(manageUsersButton);
    buttonPanel.add(logoutButton);

    adminDashboardFrame.add(buttonPanel, BorderLayout.CENTER);

    // Logout button action
    logoutButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(adminDashboardFrame, "Logging out...");
            adminDashboardFrame.dispose(); // Close the dashboard
        }
    });

    adminDashboardFrame.setVisible(true);
}



    private static void openLibrarianLogin() {
    // Librarian login window
    JFrame librarianLoginFrame = new JFrame("Librarian Login");
    librarianLoginFrame.setSize(400, 250);
    librarianLoginFrame.setLocationRelativeTo(null);
    librarianLoginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    librarianLoginFrame.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    JLabel usernameLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField(15);
    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField(15);
    JButton loginButton = new JButton("Login");

    // Style components
    usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    loginButton.setFont(new Font("Arial", Font.PLAIN, 14));

    // Add components to the panel with GridBagLayout for alignment
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 10, 10); // Padding
    librarianLoginFrame.add(usernameLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    librarianLoginFrame.add(usernameField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    librarianLoginFrame.add(passwordLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    librarianLoginFrame.add(passwordField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    librarianLoginFrame.add(loginButton, gbc);

    // Login button action
    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Validate login from database
            if (validateLogin(username, password, "librarian")) {
                JOptionPane.showMessageDialog(librarianLoginFrame, "Login Successful. Welcome Librarian!");
                librarianLoginFrame.dispose(); // Close login window
                openLibrarianDashboard(); // Open the librarian dashboard
            } else {
                JOptionPane.showMessageDialog(librarianLoginFrame, "Invalid credentials. Please try again.");
            }
        }
    });

    librarianLoginFrame.setVisible(true);
}

private static void openLibrarianDashboard() {
    // Librarian Dashboard window
    JFrame librarianDashboardFrame = new JFrame("Librarian Dashboard");
    librarianDashboardFrame.setSize(600, 400);
    librarianDashboardFrame.setLocationRelativeTo(null);
    librarianDashboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    librarianDashboardFrame.setLayout(new BorderLayout());

    JLabel dashboardLabel = new JLabel("Librarian Dashboard", JLabel.CENTER);
    dashboardLabel.setFont(new Font("Arial", Font.BOLD, 20));
    librarianDashboardFrame.add(dashboardLabel, BorderLayout.NORTH);

    // Add more librarian features here
    JButton issueBooksButton = new JButton("Issue Books");
    JButton returnBooksButton = new JButton("Return Books");
    JButton viewCatalogButton = new JButton("View Book Catalog");
    JButton logoutButton = new JButton("Logout");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(issueBooksButton);
    buttonPanel.add(returnBooksButton);
    buttonPanel.add(viewCatalogButton);
    buttonPanel.add(logoutButton);

    librarianDashboardFrame.add(buttonPanel, BorderLayout.CENTER);

    // Logout button action
    logoutButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(librarianDashboardFrame, "Logging out...");
            librarianDashboardFrame.dispose(); // Close the dashboard
        }
    });

    librarianDashboardFrame.setVisible(true);
}

private static boolean validateLogin(String username, String password, String role) {
    try (Connection connection = getConnection()) {
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Database connection error.");
            return false;
        }

        // Query to check credentials based on role
        String query = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, role);

        ResultSet resultSet = stmt.executeQuery();
        return resultSet.next(); // Returns true if a matching record is found
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    private static void openRegistrationWindow() {
        // Registration window code
        JFrame registrationFrame = new JFrame("User Registration");
        registrationFrame.setSize(400, 300);
        registrationFrame.setLocationRelativeTo(null);
        registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registrationFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JLabel roleLabel = new JLabel("Role:");
        JComboBox<String> roleComboBox = new JComboBox<>(new String[] { "admin", "librarian" });
        JButton registerButton = new JButton("Register");

        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        registerButton.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        registrationFrame.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        registrationFrame.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        registrationFrame.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        registrationFrame.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        registrationFrame.add(roleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        registrationFrame.add(roleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        registrationFrame.add(registerButton, gbc);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                if (registerUser(username, password, role)) {
                    JOptionPane.showMessageDialog(registrationFrame, "User registered successfully!");
                    registrationFrame.dispose(); // Close registration window
                } else {
                    JOptionPane.showMessageDialog(registrationFrame, "Registration failed. Try again.");
                }
            }
        });

        registrationFrame.setVisible(true);
    }

    private static boolean registerUser(String username, String password, String role) {
        try (Connection connection = getConnection()) {
            if (connection == null) {
                JOptionPane.showMessageDialog(null, "Database connection error.");
                return false;
            }

            String query = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
