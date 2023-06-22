#include <stdio.h>
#include <stdlib.h>
#include "list.h"
#include <string.h>
list *createNoeud(int valeur){
    list *noeud = (list*) malloc(sizeof(*noeud));
    noeud->valeur = valeur ;
    noeud->suivant = NULL ;
    return noeud ;
}
void cleanM(list **l){
    list *tmp ;
    while(*l!=NULL){
        tmp = *l ;
        *l = (*l)->suivant ;
        free(tmp) ;
        tmp = NULL;
    }
 }
    
/*-------------------------------------------------*/
void inserDebut(list **listeChaine ,int valeur){
    list *newElement   = (list*) malloc(sizeof(list));
    if(newElement == NULL)
        exit(1);
    newElement->valeur = valeur;
   if(*listeChaine==NULL){
        newElement->suivant = NULL ;
        *listeChaine = newElement ;
   }
   else{
    newElement->suivant = *listeChaine ;
    *listeChaine = newElement ;
   }
}
/*------------------------------------------------*/
void inserFin(list **listeChaine ,int valeur){
    list *newElement   = (list*) malloc(sizeof(list));
    if(newElement == NULL)
        exit(1);
    newElement->valeur = valeur;
    if(*listeChaine==NULL){
        newElement->suivant = NULL ;
        *listeChaine = newElement ;
        return;
    }
    list *tmp = *listeChaine;
    while(tmp->suivant!=NULL){
        tmp = tmp->suivant ;
    }
    tmp->suivant = newElement;
}
/*----------------------------------------*/
void affichListChaine(list *liste){
    list * tmp =liste;
    while(tmp!=NULL){
        printf("%d -> " ,tmp->valeur) ;
        tmp = tmp->suivant ;
    }
    printf("X \n") ;
}
int main() {
    list*e = createNoeud(5) ;
    inserDebut(&e,4) ;
    inserDebut(&e,3) ;
    inserDebut(&e,2) ;
    inserFin(&e,6) ;
    inserFin(&e,7) ;
    affichListChaine(e) ;
    cleanM(&e) ;
    e = NULL;
    return 0 ;
}