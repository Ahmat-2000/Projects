<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="skin/screen.css" />
    <title><?php echo $this->title; ?></title>
</head>
<body>
    <header>
    <nav>
        <ul>
            <?php
                foreach($this->menu as $label => $url)
                    echo '<li><a href="'.$url.'">'.$label.'</a></li>';
            ?>
        </ul>
    </nav>
    <form  action="" method="get">
      <label>
        search :
        <input type="text" name="recherche" placeholder="Chercher un PC !!" required>
        <button type="submit" >search</button>
      </label>
    </form>
    </header>

    <?php 
        if($this->feedback !==''){?>
            <div class="feedback"><?php echo $this->feedback; ?></div>
    <?php }?>

    <main>
		<h1><?php echo $this->title; ?></h1>
        <?php
        echo $this->content;
        ?>
	</main>
</body>
</html>