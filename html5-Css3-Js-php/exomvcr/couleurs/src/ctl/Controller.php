<?php

/*** Contrôleur du site des couleurs. ***/

/* Inclusion des classes nécessaires */
require_once("model/Color.php");
require_once("model/ColorStorage.php");
require_once("model/ColorBuilder.php");
require_once("view/MainView.php");


class Controller {

	protected $v;
	protected $colordb;
	protected $currentColorBuilder;
	protected $modifiedColorBuilders;

	public function __construct(MainView $view, ColorStorage $colordb) {
		$this->v = $view;
		$this->colordb = $colordb;
		$this->currentColorBuilder = key_exists('currentColorBuilder', $_SESSION) ? $_SESSION['currentColorBuilder'] : null;
		$this->modifiedColorBuilders = key_exists('modifiedColorBuilders', $_SESSION) ? $_SESSION['modifiedColorBuilders'] : array();
	}

	public function __destruct() {
		$_SESSION['currentColorBuilder'] = $this->currentColorBuilder;
		$_SESSION['modifiedColorBuilders'] = $this->modifiedColorBuilders;
	}

	public function colorPage($id) {
		/* Une couleur est demandée, on la récupère en BD */
		$color = $this->colordb->read($id);
		if ($color === null) {
			/* La couleur n'existe pas en BD */
			$this->v->makeUnknownColorPage();
		} else {
			/* La couleur existe, on prépare la page */
			$this->v->makeColorPage($id, $color);
		}
	}

	public function allColorsPage() {
		$colors = $this->colordb->readAll();
		$this->v->makeGalleryPage($colors);
	}

	public function newColor() {
		/* Affichage du formulaire de création
		* avec les données par défaut. */
		if ($this->currentColorBuilder === null) {
			$this->currentColorBuilder = new ColorBuilder();
		}
		$this->v->makeColorCreationPage($this->currentColorBuilder);
	}

	public function saveNewColor(array $data) {
		$this->currentColorBuilder = new ColorBuilder($data);
		if ($this->currentColorBuilder->isValid()) {
			/* On construit la nouvelle couleur */
			$color = $this->currentColorBuilder->createColor();
			/* On l'ajoute en BD */
			$colorId = $this->colordb->create($color);
			/* On détruit le builder courant */
			$this->currentColorBuilder = null;
			/* On redirige vers la page de la nouvelle couleur */
			$this->v->makeColorCreatedPage($colorId);
		} else {
			$this->v->makeColorNotCreatedPage();
		}
	}

	public function deleteColor($colorId) {
		/* On récupère la couleur en BD */
		$color = $this->colordb->read($colorId);
		if ($color === null) {
			/* La couleur n'existe pas en BD */
			$this->v->makeUnknownColorPage();
		} else {
			/* La couleur existe, on prépare la page */
			$this->v->makeColorDeletionPage($colorId, $color);
		}
	}

	public function confirmColorDeletion($colorId) {
		/* L'utilisateur confirme vouloir supprimer
		* la couleur. On essaie. */
		$ok = $this->colordb->delete($colorId);
		if (!$ok) {
			/* La couleur n'existe pas en BD */
			$this->v->makeUnknownColorPage();
		} else {
			/* Tout s'est bien passé */
			$this->v->makeColorDeletedPage();
		}
	}

	public function modifyColor($colorId) {
		if (key_exists($colorId, $this->modifiedColorBuilders)) {
			/* Préparation de la page de formulaire */
			$this->v->makeColorModifPage($colorId, $this->modifiedColorBuilders[$colorId]);
		} else {
			/* On récupère en BD la couleur à modifier */
			$c = $this->colordb->read($colorId);
			if ($c === null) {
				$this->v->makeUnknownColorPage();
			} else {
				/* Extraction des données modifiables */
				$builder = ColorBuilder::buildFromColor($c);
				/* Préparation de la page de formulaire */
				$this->v->makeColorModifPage($colorId, $builder);
			}
		}
	}

	public function saveColorModifications($colorId, array $data) {
		/* On récupère en BD la couleur à modifier */
		$color = $this->colordb->read($colorId);
		if ($color === null) {
			/* La couleur n'existe pas en BD */
			$this->v->makeUnknownColorPage();
		} else {
			$builder = new ColorBuilder($data);
			/* Validation des données */
			if ($builder->isValid()) {
				/* Modification de la couleur */
				$builder->updateColor($color);
				/* On essaie de mettre à jour en BD.
				* Normalement ça devrait marcher (on vient de
				* récupérer la couleur). */
				$ok = $this->colordb->update($colorId, $color);
				if (!$ok)
					throw new Exception("Identifier has disappeared?!");
				/* Redirection vers la page de la couleur */
				unset($this->modifiedColorBuilders[$colorId]);
				$this->v->makeColorModifiedPage($colorId);
			} else {
				$this->modifiedColorBuilders[$colorId] = $builder;
				$this->v->makeColorNotModifiedPage($colorId);
			}
		}
	}

}

?>
