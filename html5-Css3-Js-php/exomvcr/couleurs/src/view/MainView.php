<?php

require_once("Router.php");
require_once("model/Color.php");
require_once("model/ColorBuilder.php");

class MainView {

	protected $router;
	protected $style;
	protected $title;
	protected $content;

	public function __construct(Router $router, $feedback) {
		$this->feedback = $feedback;
		$this->router = $router;
		$this->style = "";
		$this->title = null;
		$this->content = null;
	}


	/******************************************************************************/
	/* Méthodes de génération des pages                                           */
	/******************************************************************************/

	public function makeHomePage() {
		$this->title = "Proposez vos couleurs !";
		$this->content = "Bienvenue sur ce site de partage de couleurs.";
	}

	public function makeColorPage($id, Color $c) {
		$cname = self::htmlesc($c->getName());
		$chex = $c->getHex();
		$crgb = $c->getRGB();
		$chsl = $c->getHSL();
		$cclass = "color$id";
		$cdatec = self::fmtDate($c->getCreationDate());
		$cdatem = self::fmtDate($c->getModifDate());

		$this->style .= ".$cclass { background-color: #$chex; }";
		$this->title = "La couleur $cname";
		$s = "";
		$s .= "<figure>\n<div class=\"sample $cclass\"></div>\n";
		$s .= "<figcaption>Un échantillon de $cname.</figcaption>\n</figure>\n";
		$s .= "<p>La couleur $cname a pour code hexadécimal $chex.</p>\n";
		$s .= "<p>Elle contient du rouge à ".round($crgb[0]*100/255)."%, du vert à ";
		$s .= round($crgb[1]*100/255)."%, et du bleu à ".round($crgb[2]*100/255)."%.</p>";
		$s .= "<p>Sa teinte est de ".$chsl[0]."°, avec une saturation de ".$chsl[1];
		$s .= "% et une luminosité de ".$chsl[2]."%.</p>";
		$s .= "<p>Elle a été créée ".$cdatec." et modifiée ".$cdatem."</p>\n";
		$s .= "<ul>\n";
		$s .= '<li><a href="'.$this->router->colorModifPage($id).'">Modifier</a></li>'."\n";
		$s .= '<li><a href="'.$this->router->colorDeletionPage($id).'">Supprimer</a></li>'."\n";
		$s .= "</ul>\n";
		$this->content = $s;
	}

	public function makeColorCreationPage(ColorBuilder $builder) {
		$this->title = "Ajouter votre couleur";
		$s = '<form action="'.$this->router->saveCreatedColor().'" method="POST">'."\n";
		$s .= self::getFormFields($builder);
		$s .= "<button>Créer</button>\n";
		$s .= "</form>\n";
		$this->content = $s;
	}

	public function makeColorCreatedPage($id) {
		$this->router->POSTredirect($this->router->colorPage($id), "La couleur a bien été créée !");
	}

	public function makeColorNotCreatedPage() {
		$this->router->POSTredirect($this->router->colorCreationPage(), "Erreurs dans le formulaire.");
	}


	public function makeColorDeletionPage($id, Color $c) {
		$cname = self::htmlesc($c->getName());

		$this->title = "Suppression de la couleur $cname";
		$this->content = "<p>La couleur « {$cname} » va être supprimée.</p>\n";
		$this->content .= '<form action="'.$this->router->confirmColorDeletion($id).'" method="POST">'."\n";
		$this->content .= "<button>Confirmer</button>\n</form>\n";
	}

	public function makeColorDeletedPage() {
		$this->router->POSTredirect($this->router->allColorsPage(), "La couleur a bien été supprimée !");
	}


	public function makeColorModifPage($id, ColorBuilder $builder) {
		$this->title = "Modifier la couleur";

		$this->content = '<form action="'.$this->router->updateModifiedColor($id).'" method="POST">'."\n";
		$this->content .= self::getFormFields($builder);
		$this->content .= '<button>Modifier</button>'."\n";
		$this->content .= '</form>'."\n";
	}

	public function makeColorModifiedPage($id) {
		$this->router->POSTredirect($this->router->colorPage($id), "La couleur a bien été modifiée !");
	}

	public function makeColorNotModifiedPage($id) {
		$this->router->POSTredirect($this->router->colorModifPage($id), "Erreurs dans le formulaire.");
	}


	public function makeGalleryPage(array $colors) {
		$this->title = "Toutes les couleurs";
		$this->content = "<p>Cliquer sur une couleur pour voir des détails.</p>\n";
		$this->content .= "<ul class=\"gallery\">\n";
		foreach ($colors as $id=>$c) {
			$this->content .= $this->galleryColor($id, $c);
		}
		$this->content .= "</ul>\n";
	}

