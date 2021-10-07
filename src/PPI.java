import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PPI {
    Graph G;
    ArrayList<LinkedList<Edge>> incidency;
    List<Integer> parcourus;
    LinkedList<Integer> Heuristique;
    LinkedList<Integer> newHeuristique;
    List<LinkedList<Integer>> SCC; // composantes fortement connexes
    LinkedList<Integer> scc; //composante fortement connexes
    int T; // temps d'itération

    public PPI(Graph graph, Optional<LinkedList<Integer>> heuristique){  // on peut éventuellement fournir une heuristique particulière au PPI (dans le deuxieme appel au ppi par l'algo de Kasaraju, par exemple)
        G = graph;
        parcourus = new ArrayList();
        if(heuristique.isPresent()){ Heuristique=heuristique.get();}
        else {
            Heuristique= new LinkedList<>();
            for (int i = 1; i <= G.getCardinal()/2 ; i++) {
                Heuristique.add(i);
                Heuristique.add(-i);
            }
        }
        newHeuristique= new LinkedList<>();
        SCC= new ArrayList<>();
        T=0;
        incidency =G.getIncidency();
        System.out.println("Heuristique: "+ Heuristique.toString());
        this.parcour();

    }

    public void parcour(){
        for(int s : Heuristique){
            if(parcourus.contains(s)){} //Si le sommet a déjà été parcouru, on ne fait rien
            else{
                scc= new LinkedList<>();
                scc.add(s);
                parcourus.add(s);
                for(int x : Heuristique){ // on veut parcourir les sommets accessibles selon l'ordre de priorité donné par l'heuristique
                    int pos = 2*Math.abs(s);
                    if(s<0){pos = pos-1;}          // on calcul l'indice du sommet dans incidency (cases impairs réservées aux négations)
                    else pos = pos-2;
                    LinkedList<Edge> arcs= incidency.get(pos); // On récupère la liste chainée des arcs partants du sommet exploré
                    for(Edge a:arcs){
                        if(a.destination==x){this.explore(a.destination);} // si la destination de a est le prochain sommet selon l'heuristique, on l'explore
                    }
                }
                newHeuristique.addFirst(s); // On ajoute le sommet dont on sort à la première position de la nouvelle heuristique
                SCC.add(scc); //On ajoute la composante fortement connexe à la liste des scc
            }
        }
    }

    public void explore(int sommet){
        if(parcourus.contains(sommet)){} // si le sommet a déjà été parcouru, on ne l'explore pas une seconde fois
        else {
            scc.add(sommet);
            parcourus.add(sommet);
            for(int x:Heuristique){ // on veut parcourir les sommets accessibles selon l'ordre de priorité donné par l'heuristique
                int pos = 2*Math.abs(sommet);
                if(sommet<0){pos = pos-1;}          // on calcul l'indice du sommet dans incidency (cases impairs réservées aux négations)
                else pos = pos-2;
                LinkedList<Edge> arcs= incidency.get(pos); // On récupère la liste chainée des arcs partants du sommet exploré
                for(Edge a:arcs){
                    if(a.destination==x){this.explore(a.destination);} // si la destination de a est le prochain sommet selon l'heuristique, on l'explore
                }
            }
            newHeuristique.addFirst(sommet);
        }
    }
//
    public LinkedList<Integer> getNewHeuristique(){
        return newHeuristique;
    }

    public List<LinkedList<Integer>> getSCC(){
        return SCC;
    }
}
