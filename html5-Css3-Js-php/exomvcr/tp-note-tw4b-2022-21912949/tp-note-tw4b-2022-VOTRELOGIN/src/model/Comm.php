<?php

/* Représente un commentaire. */
class Comm {

	protected $author;
	protected $text;
	protected $creationDate;

	/* Construit un comm. Si les paramètres de date ne sont pas passés,
	 * le comm est considéré comme étant nouveau. */
	public function __construct($author, $text, $creationDate=null) {
		$this->author = $author;
		$this->text = $text;
		$this->creationDate = $creationDate !== null? $creationDate: new DateTime();
	}

	public function getAuthor() {
		return $this->author;
	}

	public function getText() {
		return $this->text;
	}
	/* Renvoie un objet DateTime correspondant à
	 * la création du commentaire. */
	public function getCreationDate() {
		return $this->creationDate;
	}
}

?>
