package game;

public class ChangedDisplayType extends CreatureAction{
    
    public ChangedDisplayType(String name, Creature owner){
        super(owner);
        System.out.println("ChangedDisplayType" + name);
    }
}
