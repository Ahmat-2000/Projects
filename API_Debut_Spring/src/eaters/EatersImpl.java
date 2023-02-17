package eaters;

import animal.IAnimal;

public class EatersImpl implements IEaters{

    private IAnimal animal;
    public EatersImpl()
    {
    }
    public void setAnimal(IAnimal animal)
    {
        this.animal = animal;
    }
    @Override
    public void eat() {
        System.out.println("\nJe mange "+animal.toString());
        System.out.println("C'est délicieux :)");
        animal.crie();
    }
    
}
