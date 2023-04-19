import pygame
from CONSTANTES import * # Importation de toutes les constantes
pygame.init()
class Game:
	"""
	-----------------------------------------------------------------------------------------------------
---	C'est une classe qui génére un niveau, dessine le niveau sur la fênetre et s'occupe des événements de 
---	notre jeu.
---	c'est dans cette classe qu'on va programmer toutes les Méthodes du jeu.
	-----------------------------------------------------------------------------------------------------
	"""
	def __init__(self, n): # n = nombre du niveau.
		self.minutes = "0" # minutes initiales en string
		self.secondes = "0" # les secondes initiales en string
		self.nivo = "./niveau/nivo"+ n +".txt"
		self.mur = pygame.image.load("./img/mur.jpg")
		self.caisse = pygame.image.load("./img/caisse.jpg")
		self.caisse_ok = pygame.image.load("./img/caisse_ok.jpg")
		self.objectif = pygame.image.load("./img/objectif.png")
		self.mario_haut =  pygame.image.load("./img/mario_haut.gif") # le joueur dirigé vers le haut
		self.mario_bas = pygame.image.load("./img/mario_bas.gif")	   # le joueur dirigé vers le bas
		self.mario_droite = pygame.image.load("./img/mario_droite.gif") # le joueur dirigé vers la droite
		self.mario_gauche = pygame.image.load("./img/mario_gauche.gif") # le joueur dirigé vers la gauche
		self.mario = 1 # Cette variable ou attribut nous permet de dessiner le joueur dans la bonne position
#------------------------------------------------------------------------------------------------------------------------------
# Cette fonction crée un niveau à base d'un fichier passer en paramettre pour la classe Game.
#------------------------------------------------------------------------------------------------------------------------------
	def creation_grille(self):
		with open(self.nivo,"r") as Level:
			self.line = ""
			self.lvl = []
			for line in Level :
				self.line = line
				self.ligne = []
				for sprite in self.line :
					if sprite != "\n" :
						self.ligne.append(sprite)
				self.lvl.append(self.ligne)
			self.niveau=self.lvl
#------------------------------------------------------------------------------------------------------------------------------
# C'est la fonction qui Calcule la hauteur et la largeur de l'écran en fonction du niveau.
#------------------------------------------------------------------------------------------------------------------------------
	def width_and_hight_of_screen(self): # cette fonction calcule les coordonnées de note écran de jeu 
		liste_colone=[]					 #	qui varie à chaque niveau par rapport à la grille.
		for i in range(len(self.niveau)):
			liste_colone.append(len(self.niveau[i]))
		maxi = max(liste_colone)
		self.hauteur = len(self.niveau)*34 # hauteur de l'écran
		self.largeur = (maxi*34)		   # largeur de l'écran
#------------------------------------------------------------------------------------------------------------------------------
# Cette fonction nous permet de récupérer la position initiale en pixel du joueur à chaque niveau.
#------------------------------------------------------------------------------------------------------------------------------
	def pos_player(self):
		self.player_pos = []
		for i in range(len(self.niveau)):
			for j in range(len(self.niveau[i])):
				if self.niveau[i][j] == PLAYER:
					x = j*size
					y = i*size
					self.player_pos=[x,y]
		return self.player_pos
#------------------------------------------------------------------------------------------------------------------------------
# C'est la fonction qui dessine tout les objets du niveau à l'écran
#------------------------------------------------------------------------------------------------------------------------------
	def draw(self,ecran):
		for i in range(len(self.niveau)):
			y = i*size
			for j in range(len(self.niveau[i])):
				x = j*size
				if self.niveau[i][j] == MUR:
					ecran.blit(self.mur,(x,y))
				elif self.niveau[i][j] == CAISSE:
					ecran.blit(self.caisse,(x,y))
				elif self.niveau[i][j] == OBJECTIF:
					ecran.blit(self.objectif,(x,y))
				elif self.niveau[i][j] == PLAYER or self.niveau[i][j] == PLAYER_OBJECTIF:
					if self.mario == 1: # si le bouton droite est utilisé, on dessine mario_droite
						ecran.blit(self.mario_droite,(x,y))
					elif self.mario == 2: # si le bouton gauche est utilisé, on dessine mario_gauche
						ecran.blit(self.mario_gauche,(x,y))
					elif self.mario == 3: # si le bouton bas est utilisé, on dessine mario_bas
						ecran.blit(self.mario_bas,(x,y))
					elif self.mario == 4: # si le bouton haut est utilisé, on dessine mario_haut
						ecran.blit(self.mario_haut,(x,y))
					else:									# sinon, on dessine par défaut mario_droite 
						ecran.blit(self.mario_droite,(x,y))
				elif self.niveau[i][j] == CAISSE_OK:
					ecran.blit(self.caisse_ok,(x,y))
				# elif self.niveau[i][j] == VIDE:			# c'est un teste
				# 	pygame.draw.rect(ecran,grille_color,(x,y,34,34))

