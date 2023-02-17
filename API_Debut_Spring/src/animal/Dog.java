package animal;
/**
 * Dog
 */
public class Dog implements IAnimal {
    private String name;
    public Dog()
    {
        name = "Le chien Tom";
    }
    public void setName(String name)
    {
        this.name = name;
    }
    @Override
    public void crie()
    {
        System.out.println("\n"+this.name+" , arrêter de me dévorer !!");
    }
    @Override
    public String toString() {
        return this.name;
    }
}
