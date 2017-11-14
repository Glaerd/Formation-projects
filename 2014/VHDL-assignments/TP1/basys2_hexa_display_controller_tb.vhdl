library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity basys2_hexa_display_controller_tb is
end entity;

architecture testbench of basys2_hexa_display_controller_tb is
	constant hp : time := 10 ns;
	constant per : time := 2*hp; 
	signal clk			: std_logic := '0';
	signal resetn		: std_logic;
	signal hexa0		: std_logic_vector(6 downto 0);
	signal hexa1		: std_logic_vector(6 downto 0);
	signal hexa2		: std_logic_vector(6 downto 0);
	signal hexa3		: std_logic_vector(6 downto 0);
	signal dot_point	: std_logic_vector(3 downto 0);
	signal en			: std_logic_vector(3 downto 0);
	signal seg			: std_logic_vector(6 downto 0);
	signal dp			: std_logic;
	signal an			: std_logic_vector(3 downto 0);
begin
	clk	<= not clk after hp;
	resetn <= '1', '0' after per;
	hexa0 <= "0001";
	hexa1 <= "0010";
	hexa2 <= "0011";
	hexa3 <= "0100";
	dot_point <= "0000";
	en <= "1111";
	demo: entity work.basys2_hexa_display_controller
		port map(
			clk			=> clk,
			resetn		=> resetn,
			hexa0		=> hexa0,
			hexa1		=> hexa1,
			hexa2		=> hexa2,
			hexa3		=> hexa3,
			dot_point	=> dot_point,
			en			=> en,
			seg			=> seg,
			dp			=> dp,
			an			=> an
		);
end architecture;