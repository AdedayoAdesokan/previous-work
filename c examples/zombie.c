#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
int main()
{
int pid;
int time = 60;
	
	pid = fork();
	if (pid > 0) {
		printf("parent process %d has created child process %d\n", getpid(), pid); 
		sleep(time);}
	else {
		exit(0); }
	return 0;
}
