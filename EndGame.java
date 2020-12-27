package game;

public class EndGame extends CreatureAction{
    
    private String _name;

    public EndGame(String name, Creature owner){
        super(owner);
        _name = name;
        System.out.println("EndGame" + _name);
    }
}
