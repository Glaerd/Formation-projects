library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity feux_tricolores_tb is
end entity;

architecture testbench of feux_tricolores_tb is
	constant hp : time := 10 ns;
	constant per : time := 2*hp;
	constant part1 : time := 25 ms;
	signal clk			: std_logic := '0';
	signal resetn		: std_logic;
	signal pb			: std_logic := '0';
	signal feu1v		: std_logic;
	signal feu1o		: std_logic;
	signal feu1r		: std_logic;
	signal feu2v		: std_logic;
	signal feu2o		: std_logic;
	signal feu2r		: std_logic;
begin
	clk	<= not clk after hp;
	resetn <= '1', '0' after per;
	pb <= not pb after part1;
	dut: entity work.feux_tricolores
		port map(
			clk			=> clk,
			resetn		=> resetn,
			pb			=> pb,
			feu1v		=> feu1v,
			feu1o		=> feu1o,
			feu1r		=> feu1r,
			feu2v		=> feu2v,
			feu2o		=> feu2o,
			feu2r		=> feu2r
		);
end architecture;