package game;

public class Teleport extends CreatureAction{
    
    private String _name;

    public Teleport(String name, Creature owner){
        super(owner);
        _name = name;
        System.out.println("Teleport:Teleport" + _name);

    }

    public String getname()
    {
        return _name;
    }
}
