import java.io.*;

public class Main {
    public static void main(String[] args) {

//        Graph<String> c3;
//
//        c3 = new Graph<String>(3);
//        c3.addArc(1,-1,"");
//        c3.addArc(-1,2,"");
//        c3.addArc(2,3,""); // Quel est le propblème ici ?
//        c3.addArc(-1,3,"");
//
//        System.out.print(c3.toString());


        // Test pour vérifier que le constructeur de SAT depuis un fichier .txt fonctionne
        SAT P = new SAT("C:\\Users\\Winnie\\IdeaProjects\\2-SAT\\formulas\\testSet1\\formula2.txt");
        P.print();
        Graph GraphP = new Graph(P);
        //System.out.println(GraphP.toString());
        Kasaraju K = new Kasaraju(GraphP);
        if (K.isSatisfiable()) {
            System.out.println("satisfiable");
        } else {
            System.out.println("non satisfiable");

        }
    }
}
