		AREA    |.text|, CODE, READONLY
		
		EXPORT	__main
__main	
		ldr r6, = 0x400FE108	; chargement de l'horloge dans le registre
		mov r0, #0x00000020		; on active le port F qui corespond au port des leds
		str r0, [r6]
		NOP		
		NOP
		NOP		;pause de 3 cycles soit le temps d'activation du port
		
		ldr	r6,= 0x40025000+0x00000400		; on ajoute a l'adresse de base l'adresse du changement de direction, on va ainsi choisir de modifier un ou plusieurs ports en entrées ou sorties
		ldr r0, = 	0x30		; on choisi ici de mettre le port 4 et 5 à 1 soit la première led
        str r0, [r6]
		
		ldr r6, = 	0x40025000+0x0000051C;
        ldr r0, = 0x30		; on met à 1 les pattes 5 et 4 soit les 2 leds		
        str r0, [r6]
		
		ldr r6, = 0x40025000+0x00000500	; Choix de l'intensité de sortie (2mA)
        ldr r0, = 0x30 			
        str r0, [r6]
		
		mov r2, #0x030		;; Allume dans portF broche 4 et 5 : 00010000
		mov r3, #0x000      	; extinction des leds				
		ldr r6, = 0x40025000 + (0x30<<2)		;pour allumer les leds, on décale de 2 
		
		;;;FIN configuration des leds
loop	
		str r2, [r6]    						;; Eteint LED car r2 = 0x00      
        ldr r1, = 0x002FFFFF 						;; pour la duree de la boucle d'attente1 (wait1)

wait1	subs r1, #1
        bne wait1

        str r3, [r6]  							;; Allume portF broche 4 et 5 : 00010000 (contenu de r3)
        ldr r1, = 0x002FFFFF							;; pour la duree de la boucle d'attente2 (wait2)

wait2   subs r1, #1
        bne wait2

        b loop       
		
		nop		
        END 