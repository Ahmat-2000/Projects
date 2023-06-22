<?php

require_once("model/Comm.php");

/* Représente un commentaire en cours de construction via des formulaires */
class CommBuilder {

	const AUTHOR_REF = 'author';
	const TEXT_REF = 'text';

	protected $data;
	protected $errors;

	/* Crée une nouvelle instance, avec les données passées en argument si
 	 * elles existent, et sinon avec
 	 * les valeurs par défaut des champs de création d'un comm. */
	public function __construct($data=null) {
		if ($data === null) {
			$data = array(
				self::AUTHOR_REF => "",
				self::TEXT_REF => "",
			);
		}
		$this->data = $data;
		$this->errors = null;
	}

	/* Vérifie la validité des données envoyées par le client,
	 * tout en construisant un tableau des erreurs à corriger. */
	public function isValid() {
		$this->errors = array();
		if ( ! key_exists(self::AUTHOR_REF, $this->data) || $this->data[self::AUTHOR_REF] === "")
			$this->errors[self::AUTHOR_REF] = "Vous devez entrer un auteur";
		if ( ! key_exists(self::TEXT_REF, $this->data) || mb_strlen($this->data[self::TEXT_REF]) < 10)
			$this->errors[self::TEXT_REF] = "Votre commentaire doit faire au moins 10 caractères";
		return count($this->errors) === 0;
	}

	/* Renvoie la valeur d'un champ en fonction de la référence passée en argument. */
	public function getData($ref) {
		return key_exists($ref, $this->data)? $this->data[$ref]: '';
	}

	/* Renvoie les erreurs associées au champ de la référence passée en argument,
 	 * ou null s'il n'y a pas d'erreur ou si la validation n'a pas encore été faite. */
	public function getErrors($ref) {
		return ($this->errors !== null && key_exists($ref, $this->errors)) ? $this->errors[$ref] : null;
	}

	/* Crée une nouvelle instance de Comm avec les données
	 * fournies. 
 	 * Nécessite d'avoir appelé isValid() auparavant, sinon une exception est lancée. */
	public function createComm() {
		if ($this->errors === null) {
			throw new Exception("Data was not validated before call to createComm");
		}
		return new Comm($this->data[self::AUTHOR_REF], $this->data[self::TEXT_REF]);
	}

}

?>
