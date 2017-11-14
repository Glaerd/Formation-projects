
		AREA    |.text|, CODE, READONLY
 
; This register controls the clock gating logic in normal Run mode
SYSCTL_PERIPH_GPIOF EQU		0x400FE108	

GPIO_PORTF_BASE		EQU		0x40025000	
GPIO_PORTD_BASE		EQU		0x40007000

GPIO_O_DIR   		EQU 	0x00000400  ; GPIO Direction 

GPIO_O_DR2R   		EQU 	0x00000500  ; GPIO 2-mA Drive Select 

GPIO_O_RES			EQU		0x510 	; GPIO resistance de s้curit้ 

GPIO_O_DEN   		EQU 	0x0000051C  ; GPIO Digital Enable 
PORT67				EQU		0xC0	
PORT6				EQU		0x40
PORT7				EQU		0x80
DUREE   			EQU     0x002FFFFF	

	  	ENTRY
		EXPORT	__main
__main								
	
		;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^CONFIGURATION 
		ldr r6, = SYSCTL_PERIPH_GPIOF	
		mov r0, #0x00000028   ; port F et D	
		str r0, [r6]
		NOP		
		NOP
		NOP		
		
		ldr	r6,= GPIO_PORTF_BASE+GPIO_O_DIR		
		mov r0,#0x30		
        str r0, [r6]
		
		ldr r6, = GPIO_PORTD_BASE+GPIO_O_RES	
        ldr r0, = PORT67 			
        str r0, [r6]
		
		ldr r6, = GPIO_PORTF_BASE+GPIO_O_DEN
        mov r0, #0x30				
        str r0, [r6]
		
		ldr r6, = GPIO_PORTD_BASE+GPIO_O_DEN
		ldr r0, = PORT67
        str r0, [r6]
		
		mov	r11,PORT6  
		mov	r12,PORT7  
		ldr r10, = GPIO_PORTD_BASE + (PORT67<<2)  
		
		ldr r6, = GPIO_PORTF_BASE+GPIO_O_DR2R	
        mov r0, #0x30 			
        str r0, [r6]
		
		
		mov	r7,#0x010		
		mov	r8,#0x020		
		mov	r5,#0x030
		mov r3,#0x000      					
		ldr r6, = GPIO_PORTF_BASE + (0x30<<2)		

		;;FIN configuration 
		
		
		;จจจจจจจจจจจจจจจจจจจจจจจจจจจPARTIE PROGRAMME
		str	r3,[r6]
		mov	r4,#0
		mov	r2,#0
		
boucle	ldr	r9,[r10]
		cmp	r4,#0
		bne	saute
		cmp	r2,#0
		bne	saute
		cmp	r9,r11
		bne	saute
		str	r7,[r6]
		mov	r4,#1
		
saute	ldr	r9,[r10]
		cmp	r4,#0
		bne saute2
		cmp	r2,#0
		bne	saute2
		cmp	r9,r12
		bne	saute2
		str	r8,[r6]
		mov	r2,#1
		
saute2	ldr	r9,[r10]
		cmp	r4,#1
		bne	saute3
		cmp	r2,#0
		bne	saute3
		cmp	r9,r12
		bne	saute3
		str	r5,[r6]
		mov	r2,#1

saute3	ldr	r9,[r10]
		cmp	r4,#0
		bne	saute4
		cmp	r2,#1
		bne	saute4
		cmp	r9,r11
		bne	saute4
		str	r5,[r6]
		mov	r4,#1
	

saute4	b	boucle

		END
		
		
		
		
		
		
		