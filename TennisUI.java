import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
public class TennisUI {
    public static UI.Panel playerstatus(UI ui, int energy, int energy2) {
        UI.Panel win = ui.makePanel(0, 0, 40, 3);
        ui.refresh();
        ui.setString(1, 1, "PLayer 1: " + energy);
        ui.setString(25, 1, "Player 2: " + energy2);
        ui.refresh();
        return win;
    }
    public static UI.Panel deletePlayerStatus(UI ui) {
        UI.Panel win = ui.makePanel(0, 0, 40, 3);
        ui.refresh();
        ui.setString(1, 1, "                                                          " );
        ui.setString(25, 1, "            ");
        ui.refresh();
        return win;
    }
    //makes the energy bar at the top
    public static void writeStr(UI ui, int c, int r, String str) {
        ui.setString(c, r, str);
        ui.refresh();
    }
    //just if you want to make a string anywhere
    public static void setPlayer1(UI ui, String name) {
        ui.setString(0, 48, name);
        ui.refresh();
    }
    //sets player 1 so the user knows who's turn it is
    public static void setPlayer2(UI ui, String name) {
        ui.setString(0, 48, name);
        ui.refresh();
    }
    //set player 2 so the user knows who's turn it is
    public static void baseplayerplace1(UI ui) {
        ui.setString(18, 4, "X", TextColor.ANSI.CYAN_BRIGHT);
        ui.refresh();
    }
    //sets the player 1 X at the base level top of the court
    public static void baseplayerplace2(UI ui) {
        ui.setString(18, 43, "X", TextColor.ANSI.RED_BRIGHT);
        ui.refresh();
    }
    //sets the player 2 X at the base level bottom of the court
    public static void deletebaselocation1(UI ui) {
        ui.setString(18, 4, " ", TextColor.ANSI.CYAN_BRIGHT);
        ui.refresh();
    }
    //deletes the player 1 X from the base level top of the court
    public static void deletebaselocation2(UI ui) {
        ui.setString(18, 43, " ", TextColor.ANSI.RED_BRIGHT);
        ui.refresh();
    }
    //deletes the player 2 X from the base level bottom of the court
    public static void deletplayerlocation2(UI ui, int box, char hand, int w, int h) {
        if (hand == 'd') {
            if (box == 4) {
                ui.setString((w/8), (h/8) + 7 + h/2, "  ", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 3) {
                ui.setString((w/8) + (w/4), (h/8) + 7 + h/2, " ", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 2) {
                ui.setString((w/8) + (w/2), (h/8) + 7 + h/2, " ", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 1) {
                ui.setString((w/8) + (3*w)/4, (h/8) + 7 + h/2, " ", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
        }
        else if (hand == 't') {
            if (box == 4) {
                ui.setString((w/8), (h/8) + 7 + (3*h)/4, " ", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 3) {
                ui.setString((w/8) + (w/4), (h/8) + 7 + (3*h)/4, " ", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 2) {
                ui.setString((w/8) + (w/2), (h/8) + 7 + (3*h)/4, " ", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 1) {
                ui.setString((w/8) + (3*w)/4, (h/8) + 7 + (3*h)/4, " ", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
        }
    }
    //deletes player 2 from where ever he is located, so you can move him
    //use this before you set player location again
    public static void deleteplayerlocation1(UI ui, int box, char hand, int w, int h) {
        if (hand == 't') {
            if (box == 1) {
                ui.setString((w/8), (h/8) + 7, " ", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 2) {
                ui.setString((w/8) + (w/4), (h/8) + 7, " ", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 3) {
                ui.setString((w/8) + (w/2), (h/8) + 7, " ", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 4) {
                ui.setString((w/8) + (3*w)/4, (h/8) + 7, " ", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
        }
        else if (hand == 'd') {
            if (box == 1) {
                ui.setString((w/8), (h/8) + 7 + h/4, " ", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 2) {
                ui.setString((w/8) + (w/4), (h/8) + 7 + h/4, " ", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 3) {
                ui.setString((w/8) + (w/2), (h/8) + 7 + h/4, " ", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 4) {
                ui.setString((w/8) + (3*w)/4, (h/8) + 7 + h/4, " ", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
        }
    }
    //deletes player 1 from where ever he is located, so you can move him
    //use this before you set player location again
    public static void changeplayerlocation1(UI ui, int box, char hand, int w, int h) {
        if (hand == 't') {
            if (box == 1) {
                ui.setString((w/8), (h/8) + 7, "X", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 2) {
                ui.setString((w/8) + (w/4), (h/8) + 7, "X", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 3) {
                ui.setString((w/8) + (w/2), (h/8) + 7, "X", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 4) {
                ui.setString((w/8) + (3*w)/4, (h/8) + 7, "X", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
        }
        else if (hand == 'd') {
            if (box == 1) {
                ui.setString((w/8), (h/8) + 7 + h/4, "X", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 2) {
                ui.setString((w/8) + (w/4), (h/8) + 7 + h/4, "X", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 3) {
                ui.setString((w/8) + (w/2), (h/8) + 7 + h/4, "X", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
            else if (box == 4) {
                ui.setString((w/8) + (3*w)/4, (h/8) + 7 + h/4, "X", TextColor.ANSI.CYAN_BRIGHT);
                ui.refresh();
            }
        }
    }
    //use this to set player 1 location with which box, and top of bottom, and the w/h are the w/h of the box
    public static void changeplayerlocation2(UI ui, int box, char hand, int w, int h) {
        if (hand == 'd') {
            if (box == 4) {
                ui.setString((w/8), (h/8) + 7 + h/2, "X", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 3) {
                ui.setString((w/8) + (w/4), (h/8) + 7 + h/2, "X", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 2) {
                ui.setString((w/8) + (w/2), (h/8) + 7 + h/2, "X", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 1) {
                ui.setString((w/8) + (3*w)/4, (h/8) + 7 + h/2, "X", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
        }
        else if (hand == 't') {
            if (box == 4) {
                ui.setString((w/8), (h/8) + 7 + (3*h)/4, "X", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 3) {
                ui.setString((w/8) + (w/4), (h/8) + 7 + (3*h)/4, "X", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 2) {
                ui.setString((w/8) + (w/2), (h/8) + 7 + (3*h)/4, "X", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
            else if (box == 1) {
                ui.setString((w/8) + (3*w)/4, (h/8) + 7 + (3*h)/4, "X", TextColor.ANSI.RED_BRIGHT);
                ui.refresh();
            }
        }
    }
    //use this to set player 2 location with which box, and top of bottom, and the w/h are the w/h of the box
    public static void placeO (UI ui, int player, int box, char hand, int w, int h) {
        if (player == 1) {
            if (hand == 't') {
                if (box == 1) {
                    ui.setString((w/8), (h/8) + 7, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 2) {
                    ui.setString((w/8) + (w/4), (h/8) + 7, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 3) {
                    ui.setString((w/8) + (w/2), (h/8) + 7, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 4) {
                    ui.setString((w/8) + (3*w)/4, (h/8) + 7, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
            }
            else if (hand == 'd') {
                if (box == 1) {
                    ui.setString((w/8), (h/8) + 7 + h/4, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 2) {
                    ui.setString((w/8) + (w/4), (h/8) + 7 + h/4, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 3) {
                    ui.setString((w/8) + (w/2), (h/8) + 7 + h/4, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 4) {
                    ui.setString((w/8) + (3*w)/4, (h/8) + 7 + h/4, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
            }
        }
        else if (player == 2) {
            if (hand == 'd') {
                if (box == 4) {
                    ui.setString((w/8), (h/8) + 7 + h/2, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 3) {
                    ui.setString((w/8) + (w/4), (h/8) + 7 + h/2, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 2) {
                    ui.setString((w/8) + (w/2), (h/8) + 7 + h/2, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 1) {
                    ui.setString((w/8) + (3*w)/4, (h/8) + 7 + h/2, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
            }
            else if (hand == 't') {
                if (box == 4) {
                    ui.setString((w/8), (h/8) + 7 + (3*h)/4, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 3) {
                    ui.setString((w/8) + (w/4), (h/8) + 7 + (3*h)/4, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 2) {
                    ui.setString((w/8) + (w/2), (h/8) + 7 + (3*h)/4, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
                else if (box == 1) {
                    ui.setString((w/8) + (3*w)/4, (h/8) + 7 + (3*h)/4, "O", TextColor.ANSI.GREEN_BRIGHT);
                    ui.refresh();
                }
            }
        }
    }
    //use this to place the ball, player would indicate whos side the ball will end up in
    public static int askPlacementshot (UI ui, int c, int r) {
        String placement = ui.input("Where are you aiming (1-4): ", c, r);
        int place = Integer.parseInt(placement);
        ui.refresh();
        return place;
    }
    //gives the box of where the player wants to shoot
    public static char askTypeShot(UI ui, int c, int r) {
        String shottype = ui.input("You have two options t - topspin d - drop shot : ", c, r);
        if (shottype.charAt(0) == 't') {
            ui.setString(c, r + 1, "t");
            ui.refresh();
            return 't';
        }
        else if (shottype.charAt(0) == 'd') {
            ui.refresh();
            return 'd';
        }
        ui.refresh();
        return ' ';
    }
    //gives the type of shot the player wants to use
    public static void getInfo() {

    }
    public static int getPowerShot(UI ui, int c, int r) {
        String powerShot = ui.input("Power 1 - 100 : ", c, r);
        int power = Integer.parseInt(powerShot);
        ui.refresh();
        return power;

    }
    //gets the power of shot
    public static String getName1(UI ui, int c, int r) {
        String name = ui.input("Enter your name player 1: ", c, r);
        ui.refresh();
        return name;
    }
    public static String getName2(UI ui, int c, int r) {
        String name2 = ui.input("Enter your name player 2: ", c, r);
        ui.refresh();
        return name2;
    }
    //gets the name of both player 1 and 2
    public static UI.Panel makeCourt(UI ui, int c, int r, int w, int h) {
        UI.Panel win = ui.makePanel(c, r, w, h);
        win.canvas.drawLine(new TerminalPosition(w/2, 1), new TerminalPosition(w/2, h-2), '\u2502');
        win.canvas.drawLine(new TerminalPosition(w/4, 1), new TerminalPosition(w/4, h-2), '\u2502');
        win.canvas.drawLine(new TerminalPosition((3*w)/4, 1), new TerminalPosition((3*w)/4, h-2), '\u2502');
        win.canvas.drawLine(new TerminalPosition(1, h/4), new TerminalPosition((h*2)-2, h/4), '\u2500');
        win.canvas.drawLine(new TerminalPosition(1, (3*h)/4), new TerminalPosition((h*2)-2, (3*h)/4), '\u2500');
        win.canvas.setForegroundColor(TextColor.ANSI.BLUE_BRIGHT);
        win.canvas.drawLine(new TerminalPosition(1, h/2), new TerminalPosition((h*2)-2, h/2), '\u2500');
        win.canvas.setForegroundColor(TextColor.ANSI.WHITE);

        ui.setString(1, r + 1, "1");
        ui.setString(1 + (w/4), r + 1, "2");
        ui.setString(1 + (w/2), r + 1, "3");
        ui.setString(1 + ((3*w)/4), r + 1, "4");

        ui.setString(1, r + 9, "1");
        ui.setString(1 + (w/4), r + 9, "2");
        ui.setString(1 + (w/2), r + 9, "3");
        ui.setString(1 + ((3*w)/4), r + 9, "4");

        ui.setString(1, r + 18, "4");
        ui.setString(1 + (w/4), r + 18, "3");
        ui.setString(1 + (w/2), r + 18, "2");
        ui.setString(1 + ((3*w)/4), r + 18, "1");

        ui.setString(1, r + 27, "4");
        ui.setString(1 + (w/4), r + 27, "3");
        ui.setString(1 + (w/2), r + 27, "2");
        ui.setString(1 + ((3*w)/4), r + 27, "1");

        ui.setString(0, r, "PLAYER 1");
        ui.setString(0, (h + r) - 1, "PLAYER 2");
        ui.refresh();
        return win;
    }
    //makes the court
    public static void clearRow(UI ui, int c, int r) {
        ui.setString(c, r, "                                                          ");
        ui.refresh();
    }
    //clears a whole line, watch use in main
    //Use it to reset the word frame, using a while loop
    //THIS IS NEW I DID IT TODAY, WHILE FINDIG ERRORS
    //MAKE SURE TO RUN THIS BEFORE YOU TRY PRINTING SOMETHING OUT
    public static void main(String[] args) {
        UI ui = new UI(100, 100);
        UI.Panel w1 = makeCourt(ui, 0, 7, 40, 35);
        w1.close();
        UI.Panel w2 = playerstatus(ui, 100, 100);
        w2.close();
        baseplayerplace1(ui);
        baseplayerplace2(ui);
        setPlayer1(ui, "Kesshavan");
        char ts = askTypeShot(ui, 0, 49);
        int ps = getPowerShot(ui, 0, 50);
        int wp = askPlacementshot(ui, 0, 51);
        int energy1 = 100 - ps;
        UI.Panel w5 = deletePlayerStatus(ui);
        w5.close();
        UI.Panel w3 = playerstatus(ui, energy1, 100);
        w3.close();
        placeO(ui, 2, wp, ts, 40, 35);
        ui.waitForKey('k');
        changeplayerlocation2(ui, wp, ts, 40, 35);
        deletebaselocation2(ui);
        int count = 0;
        while (count < 4) {
            clearRow(ui, 0, 48 + count);
            count += 1;
        }
        setPlayer2(ui, "JOE");
        char ts2 = askTypeShot(ui, 0, 49);
        int ps2 = getPowerShot(ui, 0, 50);
        int wp2 = askPlacementshot(ui, 0, 51);
        int energy2 = 100 - ps2;
        UI.Panel w6 = deletePlayerStatus(ui);
        w6.close();
        UI.Panel w4 = playerstatus(ui, energy1, energy2);
        w4.close();
        placeO(ui, 1, wp2, ts2, 40, 35);
        ui.waitForKey('k');
        changeplayerlocation1(ui, wp2, ts2, 40, 35);
        deletebaselocation1(ui);
        deletplayerlocation2(ui, wp, ts, 40, 35);
        ui.refresh();
        baseplayerplace2(ui);
        deletplayerlocation2(ui, wp, ts, 40, 35);
        ui.refresh();
        ui.waitForKey('k');
        ui.clear();
    }
}