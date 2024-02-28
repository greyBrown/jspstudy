package things;

public class asd {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
   
    String strNumber = "0";
    Optional<String> opt = Optional.ofNullable(strNumber2);
    double number2 = Double.parseDouble(opt.orElse("0").isEmpty() ? "0" : strNumber2);
    System.out.println(number2);
  }

}