#-----------------------------------------------------------------------------------------------------------------------------
# La fonction " move_player " s'occupe du déplacement de notre joueur et les caisses.
#------------------------------------------------------------------------------------------------------------------------------
	def move_player(self, direction):
		self.direction = direction
		for a in range(len(self.niveau)):
			for b in range(len(self.niveau[a])):
				if self.niveau[a][b] == PLAYER or self.niveau[a][b] == PLAYER_OBJECTIF:
					ligne = a
					colone = b

#------------- Déplacement à droite -------------------------------------------------------------------------------------------
		if self.direction == "droite":
			# On test si le joueur est dans une case qui était au paravant "VIDE" :
			if self.niveau[ligne][colone] == PLAYER:
				if colone < len(self.niveau[ligne])-1 and colone+1 < len(self.niveau[ligne])-1 and self.niveau[ligne][colone+1] != MUR:
					if self.niveau[ligne][colone+1] == CAISSE and colone+2 < len(self.niveau[ligne])-1:
						if self.niveau[ligne][colone+2] == VIDE:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne][colone+1] = PLAYER
							self.niveau[ligne][colone+2] = CAISSE
						elif self.niveau[ligne][colone+2] == OBJECTIF:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne][colone+1] = PLAYER
							self.niveau[ligne][colone+2] = CAISSE_OK
						elif self.niveau[ligne][colone+2] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER
							self.niveau[ligne][colone+1] = CAISSE
					if self.niveau[ligne][colone+1] == CAISSE_OK and colone+2 < len(self.niveau[ligne])-1:
						if self.niveau[ligne][colone+2] == VIDE:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne][colone+1] = PLAYER_OBJECTIF
							self.niveau[ligne][colone+2] = CAISSE
						elif self.niveau[ligne][colone+2] == OBJECTIF:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne][colone+1] = PLAYER_OBJECTIF
							self.niveau[ligne][colone+2] = CAISSE_OK
						elif self.niveau[ligne][colone+2] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER
							self.niveau[ligne][colone+1] = CAISSE_OK
					elif self.niveau[ligne][colone+1] == VIDE:
						self.niveau[ligne][colone] = VIDE
						self.niveau[ligne][colone+1] = PLAYER
					elif self.niveau[ligne][colone+1] == OBJECTIF:
						self.niveau[ligne][colone] = VIDE
						self.niveau[ligne][colone+1] = PLAYER_OBJECTIF
			# On teste si le joueur est dans une case qui était au paravant "OBJECTIF" :
			if self.niveau[ligne][colone] == PLAYER_OBJECTIF:
				if colone < len(self.niveau[ligne])-1 and colone+1 < len(self.niveau[ligne])-1 and self.niveau[ligne][colone+1] != MUR:
					if self.niveau[ligne][colone+1] == CAISSE and colone+2 < len(self.niveau[ligne])-1:
						if self.niveau[ligne][colone+2] == VIDE:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne][colone+1] = PLAYER
							self.niveau[ligne][colone+2] = CAISSE
						elif self.niveau[ligne][colone+2] == OBJECTIF:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne][colone+1] = PLAYER
							self.niveau[ligne][colone+2] = CAISSE_OK
						elif self.niveau[ligne][colone+2] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER_OBJECTIF
							self.niveau[ligne][colone+1] = CAISSE
					elif self.niveau[ligne][colone+1] == CAISSE_OK and colone+2 < len(self.niveau[ligne])-1:
						if self.niveau[ligne][colone+2] == VIDE:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne][colone+1] = PLAYER_OBJECTIF
							self.niveau[ligne][colone+2] = CAISSE
						elif self.niveau[ligne][colone+2] == OBJECTIF:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne][colone+1] = PLAYER_OBJECTIF
							self.niveau[ligne][colone+2] = CAISSE_OK
						elif self.niveau[ligne][colone+2] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER_OBJECTIF
							self.niveau[ligne][colone+1] = CAISSE_OK
					elif self.niveau[ligne][colone+1] == VIDE:
						self.niveau[ligne][colone] = OBJECTIF
						self.niveau[ligne][colone+1] = PLAYER
					elif self.niveau[ligne][colone+1] == OBJECTIF:
						self.niveau[ligne][colone] = OBJECTIF
						self.niveau[ligne][colone+1] = PLAYER_OBJECTIF

