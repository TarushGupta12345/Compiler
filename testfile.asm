# @author Tarush Gupta
# @date 2025-11-09

	.data
	
	.text
	.globl main
main:
	li $v0, 3	#load immediate 3
	move $a0, $v0	#prepare to print
	li $v0, 1	#print integer
	syscall
	li $a0, 10	#newline character
	li $v0, 11	#print character
	syscall
	li $v0, 10	#exit
	syscall
