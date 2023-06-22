<?php
require_once('view/View.php');
require_once('control/Controller.php');
require_once('model/ComputerStorageFile.php');
require_once('model/ComputerBuilder.php');
class Router
{
    public function main($store)
    {
        $feedback = key_exists('feedback', $_SESSION) ? $_SESSION['feedback'] : '';
		$_SESSION['feedback'] = '';
        $view = new View($this,$feedback);
        $control = new Controller($view,$store);
        if(key_exists('list',$_GET))
        {
            $control->showList();
        }
        else if(key_exists('action',$_GET))
        {
            switch ($_GET['action']) {
                case 'nouveau':
                    $control->newAnimal();
                    break;
                case 'sauverNouveau':
                    $control->saveNewComputer($_POST);
                    break;
                case 'suppression':
                    if(key_exists('id',$_GET))
                        $control->confirmComputerDeletion($_GET['id']);
                    else
                        $view->makeUnknownPage();
                    break;
                case 'confirmeSuppression':
                    
                    if(key_exists('id',$_GET))
                        $control->deleteComputer($_GET['id']);
                    else
                        $view->makeUnknownPage();
                    break;
                case 'update':
                    if(key_exists('id',$_GET))
                    {   
                        $control->modifyComputer($_GET['id']);
                    }
                    else
                        $view->makeUnknownPage();
                    break;
                case 'confirmeUpdate':
                    if(key_exists('id',$_GET))
                        $control->saveComputerMOdification($_GET['id'],$_POST);
                    else
                        $view->makeUnknownPage();
                    break;
                default:
                    $view->makeHomePage();
                    break;
            }
                
        }
        else if(key_exists('id',$_GET))
        {
            $control->showInformation($_GET['id']);
        }
        else
            $view->makeHomePage();
        $view->render();
    }
    // these functions return url for pages, One url per page 
    public function getHomeURL()
    {
        return 'Computers.php';
    }
    public function getListURL()
    {
        return 'Computers.php?list';
    }
    public function getComputerCreationURL()
    {
        return 'Computers.php?action=nouveau';
    }
    public function getComputerSaveURL()
    {
        return 'Computers.php?action=sauverNouveau';
    }
    public function getComputerURL($id)
    {
        return "Computers.php?id=".$id;
    }
    public function getConfirmComputerDeletionURL($id){
        return 'Computers.php?action=confirmeSuppression&id='.$id;
    }
    public function getComputerDeletionURL($id){
        return 'Computers.php?action=suppression&id='.$id;
    }
    public function getComputerUpdateURL($id)
    {
        return 'Computers.php?action=update&id='.$id;
    }
    public function getConfirmeUpdateURL($id)
    {
        return 'Computers.php?action=confirmeUpdate&id='.$id;
    }
    
    public function POSTredirect($url, $feedback)
    {
        $_SESSION['feedback'] = $feedback;
        header('Location: '.$url,true,303);
        die();
    }
}
?>