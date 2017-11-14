-----------------------------------
--    binary to BCD converter  ----
-----------------------------------
-- Creation : A. Exertier, 03/2008
-----------------------------------

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

-----------------------------------
--    Generic parameters
-----------------------------------
-- N_digit   : Number of digits
-----------------------------------
--          Inputs 
-----------------------------------
-- clk      : main clock
-- resetn   : asynchronous low active reset
-- en       : enable
-----------------------------------
--          Outputs
-----------------------------------
-- BCD       : BCD counter
----------------------------------- 

entity BCD_counter is
  generic (
    N_digit : positive:=4);
  port (
  clk      : in  std_logic;
  resetn   : in  std_logic;
  add_subn : in  std_logic;
  en       : in  std_logic;
  BCD      : out std_logic_vector(4*N_digit-1 downto 0)
  );
end entity;

architecture RTL of BCD_counter is
  
begin
  process(resetn,clk) is      
      variable cptBCD : unsigned(BCD'range);
      variable carry  : unsigned(0 downto 0);      
  begin
      if resetn = '0' then 
        cptBCD := (others => '0');        
      elsif rising_edge(clk) then
          if en  = '1' then             
            carry(0) := '1';                        
            for i in 0 to N_digit-1 loop
             if carry(0)='1' then
              if add_subn='1' then                             
                if cptBCD(4*i+3 downto 4*i) ="1001" then
                  cptBCD(4*i+3 downto 4*i) := X"0"; 
                  carry(0) := '1';
                else                 
                  cptBCD(4*i+3 downto 4*i) := cptBCD(4*i+3 downto 4*i)+1;
                  carry(0) := '0';
                end if; 
              else
                if cptBCD(4*i+3 downto 4*i) ="0000" then
                  cptBCD(4*i+3 downto 4*i) := X"9"; 
                  carry(0) := '1';
                else                 
                  cptBCD(4*i+3 downto 4*i) := cptBCD(4*i+3 downto 4*i)-1;
                  carry(0) := '0';
                end if;    
              end if;            
             end if;
            end loop;                     
          end if;                   
      end if;   
      BCD <= std_logic_vector(cptBCD);    
  end process;
  
end architecture;