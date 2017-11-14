library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity basys2_display_controller_demo is
	port (
		clk			: in	std_logic;
		resetn		: in	std_logic;
		dot_point	: in	std_logic_vector(3 downto 0);
		en			: in	std_logic_vector(3 downto 0);
		seg			: out	std_logic_vector(6 downto 0);
		dp			: out	std_logic;
		an			: out	std_logic_vector(3 downto 0)
	);
end entity;

architecture rtl of basys2_display_controller_demo is
begin
	demo: entity work.basys2_display_controller
		port map(
			clk			=> clk,
			resetn		=> resetn,
			segments0	=> "0110001",
			segments1	=> "0000001",
			segments2	=> "0000001",
			segments3	=> "1110001",
			dot_point	=> dot_point,
			en			=> en,
			seg			=> seg,
			dp			=> dp,
			an			=> an
		);
end architecture;