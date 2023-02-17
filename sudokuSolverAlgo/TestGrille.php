<?php 
require_once "Sudoku.php";
$g = array( // grille de test
    array(0,0,0,0,0,0,0,0,0),
    array(0,0,0,0,0,0,0,0,0),
    array(0,0,0,0,0,0,0,0,0),
    array(0,0,0,0,0,0,0,0,0),
    array(0,0,0,0,0,0,0,0,0),
    array(0,0,0,0,0,0,0,0,0),
    array(0,0,0,0,0,0,0,0,0),
    array(0,0,0,0,0,0,0,0,0),
    array(0,0,0,0,0,0,0,0,0)
);
$grille0 = array(// grille de test
    array(0,4,0,0,5,3,1,0,2),
    array(2,0,8,1,0,0,7,0,0),
    array(5,0,1,4,2,0,6,0,0),
    array(8,1,4,0,3,0,2,0,7),
    array(0,6,0,2,0,5,0,1,9),
    array(0,5,0,7,4,0,0,6,3),
    array(0,0,0,0,7,4,5,8,1),
    array(1,8,5,9,0,2,0,0,0),
    array(4,0,3,0,0,8,0,2,6)
);
$grille = array( // grille de test
    array(0   ,4  , 0  , 0  , 5  , 3 ,  1   ,0    , 2),
    array(2   ,0  , 8   ,1  , 0  , 0 ,  7  , 0    , 0),
    array(5   ,0,   1,   4,   2,   0,   6,   0    , 8),
    array(8   ,1   ,4   ,0,   3,   0,   2,   0,     7),
    array(0   ,6,   0,   2,   8,   5,   0,   1,     9),
    array(0   ,5   ,0   ,7,   4,   0,   0,   6,     3),
    array(0   ,0   ,0,   0,   7,   4,   5,   8,     1),
    array(1   ,8   ,5,   9,   6   ,2   ,0   ,0   ,  0),
    array(4   ,0   ,3   ,0   ,0   ,8   ,6   ,2   ,  6)
);
$sudo = new Sudoku ($g) ;
echo "\n\n++++++++++++++++++++++ Algorithme de résolution de sudoku +++++++++++++++++++++++ \n";
echo "                      ------------------------------------                        \n\n";
echo "+++++++++++++++++++ Grille avant resolution ++++++++++++++++\n".$sudo;
if($sudo->solveSudoku())
{
    echo "\nCette grille de Sudoku est résolu avec succès :)\n";
    echo "+++++++++++++++ Grille après resolution ++++++++++++++++ \n".$sudo;

}
else {
    echo "\nCette grille ne contient pas de solution, ou bien elle est érronée dès le debut :(\n";
}
?>