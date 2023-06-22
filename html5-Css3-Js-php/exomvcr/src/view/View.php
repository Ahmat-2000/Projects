<?php
require_once('Router.php');
require_once('model/Computer.php');
require_once("model/ComputerBuilder.php");
class View 
{
    private $title, $content, $router, $menu,$feedback;
    public function __construct(Router $r,$feedback)
    {
        $this->router = $r;
        $this->title = "";
        $this->content = "";
        $this->feedback = $feedback;
        $this->menu = array(
            "Accueil" => $this->router->getHomeURL(),
            "Liste de Pc" => $this->router->getListURL(),
            "Nouveau Pc"  =>$this->router->getComputerCreationURL(),
    );
    }
/*----------------------------------------------------------------------------------------------------------------------- */

        /*                          REDIRECTION POSTREDIRECT                                      *
        * ----------------------------------------------------------------------------------------*
        * Cette partie regroupe toutes les fonctions qui s'occupent de la redirection quand un PC *
        * est crée, modifié, supprimé et même quand y'a des erreurs sur le formulaire.            *
        * ----------------------------------------------------------------------------------------*
        */

    /* Redirection vers la page du PC avec un feedback */
    public function displayComputerCreationSuccess($id) {
		$this->router->POSTredirect($this->router->getComputerURL($id),"Le PC a été crée avec succès !");
	}

    /* Redirection vers le formulaire de création d'un PC avec un feedback */
	public function displayComputerCreationFailure() {
		$this->router->POSTredirect($this->router->getComputerCreationURL(),"Erreurs dans le formulaire !");
	}

    public function displayComputerModifySuccess($id) {
		$this->router->POSTredirect($this->router->getComputerURL($id), "Le PC a bien été modifiée !");
	}

    /* Redirection vers le formulaire de création d'un PC avec un feedback */
	public function displayComputerModifyFailure($id) {
		$this->router->POSTredirect($this->router->getComputerUpdateURL($id), "Erreurs dans le formulaire !");
	}
    
    /* Redirection vers la page aui liste les PC avec un feedback */
    public function displayComputerDeleteSuccess()
    {
		$this->router->POSTredirect($this->router->getListURL(), "Le PC a été bien supprimé !");
    }
/*----------------------------------------------------------------------------------------------------------------------- */

        /*                          GESTION DE FORMULAIRE                                      *
        * -------------------------------------------------------------------------------------*
        * Cette partie regroupe toutes les fonctions qui s'occupent du formulaire de création, *
        * de modification et de suppression d'un PC.                                           *
        * -------------------------------------------------------------------------------------*
        */                                                                                     
    public function makeComputerCreationPage(ComputerBuilder $builder)
    {
        $this->title = "Fabrication du Pc";
        $url = $this->router->getComputerSaveURL();
		$form = '<form action="'.$url.'" method="POST">'."\n";
		$form .= self::getFormFields($builder);
		$form .=  '<input type="submit" value="Créer">';
		$form .= "</form>\n";
		$this->content = $form;
    }
    public function makeComputerDeletionPage($id)
    {
        $this->title = "Suppression de Pc";
        $url = $this->router->getComputerDeletionURL($id);
        $this->content = '<form action ="'.$url.'" method="POST" >
        <label>
            Le Pc d\'id= '.$id.' va être supprimé de la base<br>
            <input type="submit" value="Confirmer">
        </label>
        </form>';
    }
    
    public function makeComputerUpdatePage($id,ComputerBuilder $builder)
    {
        $this->title = "Modification du Pc";
        $url = $this->router->getConfirmeUpdateURL($id);
		$form = '<form action="'.$url.'" method="POST">'."\n";
		$form .= self::getFormFields($builder);
		$form .=  '<input type="submit" value="Modifier">';
		$form .= "</form>\n";
		$this->content = $form;
    }
/*----------------------------------------------------------------------------------------------------------------------- */

        /*                        TOUTES LES PAGES DU SITE                                         *
        * -----------------------------------------------------------------------------------------*
        * Cette partie regroupe toutes les fonctions qui s'occupent de l'affichage des différentes *
        * pages du site y compris les pages des erreurs.                                           *
        * -----------------------------------------------------------------------------------------*
        */     

