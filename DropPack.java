package game;

public class DropPack extends CreatureAction{
  
    private String _name;

    public DropPack(String name, Creature owner){
        super(owner);
        _name = name;
        System.out.println("DropPack" + _name);
    }

    public String getname()
    {
        return _name;
    }
}
