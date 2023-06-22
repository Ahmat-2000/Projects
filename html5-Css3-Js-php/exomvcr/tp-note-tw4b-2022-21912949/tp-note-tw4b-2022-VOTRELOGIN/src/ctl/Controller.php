<?php


/* Inclusion des classes nécessaires */
require_once("model/Comm.php");
require_once("model/CommStorage.php");
require_once("model/CommBuilder.php");
require_once("view/MainView.php");


class Controller {

	protected $v;
	protected $commStorage;

	public function __construct(MainView $view, CommStorage $commStorage) {
		$this->v = $view;
		$this->commStorage = $commStorage;
		$this->currentCommBuilder = key_exists('currentCommBuilder', $_SESSION) ? $_SESSION['currentCommBuilder'] : null;
		$this->modifiedColorBuilders = key_exists('modifiedColorBuilders', $_SESSION) ? $_SESSION['modifiedColorBuilders'] : array();
	}

	public function __destruct() {
		$_SESSION['currentCommBuilder'] = $this->currentCommBuilder;
		$_SESSION['modifiedColorBuilders'] = $this->modifiedColorBuilders;
	}

	public function listPage() {
		$comms = $this->commStorage->readAll();
		$this->v->makeListPage($comms);
	}

	public function newCommPage() {
		if ($this->currentCommBuilder === null) {
			$this->currentCommBuilder = new CommBuilder();
		}
		$this->v->makeCommCreationPage($this->currentCommBuilder);
	}

	public function saveNewComm(array $data) {
		//$commBuider = new CommBuilder($data);
		$this->currentCommBuilder = new CommBuilder($data);
        if(!$this->currentCommBuilder->isValid())
        {
            $this->v->makeCommCreationPage($this->currentCommBuilder);
            $this->v->displayCommNotCreated();
        }
        else{
            $comm = $this->currentCommBuilder->createComm();
            $id = $this->commStorage->create($comm);
            $this->currentCommBuilder = null;
            $this->v->displayCommCreated($id);
        }
	}

}

?>
