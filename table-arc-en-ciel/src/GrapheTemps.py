import csv
import matplotlib.pyplot as pl
"""
@ Autor Ahmat 
"""
#AlgorithmeDeHachage;NombreDeReduction;TailleDeLaTable;NombreDeMotDePasseACasser;LongueurDeMotDePasse;NombreDeMotDePasseCasser;TempsDeCassage;TempsDeGeneration
with open("./DataBase/resultat.csv", "r") as fichier :
    reader = csv.reader(fichier,delimiter=';')
    data = list(reader)
data.pop(0)
taux = {}
i = 1
for row in data : 
    key = "test "+str(i) #"-".join(row)
    value = round(float(row[6]))
    taux[key] = value
    i +=1

pl.barh(list(taux.keys()), list(taux.values()))
pl.title("Graphe de taux de reussite")
pl.xlabel("pourcentage")
pl.ylabel("table")
pl.show()
