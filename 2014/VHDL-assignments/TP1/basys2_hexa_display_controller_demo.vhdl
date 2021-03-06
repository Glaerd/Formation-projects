library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity basys2_hexa_display_controller_demo is
	port (
		clk			: in	std_logic;
		resetn		: in	std_logic;
		hexa3		: in	std_logic_vector(3 downto 0);
		en			: in	std_logic_vector(3 downto 0);
		seg			: out	std_logic_vector(6 downto 0);
		dp			: out	std_logic;
		an			: out	std_logic_vector(3 downto 0)
	);
end entity;

architecture rtl of basys2_hexa_display_controller_demo is
begin
	demo: entity work.basys2_hexa_display_controller
		port map(
			clk			=> clk,
			resetn		=> resetn,
			hexa0		=> "0001",
			hexa1		=> "0010",
			hexa2		=> "0011",
			hexa3		=> hexa3,
			dot_point	=> "0100",
			en			=> en,
			seg			=> seg,
			dp			=> dp,
			an			=> an
		);
end architecture;