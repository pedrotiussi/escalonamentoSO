/// COLOCAR FUNÇÕES QUE SERÃO INVOCADAS REPETIDAS VEZES
import java.util.Queue;

public class Auxiliares {

    public static boolean filanaovazia(Queue<Processo> rr, Queue<Processo> fcfs, Queue<Processo> io) {
        return (!(rr.peek()==null && fcfs.peek()==null && io.peek() == null));
    }

    public static void executaIO(Queue<Processo> io, Queue<Processo> rr) {

    }

    public static void imprime(Processo processo_atual, int tempo) {
    }
}
