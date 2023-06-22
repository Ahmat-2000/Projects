<?php

require_once("model/ColorStorage.php");
require_once("view/MainView.php");
require_once("ctl/Controller.php");


class Router {

	public function __construct(ColorStorage $colordb) {
		$this->colordb = $colordb;
	}

	public function main() {
		session_start();

		$feedback = key_exists('feedback', $_SESSION) ? $_SESSION['feedback'] : '';
		$_SESSION['feedback'] = '';

		$view = new MainView($this, $feedback);
		$ctl = new Controller($view, $this->colordb);

		/* Analyse de l'URL */
		$colorId = key_exists('couleur', $_GET) ? $_GET['couleur'] : null;
		$action = key_exists('action', $_GET) ? $_GET['action'] : null;
		if ($action === null) {
			/* Pas d'action demandée : par défaut on affiche
	 	 	 * la page d'accueil, sauf si une couleur est demandée,
	 	 	 * auquel cas on affiche sa page. */
			$action = ($colorId === null) ? "accueil" : "voir";
		}

		try {
			switch ($action) {
			case "voir":
				if ($colorId === null) {
					$view->makeUnknownActionPage();
				} else {
					$ctl->colorPage($colorId);
				}
				break;

			case "creerCouleur":
				$ctl->newColor();
				break;

			case "sauverNouvelleCouleur":
				$colorId = $ctl->saveNewColor($_POST);
				break;

			case "supprimer":
				if ($colorId === null) {
					$view->makeUnknownActionPage();
				} else {
					$ctl->deleteColor($colorId);
				}
				break;

			case "confirmerSuppression":
				if ($colorId === null) {
					$view->makeUnknownActionPage();
				} else {
					$ctl->confirmColorDeletion($colorId);
				}
				break;

			case "modifier":
				if ($colorId === null) {
					$view->makeUnknownActionPage();
				} else {
					$ctl->modifyColor($colorId);
				}
				break;

			case "sauverModifs":
				if ($colorId === null) {
					$view->makeUnknownActionPage();
				} else {
					$ctl->saveColorModifications($colorId, $_POST);
				}
				break;

			case "galerie":
				$ctl->allColorsPage();
				break;

			case "accueil":
				$view->makeHomePage();
				break;

			default:
				/* L'internaute a demandé une action non prévue. */
				$view->makeUnknownActionPage();
				break;
			}
		} catch (Exception $e) {
			/* Si on arrive ici, il s'est passé quelque chose d'imprévu
	 	 	 * (par exemple un problème de base de données) */
			$view->makeUnexpectedErrorPage($e);
		}

		/* Enfin, on affiche la page préparée */
		$view->render();
	}

	/* URL de la page d'accueil */
	public function homePage() {
		return ".";
	}

	/* URL de la page de la couleur d'identifiant $id */
	public function colorPage($id) {
		return ".?couleur=$id";
	}

	/* URL de la page avec toutes les couleurs */
	public function allColorsPage() {
		return ".?action=galerie";
	}

	/* URL de la page de création d'une couleur */
	public function colorCreationPage() {
		return ".?action=creerCouleur";
	}

	/* URL d'enregistrement d'une nouvelle couleur
	 * (champ 'action' du formulaire) */
	public function saveCreatedColor() {
		return ".?action=sauverNouvelleCouleur";
	}

	/* URL de la page d'édition d'une couleur existante */
	public function colorModifPage($id) {
		return ".?couleur=$id&amp;action=modifier";
	}
	
	/* URL d'enregistrement des modifications sur une
	 * couleur (champ 'action' du formulaire) */
	public function updateModifiedColor($id) {
		return ".?couleur=$id&amp;action=sauverModifs";
	}

	/* URL de la page demandant la confirmation
	 * de la suppression d'une couleur */
	public function colorDeletionPage($id) {
		return ".?couleur=$id&amp;action=supprimer";
	}

	/* URL de suppression effective d'une couleur
	 * (champ 'action' du formulaire) */
	public function confirmColorDeletion($id) {
		return ".?couleur=$id&amp;action=confirmerSuppression";
	}

	/* Fonction pour le POST-redirect-GET,
 	 * destinée à prendre des URL du routeur
 	 * (dont il faut décoder les entités HTML) */
	public function POSTredirect($url, $feedback) {
		$_SESSION['feedback'] = $feedback;
		header("Location: ".htmlspecialchars_decode($url), true, 303);
		die;
	}

}

?>
