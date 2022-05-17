CC :=gcc
CPP :=g++
LDFLAGS := -c
C_SOURCES :=$(wildcard *.c)
C_EXECUTABLE :=$(C_SOURCES:.c=)
CPP_SOURCES :=$(wildcard *.cpp)
CPP_EXECUTABLE :=$(CPP_SOURCES:.cpp=)

all:$(C_EXECUTABLE) $(CPP_EXECUTABLE)

$(C_EXECUTABLE):$(C_SOURCES)
		$(CC) $< $(LDFLAGS) $(CFLAGS) -o $@

$(CPP_EXECUTABLE):$(CPP_SOURCES)
		$(CPP) $@.cpp $(LDFLAGS) $(CFLAGS) -o $@

clean:
		rm -rf $(CPP_EXECUTABLE)