#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <unistd.h>

int main(int argc, char *argv[])
{    
    struct timeval *start;
    struct timeval end;
    pid_t pid;
    int fd[2];
    
    if (argc == 1) {
   	 return 0;
    }
    
    if (pipe(fd) == -1) {
   	 printf("Pipe failure\n");
   	 return -1;    
    }
    
    pid = fork();
    
    if (pid == 0) {
   	 close(fd[0]);
   	 start = (struct timeval *)malloc(sizeof(struct timeval));
   	 if (gettimeofday(start, NULL) != 0) {
   		 return 0;
   	 }
   	 write(fd[1], start, sizeof(struct timeval));
   	 execvp(argv[1], &argv[1]);
   	 
   	 fprintf(stderr, "Error");
    }
    else {
   	 wait(NULL);
   	 if(gettimeofday(&end, NULL) != 0) {
   		 return 0;
   	 }
   	 close(fd[1]);
   	 int start_sec = 0;
   	 int start_usec = 0;
   	 start = (struct timeval *)malloc(sizeof(struct timeval));
   	 read(fd[0], start, sizeof(struct timeval));
   	 start_sec = start ->tv_sec;
   	 start_usec = start ->tv_usec;
   	 int secs = (end.tv_sec - start_sec);
   	 int usecs = (end.tv_usec - start_usec);
   	 
   	 printf("Elapsed time %d.%d\n", secs, usecs);
    }
    return 0;
}
