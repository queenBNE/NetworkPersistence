# Persistent Homology

This repository currently contains one JAVA project: NetworkPersistence. This project contains code that allows the computation of the persistent homology of weighted networks. The persistence computation is done by using the JavaPlex library. 

We implemented two different methods to compute the persistent homology of undirected weighted networks. 

* One method uses the adjacency matrix of a network and the ExplicitMetricSpace class from JavaPlex. This method is suitable for networks with a relatively small number of vertices. However, for large sparse networks, the memory allocated for the adjacency matrix may exceed the maximum memory allocated to JAVA and hence result in an OutOfMemory exception. 

* The second method uses the CliqueComplexWeightedStream. 

Tests for both methods can be found in the test folder. 

The experiments folder contains code corresponding to the paper [Persistent Homology of Collaboration Networks](http://www.hindawi.com/journals/mpe/2013/815035/). Please cite if using the code. 

Also please [cite JavaPlex](https://github.com/appliedtopology/javaplex/wiki/Citation-Information), as all computations are run using the JavaPlex code. 

The networks found in the folder 'networks' are edgelists corresponding to the largest connected components of collaboration networks downloaded from [Mark Newman's website](http://www-personal.umich.edu/~mejn/netdata/). If using data, please cite as indicated below:
* CondensedMatter.csv: M. E. J. Newman, Proc. Natl. Acad. Sci. USA 98, 404-409 (2001). (we used the 2005 version of the network) 
* AstroPhysics.csv: M. E. J. Newman, Proc. Natl. Acad. Sci. USA 98, 404-409 (2001).
* HighEnergyPhysics.csv: M. E. J. Newman, Proc. Natl. Acad. Sci. USA 98, 404-409 (2001).
* NetworkScience.csv: M. E. J. Newman, Phys. Rev. E 74, 036104 (2006). 

