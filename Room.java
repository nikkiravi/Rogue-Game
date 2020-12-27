package game;

import java.util.ArrayList;

//import java.util.ArrayList;

public class Room extends Structure{
    
    private int _r1;
    private int pos_X;
    private int pos_Y;
    private ArrayList<Creature> creatures = new ArrayList<Creature>();
    private ArrayList<Item> items = new ArrayList<Item>();

    public Room(int r1)
    {
        _r1 = r1;
        System.out.println("Room:Room" + _r1);
    }

    public void setId(int room)
    {
        System.out.println("Room:setID" + room);
    }

    public void addCreature(Creature c1)
    {
        creatures.add(c1);
        System.out.println("Room:setCreature");
    }

    public ArrayList<Creature> getCreatures(){
        return creatures;
    }

    public void setItem(Item item){
        items.add(item);
        System.out.println("Room:setItem");
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public int getX()
    {
      pos_X = getPosX().get(0);
      return pos_X;
    }
    
    public int getY()
    {
      pos_Y = getPosY().get(0);
      return pos_Y;
    }

}
