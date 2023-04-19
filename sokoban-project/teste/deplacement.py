import pygame
from CONSTANTES import *
pygame.init()
class Game:
	"""
	C'est une classe qui génére un niveau, dessine le niveau sur la fênetre.

	"""
	def __init__(self, n): # n = nombre du niveau.
		self.nivo = "nivo"+ n +".txt"
		self.fond = pygame.image.load("fond.jpg")
		self.mur = pygame.image.load("img/mur.jpg")
		self.caisse = pygame.image.load("img/caisse.jpg")
		self.caisse_ok = pygame.image.load("img/caisse_ok.jpg")
		self.objectif = pygame.image.load("img/objectif.png")
		self.mario_haut =  pygame.image.load("img/mario_haut.gif")
		self.mario_bas = pygame.image.load("img/mario_bas.gif")
		self.mario_droite = pygame.image.load("img/mario_droite.gif")
		self.mario_gauche = pygame.image.load("img/mario_gauche.gif")
	# création du niveau à base d'un fichier.
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
	# récuperation de la position du joueur.
	def pos_player(self):
		self.player_pos = []
		for i in range(len(self.niveau)):
			for j in range(len(self.niveau[i])):
				if self.niveau[i][j] == PLAYER:
					x = j*size
					y = i*size
					self.player_pos=[x,y]
		return self.player_pos
 	# Dessine la grille et les différentes Images.
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
				elif self.niveau[i][j] == PLAYER:
					ecran.blit(self.mario_droite,(x,y))

	# Déplacement du joueur
	def move_player(self, direction):
		self.direction = direction
		for a in range(len(self.niveau)):
			for b in range(len(self.niveau[a])):
				if self.niveau[a][b] == PLAYER:
					i=a
					j=b
		# mouvement à droite
		if self.direction == "droite":
			if (j<len(self.niveau[i])-1):
				if j+1<len(self.niveau[i])-1:				
					if self.niveau[i][j+1] != MUR:
						if self.niveau[i][j+1] == CAISSE:
							if j+2<len(self.niveau[i])-1:
								if self.niveau[i][j+2] not in (CAISSE,MUR,OBJECTIF):
									self.niveau[i][j] = VIDE
									self.niveau[i][j+1] = PLAYER
									self.niveau[i][j+2] = CAISSE
								elif self.niveau[i][j+2] == OBJECTIF:
									self.niveau[i][j] = VIDE
									self.niveau[i][j+1] = PLAYER
									self.niveau[i][j+2] = CAISSE
						elif self.niveau[i][j+1] == VIDE:
							print("hello")
							if j+2<len(self.niveau[i])-1:
								if self.niveau[i][j+2] not in (CAISSE,MUR,OBJECTIF):
									self.niveau[i][j] = VIDE
									self.niveau[i][j+1] = PLAYER
									self.niveau[i][j+2] = VIDE
								elif self.niveau[i][j+2] == OBJECTIF:
									self.niveau[i][j] = VIDE
									self.niveau[i][j+1] = PLAYER
									self.niveau[i][j+2] = OBJECTIF
								elif self.niveau[i][j+2] in (CAISSE,MUR):
									self.niveau[i][j]=VIDE
									self.niveau[i][j+1]=PLAYER
						elif self.niveau[i][j+1] == OBJECTIF:
							if j+2<len(self.niveau[i])-1:
								if self.niveau[i][j+2] == OBJECTIF:
									self.niveau[i][j] = VIDE
									self.niveau[i][j+1] = PLAYER
									self.niveau[i][j+2] = OBJECTIF
								elif self.niveau[i][j+2] == VIDE:
									self.niveau[i][j] = VIDE
									self.niveau[i][j+1] = PLAYER
									self.niveau[i][j+2] = VIDE
								elif self.niveau[i][j+2] in (CAISSE,MUR):
									self.niveau[i][j] = VIDE
									self.niveau[i][j+1] = PLAYER

		# # mouvement à gauche
		elif self.direction == "gauche":
			if j>0:
				if j-1>0:
					if self.niveau[i][j-1] != MUR:
						if self.niveau[i][j-1] == CAISSE:
							if j-2>0:
								if self.niveau[i][j-2] not in (CAISSE,MUR,OBJECTIF):
									self.niveau[i][j] = VIDE
									self.niveau[i][j-1] = PLAYER
									self.niveau[i][j-2] = CAISSE
								elif self.niveau[i][j-2] == OBJECTIF:
									self.niveau[i][j] = VIDE
									self.niveau[i][j-1] = PLAYER
									self.niveau[i][j-2] = CAISSE
						elif self.niveau[i][j-1] == OBJECTIF:
							if j-2>0:
								if self.niveau[i][j-2] not in (CAISSE,MUR,OBJECTIF):
									self.niveau[i][j] = VIDE
									self.niveau[i][j-1] = PLAYER
									self.niveau[i][j-2] = VIDE
								elif self.niveau[i][j-2] == OBJECTIF:
									self.niveau[i][j] = VIDE
									self.niveau[i][j-1] = PLAYER
									self.niveau[i][j-2] = OBJECTIF
								elif self.niveau[i][j-2] in (CAISSE,MUR):
									self.niveau[i][j] = VIDE
									self.niveau[i][j-1] = OBJECTIF
						elif self.niveau[i][j-1] == VIDE:
							if j-2>0:
								if self.niveau[i][j-2] not in (CAISSE,MUR,OBJECTIF):
									self.niveau[i][j] = VIDE
									self.niveau[i][j-1] = PLAYER
									self.niveau[i][j-2] = VIDE
								elif self.niveau[i][j-2] == OBJECTIF:
									self.niveau[i][j] = VIDE
									self.niveau[i][j-1] = PLAYER
									self.niveau[i][j-2] = OBJECTIF
								elif self.niveau[i][j-2] in (CAISSE,MUR):
									self.niveau[i][j]=VIDE
									self.niveau[i][j-1]=PLAYER
		# mouvement vers le haut
		elif self.direction == "haut":
			if i>0:
				if i-1>0:
					if self.niveau[i-1][j] != MUR:
						if self.niveau[i-1][j] == CAISSE:
							if i-2>0:
								if self.niveau[i-2][j] not in (CAISSE,MUR,OBJECTIF):
									self.niveau[i][j] = VIDE
									self.niveau[i-1][j] = PLAYER
									self.niveau[i-2][j] = CAISSE
								elif self.niveau[i-2][j] == OBJECTIF: # modification ici pour l'OBJECTIF
									self.niveau[i][j] = VIDE
									self.niveau[i-1][j] = PLAYER
									self.niveau[i-2][j] = OBJECTIF
								elif self.niveau[i-2][j] in (CAISSE,MUR):
									self.niveau[i][j] = VIDE
									self.niveau[i-1][j] = PLAYER
						elif self.niveau[i-1][j] == OBJECTIF:
							if i-2>0:
								if self.niveau[i-2][j] not in (CAISSE,MUR,OBJECTIF):
									self.niveau[i][j] = VIDE
									self.niveau[i-1][j] = PLAYER
									self.niveau[i-2][j] = VIDE
								elif self.niveau[i-2][j] == OBJECTIF: # modification ici pour l'OBJECTIF
									self.niveau[i][j] = VIDE
									self.niveau[i-1][j] = PLAYER
									self.niveau[i-2][j] = OBJECTIF
								elif self.niveau[i-2][j] in (CAISSE,MUR):
									self.niveau[i][j] = VIDE
									self.niveau[i-1][j] = PLAYER
						elif self.niveau[i-1][j] == VIDE:
							if i-2>0:
								if self.niveau[i-2][j] not in (CAISSE,MUR,OBJECTIF):
									self.niveau[i][j] = VIDE
									self.niveau[i-1][j] = PLAYER
									self.niveau[i-2][j] = VIDE
								elif self.niveau[i-2][j] in (CAISSE,MUR):
									self.niveau[i][j]=VIDE
									self.niveau[i-1][j]=PLAYER
								elif self.niveau[i-2][j] == OBJECTIF:
									self.niveau[i][j] = VIDE
									self.niveau[i-1][j] = PLAYER
									self.niveau[i-2][j] = OBJECTIF
		# teste pour mouvement du bas
		elif self.direction == "bas":
			print(i,)
			print(len(self.niveau))
			if i<len(self.niveau)-1:
				if i+1<len(self.niveau)-1:
					if self.niveau[i+1][j] != MUR:
						print("je suis là")
						if self.niveau[i+1][j] == CAISSE:
							if i+2<len(self.niveau)-1:
								if self.niveau[i+2][j] not in (CAISSE,MUR,OBJECTIF):
									self.niveau[i][j] = VIDE
									self.niveau[i+1][j] = PLAYER
									self.niveau[i+2][j] = CAISSE
								elif self.niveau[i+2][j] == OBJECTIF:
									self.niveau[i][j] = VIDE
									self.niveau[i+1][j] = PLAYER
									self.niveau[i+2][j] = OBJECTIF
								elif self.niveau[i+2][j] in (CAISSE,MUR):
									self.niveau[i][j] = VIDE
									self.niveau[i+1][j] = PLAYER
						elif self.niveau[i+1][j] == VIDE:
							if i+2<len(self.niveau)-1:
								if self.niveau[i+2][j] not in (CAISSE,MUR,OBJECTIF):
									self.niveau[i][j] = VIDE
									self.niveau[i+1][j] = PLAYER
									self.niveau[i+2][j] = VIDE
								elif self.niveau[i+2][j] in (CAISSE,MUR):
									self.niveau[i][j]= VIDE
									self.niveau[i+2][j]= PLAYER
								elif self.niveau[i+2][j] == OBJECTIF:
									self.niveau[i][j] = VIDE
									self.niveau[i+1][j] = PLAYER
									self.niveau[i+2][j] = OBJECTIF
						elif self.niveau[i+1][j] == OBJECTIF:
							if i+2<len(self.niveau)-1:
								if self.niveau[i+2][j] not in (CAISSE,MUR,OBJECTIF):
									self.niveau[i][j] = VIDE
									self.niveau[i+1][j] = PLAYER
									self.niveau[i+2][j] = VIDE
								elif self.niveau[i+2][j] == OBJECTIF:
									self.niveau[i][j] = VIDE
									self.niveau[i+1][j] = PLAYER
									self.niveau[i+2][j] = OBJECTIF
								elif self.niveau[i+2][j] in (CAISSE,MUR):
									self.niveau[i][j] = VIDE
									self.niveau[i+1][j] = PLAYER









if __name__ == "__main__" :

	Grille = Game("2")
