import java.awt.*;
import java.awt.event.*;

public class TTTGame extends Frame implements ActionListener {
    private Button[][] grid = new Button[3][3];
    private boolean isXTurn = true;
    private Label infoLabel;
    private Button restartButton;

    public TTTGame() {
        setTitle("AWT Tic Tac Toe");
        setSize(400, 450);
        setLayout(new BorderLayout());

        Panel board = new Panel();
        board.setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = new Button("");
                grid[row][col].setFont(new Font("SansSerif", Font.BOLD, 36));
                grid[row][col].addActionListener(this);
                board.add(grid[row][col]);
            }
        }

        infoLabel = new Label("Turn: X");
        infoLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        infoLabel.setAlignment(Label.CENTER);

        restartButton = new Button("Start New Game");
        restartButton.addActionListener(e -> clearBoard());

        Panel bottomPanel = new Panel(new BorderLayout());
        bottomPanel.add(infoLabel, BorderLayout.CENTER);
        bottomPanel.add(restartButton, BorderLayout.EAST);

        add(board, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button clickedBtn = (Button) e.getSource();
        if (!clickedBtn.getLabel().equals("")) return;

        clickedBtn.setLabel(isXTurn ? "X" : "O");

        if (hasWon()) {
            infoLabel.setText("Winner: " + (isXTurn ? "X" : "O"));
            disableGrid();
        } else if (isBoardFull()) {
            infoLabel.setText("It's a Draw!");
        } else {
            isXTurn = !isXTurn;
            infoLabel.setText("Turn: " + (isXTurn ? "X" : "O"));
        }
    }

    private boolean hasWon() {
        String player = isXTurn ? "X" : "O";

        for (int i = 0; i < 3; i++) {
            // Rows and Columns
            if (grid[i][0].getLabel().equals(player) &&
                grid[i][1].getLabel().equals(player) &&
                grid[i][2].getLabel().equals(player)) return true;

            if (grid[0][i].getLabel().equals(player) &&
                grid[1][i].getLabel().equals(player) &&
                grid[2][i].getLabel().equals(player)) return true;
        }

        // Diagonals
        if (grid[0][0].getLabel().equals(player) &&
            grid[1][1].getLabel().equals(player) &&
            grid[2][2].getLabel().equals(player)) return true;

        if (grid[0][2].getLabel().equals(player) &&
            grid[1][1].getLabel().equals(player) &&
            grid[2][0].getLabel().equals(player)) return true;

        return false;
    }

    private boolean isBoardFull() {
        for (Button[] row : grid) {
            for (Button btn : row) {
                if (btn.getLabel().isEmpty()) return false;
            }
        }
        return true;
    }

    private void disableGrid() {
        for (Button[] row : grid) {
            for (Button btn : row) {
                btn.setEnabled(false);
            }
        }
    }

    private void clearBoard() {
        for (Button[] row : grid) {
            for (Button btn : row) {
                btn.setLabel("");
                btn.setEnabled(true);
            }
        }
        isXTurn = true;
        infoLabel.setText("Turn: X");
    }

    public static void main(String[] args) {
        new TTTGame();
    }
}
