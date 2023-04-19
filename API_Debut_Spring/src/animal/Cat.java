package animal;

/**
 * Cat
 */
public class Cat implements IAnimal {
    private String name;
    public Cat()
    {
        name = "Le chat Jerry";
    }
    @Override
    public String toString() {
        return this.name;
    }
    /**
     * Setter de l'attribut name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    @Override
    public void crie()
    {
        System.out.println("\n"+this.name+" , arrêter de me dévorer !!");
    }
}