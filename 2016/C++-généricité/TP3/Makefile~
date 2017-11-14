HEADERS = $(wildcard *.hh)
SOURCES = $(wildcard *.cpp)
OBJECTS = $(SOURCES:.cpp=.o)

parcours : $(HEADERS) $(OBJECTS)
	$(CXX) $(OBJECTS) -o $@ -Wall -Wextra -std=c++11

%.o: %.cpp %.hh
	$(CXX) $< -c -o $@ -Wall -Wextra -std=c++11

%.o: %.cpp
	$(CXX) $< -c -o $@ -Wall -Wextra -std=c++11

clean:
	$(RM) $(OBJECTS)

.PHONY: clean
