# Algorithm-Design

Write a java program which ﬁnds two feasible solutions to instances of ChannelAllocation. The input ﬁle will be called input.txt and will consist of two integers per line with each line representing one open interval. The ﬁrst feasible solution should be the true optimal solution which can be found using the LowestUnusedChannel algorithm. The second feasible solution should be the result of repeatedly using the EarliestFinishTime algorithm (that solves the IntervalScheduling problem).

Sample input.txt
1 5 6 11 2 7 9 10

Sample Interaction:
Project 1: Channel Allocation Reading the file: input.txt
LUC uses 2 channels 
1: (1,5) (6,11) 
2: (2,7) (9,10)
IterEFT uses 3 channels 
1: (1,5) (9,10) 
2: (2,7) 3: (6,11
