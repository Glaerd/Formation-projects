library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity PWM_demo is
	port (
		clk			: in	std_logic;
		resetn		: in	std_logic;
		cmd			: in	std_logic_vector(7 downto 0);
		led			: out	std_logic
	);
end entity;

architecture rtl of PWM_demo is
begin
	demo: entity work.PWM
		port map(
			clk			=> clk,
			resetn		=> resetn,
			cmd			=> cmd,
			led			=> led
		);
end architecture;