package FinalProject;

import java.util.ArrayList;

public class Turing_Machine {

    final Transition[] transitions;
    ArrayList<Character> tape;
    int head;
    int state;

    public Turing_Machine(Transition[] transitions, ArrayList<Character> tape, int head) {
        this.transitions = transitions;
        this.tape = tape;
        this.head = head;
        state = 0;
    }

    public void run() throws Turing_Error {

        int size = tape.size();
        boolean iserror = false;

        while (head < size) {
            outerloop:
            for (int i = 0; i < transitions.length; i++) {

                if (i == transitions.length - 1) 
                    iserror = true;
                

                if (transitions[i].crr.state == state && tape.get(head).equals(transitions[i].crr.symbol)) {

                    state = transitions[i].next.state;
                    tape.set(head, transitions[i].next.symbol);

                    switch (transitions[i].next.action) {
                        case 'Y':
                            System.out.print("Final String = ");
                            for (Character c : tape) 
                                System.out.print(c);
                            
                            System.out.println("Y ");
                            return;
                        case 'y':
                            System.out.print("Final String = ");
                            for (Character c : tape) 
                                System.out.print(c);
                            
                            System.out.println("Y ");
                            return;
                        case 'N':
                            System.out.print("Final String = ");
                            for (Character c : tape) 
                                System.out.print(c);
                            
                            System.out.println("N ");
                            return;
                        case 'n':
                            System.out.print("Final String = ");
                            for (Character c : tape) 
                                System.out.print(c);
                            
                            System.out.println("N ");
                            return;
                        case 'L':
                            head--;
                            break outerloop;
                        case 'l':
                            head--;
                            break outerloop;
                        case 'R':
                            head++;
                            break outerloop;
                        case 'r':
                            head++;
                            break outerloop;
                        default:
                            throw new Turing_Error("Action is Undefined. You must select between {Y,y,N,n,R,r,L,l}");
                    }

                } else if (iserror) 
                    throw new Turing_Error("The Machine Hanged.Please,Check your transition functions");
            }
        }

    }

}
