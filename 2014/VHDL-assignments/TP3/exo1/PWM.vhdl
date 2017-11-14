library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity PWM is
	port (
		clk			: in	std_logic;
		resetn		: in	std_logic;
		cmd			: in	std_logic_vector(7 downto 0);
		led			: out	std_logic
	);
end entity;

architecture rtl of PWM is
	constant Tpwm :	natural := 500000;
	constant mult : natural := Tpwm / 255; 
	
	signal reset : std_logic;
	signal reg : std_logic_vector(18 downto 0);
	signal next_reg : std_logic_vector(18 downto 0);
	signal fintemp : std_logic := '0';
	
	signal etat : std_logic;
	signal var : natural range 0 to 524287;
	begin
	
	reset <= not resetn;

	process (clk,reset) is
	begin
		if reset='0' then	reg <=(others=>'0');
							led <= '1';
		elsif rising_edge(clk) then reg <= next_reg;
									led <= etat;
		end if;
	end process;
		
	fintemp <= '1' when (unsigned(reg) >= Tpwm) else '0';
	next_reg <= (others=> '0') when (fintemp = '1') else std_logic_vector(unsigned(reg)+1);
	
	var <= to_integer(unsigned(cmd)) * mult;
	etat <=  '1' when (unsigned(reg) <= to_unsigned(var,19))
		else '0';
		

end architecture;
	