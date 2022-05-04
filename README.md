# TP_2
TP android noté. 
1 année InfoSec CentralSupelec
Systèmes Embarquées: IOT 


### question 1,2,3,4:
J'ai crée un nouveau projet avec un layout principale qui contient deux EditText et un boutton, j'ai implémenter le onclicklistner du boutton qui permet d'envoyer une requette http. Pour résoudre les problèmes rencontrés, j'ai ajouter les permissions necessaires sur le fichier manifest wake_lock pour laisser l'écran allumer (réveillé), permission pour avoir un accès internet, la troisième permet à l'application d'avoir des informations sur la connection internet. J'ai crée un nouveau thread car j'ai pas le droit d'exécuter une long action sur le thread principale
