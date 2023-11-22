package FinalProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        runMain();
        /*try{
            Transition[] transitions = {
             new Transition(new Current(0,'a'), new Next(1,'a','r'))
            ,new Transition(new Current(0,'b'), new Next(0,'b','r'))
            ,new Transition(new Current(0,'#'), new Next(0,'#','y'))
                    
            ,new Transition(new Current(1,'a'), new Next(0,'a','r'))
            ,new Transition(new Current(1,'b'), new Next(1,'b','r'))
            ,new Transition(new Current(1,'#'), new Next(1,'#','n'))};
            
            ArrayList<Character> tape = new ArrayList<>();
            
            tape.add('a');
            tape.add('b');
            tape.add('a');
            tape.add('a');
            tape.add('#');
            
            
            Turing_Machine machine=new Turing_Machine(transitions, tape, 0);
            machine.run();
            
        }catch(Throwable err){
            System.out.println(err.getMessage());
        }*/    
    }

    
    
    
    static int states = 0, alpha = 0, Malpha = 0, counter = 0, counter2 = 0, head = 0;

    static Scanner sc = new Scanner(System.in);
    static String[] alphabets, Malphabets;
    static Transition[] transitions;
    static String s = "", t = "", next_alpha = "", curr_alpha = "";
    static ArrayList<Character> tape;

    
    
    
    
    
    static void runMain() {
        try {

            System.out.print("Enter number of States: ");
            states = Integer.parseInt(sc.next());

            System.out.print("\nEnter number of string alphabet: ");
            alpha = Integer.parseInt(sc.next());
            alphabets = new String[alpha];

            Entering_Alphabets(alphabets,alpha);

            System.out.print("\nEnter number of machine alphabets: ");
            Malpha = Integer.parseInt(sc.next());
            Malphabets = new String[Malpha];

            Entering_Alphabets(Malphabets,Malpha);

            System.out.println("\nEnter Transitions: ");
            transitions = new Transition[(states * (alpha + Malpha))];

            Adding_Transitions();

            System.out.print("Enter String: ");
            t = sc.next();

            System.out.print("\nEnter intial Postion of Head: ");
            head = Integer.parseInt(sc.next());

            tape = new ArrayList<>();
            for (int i = 0; i < t.length(); i++) 
                tape.add(t.charAt(i));

            if (isConfirmed()) {
                tape.add(Malphabets[0].charAt(0));
                Turing_Machine machine = new Turing_Machine(transitions, tape, head);
                machine.run();
            }

        } catch (Turing_Error error) {
            System.out.println("\nThere is an Error in your inputs\n" + error.getMessage() + "\n");
        } catch (Exception error) {
            System.out.println("Sorry,Something Bad Happened.Please, Try again: \n" + error.getMessage() + "\n");
        }
    }

    
    
    
    
    
    static boolean isConfirmed() {
        
        System.out.println("\n\n\n");

        System.out.print("Alphabets: ");
        for (String alphabet : alphabets) 
            System.out.print("" + alphabet + "  ");
        

        System.out.print("\nMachine Alphabets: ");
        for (String Malphabet : Malphabets) 
            System.out.print("" + Malphabet + "  \n");
        

        System.out.println("\nTransition Functions: ");
        for (Transition transition : transitions) 
            System.out.print("(" + transition.crr.state + "," + transition.crr.symbol + ") , (" 
                    + transition.next.state + "," + transition.next.symbol + "," + transition.next.action + ")\n");
        

        System.out.println("\nString: " + t);
        System.out.println("\nIntial Postion of Head: " + head);

        System.out.print("\n\nTo Confirm type \"y\".To Cancel Type any:");
        s = sc.next();

        return s.contains("y") || s.contains("Y");
    }
    
    
    
    
    
    
    static void Entering_Alphabets(String[] alphabets, int alpha){
        for (int i = 0; i < alpha; i++) {
                System.out.print("\nEnter ");
                switch (i) {
                    case 0 -> System.out.print("1st ");
                    case 1 -> System.out.print("2nd ");
                    case 2 -> System.out.print("3rd ");
                    default -> System.out.println("" + i + "th ");
                }
                System.out.print("alphabet: ");
                alphabets[i] = sc.next();
            }
    }

    
    
    
    
    
    static void Adding_Transitions() throws Turing_Error {

        for (int i = 0; i < (states * (alpha + Malpha)); i++) {
            s = "(";
            System.out.print(s);
            int curr_state = Integer.parseInt(sc.next());
                if (curr_state > states) 
                    throw new Turing_Error("Current State Must not Exced the Number of States");
            

            s += curr_state + ",";
            System.out.print(s);
            curr_alpha = sc.next();
            Checking_Input();
                if (counter >= Malphabets.length && counter2 >= alphabets.length) 
                    throw new Turing_Error("Current Alphabet Must be in Alphabets defined earlier");
            

            s += curr_alpha + ") , (";
            System.out.print(s);
            int next_state = Integer.parseInt(sc.next());
                if (next_state > states) 
                    throw new Turing_Error("Next State Must not Exced the Number of States");
            

            s += next_state + ",";
            System.out.print(s);
            next_alpha = sc.next();
            Checking_Input();
                if (counter2 >= alphabets.length && counter >= Malphabets.length) 
                    throw new Turing_Error("Next Alphabet Must be From Machine Alphabets or String Alphabets");
            

            s += next_alpha + ",";
            System.out.print(s);
            String action = sc.next();
            
            switch (action) {
                case "Y", "y", "N", "n", "L", "l", "R", "r" -> {}
                default -> throw new Turing_Error("Action is Undefined. You must select between {Y,y,N,n,R,r,L,l}");
            }

            s += action + ")";
            System.out.println(s);

            Current crr = new Current(curr_state, curr_alpha.charAt(0));
            Next next = new Next(next_state, next_alpha.charAt(0), action.charAt(0));
            transitions[i] = new Transition(crr, next);
        }

    }
    
    
    
    
    
    
    static void Checking_Input(){
        
        counter = 0;
        counter2 = 0;
            for (String Malphabet : Malphabets) 
                if (!Malphabet.equals(curr_alpha)) 
                    counter++;
            
            for (String alphabet : alphabets) 
                if (!alphabet.equals(curr_alpha))
                    counter2++;
        
    }

}
