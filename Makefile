# runAll: runRedBlackTree runData runFrontEnd runBackEnd
# runAll: runRedBlackTree runData runBackEnd
run: compile
	java Frontend updatedPokemon.csv

compile:
	# javac -cp .:junit5.jar RedBlackTreeTests.java
	javac RedBlackTree.java
	javac SortedCollectionInterface.java
	# javac -cp .:junit5.jar DataWranglerTests.java
	javac Pokemon.java
	javac PokemonDataReader.java
	javac PokemonDataReaderInterface.java
	javac PokemonInterface.java
	javac -cp .:junit5.jar FrontEndDeveloperTests.java
	javac Frontend.java
	# javac -cp .:junit5.jar BackEndDeveloperTests.java
	javac Backend.java
	javac BackendInterface.java

# test: testData testBackend testFrontend
test: testData testBackend

testFrontend:
	java -jar junit5.jar -cp . --scan-classpath -n FrontEndDeveloperTests

testBackend:
	java -jar junit5.jar -cp . --scan-classpath -n BackEndDeveloperTests

testData: compile
	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTests

clean:
	$(RM) *.class
# runRedBlackTree: compileRedBlackTree
# 	java -jar junit5.jar -cp . --scan-classpath -n RedBlackTreeTests

# compileRedBlackTree: RedBlackTreeTests.java RedBlackTree.java SortedCollectionInterface.java
# 	javac -cp .:junit5.jar RedBlackTreeTests.java
# 	javac RedBlackTree.java
# 	javac SortedCollectionInterface.java

# runData: compileData
# 	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTests

# compileData: DataWranglerTests.java updatedPokemon.csv Pokemon.java PokemonDataReader.java PokemonDataReaderInterface.java PokemonInterface.java
# 	javac -cp .:junit5.jar DataWranglerTests.java
# 	javac Pokemon.java
# 	javac PokemonDataReader.java
# 	javac PokemonDataReaderInterface.java
# 	javac PokemonInterface.java

# # runFrontEnd: compileFrontEnd
# # 	java -jar junit5.jar -cp . --scan-classpath -n FrontEndDeveloperTests

# # compileFrontEnd: FrontEndDeveloperTests.java Frontend.java
# # 	javac -cp .:junit5.jar FrontEndDeveloperTests.java
# # 	javac Frontend.java


# runBackEnd: compileBackEnd
# 	java -jar junit5.jar -cp . --scan-classpath -n BackEndDeveloperTests

# compileBackEnd: BackEndDeveloperTests.java Backend.java BackendInterface.java
# 	javac -cp .:junit5.jar BackEndDeveloperTests.java
# 	javac Backend.java
# 	javac BackendInterface.java

# clean:
# 	rm *.class