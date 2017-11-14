library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity PWM8_tb is
end entity;

architecture testbench of PWM8_tb is
	constant hp : time := 10 ns;
	constant per : time := 2*hp;
	signal clk			: std_logic := '0';
	signal resetn		: std_logic;
	signal led0			: std_logic;
	signal led1			: std_logic;
	signal led2			: std_logic;
	signal led3			: std_logic;
	signal led4			: std_logic;
	signal led5			: std_logic;
	signal led6			: std_logic;
	signal led7			: std_logic;
	
begin
	clk	<= not clk after hp;
	resetn <= '1', '0' after per;
	
	dut: entity work.PWM8
		port map(
			clk			=> clk,
			resetn		=> resetn,
			led0		=> led0,
			led1		=> led1,
			led2		=> led2,
			led3		=> led3,
			led4		=> led4,
			led5		=> led5,
			led6		=> led6,
			led7		=> led7
		);
end architecture;