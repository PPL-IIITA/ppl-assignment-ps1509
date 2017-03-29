
import java.io.FileInputStream;
import java.util.*;


/**
 *
 * 
 * <h1>Class to input all the data from the text files</h1>
 * <p>Calls function to provide gifts
 * <p>Writes output to the text file
 * <p>Has function to sort data as per happiness and compatibility
 * 
 */

import java.io.*;
import java.util.Scanner;
public class input {
    
    
    int gs,bs,es,us,ls;
    /**
     * Constructor function which initializes the girl,boy and the gift list
     * @param args Command line argument to accept file names and value of k
     * @param g List of girls
     * @param b List of boys
     * @param u List of utility gifts
     * @param l List of luxury gifts
     * @param e List of essential gifts
     * @param commit Array to store the mapping of committed boys and girls
     * @param c List of couples made
     * @throws IOException Gives IO Exception on wrong arguments
     */
    
    /**
     * Constructor function which initializes the girl,boy and the gift list
     * @param args Command line argument to accept file names and value of k
     * @param g List of girls
     * @param b List of boys
     * @param u List of utility gifts
     * @param l List of luxury gifts
     * @param e List of essential gifts
     * @param commit Array to store the mapping of committed boys and girls
     * @param c List of couples made
     * @param k user input
     * @throws IOException Gives IO Exception on wrong arguments
     */
    public input(String args[],girls g[],boys b[],utility_gifts u[],luxury_gifts l[], essential_gift e[],int commit[], couple c[],int k) throws IOException{
        FileInputStream girl = new FileInputStream(args[1]);
        FileInputStream boy = new FileInputStream(args[0]);
        FileInputStream utility_gift = new FileInputStream(args[4]);
        FileInputStream luxury_gift = new FileInputStream(args[3]);
        FileInputStream essential_gift = new FileInputStream(args[2]);
        Scanner s =new Scanner(girl);
        Scanner s2 =new Scanner(boy);
        Scanner s3 = new Scanner(essential_gift);
        Scanner s4 = new Scanner(utility_gift);
        Scanner s5 = new Scanner(luxury_gift);
        
        gs=Integer.parseInt(s.next());
        bs=Integer.parseInt(s2.next());
        es=Integer.parseInt(s3.next());
        us=Integer.parseInt(s4.next());
        ls=Integer.parseInt(s5.next());
        
        for(int i=0;i<gs;i++) {
                g[i]=new girls();
                g[i].name=s.next();
                g[i].attractive=s.nextInt();
                g[i].maintainance_cost=s.nextInt();
                g[i].intellect=s.nextInt();
                g[i].criteria=s.nextInt();
                g[i].type=s.nextInt();
        }
        
        for(int i=0;i<bs;i++) {
                b[i]=new boys();
                b[i].name=s2.next();
                b[i].attractive=s2.nextInt();
                b[i].intellect=s2.nextInt();
                b[i].budget=s2.nextInt();
                b[i].min_attraction=s2.nextInt();
                b[i].criteria=s2.nextInt();
        }
        
        
        for(int i=0;i<es;i++) {
            e[i] = new essential_gift();
            e[i].value=s3.nextInt();
            e[i].price=s3.nextInt();    
        }
        
        for(int i=0;i<us;i++) {
            u[i] = new utility_gifts();
            u[i].utility_value=s4.nextInt();
            u[i].utility_class=s4.nextInt();
            u[i].value=s4.nextInt();
            u[i].price=s4.nextInt();
        }
        
       
        for(int i=0;i<ls;i++) {
            l[i] = new luxury_gifts();
            l[i].rating=s5.nextInt();
            l[i].difficulty=s5.nextInt();
            l[i].value=s5.nextInt();
            l[i].price=s5.nextInt();    
        }
    
    }
    
    /**
     * Function to make a list of of the couples
     * @param c List of couples
     * @param g List of girls
     * @param b List of boys
     * @param commit Array to map each girl to a boy
     * @param k User input
     * @return No.of couples formed
     */
    public int makelist(couple c[],girls g[],boys b[],int commit[],int k){
        int j,lo=0;
        for(j=0;j<k;j++) {
            if(g[j].status==2) {
                int flag=c[lo].find_happy(gs, bs, g[j], b, commit, j);
                if(flag==1)
                    lo++;
            }
            
            
        }
        return lo;
    }
    
    /**
     * Method to perform gifting function by boys
     * @param g List of girls
     * @param b List of boys
     * @param commit List of Girls and index of their boyfriend
     * @param k User input
     * @param l List of luxury gifts
     * @param e List of essential gifts
     * @param c List of couples
     * @throws IOException Gives IO exception when file opening error occurs
     */
    public void pair(girls g[],boys b[],int commit[],int k,luxury_gifts l[],essential_gift e[],couple c[]) throws IOException {
        File file = new File("coupledata.txt");
        FileWriter qwe= new FileWriter(file,true);
        int i,j;
        
        
       for(i=0;i< bs;i++) {
            b[i].gifting(g, l, e, ls, es, gs,k);
       }
       
       for(i=0;i<500;i++) {
            c[i] = new couple();
        }
       //to write log in te file
        Date date = new Date();
        for(j=0;j<gs;j++) {
            
            if(g[j].status == 2){
                qwe.write(date.toString()+ " " + "Committed :)" + "  " + g[j].name + "  " + g[j].partner +"  " + g[j].cost + '\n');
                qwe.write(System.getProperty("line.separator"));
                
            }
            else{
                qwe.write(date.toString()+ " " + "Not Committed :(" + "  " + g[j].name + "  " + g[j].partner +"  " + g[j].cost + '\n');
                qwe.write(System.getProperty("line.separator"));
            }
                
            
        }
        qwe.flush();
        qwe.close();
    }
    
    public int makearr1(boys b[],int k, int ar[],random_k bk) {
        int i,x;
        for(i=0;i<bs;i++){
            ar[i]=b[i].budget;
        }
        x=bk.<boys>ret_best(b,ar,bs,k);
        return x;
    }
    
    public int makearr2(girls g[],int k, int ar[],random_k bk) {
        int i,x;
        for(i=0;i<gs;i++){
            ar[i]=g[i].maintainance_cost;
        }
        x=bk.<girls>ret_best(g,ar,gs,k);
        return x;
        
    }
    
    public int makearr3(essential_gift e[], int k, int ar[],random_k bk) {
        int i,x;
        for(i=0;i<es;i++){
            ar[i]=e[i].value;
        }
        x=bk.<essential_gift>ret_best(e,ar,es,k);
        return x;
    }
    
    public int makearr4(luxury_gifts l[],int k,int ar[],random_k bk) {
        int i,x;
        for(i=0;i<ls;i++){
            ar[i]=l[i].value;
        }
        x=bk.<luxury_gifts>ret_best(l,ar,ls,k);
        return x;
    }
    
    /**
     * Method to make pairs of girls
     * @param g List of girls
     * @param b List of boys
     * @param commit List of girls and index of their boyfriends
     * @param k User input
     * @param y Randomly chosen girl
     */
    public void makep(girls g[],boys b[],int commit[],int k,int y) {
        
        if(g[y].status==1) {
            int x=g[y].matchMaker(b, k);
            commit[y]=x;
        }
    }
        
}   
    
    