#------------- Déplacement à gauche -------------------------------------------------------------------------------------------
		if self.direction == "gauche":
			# On test si le joueur est dans une case qui était au paravant "VIDE" :
			if self.niveau[ligne][colone] == PLAYER:
				if colone >0 and colone-1 >0 and self.niveau[ligne][colone-1] != MUR:
					if self.niveau[ligne][colone-1] == CAISSE and colone-2 >0:
						if self.niveau[ligne][colone-2] == VIDE:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne][colone-1] = PLAYER
							self.niveau[ligne][colone-2] = CAISSE
						elif self.niveau[ligne][colone-2] == OBJECTIF:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne][colone-1] = PLAYER
							self.niveau[ligne][colone-2] = CAISSE_OK
						elif self.niveau[ligne][colone-2] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER
							self.niveau[ligne][colone-1] = CAISSE
					if self.niveau[ligne][colone-1] == CAISSE_OK and colone-2 >0:
						if self.niveau[ligne][colone-2] == VIDE:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne][colone-1] = PLAYER_OBJECTIF
							self.niveau[ligne][colone-2] = CAISSE
						elif self.niveau[ligne][colone-2] == OBJECTIF:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne][colone-1] = PLAYER_OBJECTIF
							self.niveau[ligne][colone-2] = CAISSE_OK
						elif self.niveau[ligne][colone-2] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER
							self.niveau[ligne][colone-1] = CAISSE_OK
					elif self.niveau[ligne][colone-1] == VIDE:
						self.niveau[ligne][colone] = VIDE
						self.niveau[ligne][colone-1] = PLAYER
					elif self.niveau[ligne][colone-1] == OBJECTIF:
						self.niveau[ligne][colone] = VIDE
						self.niveau[ligne][colone-1] = PLAYER_OBJECTIF
			# On teste si le joueur est dans une case qui était au paravant "OBJECTIF" :
			if self.niveau[ligne][colone] == PLAYER_OBJECTIF:
				if colone >0 and colone -1 >0 and self.niveau[ligne][colone-1] != MUR:
					if self.niveau[ligne][colone-1] == CAISSE and colone-2 >0:
						if self.niveau[ligne][colone-2] == VIDE:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne][colone-1] = PLAYER
							self.niveau[ligne][colone-2] = CAISSE
						elif self.niveau[ligne][colone-2] == OBJECTIF:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne][colone-1] = PLAYER
							self.niveau[ligne][colone-2] = CAISSE_OK
						elif self.niveau[ligne][colone-2] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER_OBJECTIF
							self.niveau[ligne][colone-1] = CAISSE
					elif self.niveau[ligne][colone-1] == CAISSE_OK and colone-2 >0:
						if self.niveau[ligne][colone-2] == VIDE:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne][colone-1] = PLAYER_OBJECTIF
							self.niveau[ligne][colone-2] = CAISSE
						elif self.niveau[ligne][colone-2] == OBJECTIF:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne][colone-1] = PLAYER_OBJECTIF
							self.niveau[ligne][colone-2] = CAISSE_OK
						elif self.niveau[ligne][colone-2] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER_OBJECTIF
							self.niveau[ligne][colone-1] = CAISSE_OK
					elif self.niveau[ligne][colone-1] == VIDE:
						self.niveau[ligne][colone] = OBJECTIF
						self.niveau[ligne][colone-1] = PLAYER
					elif self.niveau[ligne][colone-1] == OBJECTIF:
						self.niveau[ligne][colone] = OBJECTIF
						self.niveau[ligne][colone-1] = PLAYER_OBJECTIF

