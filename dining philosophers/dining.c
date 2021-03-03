#include <pthread.h>
#include <unistd.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>


#define PHIL_COUNT 5 //the total number of philosophers
#define MAX_TIME 3  //the max amount of time a philosopher will be thinking or eating

//indicates the state that a philosopher is in
enum {THINKING, HUNGRY, EATING} state[PHIL_COUNT];

int thread_id[PHIL_COUNT];  //the number associated with each philosopher
pthread_t tids[PHIL_COUNT];  //the thread ID's of each philosopher
pthread_cond_t  condition_variables[PHIL_COUNT];  //creates a condition variable for each philosopher
pthread_mutex_t mutex_lock;  //creates the mutex lock associated with the condition variables


/* gets the philosopher to the right of a given philosopher */
int right_philosopher(int philosopher) {
	if (philosopher == PHIL_COUNT - 1) {  //the philosopher to the right of 4 is 0
		return 0;
	}
	else {
		return philosopher + 1;  //the philosopher to the right
	}
}

/* gets the philosopher to the left of a given philosopher */
int left_philosopher(int philosopher) {
	if (philosopher == 0) {
		return PHIL_COUNT - 1;  //the philosopher to the left of 0 is 4
	}
	else {
		return philosopher - 1;  //the philosopher to the left
	}
}




/* checks to see if a philosopher can enter the eating state */
void check(int philosopher) {
	//a philosopher can only eat when they are hungry and the philosophers to the left and right 
       //are not eating 
	if ((state[left_philosopher(philosopher)] != EATING) &&
(state[right_philosopher(philosopher)] != EATING) && (state[philosopher] == HUNGRY)) {
		//set the philosopher's state to eating
	     	state[philosopher] = EATING;
	     	//signal the philosopher in case it is blocked  
	     	pthread_cond_signal(&condition_variables[philosopher]);
	     }
}

/* when a philosopher wishes to eat, this function is called */
void pickup_forks(int philosopher_number) {
	pthread_mutex_lock(&mutex_lock);  //lock the mutex lock
	
	state[philosopher_number] = HUNGRY;  //set the philosopher's state to hungry
	check(philosopher_number);  //check if the philosopher can eat
	//while the philosopher is not eating
	while (state[philosopher_number] != EATING) {
		//the philosopher has to wait and release the lock
		pthread_cond_wait(&condition_variables[philosopher_number], &mutex_lock);
	}
	pthread_mutex_unlock(&mutex_lock);  //unlock the mutex lock
}

/* when a philosopher finishes eating, this function is called */
void return_forks(int philosopher_number) {
	pthread_mutex_lock(&mutex_lock);  //lock the mutex lock
	
	//set the philosopher's state to thinking
	state[philosopher_number] = THINKING;
	//check to see if the philosopher to the left can eat
	check(left_philosopher(philosopher_number));  
	//check to see if the philosopher to the right can eat
	check(right_philosopher(philosopher_number));
	
	pthread_mutex_unlock(&mutex_lock);  //unlock the mutex lock
}

/* specifies the amount of time the philosopher will eat */
void eating(int eat_time) {
	sleep(eat_time);
}

/* specifies the amount of time the philosopher will think */
void thinking(int think_time) {
	sleep(think_time);
}

/* the code for each philosopher */
void *philosopher(void *param) {
	int *lnumber = (int *)param;
	int philosopher = *lnumber;
	int duration;
	int iterations = 0;
	
	srandom((unsigned)time(NULL));
	
	while (iterations < 2) {
		//set duration to a random time between 
		duration = (int) ((random() % MAX_TIME) + 1);
		thinking(duration);  //think for a random time
		
		pickup_forks(philosopher);  //pick up the forks and eat
		printf("Philosopher %d is eating\n", philosopher);
		
		//set duration to another random time between
		duration = (int) ((random() % MAX_TIME) + 1);
		eating(duration);  //eat for a random time
		
		printf("Philosopher %d is thinking\n", philosopher);
		return_forks(philosopher);
		
		++iterations;
	}
}

/* initializes the program */
void init() {
	for (int i = 0; i < PHIL_COUNT; i++) {
		//set all the philosophers to thinking
		state[i] = THINKING;  
		thread_id[i] = i;  //each philosopher has an id number 0-4
		//create and initialize the condition variables to zero
		pthread_cond_init(&condition_variables[i], NULL);
	}
	//create and initialize the mutex lock to zero 
	pthread_mutex_init(&mutex_lock, NULL);  
}

/* creates all of the philosophers */
void create_philosophers() {
	//create 5 threads
	for (int i = 0; i < PHIL_COUNT; i++) {
		pthread_create(&tids[i], 0, philosopher, (void *)&thread_id[i]);
	}
}

int main(void) {
	init();  //initialize the program
	create_philosophers();  //create the philosophers
	for (int i = 0; i < PHIL_COUNT; i++) {
		//wait for each thread to finish
		pthread_join(tids[i], NULL);  
	}
	return 0;
}


