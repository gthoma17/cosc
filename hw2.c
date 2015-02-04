#include <stdio.h>
#include <string.h>
#include <float.h>
#include <math.h>
#include <stdlib.h>


/*
TODO:
#1 	- pi 				DONE
#2	- square root 		DONE
#3	- primes 			DONE
#4	- student score		DONE
#5	- tax 				DONE
#6	- quadratic 		DONE
#7	- factorial 		DONE
#8	- file info 		DONE
#9	- sort file 		DONE
#10	- student file 		DONE
#11	- menu 				DONE


*/

main(){
	int selection;
    do{
    	printMenu();
    	scanf("%d", &selection);
    	parseSelection(selection);
    } while(selection != 11);   
}

printMenu(){
	char *menuText = 
		"Please select from the following options:\n"
		"1  - computing pi\n"
		"2  - computing square root\n"
		"3  - displaying primes\n"
		"4  - processing grades\n"
		"5  - computing tax\n"
		"6  - solving quadratic\n"
		"7  - computing factorial\n"
		"8  - counting file\n"
		"9  - sorting file\n"
		"10 - student file\n"
		"11 - quit\n";
	printf("%s", menuText);
}
parseSelection(int selection){
	if(1 == selection){piHelper();}
	else if (2 == selection){sqrtHelper();}
	else if (3 == selection){primesHelper();}
	else if (4 == selection){gradesHelper();}
	else if (5 == selection){taxHelper();}
	else if (6 == selection){quadraticHelper();}
	else if (7 == selection){factorialHelper();}
	else if (8 == selection){countFileHelper();}
	else if (9 == selection){sortFileHelper();}
	else if (10 == selection){stuentFileHelper();}
	else if (11 == selection){/*Do nothing*/}
	else {printf("Invalid option. Try again.");}
}

double compute_pi(int n){
	double approx_pi = 4;
	double correction = 1;
	int i;
	for (i = 1; i < n; ++i){
		if (0 == i%2){
			correction = correction + (1.0/((i*2)+1));
		}
		else{
			correction = correction - (1.0/((i*2)+1));
		}
	}
	approx_pi = approx_pi * correction;
	return approx_pi;
}

