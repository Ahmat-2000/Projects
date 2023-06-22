<?php
class Computer
{
    protected $nom, $marque, $type;
    public function __construct($nom,$marque,$type)
    {
        $this->nom = $nom;
        $this->type = $type;
        $this->marque = $marque;
    }

    
    public function getNom()
    {
        return $this->nom;
    }

    public function getMarque()
    {
        return $this->marque;
    }

    public function getType()
    {
        return $this->type;
    }

    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    public function setMarque($marque)
    {
        $this->marque = $marque;
    }
    
    public function setType($type)
    {
        $this->type = $type;
    }
}
?>