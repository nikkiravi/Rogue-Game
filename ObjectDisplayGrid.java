package game;

import game.asciiPanel.AsciiPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject{

    /**
     *
     */
    private static final long serialVersionUID = -4957170715742113090L;

    // private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";

    private static AsciiPanel terminal;
    private Stack<Char>[][] objectGrid = null;

    private List<InputObserver> inputObservers = null;

    private static int gameheight;
    private static int topheight;
    private static int bottomheight;
    private static int gamewidth;

    public Stack<Char>[][] getObjectGrid(){
        return objectGrid;
    }

    public ObjectDisplayGrid(int _width, int _height) {
       gamewidth = _width;
       gameheight = _height;

       terminal = new AsciiPanel(gamewidth, gameheight);

       objectGrid = new Stack[gamewidth][gameheight];
       for(int i = 0; i < gamewidth; i++) {
           for (int j = 0; j < gameheight; j++) {
               objectGrid[i][j] = new Stack<Char>();
           }
       }
       initializeDisplay();

       super.add(terminal);
       super.setSize(gamewidth * 9, gameheight * 16);
       super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // super.repaint();
       // terminal.repaint( );
       super.setVisible(true);
       terminal.setVisible(true);
       super.addKeyListener(this);
       inputObservers = new ArrayList<>();
       super.repaint();
   }
    
    public static void getObjectDisplayGrid(int gameHeight, int width, int topHeight, int bottomHeight)
    {
        System.out.println("ObjectDisplayGrid: getObjectDisplayGrid" + gameHeight + "\n" + width + "\n" + topHeight + "\n" + bottomHeight);
        gameheight = gameHeight;
        gamewidth = width;
        topheight = topHeight;
        bottomheight = bottomHeight;
    }

    public void setTopMessageHeight(int topHeight){
        System.out.println("ObjectDisplayGrid: setTopMessageHeight" + topHeight);
        topheight = topHeight;
    }

    public int gettopheight()
    {
        return topheight;
    }

    public int getBottomheight() {
        return bottomheight;
    }

    @Override
    public void registerInputObserver(InputObserver observer) {
     //     if (DEBUG > 0) {
     //         System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
     //     }
          inputObservers.add(observer);
      }
  

    @Override
    public void keyTyped(KeyEvent e) {
     //   if (DEBUG > 0) {
     //       System.out.println(CLASSID + ".keyTyped entered" + e.toString());
     //   }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
    }


    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            observer.observerUpdate(ch);
      //      if (DEBUG > 0) {
      //          System.out.println(CLASSID + ".notifyInputObserver " + ch);
      //      }
        }
    }
    // we have to override, but we don't use this
    @Override
    public void keyPressed(KeyEvent even) {
    }

    // we have to override, but we don't use this
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public final void initializeDisplay() {
        Char ch = new Char(' ');
        for (int i = 0; i < gamewidth; i++) {
            for (int j = 0; j < gameheight; j++) {
                addObjectToDisplay(ch, i, j);
            }
        }
        terminal.repaint();
    }
    
    public void fireUp() {
        if (terminal.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void addObjectToDisplay(Char ch, int x, int y) {//main man
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid.length)) {
              objectGrid[x][y].push(ch); 
              writeToTerminal(x, y);
            }
        }
    }     
    
    public void removeObjectFromDisplay(Char ch, int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y].pop();
                writeToTerminal(x, y);
            }
        }
    }

    private void writeToTerminal(int x, int y) {
        Char cObj = objectGrid[x][y].peek();
        char ch = cObj.getChar();
        terminal.write(ch, x, y);
        terminal.repaint();
    }
}
