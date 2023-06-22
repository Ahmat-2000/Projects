<?php

/* Représente une couleur. */
class Color {

	protected $name;
	protected $hex;
	protected $creationDate;
	protected $modifDate;

	/* Construit une couleur. Si les paramètres de date ne sont pas passés,
	 * la couleur est considérée comme étant toute nouvelle.
	 * Le nom et le code hexa doivent être valides, au sens
	 * de isNameValid et isHexValid, sinon une exception est levée. */
	public function __construct($name, $hex, $creationDate=null, $modifDate=null) {
		if (!self::isNameValid($name))
			throw new Exception("Invalid color name");
		$this->name = $name;
		if (!self::isHexValid($hex))
			throw new Exception("Invalid hex");
		$this->hex = $hex;
		$this->creationDate = $creationDate !== null? $creationDate: new DateTime();
		$this->modifDate = $modifDate !== null? $modifDate: new DateTime();
	}

	public function getName() {
		return $this->name;
	}

	/* Renvoie le code hexadécimal de la couleur, 
	 * sous forme de chaîne d'exactement 6 chiffres hexa. */
	public function getHex() {
		return $this->hex;
	}

	/* Renvoie le code RGB de la couleur sous forme de tableau [r, g, b]
	 * d'entiers entre 0 et 255. */
	public function getRGB() {
		return array(
			base_convert(substr($this->hex, 0, 2), 16, 10),
			base_convert(substr($this->hex, 2, 2), 16, 10),
			base_convert(substr($this->hex, 4, 2), 16, 10),
		);
	}

	/* Renvoie le code HSL (teinte, saturation, luminosité)
	 * de la couleur sous forme de tableau d'entiers [h, s, l],
	 * avec h entre 0 et 359, et s et l entre 0 et 100.  */
	public function getHSL() {
		/* adapté de http://www.easyrgb.com/index.php?X=MATH&H=18 */
		$rgb = $this->getRGB();
		$r = $rgb[0] / 255;
		$g = $rgb[1] / 255;
		$b = $rgb[2] / 255;
		$min = min($r, $g, $b);
		$max = max($r, $g, $b);
		$delta = $max - $min;

		/* luminosité */
		$l = ($max + $min) / 2;

		if ($delta == 0) {
			/* c'est du gris */
			$h = 0;
			$s = 0;
		} else {
			/* saturation */
			if ($l < 0.5) {
				$s = $delta / ($max + $min);
			} else {
				$s = $delta / (2 - $max - $min);
			}

			/* teinte */
			$dr = (($max - $r) / 6 + $delta / 2) / $delta;
			$dg = (($max - $g) / 6 + $delta / 2) / $delta;
			$db = (($max - $b) / 6 + $delta / 2) / $delta;
			if ($r == $max) {
				$h = $db - $dg;
			} else if ($g == $max) {
				$h = 1/3 + $dr - $db;
			} else if ($b == $max) {
				$h = 2/3 + $dg - $dr;
			}

			/* normalisation */
			if ($h < 0)
				$h += 1;
			if ($h > 1)
				$h -= 1;
		}
		return array(round($h*360), round($s*100), round($l*100));
	}

	/* Renvoie un objet DateTime correspondant à
	 * la création de la couleur. */
	public function getCreationDate() {
		return $this->creationDate;
	}

	/* Renvoie un objet DateTime correspondant à
	 * la dernière modification de la couleur. */
	public function getModifDate() {
		return $this->modifDate;
	}

	/* Modifie le nom de la couleur. Le nouveau nom doit
	 * être valide au sens de isNameValid, sinon
	 * une exception est levée. */
	public function setName($name) {
		if (!self::isNameValid($name))
			throw new Exception("Invalid color name");
		$this->name = $name;
		$this->modifDate = new DateTime();
	}

	/* Modifie le code hexadécimal de la couleur.
	 * Le nouveau code doit
	 * être valide au sens de isHexValid, sinon
	 * une exception est levée. */
	public function setHex($hex) {
		if (!self::isHexValid($hex))
			throw new Exception("Invalid hex");
		$this->hex = $hex;
		$this->modifDate = new DateTime();
	}

	/* Indique si $name est un nom valide pour une couleur.
	 * Il doit faire moins de 30 caractères,
	 * et ne pas être vide. */
	public static function isNameValid($name) {
		return mb_strlen($name, 'UTF-8') < 30 && $name !== "";
	}

	/* Indique si $hex est un code hexadécimal valide.
	 * On accepte uniquement 6 chiffres hexadécimaux
	 * (mais peu importe la casse) */
	public static function isHexValid($hex) {
		return preg_match("/^[0-9a-f]{6}$/i", $hex);
	}

}

?>
