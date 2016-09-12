/*
  Last Modified: Avery 09.11
  Definitions for our datatypes
  Singleton pattern
  Made to support only integers and boolean datatypes
*/


public class Datatype{

  public static final DataType integer = new DataType( "integer" );
  public static final DataType boolean = new DataType( "boolean" );


  private String typeName;

  private DataType( String name ){

    typeName = name;
  }


  public String toString(){

    return typeName;
  }
}