    public function makeComputerPage($id,Computer $c)
    {
        $this->title = "Page sur ".self::htmlesc($c->getNom());
        $this->content = "<p>".
        self::htmlesc($c->getNom())." est un ordinateur ".self::htmlesc($c->getType()).
        " fabriqué par ".self::htmlesc($c->getMarque())
        .'<br> Vous pouvez supprimer ou modifier ce <b>PC</b> :</p>
        <ul>
        <li><a href="'.$this->router->getConfirmComputerDeletionURL($id).'">Suprimer</a></li>
        <li><a href="'.$this->router->getComputerUpdateURL($id).'">Modifier</a></li>
        </ul>';
    }
    public function makeHomePage()
    {
        $this->title = "Computers";
        $this->content = "Bienvenue à notre page d'accueil des ordinateurs";
    }
    public function makeListPage(array $computers)
    {
        $this->title = "Computers";
        $this->content = "<ul>";
        foreach ($computers as $key => $value) {
            $this->content .=
            '<li>
            <a href="'.$this->router->getComputerURL($key).'" >'.
            self::htmlesc($value->getNom()).'</a></li>';
        }
        $this->content .='</ul>';
    }
    public function makeUnknownComputerPage()
    {
        $this->title = "ERROR";
        $this->content = "<h1>Le pc n'existe pas</h1>";
    }
    public function makeUnknownPage()
    {
        $this->title = "ERROR";
        $this->content = "<h1>La page n'existe pas</h1>";
    }
    public function makeUnknownSearchPage()
    {
        $this->title = "Search";
        $this->content =  "<h2>Aucun PC ne correspond à votre recherche</h2>
                            <p>Essayer de chercher avec un nom valide :)</p>";
    }
    public function makeDebugPage($variable) {
        $this->title = 'Debug';
        $this->content = '<pre>'.htmlspecialchars(var_export(self::htmlesc($variable), true)).'</pre>';
    }
    
    public function render()
    {
        if ($this->title === null || $this->content === null) {
			$this->makeUnexpectedErrorPage();
		}
        include('squelette.php');
    }
/*----------------------------------------------------------------------------------------------------------------------- */

        /*------------------------------------*/
        #          Méthode utilitaire          #                // Une copie modifiée du code du prof
        /*------------------------------------*/
        
    /*Cette méthode permet de factoriser le code pour les pages qui s'occupent du formulaire */
    protected static function getFormFields(ComputerBuilder $builder=null) {
		$form = "";
        /* Le champ Name */
		$form .= '<p><label>Nom  : <input type="text" name="'.ComputerBuilder::NAME_REF.'" value="';
		$form .= self::htmlesc($builder->getData(ComputerBuilder::NAME_REF));
		$form .= "\" />";
		$error = $builder->getErrors(ComputerBuilder::NAME_REF);
		if ($error !== null) 
            $form .= ' <span class="error">'.$error.'</span>';
		$form .="</label></p>\n";

        /* Le champ Marque */
        $form .= '<p><label>Marque  : <input type="text" name="'.ComputerBuilder::MARQUE_REF.'" value="';
		$form .= self::htmlesc($builder->getData(ComputerBuilder::MARQUE_REF));
		$form .= "\" />";
		$error = $builder->getErrors(ComputerBuilder::MARQUE_REF);
		if ($error !== null)
			$form .= ' <span class="error">'.$error.'</span>';
		$form .= '</label></p>'."\n";

        /* Le champ Type */
        $form .= '<p><label>Type  : <input type="text" name="'.ComputerBuilder::TYPE_REF.'" value="';
		$form .= self::htmlesc($builder->getData(ComputerBuilder::TYPE_REF));
		$form .= "\" />";
		$error = $builder->getErrors(ComputerBuilder::TYPE_REF);
		if ($error !== null)
			$form .= ' <span class="error">'.$error.'</span>';
		$form .= '</label></p>'."\n";

		return $form;
	}

    /* 
    * Une fonction pour échapper les caractères spéciaux de HTML,           // Une copie du code du prof
	* car celle de PHP nécessite trop d'options.
    */
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
}
?>