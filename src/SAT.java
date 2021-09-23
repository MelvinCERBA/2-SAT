
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class SAT {
    public List<Integer> litterals = new ArrayList<>();; //Il faut initialiser la liste pour la première itération de "if (litterals.contains(lit)){}" l.35. On utilise une liste car les arrays sont de taille fixe et cela complique les choses
    public List<List<Integer>> clauses;

    public SAT(List<Integer> lits, List<List<Integer>> cls){
        litterals=lits;
        clauses=cls;
    }

    public SAT(String fileName){
        try {
            clauses = Files.lines(Paths.get(fileName)) //on récupère les lignes du fichier à la destination "fileName" sous forme d'un stream
                    .filter(line -> line.endsWith(" 0")) // On ne garde que les lignes qui terminent par 0
                    .map(line -> Arrays.stream(line // On map chaque ligne à l'array qui contient les entiers de cette ligne. ex: -2 1 0 devient [-2,1] (on ne garde pas le zero, qui ne sert à rien)
                            .substring(0, line.length() - 2)  //pour chaque ligne de longueur n, on prend une partie qui commence à l'indice 0 et s'arrete à n-2 (pour éliminer le 0 et l'espace qui le précède)
                            .trim().split("\\s+")) //on découpe la ligne à chaque espace (/s+ correspond à une suite infinie d'espaces consécutifs) ex: "-2 1" devient ["-2","1"]
                            .map(Integer::parseInt) // map à chaque String ainsi obtenu l'entier qui correspond. ex "-2" devient -2
                            .collect(Collectors.toList())) // collecte le stream final (de chaque clause) en une liste
                    .collect(Collectors.toList()); // collecte le stream contenant les listes correspondants à chaque clause en une liste de listes
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(List<Integer> clause:clauses){
            for(int lit:clause){
                if (litterals.contains(lit)){}
                else
                    litterals.add(lit);
            }

        }

    }

    public Graph Graph(){
    Graph G = new Graph(this);
        return G;
    }

    /*public boolean Satisfiable(){ //crée le graphe des implications, puis opère l'algorithme de kosaraju dessus
        Graph().
        return true;
    }*/
    
    public void print(){ // afficher litterals et clauses pour vérifier le fonctionnement du constructeur
        System.out.println(litterals);
        System.out.println(clauses);
    }

}
