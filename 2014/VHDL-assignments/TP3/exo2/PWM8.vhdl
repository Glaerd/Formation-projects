library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity PWM8 is
	port (
		clk			: in	std_logic;
		resetn		: in	std_logic;
		led0		: out	std_logic;
		led1		: out	std_logic;
		led2		: out	std_logic;
		led3		: out	std_logic;
		led4		: out	std_logic;
		led5		: out	std_logic;
		led6		: out	std_logic;
		led7		: out	std_logic
	);
end entity;

architecture rtl of PWM8 is
	constant Tpwm :	natural := 500000;
	constant mult : natural := Tpwm /100;
	
	signal reset : std_logic;
	signal reg : std_logic_vector(18 downto 0);
	signal next_reg : std_logic_vector(18 downto 0);
	signal fintemp : std_logic := '0';
	

	
	constant var0 : natural := 5* mult;
	constant var1 : natural := 10* mult;
	constant var2 : natural := 15* mult;
	constant var3 : natural := 20* mult;
	constant var4 : natural := 25* mult;
	constant var5 : natural := 30* mult;
	constant var6 : natural := 40* mult;
	constant var7 : natural := 100* mult;
	begin
	
	reset <= not resetn;

	process (clk,reset) is
	begin
		if reset='0' then	reg <=(others=>'0');
		elsif rising_edge(clk) then reg <= next_reg;
		end if;
	end process;
		
	fintemp <= '1' when (unsigned(reg) >= Tpwm) else '0';
	next_reg <= (others=> '0') when (fintemp = '1') else std_logic_vector(unsigned(reg)+1);

	led0 <= '1' when (unsigned(reg) <= to_unsigned(var0,19)) else '0';
	led1 <= '1' when (unsigned(reg) <= to_unsigned(var1,19)) else '0';
	led2 <= '1' when (unsigned(reg) <= to_unsigned(var2,19)) else '0';
	led3 <= '1' when (unsigned(reg) <= to_unsigned(var3,19)) else '0';
	led4 <= '1' when (unsigned(reg) <= to_unsigned(var4,19)) else '0';
	led5 <= '1' when (unsigned(reg) <= to_unsigned(var5,19)) else '0';
	led6 <= '1' when (unsigned(reg) <= to_unsigned(var6,19)) else '0';
	led7 <= '1' when (unsigned(reg) <= to_unsigned(var7,19)) else '0';
		

end architecture;
	