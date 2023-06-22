<?php

require_once("Router.php");
require_once("model/Comm.php");
require_once("model/CommBuilder.php");

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


	public function makeCommCreationPage(CommBuilder $builder) {
		$this->title = "Ajouter un commentaire";
		$s = '<form action="'.$this->router->saveCreatedCommUrl().'" method="POST">'."\n";
		$s .= self::getFormFields($builder);
		$s .= "<button>Créer</button>\n";
		$s .= "</form>\n";
		$this->content = $s;
	}

	public function displayCommCreated($id) {
		$this->feedback = "Le commentaire a bien été ajouté !";
		$this->router->POSTredirect($this->router->allCommsPageUrl(),$this->feedback);
	}

	public function displayCommNotCreated() {
		$this->feedback = "Erreurs dans le formulaire.";
		$this->router->POSTredirect($this->router->commCreationPageUrl(),$this->feedback);
	}


	public function makeListPage(array $comms) {
		$this->title = "Forum";
        $this->content = 
		'
		<a class="add" href="'.$this->router->commCreationPageUrl().'">Ajouter un commentaire</a>
		<ul class="forum">';
		
        foreach ($comms as $key => $value) {
            $this->content .=
            '<li>
			par <span class="author">'.htmlspecialchars($value->getAuthor()).'</span>, 
			<span class="date">'.htmlspecialchars($value->getCreationDate()->format('Y-m-d H:i:s')).'</span>
			<span class="text">'.htmlspecialchars($value->getText()).'</span>
            </li>';
        }
        $this->content .='</ul>';

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
		$this->content = "Une erreur inattendue s'est produite." . "<pre>" . self::htmlesc(var_export($e, true)) . "</pre>";
	}

	/******************************************************************************/
	/* Méthodes utilitaires                                                       */
	/******************************************************************************/

	protected function getFormFields(CommBuilder $builder) {
		$s = "";

		$s .= '<p><label>Nom  : <input type="text" name="'.CommBuilder::AUTHOR_REF.'" value="';
		$s .= self::htmlesc($builder->getData(CommBuilder::AUTHOR_REF));
		$s .= "\" />";
		$err = $builder->getErrors(CommBuilder::AUTHOR_REF);
		if ($err !== null) $s .= ' <span class="error">'.$err.'</span>';
		$s .="</label></p>\n";

		$s .= '<p><label>Commentaire : <textarea name="'.CommBuilder::TEXT_REF.'">';
		$s .= self::htmlesc($builder->getData(CommBuilder::TEXT_REF));
		$s .= '</textarea>';
		$err = $builder->getErrors(CommBuilder::TEXT_REF);
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
	<main>
		<h1><?php echo $this->title; ?></h1>
<?php if ($this->feedback !== '') { ?>
	<div class="feedback"><?php echo $this->feedback; ?></div>
<?php } ?>
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
