<?php
require_once('model/Computer.php');
require_once('model/ComputerBuilder.php');
require_once('model/ComputerStorage.php');
require_once('view/View.php');
class Controller
{
    private $view, $store;
    public function __construct(View $view, ComputerStorage $store)
    {
        $this->store = $store;
        $this->view = $view;
        $this->currentComputerBuilder = key_exists('currentComputerBuilder', $_SESSION) ? $_SESSION['currentComputerBuilder'] : null;
		$this->modifiedComputerBuilders = key_exists('modifiedComputerBuilders', $_SESSION) ? $_SESSION['modifiedComputerBuilders'] : array();
    }

    /* On en registre ces deux attributs en session avant de détruire l'objet courant */
    public function __destruct() {
		$_SESSION['currentComputerBuilder'] = $this->currentComputerBuilder;
		$_SESSION['modifiedComputerBuilders'] = $this->modifiedComputerBuilders;
	}

    public function showList()
    {
        $computerList = $this->store->readAll();
        $this->view->makeListPage($computerList);
    }
    public function showListSearch($nom)
    {
        $computerList = $this->store->search($nom);
        if($computerList === null){
            $this->view->makeUnknownSearchPage();
        }
        else
            $this->view->makeListPage($computerList);
    }
    public function showInformation($id) 
    {
        /* Un PC est demandé, on le récupère en BD */
        $computerList = $this->store->read($id);
        if($computerList === null)
        {
             /* Le PC n'existe pas en BD */
             $this->view->makeUnknownComputerPage();  
        }   
        else
        {
            /* Le PC existe, on prépare la page */
            $this->view->makeComputerPage($id,$computerList); 
        }
    }
    public function newAnimal()
    {
        /* 
        * Affichage du formulaire de création
		* avec les données par défaut. 
        */
        if ($this->currentComputerBuilder === null) {
			$this->currentComputerBuilder = new ComputerBuilder();
		}
		$this->view->makeComputerCreationPage($this->currentComputerBuilder);
    }
    public function saveNewComputer(array $data)
    {
        /* On construit le builder courant avec les données de $data */
		$this->currentComputerBuilder = new ComputerBuilder($data);
        if ($this->currentComputerBuilder->isValid()) {
			/* On construit le nouveau PC */
			$computer = $this->currentComputerBuilder->createComputer();
			/* On l'ajoute en BD */
			$id = $this->store->create($computer);
			/* On détruit le builder courant */
			$this->currentComputerBuilder = null;
			/* On redirige vers la page de nouveau PC */
			$this->view->displayComputerCreationSuccess($id);
		} else {
            /* Si y'a une erreur dans le formulaire, on redirige vers la page 
            *  du formulaire avec les erreurs
            */
			$this->view->displayComputerCreationFailure();
		}
    }
    public function deleteComputer($id)
    {
        /* On récupére le PC de la base de donné */
        $computer = $this->store->read($id);
        if($computer === null)
        {
            /* Si le PC n'exite pas dans la base, on affiche la page d'erreur */
            $this->view->makeUnknownComputerPage();
        }
        else
        {
            /* Si le PC exite, on affiche sa page */
            $this->view->makeComputerDeletionPage($id);
        }
    }
    public function confirmComputerDeletion($id)
    {
        /* L'utilisateur confirme vouloir supprimer
		* le PC. On essaie. */
		$delete = $this->store->delete($id);
		if (!$delete) {
			/* Le PC n'existe pas en BD */
			$this->view->makeUnknownComputerPage();
		} else {
			/* Tout s'est bien passé */
			$this->view->displayComputerDeleteSuccess();
		}
    }
    public function modifyComputer($id)
    {
        if (key_exists($id, $this->modifiedComputerBuilders)) {
            /* Préparation de la page de formulaire */
            $this->view->makeComputerUpdatePage($id, $this->modifiedComputerBuilders[$id]);
        } else {
            /* On récupère en BD le PC à modifier */
            $computer = $this->store->read($id);
            if ($computer === null) {
                $this->view->makeUnknowncomputerPage();
            } else {
                /* Extraction des données modifiables */
                $builder = computerBuilder::buildFromcomputer($computer);
                /* Préparation de la page de formulaire */
                $this->view->makeComputerUpdatePage($id, $builder);
            }
        }
    }
    public function saveComputerMOdification($id,array $obj)
    {
        /* On récupère en BD le PC à modifier */
		$computer = $this->store->read($id);
		if ($computer === null) {
			/* Le PC n'existe pas en BD */
			$this->view->makeUnknowncomputerPage();
		} else {
			$builder = new computerBuilder($obj);
			/* Validation des données */
			if ($builder->isValid()) {
				/* Modification du PC */
				$builder->updateComputer($computer);
				/* On essaie de mettre à jour en BD.
				* Normalement ça devrait marcher (on vient de
				* récupérer le PC). */
				$update = $this->store->update($id, $computer);
				if (!$update)
					throw new Exception("Identifier has disappeared?!");
				/* Redirection vers la page du PC */
				unset($this->modifiedComputerBuilders[$id]);
				$this->view->displayComputerModifySuccess($id);
			} else {
				$this->modifiedComputerBuilders[$id] = $builder;
				$this->view->displayComputerModifyFailure($id);
			}
		}
    }
}
?>