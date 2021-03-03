#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>

int sum = 0;
int numberCount;
int max;
int min;
float average;
int array[1024];

void *getAverage() {
    for (int i = 0; i < numberCount; i++) {  
   	 sum = sum + array[i];  //add the number to the sum
    }
    average = (float)sum / (float)numberCount;  //divide the sum by total nums
    pthread_exit(0);
}

void *getMax() {
    for (int i = 0; i < numberCount; i++) {
   	 if (i == 0) {
   		 max = array[i];  //set max to first number
   	 }
   	 else {
   		 if (max < array[i]) {  
   			 max = array[i];  //update max
   		 }
   	 }
    }
    pthread_exit(0);
}

void *getMin() {
    for (int i = 0; i < numberCount; i++) {
   	 if (i == 0) {
   		 min = array[i];  //set min to first number
   	 }
   	 else {
   		 if (min > array[i]) {
   			 min = array[i];  //update min
   		 }
   	 }
    }
    pthread_exit(0);
}

int main(int argc, char **argv) {
    char input[1024];  //initialize an array to hold the input
    numberCount = 0;   //set the count to 0;
    
    printf("Please input a series of numbers: ");
    fgets(input, 1024, stdin);  //get the input
    
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
    
    array[numberCount];  //create an array to hold the numbers
    for (int i = 0; i < numberCount; i++) {
   	 array[i] = tempArray[i];  //copy the numbers from temp
    }
    
    pthread_attr_t attr;
    pthread_attr_init(&attr);
    
    //initialize the threads
    pthread_t thread1;  
    pthread_t thread2;
    pthread_t thread3;
    
    //create the threads
    pthread_create(&thread1, &attr, getAverage, array);  
    pthread_create(&thread2, &attr, getMax, array);
    pthread_create(&thread3, &attr, getMin, array);
    
    //join the threads
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);
    pthread_join(thread3, NULL);
    
    //print the values
    printf("The average value is %f\n", average);
    printf("The minimum value is %d\n", min);
    printf("The maximum value is %d\n", max);
    
    return 0;    
}
