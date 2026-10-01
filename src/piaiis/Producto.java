
package piaiis;


public class Producto 
{
    //Atributos
    String nombre;
    float valor; 
    int cantidad; 
    String rutaImg;
    
    //Constructor parametrizado
    public Producto(String nombre, float valor, int cantidad, String rutaImg)
    {
        this.nombre=nombre;
        this.valor=valor;
        this.cantidad=cantidad;
        this.rutaImg="/imagenes/"+rutaImg;
    }
    
    //Método actualizarValorTotal
    
    public float obtenerVT()
    {
        return valor*cantidad;
    }
}
