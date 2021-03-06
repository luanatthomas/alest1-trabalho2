package src;
/**
 * Esta classe guarda as palavra do indice remissivo em ordem alfabetica.
 * 
 * @author Isabel H. Manssour
 */

public class ListaOrdenadaDePalavras {

    private Palavra head;
    private Palavra tail;
    private int count;

    // Classe interna
    private class Palavra {
        public String s;
        public ListaDeOcorrencias listaOcorrencias;
        public Palavra next;

        public Palavra(String str) {
            s = str;
            next = null;
            listaOcorrencias = new ListaDeOcorrencias();
        }
    }

    // construtor
    public ListaOrdenadaDePalavras() {
        head = null;
        tail = null;
        this.count = 0;
    }

    // metodo add para adicionar uma palavra na lista
    public void add(String palavra) {
        Palavra p = new Palavra(palavra);
        if (head == null) {
            head = p;
        } else {
            tail.next = p;
        }
        tail = p;
        count++;

        this.ordenar();
    }

    // metodo add para adicionar uma pagina na lista de ocorrencias da palavra 
    public boolean addPagina(String palavra, int pagina) {
        Palavra current = head;
        for (int i = 0; i < count; i++) {
            if (current.s.equals(palavra)) {
                if (current.listaOcorrencias.contains(pagina)) {
                    return false;
                }
                current.listaOcorrencias.add(pagina);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // metodo toString
    public String toString() { // O(n)
        StringBuilder string = new StringBuilder();
        Palavra aux = head;
        while (aux != null) {
            string.append(aux.s.toString());
            String aux2 = " (" + aux.listaOcorrencias.size() + " ocorrĂȘncias) \n";
            string.append(aux2);
            string.append(aux.listaOcorrencias.toString());
            string.append("\n");
            aux = aux.next;
        }
        return string.toString();
    }

    // remove palavra atravĂ©s do index - NAO USADO
    public String removeByIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        String elemRemovido;
        if (index == 0) {
            elemRemovido = head.s;

            if (count == 1) {
                tail = null;
            }
            head = head.next;

            count--;
            return elemRemovido;
        }

        Palavra ant = head;
        for (int i = 0; i < index - 1; i++) {
            ant = ant.next;
        }
        elemRemovido = ant.next.s;

        if (index == count - 1) {
            tail = ant;
            tail.next = null;

        } else {
            ant.next = ant.next.next;

        }
        return elemRemovido;
    }

    // retorna a palavra do index - NAO USADO
    public String get(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        if (index == count - 1)
            return tail.s;

        Palavra aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return (aux.s);
    }

    // retorna o index da palavra - NAO USADO
    public int indexOf(String palavra) {
        Palavra aux = head;
        for (int i = 0; i < count; i++) {
            if (aux.s.equals(palavra)) {
                return i;
            }
            aux = aux.next;
        }
        return -1;
    }

    // retorna o size da lista de palavras - NAO USADO
    public int size() {
        return count;
    }

    // verifica se a lista de palavras esta vazia - NAO USADO
    public boolean isEmpty() {
        return (head == null);
    }

    // limpa a lista de palavras - NAO USADO
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    // verifica se a palavra jĂĄ esta na lista
    public boolean contains(String palavra) {
        Palavra aux = head;
        for (int i = 0; i < count; i++) {
            if (aux.s.equals(palavra)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    // ordena a lista de palavras
    public void ordenar() {
        if (count > 1) {
            Palavra current = head;
            int tamanho = count;

            for (int i = 0; i < tamanho; i++) {
                Palavra next = current.next;
                for (int j = i + 1; j < tamanho; j++) {
                    int compareTo = current.s.compareTo(next.s);
                    if (compareTo > 0) {
                        String aux = current.s;
                        ListaDeOcorrencias list = current.listaOcorrencias;
                        current.s = next.s;
                        current.listaOcorrencias = next.listaOcorrencias;
                        next.s = aux;
                        next.listaOcorrencias = list;
                    }
                    next = next.next;
                }
                current = current.next;
            }

        }
    }

    public ListaOrdenadaDePalavras palavraMaisFrequente(){
        ListaOrdenadaDePalavras listaPalavrasMaisFrequentes = new ListaOrdenadaDePalavras();

        int quantOcorrencias = 0;
        Palavra aux = head;
        for (int i = 0; i < count; i++) {
            if (aux.listaOcorrencias.size() > quantOcorrencias) {
                quantOcorrencias = aux.listaOcorrencias.size();
            }
            aux = aux.next;
        }

        aux = head;
        for (int i = 0; i < count; i++) {
            if (aux.listaOcorrencias.size() == quantOcorrencias) {
                listaPalavrasMaisFrequentes.add(aux.s);
            }
            aux = aux.next;
        }

        return listaPalavrasMaisFrequentes;
    }

    public ListaDeOcorrencias buscaPalavra(String word){
        Palavra aux = head;
        for (int i = 0; i < count; i++) {
            if(aux.s.equals(word)){
                return aux.listaOcorrencias;
            }
            aux = aux.next;
        }
        return null;
    }
}
