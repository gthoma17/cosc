#include <stdio.h>
#include <string.h>
#include <float.h>
#include <math.h>


/*
TODO:
#1 	- pi 				DONE
#2	- square root 		DONE
#3	- primes 			DONE
#4	- student score		DONE
#5	- tax 				DONE
#6	- quadratic 		DONE
#7	- factorial 		
#8	- file info 		
#9	- sort file 		
#10	- student file 		
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
		//*solution1 = 5.0;
		//*solution2 = 6.0;

	}
	return 1;
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
factorialHelper(){}
countFileHelper(){}
sortFileHelper(){}
stuentFileHelper(){}

