library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity PWM8_demo is
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

architecture rtl of PWM8_demo is
begin
	demo: entity work.PWM8
		port map(
			clk			=> clk,
			resetn		=> resetn,
			led0		=> led0,
			led1		=> led1,
			led2		=> led2,
			led3		=> led3,
			led4		=> led4,
			led5		=> led5,
			led6		=> led6,
			led7		=> led7
		);
end architecture;