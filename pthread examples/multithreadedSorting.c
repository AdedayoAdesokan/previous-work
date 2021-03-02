#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


#define threadCount 3
int numberCount;
int list[1024];
int sortedList[1024];

void *sorter(void *parameterss);
void *merger(void *parameterss);

typedef struct {
    int startIndex;
    int endIndex;
} parameters;

void *sorter(void *parameterss) {
    parameters* params = (parameters *)parameterss;
    
    int start = params -> startIndex;
    int stop = params -> endIndex+1;
    
    printf("The recieved array is: ");
    for (int i = start; i < stop; i++) {  //print the portion of the array being sorted
   	 if (i == start) {
   		 printf("%d", list[i]);  
   	 }
   	 else if (i == stop -1) {
   		 printf(", %d\n", list[i]);
   	 }
   	 else {
   		 printf(", %d", list[i]);
   	 }
    }
    
    for (int i = start; i < stop; i++) {
   	 for (int j = start; j < stop - i - 1; j++) {
   		 if (list[j] > list[j+1]) {  //if current element is greater than next
   			 int temp = list[j];  //swap it with the next element
   			 list[j] = list[j+1];
   			 list[j+1] = temp;
   		 }
   		 if (list[j] < list[j-1]) {  //if current element is less than previous
   			 int k = j;
   			 while (list[k] < list[k-1]) {  //swap it with the previous
   				 int temp = list[k];
   				 list[k] = list[k-1];
   				 list[k-1] = temp;
   				 k--;
   			 }
   		 }
   	 }
    }
    
    printf("The sorted array is: ");
    for (int i = start; i < stop; i++) {  //print the sorted array 
   	 if (i == start) {
   		 printf("%d", list[i]);
   	 }
   	 else if (i == stop -1) {
   		 printf(", %d\n", list[i]);
   	 }
   	 else {
   		 printf(", %d", list[i]);
   	 }
    }
    
    for (int i = start; i < stop; i++) {  //copy the sorted elements into sortedList
   	 sortedList[i] = list[i];
    }
    printf("\n");
    pthread_exit(0);
}

void *merger(void *parameterss) {
    parameters* params = (parameters *)parameterss;
    
    int start = params -> startIndex;
    int stop = params -> endIndex + 1;
    
    for (int i = start; i < stop; i++) {
   	 for (int j = start; j < stop - i; j++) {
   		 if (sortedList[j] > sortedList[j+1]) {  //if the current element > the next
   			 int temp = sortedList[j];        //swap them
   			 sortedList[j] = sortedList[j+1];
   			 sortedList[j+1] = temp;
   		 }
   	 }
    }
    
    printf("The final sorted array is: ");  //print the final sorted array
    for (int i = 0; i < numberCount; i++) {
   	 printf("%d ", sortedList[i]);
    }
    pthread_exit(0);
}

int main () {
    char input[1024];  //initialize an array to hold the input
    numberCount = 0;   //set the count to 0;
    
    printf("Please input a series of numbers: ");
    fgets(input, 1024, stdin);  //get the input
    printf("\n");
    
    char delim[] = " ";
    char *ptr = strtok(input, delim);  //split input by white spaces
    
    int tempArray[1024];  //create a temp array to hold the numbers
    while (ptr != NULL) {
   	 const char *value = ptr;
   	 int x;
   	 sscanf(value, "%d", &x);  //convert the string to a number
   	 tempArray[numberCount] = x;  //add the number to the array
   	 numberCount++;  //increment the count
   	 
   	 ptr = strtok(NULL, delim);  //get the next token
    }
    list[numberCount];  //create an array to hold the numbers
    sortedList[numberCount];  //create an array to hold sorted numbers
    
    for (int i = 0; i < numberCount; i++) {
   	 list[i] = tempArray[i];  //copy the numbers from temp
    }
    
    pthread_t workers[threadCount];
    
    parameters *data = (parameters *)malloc(sizeof(parameters));
    data -> startIndex = 0;
    data -> endIndex = (numberCount / 2) - 1;
    pthread_create(&workers[0], 0, sorter, data);  //create the first sorting thread
        
    data = (parameters *)malloc(sizeof(parameters));
    data -> startIndex = (numberCount / 2);
    data -> endIndex = (numberCount - 1);
    pthread_create(&workers[1], 0, sorter, data);  //create the second sorting thread
        
    for (int i = 0; i < threadCount - 1; i++) {
        pthread_join(workers[i], NULL);  //wait for each of the sorting threads to finish
    }
    
    data = (parameters *)malloc(sizeof(parameters));
    data -> startIndex = 0;
    data -> endIndex = (numberCount / 2);
    pthread_create(&workers[2], 0, merger, data);  //create the merge thread
    
    pthread_join(workers[2], NULL);  //wait for the merge thread to finish
    
    return 0;
}
