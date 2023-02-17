<?php 
/**
 * - array_diff(array $a, array $b) : compare les deux array et retourne une array
 *   qui contient leur différences.
 * 
 * -  empty(array $a) : retoune true si $a est vide ou si $a est null ou bien si $a n'est pas initialisé
 * -  La classe prend en paramètre une grille 9x9
 * @param array $grille
 */
class Sudoku
{
   protected $size;
   protected $grid;
   /**
    * Initialisation de la grille et sa taille
    */
   public function __construct(array $board)
   {
      $this->grid = $board;
      $this->size = count($board);
   }

   /**
    * Affiche la grille dans le terminal
    * @return String
    */
   public function __toString()
   {
      $grille = "";
      for ($i=0; $i < $this->size; $i++) { 
         if($i % 3 == 0 )
         {
            $grille .= "-------------------------------\n";
         }
         for ($j=0; $j < $this->size; $j++) { 
            if($j % 3 == 0)
            {
               $grille .= "|";
            }
            $grille .= " ".$this->grid[$i][$j]." ";
            if($j == 8)
            {
               $grille .= "|";
            }
         }
         $grille .= "\n";
      }
      $grille .= "-------------------------------\n";
      return $grille;
   }
   /**
    * Vérifie si un nombre est déjà présent dans une ligne
    * S'il existe, on renvoie true, sinon, on renvoie false
    * @param int $row
    * @param int $number
    * @return boolean
    */
   public function isNumberInRow(int $row, int $number) 
   {
      for ($i=0; $i < $this->size; $i++) { 
         if($this->grid[$row][$i] == $number)
         {
            return true;
         }
      }
      return false;
   }
    /**
    * Vérifie si un nombre est déjà présent dans une colone
    * S'il existe, on renvoie true, sinon, on renvoie false
    * @param int $column
    * @param int $number
    * @return boolean
    */
   public function isNumberInColumn(int $column, int $number) 
   {
      for ($i=0; $i < $this->size; $i++) { 
         if($this->grid[$i][$column] == $number)
         {
            return true;
         }
      }
      return false;
   }

