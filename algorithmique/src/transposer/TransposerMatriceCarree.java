package src.transposer;
/** - Pour compiler : javac -d ../build transposer/*.java
    - Pour executer : java -cp ../build src.transposer.TransposerMatriceCarree
 * TransposerMatriceCarree
 * Algo pour trouver la transposée d'une matrice carrée sans allouer un deuxième 
 * tableau, on doit modifier le tableau initial.
 * Cette classe utillise les paramètres génériques pour
 * donner à l'utilisateur la main sur le choix de type
 * de la liste.
 * Elle implemente l'interface IStrategy<T>
 * @param <T>
 */
public class TransposerMatriceCarree<T> implements IStrategy<T> {

    private T[][] matrice; // Un tableau de type générique T
    public TransposerMatriceCarree( T[][] obj)
    {
        this.matrice = obj;
    }

    /**
     * (non-Javadoc)
     * @see src.transposer.IStrategy#algo()
     * Elle ne retourne rien. Elle modifie le tableau initial.
     */
    @Override
    public void algo() {
        if(this.matrice.length == 1 || this.matrice.length == 0 || this.matrice == null)
            return ; // si la matrice est de taille 0 ou 1, on fait rien 
        else if(this.matrice.length != this.matrice[0].length)
        {
            System.out.println("Impossible de faire la transposé de cette Matrice, car n'est pas carrée.\n");
            return ; // Si la matrice n'est pas carrée, on affiche ce message et on quitte la fonction
        }
        else if (this.matrice.length == 2) { //Si la matrice est de taille 2, on faite la transposé et on quitte.
            T tmp = this.matrice[0][1];
            this.matrice[0][1] = this.matrice[1][0];
            this.matrice[1][0] = tmp;
            return;
        }
        // sinon, on applique l'algorithme suivant pour trouver la transposé de la matrice.
        for (int i = 0; i < this.matrice.length -1; i++) {
            for (int j = i+1; j < this.matrice.length ; j++) {
                T tmp = this.matrice[i][j];
                this.matrice[i][j] = this.matrice[j][i];
                this.matrice[j][i] = tmp;
            }
        }
    }
    @Override
    public String toString()
    {
        String tmp = "";
        for (int i = 0; i < this.matrice.length; i++) {
            for (int j = 0; j < this.matrice[i].length; j++) {
                tmp += this.matrice[i][j] + "  ";
            }
            tmp += "\n";
        }
        return tmp;
    }
    public static void main(String[] args) {
        Integer[][] Matrice = {{1,2,3,4},{0,0,0,0},{4,5,6,7},{8,9,0,0}};
        IStrategy<Integer> transposee = new TransposerMatriceCarree<Integer>(Matrice);
        System.out.println("Matrice initiale : \n"+transposee);
        transposee.algo();
        System.out.println("Matrice transposée : \n"+transposee);
    }
}