library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity PWM_tb is
end entity;

architecture testbench of PWM_tb is
	constant hp : time := 10 ns;
	constant per : time := 2*hp;
	constant part1 : time := 20 ms;
	signal clk			: std_logic := '0';
	signal resetn		: std_logic;
	signal cmd			: std_logic_vector(7 downto 0) := std_logic_vector(to_unsigned(255,8));
	signal led			: std_logic;

begin
	clk	<= not clk after hp;
	resetn <= '1', '0' after per;
	cmd <= std_logic_vector(unsigned(cmd)-10) after part1;
	
	dut: entity work.PWM
		port map(
			clk			=> clk,
			resetn		=> resetn,
			cmd			=> cmd,
			led			=> led
		);
end architecture;