   /**
    * Vérifie si un nombre est déjà dans la sous grille de taille 3x3.
    * S'il existe, on renvoie true, sinon, on renvoie false
    * @param int $row
    * @param int $column
    * @param int $number
    * @return boolean
    */
   public function isNumberInBox(int $row, int $column, int $number) 
   {
      $boxStartRow    = $row - $row % 3;        // C'est la première ligne de notre sous grille 3x3
      $boxStartColumn = $column - $column % 3;  // C'est la première colone de notre sous grille 3x3
      for($i = $boxStartRow; $i < $boxStartRow + 3 ; $i++ )
      {
         for($j = $boxStartColumn; $j < $boxStartColumn + 3 ; $j++ )
         {
            if($this->grid[$i][$j] == $number)
            {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * Vérifie si le placement d'un nombre dans une case vide(zéro) est valide.
    * Si oui, on retourne true, sinon, on renvoie false
    * @param int $row
    * @param int $column
    * @param int $number
    * @return boolean
    */
   public function isValidPlacement(int $row, int $column, int $number)
   {
      if(!$this->isNumberInRow($row,$number) && 
         !$this->isNumberInColumn($column,$number) && 
         !$this->isNumberInBox($row,$column,$number)
      )
      {
         return true;
      }
      return false;
   }
   /**
    * Cette méthode permet de savoir si le jeu est terminé ou pas.
    * Elle renvoie true si la grille ne contient pas de zéro, c'est à
    * dire pas d'emplacement libre pour placer d'autres nombres libre.
    * @return boolean
    */
   public function gameOver()
   {
      for ($i=0; $i < $this->size; $i++) { 
         for ($j=0; $j < $this->size; $j++) { 
            if($this->grid[$i][$j] == 0)
            {
               return false;  // Si on trouve un zéro dans la grille, alors le jeu n'est pas terminé et on renvoie true
            }
         }
      }
      return true;   // On renvoie true si la grille ne contient aucun zéro;
   }
   /**
    * cette méthode nous permet de savoir si une grille est valide après avoir la complètement rempli.
    * Si elle est valide, on renvoie true pour dire que le sudoku est résolu.
    * sinon on renvoie false.
    * Une grille valide contient tous les nombres de 1 à 9 dans chaque ligne, chaque colone et dans dans 
    * chaque sous grille 3x3
    * @return boolean
    */
   public function isValidGame()
   {
      $box1 = $box2 = $box3 = array(); // grille temporaire pour recupèrer les sous grille 3x3
      $tmp = array(1,2,3,4,5,6,7,8,9);
      for ($i=0; $i < $this->size; $i++) { 
         if(!empty(array_diff($tmp,$this->grid[$i]))) // On vérifie que chaque ligne contient les nombres de 1 à 9, sinon on retourne false
         {
            return false;
         }
         if($i != 0 && $i % 3 == 0) // Après chaque trois ligne, on vérifie que les sous grilles 3x3 contiennent les nombres de 1 à 9, sinon on retourne false
         {
            if(!empty(array_diff($tmp,$box1)) && !empty(array_diff($tmp,$box2)) && !empty(array_diff($tmp,$box3)))
               return false;
            $box1 = $box2 = $box3 = array(); // on les vide pour les trois prochaines grille 3x3
         }
         $column = array();
         for ($j=0; $j < $this->size; $j++) { 
            $column[] = $this->grid[$i][$j]; // recupère chaque colone de notre grille pour la vérifier
            if($j <=2 )
               $box1[] = $this->grid[$i][$j]; // on recupère chaque colone de notre première grille 3x3 pour la ligne $i
            elseif ($j >= 3 && $j < 6) 
               $box2[] = $this->grid[$i][$j]; // on recupère chaque colone de notre première grille 3x3 pour la ligne $i
            else 
               $box3[] = $this->grid[$i][$j]; // on recupère chaque colone de notre première grille 3x3 pour la ligne $i
         }
         if(!empty(array_diff($tmp,$column))) // On vérifie que chaque colone contient les nombres de 1 à 9, sinon on retourne false
         {
            return false;
         }
      }
      return true; // Si on arrive à cette ligne, ça veut dire que la grille est valide, on renvoie true.
   }
   /**
    * Solver de Sudoku.
    * Un algorithme recursive de Backtracking qui retourne true si une solution est trouvée,
    * et false sinon
    * @return boolean
    */
   public function solveSudoku()
   {
      if($this->gameOver())   // Au debut on teste si la grille ne contient pas des cases vides.
      {
         return $this->isValidGame();  // si oui, on returne la validité de la grille
      } 
      // sinon on execute l'algorithme
      for ($i=0; $i < $this->size; $i++) { 
         for ($j=0; $j < $this->size; $j++) { 
            if($this->grid[$i][$j] == 0)  // On cherche une case vide dans notre grille
            {
               for($number = 1; $number <= $this->size ; $number++) // on teste tous les nombres de 1 à 9 pour cette case.
               {
                  if($this->isValidPlacement($i,$j,$number)) // Si ce nombre est valide, on le place dans la case, sinon on choisi un autre
                  {
                     $this->grid[$i][$j] = $number;
                     if($this->solveSudoku())
                     {
                        // Si le sudoku est résolu avec ce nombre choisi, alors on renvoie true et on arrête la recursion
                        return true;
                     }
                     else{
                        // Si le sudoku n'est pas résolu avec ce nombre choisi, on remet la case à 0 et on choisi un autre nombre.
                        $this->grid[$i][$j] = 0; 
                     }
                  }
               }
               // Si on ne peut pas placer un de 9 nombre, alors renvoie false pour dire que cette grille n'a pas des solutions.
               return false;
            }    
         }
      }
   }
}
?>