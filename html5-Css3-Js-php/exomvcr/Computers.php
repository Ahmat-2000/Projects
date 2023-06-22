<?php
/* pour que les sessions fonctionnent aussi avec le serveur built-in de PHP : */
if (php_sapi_name() === 'cli-server') ini_set('session.save_path', '/tmp');
/*
 * On indique que les chemins des fichiers qu'on inclut
 * seront relatifs au répertoire src.
 */
set_include_path("./src");
/* Inclusion des classes utilisées dans ce fichier */
require_once('/users/21912949/private/mysql_config.php'); 
require_once("Router.php");
require_once('model/ComputerStorageMysql.php');
//require_once('model/ComputerStorageFile.php');
//require_once('lib/ObjectFileDB.php');
error_reporting(-1);
ini_set("display_errors",1);
/*
 * Cette page est simplement le point d'arrivée de l'internaute
 * sur notre site. On se contente de créer un routeur
 * et de lancer son main.
 */
session_start();
$dns = "mysql:host=".MYSQL_HOST.";port=".MYSQL_PORT.";dbname=".MYSQL_DB.";charset=utf8";
$pdo = new PDO($dns,MYSQL_USER,MYSQL_PASSWORD);
$store = new ComputerStorageMysql($pdo);

//$bdo = new ObjectFileDB("store.txt");
//$store = new ComputerStorageFile(new ObjectFileDB($bdo);
//$store->reinit();
$router = new Router();
$router->main($store);

?>