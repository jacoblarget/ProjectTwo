# test: RedBlackTree.class
# 	java -jar junit5.jar -cp . --scan-classpath -n RedBlackTree

test: TestRedBlackTree.class
	java -jar junit5.jar -cp . --scan-classpath -n TestRedBlackTree

TestRedBlackTree.class: TestRedBlackTree.java RedBlackTree.class
	javac -cp .:junit5.jar TestRedBlackTree.java

RedBlackTree.class: RedBlackTree.java SortedCollectionInterface.class
	javac -cp .:junit5.jar RedBlackTree.java

SortedCollectionInterface.class: SortedCollectionInterface.java
	javac SortedCollectionInterface.java

clean:
	rm *.class
