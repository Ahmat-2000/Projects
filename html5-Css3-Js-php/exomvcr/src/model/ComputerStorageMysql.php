<?php
require_once 'model/ComputerStorage.php';
require_once 'model/Computer.php';

class ComputerStorageMysql implements ComputerStorage
{
    protected $pdo;
    
    public function __construct(PDO $sql)
    {
        $this->pdo = $sql;
        /*------- Gestion des erreurs
        PDO::ERRMODE_WARNING : émet un message de niveau E_WARNING en cas d'erreur
        PDO::ERRMODE_EXCEPTION : renvoie une PDOException avec code et information sur l'erreur
        */
        $this->pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }

    /* Renvoie le PC d'identifiant $id ou null
	 * si l'identifiant ne correspond à aucun PC. */
    public function read($id)
    {
        $baseTable = $this->readAll();
        if(key_exists($id,$baseTable))
        {
            return $baseTable[$id];
        }
        return null;
    }

    /* Renvoie un tableau associatif id => Color
	 * contenant tous les PC de la base. */
    public function readAll()
    {
        $baseTable = array();
        $query = $this->pdo->query("SELECT * From computers;");
        $brutData = $query->fetchAll(PDO::FETCH_ASSOC);    // PDO::FETCH_ASSOC pour changer le mode de recupération des bonnées brutes
        foreach($brutData as $key => $value)
        {
            $baseTable[$value["id"]] = new Computer($value["nom"],$value["marque"],$value["typePc"]);
        }
        $query->closeCursor();
        return $baseTable;
    }

    /* Insère une nouveau PC  dans la base. 
     * Renvoie l'identifiant de la nouveau PC. */
    public function create(Computer $c)
    {
        /*$query = $this->pdo->query("INSERT INTO computers (nom,marque,typePc) 
            VALUES ('".$c->getNom()."','".$c->getMarque()."','".$c->getType()."');");  */
        $nom = $c->getNom(); $marque = $c->getMarque(); $type = $c->getType();
        $prepareStat = $this->pdo->prepare("INSERT INTO computers (nom,marque,typePc) 
                    VALUES (:nom,:marque,:typePc);") ;
        $prepareStat->bindValue(':nom',$nom); 
        $prepareStat->bindValue(':marque',$marque); 
        $prepareStat->bindValue(':typePc',$type);
        $prepareStat->execute();
        $prepareStat->closeCursor();
        return $this->pdo->lastInsertId();
    }
   
	/* Met à jour un PC dans la base. Renvoie
	 * true si la modification a été effectuée, false
	 * si l'identifiant ne correspond à aucun PC. */
	public function update($id, Computer $c) {
        $nom = $c->getNom(); $marque = $c->getMarque(); $type = $c->getType();
        $prepareStat = $this->pdo->prepare("UPDATE computers SET nom=:nom,
            marque=:marque,typePc=:typePc    
            WHERE id=:id;") ;
        $prepareStat->bindValue(':nom',$nom); 
        $prepareStat->bindValue(':marque',$marque); 
        $prepareStat->bindValue(':typePc',$type);
        $prepareStat->bindValue(':id',$id,PDO::PARAM_INT); 
        if ($prepareStat->execute()) {
			return true;
		}
		return false;
	}

	/* Supprime un PC. Renvoie
	 * true si la suppression a été effectuée, false
	 * si l'identifiant ne correspond à aucun PC. */
	public function delete($id) {
        $prepareStat = $this->pdo->prepare("DELETE FROM computers WHERE id=:id;") ;
        $prepareStat->bindValue(':id',$id,PDO::PARAM_INT); 
        if ($prepareStat->execute()) {
			return true;
		}
		return false;
	}

	/* Vide la table sans la supprimée. */
	public function deleteAll() {
        $query = $this->pdo->query("DELETE FROM computers ;");
        $query->closeCursor();
	}

    /* Recherche d'un PC par son nom */
    public function search($nom){
        $baseTable = array();
        $prepareStat = $this->pdo->prepare("SELECT * FROM computers WHERE nom=:nom;") ;
        $prepareStat->bindValue(':nom',$nom); 
        if ($prepareStat->execute() && $prepareStat->fetchAll() !== null) {
            $brutData = $prepareStat->fetchAll(PDO::FETCH_ASSOC);    // PDO::FETCH_ASSOC pour changer le mode de recupération des bonnées brutes
            foreach($brutData as $key => $value)
            {
                $baseTable[$value["id"]] = new Computer($value["nom"],$value["marque"],$value["typePc"]);
            }
            if(count($baseTable) === 0)
                return null;
            return $baseTable;
		}
		return null;
    }
}
?>