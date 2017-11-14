# Copyright (C) 1991-2008 Altera Corporation
# Your use of Altera Corporation's design tools, logic functions 
# and other software and tools, and its AMPP partner logic 
# functions, and any output files from any of the foregoing 
# (including device programming or simulation files), and any 
# associated documentation or information are expressly subject 
# to the terms and conditions of the Altera Program License 
# Subscription Agreement, Altera MegaCore Function License 
# Agreement, or other applicable license agreement, including, 
# without limitation, that your use is for the sole purpose of 
# programming logic devices manufactured by Altera and sold by 
# Altera or its authorized distributors.  Please refer to the 
# applicable agreement for further details.

# Quartus II: Generate Tcl File for Project
# File: design3.tcl
# Generated on: Sat Aug 09 20:30:47 2008

# Load Quartus II Tcl Project package
package require ::quartus::project

set need_to_close_project 0
set make_assignments 1

# Check that the right project is open
if {[is_project_open]} {
	if {[string compare $quartus(project) "design2"]} {
		puts "Project design1 is not open"
		set make_assignments 0
	}
} else {
	# Only open if not already open
	if {[project_exists design1]} {
		project_open -revision design2 design2
	} else {
		project_new -revision design2 design2
	}
	set need_to_close_project 1
}

# Make assignments
if {$make_assignments} {
	set_global_assignment -name FAMILY "Cyclone II"
	set_global_assignment -name DEVICE EP2C35F672C6
	set_global_assignment -name TOP_LEVEL_ENTITY design3
	set_global_assignment -name ORIGINAL_QUARTUS_VERSION "8.0 SP1"
	set_global_assignment -name PROJECT_CREATION_TIME_DATE "18:04:40  AUGUST 09, 2008"
	set_global_assignment -name LAST_QUARTUS_VERSION "8.0 SP1"
	set_global_assignment -name EDA_SIMULATION_TOOL "ModelSim (VHDL)"
	set_global_assignment -name EDA_OUTPUT_DATA_FORMAT VHDL -section_id eda_simulation
	set_global_assignment -name USE_GENERATED_PHYSICAL_CONSTRAINTS OFF -section_id eda_palace
	set_global_assignment -name DEVICE_FILTER_PIN_COUNT 672
	set_global_assignment -name STRATIX_DEVICE_IO_STANDARD "3.3-V LVTTL"
	set_global_assignment -name USE_CONFIGURATION_DEVICE ON
	set_global_assignment -name PARTITION_NETLIST_TYPE SOURCE -section_id Top
	set_global_assignment -name PARTITION_COLOR 14622752 -section_id Top
	set_global_assignment -name LL_ROOT_REGION ON -section_id "Root Region"
	set_global_assignment -name LL_MEMBER_STATE LOCKED -section_id "Root Region"
	set_global_assignment -name BDF_FILE design2.bdf
	set_location_assignment PIN_AF10 -to a0
	set_location_assignment PIN_V20 -to a1
	set_location_assignment PIN_AB23 -to a2
	set_location_assignment PIN_Y23 -to a3
	set_location_assignment PIN_U9 -to a4
	set_location_assignment PIN_T2 -to a5
	set_location_assignment PIN_R2 -to a6
	set_location_assignment PIN_L3 -to a7
	set_location_assignment PIN_AB12 -to b0
	set_location_assignment PIN_AC12 -to c0
	set_location_assignment PIN_AD11 -to d0
	set_location_assignment PIN_AE11 -to e0
	set_location_assignment PIN_V14 -to f0
	set_location_assignment PIN_N9 -to g7
	set_location_assignment PIN_V21 -to b1
	set_location_assignment PIN_V22 -to b2
	set_location_assignment PIN_AA25 -to b3
	set_location_assignment PIN_U1 -to b4
	set_location_assignment PIN_P6 -to b5
	set_location_assignment PIN_P4 -to b6
	set_location_assignment PIN_L2 -to b7
	set_location_assignment PIN_W21 -to c1
	set_location_assignment PIN_AC25 -to c2
	set_location_assignment PIN_AA26 -to c3
	set_location_assignment PIN_U2 -to c4
	set_location_assignment PIN_P7 -to c5
	set_location_assignment PIN_P3 -to c6
	set_location_assignment PIN_L9 -to c7
	set_location_assignment PIN_Y22 -to d1
	set_location_assignment PIN_AC26 -to d2
	set_location_assignment PIN_Y26 -to d3
	set_location_assignment PIN_T4 -to d4
	set_location_assignment PIN_T9 -to d5
	set_location_assignment PIN_M2 -to d6
	set_location_assignment PIN_L6 -to d7
	set_location_assignment PIN_AA24 -to e1
	set_location_assignment PIN_AB26 -to e2
	set_location_assignment PIN_Y25 -to e3
	set_location_assignment PIN_R7 -to e4
	set_location_assignment PIN_R5 -to e5
	set_location_assignment PIN_M3 -to e6
	set_location_assignment PIN_L7 -to e7
	set_location_assignment PIN_AA23 -to f1
	set_location_assignment PIN_AB25 -to f2
	set_location_assignment PIN_U22 -to f3
	set_location_assignment PIN_R6 -to f4
	set_location_assignment PIN_R4 -to f5
	set_location_assignment PIN_M5 -to f6
	set_location_assignment PIN_P9 -to f7
	set_location_assignment PIN_AB24 -to g1
	set_location_assignment PIN_Y24 -to g2
	set_location_assignment PIN_W24 -to g3
	set_location_assignment PIN_T3 -to g4
	set_location_assignment PIN_R3 -to g5
	set_location_assignment PIN_M4 -to g6
	set_location_assignment PIN_V13 -to g0
	set_instance_assignment -name PARTITION_HIERARCHY root_partition -to | -section_id Top

	# Commit assignments
	export_assignments

	# Close project
	if {$need_to_close_project} {
		project_close
	}
}
