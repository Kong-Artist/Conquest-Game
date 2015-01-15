/**
 * This basically plays the same way as Risk except you won't be able to see the dice rolls or the miniature armies.
 * 
 * Cool things about this game:
 * Well it's fullscreen and compatible with any screen size.
 * Entirely run on GUI.
 * Used buffered images so it doesn't flicker.
 * Got a method to append text automatically to a custom info box. This methods cuts the text to nice lengths automatically.
 * 
 * So here we go:
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;
import java.math.*;

public class RiskGame extends JFrame implements MouseListener, MouseMotionListener{
    boolean isActive, clicked, selected, attack, view, getCard, conquered, fortify, fortified, place, moving, a, v, f, end, done, cash, quit; 
    BufferedImage screen;
    Image map, actions;
    String x, y;
    ArrayList<Country> Countries;
    ArrayList<Player> Players;
    ArrayList<Card> Cards;
    ArrayList<Integer> Rewards;
    int select, active, turn, income, fortify1, fortify2, cashIn, cash1, cash2, cash3, reward;
    Dimension dim;
    Rectangle Attack, View, Fortify, End, Done, CashIn, Quit;
    Random r;
    StartScreen Start;
    JFrame frame;

    public RiskGame(){
        super("RiskGame Window");
        Start = new StartScreen(); //Set up to set up the players and their colors.
        while(Start.started() == false){
            if(Start.isVisible() == false){
                System.exit(0);
            }
            Thread.yield(); //make sure code doesn't run while startscreen is still on.
        }
        setLayout(new BorderLayout());
        dim = Toolkit.getDefaultToolkit().getScreenSize(); //getting screen size.
        isActive = false;
        clicked = false;
        attack = false;
        view = false;
        fortify = false;
        fortified = false;
        conquered = false;
        getCard = false;
        moving = false;
        place = true;
        turn = 0;
        reward = 0;

        //Since im resizing this game for every screen size, you're going to see (int)((dim.getWidth()/1920.0)*int) and (int)((dim.getWidth()/1920.0)*int) alot since I'm scaling everything to my screen size (1080p).

        Attack = new Rectangle((int)((dim.getWidth()/1920.0)*1645), (int)((dim.getHeight()/1080.0)*360), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
        View = new Rectangle((int)((dim.getWidth()/1920.0)*1765), (int)((dim.getHeight()/1080.0)*360), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
        Fortify = new Rectangle((int)((dim.getWidth()/1920.0)*1645), (int)((dim.getHeight()/1080.0)*460), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
        End = new Rectangle((int)((dim.getWidth()/1920.0)*1765), (int)((dim.getHeight()/1080.0)*460), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
        Quit = new Rectangle((int)((dim.getWidth()/1920.0)*1645), (int)((dim.getHeight()/1080.0)*560), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));

        CashIn = new Rectangle((int)((dim.getWidth()/1920.0)*760), (int)((dim.getHeight()/1080.0)*900), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
        Done = new Rectangle((int)((dim.getWidth()/1920.0)*1060), (int)((dim.getHeight()/1080.0)*900), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));

        setExtendedState(Frame.MAXIMIZED_BOTH); //fullscreen
        setUndecorated(true); //no taskbars

        Countries = new ArrayList(); //list of countries.
        Countries.add(new Country("Alaska", 0));
        Countries.get(0).setBord(110, 155, 55, 20);
        Countries.add(new Country("Alberta", 0));
        Countries.get(1).setBord(235, 230, 60, 20);
        Countries.add(new Country("Central America", 0));
        Countries.get(2).setBord(235, 435, 60, 30);
        Countries.add(new Country("Eastern United States", 0));
        Countries.get(3).setBord(330, 370, 95, 30);
        Countries.add(new Country("Greenland", 0));
        Countries.get(4).setBord(520, 100, 75, 20);
        Countries.add(new Country("Northwest Territory", 0));
        Countries.get(5).setBord(200, 155, 150, 20);
        Countries.add(new Country("Ontario", 0));
        Countries.get(6).setBord(325, 250, 55, 20);
        Countries.add(new Country("Quebec", 0));
        Countries.get(7).setBord(435, 250, 55, 25);
        Countries.add(new Country("Western United States", 0));
        Countries.get(8).setBord(215, 335, 105, 30);
        Countries.add(new Country("Argentina", 1));
        Countries.get(9).setBord(395, 795, 70, 20);
        Countries.add(new Country("Brazil", 1));
        Countries.get(10).setBord(480, 655, 50, 20);
        Countries.add(new Country("Peru", 1));
        Countries.get(11).setBord(380, 690, 35, 20);
        Countries.add(new Country("Venezuela", 1));
        Countries.get(12).setBord(350, 565, 70, 20);
        Countries.add(new Country("Great Britain", 2));
        Countries.get(13).setBord(600, 295, 95, 20);
        Countries.add(new Country("Iceland", 2));
        Countries.get(14).setBord(650, 200, 55, 20);
        Countries.add(new Country("Northern Europe", 2));
        Countries.get(15).setBord(760, 325, 70, 35);
        Countries.add(new Country("Scandinavia", 2));
        Countries.get(16).setBord(775, 180, 80, 20);
        Countries.add(new Country("Southern Europe", 2));
        Countries.get(17).setBord(765, 410, 70, 30);
        Countries.add(new Country("Ukraine", 2));
        Countries.get(18).setBord(890, 260, 60, 20);
        Countries.add(new Country("Western Europe", 2));
        Countries.get(19).setBord(635, 455, 65, 35);
        Countries.add(new Country("Congo", 3));
        Countries.get(20).setBord(825, 745, 55, 20);
        Countries.add(new Country("East Africa", 3));
        Countries.get(21).setBord(890, 695, 85, 20);
        Countries.add(new Country("Egypt", 3));
        Countries.get(22).setBord(820, 575, 50, 20);
        Countries.add(new Country("Madagascar", 3));
        Countries.get(23).setBord(975, 855, 30, 80);
        Countries.add(new Country("North Africa", 3));
        Countries.get(24).setBord(670, 590, 100, 35);
        Countries.add(new Country("South Africa", 3));
        Countries.get(25).setBord(810, 865, 95, 20);
        Countries.add(new Country("Afghanistan", 4));
        Countries.get(26).setBord(1005, 370, 80, 20);
        Countries.add(new Country("China", 4));
        Countries.get(27).setBord(1210, 450, 50, 20);
        Countries.add(new Country("India", 4));
        Countries.get(28).setBord(1110, 520, 40, 20);
        Countries.add(new Country("Irkutsk", 4));
        Countries.get(29).setBord(1215, 245, 60, 20);
        Countries.add(new Country("Japan", 4));
        Countries.get(30).setBord(1385, 370, 50, 20);
        Countries.add(new Country("Kamchatka", 4));
        Countries.get(31).setBord(1340, 135, 80, 20);
        Countries.add(new Country("Middle East", 4));
        Countries.get(32).setBord(930, 500, 85, 20);
        Countries.add(new Country("Mongolia", 4));
        Countries.get(33).setBord(1225, 345, 70, 20);
        Countries.add(new Country("Siam", 4));
        Countries.get(34).setBord(1230, 565, 45, 20);
        Countries.add(new Country("Siberia", 4));
        Countries.get(35).setBord(1115, 165, 60, 20);
        Countries.add(new Country("Ural", 4));
        Countries.get(36).setBord(1045, 225, 45, 20);
        Countries.add(new Country("Yakutsk", 4));
        Countries.get(37).setBord(1230, 125, 60, 20);
        Countries.add(new Country("Eastern Australia", 5));
        Countries.get(38).setBord(1375, 850, 125, 20);
        Countries.add(new Country("Indonesia", 5));
        Countries.get(39).setBord(1260, 690, 45, 70);
        Countries.add(new Country("New Guinea", 5));
        Countries.get(40).setBord(1380, 690, 55, 30);
        Countries.add(new Country("Western Australia", 5));
        Countries.get(41).setBord(1290, 875, 130, 20);

        for (int c = 0; c < 42; c++){ //resizing, relocating and giving a picture and a border to every country.
            Countries.get(c).selectedImage(new ImageIcon("" + Countries.get(c).getName() + ".jpg").getImage());
            Countries.get(c).selectedImage(resize(Countries.get(c).getSelected(), (int)((dim.getWidth()/1920.0)*1576), (int)((dim.getHeight()/1080.0)*1050)));
            Countries.get(c).setBord((int)((dim.getWidth()/1920.0)*Countries.get(c).giveBorder().x) + (int)((dim.getWidth()/1920.0)*15), (int)((dim.getHeight()/1080.0)*Countries.get(c).giveBorder().y) + (int)((dim.getHeight()/1080.0)*15), (int)((dim.getWidth()/1920.0)*Countries.get(c).giveBorder().width), (int)((dim.getHeight()/1080.0)*Countries.get(c).giveBorder().height));
            Rectangle temp = new Rectangle (Countries.get(c).giveBorder().x + (Countries.get(c).giveBorder().width/2) - (int)((dim.getWidth()/1920.0)*150), Countries.get(c).giveBorder().y + (Countries.get(c).giveBorder().height/2) - (int)((dim.getHeight()/1080.0)*150), (int)((dim.getWidth()/1920.0)*300), (int)((dim.getHeight()/1080.0)*300));
        }

        for (int c = 0; c < 42; c++){ //attempting to get all the neighbors for a country.
            Rectangle temp = new Rectangle (Countries.get(c).giveBorder().x + (Countries.get(c).giveBorder().width/2) - (int)((dim.getWidth()/1920.0)*150), Countries.get(c).giveBorder().y + (Countries.get(c).giveBorder().height/2) - (int)((dim.getHeight()/1080.0)*150), (int)((dim.getWidth()/1920.0)*300), (int)((dim.getHeight()/1080.0)*300));
            for (int c2 = 0; c2 < 42; (c2)++){
                Point p1 = Countries.get(c2).giveBorder().getLocation();
                Point p2 = Countries.get(c2).giveBorder().getLocation();
                p2.translate(Countries.get(c2).giveBorder().width, 0);
                Point p3 = Countries.get(c2).giveBorder().getLocation();
                p3.translate(0, Countries.get(c2).giveBorder().height);
                Point p4 = Countries.get(c2).giveBorder().getLocation();
                p4.translate(Countries.get(c2).giveBorder().width, Countries.get(c2).giveBorder().height);
                if (temp.contains(p1) || temp.contains(p2) || temp.contains(p3) ||temp.contains(p4)){
                    if (c != c2){
                        Countries.get(c).addNeighbor(Countries.get(c2));
                    }
                }
            }
        }

        //manually adding/removing the missing/extra neighbors
        Countries.get(0).addNeighbor(Countries, "Kamchatka");
        Countries.get(1).removeNeighbor(Countries, "Eastern United States");
        Countries.get(3).removeNeighbor(Countries, "Alberta");
        Countries.get(4).addNeighbor(Countries, "Northwest Territory");
        Countries.get(4).addNeighbor(Countries, "Ontario");
        Countries.get(5).removeNeighbor(Countries, "Quebec");
        Countries.get(6).addNeighbor(Countries, "Greenland");
        Countries.get(7).removeNeighbor(Countries, "Great Britain");
        Countries.get(7).removeNeighbor(Countries, "Northwest Territory");
        Countries.get(10).addNeighbor(Countries, "North Africa");
        Countries.get(13).removeNeighbor(Countries, "Southern Europe");
        Countries.get(13).addNeighbor(Countries, "Western Europe");
        Countries.get(14).removeNeighbor(Countries, "Northern Europe");
        Countries.get(15).addNeighbor(Countries, "Scandinavia");
        Countries.get(15).removeNeighbor(Countries, "Iceland");
        Countries.get(15).removeNeighbor(Countries, "Middle East");
        Countries.get(17).addNeighbor(Countries, "North Africa");
        Countries.get(17).addNeighbor(Countries, "Ukraine");
        Countries.get(18).addNeighbor(Countries, "Middle East");
        Countries.get(19).addNeighbor(Countries, "Great Britain");
        Countries.get(20).removeNeighbor(Countries, "Madagascar");
        Countries.get(21).addNeighbor(Countries, "North Africa");
        Countries.get(21).addNeighbor(Countries, "Middle East");
        Countries.get(23).addNeighbor(Countries, "East Africa");
        Countries.get(23).removeNeighbor(Countries, "Congo");
        Countries.get(24).addNeighbor(Countries, "Southern Europe");
        Countries.get(24).addNeighbor(Countries, "Eastern Africa");
        Countries.get(24).addNeighbor(Countries, "Brazil");
        Countries.get(24).addNeighbor(Countries, "East Africa");
        Countries.get(25).addNeighbor(Countries, "East Africa");
        Countries.get(26).addNeighbor(Countries, "China");
        Countries.get(27).removeNeighbor(Countries, "Japan");
        Countries.get(27).addNeighbor(Countries, "Siberia");
        Countries.get(27).addNeighbor(Countries, "Ural");
        Countries.get(28).removeNeighbor(Countries, "Indonesia");
        Countries.get(29).removeNeighbor(Countries, "Japan");
        Countries.get(30).removeNeighbor(Countries, "China");
        Countries.get(30).removeNeighbor(Countries, "Irkutsk");
        Countries.get(30).addNeighbor(Countries, "Kamchatka");
        Countries.get(31).addNeighbor(Countries, "Mongolia");
        Countries.get(31).addNeighbor(Countries, "Japan");
        Countries.get(31).addNeighbor(Countries, "Alaska");
        Countries.get(32).removeNeighbor(Countries, "Northern Europe");
        Countries.get(32).addNeighbor(Countries, "Ukraine");
        Countries.get(32).addNeighbor(Countries, "East Africa");
        Countries.get(33).addNeighbor(Countries, "Siberia");
        Countries.get(33).addNeighbor(Countries, "Kamchatka");
        Countries.get(34).removeNeighbor(Countries, "New Guinea");
        Countries.get(35).addNeighbor(Countries, "Mongolia");
        Countries.get(35).addNeighbor(Countries, "China");
        Countries.get(35).addNeighbor(Countries, "Yakutsk");
        Countries.get(35).addNeighbor(Countries, "Ural");
        Countries.get(36).removeNeighbor(Countries, "Irkutsk");
        Countries.get(36).removeNeighbor(Countries, "Yakutsk");
        Countries.get(36).addNeighbor(Countries, "China");
        Countries.get(38).addNeighbor(Countries, "New Guinea");
        Countries.get(40).removeNeighbor(Countries, "Siam");
        Countries.get(40).addNeighbor(Countries, "Western Australia");
        Countries.get(41).addNeighbor(Countries, "New Guinea");

        Players = new ArrayList(Start.getPlayers()); //getting players from StartScreen.

        ArrayList<Country> temp2 = new ArrayList(Countries);
        Cards = new ArrayList();
        r = new Random();

        for (int c = 0; c < Players.size(); c++){ //distributing countries randomly
            for (int c2 = 0; c2 < 42/Players.size(); c2++){
                int temp = r.nextInt(temp2.size());
                Players.get(c).conquered(temp2.get(temp));
                temp2.remove(temp);
            }
        }

        int temp = 0; 
        while(temp2.size()!=0){ //giving out leftover countries
            Players.get(temp).conquered(temp2.get(0));
            temp2.remove(0);
            temp++;
        }

        for(int c = 0; c <Players.size(); c++){
            Players.get(c).start();
        }

        int unit = 0;
        for (int c = 0; c < 42; c++){ //making cards
            Cards.add(new Card(Countries.get(c).getName(), unit, -1));
            unit ++;
            if(unit == 3){
                unit = 0;
            }
        }
        Cards.add(new Card("Wild card", 3, -1));
        Cards.add(new Card("Wild card", 3, -1));

        Rewards = new ArrayList(); //setting rewards
        for (int c = 4; c < 13; c += 2){
            Rewards.add(new Integer(c));
        }
        for (int c = 15; c < 31; c += 3){
            Rewards.add(new Integer(c));
        }
        for (int c = 35; c < 61; c += 5){
            Rewards.add(new Integer(c));
        }

        addMouseListener(this);
        addMouseMotionListener(this);

        income = Players.get(0).getIncome();
        fortify1 = -1;
        fortify2 = -1;

        map = new ImageIcon("RiskMap.jpg").getImage();
        map = resize(map, (int)((dim.getWidth()/1920)*1576), (int)((dim.getHeight()/1080)*1050.0));
        actions = new ImageIcon("ActionPaneWithInfo.jpg").getImage();
        actions = resize(actions, (int)((dim.getWidth()/1920)*290), (int)((dim.getHeight()/1080)*1050.0));
        screen = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);

        frame = new JFrame();

        buffer();
        setVisible(true);

        JOptionPane.showMessageDialog(frame,
            "It is now " + Players.get(turn).getName() + "'s turn.",
            "Information",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void mousePressed( MouseEvent e ) {
        if(Players.get(turn).getCardsN() > 5){
            view = true;
        }
        if(isActive == true && view == false){
            if(selected == true && select != active && Countries.get(select).isNeighbor(Countries.get(active))){ //everything that happens when you attack. Takes note whether you conquered a country, got a card or if you can't attack.
                if(conquered == true){
                    if((fortify1 == select && fortify2 == active)||(fortify1 == active && fortify2 == select)){
                        if(Countries.get(select).getArmies() > 1){
                            Countries.get(select).lose(1);
                            Countries.get(active).gain(1);
                        }
                    }
                    if((select != fortify1) && (select != fortify2)){
                        moving = false;
                        fortify1 = -1;
                        fortify2 = -1;
                    }
                }
                if(attack == true && Countries.get(active).getPossession() != turn && Countries.get(select).getArmies() > 1){
                    Countries.get(select).attack(Countries.get(active));
                    conquered = false;
                    if(Countries.get(active).isEmpty()){
                        JOptionPane.showMessageDialog(frame,
                            "You have conquered " + Countries.get(active).getName() + "!",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                        while(frame.isVisible()){
                            Thread.yield();
                        }
                        if (getCard == false){
                            getCard = true;
                            int temp = r.nextInt(Cards.size());
                            Players.get(Countries.get(select).getPossession()).gotCard(Cards.get(temp));
                            Cards.remove(temp);
                        }
                        if (Players.get(Countries.get(active).getPossession()).getCountries() == 0){
                            Players.get(Countries.get(select).getPossession()).gotCards(Players.get(Countries.get(active).getPossession()));
                            JOptionPane.showMessageDialog(frame,
                                Players.get(turn).getName() + " has defeated " + Players.get(Countries.get(active).getPossession()).getName() + "!",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                            while(frame.isVisible()){
                                Thread.yield();
                            }
                            if(Players.get(turn).getCountries() == 42){
                                JOptionPane.showMessageDialog(frame,
                                    "Congratulations! You have conquered the world!",
                                    "Information",
                                    JOptionPane.INFORMATION_MESSAGE);
                                while(frame.isVisible()){
                                    Thread.yield();
                                }
                                System.exit(0);
                            }
                            if (Players.get(turn).getCardsN() > 5){
                                JOptionPane.showMessageDialog(frame,
                                    "You have more than 5 cards. Please cash in.",
                                    "Information",
                                    JOptionPane.INFORMATION_MESSAGE);
                                while(frame.isVisible()){
                                    Thread.yield();
                                }
                                view = true;
                                isActive = false;
                                cash1 = -1;
                                cash2 = -1;
                                cash3 = -1;
                                cashIn = 1;
                                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                            }
                        }
                        Players.get(Countries.get(select).getPossession()).conquered(Countries.get(active));
                        Players.get(Countries.get(active).getPossession()).lost(Countries.get(active));
                        Countries.get(active).conqueredBy(Countries.get(select).getPossession());
                        conquered = true;
                        moving = true;
                        Countries.get(select).lose(1);
                        Countries.get(active).gain(1);
                        fortify1 = select;
                        fortify2 = active;
                    }
                }
                if(fortify == true && Countries.get(active).getPossession() == turn && Countries.get(select).isNeighbor(Countries.get(active))){ //fortification procedure
                    if(fortified == false && Countries.get(select).getArmies() > 1){
                        fortified = true;
                        fortify1 = select;
                        fortify2 = active;
                    }
                    if((fortify1 == select && fortify2 == active)||(fortify1 == active && fortify2 == select)){
                        if(Countries.get(select).getArmies() > 1 && fortified == true){
                            Countries.get(select).lose(1);
                            Countries.get(active).gain(1);
                        }
                    }
                }
            }
            else if(selected == true && place == true){ //placing armies
                Countries.get(select).gain(1);
                income--;
                if(income == 0){
                    place = false;
                }
                buffer();
            }
            else if(Countries.get(active).getPossession() == turn){
                selected = true;
                select = active;
            }
        }
        else{
            selected = false;
        }
        if (place == false){ //allowing custom buttons to work after you finish placing armies
            if((a==true)&&(fortified == false)){ //choosing to attack
                attack = true;
                fortify = false;
            }
            if((f == true) && (fortified == false)){ //choosing to fortify
                attack = false;
                fortify = true;
                if(fortified == false){
                    fortify1 = -1;
                    fortify2 = -1;
                }
            }
            if(end){ //ending your turn
                turn++;
                if (turn == Players.size()){
                    turn = 0;
                }
                income = Players.get(turn).getIncome();
                attack = false;
                place = true;
                fortify = false;
                fortified = false;
                getCard = false;
                moving = false;
                fortify1 = -1;
                fortify2 = -1;
                JOptionPane.showMessageDialog(frame,
                    "It is now " + Players.get(turn).getName() + "'s turn.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
                while(frame.isVisible()){
                    Thread.yield();
                }
                if(Players.get(turn).ableToCash()){
                    JOptionPane.showMessageDialog(frame,
                        "You have a set to cash in!",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
                }
                while(frame.isVisible()){
                    Thread.yield();
                }
            }
        }
        if(quit){
            System.exit(0);
        }
        if(v){ //viewing cards, setting up for a potential cash-in
            view = true;
            isActive = false;
            cash1 = -1;
            cash2 = -1;
            cash3 = -1;
            cashIn = 1;
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        if (isActive == true && view == true){ //selecting cards
            if (cashIn == 4){
                cashIn = 1;
            }
            if (cashIn == 1){
                if(active != cash2 && active != cash3){
                    cash1 = active;
                    cashIn++;
                }
            }
            if (cashIn == 2){
                if(active != cash1 && active != cash3){
                    cash2 = active;
                    cashIn++;
                }
            }
            if (cashIn == 3){
                if(active != cash1 && active != cash2){
                    cash3 = active;
                    cashIn++;
                }
            }
        }
        if ((cash == true) && (place == true)){ //cashing in
            if((cash1 == -1) || (cash2 == -1) || (cash3 == -1)){
            }
            else{
                if(Players.get(turn).cashable(Cards.get(cash1), Cards.get(cash2), Cards.get(cash3))){
                    income = Players.get(turn).cash(Cards.get(cash1), Cards.get(cash2), Cards.get(cash3), Rewards, reward);
                    Cards.addAll(Players.get(turn).returnedCards());
                    reward++;
                    if (reward == 17){
                        reward = 0;
                    }
                    place = true;
                    view = false;
                }
                else{
                }
            }
        }
        if (done){ //stop viewing cards
            if (Players.get(Countries.get(select).getPossession()).getCardsN() < 5){
                view = false;
                done = false;
            }
        }
        buffer();
    }

    public void mouseMoved(MouseEvent e) {
        if(view == false){
            if (isActive){ 
                if (!Countries.get(active).giveBorder().contains(e.getX(), e.getY())) { //seeing if the mouse has left the border of a country.
                    if (isActive) {
                        isActive = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
            }
            else{
                for (int c = 0; c < 42; c++){
                    if (Countries.get(c).giveBorder().contains(e.getX(), e.getY())){ //seeing if the mouse has entered the border of a country
                        isActive = true;
                        active = c;
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        buffer();
                        return;
                    } 
                }
                if (!Fortify.contains(e.getX(), e.getY())){ //seeing if the mouse has left the fortify button
                    if (f) {
                        f = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    f = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
                if (!Attack.contains(e.getX(), e.getY())){ //... if mouse has left attack button
                    if (a) {
                        a = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    a = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
                if (!View.contains(e.getX(), e.getY())){ //... if mouse has left view button
                    if (v) {
                        v = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    v = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
                if (!End.contains(e.getX(), e.getY())){ //... if mouse has left end button
                    if (end) {
                        end = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    end = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
                if (!Quit.contains(e.getX(), e.getY())){ //... if mouse has left quit button
                    if (quit) {
                        quit = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    quit = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
            }
        }
        if (view == true){
            if (isActive){
                if (!Players.get(turn).getCards().get(active).giveBorder().contains(e.getX(), e.getY())) { //if mouse has left the border of a card
                    if (isActive) {
                        isActive = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
            }
            else{
                for (int c = 0; c < Players.get(turn).getCardsN(); c++){
                    if (Players.get(turn).getCards().get(c).giveBorder().contains(e.getX(), e.getY())){ //if mouse has entered border of a card
                        isActive = true;
                        active = c;
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        buffer();
                        return;
                    } 
                }
                if (!Done.contains(e.getX(), e.getY())){ //if mouse has left the done button
                    if (done) {
                        done = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    done = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
                if (!CashIn.contains(e.getX(), e.getY())){ //if mouse has left the cash button
                    if (cash) {
                        cash = false;
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        buffer();
                    }
                }
                else{
                    cash = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buffer();
                    return;
                }
            }
        }
    }

    public void mouseDragged(MouseEvent e){
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
        mouseMoved(e);
    }

    public void mouseEntered(MouseEvent e) {
        mouseMoved(e);
    }

    public void paint(Graphics g){
        g.drawImage(screen, 0, 0, null); //painting the buffered image
    }

    public void buffer(){
        Graphics2D g2 = screen.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //apparently, these help to make drawing more smooth
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.black);
        g2.fillRect(0, 0, dim.width, dim.height);
        if (view == false){
            g2.drawImage(map, (int)((dim.getWidth()/1920.0)*15), (int)((dim.getHeight()/1080.0)*15), null);
            g2.drawImage(actions, (int)((dim.getWidth()/1920.0)*1615), (int)((dim.getHeight()/1080.0)*15), null);
            if (isActive == true){ //drawing the highlight around countries when they're selected
                if (selected == true){
                    if (Countries.get(select).isNeighbor(Countries.get(active))){
                        g2.drawImage(Countries.get(active).getSelected(), (int)((dim.getWidth()/1920.0)*15), (int)((dim.getHeight()/1080.0)*15), null);
                    }
                    else{
                        g2.drawImage(Countries.get(select).getSelected(), (int)((dim.getWidth()/1920.0)*15), (int)((dim.getHeight()/1080.0)*15), null);
                    }
                }
                else{
                    g2.drawImage(Countries.get(active).getSelected(), (int)((dim.getWidth()/1920.0)*15), (int)((dim.getHeight()/1080.0)*15), null);
                }
            }
            else if (selected == true){
                g2.drawImage(Countries.get(select).getSelected(), (int)((dim.getWidth()/1920.0)*15), (int)((dim.getHeight()/1080.0)*15), null);
            }
            for (int c = 0; c < 42; c++){ //drawing armies and color on each country
                g2.setColor(Players.get(Countries.get(c).getPossession()).getColor());
                g2.fillOval(Countries.get(c).giveBorder().x + (Countries.get(c).giveBorder().width/2) - 10, Countries.get(c).giveBorder().y + Countries.get(c).giveBorder().height, 20, 20);
                g2.setColor(Color.white);
                g2.fillOval(Countries.get(c).giveBorder().x + (Countries.get(c).giveBorder().width/2) - 6, Countries.get(c).giveBorder().y + Countries.get(c).giveBorder().height + 4, 12, 12);
                g2.setFont(new Font("Urbana", Font.PLAIN, 12));
                g2.setColor(Color.black);
                g2.drawString(Countries.get(c).getArmies() + "", Countries.get(c).giveBorder().x + (Countries.get(c).giveBorder().width/2) - 4, Countries.get(c).giveBorder().y + Countries.get(c).giveBorder().height + 15);
            }
            g2.clearRect(0, 0, dim.width, (int)((dim.getHeight()/1080.0)*17));
            g2.clearRect(0, dim.height - (int)((dim.getHeight()/1080.0)*17), dim.width, (int)((dim.getHeight()/1080.0)*17));
            if(a){ //drawing highlight around each custom button
                g2.setColor(Color.yellow);
                g2.fillRect((int)((dim.getWidth()/1920.0)*1635), (int)((dim.getHeight()/1080.0)*350), (int)((dim.getWidth()/1920.0)*120), (int)((dim.getHeight()/1080.0)*100));
            }
            else if(v){
                g2.setColor(Color.yellow);
                g2.fillRect((int)((dim.getWidth()/1920.0)*1755), (int)((dim.getHeight()/1080.0)*350), (int)((dim.getWidth()/1920.0)*120), (int)((dim.getHeight()/1080.0)*100));
            }
            else if(f){
                g2.setColor(Color.yellow);
                g2.fillRect((int)((dim.getWidth()/1920.0)*1635), (int)((dim.getHeight()/1080.0)*450), (int)((dim.getWidth()/1920.0)*120), (int)((dim.getHeight()/1080.0)*100));
            }
            else if(end){
                g2.setColor(Color.yellow);
                g2.fillRect((int)((dim.getWidth()/1920.0)*1755), (int)((dim.getHeight()/1080.0)*450), (int)((dim.getWidth()/1920.0)*120), (int)((dim.getHeight()/1080.0)*100));
            }
            else if(quit){
                g2.setColor(Color.yellow);
                g2.fillRect((int)((dim.getWidth()/1920.0)*1635), (int)((dim.getHeight()/1080.0)*550), (int)((dim.getWidth()/1920.0)*120), (int)((dim.getHeight()/1080.0)*100));
            }
            else{
                g2.drawImage(actions, (int)((dim.getWidth()/1920.0)*1615), (int)((dim.getHeight()/1080.0)*15), null);
            }
            g2.setColor(Color.red); //drawing custom buttons
            g2.fillRect((int)((dim.getWidth()/1920.0)*1645), (int)((dim.getHeight()/1080.0)*360), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
            g2.setColor(Color.green);
            g2.fillRect((int)((dim.getWidth()/1920.0)*1765), (int)((dim.getHeight()/1080.0)*360), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
            g2.setColor(Color.blue);
            g2.fillRect((int)((dim.getWidth()/1920.0)*1645), (int)((dim.getHeight()/1080.0)*460), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
            g2.setColor(Color.cyan);
            g2.fillRect((int)((dim.getWidth()/1920.0)*1765), (int)((dim.getHeight()/1080.0)*460), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
            g2.setColor(Color.gray);
            g2.fillRect((int)((dim.getWidth()/1920.0)*1645), (int)((dim.getHeight()/1080.0)*560), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
            g2.setColor(Color.black);
            g2.setFont(new Font("Urbana", Font.PLAIN, (int)((dim.getWidth()/1920.0)*18)));
            g2.drawString("Attack", (int)((dim.getWidth()/1920.0)*1665), (int)((dim.getHeight()/1080.0)*410));
            g2.drawString("View Cards", (int)((dim.getWidth()/1920.0)*1775), (int)((dim.getHeight()/1080.0)*410));
            g2.drawString("Fortify", (int)((dim.getWidth()/1920.0)*1665), (int)((dim.getHeight()/1080.0)*510));
            g2.drawString("End turn", (int)((dim.getWidth()/1920.0)*1785), (int)((dim.getHeight()/1080.0)*510));
            g2.drawString("Quit", (int)((dim.getWidth()/1920.0)*1670), (int)((dim.getHeight()/1080.0)*610));
            g2.setFont(new Font("English", Font.PLAIN, (int)((dim.getWidth()/1920.0)*18)));
            g2.setColor(Color.black);
            if(income != 0){ //posting info onto the info box
                postInfo(Players.get(turn).getName() + ", the " + Players.get(turn).getColorName() + " player, still has to place: " + income + " armies.", g2);
            }
            else if((selected == true) && (isActive == true) && (attack == true) && (select != active) && (Countries.get(active).getPossession() != turn) && (Countries.get(select).isNeighbor(Countries.get(active)))){
                postInfo("Click to attack " + Countries.get(active).getName() + ".", g2);
            }
            else if (moving){
                postInfo("Selected " + Countries.get(select).getName() + ". You can now move troops between " + Countries.get(fortify1).getName() + " and " + Countries.get(fortify2).getName() + ". Or choose a country to attack.", g2);
            }
            else if((selected == false) && (fortify == true) && (fortify1 != -1) && (fortify2 != -1)){
                postInfo("Selected " + Countries.get(select).getName() + ". You can now fortify between " + Countries.get(fortify1).getName() + " and " + Countries.get(fortify2).getName() + " or end your turn.", g2);
            }
            else if((selected == true) && (isActive == true) && (fortify == true) && (select != active) && (Countries.get(active).getPossession() == turn) && (Countries.get(select).isNeighbor(Countries.get(active)))){
                postInfo("Selected " + Countries.get(select).getName() + ". Click to fortify to" + Countries.get(active).getName() + ".", g2);
            }
            else if((selected == true) && (fortify == true)){
                postInfo("Selected " + Countries.get(select).getName() + ". Choose a country to attack.", g2);
            }
            else if((selected == false) && (fortify == true)){
                postInfo("Select a country to fortify from.", g2);
            }
            else if((selected == true) && (attack == true)){
                postInfo("Selected " + Countries.get(select).getName() + ". Choose a country to attack.", g2);
            }
            else if((selected == false) && (attack == true)){
                postInfo("Select a country to attack from.", g2);
            }
            else if((attack == false) && (fortify == false)){
                postInfo("Choose to attack, fortify or end turn.", g2);
            }
        }
        if (view == true){
            if (Players.get(turn).getCardsN() == 0){ //drawing cards, if there are none, then draws "no cards"
                g2.setColor(Color.white);
                g2.setFont(new Font("Urbana", Font.PLAIN, 72));
                g2.drawString("You have no cards", dim.width/2 - 300, dim.height/2 + 50);
            }
            else{
                if (isActive == true){
                    g2.setColor(Color.yellow);
                    Rectangle temp = new Rectangle (Players.get(turn).getCards().get(active).giveBorder());
                    temp.grow((int)((dim.getWidth()/1920.0)*10), (int)((dim.getWidth()/1920.0)*10));
                    g2.fill(temp);
                }
                for(int c = 0; c < Players.get(turn).getCardsN(); c++){
                    if( c < 5){
                        g2.drawImage(Players.get(turn).getCards().get(c).getCard(), (int)((dim.getWidth()/1920.0)*160 + (c*350)), (int)((dim.getHeight()/1080.0)*120), null);
                        Players.get(turn).getCards().get(c).setBorder((int)((dim.getWidth()/1920.0)*160 + (c*350)), (int)((dim.getHeight()/1080.0)*120), (int)((dim.getWidth()/1920.0)*220), (int)((dim.getHeight()/1080.0)*320));
                    }
                    else{
                        g2.drawImage(Players.get(turn).getCards().get(c).getCard(), (int)((dim.getWidth()/1920.0)*160 + ((c-5)*350)), (int)((dim.getHeight()/1080.0)*540), null);
                        Players.get(turn).getCards().get(c).setBorder((int)((dim.getWidth()/1920.0)*160 + ((c-5)*350)), (int)((dim.getHeight()/1080.0)*540), (int)((dim.getWidth()/1920.0)*220), (int)((dim.getHeight()/1080.0)*320));
                    }
                }
                if (cash1 != -1){ //draws highlights around cards when they're selected
                    g2.setColor(Color.yellow);
                    Rectangle temp = new Rectangle (Players.get(turn).getCards().get(cash1).giveBorder());
                    temp.grow((int)((dim.getWidth()/1920.0)*10), (int)((dim.getWidth()/1920.0)*10));
                    g2.fill(temp);
                }
                if (cash2 != -1){
                    g2.setColor(Color.yellow);
                    Rectangle temp = new Rectangle (Players.get(turn).getCards().get(cash2).giveBorder());
                    temp.grow((int)((dim.getWidth()/1920.0)*10), (int)((dim.getWidth()/1920.0)*10));
                    g2.fill(temp);
                }
                if (cash3 != -1){
                    g2.setColor(Color.yellow);
                    Rectangle temp = new Rectangle (Players.get(turn).getCards().get(cash3).giveBorder());
                    temp.grow((int)((dim.getWidth()/1920.0)*10), (int)((dim.getWidth()/1920.0)*10));
                    g2.fill(temp);
                }
            }
            if(done){
                g2.setColor(Color.yellow);
                g2.fillRect((int)((dim.getWidth()/1920.0)*1050), (int)((dim.getHeight()/1080.0)*890), (int)((dim.getWidth()/1920.0)*120), (int)((dim.getHeight()/1080.0)*100));
            }
            else{
                g2.clearRect((int)((dim.getWidth()/1920.0)*1050), (int)((dim.getHeight()/1080.0)*890), (int)((dim.getWidth()/1920.0)*120), (int)((dim.getHeight()/1080.0)*100));
            }
            if(cash){
                g2.setColor(Color.yellow);
                g2.fillRect((int)((dim.getWidth()/1920.0)*750), (int)((dim.getHeight()/1080.0)*890), (int)((dim.getWidth()/1920.0)*120), (int)((dim.getHeight()/1080.0)*100));
            }
            else{
                g2.clearRect((int)((dim.getWidth()/1920.0)*750), (int)((dim.getHeight()/1080.0)*890), (int)((dim.getWidth()/1920.0)*120), (int)((dim.getHeight()/1080.0)*100));
            }
            g2.setColor(Color.green);
            g2.fillRect((int)((dim.getWidth()/1920.0)*760), (int)((dim.getHeight()/1080.0)*900), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
            g2.setColor(Color.red);
            g2.fillRect((int)((dim.getWidth()/1920.0)*1060), (int)((dim.getHeight()/1080.0)*900), (int)((dim.getWidth()/1920.0)*100), (int)((dim.getHeight()/1080.0)*80));
            g2.setColor(Color.black);
            g2.setFont(new Font("Urbana", Font.PLAIN, (int)((dim.getWidth()/1920.0)*20)));
            g2.drawString("Cash In", (int)((dim.getWidth()/1920.0)*780), (int)((dim.getHeight()/1080.0)*950));
            g2.drawString("Done", (int)((dim.getWidth()/1920.0)*1080), (int)((dim.getHeight()/1080.0)*950));
        }
        repaint();
    }

    public static BufferedImage resize(Image image, int width, int height) { //code found from internet. supposedly resizes images
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, //don't know
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g.setRenderingHint(RenderingHints.KEY_RENDERING, //don't know
            RenderingHints.VALUE_RENDER_QUALITY); 

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, //don't know
            RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    } 

    public void postInfo(String a, Graphics2D b){ //custom method made to cut a string into lines so that they fit into the info box
        int Line2 = 0;
        int Line3 = 0;
        int Line4 = 0;
        int Line5 = 0;
        b.setFont(new Font("English", Font.PLAIN, (int)((dim.getWidth()/1920.0)*24)));
        b.setColor(Color.black);
        if(a.length() > 80){
            for(int c = a.length() - 1 ; c != 0; c--){
                if(c == Line2 - 1){
                    b.drawString(a.substring(0, Line2), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*815));
                    break;
                }
                else if((a.charAt(c) == (' ')) && c <= 20 && Line2 == 0){
                    b.drawString(a.substring(c, Line3), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*835));
                    Line2 = c;
                }
                else if((a.charAt(c) == (' ')) && c <= 40 && Line3 == 0){
                    b.drawString(a.substring(c, Line4), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*855));
                    Line3 = c;
                }
                else if((a.charAt(c) == (' ')) && c < 60 && Line4 == 0){
                    b.drawString(a.substring(c), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*875));
                    Line4 = c;
                }
                else if((a.charAt(c) == (' ')) && c < 80 && Line5 == 0){
                    b.drawString(a.substring(c), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*875));
                    Line4 = c;
                }
            }
        }
        else if(a.length() > 60){
            for(int c = a.length() - 1 ; c != 0; c--){
                if(c == Line2 - 1){
                    b.drawString(a.substring(0, Line2), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*815));
                    break;
                }
                else if((a.charAt(c) == (' ')) && c <= 20 && Line2 == 0){
                    b.drawString(a.substring(c, Line3), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*835));
                    Line2 = c;
                }
                else if((a.charAt(c) == (' ')) && c <= 40 && Line3 == 0){
                    b.drawString(a.substring(c, Line4), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*855));
                    Line3 = c;
                }
                else if((a.charAt(c) == (' ')) && c < 60 && Line4 == 0){
                    b.drawString(a.substring(c), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*875));
                    Line4 = c;
                }
            }
        }
        else if(a.length() > 40){
            for(int c = a.length() - 1; c != 0; c--){
                if(c == Line2 - 1){
                    b.drawString(a.substring(0, Line2), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*815));
                    break;
                }
                else if((a.charAt(c) == (' ')) && c <= 20 && Line2 == 0){
                    b.drawString(a.substring(c, Line3), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*835));
                    Line2 = c;
                }
                else if((a.charAt(c) == (' ')) && c < 40 && Line3 == 0){
                    b.drawString(a.substring(c), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*855));
                    Line3 = c;
                }
            }
        }
        else if(a.length() > 20){
            for(int c = a.length() - 1; c != 0; c--){
                if(c == Line2 - 1){
                    b.drawString(a.substring(0, Line2), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*815));
                    break;
                }
                else if((a.charAt(c) == ' ') && c < 20 && Line2 == 0){
                    b.drawString(a.substring(c), (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*835));
                    Line2 = c;
                }
            }
        }
        else if(a.length() <= 20){
            b.drawString(a, (int)((dim.getWidth()/1920.0)*1675), (int)((dim.getHeight()/1080.0)*815));
        }
    }

    public static void main (String [] args){ //starts the game
        RiskGame a = new RiskGame();
    }
}