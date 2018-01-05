import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Elena
 */
public class AplicacionCuentaBancaria {
    public static void main (String [] args) {
        
        boolean nombreCorrecto = false;
        boolean cuentaCorrecta = false;
        String cuentaCompleta;
        String entidad;
        String oficina;
        String control;
        String cuenta;
        int opcion = 0;
        double ingreso;
        double retirada;
        
        Scanner teclado = new Scanner(System.in);
        //Creamos el objeto
        CuentaBancaria cuenta1 = new CuentaBancaria();
        
        //Nos pedira el nombre hasta que se correcto
        while(nombreCorrecto == false){
            try{
                System.out.println("Introduzca su nombre");
                String titular = teclado.nextLine();
                cuenta1.setTitular(titular);
                nombreCorrecto=true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }    
        }
        //Mientras que el numero de cuenta no sea correcto se repite el bucle
        while(cuentaCorrecta == false){
            
            try{
                System.out.println("Introduzca el numero de cuenta");
            
            cuentaCompleta = teclado.nextLine();
            //Comprobamos que el formato es el correcto
            CuentaBancaria.comprobarFormato(cuentaCompleta);
            entidad = cuentaCompleta.substring(0,4);
            oficina = cuentaCompleta.substring(5,9);
            control = cuentaCompleta.substring(10,12);
            cuenta =  cuentaCompleta.substring(13,23);
            //Se comprueba la longitud de cada campo y que los dígitos de control sean correctos 
            //con el método comprobarCuenta
            CuentaBancaria.comprobarCuenta(entidad, oficina, control, cuenta);
            cuenta1.setEntidad(entidad);
            cuenta1.setOficina(oficina);
            cuenta1.setControl(control);
            cuenta1.setCuenta(cuenta);
            cuentaCorrecta = true;
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        
         //Cuando el noombre y el número de cuenta sean correctos aparecerá el menú
        //Mientras que la opción sea distinta a 10, el programa realizará las acciones elegidas
        //y volverá a mostrar el menú
        //Cuando sea 10, el programa se cerrará
        while (opcion!=10){
            
            System.out.println("Elige una opcion: ");
            System.out.println("1. Ver codigo cuenta completo");
            System.out.println("2. Ver el titular de la cuenta");
            System.out.println("3. Ver el codigo de la entidad");
            System.out.println("4. Ver el codigo de la oficina");
            System.out.println("5. Ver el numero de la cuenta");
            System.out.println("6. Ver los digitos de control de la cuenta");
            System.out.println("7. Realizar un ingreso");
            System.out.println("8. Retirar efectivo");
            System.out.println("9. Consultar saldo");
            System.out.println("10.Salir");
            
            
            opcion = teclado.nextInt();
            
         //Acciones a realizar según la opción elegida utilizando los métodos 
            //de la clase CuentaBancaria
            //Si la cantidad a ingresar o a retirar no es correcta se lanza una excepción
            try{
                switch (opcion){
                    case 0:
                        break;
                    case 1:
                        System.out.println("El numero de cuenta es: "
                                + " " + cuenta1.verCuenta());

                        break;
                    case 2:
                        System.out.println(" El nombre del titular es: " + cuenta1.getTitular());
                        break;
                    case 3: 
                        System.out.println ("Los numeros de la entidad son: " + cuenta1.getEntidad());
                        break;
                    case 4: 
                        System.out.println("Los ´numeros  de la oficina son: " + cuenta1.getOficina());
                        break;
                    case 5: 
                        System.out.println("Los numeros de la cuenta son: " + cuenta1.getCuenta());
                        break;
                    case 6:
                        System.out.println("Los digitos de control son: " + cuenta1.getControl());
                       break;
                    case 7:
                        System.out.println ("Cantidad a ingresar: ");
                        ingreso = teclado.nextDouble();
                        cuenta1.ingresar(ingreso);
                        break;
                    case 8:
                        System.out.println ("Cantidad a retirar: ");
                        retirada = teclado.nextDouble();
                        cuenta1.retirar(retirada);
                        break;
                    case 9:
                        System.out.println("El saldo disponible es de: " + cuenta1.getSaldo());
                        break;

                }
            }
            
            catch (Exception e){
                 System.out.println(e.getMessage());
            }

        }
        
            
        
    }
}

