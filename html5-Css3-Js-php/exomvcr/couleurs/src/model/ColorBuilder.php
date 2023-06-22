<?php

require_once("model/Color.php");

/* Fonctions de manipulation des couleurs via des formulaires */
class ColorBuilder {

	protected $data;
	protected $errors;

	/* Crée une nouvelle instance, avec les données passées en argument si
 	 * elles existent, et sinon avec
 	 * les valeurs par défaut des champs de création d'une couleur. */
	public function __construct($data=null) {
		if ($data === null) {
			$data = array(
				"name" => "",
				/* valeur par défaut du code hexa tirée au hasard
				 * pour rendre les tests plus agréables */
				"hex" => sprintf("%06x", rand(0, 256*256*256)),
			);
		}
		$this->data = $data;
		$this->errors = array();
	}

	/* Renvoie une nouvelle instance de ColorBuilder avec les données
 	 * modifiables de la couleur passée en argument. */
	public static function buildFromColor(Color $color) {
		return new ColorBuilder(array(
			"name" => $color->getName(),
			"hex" => $color->getHex(),
		));
	}

	/* Vérifie la validité des données envoyées par le client,
	 * et renvoie un tableau des erreurs à corriger. */
	public function isValid() {
		$this->errors = array();
		if (!key_exists("name", $this->data) || $this->data["name"] === "")
			$this->errors["name"] = "Vous devez entrer un nom";
		else if (mb_strlen($this->data["name"], 'UTF-8') >= 30)
			$this->errors["name"] = "Le nom doit faire moins de 30 caractères";
		if (!key_exists("hex", $this->data) || $this->data["hex"] === "")
			$this->errors["hex"] = "Vous devez entrer une couleur";
		else if (!preg_match("/^[0-9a-f]*$/i", $this->data["hex"]))
			$this->errors["hex"] = "Caractères autorisés : 0123456789ABCDEF";
		else if (!preg_match("/^......$/i", $this->data["hex"]))
			$this->errors["hex"] = "Vous devez entrer exactement 6 chiffres hexa";
		return count($this->errors) === 0;
	}

	/* Renvoie la «référence» du champ représentant le nom d'une couleur. */
	public function getNameRef() {
		return "name";
	}

	/* Renvoie la «référence» du champ représentant le code hexa d'une couleur. */
	public function getHexRef() {
		return "hex";
	}

	/* Renvoie la valeur d'un champ en fonction de la référence passée en argument. */
	public function getData($ref) {
		return key_exists($ref, $this->data)? $this->data[$ref]: '';
	}

	/* Renvoie les erreurs associées au champ de la référence passée en argument,
 	 * ou null s'il n'y a pas d'erreur.
 	 * Nécessite d'avoir appelé isValid() auparavant. */
	public function getErrors($ref) {
		return key_exists($ref, $this->errors)? $this->errors[$ref]: null;
	}

	/* Crée une nouvelle instance de Color avec les données
	 * fournies. Si toutes ne sont pas présentes, une exception
	 * est lancée. */
	public function createColor() {
		if (!key_exists("name", $this->data) || !key_exists("hex", $this->data))
			throw new Exception("Missing fields for color creation");
		return new Color($this->data["name"], $this->data["hex"]);
	}

	/* Met à jour une instance de Color avec les données
	 * fournies. */
	public function updateColor(Color $c) {
		if (key_exists("name", $this->data))
			$c->setName($this->data["name"]);
		if (key_exists("hex", $this->data))
			$c->setHex($this->data["hex"]);
	}

}

?>
