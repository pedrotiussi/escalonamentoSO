/// COLOCAR FUNÇÕES QUE SERÃO INVOCADAS REPETIDAS VEZES
import java.util.ArrayList;
import java.util.Queue;

public class Auxiliares {

    public static boolean filanaovazia(Queue<Processo> rr, ArrayList<Processo> fcfs, Queue<Processo> io) {
        return (!(rr.peek()==null && fcfs.isEmpty() && io.peek() == null));
    }

    public static void imprime(Processo processo_atual, int tempo) {
        if (processo_atual == null)
            //System.out.print("----" + tempo + "--");
            System.out.print(tempo + "------");
        else
           // System.out.print(processo_atual.getnome() + "--" + tempo + "--");
            System.out.print(tempo + "--" + processo_atual.getnome() + "--");
    }
}
