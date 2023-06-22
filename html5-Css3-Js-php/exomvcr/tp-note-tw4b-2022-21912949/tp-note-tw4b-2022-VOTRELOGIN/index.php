<?php

/* pour que les sessions fonctionnent aussi avec le serveur built-in de PHP : */
if (php_sapi_name() === 'cli-server') ini_set('session.save_path', '/tmp');

set_include_path("./src");

require_once("model/CommStorageSession.php");
require_once("Router.php");

session_start();

$r = new Router(new CommStorageSession());
$r->main();

?>
