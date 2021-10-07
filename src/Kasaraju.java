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
        PPI ppi_1 = new PPI(G, Optional.empty());
        PPI ppi_2 = new PPI(G.mirror(),Optional.of(ppi_1.getNewHeuristique()));

        for(LinkedList<Integer> scc: ppi_2.getSCC()){
            for(int i:scc){
                for(int j:scc){
                    System.out.println("i= " + i+ "j= " + j);
                    if(i==-j){return false;}
                }
            }
        }
        return true;
    }
}