	public function makeUnknownColorPage() {
		$this->title = "Erreur";
		$this->content = "La couleur demandée n'existe pas.";
	}

	public function makeUnknownActionPage() {
		$this->title = "Erreur";
		$this->content = "La page demandée n'existe pas.";
	}

	/* Génère une page d'erreur inattendue. Peut optionnellement
	 * prendre l'exception qui a provoqué l'erreur
	 * en paramètre, mais n'en fait rien pour l'instant. */
	public function makeUnexpectedErrorPage(Exception $e=null) {
		$this->title = "Erreur";
		$this->content = "Une erreur inattendue s'est produite." . "<pre>" . var_export($e) . "</pre>";
	}

	/******************************************************************************/
	/* Méthodes utilitaires                                                       */
	/******************************************************************************/

	protected function getMenu() {
		return array(
			"Accueil" => $this->router->homePage(),
			"Couleurs" => $this->router->allColorsPage(),
			"Nouvelle couleur" => $this->router->colorCreationPage(),
		);
	}

	protected function galleryColor($id, $c) {
		$cclass = "color".$id;
		$this->style .= '.'.$cclass.' { background-color: #'.$c->getHex().'; }';
		$res = '<li><a href="'.$this->router->colorPage($id).'">';
		$res .= '<h3>'.self::htmlesc($c->getName()).'</h3>';
		$res .= '<div class="sample '.$cclass.'"></div>';
		$res .= '</a></li>'."\n";
		return $res;
	}

	protected function getFormFields(ColorBuilder $builder) {
		$nameRef = $builder->getNameRef();
		$s = "";

		$s .= '<p><label>Nom de la couleur : <input type="text" name="'.$nameRef.'" value="';
		$s .= self::htmlesc($builder->getData($nameRef));
		$s .= "\" />";
		$err = $builder->getErrors($nameRef);
		if ($err !== null)
			$s .= ' <span class="error">'.$err.'</span>';
		$s .="</label></p>\n";

		$hexRef = $builder->getHexRef();
		$s .= '<p><label>Code (6 chiffres hexadécimaux) : <input type="text" name="'.$hexRef.'" value="';
		$s .= self::htmlesc($builder->getData($hexRef));
		$s .= '" ';
		//$s .= 'pattern="[0-9a-fA-F]{6}"';
		$s .= '	/>';
		$err = $builder->getErrors($hexRef);
		if ($err !== null)
			$s .= ' <span class="error">'.$err.'</span>';
		$s .= '</label></p>'."\n";
		return $s;
	}

	protected static function fmtDate(DateTime $date) {
		return "le " . $date->format("Y-m-d") . " à " . $date->format("H:i:s");
	}

	/* Une fonction pour échapper les caractères spéciaux de HTML,
	* car celle de PHP nécessite trop d'options. */
	public static function htmlesc($str) {
		return htmlspecialchars($str,
			/* on échappe guillemets _et_ apostrophes : */
			ENT_QUOTES
			/* les séquences UTF-8 invalides sont
			* remplacées par le caractère �
			* au lieu de renvoyer la chaîne vide…) */
			| ENT_SUBSTITUTE
			/* on utilise les entités HTML5 (en particulier &apos;) */
			| ENT_HTML5,
			'UTF-8');
	}

	/******************************************************************************/
	/* Rendu de la page                                                           */
	/******************************************************************************/

	public function render() {
		if ($this->title === null || $this->content === null) {
			$this->makeUnexpectedErrorPage();
		}
		/* On affiche la page.
		 * Ici on pourrait faire des echo, mais simplement fermer
		 * la balise PHP revient au même, et le HTML est plus lisible.
		 * En revanche le code PHP est moins lisible : une autre solution
		 * est de mettre ce squelette dans un fichier à part et
		 * de simplement faire un «include» (c'est ce qui a été fait pour 
		 * le site des poèmes). */
?>
<!DOCTYPE html>
<html lang="fr">
<head>
	<title><?php echo $this->title; ?></title>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="skin/screen.css" />
	<style>
<?php echo $this->style; ?>
	</style>
</head>
<body>
	<nav class="menu">
		<ul>
<?php
/* Construit le menu à partir d'un tableau associatif texte=>lien. */
foreach ($this->getMenu() as $text => $link) {
	echo "<li><a href=\"$link\">$text</a></li>";
}
?>
		</ul>
	</nav>
<?php if ($this->feedback !== '') { ?>
	<div class="feedback"><?php echo $this->feedback; ?></div>
<?php } ?>
	<main>
		<h1><?php echo $this->title; ?></h1>
<?php
echo $this->content;
?>
	</main>
</body>
</html>
<?php /* fin de l'affichage de la page et fin de la méthode render() */

	}

}

?>
