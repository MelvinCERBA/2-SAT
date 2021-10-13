import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Kasaraju {
    Graph G;
    List<List<Integer>> SCC; // composantes fortement connexes

    public Kasaraju(Graph g){
        G=g;
    }

    public boolean isSatisfiable(){
        PPI ppi_1 = new PPI(G, Optional.empty()); // On appelle le ppi une première fois pour obtenir une nouvelle heuristique
        PPI ppi_2 = new PPI(G.mirror(),Optional.of(ppi_1.getNewHeuristique()));  // On appelle le ppi sur le graphe miroir avec la nouvelle heuristique

        for(LinkedList<Integer> scc: ppi_2.getSCC()){ // On test la présence d'un littéral et de sa négation dans les SCC rendues par le deuxième appel au ppi.
            for(int i:scc){
                for(int j:scc){
                    System.out.println("i= " + i+ "j= " + j);
                    if(i==-j){return false;}  // Si P et nonP sont dans la meme SCC, la formule n'est pas satisfiable.
                }
            }
        }
        return true; //Sinon, la formule est satisfiable.
    }
}