#---------------------------- Déplacement en Bas -------------------------------------------------------------------------------------------
		if self.direction == "bas":
			# On test si le joueur est dans une case qui était au paravant "VIDE" :
			if self.niveau[ligne][colone] == PLAYER:
				if ligne < len(self.niveau)-1 and ligne+1 < len(self.niveau)-1 and self.niveau[ligne+1][colone] != MUR:
					if self.niveau[ligne+1][colone] == CAISSE and ligne+2 < len(self.niveau)-1:
						if self.niveau[ligne+2][colone] == VIDE:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne+1][colone] = PLAYER
							self.niveau[ligne+2][colone] = CAISSE
						elif self.niveau[ligne+2][colone] == OBJECTIF:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne+1][colone] = PLAYER
							self.niveau[ligne+2][colone] = CAISSE_OK
						elif self.niveau[ligne+2][colone] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER
							self.niveau[ligne+1][colone] = CAISSE
					if self.niveau[ligne+1][colone] == CAISSE_OK and ligne+2 < len(self.niveau)-1:
						if self.niveau[ligne+2][colone] == VIDE:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne+1][colone] = PLAYER_OBJECTIF
							self.niveau[ligne+2][colone] = CAISSE
						elif self.niveau[ligne+2][colone] == OBJECTIF:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne+1][colone] = PLAYER_OBJECTIF
							self.niveau[ligne+2][colone] = CAISSE_OK
						elif self.niveau[ligne+2][colone] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER
							self.niveau[ligne+1][colone] = CAISSE_OK
					elif self.niveau[ligne+1][colone] == VIDE:
						self.niveau[ligne][colone] = VIDE
						self.niveau[ligne+1][colone] = PLAYER
					elif self.niveau[ligne+1][colone] == OBJECTIF:
						self.niveau[ligne][colone] = VIDE
						self.niveau[ligne+1][colone] = PLAYER_OBJECTIF
			# On teste si le joueur est dans une case qui était au paravant "OBJECTIF" :
			if self.niveau[ligne][colone] == PLAYER_OBJECTIF:
				if ligne < len(self.niveau)-1 and ligne+1 < len(self.niveau)-1 and self.niveau[ligne+1][colone] != MUR:
					if self.niveau[ligne+1][colone] == CAISSE and ligne+2 < len(self.niveau)-1:
						if self.niveau[ligne+2][colone] == VIDE:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne+1][colone] = PLAYER
							self.niveau[ligne+2][colone] = CAISSE
						elif self.niveau[ligne+2][colone] == OBJECTIF:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne+1][colone] = PLAYER
							self.niveau[ligne+2][colone] = CAISSE_OK
						elif self.niveau[ligne+2][colone] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER_OBJECTIF
							self.niveau[ligne+2][colone] = CAISSE
					elif self.niveau[ligne+1][colone] == CAISSE_OK and ligne+2 < len(self.niveau)-1:
						if self.niveau[ligne+2][colone] == VIDE:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne+1][colone] = PLAYER_OBJECTIF
							self.niveau[ligne+2][colone] = CAISSE
						elif self.niveau[ligne+2][colone] == OBJECTIF:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne+1][colone] = PLAYER_OBJECTIF
							self.niveau[ligne+2][colone] = CAISSE_OK
						elif self.niveau[ligne+2][colone] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER_OBJECTIF
							self.niveau[ligne+1][colone] = CAISSE_OK
					elif self.niveau[ligne+1][colone] == VIDE:
						self.niveau[ligne][colone] = OBJECTIF
						self.niveau[ligne+1][colone] = PLAYER
					elif self.niveau[ligne+1][colone] == OBJECTIF:
						self.niveau[ligne][colone] = OBJECTIF
						self.niveau[ligne+1][colone] = PLAYER_OBJECTIF

