import java.util.*;

//state machine
public class StateMachine{
    public static final int looking = 0;
    public static final int integer = 1;
    public static final int string = 2;
    
    int currentState = 0;
    String symbolString = "+-*/<=(){},:;";
    String[] keywordArray = {"if", "then", "else"};//has more than this   
    List<Token> tokenArray = new ArrayList<>();
    String accum = "";
    char curChar;
    String inputFile;
    int curIndex = 0; 
    
    public StateMachine(String fileString){
        inputFile = fileString;
    }
    public void printTokenStrings()
    {
        for (Token individualToken : tokenArray) 
        {
           System.out.println(individualToken);
        }
    }
    
    //needs to be called by a loop until length is greater than curIndex
    //make sure not to ignore what was in the accum at the end of the 
    public void takeNextToken(){
        curChar = inputFile.charAt(curIndex);       
        switch (currentState) 
        {
            case 0: //looking        
                if(Character.isDigit(curChar))
                {//intToken
                    accum += curChar;
                    currentState = 1;//integer
                }else if(Character.isLetter(curChar))
                {//boolToken, keywordToken, and identifierToken
                    accum += curChar;
                    currentState = 2;//string
                }else if(symbolString.indexOf(curChar) != -1)
                {//terminator, punctToken, and opToken                  
                    switch (curChar)  
                    {//all symbols are self-delimiting
                        case ';':
                            tokenArray.add(new TerminatorToken());
                            break;
                        case '+': case '-': case '*':
                        case '/': case '<': case '=':
                            tokenArray.add(new OpToken(curChar));
                            break;
                        case '(': case ')': case '{':
                        case '}': case ',': case ':':
                            tokenArray.add(new PunctToken(curChar));
                        default:            
                            break;
                    }                   
                }else if(!Character.isWhitespace(curChar))  
                {
                    //throw error
                    accum = "";
                    currentState = 0;//looking again
                }   
                curIndex++;
                break;
            case 1: //integer state
                if(Character.isDigit(curChar))
                {
                    accum += curChar;                   
                }else if(Character.isWhitespace(curChar))
                {
                    tokenArray.add(new IntToken(accum));
                    accum = "";
                    currentState = 0;//looking again
                }else if(symbolString.indexOf(curChar) != -1)
                {
                    tokenArray.add(new IntToken(accum));
                    switch (curChar)
                    {//the symbols are self-delimiting  
                        case ';':
                            tokenArray.add(new TerminatorToken());
                            break;
                        case '+': case '-': case '*':
                        case '/': case '<': case '=':
                            tokenArray.add(new OpToken(curChar));
                            break;
                        case '(': case ')': case '{':
                        case '}': case ',': case ':':
                            tokenArray.add(new PunctToken(curChar));
                    }  
                    accum = "";
                    currentState = 0;//looking again
                }else
                {
                    //throw error
                    accum = "";
                    currentState = 0;//looking again
                }
                curIndex++;    
                break;
            case 2: //string state
                if(Character.isLetterOrDigit(curChar))
                {
                    accum += curChar;                   
                }else if(Character.isWhitespace(curChar))
                {
                    //boolToken, keywordToken, or identifierToken?
                    if(accum.equals("false") || accum.equals("true"))
                    {
                       tokenArray.add(new BoolToken(accum)); 
                    }else if(Arrays.asList(keywordArray).contains(accum))
                    {//need to add keywords to the array still
                        tokenArray.add(new KeywordToken(accum));
                    } else
                    {
                        tokenArray.add(new IdentifierToken(accum));
                    }                   
                    accum = "";
                    currentState = 0;//looking again
                }else if(symbolString.indexOf(curChar) != -1)
                {
                    //boolToken, keywordToken, or identifierToken?
                    if(accum.equals("false") || accum.equals("true"))
                    {
                       tokenArray.add(new BoolToken(accum)); 
                    }else if(Arrays.asList(keywordArray).contains(accum))
                    {//need to add keywords to the array still
                        tokenArray.add(new KeywordToken(accum));
                    } else
                    {
                        tokenArray.add(new IdentifierToken(accum));
                    }
                    //boolToken, keywordToken, and identifierToken?
                    switch (curChar)
                    {//the symbols are self-delimiting  
                        case ';':
                            tokenArray.add(new TerminatorToken());
                            break;
                        case '+': case '-': case '*':
                        case '/': case '<': case '=':
                            tokenArray.add(new OpToken(curChar));
                            break;
                        case '(': case ')': case '{':
                        case '}': case ',': case ':':
                            tokenArray.add(new PunctToken(curChar));
                        accum = "";
                        currentState = 0;//looking again
                    }
                }else
                {
                   //throw error 
                }
                curIndex++;
                break;
        }
    }
    
}

public interface Token{
    public String toString();
}

public class IntToken implements Token{
    String outputString = "Integer ";
    public IntToken(String inputInt){ 
        outputString += inputInt;
    }
    public String toString(){ 
        return outputString;
    }    
}
public class BoolToken implements Token{
    String outputString = "Boolean ";
    public BoolToken(String inputString){   
        outputString += inputString;
    }
    public String toString(){ 
        return outputString;
    }    
}
public class TerminatorToken implements Token{
    String outputString = "Terminator ";
    public TerminatorToken(){      
    }
    public String toString(){ 
        return outputString + ";";
    }    
}
public class KeywordToken implements Token{
    String outputString = "Keyword ";
    public KeywordToken(String inputString){   
        outputString += inputString;
    }
    public String toString(){ 
        return outputString;
    }    
}
public class IdentifierToken implements Token{
    String outputString = "Identifier ";
    public IdentifierToken(String inputString){ 
        outputString += inputString;
    }
    public String toString(){ 
        return outputString;
    }    
}
public class DecToken implements Token{
    String outputString = "Declaration ";
    public DecToken(String inputString){ 
        outputString += inputString;
    }
    public String toString(){ 
        return outputString;
    }    
}
public class PunctToken implements Token{
    String outputString = "Punctuation ";
    public PunctToken(char inputChar){
        outputString += inputChar;
    }
    public String toString(){ 
        return outputString;
    }    
}
public class OpToken implements Token{
    String outputString = "Operator ";
    public OpToken(char inputChar){   
        outputString += inputChar;
    }
    public String toString(){ 
        return outputString;
    }    
}