/**
 * Implementacion del TAD {@link ListaCredenciales} con un arreglo estatico de
 * enteros. El arreglo y el contador estan ocultos (private): el cliente solo
 * puede usar las operaciones de la interfaz.
 */
public class ListaCredencialesArreglo implements ListaCredenciales {

    /** Capacidad maxima permitida al crear la lista. */
    public static final int CAPACIDAD_MAXIMA = 100;

    /** Casillas del arreglo; solo las posiciones 0 a n - 1 estan en uso. */
    private final int[] datos;

    /** Cantidad de posiciones en uso. */
    private int n;

    /**
     * Crea una lista vacia con la capacidad indicada.
     *
     * @param capacidad numero de casillas (1 a {@value #CAPACIDAD_MAXIMA})
     * @throws IllegalArgumentException si la capacidad esta fuera de rango
     */
    public ListaCredencialesArreglo(int capacidad) {
        if (capacidad < 1 || capacidad > CAPACIDAD_MAXIMA) {
            throw new IllegalArgumentException(
                "Capacidad fuera de rango (1 a " + CAPACIDAD_MAXIMA + "): " + capacidad);
        }
        datos = new int[capacidad];
        n = 0;
    }

    /** {@inheritDoc} */
    @Override
    public void insertar(int pos, int codigo) {
        // Se valida el contrato: primero espacio libre, luego posicion valida
        if (estaLlena()) {
            throw new IllegalStateException("Lista llena");
        }
        if (pos < 0 || pos > n) {
            throw new IllegalArgumentException(
                "Posicion invalida: " + pos + " (valida: 0 a " + n + ")");
        }
        // Se desplaza de derecha a izquierda para no sobrescribir datos
        for (int i = n; i > pos; i--) {
            datos[i] = datos[i - 1];
        }
        datos[pos] = codigo;
        n++;
    }

    /** {@inheritDoc} */
    @Override
    public int eliminar(int pos) {
        if (estaVacia()) {
            throw new IllegalStateException("Lista vacia");
        }
        if (pos < 0 || pos >= n) {
            throw new IllegalArgumentException(
                "Posicion invalida: " + pos + " (valida: 0 a " + (n - 1) + ")");
        }
        int eliminado = datos[pos];
        // Se copia cada elemento posterior sobre el anterior, de izquierda a derecha
        for (int i = pos; i < n - 1; i++) {
            datos[i] = datos[i + 1];
        }
        n--;
        return eliminado;
    }

    /** {@inheritDoc} */
    @Override
    public int buscar(int codigo) {
        // Solo se revisan las posiciones en uso (i < n), no toda la capacidad
        for (int i = 0; i < n; i++) {
            if (datos[i] == codigo) {
                return i;
            }
        }
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public int cantidad() {
        return n;
    }

    /** {@inheritDoc} */
    @Override
    public int capacidad() {
        return datos.length;
    }

    /** {@inheritDoc} */
    @Override
    public boolean estaVacia() {
        return n == 0;
    }

    /** {@inheritDoc} */
    @Override
    public boolean estaLlena() {
        return n == datos.length;
    }

    /** {@inheritDoc} */
    @Override
    public String representacion() {
        String texto = "[";
        for (int i = 0; i < datos.length; i++) {
            if (i > 0) {
                texto += ", ";
            }
            texto += (i < n) ? String.valueOf(datos[i]) : "_";
        }
        return texto + "]  (n=" + n + ", capacidad=" + datos.length + ")";
    }
}