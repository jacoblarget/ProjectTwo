runAll: runRedBlackTree runBackend
# runAll: runRedBlackTree runData runFrontend runBackend

runRedBlackTree: compileRedBlackTree
	java -jar junit5.jar -cp . --scan-classpath -n TestRedBlackTree

compileRedBlackTree: TestRedBlackTree.java RedBlackTree.java SortedCollectionInterface.java
	javac -cp .:junit5.jar TestRedBlackTree.java
	javac RedBlackTree.java
	javac SortedCollectionInterface.java

# runData: compileData
# 	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTests

# compileData: DataWranglerTests.java updatedPokemon.csv Pokemon.java PokemonDataReader.java PokemonDataReaderInterface.java PokemonInterface.java
# 	javac -cp .:junit5.jar DataWranglerTests.java
# 	javac Pokemon.java
# 	javac PokemonDataReader.java
# 	javac PokemonDataReaderInterface.java
# 	javac PokemonInterface.java

# runFrontend: compileFrontend
# 	java -jar junit5.jar -cp . --scan-classpath -n FrontEndDeveloperTests

# compileFrontEnd: FrontEndDeveloperTests.java Frontend.java
# 	javac -cp .:junit5.jar FrontEndDeveloperTests.java
# 	javac Frontend.java


runBackend: compileBackend
	java -jar junit5.jar -cp . --scan-classpath -n BackEndDeveloperTests

compileBackend: BackEndDeveloperTests.java Backend.java BackendInterface.java
	javac -cp .:junit5.jar BackEndDeveloperTests.java
	javac Backend.java
	javac BackendInterface.java

clean:
	rm *.class