import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Escalonamento {

    public static void main(String[] args) {
        int tempo=0;
        int qtd_processos;
        int duracao;
        int qtd_io;
        Processo processo_atual;
        Queue<Processo> rr = new LinkedList<>();
        Queue<Processo> fcfs = new LinkedList<>();
        Queue<Processo> io = new LinkedList<>();
        Scanner in = new Scanner(System.in);

        //*** LEITURA DOS DADDOS DE ENTRADA ***
        qtd_processos = in.nextInt();
        for (int i = 0; i < qtd_processos; i++) {
            duracao = in.nextInt();
            qtd_io = in.nextInt();
            processo_atual = new Processo(duracao, qtd_io, Integer.toString(i + 1));
            rr.add(processo_atual);
        }

        while (Auxiliares.filanaovazia(rr,fcfs,io)){

            processo_atual= rr.peek();
            if (processo_atual != null){
                processo_atual.executa();
                processo_atual.toIO(rr, io);
                processo_atual.toFCFS(rr, fcfs);
            }
            else {
                processo_atual = fcfs.peek();
                processo_atual.executa();
                processo_atual.toIO(fcfs, io);
                processo_atual.toRR(fcfs, rr);
            }
            Auxiliares.executaIO(io,rr);
            tempo++;
            ///Chegar se precisa imprimir, tem que pensar em como vai fazer
            Auxiliares.imprime(processo_atual,tempo);

        }




    }


}
