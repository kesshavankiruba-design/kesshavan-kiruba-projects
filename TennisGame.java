import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TennisGame {
    //Field
    private TennisPlayer p1;
    private TennisPlayer p2;
    private TennisPhysics physics;

    private TennisCourtUI courtUI;
    private UI ui;
    private TennisAudio audio;
    private UI.Panel courtPanel;
    private UI.Panel statusPanel;

    private int currentTurn;
    private int pointServer;
    private boolean pointActive;

    private final int COURT_W = 40;
    private final int COURT_H = 35;

    // Visual location state tracking to prevent ghost characters
    private boolean p1AtBase;
    private boolean p2AtBase;
    private int p1LastBox;
    private char p1LastType;
    private int p2LastBox;
    private char p2LastType;


    private final int pointsToWinSet = 2; //each set is best of 3 (first to 2 points)
    private final int setsToWinGame = 2; //each match is besst of 3 sets (first to win 2 sets)

    public TennisGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        physics = new TennisPhysics();
        courtUI = new TennisCourtUI();
        audio = new TennisAudio();
    }



    public void StartGameLoop() {
        this.ui = new UI(100, 100);

        courtPanel = courtUI.makeCourt(ui, 0, 7, 40, 35);
        statusPanel = courtUI.playerstatus(ui, 100, 100);
        
        courtUI.baseplayerplace1(ui);
        courtUI.baseplayerplace2(ui);

        courtUI.writeMsg(ui, 0, 48, "Welcome to Tennis Game");

        String name1 = courtUI.getName1(ui, 0, 49);
        String name2 = courtUI.getName2(ui, 0, 50);

        ui.wait(500);
        courtUI.clearPanel(ui);

        p1 = new TennisPlayer(name1, 100.0, 2, 't', 50);
        p2 = new TennisPlayer(name2, 100.0, 2, 't', 50);
        

        /* 
        courtUI.writeMsg(ui, 0, 48, "Bonus Objective Available!");
        courtUI.writeMsg(ui, 0, 49, "Would you like to take a quick Tennis Trivia Quiz for extra starting energy?");
        
        
        char playQuizChoice = courtUI.askTypeShot(ui, 0, 51); //just use this to get the player's input
        
        if (playQuizChoice == 'y' || playQuizChoice == 'Y') {
            TennisQuiz quiz = new TennisQuiz();
            quiz.runQuiz(ui, p1, p2); // Passes the central UI frame and player instances

        } else {
            courtUI.writeMsg(ui, 0, 50, "Alright, let's jump into the game!");
            ui.wait(1000);
        }
        */
        
        courtUI.clearPanel(ui);

        Random rand = new Random();
        pointServer = rand.nextBoolean() ? 1 : 2; //determine which player serves first
        currentTurn = pointServer;

        TennisPlayer server = (pointServer == 1) ? p1 : p2; //check server, basically 
        // if currenturn is 1 then server is 1
        courtUI.writeMsg(ui, 0, 49, server.getName() + " won the coin toss and will serve first!");

        courtUI.baseplayerplace1(ui);
        courtUI.baseplayerplace2(ui);

        ui.wait(500);

        //Match Level: Check who wins the match (first to win 3 sets)
        while (p1.getSetsWon() < setsToWinGame && p2.getSetsWon() < setsToWinGame) {
            if (p1.getSetsWon() == 0 && p2.getSetsWon() == 0) {
                courtUI.writeMsg(ui, 0, 50, "=================== NEW GAME STARTING ===================");
            }
            ui.wait(500);

            courtUI.writeMsg(ui, 0, 51, "=================== NEW SET STARTING ===================");
            ui.wait(500);

            p1.resetPointsWon();
            p2.resetPointsWon();
            courtUI.writeMsg(ui, 0, 52, String.format("==================== STARTING SET %d ===================", (p1.getSetsWon() + p2.getSetsWon() + 1)));
            ui.wait(500);
            //Ex. Starting Set 1 when both player have not won any sets
            
            ui.wait(1500);


            //Set Level: Check who wins the set (first to win 4 points)
            while (!checkSetWinner()) {
                courtUI.writeMsg(ui, 0, 53, String.format("--- Point %d ---", (p1.getPointsWon() + p2.getPointsWon() + 1)));
                ui.wait(1200);
                courtUI.clearPanel(ui);

                pointActive = true;

                //Point Level: play a single point
                playPoint();
                

                ui.wait(600);
                courtUI.clearPanel(ui);

                p1.rest();
                p2.rest();
                courtUI.deletePlayerStatus(ui);
                courtUI.playerstatus(ui, p1.getEnergy(), p2.getEnergy());
                courtUI.writeMsg(ui, 0, 48, "Changeover: Players rested and recovered 10 energy.");
                courtUI.writeMsg(ui, 0, 49, String.format("Current Set Score: %s [%d] - [%d] %s", 
                p1.getName(), p1.getPointsWon(), p2.getPointsWon(), p2.getName()));

                courtUI.deleteplayerlocation1(ui, p1LastBox, p1LastType, COURT_W, COURT_H);
                courtUI.deleteplayerlocation2(ui, p1LastBox, p1LastType, COURT_W, COURT_H);
                courtUI.deletebaselocation1(ui);
                courtUI.deletebaselocation2(ui);
                
                courtUI.baseplayerplace1(ui);
                courtUI.baseplayerplace2(ui);
                ui.wait(2000);
                courtUI.clearPanel(ui);

                }

                courtUI.writeMsg(ui, 0, 51, String.format("Current Game Set Score: %s (%d) - (%d) %s", 
                   p1.getName(), p1.getSetsWon(), p2.getSetsWon(), p2.getName()));
                ui.wait(2500);
                courtUI.clearPanel(ui);
        }

        courtUI.clearPanel(ui);
        TennisPlayer matchWinner = (p1.getSetsWon() >= setsToWinGame) ? p1 : p2;
        courtUI.writeMsg(ui, 0, 48, "================================================");
        courtUI.writeMsg(ui, 0, 49, "MATCH OVER! " + matchWinner.getName().toUpperCase() + " IS THE CHAMPION!");
        courtUI.writeMsg(ui, 0, 50, "========================================================");

        courtPanel.close();
        statusPanel.close();
        
    }  


    public void playPoint() {
        p1AtBase = true;
        p2AtBase = true;
        p1LastBox = 0;
        p1LastType = ' ';
        p2LastBox = 0;
        p2LastType = ' ';

        currentTurn = pointServer;

        courtUI.writeMsg(ui, 0, 48, "The ball is hit into play!");
        ui.wait(1000);

        
        while (pointActive) {
            processTurn();
        }

        pointServer = (pointServer == 1) ? 2 : 1;
    }

    
    public void processTurn() {
        courtUI.clearPanel(ui);

        TennisPlayer curPlayer = (currentTurn == 1) ? p1 : p2;
        TennisPlayer prevPlayer = (currentTurn == 1) ? p2 : p1;

        courtUI.writeMsg(ui, 0, 48, "Active Player: " + curPlayer.getName());

        courtUI.writeMsg(ui, 0, 49, curPlayer.getName() + "'s shot selection:");

        char shotType = courtUI.askTypeShot(ui, 0, 50);
        int placement = courtUI.askPlacementshot(ui, 0, 51);
        int power = courtUI.getPowerShot(ui, 0, 52);


        curPlayer.setShotType(shotType);
        curPlayer.setPlacement(placement);
        curPlayer.setPower(power);

        double totalChance = physics.finalChance(curPlayer, prevPlayer);
        courtUI.writeMsg(ui, 0, 53, String.format("Accuracy odds: %.1f%%.", totalChance));

        boolean isGood = physics.ballIsIn(totalChance);
        curPlayer.drainEnergy(curPlayer.getPower());
        courtUI.writeMsg(ui, 0, 54, String.format("%s's remaining energy: %.1f%%", curPlayer.getName(), curPlayer.getEnergy()));
        audio.playHit(curPlayer.getType());


        boolean wasAtBase = (currentTurn == 1) ? p1AtBase : p2AtBase;
        char oldShotType  = (currentTurn == 1) ? p1LastType : p2LastType;
        int oldPlacement  = (currentTurn == 1) ? p1LastBox : p2LastBox;

        if (isGood) {
            courtUI.writeMsg(ui, 0, 55, "Inside lines! Good shot by " + curPlayer.getName() + ".");
            courtUI.writeMsg(ui, 0, 56, "Incoming Ball! Type 'k' then hit Enter to try and save it...");
            

            wasAtBase = (currentTurn == 1) ? p1AtBase : p2AtBase;
            oldShotType  = (currentTurn == 1) ? p1LastType : p2LastType;
            oldPlacement  = (currentTurn == 1) ? p1LastBox : p2LastBox;
            
            courtUI.updateCourt(ui, currentTurn, oldShotType, wasAtBase, oldPlacement, shotType, placement, (int) p1.getEnergy(), (int) p2.getEnergy(), COURT_W, COURT_H);
            courtUI.deletePlayerStatus(ui);
            courtUI.playerstatus(ui, p1.getEnergy(), p2.getEnergy());
            ui.refreshScreen();

            ui.waitForKey('\n');
            courtUI.clearPanel(ui);

            if (currentTurn == 1) {
                p1AtBase = false;
                p1LastType = shotType;
                p1LastBox = placement;
                currentTurn = 2;

            } else {
                p2AtBase = false;
                p2LastType = shotType;
                p2LastBox = placement;
                currentTurn = 1;
            }

        } else {
            courtUI.writeMsg(ui, 0, 55, "Out! " + curPlayer.getName() + " missed the shot.");
            ui.wait(1000);
            audio.playLose();
            ui.wait(2000);
            
            if (currentTurn == 1) {
                p2.addPointsWon();
                courtUI.writeMsg(ui, 0, 56, "Point awarded to " + p2.getName() + "!");
                audio.playWin();

            } else {
                p1.addPointsWon();
                courtUI.writeMsg(ui, 0, 56, "Point awarded to " + p1.getName() + "!");
                audio.playWin();
            }
            pointActive = false;
            courtUI.writeMsg(ui, 0, 57, "Press Enter to continue:");
            ui.waitForKey('\n');
            
            
            courtUI.deleteplayerlocation1(ui, oldPlacement, oldShotType, COURT_W, COURT_H);
            courtUI.deleteplayerlocation2(ui, oldPlacement, oldShotType, COURT_W, COURT_H);
            
            
            
        }
        
    }
    

    

    public boolean checkSetWinner() {
        if (p1.getPointsWon() >= pointsToWinSet) {
            p1.addSetsWon();
            courtUI.writeMsg(ui, 0, 50, p1.getName() + " wins the set!");
            return true;
        } else if (p2.getPointsWon() >= pointsToWinSet) {
            p2.addSetsWon();
            courtUI.writeMsg(ui, 0, 50, p2.getName() + " wins the set!");
            return true;
        }
        return false;
    }

}
