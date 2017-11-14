#
# minimum Makefile for MPI at ESIEE - LPerroton 25/9/2017
#

# How to use:
# 1) compile: make tokenring
# 2) execute: mpiexec -n 4 -hosts pc1107a,pc1107b,pc1107c,pc1107d ./tokenring
#             mpiexec -n 8 -f pc1207 ./tokenring
# 3) convert: make tokenring.slog2
# 4) watch  : jumpshot tokenring.slog2 &

# includes arch. specific (obsolete)
# include $(MPI_ROOT)/conf/$(MPI_ARCH).def

# To debug code (optional)
#CFLAGS	=	-g

# Uncomment to create log files myprog.clog2
CFLAGS	+= -mpe=mpilog

# then convert logfile: make myprog.slog2 
# Implicit pattern rule for converting .clog2 file to .slog2
%.slog2 : %.clog2
	clog2TOslog2 $<

# view log trace: jumpshot myprog.slog2

# LIBS & LFLAGS to add to the MPI ones
LIBS	=	-lm
#LFLAGS	=	-L$(MPI_ROOT)/lib/
LDFLAGS	=	$(LFLAGS) $(LIBS)

# Let mpicc handles everything (good luck...)
CC = mpicc

.c:
	$(CC) $(CFLAGS) -o $@ $< $(LDFLAGS)
	mv $@ $(HOME)/pvm3/MPI/bin/
