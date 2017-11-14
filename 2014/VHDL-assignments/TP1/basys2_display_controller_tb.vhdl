library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity basys2_display_controller_tb is
end entity;

architecture testbench of basys2_display_controller_tb is
	constant hp : time := 10 ns;
	constant per : time := 2*hp; 
	signal clk			: std_logic := '0';
	signal resetn		: std_logic;
	signal segments0	: std_logic_vector(6 downto 0);
	signal segments1	: std_logic_vector(6 downto 0);
	signal segments2	: std_logic_vector(6 downto 0);
	signal segments3	: std_logic_vector(6 downto 0);
	signal dot_point	: std_logic_vector(3 downto 0);
	signal en			: std_logic_vector(3 downto 0);
	signal seg			: std_logic_vector(6 downto 0);
	signal dp			: std_logic;
	signal an			: std_logic_vector(3 downto 0);
begin
	clk	<= not clk after hp;
	resetn <= '1', '0' after per;
	segments0 <= "0110001";
	segments1 <= "0000001";
	segments2 <= "0000001";
	segments3 <= "1110001";
	dot_point <= "1111";
	en <= "1111";
	dut: entity work.basys2_display_controller
		port map(
			clk			=> clk,
			resetn		=> resetn,
			segments0	=> segments0,
			segments1	=> segments1,
			segments2	=> segments2,
			segments3	=> segments3,
			dot_point	=> dot_point,
			en			=> en,
			seg			=> seg,
			dp			=> dp,
			an			=> an
		);
end architecture;