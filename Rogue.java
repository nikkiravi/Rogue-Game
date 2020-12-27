package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

//Agenda: 
//1) Retrieve ActionInt Value Element (For Hallucinate)
//2) When a victim creature, either the player or a monster, is hit, all hitActions associated with the victim creature are performed by that creature
//3) DeathAction for player and monster
//4) All the other key inputs in the pdf

public class Rogue implements Runnable {

    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = null;
    private Thread keyStrokePrinter;
    private int game_width;
    private int game_height;
    private int width;
    private int height;
    private int room_id;
    private ArrayList<Integer> posX;
    private ArrayList<Integer> posY;
    private char type = '@';
    private int Hp;
    private static int topHeight;
    private int bottomHeight;
    private int displayHeight;
    // private static Player currPlayer;
    private static ArrayList<Creature> creatures;
    private static ArrayList<Displayable> roomsreq;
    public char ch;
    private ArrayList<ArrayList<Displayable>> list;
    private ArrayList<Displayable> subList;
    private static Room player_room;

    public Rogue(Dungeon dungeon) {
        list = dungeon.getList();
        game_height = dungeon.get_gameHeight();
        game_width = dungeon.get_width();
        topHeight = dungeon.gettopheight();
        bottomHeight = dungeon.getBottomheight();
        displayHeight = game_height + topHeight + bottomHeight;
        displayGrid = new ObjectDisplayGrid(game_width, displayHeight);
    }

    // public static Player getp1() {
    // System.out.println(currPlayer);
    // return currPlayer;
    // }

