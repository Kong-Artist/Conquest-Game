import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;

public class StartScreen extends JFrame implements ActionListener{
    Dimension dim;
    JPanel Colors, Players, Bottom;
    JTextField Player1, Player2, Player3, Player4, Player5, Player6;
    JComboBox player1, player2, player3, player4, player5, player6;
    String [] colors = {"Empty", "Red", "Blue", "Green", "Yellow", "Black", "Gray"}; 
    ArrayList <Player> Users;
    int player;
    boolean start;

    public StartScreen(){ //start screen to set up players' names and colors
        super("Start Screen");
        setSize(280, 500);
        setLayout(new BorderLayout());
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - 140, dim.height/2 - 250);

        Colors = new JPanel();
        Colors.setLayout(new GridLayout(7, 1));
        Colors.setPreferredSize(new Dimension(80, 400));
        Players = new JPanel();
        Players.setLayout(new GridLayout(7, 1));
        Players.setPreferredSize(new Dimension(200, 400));
        Bottom = new JPanel();
        Bottom.setPreferredSize(new Dimension(280, 50));

        Player1 = new JTextField("Enter name for Player 1"); //textfields for names
        Player1.setPreferredSize(new Dimension(200, 50));
        Player1.setHorizontalAlignment(JTextField.CENTER);
        Player2 = new JTextField("Enter name for Player 2");
        Player2.setPreferredSize(new Dimension(200, 50));
        Player2.setHorizontalAlignment(JTextField.CENTER);
        Player3 = new JTextField("Enter name for Player 3");
        Player3.setPreferredSize(new Dimension(200, 50));
        Player3.setHorizontalAlignment(JTextField.CENTER);
        Player4 = new JTextField("Enter name for Player 4");
        Player4.setPreferredSize(new Dimension(200, 50));
        Player4.setHorizontalAlignment(JTextField.CENTER);
        Player5 = new JTextField("Enter name for Player 5");
        Player5.setPreferredSize(new Dimension(200, 50));
        Player5.setHorizontalAlignment(JTextField.CENTER);
        Player6 = new JTextField("Enter name for Player 6");
        Player6.setPreferredSize(new Dimension(200, 50));
        Player6.setHorizontalAlignment(JTextField.CENTER);

        Player1.addMouseListener(
            new MouseAdapter() {
                public void mousePressed( MouseEvent e ) {
                    Player1.setText("");
                }
            }
        );
        Player2.addMouseListener(
            new MouseAdapter() {
                public void mousePressed( MouseEvent e ) {
                    Player2.setText("");
                }
            }
        );
        Player3.addMouseListener(
            new MouseAdapter() {
                public void mousePressed( MouseEvent e ) {
                    Player3.setText("");
                }
            }
        );
        Player4.addMouseListener(
            new MouseAdapter() {
                public void mousePressed( MouseEvent e ) {
                    Player4.setText("");
                }
            }
        );
        Player5.addMouseListener(
            new MouseAdapter() {
                public void mousePressed( MouseEvent e ) {
                    Player5.setText("");
                }
            }
        );
        Player6.addMouseListener(
            new MouseAdapter() {
                public void mousePressed( MouseEvent e ) {
                    Player6.setText("");
                }
            }
        );

        player1 = new JComboBox(colors); //combobox for colors
        player1.setPreferredSize(new Dimension(80, 50));
        player2 = new JComboBox(colors);
        player2.setPreferredSize(new Dimension(80, 50));
        player3 = new JComboBox(colors);
        player3.setPreferredSize(new Dimension(80, 50));
        player4 = new JComboBox(colors);
        player4.setPreferredSize(new Dimension(80, 50));
        player5 = new JComboBox(colors);
        player5.setPreferredSize(new Dimension(80, 50));
        player6 = new JComboBox(colors);
        player6.setPreferredSize(new Dimension(80, 50));

        JLabel Name = new JLabel("Player Names", JLabel.CENTER);
        JLabel Color = new JLabel("Player Color", JLabel.CENTER);

        JButton Info = new JButton("Info");
        Info.addActionListener(this);
        JButton Start = new JButton("Start");
        Start.addActionListener(this);

        Players.add(Name);
        Players.add(Player1);
        Players.add(Player2);
        Players.add(Player3);
        Players.add(Player4);
        Players.add(Player5);
        Players.add(Player6);

