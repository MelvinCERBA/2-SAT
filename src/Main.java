import java.io.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {

        String filename = "C:\\Users\\Winnie\\IdeaProjects\\2-SAT\\formulas\\testSet1\\formula0.txt";
        if (0 < args.length) {
            filename = args[0];
        }

        SAT P = new SAT(filename);

        if (P.Satisfiable()) {
            System.out.println("Formula " + filename + ": satisfiable");
            exit(0);
        } else {
            System.out.println("Formula " + filename + ": unsatisfiable");
            exit(0);
        }
        exit(0);
    }
}
