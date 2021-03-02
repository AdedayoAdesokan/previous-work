#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
    pid_t pid;
    int fd[2];
    char contents[1024];
    char copy[1024];
    
    char *sourcefile = argv[1];
    char *destinationfile = argv[2];
    
    if (argc == 1) {
   	 return 0;
    }
    if (pipe(fd) == -1) {
   	 printf("Pipe failure\n");
   	 return -1;    
    }
    
    pid = fork();
    
    if (pid == 0) {
   	 int destsize;
   	 ssize_t destbytes;
   	 
   	 close(fd[1]);
   	 destbytes = read(fd[0], copy, sizeof(copy));
   	 
   	 destsize = open(destinationfile, O_CREAT | O_WRONLY);
   	 write(destsize, copy, destbytes);
   	 
    }
    else {
   	 int sourcesize;
   	 ssize_t sizebytes;
   	 
   	 close(fd[0]);
   	 sourcesize = open(sourcefile, O_RDONLY);
   	 sizebytes = read(sourcesize, contents, sizeof(contents));
   	 
   	 write(fd[1], contents, sizebytes);
   	 wait(NULL);
    }
    return 0;
}
