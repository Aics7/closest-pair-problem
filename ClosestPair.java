/**
@author Issifu Alhassan
@version 1.0.0
*/
import java.util.Arrays;
import java.lang.Math;

public class ClosestPair
{
    public static void main(String[] args) 
    {
        Point[] closest; //array to store returned points
        
        try
        {
            /*Trial one*/
            System.out.print("Trial 1 ---> ");
            double[] x = {2,3,1,4,2,5,6,9,4,8};
            double[] y = {5,2,6,4,1,5,0,3,7,9};
            
            closest = closestPair(x,y);
            System.out.print("Closest pair: ("+closest[0].getX()+","+closest[0].getY()+") and ("+closest[1].getX()+","+closest[1].getY()+")");
            System.out.println();
        }
        catch(Exception e){}
        
        try
        {
            /*Trial two*/
            System.out.print("Trial 2 ---> ");
            double[] x = {2};
            double[] y = {5};
            closest = closestPair(x,y);
            System.out.print("Closest pair: ("+closest[0].getX()+","+closest[0].getY()+") and ("+closest[1].getX()+","+closest[1].getY()+")");
            System.out.println();
        }
        catch(Exception e){}
        
        try 
        {
            /*Trial three*/
            System.out.print("Trial 3 ---> ");
            double[] x = {2,3,1,4,2,5,6,9,4};
            double[] y = {5,2,6,4,1,5,0,3,7,9};
            
            closest = closestPair(x,y);
            System.out.print("Closest pair: ("+closest[0].getX()+","+closest[0].getY()+") and ("+closest[1].getX()+","+closest[1].getY()+")");
            System.out.println();
        }
        catch(Exception e){}
        
        try 
        {
            /*Trial four*/
            System.out.print("Trial 4 ---> ");
            double[] x = {2,3};
            double[] y = {5,2};
            closest = closestPair(x,y);
            System.out.print("Closest pair: ("+closest[0].getX()+","+closest[0].getY()+") and ("+closest[1].getX()+","+closest[1].getY()+")");
            System.out.println();
        }
        catch(Exception e){}
        
        try 
        {
            /*Trial five*/
            System.out.print("Trial 5 ---> ");
            double[] x = {2,3,1};
            double[] y = {5,2,6};
            closest = closestPair(x,y);
            System.out.print("Closest pair: ("+closest[0].getX()+","+closest[0].getY()+") and ("+closest[1].getX()+","+closest[1].getY()+")");
            System.out.println();
        }
        catch(Exception e){}
        
        try 
        {
            /*Trial six*/
            System.out.print("Trial 6 ---> ");
            double[] x = {-0.10,-0.67,-2.25,-0.32,-2.57,-1.25,-0.28,-1.47,-1.67,-0.52,-0.90,-1.75,-0.83,0.00,-3.00,-1.93,0.68,-3.33};
            double[] y = {5.58,5.83,4.85,11.05,7.48,5.08,6.05,6.68,6.28,8.52,9.63,4.97,9.37,5.68,4.70,10.93,5.77,7.77};
            closest = closestPair(x,y);
            System.out.print("Closest pair: ("+closest[0].getX()+","+closest[0].getY()+") and ("+closest[1].getX()+","+closest[1].getY()+")");
            System.out.println();
        }
        catch(Exception e){}
        
    }
    
    /**Takes a couple of points and returns the closest pair*/
    public static Point[] closestPair(double[] x, double[]y) throws Exception
    {
        /*if x-coordinates dont match y-coordinates*/
        if(x.length != y.length)
        {
            System.out.println("Can't compute closest pair. Input lengths mismatch!");
            throw new Exception();
        }
        /*if there is one or less points*/
        if(x.length <2 || y.length <2)
        {
            System.out.println("Can't compute closest pair. Fewer inputs!");
            throw new Exception();
        }
        /*if there are two points*/
        if(x.length == 2)
        {
            Point[] closest = {new Point(x[0],y[0]), new Point(x[1],y[1])};
            return closest;
        }
        /*if there are three points*/
        if(x.length == 3)
        {
            double cx1 = x[0], cy1 = y[0], cx2 = x[1], cy2 = y[1];
            //P0 and P1
            double cdist = Math.pow((cx1-cx2),2) + Math.pow((cy1-cy2),2); //ignoring square root to reduce computation time
            //P1 and P2
            double dist = Math.pow((x[0]-x[2]),2) + Math.pow((y[0]-y[2]),2);
            if(dist<cdist)
            {
                cx1 = x[0]; cy1 = y[0]; cx2 = x[2]; cy2 = y[2]; cdist = dist;
            }
            //P2 and P3
            dist = Math.pow((x[1]-x[2]),2) + Math.pow((y[1]-y[2]),2);
            if(dist<cdist)
            {
                cx1 = x[1]; cy1 = y[1]; cx2 = x[2]; cy2 = y[2]; cdist = dist;
            }
            Point[] closest = {new Point(cx1,cy1), new Point(cx2,cy2)};
            return closest;
        }
        
        //sorting based on x values
        int i=0;
        double z,zz;
        while (i < x.length) 
    	{
    		if (i == 0 || x[i-1] <= x[i])
    			i++;
    		else 
    		{
    			z = x[i];
    			zz = y[i];
    			x[i] = x[i-1];
    			y[i] = y[i-1];
    			x[--i] = z;
    			y[i] = zz;
    		}
    	}
        
        //finding left closest pair
        Point[] closestL = closestPair(Arrays.copyOfRange(x, 0, x.length/2),Arrays.copyOfRange(y, 0, y.length/2));
        //finding right closest pair
        Point[] closestR = closestPair(Arrays.copyOfRange(x, x.length/2, x.length),Arrays.copyOfRange(y, y.length/2, y.length));
        
        double distLsq =  Math.pow((closestL[0].getX() - closestL[1].getX()),2) + Math.pow((closestL[0].getY() - closestL[1].getY()),2);
        double distRsq =  Math.pow((closestR[0].getX() - closestR[1].getX()),2) + Math.pow((closestR[0].getY() - closestR[1].getY()),2);
        
        double minD;
        Point[] closest;
        if(distLsq<distRsq)
        {
            minD = distLsq;
            closest = closestL;
        }
        else 
        {
             minD = distRsq;
             closest = closestR;
        }
        
        double midLine = ((x[x.length/2-1]) + (x[x.length/2]))/2;
        
        //System.out.println(Arrays.toString(x));
        //System.out.println("midline... x = " + midLine);
        
        /*looking at points at a horizontal distance of minD from the mid line*/
        for(i = 0; i < x.length/2; i++)
        {
            if(midLine - x[i] < minD)
            {
                for(int j = x.length/2; j < x.length; j++)
                {
                    /*looking at points at a vertical and horizontal distance of minD from the current point*/
                    if((x[j] - midLine < minD) && (y[j] - y [i] < minD || y[i] - y [j] < minD))
                    {
                        if(Math.pow((x[i] - x[j]),2) + Math.pow((y[i] - y[j]),2) < minD)
                        {
                            closest = new Point[]{new Point(x[i],y[i]), new Point(x[j],y[j])};
                            minD = Math.pow((x[i] - x[j]),2) + Math.pow((y[i] - y[j]),2);
                        }
                    }
                }
            }
        }
        return closest;
        
    }
}

/**Point class to contain x and y coordinates of a point*/
class Point
{
    private double x,y;
    /**Point constructor*/
    public Point(double x,double y)
    {
        this.x = x;
        this.y = y;
    }
    public double getX()
    {
        return this.x;
    }
    public double getY()
    {
        return this.y;
    }
}