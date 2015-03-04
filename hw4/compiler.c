#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#include <ctype.h>

/*constants for true and false*/ 
#define FALSE 0
#define TRUE 1

/*enumerated types for token types*/ 
typedef enum
{
	MAIN, LBRACKET, RBRACKET, READ, COMMA, 
	WRITE, IF, ELSE, WHILE, LPAREN, RPAREN, 
	ASSIGNOP, ADDOP, MULOP, SUBOP, DIVOP, 
	SEMICOLON, ID, INTLITERAL, SCANEOF, GT, 
	GTE, LT, LTE, EQUAL, NOTEQ
} token;

/*functions declarations related to scanner*/ 
token scanner();
void clear_buffer();
void buffer_char(char c);
token check_reserved();
void lexical_error();

/*functions declarations related to parser*/
void parser();
void program();
void statement_list(); 
void statement();
void boolean();
void id_list();
void expression();
void term();
void factor();
void match(token tok); 
void syntax_error();
void arithop();
void operand();
void relop();
const char* intToToken();

/*global variables*/
FILE *fin;         /*source file*/
token next_token;  /*next token in source file*/
char token_buffer[100]; /*token buffer*/
int token_ptr;     /*buffer pointer*/
int line_num = 1;  /*line number in source file*/
int error = FALSE; /*flag to indicate error*/
/************************************************************************/

/*returns next token from source file*/ 
token scanner()
{
   char c;               /*current character in source file*/ 

   clear_buffer();      /*empty token buffer*/
   while(FALSE == error)           /*loop reads and returns next token*/ 
   {
      c = getc(fin);     /*read a character from source file*/
      if(c == EOF)       /*end of file*/
         return SCANEOF;
      else if (isspace(c)) /*skip white spaces and count line number*/
      {   
      	if(c == '\n')
             line_num = line_num + 1;
      }
      else if (isalpha(c)) /*identifier or reversed word*/
      {   
          buffer_char (c);          /*buffer the first character*/
          c = getc (fin);
          while (isalnum(c) || isdigit(c)) /*read and buffer subsequent characters*/
          {
             buffer_char(c); 
             c = getc(fin);
          }
          ungetc(c, fin);           /*put back the last character read*/
          return check_reserved(); /*return identifier or reserved word*/
      }
      /*integer literal*/ 
      else if (isdigit(c))
      {
          buffer_char(c);          /*buffer the first character*/
          c = getc(fin);
          while(isdigit(c))        /*read and buffer subsequent characters*/
          {
             buffer_char(c); 
             c = getc(fin);
          }
          ungetc(c, fin);          /*put back the last character read*/ 
          return INTLITERAL;       /*return integer literal*/
      }
      else if (c == '>'){   /*GT or GTE*/ 
          c = getc(fin);
          if(c == '='){
                return GTE;
          }
          else{
                ungetc(c, fin); 
                return GT;
            }
      }      
      else if (c == '<'){   /*LT or LTE*/ 
          c = getc(fin);
          if(c == '='){
                return LTE;
          }
          else{
                ungetc(c, fin); 
                return LT;
            }
      }
      else if (c == '='){   /*possible equal*/ 
          c = getc(fin);
          if(c == '='){
                return EQUAL;
          }
          else{
                ungetc(c, fin); 
                lexical_error();
            }
      }
      else if (c == '!'){   /*not equal*/ 
          c = getc(fin);
          if(c == '='){
                return NOTEQ;
          }
          else{
                ungetc(c, fin); 
                lexical_error();
            }
      }
      else if (c == '(')   /*left parentheses*/ 
          return LPAREN;
      
      else if (c == ')')   /*right parentheses*/ 
          return RPAREN;
      
      else if (c == ',')   /*comma*/
          return COMMA;
      
      else if (c == ';')   /*semicolon*/
          return SEMICOLON;
      
      else if (c == '+')   /*plus operator*/
          return ADDOP;
      else if (c == '-') /*minus operator*/
          return SUBOP;
      else if (c == '*') /*multiply operator*/
          return MULOP;
      else if (c == '{') /*minus operator*/
          return LBRACKET;
      else if (c == '}') /*multiply operator*/
          return RBRACKET;
      else if (c == '/')   /*comment or divide operator*/
      {   
      	  c = getc(fin);
          if(c == '/')     /*comment begins*/
          {
             do            /*read and discard until end of line*/
                c = getc(fin);
             while (c != '\n');
             line_num = line_num + 1;
          }
          else             /*minus operator*/
          {
              ungetc(c, fin); 
              return DIVOP;
          }
       }
       else if(c == ':')              /*possible assignment operator*/
       {
            c = getc(fin);
            if(c == '=')              /*assignment operator*/ 
                return ASSIGNOP;
            else                      /*error due to :*/
            {
                ungetc(c, fin); 
                lexical_error();
            }
       }
       else
       {
       		lexical_error();
       }
   }
}
token check_reserved()
{    
     if(strcmp(token_buffer, "main") == 0)             /*six reserved words*/
         return MAIN;
     else if(strcmp(token_buffer, "read") == 0) 
         return READ;
     else if(strcmp(token_buffer, "write") == 0) 
         return WRITE;
     else if (strcmp(token_buffer, "if") == 0) 
         return IF;
     else if (strcmp(token_buffer, "else") == 0) 
         return ELSE;
     else if (strcmp(token_buffer, "while") == 0) 
         return WHILE;     
     else                                              /*identifier*/
         return ID;
}
/*clears the buffer*/ 
void clear_buffer()
{
     token_ptr = 0;                         /*reset token pointer*/ 
     token_buffer[token_ptr] = '\0';        /*add null character*/
}
/*appends the character to buffer*/ 
void buffer_char(char c)
{
     token_buffer[token_ptr] = c;            /*append current character*/
     token_ptr = token_ptr + 1;             /*move token pointer*/
     token_buffer[token_ptr] = '\0';        /*move null character*/
}
void lexical_error(){
	error = TRUE;
	printf("Lexical Error on line %d\n", line_num);
}
void match(token tok)
{
   if(tok == next_token) /*expected token and actual token match*/
       ;
   else
       syntax_error();   /*expected token and actual token do not match*/
   //printf("current token: %s   line: %d\n", intToToken(next_token), line_num);
   next_token = scanner(); /*read the next token*/
}
void syntax_error(){
  error = TRUE;
  printf("Syntax Error on line %d\n", line_num);
}

