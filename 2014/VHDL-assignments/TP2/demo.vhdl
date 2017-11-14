library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity demo is
	port (
		clk			: in	std_logic;
		resetn		: in	std_logic;
		pb			: in	std_logic;
		an			: out	std_logic_vector(3 downto 0);
		seg			: out	std_logic_vector(6 downto 0);
		dp			: out	std_logic
	);
end entity;

architecture rtl of demo is
signal seg0 : std_logic_vector(6 downto 0);
signal seg2 : std_logic_vector(6 downto 0);
signal f1v : std_logic;
signal f1o : std_logic;
signal f1r : std_logic;
signal f2v : std_logic;
signal f2o : std_logic;
signal f2r : std_logic;
signal f1vM : std_logic_vector(6 downto 0);
signal f1oM : std_logic_vector(6 downto 0);
signal f1rM : std_logic_vector(6 downto 0);
signal f2vM : std_logic_vector(6 downto 0);
signal f2oM : std_logic_vector(6 downto 0);
signal f2rM : std_logic_vector(6 downto 0);

begin
	f1vM <= "1110111" when f1v = '1' else "1111111";
	f1oM <= "1111110" when f1o = '1' else "1111111";
	f1rM <= "0111111" when f1r = '1' else "1111111";
	f2vM <= "1110111" when f2v = '1' else "1111111";
	f2oM <= "1111110" when f2o = '1' else "1111111";
	f2rM <= "0111111" when f2r = '1' else "1111111";
	seg0 <= (f1vM and f1oM and f1rM);
	seg2 <= (f2vM and f2oM and f2rM);
	
feux_tricolores : entity work.feux_tricolores
		port map(
			clk			=> clk,
			resetn		=> resetn,
			pb			=> pb,
			feu1v		=> f1v,
			feu1o		=> f1o,
			feu1r		=> f1r,
			feu2v		=> f2v,
			feu2o		=> f2o,
			feu2r		=> f2r
		);
		
	disp_contr : entity work.basys2_display_controller
		port map(
			clk			=> clk,
			resetn		=> resetn,
			segments0	=> seg0,
			segments1	=> "1111111",
			segments2	=> seg2,
			segments3	=> "1111111",
			dot_point	=> "0000",
			en			=> "0101",
			an			=> an,
			seg			=> seg,
			dp			=> dp
		);
end architecture;