package edu.uwf.cd.acp.p3;

import javafx.scene.shape.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import java.lang.Runnable;
import java.util.Random;
//import java.Exception.InterruptedException;

public class Horse implements Runnable
{
  int x1,y1;
  boolean first = true;
  Rectangle head = new Rectangle();
  Rectangle body = new Rectangle();
  Rectangle frontLegs = new Rectangle();
  Rectangle backLegs = new Rectangle();
  GraphicsContext gc;
  Canvas c;
  int whichHorse;
  Random r = new Random();
  final int bodywidth = 70;
  App drawer;
  
  public Horse(int x, int y, Canvas c, int horseNum, App drawer)
  {
    x1 = x;
    y1 = y;
    this.c = c;
    this.drawer = drawer;
    gc = c.getGraphicsContext2D();
    whichHorse = horseNum;
  
    head.setX(x1+50);
    head.setY(y1);
    head.setWidth(20);
    head.setHeight(10);

    body.setX(x1);
    body.setY(y1+10);
    body.setWidth(bodywidth - 15);
    body.setHeight(20);
    
    frontLegs.setX(x1+40);
    frontLegs.setY(y1+30);
    frontLegs.setWidth(10);
    frontLegs.setHeight(10);
    
    backLegs.setX(x1);
    backLegs.setY(y1+30);
    backLegs.setWidth(10);
    backLegs.setHeight(10);
    draw(gc);   
  }


     @Override
     public void run()
      {
         while(x1<700)
         {
            // drawer.horseLock.lock();
            try
            {  
              draw(gc);
              if(x1>=700)
              {
                 // drawer.stopAll(whichHorse);
                 System.out.println("Winner " + whichHorse + " is returning");
                 // drawer.horseLock.unlock();
                 return;
              }
              //  drawer.horseLock.unlock();
              Thread.sleep(r.nextInt(500));
            }
            catch(InterruptedException e)
            {
               System.out.println("Got interruptedException" + whichHorse);
               return;
            }
            finally
            {  
            }
         }       
         return;
      }
 
  public void setPos(int x,int y)
  {
    x1 = x; y1 = y;
  }
 
  public void setFirst(boolean isFirst)
  {
     first = isFirst;
  }
  
  
  public void eraseCurrentPos()
  {
    gc.clearRect(x1, y1, x1+100, y1+100);  
  }

  public void draw()
  {
    draw(gc);
  }

 
  public void draw(GraphicsContext gc)
  {
    System.out.println("in Draw at " + x1);
    if(!first)
    {
       gc.clearRect(x1, y1, x1+100, y1+100);
       x1+=r.nextInt(50);
    } 
    first = false;
    head.setX(x1+50);
    body.setX(x1);
    frontLegs.setX(x1+40);
    backLegs.setX(x1);
    drawHorse(gc);
  }
  
  public void drawHorse(GraphicsContext gc)
  {
     System.out.println("Drawing horse " + whichHorse + " at " + x1);
     gc.setStroke(Color.BLACK);
     gc.strokeRect(head.getX(), head.getY(),head.getWidth(),head.getHeight());
     gc.strokeRect(body.getX(), body.getY(),body.getWidth(),body.getHeight());
     gc.strokeRect(frontLegs.getX(), frontLegs.getY(),frontLegs.getWidth(),frontLegs.getHeight());
     gc.strokeRect(backLegs.getX(), backLegs.getY(),backLegs.getWidth(),backLegs.getHeight());
  }
}