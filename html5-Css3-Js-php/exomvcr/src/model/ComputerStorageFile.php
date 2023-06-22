<?php
require_once('lib/ObjectFileDB.php');
require_once('model/ComputerStorage.php');
require_once('model/Computer.php');
class ComputerStorageFile implements ComputerStorage
{
    private $db;
    
    /* Construit une nouvelle instance, qui utilise le fichier donné
	 * en paramètre. */
    public function __construct(ObjectFileDB $base)
    {
        /* le ObjectFileDB dans lequel l'instance est enregistrée */
        $this->db = $base;
    }
    public function reinit()
    {
        $computersTab = array(
            'tuf' => new Computer('TUF GAMING', 'ASUS','portable'),
            'pavilion' => new Computer('PAVILION GAMING', 'PHP', 'portable'),
            'slim' => new Computer('SLIM DESKTOP S01', 'PHP', 'POSTE'),
        );
        foreach($computersTab as $key => $value)
        {
            $this->db->insert($value);
        }
    }

	
	/* Insère une nouveau PC  dans la base. 
	 * Renvoie l'identifiant de la nouveau PC. */
	public function create(Computer $c) {
        return $this->db->insert($c);
	}

	/* Renvoie le PC d'identifiant $id ou null
	 * si l'identifiant ne correspond à aucun PC. */
	public function read($id) {
		if ($this->db->exists($id)) {
			return $this->db->fetch($id);
        } else {
			return null;
        }
	}

	/* Renvoie un tableau associatif id => Color
	 * contenant tous les PC de la base. */
	public function readAll() {
		return $this->db->fetchAll();
	}

	/* Met à jour un PC dans la base. Renvoie
	 * true si la modification a été effectuée, false
	 * si l'identifiant ne correspond à aucun PC. */
	public function update($id, Computer $c) {
		if ($this->db->exists($id)) {
            $this->db->update($id, $c);
			return true;
		}
		return false;
	}

	/* Supprime un PC. Renvoie
	 * true si la suppression a été effectuée, false
	 * si l'identifiant ne correspond à aucun PC. */
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