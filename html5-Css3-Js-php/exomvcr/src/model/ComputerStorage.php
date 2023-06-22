<?php
require_once('model/Computer.php');
interface ComputerStorage
{
    public function read($id);
    public function readAll();
    public function create(Computer $c);
    public function delete($id);
}
?>