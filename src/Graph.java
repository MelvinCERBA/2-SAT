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
        for (int lit : sat.litterals) {                   //Pour chaque littéral de la formule,
            for (List<Integer> clause : sat.clauses) {   //pour chaque clause de la formule,
                if (clause.get(0) == lit) {              //si la clause commence par le littéral sélectionné:
                    Edge newArc1 = new Edge(-clause.get(0), clause.get(1),(Label) ""); // -> arc correspondant à l'implication -x => y pour la clause [xUy]
                    int i = lit;
                    if(lit<0){lit = -(2*lit); }      // ( on place les négations dans les cases d'indice pair, car il n'existe pas d'indice négatif )
                    if (incidency.get(i).contains(newArc1)) {}  // et que les arcs correspondants à cette clause n'existent pas ( il suffit de vérifier l'une des deux implication d'une clause pour savoir si les deux sont déjà présentes
                    else{
                        addArc(-clause.get(0), clause.get(1), (Label) "");   //alors on ajoute ces arcs au graphe
                        addArc(-clause.get(1), clause.get(0), (Label) "");
                    }

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
        int i = source;
        if(source<0){i = -(2*i);}          // on place les négations dans les cases d'indice pair, car il n'existe pas d'indice négatif
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
