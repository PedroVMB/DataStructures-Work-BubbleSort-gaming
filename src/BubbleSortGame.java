import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BubbleSortGame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JButton[][] buttons;
    private int[][] numbers;

    public BubbleSortGame() {
        initialize();
        generateNumbers();
        createButtons();
    }

    private void initialize() {
        setTitle("Bubble Sort Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void generateNumbers() {
        numbers = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                numbers[i][j] = (int) (Math.random() * 100);
            }
        }
    }

    private void createButtons() {
        panel = new JPanel(new GridLayout(5, 5));
        buttons = new JButton[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j] = new JButton(Integer.toString(numbers[i][j]));
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 18));
                buttons[i][j].setMargin(new Insets(10, 10, 10, 10));
                buttons[i][j].addActionListener(new ButtonListener(i, j));
                panel.add(buttons[i][j]);
            }
        }

        add(panel, BorderLayout.CENTER);

        JButton sortButton = new JButton("Sort");
        sortButton.setFont(new Font("Arial", Font.BOLD, 18));
        sortButton.addActionListener(new SortButtonListener());
        add(sortButton, BorderLayout.SOUTH);
    }

    private void swapNumbers(int i, int j, int x, int y) {
        int temp = numbers[i][j];
        numbers[i][j] = numbers[x][y];
        numbers[x][y] = temp;

        // Atualiza o texto nos botões após a troca dos números
        buttons[i][j].setText(Integer.toString(numbers[i][j]));
        buttons[x][y].setText(Integer.toString(numbers[x][y]));
    }

    private class ButtonListener implements ActionListener {
        private int row;
        private int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (col < 4 && numbers[row][col] > numbers[row][col + 1]) {
                swapNumbers(row, col, row, col + 1);
            }
        }
    }

    private class SortButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean swapped;

            do {
                swapped = false;

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (numbers[i][j] > numbers[i][j + 1]) {
                            swapNumbers(i, j, i, j + 1);
                            swapped = true;

                            try {
                                Thread.sleep(500); // Delay for visualization purposes
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            } while (swapped);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BubbleSortGame game = new BubbleSortGame();
            game.setVisible(true);
        });
    }
}