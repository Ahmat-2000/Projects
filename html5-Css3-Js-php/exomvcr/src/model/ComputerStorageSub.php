<?php
require_once('ComputerStorage.php');
class ComputerStorageSub implements ComputerStorage
{
    private $computersTab;
    public function __construct()
    {
        $this->computersTab = array(
            'tuf' => new Computer('TUF GAMING', 'ASUS','portable'),
            'pavilion' => new Computer('PAVILION GAMING', 'PHP', 'portable'),
            'slim' => new Computer('SLIM DESKTOP S01', 'PHP', 'POSTE'),
        );
    }
    public function read($id)
    {
        if(key_exists($id,$this->computersTab))
        {
            return $this->computersTab[$id];
        }
        return null;
    }
    public function readAll()
    {
        return $this->computersTab;
    }
}
?>