package game;

public class Passage extends Structure{
    
    public Passage()
    {
        System.out.println("Passage");
    }

    public void setName(String name)
    {
        System.out.println("Passage:setName" + name);
    }

    public void setID(int room1, int room2)
    {
        System.out.println("Passage:setID" + room1 + "\n" + room2);

    }
    //RECIEVE POSX AND POSY SEPARATELY, STORE A SET OF ARRAYLISTS WITH COORDINATE VALUES
}
