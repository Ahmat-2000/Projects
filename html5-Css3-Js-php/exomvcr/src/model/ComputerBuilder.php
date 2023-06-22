<?php
require_once('model/Computer.php');

/* Représente un computer en cours de construction via des formulaires */
class ComputerBuilder
{
    const NAME_REF ='NOM';
    const TYPE_REF = 'TYPE';
    const MARQUE_REF = 'MARQUE';
    private $data, $errors;

    /* Crée une nouvelle instance, avec les données passées en argument si
 	 * elles existent, et sinon avec
 	 * les valeurs par défaut des champs de création d'un Computer. */
    public function __construct($data=null)
    {
        if($data === null)
        {
            $data = array(
                self::NAME_REF => "",
                self::MARQUE_REF => "",
                self::TYPE_REF => "",
            );
        }
        $this->data = $data;
        $this->errors = null;
    }

	/*
    * Renvoie la valeur d'un champ en fonction de la référence passée en argument. 
    */
	public function getData($ref) {
		return key_exists($ref, $this->data)? $this->data[$ref]: '';
	}

    /* Renvoie les erreurs associées au champ de la référence passée en argument,
 	 * ou null s'il n'y a pas d'erreur ou si la validation n'a pas encore été faite. 
     */
    public function getErrors($ref) {
		return ($this->errors !== null && key_exists($ref, $this->errors)) ? $this->errors[$ref] : null;
	}
    /* Crée une nouvelle instance de Computer avec les données
	 * fournies. 
 	 * Nécessite d'avoir appelé isValid() auparavant, sinon une exception est lancée. 
     */
    public function createComputer()
    {
        if ($this->errors === null) {
			throw new Exception("Les données ne sont pas validées avant d'appeller createComputer");
		}
        return new Computer($this->data[self::NAME_REF],$this->data[self::MARQUE_REF],$this->data[self::TYPE_REF]);
    }

    /* Renvoie une nouvelle instance de ComputerBuilder avec les données
 	 * modifiables du PC passée en argument. */
    public static function buildFromComputer(Computer $computer) {
		return new ComputerBuilder(array(
			self::NAME_REF   => $computer->getNom(),
			self::MARQUE_REF => $computer->getMarque(),
            self::TYPE_REF   => $computer->getType(),
		));
	}
	/* Met à jour une instance de Computer avec les données
	 * fournies. */
	public function updateComputer(Computer $computer) {
		if (key_exists(self::NAME_REF, $this->data))
			$computer->setNom($this->data[self::NAME_REF]);
		if (key_exists(self::MARQUE_REF, $this->data))
			$computer->setMarque($this->data[self::MARQUE_REF]);
        if (key_exists(self::TYPE_REF, $this->data))
			$computer->setType($this->data[self::TYPE_REF]);
	}

    /* Vérifie la validité des données envoyées par le client,
	 * tout en construisant un tableau des erreurs à corriger. 
     * */
    public function isValid()
    {
        $this->errors = array();
		if ( ! key_exists(self::NAME_REF, $this->data) || trim($this->data[self::NAME_REF]) ==="")
			$this->errors[self::NAME_REF] = "* Vous devez entrer un nom valide";
		if ( ! key_exists(self::MARQUE_REF, $this->data) || trim($this->data[self::MARQUE_REF]) ==="") 
			$this->errors[self::MARQUE_REF] = "* Vous devez entrer une marque valide ";
        if ( ! key_exists(self::TYPE_REF, $this->data) ||trim($this->data[self::TYPE_REF]) ==="") 
            $this->errors[self::TYPE_REF] = "* Vous devez entrer le type de PC";
		return count($this->errors) === 0;
    }
}
?>