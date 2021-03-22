DataWrangler README for Project Two (CS400 @ UW Madison)
========================================================

Name of DataWrangler: Patrick Nowakowski 
@wisc.edu Email of DataWrangler: pnowakowski@wisc.edu
Group: JD 
Team: Blue

Files Written by Me:
--------------------
PokemonInterface.java
Defines the methods and structure for the Pokemon.java Class

Pokemon.java
Implements the methods declared in PokemonInterface.java, as well as private fields
These methods include a Pokemon object constructor, toString(), equals(), compareTo(),
additional comparison methods, and getters.

PokemonDataReaderInterface.java
Base Interface for PokemonDataReader, which declares the readDataSet() method to be
implemented.

PokemonDataReader.java
Implements the readDataSet() method that takes a Reader object for a (CSV) file.
It uses the Reader.read() method to read the CSV file on character at a time and store
the readings as String and int stats in Pokemon objects, and those Pokemon into a
returned List<PokemonInterface>.

DataWranglerTests.java
Week One Tests for the Pokemon and PokemonDataReader Classes. Tests include
constructor and expected output JUnit 5 tests that also return booleans.

And of course, this file, DataWrangler README

Additional Contributions:
-------------------------
Helping to organize team communications through Discord.

Signature:
----------
Patrick Nowakowski 3/19/2021

I referenced my previous code from Project One, to see in my old Backend files
how the DataWrangler's readDataSet() method was called to make sure I was writing
it correctly in my DataWrangler code for this project. I also did come research on
the interwebs of CSV files and how to read from them using Reader.read(), but didn't
copy any code. Just letting you know.
 