void parser()
{
   next_token = scanner();  /*read the first token*/ 
   program();               /*parse the program*/ 
   match(SCANEOF);          /*check end of file*/
}
void program(){
    match(MAIN);
    match(LBRACKET);
    statement_list();
    match(RBRACKET);
}
void statement_list(){
    statement();
    while(TRUE){
      if (next_token == ID ||
          next_token == READ ||
          next_token == WRITE ||
          next_token == WHILE ||
          next_token == IF){
        statement();
      }
      else{
        break;
      }
    }
}
void statement(){
  if (next_token == ID){ //assignment
    match(ID);
    match(ASSIGNOP);
    expression();
    match(SEMICOLON);
  }
  else if (next_token == READ){
    match(READ);
    match(LPAREN);
    id_list();
    match(RPAREN);
    match(SEMICOLON);
  }
  else if (next_token == WRITE){
    match(WRITE);
    match(LPAREN);
    id_list();
    match(RPAREN);
    match(SEMICOLON);
  }
  else if (next_token == IF){
    match(IF);
    match(LPAREN);
    boolean();
    match(RPAREN);
    match(LBRACKET);
    statement_list();
    match(RBRACKET);
    if(next_token == ELSE){
      match(ELSE);
      match(LBRACKET);
      statement_list();
      match(RBRACKET);
    }
  }
  else if (next_token == WHILE){
    match(WHILE);
    match(LPAREN);
    boolean();
    match(RPAREN);
    match(LBRACKET);
    statement_list();
    match(RBRACKET);
  }
  else                              /*invalid beginning of statement*/
    syntax_error();
}
void expression(){
  term();
  while(TRUE){
      if (next_token == ADDOP ||
          next_token == SUBOP ||
          next_token == MULOP ||
          next_token == DIVOP){
        arithop();
        term();
      }
      else{
        break;
      }
    }
}
void id_list(){
  match(ID);
  while(TRUE){
      if (next_token == COMMA){
        match(COMMA);
        match(ID);
      }
      else{
        break;
      }
    }
}
void term(){
  if (next_token == ID){
    match(ID);
  }
  else if (next_token == INTLITERAL){
    match(INTLITERAL);
  }
  else if (next_token == LPAREN){
    match(LPAREN);
    expression();
    match(RPAREN);
  }
  else
    syntax_error();
}
void boolean(){
  operand();
  relop();
  operand();
}
void arithop(){
  if (next_token == ADDOP){
    match(ADDOP);
  }
  else if (next_token == SUBOP){
    match(SUBOP);
  }
  else if (next_token == MULOP){
    match(MULOP);
  }
  else if (next_token == DIVOP){
    match(DIVOP);
  }
  else
    syntax_error();
}
void operand(){
  if (next_token == ID){
    match(ID);
  }
  else if (next_token == INTLITERAL){
    match(INTLITERAL);
  }
  else
    syntax_error();
}
void relop(){
  if (next_token == GT){
    match(GT);
  }
  else if (next_token == GTE){
    match(GTE);
  }
  else if (next_token == LT){
    match(LT);
  }
  else if (next_token == LTE){
    match(LTE);
  }
  else if (next_token == EQUAL){
    match(EQUAL);
  }
  else if (next_token == NOTEQ){
    match(NOTEQ);
  }
  else
    syntax_error();
}
main(){
  int selection;
    do{
    //print the menu
      printMenu();
    //get user selection
      scanf("%d", &selection);
    //parse their selection
      parseSelection(selection);
  //break on 3
    } while(selection != 3); 
}
printMenu(){
  char *menuText = 
    "Please select from the following options:\n"
    "1  - Write tokens to a file\n"
    "2  - Parse a source file\n"
    "3  - quit\n";
  printf("%s", menuText);
}
parseSelection(int selection){
  if (1 == selection) 
    printTokens();
  else if(2 == selection)
    parseFile();
  else if(3 == selection)
    printf("Goodbye!\n");
  else
    printf("Invalid option. Try again.\n");
}
const char* intToToken(token thisToken){
  switch(thisToken){
    case MAIN:    return "MAIN";
    case LBRACKET:  return "LBRACKET";
    case RBRACKET:  return "RBRACKET";
    case READ:    return "READ";
    case COMMA:   return "COMMA";
    case WRITE:   return "WRITE";
    case IF:    return "IF";
    case ELSE:    return "ELSE";
    case WHILE:   return "WHILE";
    case LPAREN:  return "LPAREN";
    case RPAREN:  return "RPAREN";
    case ASSIGNOP:  return "ASSIGNOP";
    case ADDOP:   return "ADDOP";
    case MULOP:   return "MULOP";
    case SUBOP:   return "SUBOP";
    case DIVOP:   return "DIVOP";
    case SEMICOLON: return "SEMICOLON";
    case ID:    return "ID";
    case INTLITERAL: return "INTLITERAL";
    case SCANEOF:   return "SCANEOF";
    case GT:    return "GT";
    case GTE:   return "GTE";
    case LT:    return "LT";
    case LTE:   return "LTE";
    case EQUAL:   return "EQUAL";
    case NOTEQ:   return "NOTEQ";
  }
}

printTokens(){
  char infile[81], outfile[81];

  printf("Where is your input file?\n");
  scanf("%s",infile);
  FILE *fin = fopen(infile,"r");

  printf("Where is your outfile file (does not need to exist already)?\n");
  scanf("%s",outfile);
  FILE *fout = fopen(outfile,"w");
  token thisToken;

  do{
    thisToken = scanner();
    fprintf(fout, "%s\n", intToToken(thisToken));
  }while(SCANEOF != thisToken && TRUE != error);
}
parseFile(){
  char infile[81];
  printf("Where is your input file?\n");
  scanf("%s",infile);
  FILE *fin = fopen(infile,"r");
  error = FALSE;
  parser();
  if (FALSE == error){
    printf("No errors found!\n");
  }
}