double compute_sqrt(double x){
	int i;
	double guess = 1;
	for (i = 0; i < 10; ++i){
		guess = 0.5*(guess+(x/guess));
	}
	return guess;
}
int is_prime(int n){
	int i;
	for (i = 2; i < n; ++i){
		if (n%i==0){
			return 0;
		}
	}
	return 1;
}
void display_primes(int n){
	int i;
	printf("The prime numbers under %d are: ", n);
	for (i = 1; i < n; ++i){
		if (is_prime(i)){
			printf("%d ",i);
		}
	}
	printf("\n");
}
void process_scores(){
	char line[81];
	char thisName[81];
	double thisScore;
	char maxName[81];
	double maxScore = -DBL_MAX;
	char minName[81];
	double minScore = DBL_MAX;
	double runningTotal;
	int numScores = 0;

	gets(line);
	gets(line);
	/*^^^^^^^^^kluge*/
	sscanf(line, "%s %lf", &thisName, &thisScore);
	while(strcmp(thisName,"q") != 0 && strcmp(thisName,"Q") != 0){
		if (thisScore > maxScore){
			maxScore = thisScore;
			strcpy(maxName,thisName);
		}
		if (thisScore < minScore){
			minScore = thisScore;
			strcpy(minName,thisName);
		}
		runningTotal += thisScore;
		++numScores;
		gets(line);
		sscanf(line, "%s %lf", &thisName, &thisScore);
	}
	printf("Top Score: %s %f\n", maxName, maxScore);
	printf("Low Score: %s %f\n", minName, minScore);
	printf("Avg Score: %f\n",(runningTotal/numScores));
}
double compute_tax(int income, char *status, char state){
	double taxRate;

	if(income < 0){return -1.0;}

	if (strcmp(status,"married")==0 || strcmp(status,"MARRIED")==0){
		if (income < 50000){taxRate = 10;}
		else{taxRate = 15;}
	}
	else if (strcmp(status,"single")==0 || strcmp(status,"SINGLE")==0){
		if (income < 30000){taxRate = 20;}
		else{taxRate = 25;}
	}
	else{
		printf("invalid status: %s\n", status);
		return -1.0;
	}

	if ('O' == state || 'o' == state){taxRate=taxRate-3;}
	else if ('I' == state || 'i' == state){;}
	else{
		printf("invalid state: %c\n", &state);
		return -1.0;}

	printf("Your taxRate is: %f\n", taxRate);
	return income*taxRate;
}
int quadratic(double a, double b, double c, double *solution1, double *solution2){
	if ((pow(b,2)-(4*a*c))<0){
		return 0;
	}
	else{
		*solution1 = (-b + (sqrt((pow(b,2)-(4*a*c))))) / (2*a);
		*solution2 = (-b - (sqrt((pow(b,2)-(4*a*c))))) / (2*a);
	}
	return 1;
}
int factorial(int n){
	if(1==n){
		return 1;
	}
	else{
		return n*factorial(n-1);
	}
}
void file_count(char *file, int *characters, int *lines){
	FILE *fp = fopen(file,"r");
	char thisChar;
	if(fp){
		*characters = 0;
		*lines = 1;
		while((thisChar = getc(fp)) != EOF){
			*characters = *characters + 1;
			if(thisChar == '\n'){
				*lines = *lines + 1;
			}
		}
		fclose(fp);
	}
	else{
		*characters = -1;
		*lines = -1;
		printf("Invalid file name\n");
	}
}
void file_sort(char *infile, char *outfile){
	FILE *fin = fopen(infile,"r");
	FILE *fout = fopen(outfile,"w");
	char buf[256];

	if(fin){
		fgets(buf, sizeof(buf), fin);
		int length = buf[0] - '0';

		int student_numbers[length];
		char student_grades[length][3]; //grade should consume at most 2 chars
		char student_gpas[length][20];  
		int i,j,number_temp;
		char grade_temp[3];
		char gpa_temp[20];

		for (i=0; i < length; ++i){
			fgets(buf, sizeof(buf),fin);
			//now seperate out the different fields
			//student number
			char *token=strtok(buf, " \t");
			student_numbers[i] = atoi(token);
			//grade
			token = strtok(NULL, " \t");
			strcpy(student_grades[i],token);
			//gpa
			token = strtok(NULL, " \t\n");
			strcpy(student_gpas[i],token);
		}
		//bubble sort becasue why not
		for (i=1;i<length;++i){
			for(j=0;j<length-i;++j){
				if(student_numbers[j] > student_numbers[j+1]){
					//number
					number_temp = student_numbers[j];
					student_numbers[j] = student_numbers[j+1];
					student_numbers[j+1] = number_temp;
					//grade
					strcpy(grade_temp,student_grades[j]);
					strcpy(student_grades[j],student_grades[j+1]);
					strcpy(student_grades[j+1],grade_temp);
					//gpa
					strcpy(gpa_temp,student_gpas[j]);
					strcpy(student_gpas[j],student_gpas[j+1]);
					strcpy(student_gpas[j+1],gpa_temp);
				}
			}
		}
		//write to output file
		for (i = 0; i < length; ++i){
			printf("%d\t", student_numbers[i]);
			printf("%s\t", student_grades[i]);
			printf("%s\n", student_gpas[i]);
		}
		if(fout){
			for (i = 0; i < length; ++i){
				fprintf(fout, "%d\t", student_numbers[i]);
				fprintf(fout, "%s\t", student_grades[i]);
				fprintf(fout, "%s\n", student_gpas[i]);
			}
			fclose(fout);
		}
		else{
			printf("Invalid output file\n");
		}
		fclose(fin);
	}
	else{
		printf("Invalid input file name\n");
	}
	//local variables are automatically freed when function ends
}
typedef struct{
	char name[80];
	int age;
	double gpa;
}student;
void file_student(char *infile){
	FILE *fin = fopen(infile,"r");
	char buf[256];

	if(fin){
		fgets(buf, sizeof(buf), fin);
		int length = buf[0] - '0';
		int i,j,k;
		student *students;
		students = (student*) malloc(sizeof(student)*length);

		for (i=0; i < length; ++i){
			fgets(buf, sizeof(buf),fin);
			//now seperate out the different fields
			//name
			char *token=strtok(buf, " \t");
			strcpy(students[i].name,token);
			//age
			token = strtok(NULL, " \t");
			students[i].age = atoi(token);
			//gpa
			token = strtok(NULL, " \t\n");
			students[i].gpa = atof(token);
		}
		//print avg gpa
		double total_gpa = 0;
		for (i=0; i < length; ++i){
			total_gpa = total_gpa + students[i].gpa;
		}
		printf("Average gpa: %f\n\n", total_gpa/length);

		//print students with gpa > 2
		printf("The following students have a GPA >= 2.0\n");
		for (i=0; i < length; ++i){
			if(2.0<=students[i].gpa){
				printf("%s\n",students[i].name);
			}
		}
		printf("Congratulations!\n\n");

		printf("All student info: \n");
		//students & info in alphabetic order
		char smallest_name[80];
		int smallest_index;
		for(j=0; j<length; ++j){
			//find the first name we havn't printed yet
			for(k=0;k<length;++k){
				if(students[k].name[0] != '\0'){
					strcpy(smallest_name, students[k].name);
					smallest_index = k;
					break;
				}
			}
			//compare it to all other names we havn't printed
			for(i=0; i<length; ++i){
				if(students[i].name[0] != '\0'){
					if ((strcmp(smallest_name, students[i].name)) > 0){
						strcpy(smallest_name, students[i].name);
						smallest_index = i;
					}
				}
			}
			printf("%s\t", students[smallest_index].name);
			printf("%d\t", students[smallest_index].age);
			printf("%f\n", students[smallest_index].gpa);
			students[smallest_index].name[0] = '\0';
		}



		free(students);
	}
	else{
		printf("Invalid input file name\n");
	}
	//local variables are automatically freed when function ends
}

