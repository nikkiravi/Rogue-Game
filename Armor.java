package game;

public class Armor extends Item{

    private String _name;
    private int room_id;

    public Armor(String name)
    {
        _name = name;
        System.out.println("Armor:Armor"+_name);
    }

    public void setName(String name)
    {
        System.out.println("Armor:setName" + name);
    }

    public String getName(){
        return _name;
    }

    public int getRoomID(){
        return room_id;
    }

    public void setID(int room, int serial)
    {
        room_id = room;
        System.out.println("Armor:setID:room:" + room + "\nserial" + serial);
    }
}
