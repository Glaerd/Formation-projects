	.file	"main.c"
	.section	.rodata
.LC0:
	.string	"Open FD : %d\n"
.LC1:
	.string	"1"
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$32, %rsp
	movl	%edi, -20(%rbp)
	movq	%rsi, -32(%rbp)
	movq	-32(%rbp), %rdx
	movl	-20(%rbp), %eax
	movq	%rdx, %rsi
	movl	%eax, %edi
	call	getInput
	movl	$0, %esi
	movq	%rax, %rdi
	movl	$0, %eax
	call	open
	movl	%eax, -4(%rbp)
	movl	-4(%rbp), %eax
	movl	%eax, %esi
	movl	$.LC0, %edi
	movl	$0, %eax
	call	printf
	movq	-32(%rbp), %rax
	addq	$16, %rax
	movq	(%rax), %rax
	movl	$.LC1, %esi
	movq	%rax, %rdi
	call	strcmp
	testl	%eax, %eax
	jne	.L2
	movl	-4(%rbp), %eax
	movl	%eax, %edi
	call	readFirstLine
	movq	%rax, %rdi
	call	printLine
	jmp	.L3
.L2:
	movl	-4(%rbp), %eax
	movl	%eax, %edi
	call	readLine
.L3:
	movl	$0, %eax
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.ident	"GCC: (Debian 4.7.2-5) 4.7.2"
	.section	.note.GNU-stack,"",@progbits
