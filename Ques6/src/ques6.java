
import java.io.IOException;

/**
 *
 * 
 * <h1>Main file of Question 6</h1>
 * <p>Performs gifting for 't' days and breaks-up 't' least happy couple and make new pairs after each 't' days
 * @author Puja Kumari
 */
public class ques6 {
    /**
     * Main method which gives most happy and most compatible couple
     * @param args List of arguments
     * <p>1st input : boy.txt
     * <p>2nd input : girl.txt
     * <p>3rd input : essential.txt
     * <p>4th input : luxury.txt
     * <p>5th input : utility.txt
     * <p>6th input : No. of days gifting is performed
     * @throws IOException Gives IO Exception when arguments are wrong
     */
    public static void main(String args[]) throws IOException {
    
        int k=Integer.parseInt(args[5]),j,lo,i; //lo=no. of couples
        int[] commit = new int[1000];
        int[] breakup = new int[k];
        couple[] c = new couple[500];
        girls[] g = new girls [1000];
        boys[] b = new boys [1000];
        utility_gifts[] u = new utility_gifts [100];
        luxury_gifts[] l = new luxury_gifts [100];
        essential_gift[] e = new essential_gift [100];
        input in = new input(args,g,b,u,l,e,commit,c);
        in.gift(g, b, l, e);//gifting done 1st time
        lo=in.makelist(c, g, b, commit);
        for(int x=0;x<k;x++) {
            //sort according to happiness
            in.sorth(c,lo);
            //breaking up least 't' happy couples and make new pairs
            in.pair(g,b,commit,c,breakup,k,lo);
            in.gift(g, b, l, e);  // gifting
        
            //Updating couple object
            lo=in.makelist(c, g, b, commit);
        }
        
    }
        
}
