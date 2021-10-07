import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PPI {
    Graph G;
    ArrayList<LinkedList<Edge>> incidency;
    List<Integer> parcourus;
    LinkedList<Integer> Heuristique; // heuristique selon laquelle on va parcourir le graphe
    LinkedList<Integer> newHeuristique;  // nouvelle heuristique, obtenue en parcourant le graphe
    List<LinkedList<Integer>> SCC; // composantes fortement connexes
    LinkedList<Integer> scc; //composante fortement connexes
    int T; // temps d'itération

    public PPI(Graph graph, Optional<LinkedList<Integer>> heuristique){  // on peut éventuellement fournir une heuristique particulière au PPI (dans le deuxieme appel au ppi par l'algo de Kasaraju, par exemple)
        G = graph;
        parcourus = new ArrayList();
        if(heuristique.isPresent()){ Heuristique=heuristique.get();} // Si on nous a fourni une heuristique, on l'utilise
        else {
            Heuristique= new LinkedList<>();
            for (int i = 1; i <= G.getCardinal()/2 ; i++) {  // Sinon, on utilise une heuristique standard : [1 ,-1 ,2 ,-2 , ...]
                Heuristique.add(i);
                Heuristique.add(-i);
            }
        }
        newHeuristique= new LinkedList<>(); // On initialise la nouvelle heuristique pour pouvoir la remplir pendant qu'on parcourt le graphe
        SCC= new ArrayList<>();  // Pareil pour la liste des composantes fortement connexes
        incidency =G.getIncidency(); // On récupère incidency (la liste des listes chainées des arcs sortants de chaque sommet)
        //System.out.println("Heuristique: "+ Heuristique.toString());

        this.parcour(); // On parcourt le graphe pour définir la nouvelle heuristique et la liste des SCC
    }

    public void parcour(){
        for(int s : Heuristique){
            if(parcourus.contains(s)){} //Si le sommet a déjà été parcouru, on ne l'explore pas
            else{
                scc= new LinkedList<>(); // On crée une nouvelle composante connexe
                scc.add(s);              // et on ajoute le sommet parcouru à celle-ci

                parcourus.add(s);  // On ajoute le sommet aux parcourus pour ne pas revenir dessus plus tard

                for(int x : Heuristique){ // on veut parcourir les sommets accessibles selon l'ordre de priorité donné par l'heuristique
                    int pos = 2*Math.abs(s);       //
                    if(s<0){pos = pos-1;}          // <- on calcule l'indice du sommet dans incidency (cases impairs réservées aux négations)
                    else pos = pos-2;              //
                    LinkedList<Edge> arcs= incidency.get(pos); // On récupère la liste chainée des arcs partants du sommet exploré
                    for(Edge a:arcs){
                        if(a.destination==x){this.explore(a.destination);} // si la destination de l'arc a est le prochain sommet selon l'heuristique, on l'explore
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
            scc.add(sommet);  // On ajoute le sommet à la composante fortement connexe courante
            parcourus.add(sommet); // On ajoute le sommet aux parcourus


            for(int x:Heuristique){ // on veut parcourir les sommets accessibles selon l'ordre de priorité donné par l'heuristique
                int pos = 2*Math.abs(sommet);       //
                if(sommet<0){pos = pos-1;}          // <- on calcul l'indice du sommet dans incidency (cases impairs réservées aux négations)
                else pos = pos-2;                   //
                LinkedList<Edge> arcs= incidency.get(pos); // On récupère la liste chainée des arcs partants du sommet exploré
                for(Edge a:arcs){
                    if(a.destination==x){this.explore(a.destination);} // si la destination de a est le prochain sommet selon l'heuristique, on l'explore
                }
            }
            newHeuristique.addFirst(sommet); // On ajoute le sommet dont on sort à la première position de la nouvelle heuristique
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
