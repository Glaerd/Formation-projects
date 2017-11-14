library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity basys2_hexa_display_controller is
	port (
		clk			: in	std_logic;
		resetn		: in	std_logic;
		hexa0		: in	std_logic_vector(3 downto 0);
		hexa1		: in	std_logic_vector(3 downto 0);
		hexa2		: in	std_logic_vector(3 downto 0);
		hexa3		: in	std_logic_vector(3 downto 0);
		dot_point	: in	std_logic_vector(3 downto 0);
		en			: in	std_logic_vector(3 downto 0);
		seg			: out	std_logic_vector(6 downto 0);
		dp			: out	std_logic;
		an			: out	std_logic_vector(3 downto 0)
	);
end entity;

architecture rtl of basys2_hexa_display_controller is
	signal reg : std_logic_vector(16 downto 0);
	signal next_reg : std_logic_vector(16 downto 0);
	signal fincompt : std_logic;
	signal hexa_seg : std_logic_vector(6 downto 0);
	signal hexa : std_logic_vector(3 downto 0);
	type etat is (init, s0, s1, s2, s3);
	signal present : etat;
	signal futur : etat;
	
	begin
	
	--------------- PARTIE OPERATIVE ---------------
	operative : process (clk,resetn) is
	begin
		if resetn='1' then reg <=(others=>'0');
		elsif rising_edge(clk) then reg <= next_reg;
		end if;
	end process operative;
		fincompt <= '1' when (unsigned(reg) >= 125000) else '0';
		next_reg <= (others=> '0') when (fincompt = '1') else std_logic_vector(unsigned(reg)+1);
		hexa_seg <= "1001111" when hexa = "0001"   --1
			else	"0010010" when hexa = "0010"   --2
			else	"0000110" when hexa = "0011"   --3
			else	"1001100" when hexa = "0100"   --4
			else	"0100100" when hexa = "0101"   --5
			else	"0100000" when hexa = "0110"   --6
			else	"0001111" when hexa = "0111"   --7
			else	"0000000" when hexa = "1000"   --8
			else	"0000100" when hexa = "1001"   --9
			else	"0001000" when hexa = "1010"   --A
			else	"1100000" when hexa = "1011"   --b
			else	"0110001" when hexa = "1100"   --C
			else	"1000010" when hexa = "1101"   --d
			else	"0110000" when hexa = "1110"   --E
			else	"0111000" when hexa = "1111"   --F
			else "0000001";
	------------------------------------------------
	---------------     CONTROLE     ---------------
	registre_etat : process(clk, resetn) is
	begin
		if resetn='1' then present <= init;
		elsif rising_edge(clk) then present <= futur;
		end if;
	end process registre_etat;
	
	evolution : process (present, fincompt) is
	begin
		futur <= present;
		if fincompt='1' then
			case present is
			when init => futur <= s0;
			when s0 => futur <= s1;
			when s1 => futur <= s2;
			when s2 => futur <= s3;
			when s3 => futur <= s0;
			end case;
		end if;
	end process evolution;
	
	actions : process(present,dot_point,hexa_seg,hexa0,hexa1,hexa2,hexa3,en) is
	begin
		hexa <= "0000";
		seg <= "0000000";
		dp <= '0';
		an <= "0000";
	
		case present is
		when init	=>	null;
		when s0		=>	hexa <= hexa0;
						seg <= hexa_seg ;
						dp <= not dot_point(0);
						an <= not (en(0)&"000");
		when s1		=>	hexa <= hexa1;
						seg <= hexa_seg ;
						dp <= not dot_point(1);
						an <= not ('0'&en(1)&"00");
		when s2		=>	hexa <= hexa2;
						seg <= hexa_seg ;
						dp <= not dot_point(2);
						an <= not ("00"&en(2)&'0');
		when s3		=>	hexa <= hexa3;
						seg <= hexa_seg ;
						dp <= not dot_point(3);
						an <= not ("000"&en(3));
		end case;
	end process actions;	
	------------------------------------------------
end architecture;
	