#----------------------------- Déplacement en Haut -------------------------------------------------------------------------------------------
		if self.direction == "haut":
			# On test si le joueur est dans une case qui était au paravant "VIDE" :
			if self.niveau[ligne][colone] == PLAYER:
				if ligne >0 and ligne-1 >0 and self.niveau[ligne-1][colone] != MUR:
					if self.niveau[ligne-1][colone] == CAISSE and ligne-2 >0:
						if self.niveau[ligne-2][colone] == VIDE:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne-1][colone] = PLAYER
							self.niveau[ligne-2][colone] = CAISSE
						elif self.niveau[ligne-2][colone] == OBJECTIF:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne-1][colone] = PLAYER
							self.niveau[ligne-2][colone] = CAISSE_OK
						elif self.niveau[ligne-2][colone] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER
							self.niveau[ligne-1][colone] = CAISSE
					if self.niveau[ligne-1][colone] == CAISSE_OK and ligne-2 >0:
						if self.niveau[ligne-2][colone] == VIDE:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne-1][colone] = PLAYER_OBJECTIF
							self.niveau[ligne-2][colone] = CAISSE
						elif self.niveau[ligne-2][colone] == OBJECTIF:
							self.niveau[ligne][colone] = VIDE
							self.niveau[ligne-1][colone] = PLAYER_OBJECTIF
							self.niveau[ligne-2][colone] = CAISSE_OK
						elif self.niveau[ligne-2][colone] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER
							self.niveau[ligne-1][colone] = CAISSE_OK
					elif self.niveau[ligne-1][colone] == VIDE:
						self.niveau[ligne][colone] = VIDE
						self.niveau[ligne-1][colone] = PLAYER
					elif self.niveau[ligne-1][colone] == OBJECTIF:
						self.niveau[ligne][colone] = VIDE
						self.niveau[ligne-1][colone] = PLAYER_OBJECTIF
			# On teste si le joueur est dans une case qui était au paravant "OBJECTIF" :
			if self.niveau[ligne][colone] == PLAYER_OBJECTIF:
				if ligne >0 and ligne -1 >0 and self.niveau[ligne-1][colone] != MUR:
					if self.niveau[ligne-1][colone] == CAISSE and ligne-2 >0:
						if self.niveau[ligne-2][colone] == VIDE:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne-1][colone] = PLAYER
							self.niveau[ligne-2][colone] = CAISSE
						elif self.niveau[ligne-2][colone] == OBJECTIF:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne-1][colone] = PLAYER
							self.niveau[ligne-2][colone] = CAISSE_OK
						elif self.niveau[ligne-2][colone] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER_OBJECTIF
							self.niveau[ligne-1][colone] = CAISSE
					elif self.niveau[ligne-1][colone] == CAISSE_OK and ligne-2 >0:
						if self.niveau[ligne-2][colone] == VIDE:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne-1][colone] = PLAYER_OBJECTIF
							self.niveau[ligne-2][colone] = CAISSE
						elif self.niveau[ligne-2][colone] == OBJECTIF:
							self.niveau[ligne][colone] = OBJECTIF
							self.niveau[ligne-1][colone] = PLAYER_OBJECTIF
							self.niveau[ligne-2][colone] = CAISSE_OK
						elif self.niveau[ligne-2][colone] in (CAISSE,MUR,CAISSE_OK):
							self.niveau[ligne][colone] = PLAYER_OBJECTIF
							self.niveau[ligne-1][colone] = CAISSE_OK
					elif self.niveau[ligne-1][colone] == VIDE:
						self.niveau[ligne][colone] = OBJECTIF
						self.niveau[ligne-1][colone] = PLAYER
					elif self.niveau[ligne-1][colone] == OBJECTIF:
						self.niveau[ligne][colone] = OBJECTIF
						self.niveau[ligne-1][colone] = PLAYER_OBJECTIF
