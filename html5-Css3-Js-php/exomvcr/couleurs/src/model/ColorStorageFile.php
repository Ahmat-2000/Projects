<?php

require_once("lib/ObjectFileDB.php");
require_once("model/Color.php");
require_once("model/ColorStorage.php");




/*
 * Gère le stockage de couleurs dans un fichier.
 * Plus simple que l'utilisation d'une base de données,
 * car notre application est très simple.
 */

class ColorStorageFile implements ColorStorage {

	/* le ObjectFileDB dans lequel l'instance est enregistrée */
	private $db;

	/* Construit une nouvelle instance, qui utilise le fichier donné
	 * en paramètre. */
	public function __construct($file) {
		$this->db = new ObjectFileDB($file);
	}

	/* Insère une nouvelle couleur dans la base. Renvoie l'identifiant
	 * de la nouvelle couleur. */
	public function create(Color $c) {
        return $this->db->insert($c);
	}

	/* Renvoie la couleur d'identifiant $id, ou null
	 * si l'identifiant ne correspond à aucune couleur. */
	public function read($id) {
		if ($this->db->exists($id)) {
			return $this->db->fetch($id);
        } else {
			return null;
        }
	}

	/* Renvoie un tableau associatif id => Color
	 * contenant toutes les couleurs de la base. */
	public function readAll() {
		return $this->db->fetchAll();
	}

	/* Met à jour une couleur dans la base. Renvoie
	 * true si la modification a été effectuée, false
	 * si l'identifiant ne correspond à aucune couleur. */
	public function update($id, Color $c) {
		if ($this->db->exists($id)) {
            $this->db->update($id, $c);
			return true;
		}
		return false;
	}

	/* Supprime une couleur. Renvoie
	 * true si la suppression a été effectuée, false
	 * si l'identifiant ne correspond à aucune couleur. */
	public function delete($id) {
		if ($this->db->exists($id)) {
			$this->db->delete($id);
			return true;
		}
		return false;
	}

	/* Vide la base. */
	public function deleteAll() {
        $this->db->deleteAll();
	}
}

?>
