public class AbstractSyntaxTree{
  public String toString(){ return "AST"; } // What does this do? FIXME
}

/*
  Defines a program
*/
class Program extends AbstractSyntaxTree{

  // Program Parts
  private Identifier identifier;
  private Formals formals;
  private Definitions definitions;
  private Body body;

  // Program creation
  public Program( Identifier i, Formals f, Definitions d, Body b ){
    identifier = i;
    formals = f;
    definitions = d;
    body = b;
  }

  // Returns program parts when called FIXME
  public Identifier identifier(){ return identifier; } //FIXME can I call function identifier if I have variable called identitier?
  public Formals formals(){ return formals; }
  public Definitions definitions(){ return definitions; }
  public Body bodys(){ return body; }

}

/*
  Defines all program values. Found in ACDC program, do we need it here? FIXME
*/
class Value extends AbstractSyntaxTree{

  // Value parts
  private Datatype type;

  // Value creation
  public Value( Datatype t ){
    type = t;
  }
  public Datatype type(){
    return type;
  }
  public void setType( Datatype t ){
    type = t;
  }

  // Returns Value parts
  public String toString(){
    String typeString;
    if( type == null ){
      typeString = "none";
    } else if( type.equals(Datatype.Integer) ) {
      typeString = "integer";
    } else if( type.equals(Datatype.Boolean) ){
      typeString = "boolean";
    } else {
      typeString = "other dataype - PROBABLY AN ERROR" //FIXME is this an error to reach here?
    }

    return " (type " + typeString + ") ";
  }
}
class Identifier extends Value{

  //FIXME how do you define an identifier or name? Same as ACDC compiler example?
}


class Formals extends AbstractSyntaxTree{

  // Formals parts
  //TODO how do you incorporate epsilon the empty string?
  private Def def;
  private Definitions definitions;

  // Formals creation
  public Formals(){} //FIXME Do I create an empty constructor for epsilon, no formal declarations?

  public Formals( Def d, Definitions ds ){}
    def = d;
    definitions = ds;
  }

  // Returns Formals parts when called FIXME
  public Def def(){ return def; } //FIXME can I have function def if I have variable def?
  public Definitions definitions(){ return definitions; }//^^^^
}


class Definitions extends AbstractSyntaxTree{

  //TODO once again, how to handle epsilon in this case FIXME
}


class Body extends AbstractSyntaxTree{

  private StatementList statementList;

  public Body( StatementList sl ){
    statementList = sl;
  }
  //FIXME do we need to identify "BEGIN" and "END" in this definition of the syntax???
  public StatementList statementList(){ return statementList; }// FIXME
}