#----------------------------------------------------------------------------------------------------------------------------------------
# Cette fonction permet de terminer le jeu si le joueur fait une mauvaise action en déplaçant une caisse
#----------------------------------------------------------------------------------------------------------------------------------------
	def you_lose(self):
		global game_over
		game_over = False
		for i in range(len(self.niveau)):
			for j in range(len(self.niveau[i])):
				if self.niveau[i][j] == CAISSE: 
					if  j+1 < len(self.niveau[i]) and self.niveau[i][j+1] == MUR:
						if ( i+1< len(self.niveau) and (self.niveau[i+1][j] == MUR)) or ( i-1 >=0 and (self.niveau[i-1][j] == MUR)):
							game_over = True
					if  j-1 >= 0 and self.niveau[i][j-1] == MUR:
						if ( i+1< len(self.niveau) and (self.niveau[i+1][j] == MUR)) or ( i-1 >=0 and (self.niveau[i-1][j] == MUR)):
							game_over = True
					if  (j+1 < len(self.niveau[i])) and (j-1 >= 0) and i+1 < len(self.niveau) and (i-1) >= 0:
						if (self.niveau[i][j+1] in (CAISSE,MUR,CAISSE_OK)) and (self.niveau[i+1][j] in (CAISSE,MUR,CAISSE_OK)) and (self.niveau[i+1][j+1] in (CAISSE,MUR,CAISSE_OK)):
							game_over = True
						if (self.niveau[i][j+1] in (CAISSE,MUR,CAISSE_OK)) and (self.niveau[i-1][j] in (CAISSE,MUR,CAISSE_OK)) and (self.niveau[i-1][j+1] in (CAISSE,MUR,CAISSE_OK)):
							game_over = True
						if (self.niveau[i][j-1] in (CAISSE,MUR,CAISSE_OK)) and (self.niveau[i-1][j-1] in (CAISSE,MUR,CAISSE_OK)) and (self.niveau[i-1][j] in (CAISSE,MUR,CAISSE_OK)):
							game_over = True
						if (self.niveau[i][j-1] in (CAISSE,MUR,CAISSE_OK)) and (self.niveau[i+1][j-1] in (CAISSE,MUR,CAISSE_OK)) and (self.niveau[i+1][j] in (CAISSE,MUR,CAISSE_OK)):
							game_over = True
						if self.niveau[i][j+1] == CAISSE and self.niveau[i-1][j+1] == MUR and self.niveau[i+1][j] == MUR:
							game_over = True
						if self.niveau[i][j-1] == CAISSE and self.niveau[i-1][j-1] == MUR and self.niveau[i+1][j] == MUR:
							game_over = True
						if self.niveau[i-1][j] == CAISSE and self.niveau[i][j+1] == MUR and self.niveau[i-1][j-1] == MUR:
							game_over = True
						if self.niveau[i-1][j] == CAISSE and self.niveau[i-1][j+1] == MUR and self.niveau[i-1][j] == MUR:
							game_over = True
						# if OBJECTIF not in self.niveau[i]:
						# 	if CAISSE_OK not in self.niveau[i+1] and CAISSE not in self.niveau[i+1] and VIDE not in self.niveau[i+1] and OBJECTIF not in self.niveau[i+1] and PLAYER not in self.niveau[i+1] and PLAYER_OBJECTIF not in self.niveau[i+1]:
						# 		if self.niveau[i][0] == MUR and self.niveau[i][-1] == MUR:
						# 			game_over = True
						# 	if OBJECTIF not in self.niveau[i-1] and CAISSE_OK not in self.niveau[i-1] and CAISSE not in self.niveau[i-1] and VIDE not in self.niveau[i-1] and PLAYER not in self.niveau[i-1] and PLAYER_OBJECTIF not in self.niveau[i-1]:
						# 		if self.niveau[i][0] == MUR and self.niveau[i][-1] == MUR:
						# 			game_over = True

		return game_over
#--------------------------------------------------------------------------------------------------------------------------------
# Cette nous permet de savoir si le joueur  a gagné au jeu ou bien qu'il a terminé un niveau
#----------------------------------------------------------------------------------------------------------------------------------
	def you_win(self):
		self.OBJECTIF = 0
		self.player = 0
		for i in range(len(self.niveau)):
			for j in range(len(self.niveau[i])):
				if self.niveau[i][j] == OBJECTIF:
					self.OBJECTIF+=1
				elif self.niveau[i][j] == PLAYER_OBJECTIF:
					self.player +=1
		self.win = self.OBJECTIF
		self.PLAYER_OBJECTIF = self.player
#---------------------------------- Calcule de temps ------------------------------------------------------------
	def time_of_game(self,temps):
		s ="" 		# pour afficher un zero devannt le nombre de seconde 
		m =""		# pour afficher un zero devannt le nombre de minutes
		if temps<60:
			self.secondes =str(temps)
		if temps >= 60 and temps % 60 == 0:
			self.minutes = str(int(temps/60))	
		if temps>=60:
			self.secondes = str(temps%60)
		if int(self.secondes) < 10:
			s = "0"
		if int(self.minutes) < 10:
			m = "0"
		return m+self.minutes+":"+s+self.secondes
