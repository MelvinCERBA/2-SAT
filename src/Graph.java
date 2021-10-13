import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph<Label> {

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;

    public int getCardinal(){return cardinal;}
    public ArrayList<LinkedList<Edge>> getIncidency() {
        return incidency;
    }

    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size+1);
        for (int i = 0;i<cardinal;i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }

    public Graph(SAT sat) {
        int litMax=1;
        for(int k:sat.litterals){
            if(Math.abs(k)>litMax){
                litMax=Math.abs(k);                         // On détermine la taille de incidency (par ex une formule ne contenant que les littéraux [1, -1, 3, -3] a besoin d'une liste chainée de taille 6, car -3 est stocké à l'indice 5.
            }
        }
        cardinal = 2*litMax;
        incidency = new ArrayList<LinkedList<Edge>>();

        for (int i = 0; i < cardinal; i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
        for (List<Integer> clause : sat.clauses) {   //pour chaque clause de la formule,
            for (int j = 0; j <=1; j++) {            //Pour chacun des deux littéraux de la clause,
                int lit = clause.get(j);
                Edge newArc1 = new Edge(-clause.get(j), clause.get(1-j),(Label) ""); // -> arc correspondant à l'implication -x => y pour j = 0 et -y => x pour j=1 ( pour la clause [xUy])
                int i = 2*Math.abs(lit);
                if(lit<0){i = i-1;}      // les implications dont le premier littéral est une négations sont contenues dans les cases d'indice impair, car il n'existe pas d'indice négatif )
                else i = i-2;
                if (incidency.get(i).contains(newArc1)) {}  // On vérifie que l'arc n'existe pas déjà
                else{
                    addArc(-clause.get(j), clause.get(1-j),(Label) "");   //On ajoute cet arcs au graphe
                }

            }
        }
    }

    public Graph mirror(){ // à implémenter
        Graph G= new Graph(cardinal);
        for(LinkedList<Edge> l: incidency){
            for(Edge e:l){
                G.addArc(e.destination,e.source,e.label);  // Pour chaque arc du graph actuel, on rajoute l'arc opposé au graphe mirroir
            }
        }
        return this;
    }


    public void addArc(int source, int dest, Label label) {
        int i = 2*Math.abs(source);
        if(source<0){i = i-1;}          // on place les négations dans les cases d'indice impair, car il n'existe pas d'indice négatif
        else i = i-2;                      // on place par exemple 1 à l'indice 0, -1 à l'indice 1
        incidency.get(i).addLast(new Edge(source,dest,label));
    }

    public String toString() {
        String result = new String("");
        result = result.concat("Nombre sommets : " + cardinal + "\n");
        result = result.concat("Sommets : \n");
        for (int i = 0; i<cardinal;i++) {
	    result = result.concat(i + " ");
		}
        result = result.concat("\nArcs : \n");
        for (int i = 0; i<cardinal;i++) {
            for (Edge e : incidency.get(i)) {
                result = result.concat(e.source + " -> " + e.destination + ", étiquette : "
				       + e.label.toString() + "\n");
            }
        }
        return result;
	
    }
    
}
