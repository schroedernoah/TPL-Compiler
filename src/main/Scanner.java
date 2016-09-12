/*
  Scanner datatype definition
  Last Modified: Avery 09.11
*/

import java.io.PushBackInputStream;
import java.io.IOException;


public class Scanner{

  private PushBackInputStream source;
  private Token lookahead;

  public Scanner( PushBackInputStream in ){
    source = in;
    lookahead = null;
  }

  public Token peek() throws IOException, LexicalException{}
  public Token nextToken() throws IOException, LexicalException{}
  protected Token getNextToken() throws IOException, LexicalException{}
  protected int getNextByte() throws IOException{} //Does is always return int? Why?
  protected Token numericToken() throws IOException, LexicalException{}
  protected boolean isNumericDigit( char c ){}
  protected boolean isWhitespace( char c ){}

}