#------------------------------------------- un nouveau écran avec de menu pour permetre le choix d'un niveau -------------------
def Winner_or_loser(ecran, largeur, hauteur,gagner_ou_perdu):
	ecran.fill(white)
	mouse = pygame.mouse.get_pos()
	arial_font = pygame.font.SysFont("arial", 30)
	arial_font1 = pygame.font.SysFont("arial", 20)
	ressayez = arial_font.render("ressayez", True, black)
	#---------------------------------------- écriture de texte et création de rectangles ------------------------------------------
	rect_accueil = pygame.draw.rect(ecran, green, ((largeur/2)-160, hauteur/2, 120, 40))
	rect_quitter = pygame.draw.rect(ecran, red, ((largeur/2)+20, hauteur/2, 120, 40))
	if gagner_ou_perdu == "Vous avez perdu, faites un effort":
		rect_ressayez = pygame.draw.rect(ecran, green, (150, hauteur/2 +80, 130, 40))
		if 150 < mouse[0] < 280 and hauteur/2+80 < mouse[1] < hauteur/2 +120:
			rect_ressayez = pygame.draw.rect(ecran, bright_green, (150, hauteur/2 +80, 130, 40))
		ecran.blit(ressayez , (160 , hauteur/2+80))
		# pygame.display.flip()

	accueil = arial_font.render("accueil", True, black)
	quitter = arial_font.render("quitter", True, black)
	afficher = arial_font1.render(gagner_ou_perdu, True, black)

	#-------------------- pour changer la couleur de rectange quand le curseur est au dessus de ce dernier-----------------------
	if (largeur/2)-160 < mouse[0] <(largeur/2)-40 and hauteur/2 < mouse[1] < hauteur/2 +40:
		rect_accueil = pygame.draw.rect(ecran, bright_green, ((largeur/2)-160, hauteur/2, 120, 40))
	elif (largeur/2)+20 < mouse[0] < (largeur/2)+140 and hauteur/2 < mouse[1] < hauteur/2 +40:
		rect_quitter = pygame.draw.rect(ecran, bright_red, ((largeur/2)+20, hauteur/2, 120, 40))
	#-------------------------------------- bliter les textes sur les rectangles dans l'écran d'accueil ------------------------------
	ecran.blit(accueil , ((largeur/2) - 145 , (hauteur/2)))
	ecran.blit(quitter , ((largeur/2)+40, (hauteur/2)))
	ecran.blit(afficher , (largeur/2 -150 , hauteur/2-100))

	pygame.display.flip()
#---------------------------------------------- pour gerer l'écran de choix de niveau-----------------------------------------------------
def accueil(ecran,largeur,hauteur):
	ecran.fill(white)
	#---------------------------------------- écriture de texte " niveau et son numéro" ------------------------------------------
	font = pygame.font.SysFont("ubuntu",20)
	level = font.render("Choisir un niveau", True, black) # écriture du texte avec la couleur noire
	niveau1_text = font.render("1", True, black)		  # écriture du texte avec la couleur noire
	niveau2_text = font.render("2", True, black)		  # écriture du texte avec la couleur noire
	niveau3_text = font.render("3", True, black)		  # écriture du texte avec la couleur noire
	niveau4_text = font.render("4", True, black)		  # écriture du texte avec la couleur noire
	niveau5_text = font.render("5", True, black)		  # écriture du texte avec la couleur noire
	niveau6_text = font.render("6", True, black)		  # écriture du texte avec la couleur noire
	message_texte1 = font.render("Si vous cliquez sur 'aléa', le programme vous ",True,red)
	message_texte2 = font.render("choisira un niveau aléatoire de 7 à 50",True,red)
	message_texte3 = font.render("Votre score sera égal au nombre de ",True,red)
	message_texte4 = font.render("déplacemnts effectués par le personnage",True,red)

	alea_text  = font.render("alea", True, black)
	#---------------------------------------------- Création de rectangles--------------------------------------------------------
	accueil = pygame.draw.rect(ecran, green, (largeur/2 - 45, hauteur/2 - 100, 170, 50))
	niveau1 = pygame.draw.rect(ecran, green, (largeur/2 - 45, hauteur/2, 40, 40))
	niveau2 = pygame.draw.rect(ecran, green, (largeur/2, hauteur/2, 40, 40))
	niveau3 = pygame.draw.rect(ecran, green, (largeur/2 + 45, hauteur/2, 40, 40))
	niveau4 = pygame.draw.rect(ecran, green, (largeur/2 -45 , hauteur/2 + 45, 40, 40))
	niveau5 = pygame.draw.rect(ecran, green, (largeur/2, hauteur/2 + 45, 40, 40))
	niveau6 = pygame.draw.rect(ecran, green, (largeur/2 + 45, hauteur/2 + 45, 40, 40))
	alea = pygame.draw.rect(ecran, red, (298, 210, 37, 85))
	#-------------------- pour changer la couleur de rectange quand le curseur est au dessus de ce dernier-----------------------
	mouse = pygame.mouse.get_pos()
	if largeur/2 - 45 < mouse[0] < largeur/2 - 5 and hauteur/2 < mouse[1] < hauteur/2 + 40:
		niveau1 = pygame.draw.rect(ecran, bright_green, (largeur/2 - 45, hauteur/2, 40, 40))
	elif largeur/2 < mouse[0] < largeur/2 + 40 and hauteur/2 < mouse[1] < hauteur/2 + 40:
		niveau2 = pygame.draw.rect(ecran, bright_green, (largeur/2, hauteur/2, 40, 40))
	elif largeur/2 + 45 < mouse[0] < largeur/2 + 85 and hauteur/2 < mouse[1] < hauteur/2 +40:
		niveau3 = pygame.draw.rect(ecran, bright_green, (largeur/2 + 45, hauteur/2, 40, 40))
	elif largeur/2 -45 < mouse[0] < largeur/2 -5 and hauteur/2 + 45 < mouse[1] < hauteur/2 +85:
		niveau4 = pygame.draw.rect(ecran, bright_green, (largeur/2 -45 , hauteur/2 + 45, 40, 40))
	elif largeur/2 < mouse[0] < largeur/2+40 and hauteur/2 + 45 < mouse[1] < hauteur/2 + 85:
		niveau5 = pygame.draw.rect(ecran, bright_green, (largeur/2, hauteur/2 + 45, 40, 40))
	elif largeur/2 + 45 < mouse[0] < largeur/2 +85 and hauteur/2 + 45 < mouse[1] < hauteur/2 + 85:
		niveau6 = pygame.draw.rect(ecran, bright_green, (largeur/2 + 45, hauteur/2 + 45, 40, 40))
	elif 298 < mouse[0] < 335 and 210 < mouse[1] < 295:
		alea = pygame.draw.rect(ecran, bright_red, (298, 210, 37, 85))
	#-------------------------------------- bliter les textes sur les rectangles dans l'écran d'accueil ------------------------------
	ecran.blit(level, ((largeur/2) -40, (hauteur/2) -87.5))
	ecran.blit(alea_text,(298,240))
	ecran.blit(niveau1_text, ((largeur/2) -30,(hauteur/2) +10))
	ecran.blit(niveau2_text, ((largeur/2) + 15, (hauteur/2) + 10))
	ecran.blit(niveau3_text, ((largeur/2) + 60, (hauteur/2) + 10))
	ecran.blit(niveau4_text, ((largeur/2) - 30, (hauteur/2) + 55))
	ecran.blit(niveau5_text, ((largeur/2) + 15, (hauteur/2) + 55))
	ecran.blit(niveau6_text, ((largeur/2) + 60, (hauteur/2) + 55))
	ecran.blit(message_texte1,(0,0))
	ecran.blit(message_texte2,(0,25))
	ecran.blit(message_texte3,(0,370))
	ecran.blit(message_texte4,(0,395))
	pygame.display.flip()

