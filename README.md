# Data-Structures
Last major assignment for csci241: Data structures &amp; algorithms

The assignment's objective is to implement Dijkstra’s Single-Source Shortest Paths algorithm using a Graph 

1. Implement the compute() method in the ShortestPaths class according to its specification.
2. Implement the shortestPathLength method. Notice that this method’s precondition states that compute() has been
   called with the desired origin node, so the paths field should already be filled in with the final shortest paths data.
3. Implement the shortestPath() method. Once again, compute() has already been called with the desired origin.

   a.) In the main method, create and use an instance of ShortestPaths to compute shortest-paths data from the origin
         node specified in the command line arguments.
   b.) If a destination node was not specified on the command line, print a table of reachable nodes and their shortest
       path lengths.
   c.) If a destination node was specified on the command line, print the nodes in the shortest path from the origin to
       the destination, followed by the length of the path.
