package main;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

import animal.IAnimal;
import eaters.IEaters;

public class Main {
    /**
     * On utilise la réflection pour créer dynamiquement nos instance.
     * De cette manière, on gère les dépendance entre les classes.
     * Cette méthode permet à notre application de se fermer à la modification et de 
     * d'ouvrir à l'extension.
     * Pour modifier quelque chose, on modifie le fichier ../config/conf.txt
     * @param args
     */
    public static void main(String[] args) {
        try{
            // Scanner permet de lire le fichier conf.txt et returne les élément ligne par ligne
            // La méthode next() permet lire une ligne du fichier le met le curseur à la ligne suivante.
            Scanner file = new Scanner(new File("../config/conf.txt"));
            /*
             * l'objet Class nous permet de récupèrer une classe par son nom.
             * En appelant newInstance sur cette classe, on crée une nouvelle instance dymaniquement.
             * Cette retourne un élèment de type objet, on doit caster cette objet vers le type souhaité
             */
            String nom = file.next();
            Class<?> animalClass = Class.forName(nom); // on récupère la classe correspondante au nom écrit à la première ligne de conf.txt

            //IAnimal animal = (IAnimal) animalClass.newInstance() ; // On crée une instance de cette classe avec la méthode newInstance
            /*
             * La méthode newInstance() toute seul fonction mais elle est remplacée par getDeclaredConstructor().newInstance()
             * depuis Java 9
             * getDeclaredConstructors() et getDeclaredConstructor(Class<?>... types) : retournent le tabeau des constructeurs de cette classes, 
             * publics ou non, ou le constructeur qui prend en paramètre la liste des classes (donc des types) indiquée. 
             */
            IAnimal animal = (IAnimal) animalClass.getDeclaredConstructor().newInstance() ; // On crée une instance de cette classe avec la méthode newInstance

            /*Method eaterMethod = animalClass.getMethod("crie");
            eaterMethod.invoke(animal);*/
            nom = file.next();
            Class<?> eatersClass = Class.forName(nom);// on récupère la classe correspondante au nom écrit à la deuxième ligne de conf.txt

            IEaters eaters = (IEaters) eatersClass.getDeclaredConstructor().newInstance();// On crée une instance de cette classe avec la méthode newInstance

            Method eaterMethod = eatersClass.getMethod("setAnimal", new Class<?>[]{IAnimal.class});
            /*
             * On invoque (appeller) la méthode récupèrée par eaterMethod.
             * Le premier argument c'est l'objet sur lequel on appelle cette méthode.
             * Le sécond argument c'est un tableau d'objet qui contient les paramètre de la méthode
             */
            eaterMethod.invoke(eaters, new Object[]{animal});
            eaters.eat();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
