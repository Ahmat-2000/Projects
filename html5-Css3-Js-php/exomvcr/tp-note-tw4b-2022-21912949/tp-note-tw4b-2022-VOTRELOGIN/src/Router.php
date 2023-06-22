<?php

require_once("model/CommStorage.php");
require_once("view/MainView.php");
require_once("ctl/Controller.php");


class Router {

	public function __construct(CommStorage $commdb) {
		$this->commdb = $commdb;
	}

	public function main() {

		$feedback = key_exists('feedback', $_SESSION) ? $_SESSION['feedback'] : '';
		$_SESSION['feedback'] = '';

		$view = new MainView($this, $feedback);
		$ctl = new Controller($view, $this->commdb);

		/* Analyse de l'URL */
		$action = key_exists('action', $_GET) ? $_GET['action'] : null;
		if ($action === null) {
			/* Pas d'action demandée : par défaut on affiche
	 	 	 * la liste */
			$action = "liste";
		}

		switch ($action) {

		case "nouveau":
			$ctl->newCommPage();
			break;

		case "sauver":
			$ctl->saveNewComm($_POST);
			break;

		case "liste":
			$ctl->listPage();
			break;

		case "reinit":
			/* action utile pour débugger */
			$this->commdb->reinit();
			$ctl->listPage();
			break;

		default:
			/* L'internaute a demandé une action non prévue. */
			$view->makeUnknownActionPage();
			break;
		}

		/* Enfin, on affiche la page préparée */
		$view->render();
	}


	/* URL de la page avec tous les comms */
	public function allCommsPageUrl() {
		return "?action=liste";
	}

	/* URL de la page d'ajout de commentaire */
	public function commCreationPageUrl() {
		return "?action=nouveau";
	}

	/* URL d'enregistrement d'un nouveau comm
	 * (champ 'action' du formulaire) */
	public function saveCreatedCommUrl() {
		return "?action=sauver";
	}

	/* URL de réinitialisation de la BD */
	public function reinitUrl() {
		return "?action=reinit";
	}
	/***********************************************************************/

	/* Fonction pour le POST-redirect-GET */
	public function POSTredirect($url, $feedback) {
		$_SESSION['feedback'] = $feedback;
		header("Location: ".$url, true, 303);
		die;
	}


}

?>
