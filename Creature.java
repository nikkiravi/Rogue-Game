package game;

import java.util.ArrayList;

public class Creature extends Displayable {
    
    private int hp;
    private int HpMoves;
    private int maxHit;
    private ArrayList<CreatureAction> cact = new ArrayList<CreatureAction>();
    // private Creature c = new Creature();
    private CreatureAction changeDisplay;
    private CreatureAction updateDisplay;
    private CreatureAction youWin;
    private CreatureAction dact;
    private CreatureAction teleport;
    private CreatureAction endgame;
    //private Item weapon;
    //private Item armor;
    
    public Creature()
    {
        System.out.println("Creature");
    }

    public void setHp(int h)
    {
        hp = h;
    }

    public int getHp()
    {
        return hp;
    }

    @Override
    public void setMaxHit(int _maxHit) {
        maxHit = _maxHit;
    }

    public int getMaxHit() {
        return maxHit;
    }
    
    public void setHpMoves(int hpm)
    {
        System.out.println("Creature:setHpMoves" + hpm);
        HpMoves = hpm;
    }

    public int getHpMoves() {
        return HpMoves;
    }
    
    public void setDeathAction(CreatureAction da)
    {
        System.out.println("Creature:setDeathAction");
        dact = da;
    }

    public CreatureAction getDact() {
        return dact;
    }

    public void setHitAction(CreatureAction ha)
    {
        System.out.println("Creature:setHitAction");
        cact.add(ha);
    }

    public ArrayList<CreatureAction> getCact() {
        return cact;
    }

    public void setChangeDisplay(CreatureAction ca){
        changeDisplay = ca;
        System.out.println("Creature:setChangeDisplay");
    }

    public CreatureAction getChangeDisplay(){
        System.out.println("Creature:gerChangeDisplay");
        return changeDisplay;
    }

    public void setEndAction(CreatureAction ea){
        System.out.println("Creature:setEndAction");
        endgame = ea;
    }

    public CreatureAction getEndAction(){
        System.out.println("Creature:getEndAction");
        return endgame;
    }

    public void setYouWinAction(CreatureAction yw){
        System.out.println("Creature:setYouWinAction");
        youWin = yw;
    }

    public CreatureAction getYouWinAction(){
        System.out.println("Creature:getYouWinAction");
        return youWin;
    }

    public void setUpdateDisplay(CreatureAction ud){
        System.out.println("Creature:setUpdateDisplay");
        updateDisplay = ud;
    }

    public CreatureAction getUpdateDisplay(){
        System.out.println("Creature:getupdateDisplay");
        return updateDisplay;
    }

    public void setTeleport(CreatureAction tele){
        System.out.println("Creature:setUpdateDisplay");
        teleport = tele;
    }

    public CreatureAction getTeleport(){
        System.out.println("Creature:getupdateDisplay");
        return teleport;
    }

}
