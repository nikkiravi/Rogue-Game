package game;

import java.util.ArrayList;

public class Item extends Displayable {

    private Creature owner;
    //private Creature c = new Creature();
    private ItemAction action;
    private int itemintvalue;
    private ArrayList<Integer> PosX = new ArrayList<Integer>();
	private ArrayList<Integer> PosY= new ArrayList<Integer>();

    public void setOwner(Creature _owner)
    {
        System.out.println("Item");
        owner = _owner;
    }

    public Creature getOwner(){
        return owner;
    }

    public void addItemAction(ItemAction itemaction)
    {
        System.out.println("Item:addItemAction");
        action = itemaction;
    }

    public ItemAction getItemAction(){
        System.out.println("Item:getItemAction");
        return action;
    }

    public void setIntValue(int intVal){
        System.out.println("Item:setIntValue");
        itemintvalue = intVal;
    }

    public int getIntValue(){
        System.out.println("Item:getIntValue");
        return itemintvalue;
    }

    public void SetPosX(int x)
    {
        PosX.add(x);
        System.out.println("Displayable:SetPosX" + x);
    }

    public void SetPosY(int y)
    {
        PosY.add(y);
        System.out.println("Displayable:setPosY" + y);
    }

    public ArrayList<Integer> getPosX(){
        return PosX;
    }

    public ArrayList<Integer> getPosY(){
        return PosY;
    }
}
