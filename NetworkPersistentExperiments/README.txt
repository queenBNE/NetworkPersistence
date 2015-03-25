README 

To compile the DescendingPersistentHomology java file, go to the directory 'NetworkPersistentExperiments':

cd ~/git/PersistentNetworks/NetworkPersistentExperiments

The following command compiles the source code:
javac -d bin -sourcepath src -cp lib/javaplex-4.2.1.jar:lib/network_persistence.jar src/network/persistence/bash/DescendingPersistentHomology.java 

To then run the code, execute
java -cp bin:lib/javaplex-4.2.1.jar:lib/network_persistence.jar network.persistence.bash.DescendingPersistentHomology <network-file-name> <network-result-prefix>

For example use:
<network-file-name> 		networks/networkScience.csv
<network-result-prefix> 	results/networkScience

Complete command:
java -cp bin:lib/javaplex-4.2.1.jar:lib/network_persistence.jar network.persistence.bash.DescendingPersistentHomology networks/networkScience.csv results/networkScience

There are some scripts in the script folder too. 
