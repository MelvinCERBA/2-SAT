import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph<Label> {

    public class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }
    }

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;

    public ArrayList<LinkedList<Edge>> getIncidency() {
        return incidency;
    }
    public int getCardinal(){return  cardinal}

    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size+1);
        for (int i = 0;i<cardinal;i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }

    public Graph(SAT sat) {
        cardinal = sat.litterals.size();
        incidency = new ArrayList<LinkedList<Edge>>(cardinal + 1);
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
        return this;
    }

    /*public List<Integer> ppi_1(){ //premier parcours en profondeur itéré: rend un nouvel ordre de priorité entre les sommets
        return;
    }

    public List<List<Integer>> ppi_2(List<Integer>){ //deuxième ppi, utilise l'ordre de priorité donné par le ppi 1 et rend la liste des scc.
        return;
    }

    public

    public int order() {
        return cardinal;
    }*/

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
