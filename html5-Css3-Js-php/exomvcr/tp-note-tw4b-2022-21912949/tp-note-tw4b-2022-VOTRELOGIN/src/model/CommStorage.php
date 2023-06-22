<?php

require_once("model/Comm.php");

interface CommStorage {

	/* Insère un nouveau commentaire dans la base. Renvoie l'identifiant
	 * du nouveau commentaire. */
	public function create(Comm $c);

	/* Renvoie le commentaire d'identifiant $id, ou null
	 * si l'identifiant ne correspond à aucun comm. */
	public function read($id);

	/* Renvoie un tableau associatif id => Comm
	 * contenant tous les commentaires de la base. */
	public function readAll();

	/* Remet la base dans l'état initial. Utile pour débugger,
 	 * pas très utile en prod. */
	public function reinit();

}

