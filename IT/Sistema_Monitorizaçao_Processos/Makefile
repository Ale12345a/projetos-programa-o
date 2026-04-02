CC = gcc
CFLAGS = -Wall -g
LDFLAGS = -lncurses
SRC = $(wildcard src/*.c)
OBJ = $(SRC:.c=.o)
TARGET = monitor

all: $(TARGET)

$(TARGET): $(OBJ)
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)

clean:
	rm -f $(OBJ) $(TARGET)