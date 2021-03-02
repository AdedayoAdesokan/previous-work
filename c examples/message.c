#include <unistd.h>
#include <ctype.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main ()
{
    pid_t pid;
    int fd1[2];
    int fd2[2];
    char message[1024];
    char output[1024];
    
    if (pipe(fd1) == -1) {
   	 printf("Pipe failure\n");
   	 return -1;    
    }
    if (pipe(fd2) == -1) {
   	 printf("Pipe failure\n");
   	 return -1;    
    }
    
    pid = fork();
    
    if (pid == 0) {
   	 close(fd1[0]);
   	 printf("input: ");
   	 fgets(message, sizeof(message), stdin);
   	 write(fd1[1], message, strlen(message) + 1);
   	 
   	 close(fd2[1]);
   	 read(fd2[0], output, sizeof(output));
   	 printf("%s", output);
   	 
   	 exit(0);
    }
    else {
   	 close(fd1[1]);
   	 read(fd1[0], output, sizeof(output));
   	 for (int i = 0; i < sizeof(output); i++) {
   		 if (isupper(output[i])) {
   			 output[i] = tolower(output[i]);
   		 }
   		 else {
   			 output[i] = toupper(output[i]);
   		 }
   	 }
   	 
   	 close(fd2[0]);
   	 write(fd2[1], output, strlen(output) + 1);
   	 wait(NULL);
    }
    return 0;
}
