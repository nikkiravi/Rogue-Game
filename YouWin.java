package game;

public class YouWin extends CreatureAction{
    
    private String _name;

    public YouWin(String name, Creature owner)
    {
        super(owner);
        _name = name;
        System.out.println("YouWin:YouWin" + _name);
    }

    public String getname()
    {
        return _name;
    }
}
