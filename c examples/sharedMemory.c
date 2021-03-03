#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <unistd.h>
#include <sys/mman.h>

int main(int argc, char *argv[1])
{
    struct timeval *ptr = (struct timeval *)malloc(sizeof(struct timeval));
    struct timeval begin, finish, elapsed_time;
    const char *name = "Shared Memory";
    int fd;
    pid_t pid;
    
    fd = shm_open(name, O_CREAT | O_RDWR, 0666);
    ftruncate(fd, sizeof(struct timeval));
    ptr = (struct timeval *)mmap(0, sizeof(struct timeval), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    
    close(fd);
    pid = fork();
    
    if (pid == 0) {
   	 gettimeofday(&begin, 0);
   	 memcpy(ptr, &begin, sizeof(struct timeval));
   	 execvp(argv[1], &argv[1]);
   	 fprintf(stderr, "Error");
    }
    else {
   	 wait(NULL);
   	 gettimeofday(&finish, 0);

   	 memcpy(&begin, ptr, sizeof(struct timeval));
   	 
   	 int begin_sec = 0;
   	 int begin_usec = 0;
   	 begin_sec = begin.tv_sec;
   	 begin_usec = begin.tv_usec;
   	 int secs = (finish.tv_sec - begin_sec);
   	 int usecs = (finish.tv_usec - begin_usec);
   	 printf("Elapsed time %d.%d\n", secs, usecs);
    }
    return 0;
}
