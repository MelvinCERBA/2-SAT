import java.util.ArrayList;
import java.util.List;

public class Kasaraju {
    Graph G;
    List<Integer> sommetsParcourus;
    List<Integer> heuristique;




    public Kasaraju(Graph graph){
        G = graph;
        sommetsParcourus = new ArrayList();
        for(int i=0; i<= G.getCardinal()-1; i++){
            heuristique.add(i);
        }

        for(int sommet : heuristique){
            if(sommetsParcourus.contains(sommet)){}
            else
                clock[sommet][0] = t++
                sommetsParcourus.add(sommet);

        }
    }

    private int[] ppi_1(){ //première utilisation du ppi, rend une nouvelle heuristique
        int[][] clock; // [[temps d'entrée sommet 1, temps de sortie sommet 1], [...], ...]
        int t;
        int it;


    }

    private List<List<Integer>> ppi_2(int[] heuristique){ // deuxième utiisation du ppi, rend les SCC
        for(int sommet : heuristique){
            if(sommetsParcourus.contains(sommet)){}
            else
                clock[sommet][0] = t++
            sommetsParcourus.add(sommet);

        }
    }
}
