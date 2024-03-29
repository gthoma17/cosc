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
   ID, INTLITERAL, BEGIN, END, READ, WRITE, 
   PLUSOP, MINUSOP, ASSIGNOP, LPAREN, RPAREN, 
   COMMA, SEMICOLON, SCANEOF
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
void id_list();
void expression_list(); 
void expression();
void term();
void add_op();
void match(token tok); 
void syntax_error();

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

   while(TRUE)           /*loop reads and returns next token*/ 
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
          while (isalnum(c) || c == '_') /*read and buffer subsequent characters*/
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
      
      else if (c == '(')   /*left parentheses*/ 
          return LPAREN;
      
      else if (c == ')')   /*right parentheses*/ 
          return RPAREN;
      
      else if (c == ',')   /*comma*/
          return COMMA;
      
      else if (c == ';')   /*semicolon*/
          return SEMICOLON;
      
      else if (c == '+')   /*plus operator*/
          return PLUSOP;
      
      else if (c == '-')   /*comment or minus operator*/
      {   
      	  c = getc(fin);
          if(c == '-')     /*comment begins*/
          {
             do            /*read and discard until end of line*/
                c = getc(fin);
             while (c != '\n');
             line_num = line_num + 1;
          }
          else             /*minus operator*/
          {
              ungetc(c, fin); 
              return MINUSOP;
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

       else                           /*invalid character*/
            lexical_error();
    }

}
/**********************************************************************/

/*clears the buffer*/ 
void clear_buffer()
{
     token_ptr = 0;                         /*reset token pointer*/ 
     token_buffer[token_ptr] = '\0';        /*add null character*/
}
/**********************************************************************/

/*appends the character to buffer*/ 
void buffer_char(char c)
{
     token_buffer[token_ptr] = c;            /*append current character*/
     token_ptr = token_ptr + 1;             /*move token pointer*/
     token_buffer[token_ptr] = '\0';        /*move null character*/
}
/**********************************************************************/

/*checks whether buffer is reserved word or identifier*/ 
token check_reserved()
{    
     if(strcmp(token_buffer, "begin") == 0)             /*four reserved words*/
         return BEGIN;
     else if (strcmp(token_buffer, "end") == 0) 
         return END;
     else if(strcmp(token_buffer, "read") == 0) 
         return READ;
     else if(strcmp(token_buffer, "write") == 0) 
         return WRITE;
     else                                              /*identifier*/
         return ID;
}
/*********************************************************************/
/*reports lexical error and sets error flag*/ 
void lexical_error()
{  
   printf("lexical error in line %d/n", line_num); 
   error = TRUE;
}


/********************************************************************/

/*parses source file*/ 
void parser()
{
   next_token = scanner();  /*read the first token*/ 
   program();               /*parse the program*/ 
   match(SCANEOF);          /*check end of file*/
}

/*********************************************************************/

/*parses a program*/
/* <program> --> begin<stmtlist>end */ 
void program()
{
   match(BEGIN);            /*begin*/
   statement_list();       /*list of statements*/
   match(END);              /*end*/
}

/*********************************************************************/

/*parses list of statements*/
/* <stmtlist> --> <stmt>{<stmt>} */
void statement_list()
{
   statement();             /*first statement*/
   while (TRUE)
   {
     if(next_token == ID || next_token == READ || next_token == WRITE) 
        statement();        /*subsequent statements*/
     else
        break;
    }
}


 /********************************************************************/

 /*parses one statement*/
 /* <stmt> --> id:=<expr>;
   <stmt> --> read(<idlist>);
   <stmt> --> write(<exprlist>); */ 
void statement()
{
   if(next_token == ID)  /*assignment statement*/
   {
     match (ID);
     match (ASSIGNOP); 
     expression();
     match(SEMICOLON);
   }      
   else if (next_token == READ)       /*read statement*/
   {   match(READ); 
       match(LPAREN); 
       id_list();
       match(RPAREN); 
       match(SEMICOLON);
   }
   else if (next_token == WRITE) /*write statement*/
   {
       match(WRITE); 
       match(LPAREN); 
       expression_list();
       match(RPAREN); 
       match(SEMICOLON);
   }
   else                              /*invalid beginning of statement*/
       syntax_error();
}
/********************************************************************/

/*parses list of identifiers*/ 
/* <idlist> --> id{,id} */ 
void id_list()
{
     match(ID);                        /*first identifier*/
     while(next_token == COMMA)

         match(COMMA);
         match(ID);                  /*subsequent identifiers*/
}


/*******************************************************************/

/*parses list of expressions*/ 
/* <explist> --> <exp>{,<exp>} */
void expression_list()
{    expression();                   /*first expression*/
     while(next_token == COMMA)

         match(COMMA);
         expression();               /*susequent expressions*/
}

/******************************************************************/ 

/*parses one expression*/
/* <exp> --> <term>{<adop><term>} */
void expression()
{
     term();                        /*first term*/
     while(next_token == PLUSOP || next_token == MINUSOP)
     {   
         add_op();                  /*plus or minus operators*/
         term();                    /*subsequent terms*/
     }
}
/******************************************************************/

/*parses one term*/
/* <term> --> id
   <term> --> integer
   <term> --> (<expr>) */ 
void term()
{
   if(next_token == ID)          /*identifier*/
       match(ID);
   else if(next_token == INTLITERAL) /*integer literal*/
       match(INTLITERAL);
   else if(next_token ==LPAREN)  /*expression inside parentheses*/
   {
       match(LPAREN); 
       expression(); 
       match(RPAREN);
   }
   else                          /*invalid term*/
       syntax_error();
}
/******************************************************************/

/*parses plus or minus operator*/ 
/* <adop> --> +|- */
void add_op()
{
   if(next_token == PLUSOP || next_token == MINUSOP) 
       match(next_token);
   else
       syntax_error();
}
/******************************************************************/

/*checks whether the expected token and the actual token match, 
and also reads the next token from source file*/
void match(token tok)
{
   if(tok == next_token) /*expected token and actual token match*/
       ;
   else
       syntax_error();   /*expected token and actual token do not match*/

   next_token = scanner(); /*read the next token*/
}
/********************************************************************/

/*reports syntax error*/ 
void syntax_error()
{
   printf("syntax error in line %d\n", line_num); 
   error = TRUE;
}
/********************************************************************/
int main(int argc, char const *argv[])
{
	return 0;
} 