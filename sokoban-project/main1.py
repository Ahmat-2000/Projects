#--------------------------------------------- Partie reservée aux importations ---------------------------------------------------------------
import pygame, time
from classe import *
from CONSTANTES import *
from random import randint
#--------------------------------------------- Partie réservée aux initialisations ------------------------------------------------------------
pygame.init()
accueil_image = pygame.image.load("./img/accueil.png") # c'est pour télégarcher l'image d'accueil

pygame.mixer.music.load("./son.wav")
#pygame.mixer.music.play(-1)
#itachi = pygame.mixer.Sound("./son.wav") 			 # c'est pour télégarcher la musique 	
game = True
guide = True
home = True # variable pour la boucle de choix de niveau
#------------------------------------------ Partie réservée aux boucles -----------------------------------------------------------------------
while game: # boucle principale
	pygame.mixer.music.play(-1)
	if guide == True:
		#itachi.play(-1) # pour jouer et repeter la musique à l'infini
		ecran_accueil = pygame.display.set_mode((420,420))
		pygame.display.set_caption(TITRE)
		perso = pygame.image.load("./img/perso.png")
		pygame.display.set_icon(perso)
		while guide == True:	# boucle de l'écran d'accueil
			for event in pygame.event.get():
				if event.type == pygame.QUIT : # si le joueur tape sur la croix, on ferme le jeu.
					guide = False
					home = False
					game_over = False
					gagner = 0
					choix = " "
					game = False
			ecran_accueil.blit(accueil_image,(0,0))
			mouse = pygame.mouse.get_pos()	# Une variable pour récupèrer la position du curseur de la souris sur l'écran du jeu
			click = pygame.mouse.get_pressed() # Une variable pour récupère le clique du souris sur l'écran du jeu
			if 242 < mouse[0] < 318 and 397 < mouse[1] < 415:
				font = pygame.font.SysFont("ubuntu", 16) # on télécharge un font et une taille pour le texte
				texte = font.render("Jouer", True,black) # On écrit le texte "jouer" avec le couleur noir
				rect_blanc = pygame.draw.rect(ecran_accueil,white,(243,397,76,19)) # on supprime l'ancien rectange en le remplissant par la couleur noir
				rect_gris = pygame.draw.rect(ecran_accueil,bright_green,(243,397,76,19)) # on crée un autre rectange dans la même position avec la couleur verte
				ecran_accueil.blit(texte,(260,396)) # on blite le texte sur l'ecran au milieu du rectangle vert 
				if click[0] == 1: # Si on clique sur le bouton gauche de la souris, on quitte cette fenêtre et on se dirige vers l'écran de choix de niveau
					guide = False
					home = True
			pygame.display.flip()
	if home == True:
		largeur , hauteur = 420 , 420  # largeur et hauteur de notre écran de choix de niveau
		ecran = pygame.display.set_mode((420,420))
		pygame.display.set_caption(TITRE)
		perso = pygame.image.load("./img/perso.png")
		pygame.display.set_icon(perso)
		choix = " "         # On initialise le choix du niveau à vide
		while home == True:				# boucle de l'écran de choix de niveau
			for event in pygame.event.get():
				if event.type == pygame.QUIT:
					home = False
					game_over = False
					gagner = 0
					choix = " "
					guide = False
					game = False
			accueil(ecran,420,420)
	#----------------------------------------- Choix de niveau --------------------------------------------------
			mouse = pygame.mouse.get_pos()		# une variable pour réperer la position du curseur de la souri
			click = pygame.mouse.get_pressed()	# une variable pour réperer la position du click de la souri sur l'écran de notre "jeu"
			if largeur/2 - 45 < mouse[0] < largeur/2 - 5 and hauteur/2 < mouse[1] < hauteur/2 + 40:
				if click[0] == 1: # 1 pour un click par le boutton gauche de la souri
					choix = "1"
					home = False # on sort de la boucle d'accueil après avoir faire notre choix
			elif largeur/2 < mouse[0] < largeur/2 + 40 and hauteur/2 < mouse[1] < hauteur/2 + 40:
				if click[0] == 1:# Si on clique sur le bouton gauche de la souris dans cette position, on choisi le niveau 2
					choix = "2"
					home = False
			elif largeur/2 + 45 < mouse[0] < largeur/2 + 85 and hauteur/2 < mouse[1] < hauteur/2 +40:
				if click[0] == 1:
					choix = "3"
					home = False
			elif largeur/2 -45 < mouse[0] < largeur/2 -5 and hauteur/2 + 45 < mouse[1] < hauteur/2 +85:
				if click[0] == 1:
					choix = "4"
					home = False
			elif largeur/2 < mouse[0] < largeur/2 +40 and hauteur/2 + 45 < mouse[1] < hauteur/2 + 85:
				if click[0] == 1:
					choix = "5"
					home = False
			elif largeur/2 + 45 < mouse[0] < largeur/2 +85 and hauteur/2 + 45 < mouse[1] < hauteur/2 + 85:
				if click[0] == 1:
					choix = "6"
					home = False
			elif 298 < mouse[0] < 335 and 210 < mouse[1] < 295:
				if click[0] == 1:
					choix = str(randint(7,50))
					home = False
