import java.io.*;

public class Main {
    public static void main(String[] args) {

        Graph<String> c3;

        c3 = new Graph<String>(3);
        c3.addArc(0,1,"");
        c3.addArc(1,2,"");
        c3.addArc(2,3,""); // Quel est le propblème ici ?

        System.out.print(c3.toString());


        SAT P = new SAT("E:\\Documents\\Fac\\L3\\ALGO\\tp1_2\\formulas\\formule-2-sat.txt");
        P.print();

    }
}
