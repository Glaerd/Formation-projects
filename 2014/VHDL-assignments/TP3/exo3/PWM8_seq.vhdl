library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity PWM8_seq is
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

architecture rtl of PWM8_seq is
	constant Tpwm :	natural := 500000;
	constant mult : natural := Tpwm /100;
	
	signal reset : std_logic;
	signal reg1 : std_logic_vector(18 downto 0);
	signal next_reg1 : std_logic_vector(18 downto 0);
	signal reg2 : std_logic_vector(23 downto 0);
	signal next_reg2 : std_logic_vector(23 downto 0);
	signal fintemp : std_logic := '0';
	signal fin025 : std_logic := '0';
	signal en : std_logic_vector(7 downto 0);
	
	type etat is (on0,on1,on2,on3,on4,on5,on6,on7,off0,off1,off2,off3,off4,off5,off6,off7);
	signal present : etat;
	signal futur : etat;
	
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
		if reset='0' then	reg1 <=(others=>'0');
		elsif rising_edge(clk) then reg1 <= next_reg1;
		end if;
	end process;
		
	fintemp <= '1' when (unsigned(reg1) >= Tpwm) else '0';
	next_reg1 <= (others=> '0') when (fintemp = '1') else std_logic_vector(unsigned(reg1)+1);

	process (clk,reset) is
	begin
		if reset='0' then	reg2 <=(others=>'0');
		elsif rising_edge(clk) then reg2 <= next_reg2;
		end if;
	end process;
		
	fin025 <= '1' when (unsigned(reg2) >= 12500000) else '0';
	next_reg2 <= (others=> '0') when (fin025 = '1') else std_logic_vector(unsigned(reg2)+1);
	
	registre_etat : process(clk, resetn) is
	begin
		if resetn='1' then present <= off7;
		elsif rising_edge(clk) then present <= futur;
		end if;
	end process registre_etat;
	
	evolution : process (present, fin025) is
	begin
		futur <= present;
		if fin025='1' then
			case present is
			when off7 => futur <= on0;
			when on0 => futur <= on1;
			when on1 => futur <= on2;
			when on2 => futur <= on3;
			when on3 => futur <= on4;
			when on4 => futur <= on5;
			when on5 => futur <= on6;
			when on6 => futur <= on7;
			when on7 => futur <= off0;
			when off0 => futur <= off1;
			when off1 => futur <= off2;
			when off2 => futur <= off3;
			when off3 => futur <= off4;
			when off4 => futur <= off5;
			when off5 => futur <= off6;
			when off6 => futur <= off7;
			end case;
		end if;
	end process evolution;
	
	actions : process(present) is
	begin
		en <= "00000000";
		case present is
		when off7 => null;
		when on0 => en <= "00000001";
		when on1 => en <= "00000011";
		when on2 => en <= "00000111";
		when on3 => en <= "00001111";
		when on4 => en <= "00011111";
		when on5 => en <= "00111111";
		when on6 => en <= "01111111";
		when on7 => en <= "11111111";
		when off0 => en <= "11111110";
		when off1 => en <= "11111100";
		when off2 => en <= "11111000";
		when off3 => en <= "11110000";
		when off4 => en <= "11100000";
		when off5 => en <= "11000000";
		when off6 => en <= "10000000";
		end case;
	end process actions;

	led0 <= '1' when (unsigned(reg1) <= to_unsigned(var0,19) and en(0) = '1') else '0';
	led1 <= '1' when (unsigned(reg1) <= to_unsigned(var1,19) and en(1) = '1') else '0';
	led2 <= '1' when (unsigned(reg1) <= to_unsigned(var2,19) and en(2) = '1') else '0';
	led3 <= '1' when (unsigned(reg1) <= to_unsigned(var3,19) and en(3) = '1') else '0';
	led4 <= '1' when (unsigned(reg1) <= to_unsigned(var4,19) and en(4) = '1') else '0';
	led5 <= '1' when (unsigned(reg1) <= to_unsigned(var5,19) and en(5) = '1') else '0';
	led6 <= '1' when (unsigned(reg1) <= to_unsigned(var6,19) and en(6) = '1') else '0';
	led7 <= '1' when (unsigned(reg1) <= to_unsigned(var7,19) and en(7) = '1') else '0';
		

end architecture;
	