import java.util.Queue;

public class Processo {
    private int burst, qtd_io, tempo_Q1, tempo_executado, tempo_io;
    private String nome;

    Processo (int burst, int io, String nome){
        this.burst = burst;
        this.qtd_io = io;
        this.nome = nome;
        this.tempo_Q1 = this.tempo_io = this.tempo_executado = 0;
    }

    public void imprimeprocesso (){ ///usado somente para teste
        System.out.println("Nome: " + nome + "quantidade de IO: " + qtd_io + "Tempo de Burst: " + burst + "Tempo: "
                            + tempo_Q1 + tempo_executado + tempo_io);
    }

    public void executa() {
        this.tempo_executado++;
    }


    public void toIO(Queue<Processo> filas, Queue<Processo> io) {
        if (this.tempo_executado == this.burst && this.qtd_io > 0){
            Processo p = filas.poll();
            p.tempo_executado = 0;
            p.qtd_io--;
            io.add(p);
        }
    }

    public void toFCFS(Queue<Processo> rr, Queue<Processo> fcfs) {
        if (this.tempo_executado == 20){
            Processo p = rr.poll();
            p.tempo_executado = 0;
            fcfs.add(p);
        }
    }

    public void toRR(Queue<Processo> fcfs, Queue<Processo> rr) {
        if (this.tempo_Q1 > 25){
            Processo p;
            p = fcfs.poll();
            p.tempo_Q1=0;
            rr.add(p);
        }
    }
}
