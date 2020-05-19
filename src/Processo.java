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


    public boolean toIO(Queue<Processo> rr, Queue<Processo> io) {
        if (this.tempo_executado == this.burst && this.qtd_io > 0){
            Processo p = rr.poll();
            p.tempo_executado = 0;
            p.qtd_io--;
            io.add(p);
            return true;
        }
        if (this.tempo_executado == this.burst && this.qtd_io==0) {
            this.tempo_executado = 0;
            rr.poll();
            return false;
        }
        return false;
    }

    public boolean toIO(ArrayList<Processo> fcfs, Queue<Processo> io) {
        if (this.tempo_executado == this.burst && this.qtd_io > 0){
            Processo p = fcfs.get(0);
            fcfs.remove(p);
            p.tempo_executado = 0;
            p.espera_Q1 = 0;
            p.executado_Q0 = 0;
            p.qtd_io--;
            io.add(p);
            return true;
        }
        if (this.tempo_executado == this.burst && this.qtd_io==0) {
            fcfs.get(0);
            fcfs.remove(0);
            return false;
        }
        return false;
    }
    public boolean toFCFS(Queue<Processo> rr, ArrayList<Processo> fcfs) {
        if (this.executado_Q0 == 10 && rr.peek() != null){
            Processo p = rr.poll();
            fcfs.add(p);
            return true;
        }
        return false;
    }

    public static boolean toRR(ArrayList<Processo> fcfs, Queue<Processo> rr) { // evitar o starvation
        for (int i=0; i < fcfs.size(); i++){
            Processo p = fcfs.get(i);
            if (p.espera_Q1 == 31){
                fcfs.remove(p);
                p.espera_Q1 = 0;
                p.executado_Q0 = 0;
                rr.add(p);
                return true;
            }
        }
        return false;
    }

    public boolean executaIO(Queue<Processo> io, Queue<Processo> rr) {
        this.tempo_io++;
        if (this.tempo_io == 20) {
            Processo p = io.poll();
            p.tempo_io = 0;
            p.tempo_executado = 0;
            p.executado_Q0 = 0;
            rr.add(p);
            return true;
        }
        return false;
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

    public void imprime(){
        System.out.println(this.getnome());
    }

    public int gettempo_executado() { return this.tempo_executado;
    }
}
