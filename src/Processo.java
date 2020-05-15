import java.util.ArrayList;
import java.util.Queue;


public class Processo {
    private int burst, qtd_io, espera_Q1, tempo_executado, tempo_io, executado_Q0;
    private String nome;

    Processo (int burst, int io, String nome){
        this.burst = burst;
        this.qtd_io = io;
        this.nome = "P" + nome;
        this.espera_Q1 = this.tempo_io = this.tempo_executado = this.executado_Q0 = 0;
    }

    public static void atualizaQ1(ArrayList<Processo> fcfs) {
        Processo p;
        for (int i = 0; i < fcfs.size(); i++){
            p = fcfs.get(i);
            p.espera_Q1++;
        }
    }


    public void executarr() {
        this.tempo_executado++;
        this.executado_Q0++;
    }


    public void toIO(Queue<Processo> filas, Queue<Processo> io) {
        if (this.tempo_executado == this.burst && this.qtd_io > 0){
            Processo p = filas.poll();
            p.tempo_executado = 0;
            p.qtd_io--;
            io.add(p);
        }
        if (this.tempo_executado == this.burst && this.qtd_io==0) {
            this.tempo_executado = 0;
            filas.poll();
        }
    }

    public void toIO(ArrayList<Processo> filas, Queue<Processo> io) {
        if (this.tempo_executado == this.burst && this.qtd_io > 0){
            Processo p = filas.get(0);
            filas.remove(p);
            p.tempo_executado = 0;
            p.espera_Q1 = 0;
            p.executado_Q0 = 0;
            p.qtd_io--;
            io.add(p);
        }
        if (this.tempo_executado == this.burst && this.qtd_io==0) {
            filas.get(0);
            filas.remove(0);
        }
    }
    public void toFCFS(Queue<Processo> rr, ArrayList<Processo> fcfs) {
        if (this.executado_Q0 == 10 && rr.peek() != null){
            Processo p = rr.poll();
            fcfs.add(p);
        }
    }

    public static void toRR(ArrayList<Processo> fcfs, Queue<Processo> rr) {
        for (int i=0; i < fcfs.size(); i++){
            Processo p = fcfs.get(i);
            if (p.espera_Q1 == 30){
                fcfs.remove(p);
                p.espera_Q1 = 0;
                p.executado_Q0 = 0;
                rr.add(p);
                break;
            }
        }
    }

    public void executaIO(Queue<Processo> io, Queue<Processo> rr) {
        this.tempo_io++;
        if (this.tempo_io == 20) {
            Processo p = io.poll();
            p.tempo_io = 0;
            p.tempo_executado = 0;
            rr.add(p);
        }
    }

    public String getnome() {
        return nome;
    }

    public void settempo_q1(int i) {
        this.espera_Q1 = i;
    }

    public void executafcfs() {
        this.tempo_executado++;
    }
}
