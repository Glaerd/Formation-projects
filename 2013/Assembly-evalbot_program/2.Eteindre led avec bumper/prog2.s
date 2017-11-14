
   	
		AREA    |.text|, CODE, READONLY
 
; This register controls the clock gating logic in normal Run mode
SYSCTL_PERIPH_GPIOF EQU		0x400FE108	

GPIO_PORTF_BASE		EQU		0x40025000	
GPIO_PORTE_BASE		EQU		0x40024000

GPIO_O_DIR   		EQU 	0x00000400  ; GPIO Direction 

GPIO_O_DR2R   		EQU 	0x00000500  ; GPIO 2-mA Drive Select 

GPIO_O_RES			EQU		0x510 	; GPIO resistance de sécurité bumper

GPIO_O_DEN   		EQU 	0x0000051C  ; GPIO Digital Enable 
PORT45				EQU		0x30	
PORT01				EQU		0x03
DUREE   			EQU     0x002FFFFF	

	  	ENTRY
		EXPORT	__main
__main								
	
		;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^CONFIGURATION LED
		ldr r6, = SYSCTL_PERIPH_GPIOF	; chargement de l'horloge dans le registre
		mov r0, #0x00000030		; on active le port F et E qui corespondent au port des leds et des bumpers
		str r0, [r6]
		NOP		
		NOP
		NOP		;pause de 3 cycles soit le temps d'activation du port
		
		ldr	r6,= GPIO_PORTF_BASE+GPIO_O_DIR		; on ajoute a l'adresse de base l'adresse du changement de direction, on va ainsi choisir de modifier un ou plusieurs ports en entrées ou sorties
		ldr r0, = 	PORT45		; on choisi ici de mettre le port 4 et 5 à 1 soit la première led
        str r0, [r6]
		
		;ldr	r6,= GPIO_PORTE_BASE+GPIO_O_DIR		; on ajoute a l'adresse de base l'adresse du changement de direction, on va ainsi choisir de modifier un ou plusieurs ports en entrées ou sorties
		;ldr r0, = 	PORT01		; on choisi ici de mettre le port 0 et 1 en sorties soit les deux bumpers
        ;str r0, [r6]
		
		ldr r6, = GPIO_PORTE_BASE+GPIO_O_RES	; on emule une resistance pour éviter de cramer les composants IMPORTANT TOUJOURS AVANT ENABLE 
        ldr r0, = PORT01 			
        str r0, [r6]
		
		ldr r6, = 	GPIO_PORTF_BASE+GPIO_O_DEN;
        ldr r0, = PORT45		; on met à 1 les pattes 5 et 4 soit les 2 leds		
        str r0, [r6]
		
		ldr r6, = 	GPIO_PORTE_BASE+GPIO_O_DEN;
		ldr r0, = PORT01		; on met à 1 les pattes 0 et 1 soit les 2 bumpers, on les actives ainsi	
        str r0, [r6]
		
		mov	r11,#0x01  ; on assigne au registre le premier bumper
		mov	r12,#0x02  ; on assigne au registre le second bumper
		ldr r10, = GPIO_PORTE_BASE + (PORT01<<2)  ;adresse de detection d'état des bumpers
		
		ldr r6, = GPIO_PORTF_BASE+GPIO_O_DR2R	; Choix de l'intensité de sortie (2mA)
        ldr r0, = PORT45 			
        str r0, [r6]
		
		
		mov	r7, #0x010		;allume une des 2 leds
		mov	r8, #0x020		;allume une des 2 leds
		mov	r5,	#0x030
		mov r3, #0x000      	; extinction des leds				
		ldr r6, = GPIO_PORTF_BASE + (PORT45<<2)		;adresse stockant l'état des leds

		;;FIN configuration 
		
		
		;¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨PARTIE PROGRAMME
		
		
boucle	ldr	r9,[r10]		;on recupére la valeure contenue dans r10
		cmp	r9,#0
		bne	saute
		str	r3,[r6]		;on stocke dans r6 (etat led) la valeur 0 soit l'extinction des leds.
		
saute	cmp	r9,#0x01	; si bumper n°1 est enfoncé (1)
		bne	saute2
		str	r7,[r6]		;on éteint une des 2 leds

saute2	cmp	r9,#0x02
		bne	saute3
		str	r8,[r6]
		
saute3	cmp	r9,#0x03	; on regarde si les 2 bumpers sont enfoncés.
		bne	boucle
		str	r5,[r6]		; on eteint les 2 leds
		b boucle
		END
