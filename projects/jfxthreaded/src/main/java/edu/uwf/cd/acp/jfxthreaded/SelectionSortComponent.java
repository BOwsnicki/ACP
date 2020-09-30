package edu.uwf.cd.acp.jfxthreaded;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
   A component that displays the current state of the selection sort algorithm.
*/
public class SelectionSortComponent
{
   /**
      Constructs the component.
   */
   Canvas c;
   GraphicsContext gc;
   private SelectionSorter sorter;
      
   public SelectionSortComponent(Canvas c)
   {
      int []a = ArrayUtil.randomIntArray(30, 300);
      this.c = c;
      gc = c.getGraphicsContext2D();
      sorter = new SelectionSorter(a, this);
   }


/**
      Draws the current state of the sorting algorithm.
      @param g2 the graphics context
   */
   public void draw()
   {
      System.out.println("in draw");
      sorter.sortStateLock.lock();
      try
      {
         int deltaX = 500 / sorter.a.length;
			int markedPosition = sorter.getMarkedPosition();
			int alreadySorted = sorter.getSortedPosition();
         for (int i = 0; i < sorter.a.length; i++)
         {
          
          
          gc.clearRect(i * deltaX, 0, 5, 500);
         
     
            if (i == markedPosition)
                gc.setStroke(Color.RED);
            else if (i <= alreadySorted)
               gc.setStroke(Color.BLUE);
            else
               gc.setStroke(Color.BLACK);
      
            gc.strokeLine(i * deltaX, 0, i * deltaX, sorter.a[i]);      
			   gc.strokeLine(i * deltaX + 1, 0, i * deltaX + 1, sorter.a[i]);
				gc.strokeLine(i * deltaX + 2, 0, i * deltaX + 2, sorter.a[i]);
				gc.strokeLine(i * deltaX + 3, 0, i * deltaX + 3, sorter.a[i]);
         }
      }
      finally
      {
         sorter.sortStateLock.unlock();
      }
   }



   /**
      Starts a new animation thread.
   */
   public void startAnimation()
   {
      class AnimationRunnable implements Runnable
      {
         public void run()
         {
            try
            {
              System.out.println("starting animation");
               sorter.sort();
            }
            catch (InterruptedException exception)
            {
            }
         }
      }
      
      Runnable r = new AnimationRunnable();
      Thread t = new Thread(r);
      t.start();
   }

}

