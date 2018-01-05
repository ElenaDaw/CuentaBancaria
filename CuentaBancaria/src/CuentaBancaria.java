
/**
 *
 * @author Elena
 */
public class CuentaBancaria {
    
    String titular;
    String entidad;
    String oficina;
    String control;
    String cuenta;
    double saldo;
    
    long telefono;
    String direccion;
    
    public CuentaBancaria(){
        this.saldo = 0;
    }
    
    public CuentaBancaria(String titular, String entidad, String oficina, String control, String cuenta,double saldo){
        this.titular = titular;
        this.entidad = entidad;
        this.oficina = oficina;
        this.control = control;
        this.cuenta = cuenta;
        this.saldo = saldo;
        
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) throws Exception {
       if (titular.length()>= 100){
          throw new Exception("El nombre supera los campos permitidos");
      }else {
          this.titular=titular;
      }
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
    
  //Método que permite calcular los dígitos de control
    private static String calcularDC(String entidad, String oficina, String control, String cuenta){
      String cta = entidad + oficina + control + cuenta;


      int d1 = 0;
      int d2 = 0;
      int cont;

      int[] multiplica ={1,2,4,8,5,10,9,7,3,6};


      for (cont = 0;cont < (multiplica.length - 2);cont++){
        d1 += multiplica[cont+2] * Integer.parseInt(cta.substring(cont, cont+1));
      }

      for (cont = 0;cont < multiplica.length;cont++){
        d2 += multiplica[cont] * Integer.parseInt(cta.substring((cont+10),(cont+10+1)));
      }

      d1=11-(d1%11);
      d2=11-(d2%11);

      d1 = (d1==11) ? 0 : (d1==10) ? 1 : d1;
      d2 = (d2==11) ? 0 : (d2==10) ? 1 : d2;

      String digitos = String.valueOf(d1)+String.valueOf(d2);
      return digitos;
  }
    
   //Método para comprobar si la longitud de cada campo es correcta
  //Captura excepción en el caso de que la longitud no sea la correcta
  //o los dígitos de control no sean correctos utilizando el método calcularDC 
    public static void comprobarCuenta(String entidad, String oficina, String control, String cuenta) throws Exception{
        if(entidad.length()!=4){
            throw new Exception ("La longitud de la entidad no es correcta");
        }
        if(oficina.length()!= 4){
            throw new Exception("La longitud de la oficina no es correcta");
            
        }
        if(control.length() != 2){
            throw new Exception ("La longitud de los digitos de control no es correcta");
        }
        if(cuenta.length() != 10){
            throw new Exception("La longitud de la cuenta no es correcta");
        }
        if (control.compareTo(calcularDC(entidad, oficina, control, cuenta)) != 0){
          throw new Exception("Los digitos de control no son validos");
      }
    }
    //Metodo que comprueba que en una posición hay un nuemro del 0 al 99
    
    public static void comprobarDigito(String cuenta, int posicion)  throws Exception {
        if(cuenta.charAt(posicion)== '0' ||
                cuenta.charAt(posicion) == '1' ||
                cuenta.charAt(posicion) == '2' ||
                cuenta.charAt(posicion) == '3' ||
                cuenta.charAt(posicion) == '4' ||
                cuenta.charAt(posicion) == '5' ||
                cuenta.charAt(posicion) == '6' ||
                cuenta.charAt(posicion) == '7' ||
                cuenta.charAt(posicion) == '8' ||
                cuenta.charAt(posicion) == '9'){
        }else{
              throw new Exception("Algunos de los campos no contienen numeros");
        }
    }
    
    //Metodo que comprueba que el formato de la cuenta es el correcto
    public static void comprobarFormato(String cuenta) throws Exception{
        //Se comprueba la longitud total
        if(cuenta.length() != 23){
            throw new Exception ("La longitud del numero de cuenta no es correcta");
        }
        //Se comprueba en que posicion deben aparecer guiones "-"
        if((cuenta.charAt(4) != '-') || (cuenta.charAt(9) != '-')||(cuenta.charAt(12)!= '-')){
            throw new Exception ("Se requiere separar los campos con guiones '0000-0000-00-0000000000'");
        }
        //con el metodo comprobarDigito se comprueba en que posicion deben apareceer numeros
        comprobarDigito(cuenta,0);
        comprobarDigito(cuenta, 1);
        comprobarDigito(cuenta, 2);
        comprobarDigito(cuenta, 3);
        comprobarDigito(cuenta, 5); 
        comprobarDigito(cuenta, 6); 
        comprobarDigito(cuenta, 7); 
        comprobarDigito(cuenta, 8); 
        comprobarDigito(cuenta, 10); 
        comprobarDigito(cuenta, 11); 
        comprobarDigito(cuenta, 13);  
        comprobarDigito(cuenta, 14); 
        comprobarDigito(cuenta, 15);  
        comprobarDigito(cuenta, 16);  
        comprobarDigito(cuenta, 17);  
        comprobarDigito(cuenta, 18);  
        comprobarDigito(cuenta, 19);  
        comprobarDigito(cuenta, 20);  
        comprobarDigito(cuenta, 21); 
        comprobarDigito(cuenta, 22);
    }
    
    //Metodo que muestra el numero de cuenta completo
    public String verCuenta(){
        return this.entidad + "-" + this.oficina + "-" + this.control + "-" + this.cuenta;
    }
    
   //Método para ingresar saldo
  //Captura de una excepción si se intenta ingresar una cantidad negativa
  public void ingresar (double cantidad)throws Exception {
      if (cantidad < 0 ){
          throw new Exception("No es posible ingresar esa cantidad. La cantidad es negativa");
      }
      this.saldo = this.saldo + cantidad;
  }
  //Método para retirar saldo
  //Captura de una excepción si se intenta retirar una cantidad mayor al saldo disponible
  public void retirar (double cantidad) throws Exception{
      if ((cantidad > this.saldo) || (cantidad < 0)){
          throw new Exception("No es posible retirar esa cantidad. La cantidad es mayor al saldo disponible ");
      }
      this.saldo = this.saldo - cantidad;
      
  }
    
}

