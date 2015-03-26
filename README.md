# Persistent Homology

This repository contains several folders with R code, JAVA code and shell scripts. 


NetworkPersistence
------------------
This folder contains a JAVA project (for Eclipse) written to interact with the JavaPlex library, to compute the persistent homology of weighted networks. The build folder contains network_persistence.jar, a library that be used in combination with javaplex-4.2.1.jar in your own projects.   

We implemented two different methods to compute the persistent homology of undirected weighted networks. 

* One method uses the adjacency matrix of a network and the ExplicitMetricSpace class from JavaPlex. This method is suitable for networks with a relatively small number of vertices. However, for large sparse networks, the memory allocated for the adjacency matrix may exceed the maximum memory allocated to JAVA and hence result in an OutOfMemory exception. -- See WeightedGraphFiltrationTest in the test folder for an example.

* The second method uses code built on the FlagComplexStream. See WeightedFlagComplexStreamTest in the test folder for an example.  


NetworkPersistenceExperiments
-----------------------------
This project uses JavaPlex and the network_persistence.jar to compute the persistent homology of networks as discussed in the paper [Persistent Homology of Collaboration Networks](http://www.hindawi.com/journals/mpe/2013/815035/). Please cite if using the code. 

Also please [cite JavaPlex](https://github.com/appliedtopology/javaplex/wiki/Citation-Information), as all computations are run using the JavaPlex code. 

The networks found in the folder 'networks' are edgelists corresponding to the largest connected components of collaboration networks downloaded from [Mark Newman's website](http://www-personal.umich.edu/~mejn/netdata/). If using data, please cite as indicated below:
* CondensedMatter.csv: M. E. J. Newman, Proc. Natl. Acad. Sci. USA 98, 404-409 (2001). (we used the 2005 version of the network) 
* AstroPhysics.csv: M. E. J. Newman, Proc. Natl. Acad. Sci. USA 98, 404-409 (2001).
* HighEnergyPhysics.csv: M. E. J. Newman, Proc. Natl. Acad. Sci. USA 98, 404-409 (2001).
* NetworkScience.csv: M. E. J. Newman, Phys. Rev. E 74, 036104 (2006). 


NetworkPersistenceR 
-------------------
This folder contains a small Rmd script for plotting some of the output of the persistent homology computations in NetworkPersistenceExperiments. 