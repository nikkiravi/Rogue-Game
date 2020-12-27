package game;

public class UpdateDisplay extends CreatureAction{
    
    private String _name;

    public UpdateDisplay(String name, Creature owner){
        super(owner);
        _name = name;
        System.out.println("UpdateDisplay" + _name);
    }

    public String getname()
    {
        return _name;
    }
}