    @Override
    public void run() {

        //displayGrid.fireUp();
        // 0 - rooms, 1 - creatues, 2 - items, 3 - passages
        // Monsters = Trolls: T, Snakes: S, Hob: H,
        // player: @
        // rooms: Walls - X and Floor - .,
        // Passages - #,
        // Connection between Passage and Room - +,
        // list[0] = rooms
        // list[1] = creature
        // list[2] = items
        // list[3] = passage
        displayGrid.initializeDisplay();

        //Top Display
        displayGrid.addObjectToDisplay(new Char('H'), 0,0);
        displayGrid.addObjectToDisplay(new Char('P'), 1,0);
        displayGrid.addObjectToDisplay(new Char(':'), 2,0);

        displayGrid.addObjectToDisplay(new Char('S'), 7,0);
        displayGrid.addObjectToDisplay(new Char('c'), 8,0);
        displayGrid.addObjectToDisplay(new Char('o'), 9,0);
        displayGrid.addObjectToDisplay(new Char('r'), 10,0);
        displayGrid.addObjectToDisplay(new Char('e'), 11,0);
        displayGrid.addObjectToDisplay(new Char(':'), 12,0);
        displayGrid.addObjectToDisplay(new Char('0'), 13,0);

        //Bottom Display
        //displayGrid.addObjectToDisplay(new Char('P'), 0,displayHeight - bottomHeight - 1);
        //displayGrid.addObjectToDisplay(new Char('a'), 1,displayHeight - bottomHeight - 1);
        //displayGrid.addObjectToDisplay(new Char('c'), 2,displayHeight - bottomHeight - 1);
        //displayGrid.addObjectToDisplay(new Char('k'), 3,displayHeight - bottomHeight - 1);
        //displayGrid.addObjectToDisplay(new Char(':'), 4,displayHeight - bottomHeight - 1);

        displayGrid.addObjectToDisplay(new Char('I'), 0, displayHeight - 1);
        displayGrid.addObjectToDisplay(new Char('n'), 1, displayHeight - 1);
        displayGrid.addObjectToDisplay(new Char('f'), 2, displayHeight - 1);
        displayGrid.addObjectToDisplay(new Char('o'), 3, displayHeight - 1);
        displayGrid.addObjectToDisplay(new Char(':'), 4, displayHeight - 1);

        //Drawing the stuff
        for (int i = 0; i < list.size(); i++) {
            subList = list.get(i);
            for (int j = 0; j < subList.size(); j++) {

                width = subList.get(j).getWidth();
                height = subList.get(j).getHeight();
                posX = subList.get(j).getPosX();
                posY = subList.get(j).getPosY();
                type = subList.get(j).getType();
                room_id = subList.get(j).getRoomID();

                if (i == 0) {
                    int l = 0;
                    for (int y = posY.get(0); y < posY.get(0) + height; y++) {
                        for (l = posX.get(0) + 1; l < posX.get(0) + width - 1; l++) {
                            displayGrid.addObjectToDisplay(new Char('.'), l, y + topHeight);
                        }

                        displayGrid.addObjectToDisplay(new Char('X'), posX.get(0), y + topHeight);
                        displayGrid.addObjectToDisplay(new Char('X'), posX.get(0) + width - 1, y + topHeight);
                    }
                    for (int x = posX.get(0); x < posX.get(0) + width; x++) {
                        displayGrid.addObjectToDisplay(new Char('X'), x, posY.get(0) + topHeight);
                        displayGrid.addObjectToDisplay(new Char('X'), x, posY.get(0) + height - 1 + topHeight);
                    }
                }

                else if (i == 1) {

                    int relativeY = -1;
                    int relativeX = -1;

                    for (int id = 1; id <= list.get(0).size(); id++) {
                        if (room_id == id) {
                            relativeX = list.get(0).get(id - 1).getPosX().get(0) + posX.get(0);
                            relativeY = list.get(0).get(id - 1).getPosY().get(0) + posY.get(0);
                        }
                    }

                    if (type == 'T' | type == 'S' | type == 'H') {
                        // System.out.println("Monster: PosX: " + posX.get(0) + ", PosY: " +
                        // posY.get(0));
                        // id = subList.get(j).getRoomID()
                        // if(id = 1): list[0].sublist[0].posX and list[0].subList[0].posY

                        displayGrid.addObjectToDisplay(new Char(type), relativeX, relativeY + topHeight);
                        Monster monster = (Monster) subList.get(j);
                        monster.setstartingX(relativeX);
                        monster.setstartingY(relativeY + topHeight);

                        //int monX = monster.getstartingX();
                        //int monY = monster.getstartingY();
                    } // Monster

                    else {
                        // System.out.println("Player: PosX: " + posX.get(0) + ", PosY: " +
                        // posY.get(0));
                        displayGrid.addObjectToDisplay(new Char('@'), relativeX, relativeY + topHeight);
                        Hp = subList.get(j).getHp(); //20
                        String nums = Integer.toString(Hp);
                        int ind = 3;
                        for(char h : nums.toCharArray()) {
                            displayGrid.addObjectToDisplay(new Char(h), ind,0);
                            ind++;
                        }
                    } // Player
                }

                else if (i == 2) {
                    if (posX.size() != 0 & posY.size() != 0) {

                        int relativeY = -1;
                        int relativeX = -1;

                        for (int id = 1; id <= list.get(0).size(); id++) {
                            if (room_id == id) {
                                relativeX = list.get(0).get(id - 1).getPosX().get(0) + posX.get(0);
                                relativeY = list.get(0).get(id - 1).getPosY().get(0) + posY.get(0);
                            }
                        }

                        if (subList.get(j) instanceof Scroll) {
                            if (displayGrid.getObjectGrid()[relativeX][relativeY + topHeight].peek().getChar() != 'X'){

                                displayGrid.addObjectToDisplay(new Char('?'), relativeX, relativeY + topHeight);
                                Item item = (Item) subList.get(j);
                                item.SetPosX(relativeX);
                                item.SetPosY(relativeY + topHeight);
                            }
                        }

                        else if (subList.get(j) instanceof Sword) {
                            if (displayGrid.getObjectGrid()[relativeX][relativeY + topHeight].peek().getChar() != 'X'){

                                displayGrid.addObjectToDisplay(new Char('|'), relativeX, relativeY + topHeight);
                                Item item = (Item) subList.get(j);
                                item.SetPosX(relativeX);
                                item.SetPosY(relativeY + topHeight);
                            }
                        }

                        else if (subList.get(j) instanceof Armor) {
                            if (displayGrid.getObjectGrid()[relativeX][relativeY + topHeight].peek().getChar() != 'X'){

                                displayGrid.addObjectToDisplay(new Char(']'), relativeX, relativeY + topHeight);
                                Item item = (Item) subList.get(j);
                                item.SetPosX(relativeX);
                                item.SetPosY(relativeY + topHeight);
                            }
                        }
                    }
                }

                else if (i == 3) {
                    for (int k = 0; k < posX.size(); k++) {
                        displayGrid.addObjectToDisplay(new Char('+'), posX.get(k), posY.get(k) + topHeight);

                        int currX = posX.get(k);
                        int currY = posY.get(k) + topHeight;
                        int nextX = -1;
                        int nextY = -1;

                        if (k < posX.size() - 1) {
                            nextX = posX.get(k + 1);
                            nextY = posY.get(k + 1) + topHeight;
                        }

                        int relativeY = nextY - currY;
                        int relativeX = nextX - currX;

                        if (relativeX == 0 & relativeY > 0) {
                            for (int step = currY + 1; step < nextY; step++) {
                                displayGrid.addObjectToDisplay(new Char('#'), currX, step);
                            }
                        }

                        else if (relativeX == 0 & relativeY < 0) {
                            for (int step = currY - 1; step > nextY; step--) {
                                displayGrid.addObjectToDisplay(new Char('#'), currX, step);
                            }
                        }

                        else if (relativeX > 0 & relativeY == 0) {
                            for (int step = currX + 1; step < nextX; step++) {
                                displayGrid.addObjectToDisplay(new Char('#'), step, currY);
                            }
                        }

                        else if (relativeX < 0 & relativeY == 0) {
                            for (int step = currX - 1; step > nextX; step--) {
                                displayGrid.addObjectToDisplay(new Char('#'), step, currY);
                            }
                        }

                    }
                }
            }
        }

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void main(String[] args) throws Exception {

        // check if a filename is passed in. If not, print a usage message.
        // If it is, open the file
            String fileName = null;
        switch (args.length) {
        case 1:
           // note that the relative file path may depend on what IDE you are
       // using.  This worked for NetBeans.
           fileName = "../xmlfiles/" + args[0]; //../xmlfiles. + args[0]
           break;
        default:
           System.out.println("java game.Rogue <xmlfilename>");
       return;
        }
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DungeonXMLHandler handler = new DungeonXMLHandler();
            saxParser.parse(new File(fileName), handler);

            Dungeon dungeon = handler.getDungeon();
            Room room = handler.getRoom();

            Rogue rog = new Rogue(dungeon);
            Thread rogue = new Thread(rog);

            roomsreq = dungeon.getRooms();
            player_room = null;

            Player plays = null;
            int room_ID = 0;

            creatures = room.getCreatures();
            for (int i = 0; i < creatures.size(); i++)
                if (creatures.get(i) instanceof Player) {
                    plays = (Player) creatures.get(i);
                    room_ID = creatures.get(i).getRoomID();
                }

            for (int j = 1; j <= roomsreq.size(); j++){ //possible bug around this
                if (j == room_ID) {
                    player_room = (Room) roomsreq.get(j-1);
                }
            }

            int final_x = player_room.getPosX().get(0);
            int final_y = player_room.getPosY().get(0) + topHeight;

            plays.setstartingX(final_x);
            plays.setstartingY(final_y);

            rogue.start();

            rog.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid, plays, handler));
            rog.keyStrokePrinter.start();

            rogue.join();
            rog.keyStrokePrinter.join();

        } 
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
