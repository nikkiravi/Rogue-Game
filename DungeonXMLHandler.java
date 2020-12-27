package game;

import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DungeonXMLHandler extends DefaultHandler {
    
    private StringBuilder data;
    private static final String CLASSID = "DungeonXMLHandler";

    private Stack<Displayable> dispstack = null;
    private Stack<Action> actstack = null;
 
    private Dungeon dungeon = new Dungeon();
    private Room room = new Room(0);
    private Room currRoom = null;
    private Creature currCreature = null;
    private Action currAction = null;
    private Item currItem = null;
    private Passage currPassage = null;
    private Player currPlayer = null; 

    public Dungeon getDungeon(){
        return dungeon;
    }

    public Room getRoom(){
        return room;
    }

    private boolean bvisible = false;
    private boolean bposX = false;
    private boolean bposY = false;
    private boolean bwidth = false;
    private boolean bheight = false;
    private boolean bhp = false;
    private boolean bmaxhit = false;
    private boolean bactionMessage = false;
    private boolean bactionIntValue = false;
    private boolean bactionCharValue = false;
    private boolean bItemIntValue = false;
    private boolean btype = false;
    private boolean bhpMoves = false;

    public DungeonXMLHandler() {
        dispstack = new Stack<Displayable>();
        actstack = new Stack<Action>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    
        if (qName.equalsIgnoreCase("Dungeon")) {
            String dunName = attributes.getValue("name");
            int width = Integer.parseInt(attributes.getValue("width"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            
            ObjectDisplayGrid.getObjectDisplayGrid(gameHeight, width, topHeight, bottomHeight);

            dungeon.getDungeon(dunName, width, gameHeight, topHeight, bottomHeight);

        } else if(qName.equalsIgnoreCase("Rooms")) { //order followed in testDrawing.xml

        } else if(qName.equalsIgnoreCase("Room")) {
            int roomid = Integer.parseInt(attributes.getValue("room"));
            
            Room room = new Room(roomid);
            dungeon.addRoom(room);
            currRoom = room; 

            dispstack.push(currRoom);

        } else if(qName.equalsIgnoreCase("visible")) {
            bvisible = true;
        } else if(qName.equalsIgnoreCase("posX")) {
            bposX = true;
        } else if(qName.equalsIgnoreCase("posY")) {
            bposY = true;
        } else if(qName.equalsIgnoreCase("width")) {
            bwidth = true;
        } else if(qName.equalsIgnoreCase("height")) {
            bheight = true;
        } else if(qName.equalsIgnoreCase("Monster")) {

            String monName = attributes.getValue("name");
            int monRoom = Integer.parseInt(attributes.getValue("room"));
            int monSerial = Integer.parseInt(attributes.getValue("serial"));
            
            Monster m1 = new Monster();
            m1.setName(monName);
            m1.setID(monRoom, monSerial);

            dungeon.addCreature(m1);

            currCreature = m1;
            room.addCreature(m1);
            dispstack.push(currCreature);

        } else if(qName.equalsIgnoreCase("type")) {
            btype = true;
        } else if(qName.equalsIgnoreCase("hp")) {
            bhp = true;
        } else if(qName.equalsIgnoreCase("maxhit")) {
            bmaxhit = true;

        } else if(qName.equalsIgnoreCase("CreatureAction")) {

            String creatureName = attributes.getValue("name");
            String creatureType = attributes.getValue("type");
            CreatureAction c1 = null;

            if(creatureName.equals("Remove")){
                if(currCreature != null){
                    c1 = new Remove(creatureName, currCreature);
                }

                else{
                    c1 = new Remove(creatureName, currPlayer); 
                }
            }

            else if(creatureName.equals("YouWin")){
                if(currCreature != null){
                    c1 = new YouWin(creatureName, currCreature); 
                    currCreature.setYouWinAction(c1);
                }

                else{
                    c1 = new YouWin(creatureName, currPlayer); 
                }
            }

            else if(creatureName.equals("Teleport")){
                if(currCreature != null){
                    c1 = new Teleport(creatureName, currCreature); 
                    currCreature.setTeleport(c1);
                }

                else{
                    c1 = new Teleport(creatureName, currPlayer); 
                }
            }

            else if(creatureName.equals("ChangeDisplayedType")){
                if(currCreature != null){
                    c1 = new ChangedDisplayType(creatureName, currCreature); 
                    currCreature.setChangeDisplay(c1);
                }

                else{
                    c1 = new ChangedDisplayType(creatureName, currPlayer); 
                    currPlayer.setChangeDisplay(c1);
                }
            }

            else if(creatureName.equals("UpdateDisplay")){
                if(currCreature != null){
                    c1 = new UpdateDisplay(creatureName, currCreature); 
                    currCreature.setUpdateDisplay(c1);
                }

                else{
                    c1 = new UpdateDisplay(creatureName, currPlayer); 
                }
            }

            else if(creatureName.equals("EndGame")){
                if(currCreature != null){
                    c1 = new EndGame(creatureName, currCreature); 
                    currCreature.setEndAction(c1); 
                }

                else{
                    c1 = new EndGame(creatureName, currPlayer); 
                    currPlayer.setEndAction(c1); 
                }
            }

            else if(creatureName.equals("DropPack")){
                if(currCreature != null){
                    c1 = new DropPack(creatureName, currCreature); 
                }

                else{
                    c1 = new DropPack(creatureName, currPlayer); 
                    currPlayer.setDropPack(c1);
                }
            }

            if(currCreature != null){
                if(creatureType.equals("death")){
                    currCreature.setDeathAction(c1);
                }
                else if(creatureType.equals("hit")){
                    currCreature.setHitAction(c1);
                }
            }

            if(currPlayer != null){
                if(creatureType.equals("death")){
                    currPlayer.setDeathAction(c1);
                }
                else if(creatureType.equals("hit")){
                    currPlayer.setHitAction(c1);
                }
            }
            
            currAction = c1;
            actstack.push(currAction);

        }else if(qName.equalsIgnoreCase("actionMessage")) {
                bactionMessage = true;
    
        } else if(qName.equalsIgnoreCase("actionIntValue")) {
                bactionIntValue = true;
            
        } else if(qName.equalsIgnoreCase("actionCharValue")) {
                bactionCharValue = true;
        }else if(qName.equalsIgnoreCase("Scroll")) {

            String scrollName = attributes.getValue("name");
            int scrollRoom = Integer.parseInt(attributes.getValue("room"));
            int scrollSerial = Integer.parseInt(attributes.getValue("serial"));

            Scroll s1 = new Scroll(scrollName);
            s1.setID(scrollRoom, scrollSerial);
            currItem = s1;

            if(currPlayer != null){
                s1.setOwner(currPlayer);
                currPlayer.setScroll(s1);
            }

            currRoom.setItem(s1);
            dungeon.addItem(s1);
            dispstack.push(currItem);

        }else if(qName.equalsIgnoreCase("ItemAction")) {

            String itemName = attributes.getValue("name");
            //String itemType = attributes.getValue("type"); unnecessary?

            if(itemName.equals("BlessArmor")){
                BlessCurseOwner bco1 = new BlessCurseOwner(currCreature); 
                currItem.addItemAction(bco1);
                currAction = bco1; 
            }

            else if(itemName.equals("Hallucinate")){
                Hallucinate h1 = new Hallucinate(currCreature);
                currItem.addItemAction(h1);
                currAction = h1;
            }

            actstack.push(currAction);
            
        } else if(qName.equalsIgnoreCase("Player")) {

            //String player_name = attributes.getValue("name");
            int playerRoom = Integer.parseInt(attributes.getValue("room"));
            int playerSerial = Integer.parseInt(attributes.getValue("serial"));

            Player p1 = new Player();
            p1.setID(playerRoom, playerSerial);
            currPlayer = p1;
            dispstack.push(currPlayer);
            dungeon.addCreature(p1);
            room.addCreature(p1);

        }else if(qName.equalsIgnoreCase("hpMoves")) {
            bhpMoves = true;
        }else if(qName.equalsIgnoreCase("Armor")){
            String armor_name = attributes.getValue("name");
            int armor_room = Integer.parseInt(attributes.getValue("room"));
            int armor_serial = Integer.parseInt(attributes.getValue("serial"));

            Armor a1 = new Armor(armor_name);
            a1.setName(armor_name);
            a1.setID(armor_room, armor_serial);
            dungeon.addItem(a1);

            if(currPlayer != null)
            {
                currPlayer.setArmor(a1);
                a1.setOwner(currPlayer);
            }

            currRoom.setItem(a1);
            currItem = a1;
            dispstack.push(currItem);
        }
        else if(qName.equalsIgnoreCase("ItemIntValue")){
            bItemIntValue = true;
        }

        else if(qName.equalsIgnoreCase("Sword")){
            String swordName = attributes.getValue("name");
            int swordRoom = Integer.parseInt(attributes.getValue("room"));
            int swordSerial = Integer.parseInt(attributes.getValue("serial"));

            Sword sw1 = new Sword(swordName);
            sw1.setID(swordRoom, swordSerial);
            dungeon.addItem(sw1);

            if(currPlayer != null)
            {
                currPlayer.setWeapon(sw1);
                sw1.setOwner(currPlayer);
            }

            currRoom.setItem(sw1);
            currItem = sw1;
            dispstack.push(currItem);

        }else if(qName.equalsIgnoreCase("Passages")){ 

        }else if(qName.equalsIgnoreCase("Passage")){

            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));     
            Passage p1 = new Passage();
            p1.setID(room1, room2);
            currPassage = p1;
            dungeon.addPassage(p1);

            dispstack.push(currPassage);
        } 

        data = new StringBuilder();      
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        // Displayable d1 = new Displayable();
        // Action a1 = new Action();
    
        if(bvisible){
            Displayable x = dispstack.peek();
            
            x.setVisible(Integer.parseInt(data.toString()));
            bvisible = false;
        }

        else if(bposX){
            Displayable x = dispstack.peek();
            
            x.SetPosX(Integer.parseInt(data.toString()));
            bposX = false;
        }

        else if(bposY){
            Displayable x = dispstack.peek();
            
            x.SetPosY(Integer.parseInt(data.toString()));
            bposY = false;
        }

        else if(bwidth){
            Displayable x = dispstack.peek();
            
            x.SetWidth(Integer.parseInt(data.toString()));
            bwidth = false;
        }

        else if(bheight){
            Displayable x = dispstack.peek();
            
            x.setHeight(Integer.parseInt(data.toString()));
            bheight = false;
        }
        
        else if(bhp){
            Displayable x = dispstack.peek();
            x.setHp(Integer.parseInt(data.toString()));
            bhp = false;
        }

        else if(bmaxhit){
            Displayable x = dispstack.peek();
            
            x.setMaxHit(Integer.parseInt(data.toString()));
            bmaxhit = false;
        }

        else if(bactionMessage){
            Action x = actstack.peek();
            x.setMessage(data.toString());
            bactionMessage = false;
        }

        else if(bactionCharValue){
            Action x = actstack.peek();

            x.setCharValue(data.toString().charAt(0)); //string to character
            bactionCharValue = false;
        }

        else if(bactionIntValue){
            if(currAction != null){
                Action x = actstack.peek();
                x.setIntValue(Integer.parseInt(data.toString()));
                bactionIntValue = false;
            }

            else{
                currItem.setIntValue(Integer.parseInt(data.toString()));
            }
            
        }

        else if(bItemIntValue){
            Displayable x = dispstack.peek(); 
            x.setIntValue(Integer.parseInt(data.toString()));
            bItemIntValue = false;
        }

        else if(btype){
            Displayable x = dispstack.peek();
            
            x.setType(data.toString().charAt(0)); //String to character
            btype = false;
        }

        else if(bhpMoves){
            Displayable x = dispstack.peek();
                
            x.setHpMove(Integer.parseInt(data.toString()));
            bhpMoves = false;
        }

        if(qName.equalsIgnoreCase("Dungeon")){
        }

        else if(qName.equalsIgnoreCase("Rooms")){
        }

        else if(qName.equalsIgnoreCase("Room")){
            currRoom = null;
            dispstack.pop();
        }

        else if(qName.equalsIgnoreCase("Monster")){
            currCreature = null;
            dispstack.pop();
        }

        else if(qName.equalsIgnoreCase("Player")){
            currPlayer = null;
            dispstack.pop();

        }

        else if(qName.equalsIgnoreCase("Sword")){
            currItem = null;
            dispstack.pop();
        }

        else if(qName.equalsIgnoreCase("Armor")){
            currItem = null;
            dispstack.pop();
        }

        else if(qName.equalsIgnoreCase("Scroll")){
            currItem = null;
            dispstack.pop();
        }

        else if(qName.equalsIgnoreCase("Passage")){
            currPassage = null;
            dispstack.pop();
        }

        else if(qName.equalsIgnoreCase("CreatureAction")){
            currAction = null;
            actstack.pop();
        }

        else if(qName.equalsIgnoreCase("ItemAction")){
            currAction = null;
            actstack.pop();
        }

        else if(qName.equalsIgnoreCase("Passages")){
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

    @Override
    public String toString(){
        String str = CLASSID + ",DungeonXMLHandler:\n";
        
        if(dispstack != null) {
            str += " stack:\n";
            for(Displayable displayable : dispstack){
                str += displayable.toString();
            }
        } else {
            str += " stack: null\n";
        }

        if(actstack != null){
            str += " stack:\n";
            for(Action action : actstack){
                str += action.toString();
            }
        } else {
            str += " stack: null";
        }

        str += " data:" + data.toString() + "\n"; //comment this out?

        if(currRoom != null) //since this is part of dispStack not necessary
        {
            str += " currRoom:\n";
            str += currRoom.toString();
        } else {
            str += " currRoom: null\n";
        }

        if(dungeon != null)
        {
            str += " dungeon:\n";
            str += dungeon.toString();
        } else {
            str += " dungeon: null\n";
        }

        str += "bvisible: " + bvisible + "\n";
        str += "bposX: " + bposX + "\n";
        str += "bposY: " + bposY + "\n";
        str += "bwidth: " + bwidth + "\n";
        str += "bheight: " + bheight + "\n";
        str += "bhp: " + bhp + "\n";
        str += "bmaxhit: " + bmaxhit + "\n";
        str += "bactionMessage: " + bactionMessage + "\n";
        str += "bactionIntValue: " + bactionIntValue + "\n";
        str += "bactionCharValue: " + bactionCharValue + "\n";
        str += "btype: " + btype + "\n";
        str += "bhpMoves: " + bhpMoves + "\n";
        return str;
    }

}