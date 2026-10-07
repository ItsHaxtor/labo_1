/**
 * TAD ListaCredenciales: registro ordenado de codigos de credencial de un
 * gimnasio, con capacidad maxima definida al crearlo.
 *
 * <p><b>Valores:</b> una secuencia de codigos enteros, ubicados en las
 * posiciones contiguas 0, 1, ..., cantidad() - 1.</p>
 *
 * <p><b>Invariante:</b> 0 &lt;= cantidad() &lt;= capacidad(), y no existen
 * huecos entre las posiciones en uso.</p>
 *
 * <p>Esta interfaz solo declara el contrato (el "que"); no dice como se
 * guardan los datos (el "como").</p>
 */
public interface ListaCredenciales {

    /**
     * Inserta un codigo en una posicion, desplazando a la derecha los
     * elementos que estaban desde esa posicion.
     *
     * <p><b>Precondicion:</b> la lista no esta llena y 0 &lt;= pos &lt;= cantidad().</p>
     * <p><b>Postcondicion:</b> codigo queda en pos, los elementos previos desde pos
     * quedan una posicion mas a la derecha y cantidad() aumenta en 1.</p>
     *
     * @param pos    posicion de insercion (0 a cantidad())
     * @param codigo codigo a insertar
     * @throws IllegalStateException    si la lista esta llena
     * @throws IllegalArgumentException si pos esta fuera del rango 0 a cantidad()
     */
    void insertar(int pos, int codigo);

    /**
     * Elimina el codigo de una posicion, desplazando a la izquierda los
     * elementos posteriores para cerrar el hueco.
     *
     * <p><b>Precondicion:</b> la lista no esta vacia y 0 &lt;= pos &lt; cantidad().</p>
     * <p><b>Postcondicion:</b> devuelve el codigo que estaba en pos, los elementos
     * posteriores quedan una posicion mas a la izquierda y cantidad() disminuye en 1.</p>
     *
     * @param pos posicion a eliminar (0 a cantidad() - 1)
     * @return el codigo eliminado
     * @throws IllegalStateException    si la lista esta vacia
     * @throws IllegalArgumentException si pos esta fuera del rango 0 a cantidad() - 1
     */
    int eliminar(int pos);

    /**
     * Busca un codigo, de izquierda a derecha, solo entre las posiciones en uso.
     *
     * <p><b>Precondicion:</b> ninguna (funciona tambien con la lista vacia).</p>
     * <p><b>Postcondicion:</b> la lista no cambia.</p>
     *
     * @param codigo codigo buscado
     * @return la posicion de la primera coincidencia, o -1 si no esta
     */
    int buscar(int codigo);

    /**
     * Consulta cuantas posiciones estan en uso.
     *
     * @return cantidad de posiciones en uso
     */
    int cantidad();

    /**
     * Consulta el tamano fijo del arreglo.
     *
     * @return numero maximo de codigos que la lista puede contener
     */
    int capacidad();

    /**
     * Indica si la lista no tiene ningun codigo.
     *
     * @return true si no hay posiciones en uso
     */
    boolean estaVacia();

    /**
     * Indica si ya no quedan casillas libres.
     *
     * @return true si cantidad() es igual a capacidad()
     */
    boolean estaLlena();

    /**
     * Describe el estado completo de la lista en una linea.
     * Ejemplo: {@code [117, 142, _, _]  (n=2, capacidad=4)}
     *
     * @return texto con las posiciones en uso, las libres marcadas con _ y los contadores
     */
    String representacion();
}