        Colors.add(Color);
        Colors.add(player1);
        Colors.add(player2);
        Colors.add(player3);
        Colors.add(player4);
        Colors.add(player5);
        Colors.add(player6);

        Bottom.add(Info);
        Bottom.add(new JLabel());
        Bottom.add(new JLabel());
        Bottom.add(Start);

        add("North", new ScreenLogo());
        add("Center", Players);
        add("East", Colors);
        add("South", Bottom);

        start = false;

        Users = new ArrayList();

        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        int empty = 0;
        int red = 0;
        int blue = 0;
        int green = 0;
        int yellow = 0;
        int black = 0;
        int grey = 0;
        switch (player1.getSelectedIndex()){ //checks the color of every player
            case 0: empty++;    break;
            case 1: red++;      break;
            case 2: blue++;     break;
            case 3: green++;    break;
            case 4: yellow++;   break;
            case 5: black++;    break;
            case 6: grey++;     break;
        }
        switch (player2.getSelectedIndex()){
            case 0: empty++;    break;
            case 1: red++;      break;
            case 2: blue++;     break;
            case 3: green++;    break;
            case 4: yellow++;   break;
            case 5: black++;    break;
            case 6: grey++;     break;
        }
        switch (player3.getSelectedIndex()){
            case 0: empty++;    break;
            case 1: red++;      break;
            case 2: blue++;     break;
            case 3: green++;    break;
            case 4: yellow++;   break;
            case 5: black++;    break;
            case 6: grey++;     break;
        }
        switch (player4.getSelectedIndex()){
            case 0: empty++;    break;
            case 1: red++;      break;
            case 2: blue++;     break;
            case 3: green++;    break;
            case 4: yellow++;   break;
            case 5: black++;    break;
            case 6: grey++;     break;
        }
        switch (player5.getSelectedIndex()){
            case 0: empty++;    break;
            case 1: red++;      break;
            case 2: blue++;     break;
            case 3: green++;    break;
            case 4: yellow++;   break;
            case 5: black++;    break;
            case 6: grey++;     break;
        }
        switch (player6.getSelectedIndex()){
            case 0: empty++;    break;
            case 1: red++;      break;
            case 2: blue++;     break;
            case 3: green++;    break;
            case 4: yellow++;   break;
            case 5: black++;    break;
            case 6: grey++;     break;
        }
        if (e.getActionCommand() == "Start"){
            JFrame frame = new JFrame();
            if(empty == 6){ //shows errors such as no players, only1 player, multiple players with the same colors or no names
                JOptionPane.showMessageDialog(frame,
                    "There are no players!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            else if (empty == 5){
                JOptionPane.showMessageDialog(frame,
                    "There is only 1 player!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            else if((red>1) || (blue>1) || (green>1) || (yellow>1) || (black>1) || (grey>1)){
                JOptionPane.showMessageDialog(frame,
                    "You have more than 1 player with the same color!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            else if((Player1.getText().equals("") && player1.getSelectedIndex()!=0) || (Player2.getText().equals("") && player2.getSelectedIndex()!=0) || (Player3.getText().equals("") && player3.getSelectedIndex()!=0) || (Player4.getText().equals("") && player4.getSelectedIndex()!=0) || (Player5.getText().equals("") && player5.getSelectedIndex()!=0) || (Player6.getText().equals("") && player6.getSelectedIndex()!=0)){
                JOptionPane.showMessageDialog(frame,
                    "There is a player with no name!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            else{ //starts the game by setting every player up 
                Color temp = Color.white;
                start = true;
                if(player1.getSelectedIndex()!=0){
                    switch(player1.getSelectedIndex()){
                        case 0:                        break;
                        case 1: temp = Color.red;      break;
                        case 2: temp = Color.blue;     break;
                        case 3: temp = Color.green;    break;
                        case 4: temp = Color.yellow;   break;
                        case 5: temp = Color.black;    break;
                        case 6: temp = Color.gray;     break;
                    }
                    Users.add(new Player(Player1.getText(), player, temp));
                    player++;
                }
                if(player2.getSelectedIndex()!=0){
                    switch(player2.getSelectedIndex()){
                        case 0:                        break;
                        case 1: temp = Color.red;      break;
                        case 2: temp = Color.blue;     break;
                        case 3: temp = Color.green;    break;
                        case 4: temp = Color.yellow;   break;
                        case 5: temp = Color.black;    break;
                        case 6: temp = Color.gray;     break;
                    }
                    Users.add(new Player(Player2.getText(), player, temp));
                    player++;
                }
                if(player3.getSelectedIndex()!=0){
                    switch(player3.getSelectedIndex()){
                        case 0:                        break;
                        case 1: temp = Color.red;      break;
                        case 2: temp = Color.blue;     break;
                        case 3: temp = Color.green;    break;
                        case 4: temp = Color.yellow;   break;
                        case 5: temp = Color.black;    break;
                        case 6: temp = Color.gray;     break;
                    }
                    Users.add(new Player(Player3.getText(), player, temp));
                    player++;
                }
                if(player4.getSelectedIndex()!=0){
                    switch(player4.getSelectedIndex()){
                        case 0:                        break;
                        case 1: temp = Color.red;      break;
                        case 2: temp = Color.blue;     break;
                        case 3: temp = Color.green;    break;
                        case 4: temp = Color.yellow;   break;
                        case 5: temp = Color.black;    break;
                        case 6: temp = Color.gray;     break;
                    }
                    Users.add(new Player(Player4.getText(), player, temp));
                    player++;
                }
                if(player5.getSelectedIndex()!=0){
                    switch(player5.getSelectedIndex()){
                        case 0:                        break;
                        case 1: temp = Color.red;      break;
                        case 2: temp = Color.blue;     break;
                        case 3: temp = Color.green;    break;
                        case 4: temp = Color.yellow;   break;
                        case 5: temp = Color.black;    break;
                        case 6: temp = Color.gray;     break;
                    }
                    Users.add(new Player(Player5.getText(), player, temp));
                    player++;
                }
                if(player6.getSelectedIndex()!=0){
                    switch(player6.getSelectedIndex()){
                        case 0:                        break;
                        case 1: temp = Color.red;      break;
                        case 2: temp = Color.blue;     break;
                        case 3: temp = Color.green;    break;
                        case 4: temp = Color.yellow;   break;
                        case 5: temp = Color.black;    break;
                        case 6: temp = Color.gray;     break;
                    }
                    Users.add(new Player(Player6.getText(), player, temp));
                    player++;
                }
                setVisible(false);
            }
        }
        if (e.getActionCommand() == "Info"){ //information about Risk
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                "Risk is a turn-based game for two to six players. The standard version is played on a board depicting a \n"
                + "political map of the Earth, divided into forty-two territories, which are grouped into six continents. The \n"
                + "primary object of the game is \"world domination,\" or \"to occupy every territory on the board and in so \n"
                + "doing, eliminate all other players.\" Players control armies with which they attempt to capture territories \n"
                + "from other players, with results determined by dice rolls. At the beginning of each turn, you must place \n"
                + "your armies onto your countries. The armies you place is determined by the number of countries you \n"
                + "own, divided by three, always rounded down. Then you can choose to either attack, fortify (moving your \n"
                + "troops between two adjacent countries) or end your turn. If you successfully conquer a country on your \n"
                + "turn, you will receive as Risk card. You can only receive 1 Risk card per turn. At the beginning of every \n"
                + "turn, you may choose to cash in 3 cards to receive an army bonus. You can only cash in a set of 3 of a \n"
                + "kind, 1 of each or a wild card and any other two cards. Cash in rewards follow this sequence: 4, 6, 8, 10, \n"
                + "12, 15, 18, 21, 24, 27, 30, 35, 40, 45, 50, 55, 60. For example, the first person to cash in a set will receive \n"
                + "a 4 army bonus while the next person will receive a 6 army bonus. If you have 5 cards at the beginning \n"
                + "of your turn, you must cash in a set. If you defeat a player by conquering all his countries, you take all of \n"
                + "his Risk cards too. If you have 6 cards or more because of this, you must cash in sets until you have less \n"
                + "than 6 cards. There are also bonuses for controlling continents. For example, a player who has \n"
                + "conquered all of Oceania and holds all of those countries until his next turn will receive a bonus of 2 \n"
                + "armies. As long as he controls all of Oceania, he will continue to receive 2 extra armies at the beginning \n"
                + "of every turn. The dice rolls are simulated so don't complain that the game is rigged. Enjoy! \n"
                + "\n                                                                               IMPORTANT"
                + "\n                      You must click on the country's name to select it, or else it will not work.",
                "How to play",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean started(){
        return start;
    }

    public ArrayList<Player> getPlayers(){
        return Users;
    }
}