import pygame
from classe1 import *
from CONSTANTES import *
pygame.init()
Grille = Game("0")
Grille.creation_grille()
Grille.width_and_hight_of_screen()
ecran = pygame.display.set_mode((Grille.largeur,Grille.hauteur))
pygame.display.set_caption(TITRE)
ecran.fill(font_color)
Grille.pos_player()
Grille.draw(ecran)
pygame.display.flip()
game = True
ecran = pygame.display.set_mode((640,420))
# acc = pygame.image.load("accueil1.png")
# print(Grille.GAME_OVER)
while game:
	for event in pygame.event.get():
		if event.type == pygame.QUIT :
			game = False
		elif event.type == pygame.KEYDOWN:
			if event.key == pygame.K_RIGHT:
				Grille.move_player("droite")
				Grille.player_pos[0]+=34
				Grille.mario = 1
			elif event.key == pygame.K_LEFT:
				Grille.move_player("gauche")
				Grille.player_pos[0]-=34
				Grille.mario = 2
			elif event.key == pygame.K_UP:
				Grille.move_player("haut")
				Grille.player_pos[1]-=34
				Grille.mario = 4
			elif event.key == pygame.K_DOWN:
				Grille.move_player("bas")
				Grille.player_pos[1]+=34
				Grille.mario = 3
	Grille.you_lose()
	ecran.fill(font_color)
	# ecran.blit(acc,(0,0))
	# mouse = pygame.mouse.get_pos()
	# print(mouse)
	# click = pygame.mouse.get_pressed()
	# if 242 < mouse[0] < 318 and 397 < mouse[1] <415 :
	# 	font = pygame.font.SysFont("ubuntu", 16)
	# 	texte = font.render("Jouer", True,black)
	# 	rect_blanc = pygame.draw.rect(ecran,white,(243,397,76,19))
	# 	rect_gris = pygame.draw.rect(ecran,bright_green,(243,397,76,19))
	# 	ecran.blit(texte,(260,396))
	Grille.draw(ecran)
	# Grille.you_win()
	# if Grille.win == 0 and Grille.PLAYER_OBJECTIF == 0:
	# 	Grille.Winner(ecran)
	# 	mouse = pygame.mouse.get_pos()		# une variable pour réperer la position du curseur de la souri
	# 	click = pygame.mouse.get_pressed()	# une variable pour réperer la position du click de la souri sur l'écran de notre "jeu"
	# 	if Grille.largeur/2 - 180 < mouse[0] < Grille.largeur/2 - 30 and Grille.hauteur/2 < mouse[1] < Grille.hauteur/2 + 40:
	# 		if click[0] == 1: # 1 pour un click par le boutton gauche de la souri
	# 			play = False # on sort de la boucle d'accueil après avoir faire notre choix
	# 			game = False
	# 			pygame.quit()
	# 	elif Grille.largeur/2 +80 < mouse[0] < Grille.largeur/2 +230 and Grille.hauteur/2 < mouse[1] < Grille.hauteur/2 + 40:
	# 		if click[0] == 1:
	# 			play = False
	# 			home = True
	pygame.display.flip()
pygame.quit()
quit()
