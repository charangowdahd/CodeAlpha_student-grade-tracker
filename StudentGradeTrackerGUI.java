import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class StudentGradeTrackerGUI extends JFrame {
    private final ArrayList<Student> students = new ArrayList<>();

    private final JTextField nameField = new JTextField();
    private final JTextField scoreField = new JTextField();
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"No.", "Name", "Score", "Grade"}, 0
    );
    private final JTable studentTable = new JTable(tableModel);

    {
        studentTable.setRowSelectionAllowed(true);
        studentTable.setColumnSelectionAllowed(false);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private final JLabel avgLabel = new JLabel("0.00");
    private final JLabel highestLabel = new JLabel("N/A");
    private final JLabel lowestLabel = new JLabel("N/A");
    private final JLabel totalLabel = new JLabel("0");

    public StudentGradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(new Color(15, 23, 42));

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        mainPanel.setBackground(new Color(15, 23, 42));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(15, 23, 42));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));

        JLabel titleLabel = new JLabel("Student Grade Tracker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(new Color(224, 242, 254));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(96, 165, 250)),
                "Add Student",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("SansSerif", Font.BOLD, 14),
                new Color(224, 242, 254)
        ));
        formPanel.setBackground(new Color(30, 41, 59));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(96, 165, 250)),
                        "Add Student",
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION,
                        new Font("SansSerif", Font.BOLD, 14),
                        new Color(224, 242, 254)
                ),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        JLabel nameLabel = new JLabel("Student Name:");
        JLabel scoreLabel = new JLabel("Score (0-100):");
        nameLabel.setForeground(new Color(224, 242, 254));
        scoreLabel.setForeground(new Color(224, 242, 254));
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoreField.setAlignmentX(Component.LEFT_ALIGNMENT);

        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(nameLabel);
        formPanel.add(Box.createVerticalStrut(6));
        formPanel.add(nameField);
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(scoreLabel);
        formPanel.add(Box.createVerticalStrut(6));
        formPanel.add(scoreField);
        formPanel.add(Box.createVerticalStrut(12));

        nameField.setBackground(new Color(15, 23, 42));
        nameField.setForeground(new Color(248, 250, 252));
        nameField.setCaretColor(new Color(248, 250, 252));
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        nameField.setPreferredSize(new Dimension(0, 46));
        nameField.setMinimumSize(new Dimension(0, 46));
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(148, 163, 184)),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        scoreField.setBackground(new Color(15, 23, 42));
        scoreField.setForeground(new Color(248, 250, 252));
        scoreField.setCaretColor(new Color(248, 250, 252));
        scoreField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        scoreField.setPreferredSize(new Dimension(0, 46));
        scoreField.setMinimumSize(new Dimension(0, 46));
        scoreField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        scoreField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(148, 163, 184)),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Selected");
        addButton.setBackground(new Color(59, 130, 246));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        addButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(96, 165, 250), 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        removeButton.setBackground(new Color(239, 68, 68));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFocusPainted(false);
        removeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        removeButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(248, 113, 113), 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        removeButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        removeButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new GridLayout(1, 2, 12, 0));
        buttonRow.setOpaque(false);
        buttonRow.add(addButton);
        buttonRow.add(removeButton);
        buttonRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        formPanel.add(buttonRow);

        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 16, 16));
        statsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(96, 165, 250)),
                "Statistics",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("SansSerif", Font.BOLD, 14),
                new Color(224, 242, 254)
        ));
        statsPanel.setBackground(new Color(15, 23, 42));

        statsPanel.add(createStatBox("Total Students", totalLabel));
        statsPanel.add(createStatBox("Average Score", avgLabel));
        statsPanel.add(createStatBox("Highest Score", highestLabel));
        statsPanel.add(createStatBox("Lowest Score", lowestLabel));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(96, 165, 250)),
                "Student Summary",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("SansSerif", Font.BOLD, 14),
                new Color(224, 242, 254)
        ));
        tablePanel.setBackground(new Color(15, 23, 42));
        tablePanel.setPreferredSize(new Dimension(0, 260));
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.getViewport().setBackground(new Color(15, 23, 42));
        studentTable.setBackground(new Color(15, 23, 42));
        studentTable.setForeground(new Color(248, 250, 252));
        studentTable.setSelectionBackground(new Color(37, 99, 235));
        studentTable.setGridColor(new Color(71, 85, 105));
        studentTable.setRowHeight(30);
        studentTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        studentTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        studentTable.getTableHeader().setBackground(new Color(30, 41, 59));
        studentTable.getTableHeader().setForeground(new Color(224, 242, 254));
        studentTable.setFillsViewportHeight(true);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(statsPanel, BorderLayout.EAST);
        mainPanel.add(tablePanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> addStudent());
        removeButton.addActionListener(e -> removeSelectedStudent());

        studentTable.getInputMap().put(KeyStroke.getKeyStroke("DELETE"), "removeSelectedStudent");
        studentTable.getActionMap().put("removeSelectedStudent", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                removeSelectedStudent();
            }
        });

        refreshTable();
    }

    private JPanel createStatBox(String title, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(96, 165, 250), 1),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        panel.setBackground(new Color(30, 41, 59));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        titleLabel.setForeground(new Color(191, 219, 254));

        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setForeground(new Color(248, 250, 252));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);
        return panel;
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String scoreText = scoreField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a student name.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double score;
        try {
            score = Double.parseDouble(scoreText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid score between 0 and 100.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (score < 0 || score > 100) {
            JOptionPane.showMessageDialog(this, "Score must be between 0 and 100.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        students.add(new Student(name, score));
        nameField.setText("");
        scoreField.setText("");
        refreshTable();
        nameField.requestFocus();
    }

    private void removeSelectedStudent() {
        int[] selectedRows = studentTable.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Please select a student to remove.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int viewRow = selectedRows[0];
        int modelRow = studentTable.convertRowIndexToModel(viewRow);

        if (modelRow < 0 || modelRow >= students.size()) {
            return;
        }

        String studentName = students.get(modelRow).name;
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to remove '" + studentName + "'?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        students.remove(modelRow);
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            tableModel.addRow(new Object[]{
                    i + 1,
                    student.name,
                    String.format("%.2f", student.score),
                    getLetterGrade(student.score)
            });
        }

        updateStatistics();
    }

    private void updateStatistics() {
        if (students.isEmpty()) {
            totalLabel.setText("0");
            avgLabel.setText("0.00");
            highestLabel.setText("N/A");
            lowestLabel.setText("N/A");
            return;
        }

        double total = 0;
        double highest = students.get(0).score;
        double lowest = students.get(0).score;

        for (Student student : students) {
            total += student.score;
            if (student.score > highest) highest = student.score;
            if (student.score < lowest) lowest = student.score;
        }

        totalLabel.setText(String.valueOf(students.size()));
        avgLabel.setText(String.format("%.2f", total / students.size()));
        highestLabel.setText(String.format("%.2f", highest));
        lowestLabel.setText(String.format("%.2f", lowest));
    }

    private String getLetterGrade(double score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    private static class Student {
        private final String name;
        private final double score;

        private Student(String name, double score) {
            this.name = name;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeTrackerGUI app = new StudentGradeTrackerGUI();
            app.setVisible(true);
        });
    }
}
