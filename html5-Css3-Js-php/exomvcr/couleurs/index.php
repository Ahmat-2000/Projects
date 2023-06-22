<?php

set_include_path("./src");

require_once("model/ColorStorageFile.php");
require_once("Router.php");

$r = new Router(new ColorStorageFile($_SERVER['TMPDIR'].'/color_db.txt'));
$r->main();

?>
