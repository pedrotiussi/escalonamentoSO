import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Escalonamento {

    public static void main(String[] args) {

        int tempo=0;
        int qtd_processos;
        int duracao;
        int qtd_io;
        Processo processo_atual, processo_io;
        Queue<Processo> rr = new LinkedList<>();
        ArrayList<Processo> fcfs = new ArrayList<>();
        Queue<Processo> io = new LinkedList<>();
        Scanner in = new Scanner(System.in);

        //*** LEITURA DOS DADDOS DE ENTRADA ***
        System.out.print("Bem vinda, professora Raquel!\nInsira a quantidade de processos, duração e quantidade de operações de IO de cada um:\n");
        qtd_processos = in.nextInt();
        for (int i = 0; i < qtd_processos; i++) {
            duracao = in.nextInt();
            qtd_io = in.nextInt();
            processo_atual = new Processo(duracao, qtd_io, Integer.toString(i));
            rr.add(processo_atual);
        }

        Processo processo_anterior;
        boolean imprimiu;
        processo_anterior = processo_atual = rr.peek();
        System.out.print("\n\n***Diagrama de Gantt***\n\n\n" + tempo + "--");

        while (Auxiliares.filanaovazia(rr,fcfs,io)) {

            processo_io = io.peek();
            imprimiu = false;

            processo_atual = rr.peek();

            if (!fcfs.isEmpty() && rr.peek() == null) {
                processo_atual = fcfs.get(0);
                processo_atual.executafcfs();
                processo_atual.settempo_q1(0);
                processo_atual.toIO(fcfs, io);
                if (processo_anterior != processo_atual) {
                    Auxiliares.imprime(processo_anterior, tempo);
                    imprimiu = true;
                }
            }

            Processo temporaria = processo_atual;
            processo_atual = rr.peek();


            if (rr.peek() != null) {
                processo_atual.executarr();
                if (!processo_atual.toIO(rr, io)) {
                    processo_atual.toFCFS(rr, fcfs);
                }
                if (processo_anterior != processo_atual) {
                    Auxiliares.imprime(processo_anterior, tempo);
                    imprimiu = true;
                }
            }

            if (processo_io != null) {
                processo_io.executaIO(io, rr);
            }


            // Se Q0 for vazia e Q1 tiver processo na fila, devemos ter como processo atual aquele de Q1,
            // que está salva na variável temporaria
            // Esse if se fez necessário para tratar os casos de impressão do diagrama de Gantt.
            if (processo_atual == null && temporaria != null) {
                processo_atual = temporaria;
                imprimiu = false;
            }

            if (!fcfs.isEmpty()) {
                Processo.atualizaQ1(fcfs);
                Processo.toRR(fcfs, rr);
            }

            if (!imprimiu && processo_anterior != null && processo_atual == null) {
                Auxiliares.imprime(processo_anterior, tempo);
            }

            processo_anterior = processo_atual;
            tempo++;
        }
        System.out.println(processo_anterior.getnome() + "--" + tempo);

    }

}
