# SQL parser

The data collection in this task is a structure representing some data table that has column names and each row in the table is an element of the collection. It is necessary to implement a method that receives a command in the form of a string at the input (the format requirements will be below). The command must perform the four main operations of inserting, modifying, searching and deleting items from the data collection. Also, the sampling conditions from the collection should beined when modifying, deleting and searching. The output lists the items in the collection that were found, modified, added, or deleted.



1) Clamps in Condition are not implemented;
2) The validation of the request has been implemented;
3) 3)CopyOnWriteArrayList and ConcurrentHashMap are used to create a Thread-safe collection;

Unit Tests Written

All methods return copies of Map so that it is not possible to change the collection from outside;
