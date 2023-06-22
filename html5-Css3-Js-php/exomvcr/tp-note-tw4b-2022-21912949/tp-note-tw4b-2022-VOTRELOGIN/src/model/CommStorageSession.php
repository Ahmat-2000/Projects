<?php

require_once("model/Comm.php");
require_once("model/CommStorage.php");

/*
 * Gère le stockage de commentaires dans un tableau stocké en session.
 * À peu près complètement inutile en prod : chaque internaute ne peut
 * voir que ses propres commentaires !
 */
class CommStorageSession implements CommStorage {

	private $db;

	/* Construit une nouvelle instance. */
	public function __construct() {
		if (session_status() !== PHP_SESSION_ACTIVE) {
			throw new Exception("No session started (or sessions disabled), cannot use CommStorageSession");
		}
		if (key_exists('comm_storage_db', $_SESSION)) {
			$this->db = $_SESSION['comm_storage_db'];
		} else {
			$this->reinit();
		}
	}

	/* Enregistre les modifications en session avant de disparaître. */
	public function __destruct() {
		$_SESSION['comm_storage_db'] = $this->db;
	}

	/* Insère un nouveau commentaire dans la base. Renvoie l'identifiant
	 * du nouveau commentaire. */
	public function create(Comm $c) {
		$this->db[] = $c;
		$keys = array_keys($this->db);
        	return end($keys);
	}

	/* Renvoie le commentaire d'identifiant $id, ou null
	 * si l'identifiant ne correspond à aucun comm. */
	public function read($id) {
		if (key_exists($id, $this->db)) {
			return $this->db[$id];
        } else {
			return null;
        }
	}

	/* Renvoie un tableau associatif id => Comm
	 * contenant tous les commentaires de la base. */
	public function readAll() {
		return $this->db;
	}

	/* Remet la base dans l'état initial. Utile pour débugger,
 	 * pas très utile en prod. */
	public function reinit() {
		/* On remplit avec des commentaires bidon
		 * pour que ce soit plus vivant. */
		$this->db = array(
			new Comm("toto",  "C'est mon premier commentaire."),
			new Comm("Jean-Mimi",  "J'adore ce site !"),
		);
	}

}