piHelper(){
	int n;
	printf("How many terms worth of precision do you want?\n");
	scanf("%d",&n);
	printf("Pi is approximately: %f\n", compute_pi(n));
}
sqrtHelper(){
	double x;
	printf("What number do you want the square root of?\n");
	scanf("%lf",&x);
	printf("The square root of %f is approximately: %f\n", x, compute_sqrt(x));
}
primesHelper(){
	int n;
	printf("What number do you want to know the primes under?\n");
	scanf("%d",&n);
	display_primes(n);
}
gradesHelper(){
	printf("Please enter name and score seperated by whitespace \n");
	printf("Enter 'q' (no quotes) when you are done\n");
	process_scores();
}
taxHelper(){
	int income;
	char status[8];
	char state;

	printf("What is your income?\n");
	scanf("%d",&income);

	printf("What is your marital status?\n");
	scanf("%s",status);

	printf("What is your residency status? (O/I)\n");
	scanf(" %c",&state);

	compute_tax(income, status, state);
}
quadraticHelper(){
	double a, b, c, solution1, solution2;

	printf("What is A?\n");
	scanf("%lf",&a);

	printf("What is B?\n");
	scanf("%lf",&b);

	printf("What is C?\n");
	scanf("%lf",&c);

	if(quadratic(a,b,c,&solution1,&solution2)){
		printf("Solutions: %f %f\n",solution1,solution2);
	}
	else{
		printf("Given inputs will not produce a real output\n");
	}
}
factorialHelper(){
	int n;
	printf("What is N?\n");
	scanf("%d",&n);

	printf("The answer is: %d\n", factorial(n));
}
countFileHelper(){
	int characters, lines;
	char filename[81];

	printf("Where is your file?\n");
	scanf("%s",filename);

	file_count(filename,&characters,&lines);

	printf("total chars: %d\n", characters);
	printf("total lines: %d\n", lines);
}
sortFileHelper(){
	char infile[81], outfile[81];

	printf("Where is your in file?\n");
	scanf("%s",infile);
	printf("Where is your outfile file (does not need to exist already)?\n");
	scanf("%s",outfile);

	file_sort(infile, outfile);
}
stuentFileHelper(){
	char infile[81];

	printf("Where is your in file?\n");
	scanf("%s",infile);

	file_student(infile);
}