#---------------------------------- Calcule de temps ------------------------------------------------------------
def time_of_game(temps):
	minutes = 0
	secondes = 0
	if temps<60:
		secondes = temps
	if temps >= 60 and temps % 60 == 0:
		minutes = int(temps/60)	
	if temps>=60:
		secondes = temps%60
	return str(minutes) + ":" + str(secondes)
#--------------------------------pour afficher le score, le temps et choisir un niveau -------------------------------------
def score(ecran,cout_de_deplacement,largeur,temps): # largeur = largeur de l'écran
	rectangle_blanc = pygame.draw.rect(ecran, white, (0, 0, largeur, 34))
	rectangle_choix = pygame.draw.rect(ecran, green, (largeur/2+70, 5, 170, 25))
	font = pygame.font.SysFont("ubuntu",20)
	score_texte = font.render("Score : "+str(cout_de_deplacement), True, black)
	temps_texte = font.render("Temps : "+temps, True, black)
	choix_texte = font.render("Choisir un niveau", True, black)
	mouse = pygame.mouse.get_pos()
	if largeur/2 +70 < mouse[0] < largeur/2 +240 and 5 < mouse[1] < 30:
		rectangle_choix = pygame.draw.rect(ecran, bright_green, (largeur/2+70, 5, 170, 25))
	ecran.blit(score_texte,(0,5))
	ecran.blit(temps_texte,(largeur/2-80,5))
	ecran.blit(choix_texte,(largeur/2+80,5))

def fin_niveau(ecran,largeur,hauteur,texte):
	font = pygame.font.SysFont("ubuntu",40,red)
	afficher = font.render(texte, True, red)
	ecran.blit(afficher,(largeur/2,hauteur/2))
	pygame.display.flip()

# if __name__ == "__main__" :

# 	Grille = Game("2")
# 	# Grille.creation_grille()
# 	# Grille.you_lose()