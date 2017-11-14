library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity feux_tricolores is
	port (
		clk			: in	std_logic;
		resetn		: in	std_logic;
		pb			: in	std_logic;
		feu1v		: out	std_logic;
		feu1o		: out	std_logic;
		feu1r		: out	std_logic;
		feu2v		: out	std_logic;
		feu2o		: out	std_logic;
		feu2r		: out	std_logic
	);
end entity;

architecture rtl of feux_tricolores is
	constant tempo05 :	natural := 25000000;
	constant tempo1 : 	natural := 50000000;
	constant tempo22 : 	natural := 110000000;
	
	signal reset : std_logic;
	signal reg : std_logic_vector(27 downto 0);
	signal next_reg : std_logic_vector(27 downto 0);
	signal fintemp : std_logic := '0';
	signal temp : std_logic_vector(27 downto 0) := std_logic_vector(to_unsigned(tempo22,28));
	
	type etat is (f1v2r, f1o2r, f1r2v, f1r2o, cligno1, cligno2);
	signal present : etat;
	signal futur : etat;
	
	begin
	
	reset <= not resetn;
	--------------- PARTIE OPERATIVE ---------------
	operative : process (clk,reset) is
	begin
		if reset='0' then reg <=(others=>'0');
		elsif rising_edge(clk) then reg <= next_reg;
		end if;
	end process operative;
		fintemp <= '1' when (unsigned(reg) >= unsigned(temp)) else '0';
		next_reg <= (others=> '0') when (fintemp = '1') else std_logic_vector(unsigned(reg)+1);
	------------------------------------------------

	---------------     CONTROLE     ---------------
	registre_etat : process(clk, reset) is
	begin
		if reset='0' then present <= f1v2r;
		elsif rising_edge(clk) then present <= futur;
		end if;
	end process registre_etat;
	
	evolution : process (present, fintemp, pb) is
	begin
		futur <= present;
		case present is
		when f1v2r =>	if pb = '1' then futur <= cligno1;
						elsif (pb = '0') and (fintemp = '1') then futur <= f1o2r;
						end if;
		when f1o2r => 	if pb = '1' then futur <= cligno1;
						elsif (pb = '0') and (fintemp = '1') then futur <= f1r2v;
						end if;
		when f1r2v => 	if pb = '1' then futur <= cligno1;
						elsif (pb = '0') and (fintemp = '1') then futur <= f1r2o;
						end if;
		when f1r2o => 	if pb = '1' then futur <= cligno1;
						elsif (pb = '0') and (fintemp = '1') then futur <= f1v2r;
						end if;
		when cligno1 => if (pb = '1') and (fintemp = '1') then futur <= cligno2;
						elsif pb = '0' then futur <= f1v2r;
						end if;
		when cligno2 => if (pb = '1') and (fintemp = '1') then futur <= cligno1;
						elsif pb = '0' then futur <= f1v2r;
						end if;
		end case;
	end process evolution;
	
	actions : process(present) is
	begin
		temp <= std_logic_vector(to_unsigned(tempo22,28));
		feu1v <= '1';
		feu1o <= '0';
		feu1r <= '0';
		feu2v <= '0';
		feu2o <= '0';
		feu2r <= '1';
	
		case present is
		when f1v2r	=>	null;
		when f1o2r	=>	temp <= std_logic_vector(to_unsigned(tempo1,28));
						feu1v <= '0';
						feu1o <= '1';
						feu1r <= '0';
						feu2v <= '0';
						feu2o <= '0';
						feu2r <= '1';
		when f1r2v	=>	temp <= std_logic_vector(to_unsigned(tempo22,28));
						feu1v <= '0';
						feu1o <= '0';
						feu1r <= '1';
						feu2v <= '1';
						feu2o <= '0';
						feu2r <= '0';
		when f1r2o	=>	temp <= std_logic_vector(to_unsigned(tempo1,28));
						feu1v <= '0';
						feu1o <= '0';
						feu1r <= '1';
						feu2v <= '0';
						feu2o <= '1';
						feu2r <= '0';
		when cligno1 =>	temp <= std_logic_vector(to_unsigned(tempo05,28));
						feu1v <= '0';
						feu1o <= '1';
						feu1r <= '0';
						feu2v <= '0';
						feu2o <= '0';
						feu2r <= '0';
		when cligno2 =>	temp <= std_logic_vector(to_unsigned(tempo05,28));
						feu1v <= '0';
						feu1o <= '0';
						feu1r <= '0';
						feu2v <= '0';
						feu2o <= '1';
						feu2r <= '0';
		end case;
	end process actions;	
	------------------------------------------------
end architecture;
	