#-------------------------------------- On vérifie que le joueur a choisi un niveau --------------------------------------------------
	if choix != " ":
		temps1 = pygame.time.get_ticks()
		play = True
		game_over = False
		gagner = 0 # une variable qui nous permet de savoir si le joueur a gagné
		cout_de_deplacement = 0 # Nombre de déplacement
		temps = 0 # le temps initial
		Grille = Game(choix)
		Grille.creation_grille()
		Grille.width_and_hight_of_screen()
		ecran = pygame.display.set_mode((Grille.largeur,Grille.hauteur))
		pygame.display.set_caption(TITRE)
		perso = pygame.image.load("./img/perso.png")
		pygame.display.set_icon(perso)
		ecran.fill(font_color)
		Grille.pos_player()
		Grille.draw(ecran)
		pygame.display.flip()
#------------------------------------ après le choix on lance le niveau dans la boucle play pour le jouer---------------------------------
		while play == True: # boucle du jeu
			temps2 = pygame.time.get_ticks()
			temps = int((temps2 - temps1)/1000)
			for event in pygame.event.get():
				if event.type == pygame.QUIT :
					play = False
					win_or_lose =False
					game = False
				elif event.type == pygame.KEYDOWN:
					if event.key == pygame.K_RIGHT:
						cout_de_deplacement +=1 
						Grille.move_player("droite")
						Grille.player_pos[0]+=34
						Grille.mario = 1
					elif event.key == pygame.K_LEFT:
						cout_de_deplacement +=1
						Grille.move_player("gauche")
						Grille.player_pos[0]-=34
						Grille.mario = 2
					elif event.key == pygame.K_UP:
						cout_de_deplacement +=1
						Grille.move_player("haut")
						Grille.player_pos[1]-=34
						Grille.mario = 4
					elif event.key == pygame.K_DOWN:
						cout_de_deplacement +=1
						Grille.move_player("bas")
						Grille.player_pos[1]+=34
						Grille.mario = 3
			Grille.you_win()
			game_over = Grille.you_lose()
			Grille.width_and_hight_of_screen()
			ecran.fill(font_color)
			Grille.draw(ecran)
			time_is = Grille.time_of_game(temps)
			score(ecran,cout_de_deplacement,Grille.largeur,time_is)
			pygame.display.flip()
			mouse = pygame.mouse.get_pos()
			click = pygame.mouse.get_pressed()
			if Grille.largeur/2 +80 < mouse[0] < Grille.largeur/2 +240 and 5 < mouse[1] < 30:
				if click[0] == 1:
					play = False
					home = True
			if Grille.win == 0 and Grille.PLAYER_OBJECTIF == 0: # à chaque déplacement on vérifie si le joueur a terminé le niveau
				gagner = 1
				fin_niveau(ecran,Grille.largeur,Grille.hauteur, "Bravo !") # on affiche " Bravo !" s'il gagne
				time.sleep(3) # on interrompt le programme pour 3 sécondes
				play = False
			if game_over == True: # le joueur fait un mauvais pas, on affiche "Désolé !"
				fin_niveau(ecran,Grille.largeur,Grille.hauteur, "Désolé !") 
				time.sleep(3) # on interrompt le programme pour 3 sécondes
				play = False
			

	if gagner == 1 or game_over == True: # on teste si le a terminé un niveau avec succé ou s'il a fait un échec
		ecran = pygame.display.set_mode((420,420))
		largeur = 420
		hauteur = 420
		pygame.display.set_caption(TITRE)
		perso = pygame.image.load("./img/perso.png")
		pygame.display.set_icon(perso)
		win_or_lose =True
		while win_or_lose == True: # une boule qui permet de revenir sur l'écran d'accueil 
			for event in pygame.event.get():
				if event.type == pygame.QUIT:
					win_or_lose = False
					game = False
			mouse = pygame.mouse.get_pos()		# une variable pour réperer la position du curseur de la souri
			click = pygame.mouse.get_pressed()	# une variable pour réperer la position du click de la souri sur l'écran de notre "jeu"
			if gagner == 1:
				Winner_or_loser(ecran, largeur , hauteur , "Vous avez terminé le niveau, Bravo!")
				if (largeur/2)-160 < mouse[0] <(largeur/2)-40 and hauteur/2 < mouse[1] < hauteur/2 +40:
					if click[0] == 1: # 1 pour un click par le boutton gauche de la souri
						win_or_lose = False # on sort de la boucle de win_or_lose après avoir faire notre choix
						guide = False
						home = True
				elif (largeur/2)+20 < mouse[0] < (largeur/2)+140 and hauteur/2 < mouse[1] < hauteur/2 +40:
					if click[0] == 1:
						win_or_lose = False
						game = False
			elif game_over == True:
				Winner_or_loser(ecran, largeur , hauteur , "Vous avez perdu, faites un effort")
				if (largeur/2)-160 < mouse[0] <(largeur/2)-40 and hauteur/2 < mouse[1] < hauteur/2 +40:
					if click[0] == 1: # 1 pour un click par le boutton gauche de la souri
						win_or_lose = False # on sort de la boucle de win_or_lose après avoir faire notre choix
						guide = False
						home = True
				elif 150 < mouse[0] < 280 and hauteur/2+80 < mouse[1] < hauteur/2 +120:
					if click[0] == 1:
						win_or_lose = False # on sort de la boucle de win_or_lose après avoir faire notre choix
						guide = False
						home = False
						game_over = False
						Choix = choix
				elif (largeur/2)+20 < mouse[0] < (largeur/2)+140 and hauteur/2 < mouse[1] < hauteur/2 +40:
					if click[0] == 1:
						win_or_lose = False
						game = False




pygame.quit()
